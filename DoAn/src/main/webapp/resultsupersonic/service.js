/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllDoctorId : 'supersonic/getConformDoctorId',
		getSupersonicId:  "supersonic/getSupersonicId",
		updateSupersonic : 'supersonic/updateSupersonic'
	};

	var services = {};
	var request = new Request();
	services.updateSupersonic = function(data) {
		var d = $.Deferred();
		request.requestAjax(data,servicePath.updateSupersonic).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	
	services.getAllDoctorId = function() {
		 var d = $.Deferred();
		 var i;
		request.requestAjax(null,servicePath.getAllDoctorId).done(function(data){
			var patterns = [];
			for(i = 0; i < data.length; i++){
				patterns.push(new PatientListItem(data[i].supersonicId,data[i].name,request.formatDate(new Date(data[i].dayCare), 'yyyy-MM-dd')));
			}
			
			d.resolve(patterns);
		}) ;
		return d.promise();
	};
	
	services.getSupersonicId = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.getSupersonicId).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	
	return services;
})();
