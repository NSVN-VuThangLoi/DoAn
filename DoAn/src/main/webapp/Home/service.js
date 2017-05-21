/* global nts, _ */

var services = (function() {

	var servicePath = {
			countMedicalExam : 'home/home'
	};

	var services = {};
	var request = new Request();
	services.countMedicalExam = function() {
		var d = $.Deferred();
		request.requestAjax(null,servicePath.countMedicalExam).done(function(res){
			d.resolve(res);
		}) ;
		return d.promise();
	};
	return services;
})();
