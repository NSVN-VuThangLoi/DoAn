/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllDoctor : 'bloodTest/getBloodfollowDoctorId',
		getBloodTestId : 'bloodTest/getBloodtest',
		updateBloodtest : 'bloodTest/updateresultblootest'
	};

	var services = {};
	var request = new Request();
	services.updateBloodtest = function(data) {
		var d = $.Deferred();
		request.requestAjax(data,servicePath.updateBloodtest).done(function(res){
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
				patterns.push(new DoctorListItem(data[i].bloodtestId,data[i].name));
			}
			
			d.resolve(patterns);
		}) ;
		return d.promise();
	};
	
	services.getBloodTestId = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.getBloodTestId).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	
	return services;
})();
