var screen;
function ScreenModel() {
	var self = this;
	screen = self;
	self.xquangId = ko.observable('');
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
//ScreenModel.prototype.register = function() {
//	var self = this;
//	if(self.patient().result() != null){
//		var data = {
//				xquangId : screen.xquangId(),
//				result: self.patient().result()
//		}
//		services.updateXquang(data).done(function(res) {
//			alert(res);
//			self.listCare().reload();
////			self.listCare().select(res.userId);
//		}).fail(function(res){
//			alert(res.result);
//		});
//	}else{
//		alert("Bạn chưa điền kết luận khám. Bạn vui lòng nhập và lưu lại!");
//	}	
//}
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
Patient.prototype.reload = function(xquangId) {
	var self = this;
	var request = new Request();
	var dfd = $.Deferred();
	services.getXquangId(xquangId).done(function(res) {
		self.userId(res.userId);
		self.name(res.name);
		var date = new Date(res.dayCare);
		self.dayCare(request.formatDate(date, 'yyyy-MM-dd'));
		self.age(res.age);
		self.address(res.addressPatient);
		self.diagnose(res.diagnose);
		self.result(res.result);
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
		screen.xquangId(selectedCode);
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
		return self.name()
				+ '\u00A0'  + '\u00A0' + '\u00A0' +'\u00A0' +'\u00A0' +'\u00A0' + self.dayCare();
	}, self);
}
//var increasement = 10;
//var timer = function(){setInterval(resizeImage($("#image img")), 100);}
//function resizeImage(image){
//	var width = image.width();
//	
//	if(width > 400){
//		clearInterval(timer);
//	}else{
//		var height = image.height();
//		width += increasement;
//		height += increasement;
//		image.width(width);
//		image.height(height);
//	}
//	}
//$(function(){
//	$("#image img").hover(function(){
//		timer();
//		});
//	
//});