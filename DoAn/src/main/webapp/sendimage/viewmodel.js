function ScreenModel() {
	var self = this;
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
    formData.append('userId', self.information().userId());
    $.ajax({
        url: 'http://localhost:8080/Note/Demo/login/upload',
        type: 'POST',
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        success: function(){
            alert('file upload complete');
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
		self.userId = ko.observable('');
		self.name = ko.observable('');
		self.dayCare = ko.observable(new Date());
}
information.prototype.clear = function(){
	var self = this;
	self.userId('');
	self.name('');
	self.dayCare('');
}
information.prototype.reload = function(userId) {
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

		self.selectionChangedEvent.fire(selectedCode);
	});
	self.unselecting = ko.observable(false);
}
listFile.prototype.reload = function(){
	var self = this;
	var dfd = $.Deferred();
	services.getAllDoctor().done(function(patterns) {
		self.items(patterns);
		dfd.resolve();
	});
	return dfd.promise();
}
listFile.prototype.selectFirst = function() {
	var self = this;
	self.select(self.items()[0].userId());

};
listFile.prototype.select = function(code) {
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
