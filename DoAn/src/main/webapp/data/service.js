/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllDoctor : 'Doctor/getDoctor',
		registerDoctor : 'mn/pers/0202/command/personalmedical/add',
		registerFamilyMedicalHistory : 'mn/pers/0202/command/familymedical/add',
		queryInit : 'Doctor/insert'
	};

	var services = {};
	var request = new Request();
	services.queryInit = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.queryInit).done(function(data){
			d.resolve();
		}) ;
	};
	services.getAllDoctor = function() {
		 var d = $.Deferred();
		request.requestAjax(null,servicePath.getAllDoctor).done(function(data){
			var patterns = _.map(data, function(pattern){
				return new ListDoctor(pattern.code,pattern.name);
			});
			d.resolve(patterns);
		}) ;
		return dfd.promise();
	};
	return services;
})();
