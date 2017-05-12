/* global nts, _ */

var services = (function() {

	var servicePath = {
		insertDiagnose : 'diagnose/insert',
		getUserId: 'Patient/getPatient'
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
	services.getUserId = function(data){
		var d = $.Deferred();
		request.requestAjax(data,servicePath.getUserId).done(function(res){
			d.resolve(res);
		});
		return d.promise();
	}
	return services;
})();
