
$(document).ready(function(){
	var address = $("#inputId").val();
	document.location.href = "https://blockchain.info/payment_request?address="+address;
});