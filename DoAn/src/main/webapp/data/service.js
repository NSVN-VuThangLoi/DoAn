/* global nts, _ */

var services = (function() {

	var servicePath = {
		getDiseaseData : 'mn/pers/0202/query/medical/search',
		registerPersonalMedicalHistory : 'mn/pers/0202/command/personalmedical/add',
		registerFamilyMedicalHistory : 'mn/pers/0202/command/familymedical/add',
		queryInit : 'mn/pers/0202/query/init'
	};

	var services = {};
	services.queryInit = function(data) {
		 $.ajax({
             url: "http://localhost:8080/Note/Demo/Login/get",
             type: "post",
             contentType: "application/json",
             data: JSON.stringify(data),
             dataType : 'json',
             success: function (result) {
            	 if(result.detail == null){
            		 window.location.href = "/Note/index.html";
            	 }
            	 else{
            		 alert(result.detail);
            	 }
             }
         });
	};

	return services;
})();
