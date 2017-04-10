function ScreenModel() {
	var self = this;
	self.doctor = ko.observable(new Doctor());
	self.listDoctor = ko.observable(new ListDoctor());
}

ScreenModel.prototype.start = function() {
	var self = this;

};

ScreenModel.prototype.goCreateMode = function() {
	var self = this;
	self.doctor().clear();
}
ScreenModel.prototype.register = function() {
	var self = this;
	var data = {
			doctorId : self.doctor().userId(),
			name: self.doctor().name(),
			birthDay: self.doctor().birthDay(),
			password : self.doctor().password(),
			phoneNumber: self.doctor().phoneNumber(),
			email: self.doctor().email(),
			position: self.doctor().position(),
			addressWord: self.doctor().address(),
			sex: self.doctor().gender()	
	}
	services.queryInit(data);
}
ScreenModel.prototype.deleteDoctor = function() {
	var self = this;
}
function Doctor (){
		var self = this;
		self.userId = ko.observable('');
		self.name = ko.observable('');
		self.birthDay = ko.observable('');
		self.address = ko.observable('');
		self.email = ko.observable('');
		self.password = ko.observable('');
		self.confirmPassword = ko.observable('');
		self.phoneNumber = ko.observable('');
		self.position = ko.observable('');
		self.gender = ko.observable(true);
}
Doctor.prototype.clear = function(){
	var self = this;
	self.userId('');
	self.name('');
	self.birthDay('');
	self.address('');
	self.email('');
	self.password('');
	self.confirmPassword('');
	self.phoneNumber('');
	self.position('');
	self.gender(true);
}
function ListDoctor(){
	var self = this;
	self.items = ko.observableArray([]);
	self.selectedCode = ko.observable();
}
