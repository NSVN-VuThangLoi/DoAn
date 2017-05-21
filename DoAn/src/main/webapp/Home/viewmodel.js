function ScreenModel() {
	var self = this;
	self.countXquang = ko.observable();
	self.countSupersonic = ko.observable();
	self.countBloodtest = ko.observable();
	
}

ScreenModel.prototype.start = function() {
	var self = this;
	self.reload();
};
ScreenModel.prototype.reload = function() {
	var self = this;
	services.countMedicalExam().done(function(res) {
		if(res != null){
			self.countXquang (res.countXquang);
			self.countSupersonic(res.countSupersonic);
			self.countBloodtest(res.countBloodTest);
		}else{
			alert("Bạn chưa đăng nhập");
		}
	});
}



