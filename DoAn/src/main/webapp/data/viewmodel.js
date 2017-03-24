function ScreenModel() {
	var self = this;
	self.user = ko.observable("Thanh");
}

ScreenModel.prototype.start = function() {
	var self = this;

};
ScreenModel.prototype.get = function() {
	var self = this;
//	var data = {
//		type : "ThanhBau"
//	}
	services.queryInit();
};
