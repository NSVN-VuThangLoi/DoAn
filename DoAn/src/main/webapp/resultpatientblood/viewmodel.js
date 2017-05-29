var screnn;
function ScreenModel() {
	var self = this;
	screnn = self;
	self.bloodtestId = ko.observable();
	self.doctor = ko.observable(new Doctor());
	self.listDoctor = ko.observable(new ListDoctor());
	self.bloodTest = ko.observable(new BloodTest());
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
		var data = {
				bloodTestId : screnn.bloodtestId(),
				result: self.doctor().result()	
		}
		services.updateBloodtest(data).done(function(res) {
			alert(res.result);
			self.listDoctor().clear();
			self.listDoctor().reload();
//			self.listDoctor().select(res.doctorId);
		}).fail(function(res){
			alert(res.result);
		});
}
function Doctor (){
		var self = this;
		var request = new Request();
		self.userId = ko.observable();
		self.name = ko.observable();
		self.birthDay = ko.observable(request.formatDate(new Date(), 'yyyy-MM-dd'));
		self.address = ko.observable('');
		self.gender = ko.observable('');
		self.result = ko.observable('');
}
Doctor.prototype.clear = function(){
	var self = this;
	self.userId('');
	self.name('');
	self.birthDay('');
	self.address('');
	self.gender('');
}
Doctor.prototype.reload = function(userId) {
	var self = this;
	var request = new Request();
	var dfd = $.Deferred();
	services.getBloodTestId(userId).done(function(res) {
		self.userId(res.userId);
		self.name(res.name);
		var date = new Date(res.dayCare);
		self.birthDay(request.formatDate(date, 'yyyy-MM-dd'));
		self.address(res.address);
		self.gender(res.gender == true? "Nam": "Nữ");
		
		screnn.bloodTest().valueUre(res.valueUre);
		screnn.bloodTest().valueFe(res.valueFe);
		screnn.bloodTest().valueGlucose(res.valueGlucose);
		screnn.bloodTest().valueMagie(res.valueMagie);
		screnn.bloodTest().valueCreatinin(res.valueCreatinin);
		screnn.bloodTest().valueAstGot(res.valueAstgot);
		screnn.bloodTest().valueAcidUric(res.valueAcidUric);
		screnn.bloodTest().valueAltGpt(res.valueAltgpt);
		screnn.bloodTest().valueBilirubinTp(res.valueBilirubintp);
		screnn.bloodTest().valueAmylase(res.valueAmylase);
		screnn.bloodTest().valueBilirubinTt(res.valueBilirubintt);
		
		screnn.bloodTest().valueCk(res.valueCk);
		screnn.bloodTest().valueBilirubinGt(res.valueBilirubingt);
		screnn.bloodTest().valueCkMb(res.valueCkmb);
		screnn.bloodTest().valueProteinTp(res.valueProteintp);
		screnn.bloodTest().valueLDH(res.valueLdh);
		screnn.bloodTest().valueAlbunmin(res.valueAlbunmin);
		screnn.bloodTest().valueGGT(res.valueGgt);
		screnn.bloodTest().valueGlobulin(res.valueGlobulin);
		
		screnn.bloodTest().valueCholinesterase(res.valueCholinesterase);
		screnn.bloodTest().valueRateAG(res.valueRateag);
		screnn.bloodTest().valuePhosphatase(res.valuePhosphatase);
		screnn.bloodTest().valueFibrinogen(res.valueFibrinogen);
		screnn.bloodTest().valueCholesterol(res.valueCholesterol);
		screnn.bloodTest().valuePHArtery(res.valuePhArtery);
		screnn.bloodTest().valueTriglycerid(res.valueTriglycerid);
		
		screnn.bloodTest().valuePCO2(res.valuePco2);
		screnn.bloodTest().valueHDLcho(res.valueHdlcho);
		screnn.bloodTest().valuePO2Artery(res.valuePo2Artery);
		screnn.bloodTest().valueLDLCho(res.valueLdlCho);
		screnn.bloodTest().valueStandardHCO3(res.valueStandardHco3);
		screnn.bloodTest().valueNaPlus(res.valueNaplus);
		
		screnn.bloodTest().valueAlkalineBalance(res.valueAlkalineBalance);
		screnn.bloodTest().valueKPlus(res.valueKplus);
		screnn.bloodTest().valueClSubtract(res.valueClSubtract);
		screnn.bloodTest().valueCalci(res.valueCalci);
		screnn.bloodTest().valueCalciIon(res.valueCalciIon);
		screnn.bloodTest().valuePhosho(res.valuePhosho);
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
			return;
		}
		if (selectedCode === undefined) {
			// when selected item is removed
			self.selectFirst();
			return;
		}
		screnn.bloodtestId(selectedCode);
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
	if(self.items().length != 0){
		self.select(self.items()[0].userId());
	}else{
		alert("Đã hết dữ liệu");
	}
};
ListDoctor.prototype.select = function(code) {
	var self = this;
	self.selectedCode(code);
};
function DoctorListItem(userId, name,dayCare) {
	var self = this;
	self.userId = ko.observable(userId);
	self.name = ko.observable(name);
	self.dayCare = ko.observable(dayCare);
	self.displayText = ko.computed(function() {
		return self.dayCare()
				+ '\u00A0'  + '\u00A0' + '\u00A0' +'\u00A0' +'\u00A0' +'\u00A0'+ self.name();
	}, self);
}
function BloodTest(){
	var self = this;
	self.valueUre = ko.observable("");
	self.valueFe = ko.observable("");
	self.valueGlucose = ko.observable("");
	self.valueMagie = ko.observable("");
	self.valueCreatinin = ko.observable("");
	self.valueAstGot = ko.observable("");
	self.valueAcidUric = ko.observable("");
	self.valueAltGpt = ko.observable("");
	self.valueBilirubinTp = ko.observable("");
	self.valueAmylase = ko.observable("");
	self.valueBilirubinTt = ko.observable("");
	
	self.valueCk = ko.observable("");
	self.valueBilirubinGt = ko.observable("");
	self.valueCkMb = ko.observable("");
	self.valueProteinTp = ko.observable("");
	self.valueLDH = ko.observable("");
	self.valueAlbunmin = ko.observable("");
	self.valueGGT = ko.observable("");
	self.valueGlobulin = ko.observable("");
	
	self.valueCholinesterase = ko.observable("");
	self.valueRateAG = ko.observable("");
	self.valuePhosphatase = ko.observable("");
	self.valueFibrinogen = ko.observable("");
	self.valueCholesterol = ko.observable("");
	self.valuePHArtery = ko.observable("");
	self.valueTriglycerid = ko.observable("");
	
	self.valuePCO2 = ko.observable("");
	self.valueHDLcho = ko.observable("");
	self.valuePO2Artery = ko.observable("");
	self.valueLDLCho = ko.observable("");
	self.valueStandardHCO3 = ko.observable("");
	self.valueNaPlus = ko.observable("");
	
	self.valueAlkalineBalance = ko.observable("");
	self.valueKPlus = ko.observable("");
	self.valueClSubtract = ko.observable("");
	self.valueCalci = ko.observable("");
	self.valueCalciIon = ko.observable("");
	self.valuePhosho = ko.observable("");
	
}