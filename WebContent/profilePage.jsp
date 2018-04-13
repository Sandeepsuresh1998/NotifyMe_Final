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
	<header>
		<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
			<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="homePage.jsp">
				<div class="d-flex justify-content-center align-items-center">
					<i class="material-icons" style="color: white;">notifications</i> <strong>NotifyMe</strong>
				</div>
			</a> <input class="form-control form-control-dark w-100" type="text"
				placeholder="Search" aria-label="Search">
			<ul class="navbar-nav px-3">
				<li class="nav-item text-nowrap"><a href="profilePage.jsp">
						<i class="material-icons" style="color: white;">person</i>
				</a></li>
			</ul>
		</nav>
	</header>
	<main role="main">
	<div class="album py-5 bg-light">
		<div class="container">
			<div class="row">
				<div class="container-fluid">
					<div class="row">
						<nav class="col-md-2 d-none d-md-block bg-light sidebar">
							<div class="sidebar-sticky">
								<ul class="nav flex-column">
									<li class="nav-item"><a class="nav-link active" href="#">
											<span data-feather="home"></span> Settings <span
											class="sr-only">(current)</span>
									</a></li>
								</ul>
								<h6
									class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
									<span>Widgets</span> <a
										class="d-flex align-items-center text-muted" href="#"> <span
										data-feather="plus-circle"></span>
									</a>
								</h6>
								<ul class="nav flex-column mb-2">
									<li class="nav-item"><a class="nav-link" href="#"> <span
											data-feather="file-text"></span> Twitter
									</a></li>
									<li class="nav-item"><a class="nav-link" href="#"> <span
											data-feather="file-text"></span> YouTube
									</a></li>
									<li class="nav-item"><a class="nav-link" href="#"> <span
											data-feather="file-text"></span> CNN
									</a></li>
									<li class="nav-item"><a class="nav-link" href="#"> <span
											data-feather="file-text"></span> Calendar
									</a></li>
									<li class="nav-item"><a class="nav-link" href="#"> <span
											data-feather="file-text"></span> Gmail
									</a></li>
									<li class="nav-item"><a class="nav-link" href="#"> <span
											data-feather="file-text"></span> Weather
									</a></li>
								</ul>
							</div>
						</nav>

						<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
		
						</main>
					</div>
				</div>
			</div>
		</div>
	</div>

	</main>

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