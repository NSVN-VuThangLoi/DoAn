/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllXquang: "xquang/getNonImage",
		getXquangId:  "xquang/getXquangId"
	};

	var services = {};
	var request = new Request();
	services.getAllXquang = function() {
		var d = $.Deferred();
		request.requestAjax(null,servicePath.getAllXquang).done(function(res){
			var patterns = [];
			if(res != undefined){
				for(i = 0; i < res.length; i++){
					patterns.push(new DoctorListItem(res[i].xquangId,res[i].name,request.formatDate(new Date(res[i].dayCare), 'yyyy-MM-dd')));
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
