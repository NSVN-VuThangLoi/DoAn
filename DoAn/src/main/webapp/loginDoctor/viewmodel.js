function ScreenModel() {
	var self = this;
	
	self.userId = ko.observable();
	self.password = ko.observable();
}

ScreenModel.prototype.start = function() {
	var self = this;
	var dfd = $.Deferred();
	$("#UserId").focus();
	return dfd.promise();
};

ScreenModel.prototype.signIn = function() {
	var self = this;
	var data = {
			userId: self.userId(),
			password: self.password()
	}
	services.getDoctor(data).done(function(res){
		if(res.nameNotice == "Đăng nhập thành công"){
			alert(res.nameNotice);
			window.location.href='http://localhost:8080/DoAn/sendfile/index.xhtml?preUrl='+window.location.href;
		}else{
			alert(res.nameNotice);
		}
	}).fail(function(res){
		alert(res);
	});
}
