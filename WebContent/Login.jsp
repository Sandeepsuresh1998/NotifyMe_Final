<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NotifyMe - Login</title>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="OAuth.js"></script>
<meta name="google-signin-client_id"
	content="943857468024-saaqgfpqu23qqlnc7ce0i5nok6na6tif.apps.googleusercontent.com">
</head>
<body>
	<!--
	Client Secret: OUyYhuHPbG3T5azvLD0hl_j2
	Client ID: 496920584851-fmjavghisqpvdb9i0s0cs08q5fuvo63u.apps.googleusercontent.com
	-->

	<!-- Googles own class for their sign in button -->
	<!-- <div class="g-signin2" data-onsuccess="onSignIn"></div> -->
	<!-- <a href="#" onclick="signOut();">Sign out</a> -->

	<button onclick="trySampleRequest();">Get Access</button>
	<button onclick="toHomePage();">Continue to Home Page</button>
	<!-- <button onclick="revokeAccess();">Revoke Access</button> -->
</body>
</html>