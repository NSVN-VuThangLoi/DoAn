function ScreenModel() {
	var self = this;
	self.patient = ko.observable(new Patient());
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
	if(self.patient().birthDay() ==""){
		alert("Bạn chưa nhập ngày sinh");
	}else if(self.patient().userId() ==""){
		alert("Bạn chưa nhập userId");
	}else if(self.patient().name() ==""){
		alert("Bạn chưa nhập name");
	}else if(self.patient().password() ==""){
		alert("Bạn chưa nhập password");
	}else{
		var data = {
				userId : self.patient().userId(),
				name: self.patient().name(),
				birthDay: self.patient().birthDay(),
				password : self.patient().password(),
				phoneNumber: self.patient().phoneNumber(),
				email: self.patient().email(),
				address: self.patient().address(),
				sex: self.patient().gender()	
		}
		services.insertPatient(data).done(function(res) {
			alert(res.result);
			self.patient().clear();
			self.listPatient().reload().done(function() {
				self.listPatient().select(res.userId);
			});
		}).fail(function(res){
			alert(res.result);
		});
	}
	
}
ScreenModel.prototype.deletePatient = function() {
	var self = this;
	services.removePatient(self.patient().userId()).done(function(res) {
		alert(res);
		self.listPatient().reload();
	}).fail(function(res){
		alert(res);
	});
	
}
function Patient(){
		var self = this;
		self.userId = ko.observable('');
		self.name = ko.observable('');
		self.birthDay = ko.observable(new Date());
		self.address = ko.observable('');
		self.email = ko.observable('');
		self.password = ko.observable('');
		self.confirmPassword = ko.observable('');
		self.phoneNumber = ko.observable('');
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
	self.gender('true');
}
Patient.prototype.reload = function(userId) {
	var self = this;
	var request = new Request();
	var dfd = $.Deferred();
	services.getPatient(userId).done(function(res) {
		self.userId(res.userId);
		self.name(res.name);
		var date = new Date(res.birthDay);
		self.birthDay(request.formatDate(date, 'yyyy-MM-dd'));
		self.address(res.address);
		self.email(res.email);
		self.phoneNumber(res.phoneNumber);
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
	services.getAllPatient().done(function(patterns) {
		if(patterns != null){
			self.items(patterns);
		}
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
function PatientListItem(userId, name) {
	var self = this;
	self.userId = ko.observable(userId);
	self.name = ko.observable(name);
	self.displayText = ko.computed(function() {
		return self.userId()
				+ '     ' + self.name();
	}, self);
}
