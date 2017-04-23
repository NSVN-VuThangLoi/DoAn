/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllPatient : 'Patient/getAllPatient',
		getPatient : 'Patient/getPatient',
		removePatient : 'Patient/removePatient',
		insertPatient : 'Patient/insertPatient'
	};

	var services = {};
	var request = new Request();
	services.insertPatient = function(data) {
		var d = $.Deferred();
		request.requestAjax(data,servicePath.insertPatient).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	
	services.getAllPatient = function() {
		 var d = $.Deferred();
		 var i;
		request.requestAjax(null,servicePath.getAllPatient).done(function(data){
			var patterns = [];
			for(i = 0; i < data.length; i++){
				patterns.push(new PatientListItem(data[i].userId,data[i].name));
			}
			
			d.resolve(patterns);
		}) ;
		return d.promise();
	};
	
	services.getPatient = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.getPatient).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	services.removePatient = function(data) {
		 var d = $.Deferred();
		request.requestText(data,servicePath.removePatient).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	
	return services;
})();
