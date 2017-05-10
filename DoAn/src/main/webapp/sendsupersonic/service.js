/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllSupersonic: "supersonic/getNonImage",
		getSupersonicId:  "supersonic/getSupersonicId"
	};

	var services = {};
	var request = new Request();
	services.getAllSupersonic = function() {
		var d = $.Deferred();
		request.requestAjax(null,servicePath.getAllSupersonic).done(function(res){
			var patterns = [];
			if(res != undefined){
				for(i = 0; i < res.length; i++){
					patterns.push(new DoctorListItem(res[i].supersonicId,request.formatDate(new Date(res[i].dayCare), 'yyyy-MM-dd')));
				}
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
