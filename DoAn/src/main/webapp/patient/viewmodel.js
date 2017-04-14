function ScreenModel() {
	var self = this;
	self.patient = ko.observable(new Doctor());
	self.listPatient = ko.observable(new ListPatient());
	self.listPatient().selectionChangedEvent.add(function(selectedCode) {
		if (selectedCode !== undefined) {
			self.patient().reload(selectedCode);
		}
	});
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

ScreenModel.prototype.goCreateMode = function() {
	var self = this;
	self.patient().clear();
}
ScreenModel.prototype.register = function() {
	var self = this;
	if(self.doctor().birthDay() ==""){
		alert("Bạn chưa nhập ngày sinh");
	}else if(self.doctor().userId() ==""){
		alert("Bạn chưa nhập userId");
	}else if(self.doctor().name() ==""){
		alert("Bạn chưa nhập name");
	}else if(self.doctor().password() ==""){
		alert("Bạn chưa nhập password");
	}else{
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
		services.insertDoctor(data).done(function(res) {
			alert(res.result);
			self.listPatient().reload();
		}).fail(function(res){
			alert(res.result);
		});
	}
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
function Patient (){
		var self = this;
		self.userId = ko.observable('');
		self.name = ko.observable('');
		self.birthDay = ko.observable(new Date());
		self.address = ko.observable('');
		self.email = ko.observable('');
		self.password = ko.observable('');
		self.confirmPassword = ko.observable('');
		self.phoneNumber = ko.observable('');
		self.position = ko.observable('');
		self.gender = ko.observable(true);
}
Patient.prototype.clear = function(){
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
	self.gender('true');
}
Patient.prototype.reload = function(userId) {
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
function ListPatient(){
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
ListPatient.prototype.reload = function(){
	var self = this;
	var dfd = $.Deferred();
	services.getAllDoctor().done(function(patterns) {
		self.items(patterns);
		dfd.resolve();
	});
	return dfd.promise();
}
ListPatient.prototype.selectFirst = function() {
	var self = this;
	self.select(self.items()[0].userId());

};
ListPatient.prototype.select = function(code) {
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
