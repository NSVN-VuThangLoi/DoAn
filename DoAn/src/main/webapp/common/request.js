function Request() {
	var self = this;
}
Request.prototype.requestAjax = function(data, path) {
	var self = this;
	var d = $.Deferred();
	if (typeof data === 'object') {
        data = JSON.stringify(data);
    }
	$.ajax({
		url :"http://localhost:8080/DoAn/Demo/" + path,
		type : "post",
		contentType : "application/json",
		data : data,
		dataType : 'json',
		success : function(result) {
			return result;
			d.resolve();
			d.reject(res);
		}
	}).done(function(res) {
		if (res === undefined) {
			d.resolve();
		} else if (res.businessException) {
			d.reject(res);
		} else {
			d.resolve(res);
		}
	}).fail(function(res) {
		d.resolve(res);
	});
	return d.promise();
};
Request.prototype.requestText = function(data, path) {
	var self = this;
	var d = $.Deferred();
	$.ajax({
		url :"http://localhost:8080/DoAn/Demo/" + path,
		type : "post",
		contentType : "text/plain",
		data : data,
		dataType : 'text',
		success : function(result) {
			return result;
			d.resolve();
			d.reject(res);
		}
	}).done(function(res) {
		if (res === undefined) {
			d.resolve();
		} else if (res.businessException) {
			d.reject(res);
		} else {
			d.resolve(res);
		}
	}).fail(function(res) {
		d.resolve(res);
	});
	return d.promise();
};

Request.prototype.formatDate = function(date, format) {
    if (!format)
        format = 'yyyy-MM-dd hh:mm:ss.SSS';
    format = format.replace(/yyyy/g, date.getFullYear());
    format = format.replace(/yy/g, ('0' + (date.getFullYear() % 100)).slice(-2));
    format = format.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
    format = format.replace(/dd/g, ('0' + date.getDate()).slice(-2));
    format = format.replace(/hh/g, ('0' + date.getHours()).slice(-2));
    format = format.replace(/mm/g, ('0' + date.getMinutes()).slice(-2));
    format = format.replace(/ss/g, ('0' + date.getSeconds()).slice(-2));
    if (format.match(/S/g)) {
        var milliSeconds = ('00' + date.getMilliseconds()).slice(-3);
        var length = format.match(/S/g).length;
        for (var i = 0; i < length; i++)
            format = format.replace(/S/, milliSeconds.substring(i, i + 1));
    }
    return format;
}