/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllDoctorId : 'xquang/getConformUserId',
		getXquangId:  "xquang/getXquangId"
//		updateXquang : 'xquang/updateXquang'
	};

	var services = {};
	var request = new Request();
//	services.updateXquang = function(data) {
//		var d = $.Deferred();
//		request.requestAjax(data,servicePath.updateXquang).done(function(res){
//			d.resolve(res);
//		}) ;
//		return d.promise();
//	};
//	
	services.getAllDoctorId = function() {
		 var d = $.Deferred();
		 var i;
		request.requestAjax(null,servicePath.getAllDoctorId).done(function(data){
			var patterns = [];
			for(i = 0; i < data.length; i++){
				patterns.push(new PatientListItem(data[i].xquangId,data[i].name,request.formatDate(new Date(data[i].dayCare), 'yyyy-MM-dd')));
			}
			
			d.resolve(patterns);
		}) ;
		return d.promise();
	};
	
	services.getXquangId = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.getXquangId).done(function(res){
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
