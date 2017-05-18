/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllNurse : 'Nurse/getAllNurse',
		getNurse : 'Nurse/getNurse',
		removeNurse : 'Nurse/removeDoctor',
		insertNurse : 'Nurse/insert'
	};

	var services = {};
	var request = new Request();
	services.insertNurse = function(data) {
		var d = $.Deferred();
		request.requestAjax(data,servicePath.insertNurse).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	
	services.getAllNurse = function() {
		 var d = $.Deferred();
		 var i;
		request.requestAjax(null,servicePath.getAllNurse).done(function(data){
			var patterns = [];
			for(i = 0; i < data.length; i++){
				patterns.push(new DoctorListItem(data[i].nurseId,data[i].name));
			}
			
			d.resolve(patterns);
		}) ;
		return d.promise();
	};
	
	services.getNurse = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.getNurse).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	services.removeNurse = function(data) {
		 var d = $.Deferred();
		request.requestText(data,servicePath.removeNurse).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	
	return services;
})();
