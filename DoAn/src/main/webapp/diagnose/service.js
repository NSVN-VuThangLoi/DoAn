/* global nts, _ */

var services = (function() {

	var servicePath = {
		insertDiagnose : 'diagnose/insert'
	};

	var services = {};
	var request = new Request();
	services.insertDiagnose = function(data) {
		var d = $.Deferred();
		request.requestAjax(data,servicePath.insertDiagnose).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	return services;
})();
