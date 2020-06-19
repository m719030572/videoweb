function changeVerifyAction()
{
	var form = document.getElementById("signupForm");
	form.setAttribute("action", "register/getverify");
}

function changeSubmitAction()
{
	var form = document.getElementById("signupForm");
	window.alert(form.getAttribute("action"));
	form.setAttribute("action", "registsader/dealregister");
	form.setAttribute("th:action", "regissstsader/dealregister");
}
function setEmail()
{
	var aa=verify.email;
}