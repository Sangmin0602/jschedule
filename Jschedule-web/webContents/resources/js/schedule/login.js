function showError ( errMsg) {
	$('#login-error')
		.html(errMsg)
		.fadeIn(300)
		.fadeOut(2000);
		
}
function processLogin() {
	var url = ctxpath + '/login.json';
	$.post(url, $('#frmLogin').serialize(), function(json){
		if ( json.success) {
			location.reload();
		} else {
			showError(json.cause);
		}
	});
}
$(document).ready(function(){
	
	$('#btnLogin').click (processLogin);
});