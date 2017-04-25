function ScreenModel() {
	var self = this;
	self.doctor = ko.observable(new listExamRecords());
	self.listPatient = ko.observable(new listPatient());
	self.listPatient().selectionChangedEvent.add(function(selectedCode) {
		if (selectedCode !== undefined) {
			self.doctor().reload(selectedCode);
		}
	});
	self.UploadedFile = ko.observable("");
}

ScreenModel.prototype.start = function() {
	var self = this;
	var dfd = $.Deferred();
	$.when(self.listPatient().reload()).done(function() {
		self.listPatient().selectFirst();
		dfd.resolve();
	});
	return dfd.promise();
};
ScreenModel.prototype.importFile = function(){
	var self = this;
	$("#file")[0].click();
}
ScreenModel.prototype.sendFile = function(){
	var self = this;
}
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
				sex: self.doctor().gender()	}
		services.insertDoctor(data).done(function(res) {
			alert(res.result);
			self.doctor().clear();
			self.listPatient().reload();
			self.listPatient().select(res.doctorId);
		}).fail(function(res){
			alert(res.result);
		});
}
ScreenModel.prototype.deleteDoctor = function() {
	var self = this;
	services.removeDoctor(self.doctor().userId()).done(function(res) {
		alert(res);
		self.listPatient().reload();
	}).fail(function(res){
		alert(res);
	});
}
function listExamRecords (){
		var self = this;
		self.userId = ko.observable('');
		self.name = ko.observable('');
		self.dayCare = ko.observable(new Date());
}
listExamRecords.prototype.clear = function(){
	var self = this;
	self.userId('');
	self.name('');
	self.dayCare('');
}
listExamRecords.prototype.reload = function(userId) {
	var self = this;
	var request = new Request();
	var dfd = $.Deferred();
	services.getDoctor(userId).done(function(res) {
		self.userId(res.doctorId);
		self.name(res.name);
		var date = new Date(res.birthDay);
		self.birthDay(request.formatDate(date, 'yyyy-MM-dd'));
		self.address(res.addressWord);
		self.email(res.email);
		self.phoneNumber(res.phoneNumber);
		self.position(res.position);
		if(res.sex){
			self.gender('true');
		}else{
			self.gender('false');
		}
		
		dfd.resolve();
	});
	return dfd.promise();
}
function listPatient(){
	var self = this;
	self.items = ko.observableArray([]);
	self.selectedCode = ko.observable();
	self.selectionChangedEvent = $.Callbacks();
	self.selectedCode.subscribe(function(selectedCode) {
		if (self.unselecting()) {
			self.unselecting(false);
			return;
		}
		if (self.items().length === 0) {
			screenModel.createMode();
			return;
		}
		if (selectedCode === undefined) {
			// when selected item is removed
			self.selectFirst();
			return;
		}

		self.selectionChangedEvent.fire(selectedCode);
	});
	self.unselecting = ko.observable(false);
}
listPatient.prototype.reload = function(){
	var self = this;
	var dfd = $.Deferred();
	services.getAllDoctor().done(function(patterns) {
		self.items(patterns);
		dfd.resolve();
	});
	return dfd.promise();
}
listPatient.prototype.selectFirst = function() {
	var self = this;
	self.select(self.items()[0].userId());

};
listPatient.prototype.select = function(code) {
	var self = this;
	self.selectedCode(code);
};
function DoctorListItem(userId, name) {
	var self = this;
	self.userId = ko.observable(userId);
	self.name = ko.observable(name);
	self.displayText = ko.computed(function() {
		return self.userId()
				+ '     ' + self.name();
	}, self);
}
