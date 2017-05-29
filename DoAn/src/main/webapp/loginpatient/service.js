/* global nts, _ */

var services = (function() {

	var servicePath = {
		getPatient : 'loginDoctor/checkPatient'
	};

	var services = {};
	var request = new Request();
	
	services.getPatient = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.getPatient).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	return services;
})();
