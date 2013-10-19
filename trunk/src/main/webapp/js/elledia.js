function setFieldAsInvalid (id) {
	$("#" + id).addClass("is-error-element");
	$("#" + id).focus (function (){
		setFieldAsValid (id);
	});
}

function setFieldAsInvalid (id, resetId) {
	$("#" + id).addClass("is-error-element");
	$("#" + id).focus (function (){
		setFieldAsValid (id);
		try {
			$("#" + resetId).removeClass("label-danger");
			$("#" + resetId).html("");
		} catch (e) {}
	});
}

function setFieldAsValid (id) {
	$("#" + id).removeClass("is-error-element");
}
