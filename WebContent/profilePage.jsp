<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Album example for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="main.css" rel="stylesheet">
</head>
<body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<header>
		<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
			<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="homePage.jsp">
				<div class="d-flex justify-content-center align-items-center">
					<i class="material-icons" style="color: white;">notifications</i> <strong>NotifyMe</strong>
				</div>
			</a> 
			<input class="form-control form-control-dark w-100" id="google_search" type="text"
				placeholder="Search" aria-label="Search">
			<ul class="navbar-nav px-3">
				<li class="nav-item text-nowrap"><a href="profilePage.jsp">
						<i class="material-icons" style="color: white;">person</i>
				</a></li>
			</ul>
		</nav>
	</header>
	<main role="main">
	<div class="container">
		<div class="d-flex justify-content-center align-items-center">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Widget</th>
						<th scope="col">Use</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">Twitter</th>
						<td><select>
								<option>Yes</option>
								<option>No</option>
						</select></td>
					</tr>
					<tr>
						<th scope="row">YouTube</th>
						<td><select>
								<option>Yes</option>
								<option>No</option>
						</select></td>
					</tr>
					<tr>
						<th scope="row">Gmail</th>
						<td><select>
								<option>Yes</option>
								<option>No</option>
						</select></td>
					</tr>
					<tr>
						<th scope="row">Weather</th>
						<td><select>
								<option>Yes</option>
								<option>No</option>
						</select></td>
					</tr>
					<tr>
						<th scope="row">Calendar</th>
						<td><select>
								<option>Yes</option>
								<option>No</option>
						</select></td>
					</tr>
					<tr>
						<th scope="row">CNN</th>
						<td><select>
								<option>Yes</option>
								<option>No</option>
						</select></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</main>
	<script>
	$("#google_search").on('keyup', function (e) {
	    if (e.keyCode == 13) {
	    		window.open('https://google.com/search?q='+$('#google_search').val(),'_blank')
	    }
	});
	</script>
	<footer class="text-muted"> </footer>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
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
</body>
</html>