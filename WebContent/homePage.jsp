<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<!-- <script src="WSConnections.js"></script> -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>NotifyMe - Home</title>

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
<body onload="loadWidgets()">
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
				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
						<div class="card-body">
							<p class="card-text">YouTube</p>
							<div class="d-flex justify-content-between align-items-center">
								<label class="sr-only" for="inlineFormInputGroupUsername2">Search</label>
								<div class="input-group mb-2 mr-sm-2">
									<div class="input-group-prepend">
										<a><div class="input-group-text">
												<i class="material-icons">search</i>
											</div></a>
									</div>
									<input type="text" class="form-control"
										id="inlineFormInputGroupUsername2" placeholder="Search">
								</div>
								<%
									for (int i = 0; i < 6; i += 2) {
								%>
								<div class="row">
									<div class="col-sm-6" id="video_<%=i%>"></div>
									<div class="col-sm-6" id="video_<%=i + 1%>"></div>
								</div>
								<%
									}
								%>
								<br /> <small class="text-muted">9 mins</small>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
						<div class="card-body">
							<p class="card-text">Twitter</p>
							<%
								for (int i = 0; i < 10; i++) {
							%>
							<div class="row tweetRow">
								<div class="col-sm-2">
									#<%=i + 1%>
								</div>
								<div class="col-sm-10" id="tweet_<%=i%>"></div>
							</div>
							<%
								}
							%>
							<small class="text-muted">9 mins</small>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
						<div class="card-body gmail_body">
							<h5 class="card-title">Gmail</h5>
							<%
								for (int i = 0; i < 8; i++) {
							%>
							<div class="row mail_row">
								<div class="col-sm-5 mail_from_subject">
									<div class="row ">
										<div class="col-sm-6">From :</div>
										<div class="col-sm-6" id="email_from_<%=i%>">{{NAME}}</div>
									</div>
									<div class="row">
										<div class="col-sm-6">Subject :</div>
										<div class="col-sm-6" id="subject_<%=i%>">{{SUBJECT}}</div>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="vl"></div>
								</div>
								<div class="col-sm-5">
									<div id="email_body_<%=i%>">{{MESSAGE BODY}}</div>
								</div>

							</div>
							<%
								}
							%>
							<div class="d-flex justify-content-between align-items-center">
								<small class="text-muted">9 mins</small>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
						<div class="card-body">
							<h5 class="card-title">Calendar</h5>
							<p class="card-text">
							<div id="calendarBody">{{INSERT GOOGLE CALENDAR}}</div>
							</p>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group">
									<button type="button" class="btn btn-sm btn-outline-secondary">View</button>
									<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
								</div>
								<small class="text-muted">9 mins</small>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
						<div class="card-body">
							<h5 class="card-title">CNN</h5>
							<%
								for (int i = 0; i < 5; i++) {
							%>
							<div id="story_<%=i%>">
								<div class="row">
									<div class="col-sm-12" id="headline_<%=i%>">{{HEADLINE}}</div>
									<div class="col-sm-12" id="author_<%=i%>">{{AUTHOR}}</div>
								</div>
								<div class="row">
									<div class="col-sm-12" id="preview_text_<%=i%>">{{STORY
										DECSRIPTION}}</div>
								</div>
							</div>
							<%
								}
							%>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
						<div class="card-body">
							<h5 class="card-title">Weather</h5>
							<p class="card-text">
							<div id="weatherBody"></div>
							</p>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group">
									<button type="button" class="btn btn-sm btn-outline-secondary">View</button>
									<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
								</div>
								<small class="text-muted">9 mins</small>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	</main>

	<script>
		function loadWidgets() {
			var userId = localStorage.getItem('userId');
			console.log("Made it to this call " + userId);
			socket = new WebSocket("ws://localhost:8080/NotifyMe_Final/ws");
			// on open
			socket.onopen = function(event) {
				socket.send(userId);
				console.log("Connection established");
			}
			
			// on message
			socket.onmessage = function(event) {

				var data = event.data.split("|");

				var header = data[0];

				if (header.includes("Twitter")) {
					var obj = JSON.parse(data[1]);
					console.log(obj);

					var idNum = 0;
					for ( var key in obj) {
						if (obj.hasOwnProperty(key)) {
							if (idNum >= 10) {
								break;
							}
							var twitterName = key;//Name of twitter object
							var twitterURL = obj[key];
							var tweet_div = document.getElementById("tweet_" + idNum);
							var anchorTag = document.createElement("a");
							anchorTag.innerHTML = twitterName;
							anchorTag.href = twitterURL;
							tweet_div.appendChild(anchorTag);
							idNum++;
						}
					}
				}
				else if (header.includes("Weather")) {
					var obj = JSON.parse(data[1]);
					console.log(obj);

					var iconNum = obj.weather[0].icon;
					var description = obj.weather[0].description;
					var temp = Math.trunc(kelvinToFahrenheit(obj.main.temp));

					var weatherDiv = document.getElementById("weatherBody");

					//Get pic 
					var weatherIcon = document.createElement("IMG");
					weatherIcon.alt = "Weather.img";
					console.log("http://openweathermap.org/img/w/" + iconNum + "png");
					weatherIcon.src = "http://openweathermap.org/img/w/" + iconNum + ".png";
					weatherDiv.appendChild(weatherIcon);

					//Get Temperature
					var tempElement = document.createTextNode(temp)
					var tempHeader = document.createElement("h1");
					tempHeader.appendChild(tempElement);
					weatherDiv.appendChild(tempHeader);

					//Get Description
					var descriptionElement = document
							.createTextNode(description)
					var descriptionHeader = document.createElement("h1");
					descriptionHeader.appendChild(descriptionElement);
					weatherDiv.appendChild(descriptionHeader);
				}
				else if (header.includes("Gmail")) {
					var obj = JSON.parse(data[1]);
					console.log(obj);
					
					/* for (i = 0; i < 8; i++) {
						$('#email_from_'+i).html();
					} */
				}
				else if (header.includes("Crypto")) {
					var obj = JSON.parse(data[1]);
					
					//Put Crypto in its own div
				}

			}
				
			// on close
			socket.onclose = function(event) {
			}
			
			// auto close when close window 
			window.onbeforeunload = function(event) {
				socket.close();
			}
		}

		function kelvinToFahrenheit(temp) {
			return (temp * (9.0 / 5.0) - 459.67);
		}
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
<%
	
%>


</html>