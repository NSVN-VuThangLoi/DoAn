/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllXquang: "xquang/getNonImage"
	};

	var services = {};
	var request = new Request();
	services.getAllXquang = function() {
		var d = $.Deferred();
		request.requestAjax(null,servicePath.getAllXquang).done(function(res){
			var patterns = [];
			for(i = 0; i < res.length; i++){
				patterns.push(new DoctorListItem(res[i].userId,request.formatDate(new Date(res[i].dayCare), 'yyyy-MM-dd')));
			}
			d.resolve(patterns);
		}) ;
		return d.promise();
	};

	return services;
})();
