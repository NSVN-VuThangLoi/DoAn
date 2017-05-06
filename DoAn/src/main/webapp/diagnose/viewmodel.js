function ScreenModel() {
	var self = this;
	self.doctor = ko.observable(new Doctor());
	
}

ScreenModel.prototype.start = function() {
	var self = this;
	var dfd = $.Deferred();
	$("#userId").focus();
	return dfd.promise();
};

ScreenModel.prototype.goCreateMode = function() {
	var self = this;
	self.doctor().clear();
}
ScreenModel.prototype.register = function() {
	var self = this;
	if(self.doctor().userId() ==""){
		alert("Bạn chưa nhập userId");
	}else if(self.doctor().name() ==""){
		alert("Bạn chưa nhập name");
	}else{
		var data = {
				userId : self.doctor().userId(),
				name: self.doctor().name(),
				diagnose: self.doctor().diagnose(),
				supersonic:  self.doctor().supersonic(),
				takeXQuang:  self.doctor().takeXQuang(),
				ctScanner: self.doctor().ctScanner()	
		}
		services.insertDiagnose(data).done(function(res) {
			alert(res);
			self.doctor().clear();
		}).fail(function(res){
			alert(res);
		});
	}
}
function Doctor (){
		var self = this;
		self.userId = ko.observable('');
		self.name = ko.observable('');
		self.diagnose = ko.observable('');
		self.supersonic = ko.observable(false);
		self.takeXQuang = ko.observable(false);
		self.ctScanner = ko.observable(false);
}
Doctor.prototype.clear = function(){
	var self = this;
	self.userId('');
	self.name('');
	self.diagnose('');
	self.supersonic(false);
	self.takeXQuang(false);
	self.ctScanner(false);
}
