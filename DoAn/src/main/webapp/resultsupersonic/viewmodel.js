var screen;
function ScreenModel() {
	var self = this;
	screen = self;
	self.supersonicId = ko.observable('');
	self.urlImage = ko.observable("");
	self.patient = ko.observable(new Patient());
	self.listCare = ko.observable(new listCare());
	self.listCare().selectionChangedEvent.add(function(selectedCode) {
		if (selectedCode !== undefined) {
			self.patient().reload(selectedCode);
		}
	});
}

ScreenModel.prototype.start = function() {
	var self = this;
	var dfd = $.Deferred();
	$.when(self.listCare().reload()).done(function() {
		self.listCare().selectFirst();
		dfd.resolve();
	});
	return dfd.promise();
};
ScreenModel.prototype.register = function() {
	
	var self = this;
	if(self.patient().result() != null){
		var data = {
				supersonicId : screen.supersonicId(),
				result: self.patient().result()
		}
		services.updateSupersonic(data).done(function(res) {
			alert(res);
			self.listCare().reload();
		}).fail(function(res){
			alert(res.result);
		});
	}else{
		alert("Bạn chưa nhập kết luận khám. Bạn vui lòng nhập lại và lưu kết quả");
	}
		
}
function Patient(){
		var self = this;
		self.userId = ko.observable('');
		self.name = ko.observable('');
		self.age = ko.observable('');
		self.dayCare = ko.observable(new Date());
		self.address = ko.observable('');
		self.result = ko.observable('');
		self.gender = ko.observable(true);
		self.diagnose = ko.observable('');
}
Patient.prototype.clear = function(){
	var self = this;
	self.userId('');
	self.name('');
	self.dayCare('');
	self.address('');
	self.gender('true');
}
Patient.prototype.reload = function(supersonicId) {
	var self = this;
	var request = new Request();
	var dfd = $.Deferred();
	services.getSupersonicId(supersonicId).done(function(res) {
		self.userId(res.userId);
		self.name(res.name);
		var date = new Date(res.dayCare);
		self.dayCare(request.formatDate(date, 'yyyy-MM-dd'));
		self.age(res.age);
		self.address(res.addressPatient);
		self.diagnose(res.diagnose);
		if(res.sex){
			self.gender('true');
		}else{
			self.gender('false');
		}
		screen.urlImage(res.urlImage);
		dfd.resolve();
	});
	return dfd.promise();
}
function listCare(){
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
			return;
		}
		if (selectedCode === undefined) {
			// when selected item is removed
			self.selectFirst();
			return;
		}
		screen.supersonicId(selectedCode);
		self.selectionChangedEvent.fire(selectedCode);
	});
	self.unselecting = ko.observable(false);
}
listCare.prototype.reload = function(){
	var self = this;
	var dfd = $.Deferred();
	services.getAllDoctorId().done(function(patterns) {
		if(patterns != null){
			self.items(patterns);
		}
		dfd.resolve();
	});
	return dfd.promise();
}
listCare.prototype.selectFirst = function() {
	var self = this;
	if(self.items().length != 0){
		self.select(self.items()[0].xquangId());
	}else{
		alert("không có dữ liệu");
	}
	
};
listCare.prototype.select = function(code) {
	var self = this;
	self.selectedCode(code);
};
function PatientListItem(xquangId, name,dayCare) {
	var self = this;
	self.xquangId = ko.observable(xquangId);
	self.name = ko.observable(name);
	self.dayCare = ko.observable(dayCare);
	self.displayText = ko.computed(function() {
		return self.dayCare()+ '\u00A0'  + '\u00A0' + '\u00A0' +'\u00A0' +'\u00A0' +'\u00A0' + self.name();
	}, self);
}