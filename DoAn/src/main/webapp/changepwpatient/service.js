/* global nts, _ */

var services = (function() {

	var servicePath = {
			changePassword : 'changepassword/change'
	};

	var services = {};
	var request = new Request();
	services.changePassword = function(data) {
		var d = $.Deferred();
		request.requestAjax(data,servicePath.changePassword).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	return services;
})();
