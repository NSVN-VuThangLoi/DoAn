function Request() {
	var self = this;
}
Request.prototype.requestAjax = function(data, path) {
	var self = this;
	var d = $.Deferred();
	$.ajax({
		url :"http://localhost:8080/DoAn/Demo/" + path,
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(data),
		dataType : 'json',
		success : function(result) {
			return result;
			d.resolve();
			d.reject(res);
		}
	}).done(function(res) {
		if (res === undefined) {
			d.resolve();
		} else if (res.businessException) {
			d.reject(res);
		} else {
			d.resolve(res);
		}
	}).fail(function(res) {
		// request.ajaxFailed(res, d)
	});
	return d.promise();
};