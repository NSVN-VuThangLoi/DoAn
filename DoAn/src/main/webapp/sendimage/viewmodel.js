var screnn;
function ScreenModel() {
	var self = this;
	screen = self;
	self.xquangId = ko.observable('');
	self.information = ko.observable(new information());
	self.listFile = ko.observable(new listFile());
	self.listFile().selectionChangedEvent.add(function(selectedCode) {
		if (selectedCode !== undefined) {
			self.information().reload(selectedCode);
		}
	});
}

ScreenModel.prototype.start = function() {
	var self = this;
	var dfd = $.Deferred();
	$.when(self.listFile().reload()).done(function() {
		self.listFile().selectFirst();
		dfd.resolve();
	});
	return dfd.promise();
};
function progress(e) {
    if (e.lengthComputable) {
        $('#progress_percent').text(Math.floor((e.loaded * 100) / e.total));
        $('progress').attr({value:e.loaded,max:e.total});
    }
}
ScreenModel.prototype.register = function() {
	var self = this;
	var file = $('input[name="upload_file"]').get(0).files[0];

    var formData = new FormData();
    formData.append('file', file);
    formData.append('xquangId',self.xquangId());
    $.ajax({
        url: "http://" + window.location.host + "DoAn/Demo/xquang/upload",
        type: 'POST',
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        success: function(res){
            alert('file upload complete');
            self.listFile().reload();
        },
        error: function(response){
            var error = "error";
            if (response.status === 409){
                error = response.responseText;
            }
            alert(error);
        },
        xhr: function() {
            var myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) {
                myXhr.upload.addEventListener('progress', progress, false);
            } else {
                console.log('Upload progress is not supported.');
            }
            return myXhr;
        }
    });
}
function information (){
		var self = this;
		var request = new Request();
		self.userId = ko.observable('');
		self.name = ko.observable('');
		self.dayCare = ko.observable(request.formatDate(new Date(), 'yyyy-MM-dd'));
}
information.prototype.clear = function(){
	var self = this;
	self.userId('');
	self.name('');
	self.dayCare('');
}
information.prototype.reload = function(xquangId) {
	var self = this;
	var request = new Request();
	var dfd = $.Deferred();
	services.getXquangId(xquangId).done(function(res) {
		self.userId(res.userId);
		self.name(res.name);
		var date = new Date(res.dayCare);
		self.dayCare(request.formatDate(date, 'yyyy-MM-dd'));
		dfd.resolve();
	});
	return dfd.promise();
}
function listFile(){
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
		screen.xquangId(selectedCode);
		self.selectionChangedEvent.fire(selectedCode);
	});
	self.unselecting = ko.observable(false);
}
listFile.prototype.reload = function(){
	var self = this;
	var dfd = $.Deferred();
	services.getAllXquang().done(function(patterns) {
		self.items(patterns);
		dfd.resolve();
	});
	return dfd.promise();
}
listFile.prototype.selectFirst = function() {
	var self = this;
	if(self.items().length != 0){
		self.select(self.items()[0].xquangId());
	}else{
		alert("Đã hết hồ sơ khám");
	}

};
listFile.prototype.select = function(code) {
	var self = this;
	self.selectedCode(code);
};
function DoctorListItem(xquangId, name,dayCare) {
	var self = this;
	self.xquangId = ko.observable(xquangId);
	self.name = ko.observable(name);
	self.dayCare = ko.observable(dayCare);
	self.displayText = ko.computed(function() {
		return self.dayCare()+ '\u00A0'  + '\u00A0' + '\u00A0' +'\u00A0' +'\u00A0' +'\u00A0' + self.name();
	}, self);
}
