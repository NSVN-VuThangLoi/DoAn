/* global nts, _ */

var services = (function() {

	var servicePath = {
		getDoctor : 'loginDoctor/checkDoctor',
	};

	var services = {};
	var request = new Request();
	
	services.getDoctor = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.getDoctor).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	return services;
})();
