$(function() {

	var screenModel = new ScreenModel();
	ko.applyBindings(screenModel);
	screenModel.start();
});