<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="400688155365-8piogcl8vcj4vsdds8jq10mv7463pi2g.apps.googleusercontent.com">
</head>
<body>
<!--
	Client Secret: Tfs-KJtjCO2_htPmsE-jZw_k
	Client ID: 400688155365-8piogcl8vcj4vsdds8jq10mv7463pi2g.apps.googleusercontent.com
-->

<!-- Googles own class for their sign in button -->
<div class="g-signin2" data-onsuccess="onSignIn"></div>

<!-- Sends the token to the server to get the userID -->
<script>
	function onSignIn(googleUser) {
		var token = googleUser.getAuthResponse().id_token;
		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'http://localhost:8080/YOUR_OWN_DIRECTORY/LoginServlet', false);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onload = function() {
			console.log('Signed in as: ' + xhr.responseText);
		};
		xhr.send('token=' + token)
		
		// show authorize button
		
		// function for oauth
		// ...
		
	};
</script>
</body>
</html>