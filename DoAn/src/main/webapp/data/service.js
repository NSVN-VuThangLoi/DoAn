/* global nts, _ */

var services = (function() {

	var servicePath = {
		getDiseaseData : 'mn/pers/0202/query/medical/search',
		registerDoctor : 'mn/pers/0202/command/personalmedical/add',
		registerFamilyMedicalHistory : 'mn/pers/0202/command/familymedical/add',
		queryInit : 'http://localhost:8080/DoAn/Demo/Doctor/insert'
	};

	var services = {};
	var request = new Request();
	services.queryInit = function(data) {
		 var d = $.Deferred();
		request.requestAjax(data,servicePath.queryInit).done(function(data){
			var b = 1; 
			d.resolve();
		}) ;
	};

	return services;
})();
