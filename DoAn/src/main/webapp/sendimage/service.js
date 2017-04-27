/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllDoctor : 'Doctor/getAllDoctor',
		getDoctor : 'Doctor/getDoctor',
		removeDoctor : 'Doctor/removeDoctor',
		insertFile : 'File/insert'
	};

	var services = {};
	var request = new Request();
	services.insertFile = function(data) {
		var d = $.Deferred();
		request.requestFile(data,servicePath.insertFile).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	
	services.getAllDoctor = function() {
		 var d = $.Deferred();
		 var i;
		request.requestAjax(null,servicePath.getAllDoctor).done(function(data){
			var patterns = [];
			for(i = 0; i < data.length; i++){
				patterns.push(new DoctorListItem(data[i].doctorId,data[i].name));
			}
			
			d.resolve(patterns);
		}) ;
		return d.promise();
	};
	
	services.getDoctor = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.getDoctor).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	services.removeDoctor = function(data) {
		 var d = $.Deferred();
		request.requestText(data,servicePath.removeDoctor).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	
	return services;
})();
