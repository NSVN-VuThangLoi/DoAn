/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllXquang: "xquang/getNonImage"
	};

	var services = {};
	services.getAllXquang = function(data) {
		var d = $.Deferred();
		request.requestAjax(data,servicePath.getAllXquang).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};

	return services;
})();
