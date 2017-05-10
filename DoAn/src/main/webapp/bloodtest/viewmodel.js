function ScreenModel() {
	var self = this;
	self.doctor = ko.observable(new Doctor());
	self.listDoctor = ko.observable(new ListDoctor());
	self.listDoctor().selectionChangedEvent.add(function(selectedCode) {
		if (selectedCode !== undefined) {
			self.doctor().reload(selectedCode);
		}
	});
}

ScreenModel.prototype.start = function() {
	var self = this;
	var dfd = $.Deferred();
	$.when(self.listDoctor().reload()).done(function() {
		self.listDoctor().selectFirst();
		dfd.resolve();
	});
	return dfd.promise();
};

ScreenModel.prototype.goCreateMode = function() {
	var self = this;
	self.doctor().clear();
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
			self.doctor().clear();
			self.listDoctor().reload();
			self.listDoctor().select(res.doctorId);
		}).fail(function(res){
			alert(res.result);
		});
	}
}
ScreenModel.prototype.deleteDoctor = function() {
	var self = this;
	services.removeDoctor(self.doctor().userId()).done(function(res) {
		alert(res);
		self.listDoctor().reload();
	}).fail(function(res){
		alert(res);
	});
}
function Doctor (){
		var self = this;
		var request = new Request();
		self.userId = ko.observable('');
		self.name = ko.observable('');
		self.birthDay = ko.observable(request.formatDate(new Date(), 'yyyy-MM-dd'));
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
	self.gender('true');
}
Doctor.prototype.reload = function(userId) {
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
function ListDoctor(){
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
ListDoctor.prototype.reload = function(){
	var self = this;
	var dfd = $.Deferred();
	services.getAllDoctor().done(function(patterns) {
		self.items(patterns);
		dfd.resolve();
	});
	return dfd.promise();
}
ListDoctor.prototype.selectFirst = function() {
	var self = this;
	self.select(self.items()[0].userId());

};
ListDoctor.prototype.select = function(code) {
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
function bloodTest(){
	self.valueUre = ko.observable("123");
	self.valueFe = ko.observable("123");
	self.valueGlucose = ko.observable("123");
	self.valueMagie = ko.observable("123");
	self.valueCreatinin = ko.observable("123");
	self.valueAstGot = ko.observable("123");
	self.valueAcidUric = ko.observable("123");
	self.valueAltGpt = ko.observable("123");
	self.valueBilirubinTp = ko.observable("123");
	self.valueAmylase = ko.observable("123");
	self.valueBilirubinTt = ko.observable("123");
	
	self.valueCk = ko.observable("123");
	self.valueBilirubinGt = ko.observable("123");
	self.valueCkMb = ko.observable("123");
	self.valueProteinTp = ko.observable("123");
	self.valueLDH = ko.observable("123");
	self.valueAlbunmin = ko.observable("123");
	self.valueGGT = ko.observable("123");
	self.valueGlobulin = ko.observable("123");
	
	self.valueCholinesterase = ko.observable("123");
	self.valueRateAG = ko.observable("123");
	self.valuePhosphatase = ko.observable("123");
	self.valueFibrinogen = ko.observable("123");
	self.valueCholesterol = ko.observable("123");
	self.valuePHArtery = ko.observable("123");
	self.valueTriglycerid = ko.observable("123");
	
	self.valuePCO2 = ko.observable("123");
	self.valueHDLcho = ko.observable("123");
	self.valuePO2Artery = ko.observable("123");
	self.valueLDLCho = ko.observable("123");
	self.valueStandardHCO3 = ko.observable("123");
	self.valueNaPlus = ko.observable("123");
	
	self.valueAlkalineBalance = ko.observable("123");
	self.valueKPlus = ko.observable("123");
	self.valueClSubtract = ko.observable("123");
	self.valueCalci = ko.observable("123");
	self.valueCalciIon = ko.observable("123");
	self.valuePhosho = ko.observable("123");
	
	
}