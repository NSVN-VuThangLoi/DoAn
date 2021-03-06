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
ScreenModel.prototype.search = function(){
	var self = this;
	var request = new Request();
	if(self.userId() ==""){
		alert("Bạn chưa nhập userId");
	}else{
		services.getUserId(self.userId()).done(function(res){
			if(res == undefined){
				alert("không tìm thấy tài khoản này. Vui lòng nhập lại.")
			}else{
				self.name(res.name);
				self.address(res.address);
				self.gender(res.sex == true? "Nam": "Nữ");
				self.birthday(request.formatDate(new Date(res.birthDay), 'dd-MM-yyyy'));
			}
		});
	}
	
}
ScreenModel.prototype.register = function() {
	var self = this;
	if(self.doctor().userId() ==""){
		alert("Bạn chưa nhập userId");
	}else{
		var data = {
				userId : self.doctor().userId(),
				name: self.doctor().name(),
				diagnose: self.doctor().diagnose(),
				supersonic:  self.doctor().supersonic(),
				takeXQuang:  self.doctor().takeXQuang(),
				ctScanner: self.doctor().ctScanner(),
				bloodtest: self.doctor().bloodtest()
		}
		services.insertDiagnose(data).done(function(res) {
			alert(res.result);
			self.doctor().clear();
		}).fail(function(res){
			alert(res.result);
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
		self.bloodtest = ko.observable(false);
		self.address = ko.observable('');
		self.gender = ko.observable('');
		self.birthday = ko.observable('');
}
Doctor.prototype.clear = function(){
	var self = this;
	self.userId('');
	self.name('');
	self.diagnose('');
	self.supersonic(false);
	self.takeXQuang(false);
	self.ctScanner(false);
	self.address('');
	self.gender('');
	self.birthday('');
}

