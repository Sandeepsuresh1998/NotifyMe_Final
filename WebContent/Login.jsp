<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	if(request.getParameter("access_token") != null){
		System.out.println("HERERERERERERERWERQWERQWERQWERQ");
		String redirectURL = "homePage.jsp";
	    response.sendRedirect(redirectURL);
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NotifyMe - Login</title>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="OAuth.js"></script>
<link href="main.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<meta name="google-signin-client_id"
	content="496920584851-fmjavghisqpvdb9i0s0cs08q5fuvo63u.apps.googleusercontent.com">
</head>

<!--
	Client Secret: OUyYhuHPbG3T5azvLD0hl_j2
	Client ID: 496920584851-fmjavghisqpvdb9i0s0cs08q5fuvo63u.apps.googleusercontent.com
	-->

<!-- Googles own class for their sign in button -->
<!-- <div class="g-signin2" data-onsuccess="onSignIn"></div> -->
<!-- <a href="#" onclick="signOut();">Sign out</a> -->
<body>
	<header> <nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
	<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="homePage.jsp">
		<div class="d-flex justify-content-center align-items-center">
			<i class="material-icons" style="color: white;">notifications</i> <strong>NotifyMe</strong>
		</div>
	</a> <input class="form-control form-control-dark w-100" type="text"
		placeholder="Search" aria-label="Search">
	<ul class="navbar-nav px-3">
		<li class="nav-item text-nowrap"><a href="profilePage.jsp"> <i
				class="material-icons" style="color: white;">person</i>
		</a></li>
	</ul>
	</nav> </header>
	<div class="text-center fill-available d-flex justify-content-center align-items-center">
		<div class="form-signin">
			<button class="btn btn-lg  btn-outline-secondary btn-block" id="login"
				onclick="trySampleRequest();" type="submit">Login</button>
			<button class="btn btn-lg btn-outline-secondary btn-block" id="home"
				onclick="toHomePage();" type="submit">Continue to Home Page</button>
		</div>
	</div>
</body>

<!-- <button onclick="revokeAccess();">Revoke Access</button> -->

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</html>