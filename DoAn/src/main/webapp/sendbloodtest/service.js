/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllBloodTest: "bloodTest/getIsBloodTest",
		getXquangId:  "xquang/getXquangId"
	};

	var services = {};
	var request = new Request();
	services.getAllBloodTest = function() {
		var d = $.Deferred();
		request.requestAjax(null,servicePath.getAllBloodTest).done(function(res){
			var patterns = [];
			if(res != undefined){
				for(i = 0; i < res.length; i++){
					patterns.push(new DoctorListItem(res[i].bloodtestId,request.formatDate(new Date(res[i].dayCare), 'yyyy-MM-dd')));
				}
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

	return services;
})();
