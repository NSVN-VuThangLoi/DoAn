function ScreenModel() {
	var self = this;
	self.passwordOld = ko.observable();
	self.passwordNew = ko.observable();
	self.passwordNewConfirm = ko.observable();
	
}

ScreenModel.prototype.start = function() {
	var self = this;
	var dfd = $.Deferred();
	$("#passwordOld").focus();
	return dfd.promise();
};

ScreenModel.prototype.goCreateMode = function() {
	var self = this;
	self.passwordOld('');
	self.passwordNew('');
	self.passwordNewConfirm('');
}

ScreenModel.prototype.register = function() {
	var self = this;
	if(self.passwordOld ==""){
		alert("Bạn chưa nhập mật khẩu cũ");
	}else if(self.passwordNew ==""){
		alert("Bạn chưa nhập mật khẩu mới");
	}else if(self.passwordNewConfirm ==""){
		alert("Bạn chưa nhập mật khẩu nhập lại");
	}else{
		var data = {
				passwordOld : self.passwordOld(),
				passwordConfirm: self.passwordNewConfirm(),
				passwordNew: self.passwordNew(),
		}
		services.changePassword(data).done(function(res) {
			if(res.result =="PW0001"){
				alert("Mật khẩu cũ không trùng với mật khẩu hiện tại lưu trong cơ sở dữ liệu. Bạn vui lòng nhập lại!");
			}
			if(res.result =="PW0002"){
				alert("Mật khẩu cũ trùng mật khẩu mới. Bạn vui lòng nhập lại!")
			}
			if(res.result =="PW0003"){
				alert("Mật khẩu mới và nhập lại mật khẩu mới không giống nhau. Bạn vui lòng nhập lại!");
			}
			if(res.result =="PW1001"){
				alert("Bạn đã thay đổi mật khẩu thành công");
			}
			if(res.result == "PW1111"){
				alert("Bạn chưa đăng nhập");
			}
		}).fail(function(res){
			alert(res.result);
		});
	}
}


