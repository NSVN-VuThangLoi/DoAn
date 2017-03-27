var hust = hust || {};

hust.request = (function () {
    var consts = {
        path: {
            webapi: 'webapi',
            sessiontimeout: '/error/sessiontimeout/index.xhtml'
        }
    }

    // SubSessionIDのリポジトリ
    // ひとまずSessionStorageで良さそうだが、もし何か問題があったら実装を変更する
    var subSessionIdRepository = {
        get: function () {
            return hust.sessionStorage.getItem(hust.names.sessionStorage.ssid);
        },
        set: function (subSessionId) {
        	hust.sessionStorage.setItem(hust.names.sessionStorage.ssid, subSessionId);
        },
        hasSubSession: function () {
            var subSessionId = hust.sessionStorage.getItem(nts.names.sessionStorage.ssid);
            return !hust.util.isNullOrUndefined(subSessionId);
        }
    };

    // CSRF対策トークンのリポジトリ
    var CsrfTokenRepository = {
        get: function () {
            return hust.sessionStorage.getItem(hust.names.sessionStorage.csrfToken);
        },
        set: function (csrfToken) {
        	hust.sessionStorage.setItem(hust.names.sessionStorage.csrfToken, csrfToken);
        }
    };

    /**
     * Query string on HTTP request
     */
    function QueryString () {
        this.items = {};
    }

    /**
     * Instantiate QueryString from url
     */
    QueryString.parseUrl = function (url) {
        var instance = new QueryString();

        var queryString = url.split('?')[1];
        if (queryString) {
            var queryEntries = queryString.split('&');
            for (var i = 0; i < queryEntries.length; i++) {
                var entryParts = queryEntries[i].split('=');
                instance.set(entryParts[0], entryParts[1]);
            }
        }
        return instance;
    };

    /**
     * Instantiate QueryString from an object with key-value entries
     */
    QueryString.build = function (entriesObj) {
        var instance = new QueryString();

        for (var key in entriesObj) {
            instance.set(key, entriesObj[key]);
        }

        return instance;
    };

    /**
     * Get item value
     */
    QueryString.prototype.get = function (key) {
        return this.items[key];
    };

    /**
     * Set item
     */
    QueryString.prototype.set = function (key, value) {
        if (key === null || key === undefined || key === '') {
            return;
        }
        this.items[key] = value;
    };

    /**
     * Remove item
     */
    QueryString.prototype.remove = function (key) {
        delete this.items[key];
    };

    /**
     * Merge from another QueryString
     */
    QueryString.prototype.mergeFrom = function (otherObj) {
        for (var otherKey in otherObj.items) {
            this.set(otherKey, otherObj.items[otherKey]);
        }
    };

    /**
     * Return a number of items
     */
    QueryString.prototype.count = function () {
        var count = 0;
        for (var key in this.items) {
            count++;
        }
        return count;
    };

    /**
     * Return true if QueryString has any items
     */
    QueryString.prototype.hasItems = function () {
        return this.count() !== 0;
    };

    /**
     * Serialize QueryString to url
     */
    QueryString.prototype.serialize = function () {
        var entryStrings = [];
        for (var key in this.items) {
            entryStrings.push(key + '=' + this.items[key]);
        }

        return entryStrings.join('&');
    };

    /**
     * URL & QueryString
     */
    function Locator (url) {
        this.rawUrl = url.split('?')[0];
        this.queryString = QueryString.parseUrl(url);
    }

    Locator.prototype.mergeRelativePath = function (relativePath) {
        var stack = this.rawUrl.split('/');
        var parts = relativePath.split('/');
        var queryStringToAdd = QueryString.parseUrl(relativePath);

        // 最後のファイル名は除外
        // (最後がフォルダ名でしかも / で終わっていない場合は考慮しない)
        stack.pop();
        
        // relativePathの先頭が '/' の場合、それを取り除く
        if (parts[0] === '') {
        	parts.shift();
        }

        for (var i = 0; i < parts.length; i++) {
            if (parts[i] === '.')
                continue;
            if (parts[i] === '..')
                stack.pop();
            else
                stack.push(parts[i]);
        }
        
        queryStringToAdd.mergeFrom(this.queryString);

        return new Locator(stack.join('/') + '?' + queryStringToAdd.serialize());
    };

    Locator.prototype.serialize = function () {
        if (this.queryString.hasItems()) {
            return this.rawUrl + '?' + this.queryString.serialize();
        } else {
            return this.rawUrl;
        }
    };

    Locator.prototype.appRootRelative = function () {
        var path = this.rawUrl.slice(request.location.appRoot.length);
        if (path.charAt(0) !== '/') {
            path = '/' + path;
        }
        return path;
    };

    function AjaxMock (originalMethod) {
        this.originalMethod = originalMethod;
        this.mocks = [];
    }

    AjaxMock.prototype.set = function (mock) {
        mock.res = (typeof mock.res !== 'function') ? (function (res) {
            return function () {
                return res;
            };
        })(mock.res) : mock.res;

        if (mock.err !== undefined) {
            mock.err = (typeof mock.err !== 'function') ? (function (err) {
                return function () {
                    return err;
                };
            })(mock.err) : mock.err;
        }

        this.mocks.push(mock);
        return this;
    };

    AjaxMock.prototype.callAjax = function (path, options) {
        var dfd = $.Deferred();

        var mocked = false;
        for (var i = 0; i < this.mocks.length; i++) {
            var mock = this.mocks[i];
            var found = false;
            try {
                found = mock.req(path, options);
            } catch (e) {}
            if (found) {
                mocked = true;
                setTimeout(function () {
                    if (mock.err)
                        dfd.reject(mock.err());
                    else
                        dfd.resolve(mock.res());
                }, 0);
                break;
            }
        }

        if (!mocked) {
            this.originalMethod(path, options).done(function (res) {
                dfd.resolve(res);
            }).fail(function (res) {
                dfd.reject(res);
            });
        }

        return dfd.promise();
    };

    function setupLocatorWithSubSession (baseUrl, path) {
        if (path.charAt(0) === '/') {
            path = path.slice(1);
        }
        var locator = new Locator(baseUrl).mergeRelativePath(path);

        return locator;
    }

    var currentLocator = new Locator(window.location.href);
    var applicationRootPath = currentLocator.mergeRelativePath(window.rootPath).rawUrl;

    var request = {
        QueryString: QueryString,
        Locator: Locator,
        location: {
            current: currentLocator,
            appRoot: applicationRootPath,
            ajaxRoot: applicationRootPath + consts.path.webapi + '/'
        }
    };

    request.login = function (path, parameters) {

        return request.ajax(path, parameters).then(function (res) {
            return request.ajax(res.loginWebService, {
                ticket: res.ticket
            });
        }).then(function (res) {
            if (res.csrfToken === undefined) {
                throw new Error('CSRF protection token is required');
            }

            hust.sessionStorage.setItem(hust.names.sessionStorage.encryptedUserInfo, res.encryptedUserInfo);

            CsrfTokenRepository.set(res.csrfToken);
            request.jump(res.welcomePage);
        });
    };

    request.loggedIn = function (parameters) {
        if (parameters.csrfToken === undefined) {
            throw new Error('CSRF protection token is required');
        }

        hust.sessionStorage.setItem(hust.names.sessionStorage.pathToTop, parameters.welcomePage);
        hust.sessionStorage.setItem(hust.names.sessionStorage.encryptedUserInfo, parameters.encryptedUserInfo);
        CsrfTokenRepository.set(parameters.csrfToken);
    };

    request.logout = function (returnsToLogin) {
        returnsToLogin = returnsToLogin !== false;

        var command = {
            userInfo: hust.sessionStorage.getItem(hust.names.sessionStorage.encryptedUserInfo)
        };

        request.ajax('/op/domain/gateway/command/logout', command).done(function (res) {
            if (returnsToLogin) {
                request.jump(res.pathToLoginPage);
            }
        });
    };

    request.getSubSession = function () {
        var d = $.Deferred();

        // No have subsessionId
        if (!subSessionIdRepository.hasSubSession()) {
        	hust.request.ajax('/subsession/get', {
                type: 'GET'
            }).done(function (res) {
                if (res.subSessionId !== null && res.subSessionId !== 'undefined' && res.subSessionId !== '') {
                	hust.names.queryString.subSessionId = res.queryString;
                    subSessionIdRepository.set(res.subSessionId);
                }
                d.resolve(res);
            }).fail(d.reject);
        } else {
            setTimeout(function () {
                d.resolve();
            }, 0);
        }

        return d.promise();
    };

    /**
     * Call web service by ajax
     * 
     * @param {type}
     *            path to web service
     * @param {type}
     *            data is sent to web service
     * @param {type}
     *            options
     * @returns jQuery.Deferred
     */
    request.ajax = function (path, data, options) {
        var d = $.Deferred();
        options = options || {};

        if (data === undefined) {
            data = {};
        }

        if (typeof data === 'object') {
            data = JSON.stringify(data);
        }
//        console.log(data);
        var locator = setupLocatorWithSubSession(hust.request.location.ajaxRoot, path);

        $.ajax({
            type: options.method || 'POST',
            contentType: options.contentType || 'application/json',
            url: locator.serialize(),
            dataType: options.dataType || 'json',
            data: data,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(hust.names.requestHeader.csrfToken, CsrfTokenRepository.get());
                xhr.setRequestHeader(hust.names.requestHeader.pagePath, request.location.current.rawUrl);

                // Add sub session id
                if (subSessionIdRepository.hasSubSession()) {
                    xhr.setRequestHeader(hust.names.requestHeader.subSessionId, subSessionIdRepository.get());
                }
            }
        }).done(function (res) {
            if (res === undefined) {
                // empty response
                d.resolve();
            } else if (res.businessException) {
                d.reject(res);
            } else {
                d.resolve(res);
            }
        }).fail(function (res) {
            request.ajaxFailed(res, d)
        });

        return d.promise();
    };

    request.ajaxFailed = function (res, ajaxDeferred) {
        if (res.responseJSON) {
            var response = res.responseJSON;

            if (response.sessionTimeout === true) {
            	hust.request.jump(consts.path.sessiontimeout);
                return;
            } else if (response.csrfError === true) {
            	hust.ui.confirmSaveDisable();
                request.jumpToSystemError();
                return;
            } else if (response.optimisticLock === true) {
            	hust.ui.alert(hust.ui.message('Com_0000113')).then(function () {
                    ajaxDeferred.reject(response);
                });
                return;
            } else if (response.manipulationRejected === true) {
                nts.ui.alert(response.message).then(function () {
                    ajaxDeferred.reject(response);
                });
                return;
            } else if (response.targetNotFound === true) {
                nts.ui.alert(nts.ui.message('Com_0000139')).then(function () {
                    ajaxDeferred.reject(response);
                });
                return;
            } else if (response.identifiedSystemError === true) {
            	nts.ui.alert(nts.ui.message(response.messageId)).then(function () {
                    ajaxDeferred.reject(response);
            	});
            }
        }

        //console.log('ajax error');
        //console.log(res);
        //nts.ui.confirmSaveDisable();
        request.jumpToSystemError();
    };

    /**
     * Jump to specified page.
     * 
     * @param {String}
     *            path path to destination
     * @returns {undefined}
     */
    request.jump = function (path, data) {
        
        path = new Locator(applicationRootPath).mergeRelativePath(path).appRootRelative();

        var locator = setupLocatorWithSubSession(nts.request.location.appRoot, path);

        var crossPageTemp = {
            from: request.location.current.appRootRelative(),
            path: path,
            data: data
        };

        nts.sessionStorage.setItem(nts.names.sessionStorage.crossPageTemp, JSON.stringify(crossPageTemp));

        nts.ui.jump(locator.serialize());
    };

    request.jumpImmediately = function (path) {
        if (!/^http/.test(path)) {
            path = setupLocatorWithSubSession(nts.request.location.ajaxRoot, path).serialize();
        }

        nts.ui.jump(path, true);
    };
    
    request.newWindow = function (path) {
        if (!/^http/.test(path)) {
            path = new Locator(applicationRootPath).mergeRelativePath(path).serialize();
        }

        return window.open(path);
    }

    /**
     * Jump to top page.
     */
    request.jumpToTop = function () {
        var pathToTop = nts.sessionStorage.getItem(nts.names.sessionStorage.pathToTop);
        request.jump(pathToTop);
    };
    
    /**
     * Jump to system error page.
     */
    request.jumpToSystemError = function () {
    	nts.sessionStorage.setItemStringifyJson(nts.names.sessionStorage.systemError, {
    		time: nts.text.formatDate(new Date(), 'yyyy/MM/dd hh:mm:ss'),
    		url: location.href
    	});
    	
    	nts.ui.confirmSaveDisable();
    	request.jump('/error/system/index.xhtml');
    };
    
    /**
     * Jump to previous page.
     */
    request.back = function () {
        request.jump(request.pathToBack);
    };
    
    /**
     * Set previous page to jump.
     */
    request.setBack = function (pathToBack) {
        request.pathToBack = pathToBack;
    };

    /**
     * Download a file from specified path.
     * 
     * @param {String}
     *            path path to download
     * @returns {undefined}
     */
    request.downloadFrom = function (path) {
        request.jumpImmediately(path);
    };

    /**
     * Show modal dialog.
     * 
     * @param {String}
     *            path to the page that will be shown on dialog
     * @param {type}
     *            options
     * @returns handler
     */
    request.modalDialog = function (path, options) {
        var locator = setupLocatorWithSubSession(nts.request.location.appRoot, path);
        return nts.ui.modalDialog(locator.serialize(), options);
    };

    /**
     * Show modeless dialog.
     * 
     * @param {String}
     *            path to the page that will be shown on dialog
     * @param {type}
     *            options
     * @returns handler
     */
    request.modelessDialog = function (path, options) {
        var locator = setupLocatorWithSubSession(nts.request.location.appRoot, path);
        return nts.ui.modelessDialog(locator.serialize(), options);
    };

    /**
     * Mock request.ajax
     * 
     * @returns mock builder
     */
    request.ajaxmock = function () {
        var ajaxMock = new AjaxMock(this.ajax);
        this.ajax = function () {
            return ajaxMock.callAjax.apply(ajaxMock, arguments);
        };

        return ajaxMock;
    };

    return request;
})();
