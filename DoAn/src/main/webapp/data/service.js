/* global nts, _ */

var services = (function() {

	var servicePath = {
		getAllDoctor : 'Doctor/getAllDoctor',
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
		 var i;
		request.requestAjax(null,servicePath.getAllDoctor).done(function(data){
			var patterns = [];
			for(i = 0; i < data.length; i++){
				patterns.push(new DoctorListItem(data[i].doctorId,data[i].name));
			}
			
			d.resolve(patterns);
		}) ;
		return d.promise();
	};
	return services;
})();
