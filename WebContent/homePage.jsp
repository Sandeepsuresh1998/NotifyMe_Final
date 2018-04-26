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
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<header>
		<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
			<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="homePage.jsp">
				<div class="d-flex justify-content-center align-items-center">
					<i class="material-icons" style="color: white;">notifications</i> <strong>NotifyMe</strong>
				</div>
			</a> <input class="form-control form-control-dark w-100"
				id="google_search" type="text" placeholder="Search"
				aria-label="Search">
			<ul class="navbar-nav px-3">
				<li class="nav-item text-nowrap"><a
					href="
				<%if (session.getAttribute("userId") != null) {%>
					Login.jsp
				<%} else {%>
					Login.jsp
				<%}%>
								">
						<i class="material-icons" style="color: white;">person</i>
				</a></li>
			</ul>
		</nav>
	</header>
	<main role="main">
	<div class="container">
		<%
			if (session.getAttribute("userId") != null) {
		%>
		<div class="row">
			<div class="col-sm-6">
				<div class="gridle-item">
					<div class="card-body">
						<h5 class="card-title">Twitter</h5>
						<ul class="list-group">
							<%
								for (int i = 0; i < 10; i++) {
							%>
							<li
								class="row tweetRow list-group-item <%if (i == 0) {%>list-group-first<%}%><%if (i == 9) {%>list-group-last<%}%>">
								<div class="col-sm-2">
									#<%=i + 1%>
								</div>
								<div class="col-sm-10" id="tweet_<%=i%>"></div>
							</li>
							<%
								}
							%>
						</ul>
						<div class=" row black_border_top"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="gridle-item">
					<div class="card-body">
						<h5 class="card-title">Gmail</h5>
						<%
							for (int i = 0; i < 8; i++) {
						%>
						<div class="row mail_row gmail_body	">
							<div class="col-sm-5 mail_from_subject">
								<div class="row ">
									<div class="col-sm-2">
										<strong>From :</strong>
									</div>
									<div class="col-sm-10" id="email_from_<%=i%>"></div>
								</div>
								<div class="row">
									<div class="col-sm-2">
										<strong>Subject :</strong>
									</div>
									<div class="col-sm-10" id="subject_<%=i%>"></div>
								</div>
							</div>
							<div
								class="col-sm-2 d-flex justify-content-center align-items-center">
								<div class="vl"></div>
							</div>
							<div class="col-sm-5">
								<div id="email_body_<%=i%>"></div>
							</div>

						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="gridle-item">
					<div class="card-body">
						<h5 class="card-title">Stocks</h5>
						<ul class="list-group">
							<li class="list-group-item list-group-first" id="AAPL"></li>
							<li class="list-group-item" id="NFLX"></li>
							<li class="list-group-item" id="MSFT"></li>
							<li class="list-group-item" id="TSLA"></li>
							<li class="list-group-item" id="FB"></li>
							<li class="list-group-item" id="GOOGL"></li>
							<li class="list-group-item list-group-last" id="AMZN"></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="gridle-item">
					<div class="card-body">
						<h5 class="card-title">Weather</h5>
						<div class="weatherBody text-center">
							<div class="row">
								<div class="col-sm-12" id="current_weather_today"></div>
							</div>
							<div class="row">
								<div class="col-sm-12" id="weather_picture_today"></div>
							</div>
							<div class="row">
								<div class="col-sm-12" id="current_temperature_today"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="gridle-item">
					<div class="card-body">
						<h5 class="card-title">Calendar</h5>
						<div id="calendarBody"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="gridle-item">
					<div class="card-body">
						<h5 class="card-title">YouTube</h5>
						<label class="sr-only" for="inlineFormInputGroupUsername2">Search</label>
						<div class="input-group mb-2 mr-sm-2">
							<button
								class="input-group-prepend btn btn-secondary no_border_radius_right youtube-search"
								id="youtube-search">
								<i class="material-icons">search</i>
							</button>
							<input type="text" class="form-control" placeholder="Search"
								id="youtube-search-input">
						</div>
						<div class="row">
						<div class="col-sm-3 videowrapper" id="video_0"></div>
						<div class="col-sm-3 videowrapper" id="video_1"></div>
						<div class="col-sm-3 videowrapper" id="video_2"></div>
						<div class="col-sm-3 videowrapper" id="video_3"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
				} else {
			%>
		<div class="row">
			<div class="col-sm-4">
				<div class="gridle-item">
					<div class="card-body">
						<h5 class="card-title">Twitter</h5>
						<ul class="list-group">
							<%
									for (int i = 0; i < 10; i++) {
								%>
							<li
								class="row tweetRow list-group-item <%if (i == 0) {%>list-group-first<%}%><%if (i == 9) {%>list-group-last<%}%>">
								<div class="col-sm-2">
									#<%=i + 1%>
								</div>
								<div class="col-sm-10" id="tweet_<%=i%>"></div>
							</li>
							<%
									}
								%>
						</ul>
						<div class=" row black_border_top"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="gridle-item">
					<div class="card-body">
						<h5 class="card-title">Stocks</h5>
						<ul class="list-group">
							<li class="list-group-item list-group-first" id="AAPL"></li>
							<li class="list-group-item" id="NFLX"></li>
							<li class="list-group-item" id="MSFT"></li>
							<li class="list-group-item" id="TSLA"></li>
							<li class="list-group-item" id="FB"></li>
							<li class="list-group-item" id="GOOGL"></li>
							<li class="list-group-item list-group-last" id="AMZN"></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="gridle-item">
					<div class="card-body">
						<h5 class="card-title">Weather</h5>
						<div class="weatherBody text-center">
							<div class="row">
								<div class="col-sm-12" id="current_weather_today"></div>
							</div>
							<div class="row">
								<div class="col-sm-12" id="weather_picture_today"></div>
							</div>
							<div class="row">
								<div class="col-sm-12" id="current_temperature_today"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
				}
			%>
	</div>
	</main>
	<script
		src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
	<script>
		function loadWidgets() {
			/* var userId = localStorage.getItem('userId'); */
			/* console.log("start connection for userid " + userId); */
			console.log("starting connection");
			socket = new WebSocket("ws://localhost:8080/NotifyMe_Final/ws");
			// on open
			socket.onopen = function(event) {
				/* socket.send(userId); */
				/* console.log("Connection established for userid " + userId); */
				console.log("Connection established");
			}

			// on message
			socket.onmessage = function(event) {

				var data = event.data.split("|");

				var header = data[0];

				if (header.includes("Twitter")) {
					console.log('start twitter js function');

					var obj = JSON.parse(data[1]);
					/* console.log(obj); */

					var idNum = 0;
					for ( var key in obj) {
						if (obj.hasOwnProperty(key)) {
							if (idNum >= 10) {
								break;
							}
							var twitterName = key;//Name of twitter object
							var twitterURL = obj[key];
							var tweet_div = document.getElementById("tweet_"
									+ idNum);
							//Clearing the node for when it refreshes
							while (tweet_div.hasChildNodes()) {
								tweet_div.removeChild(tweet_div.firstChild);
							}
							var anchorTag = document.createElement("a");
							anchorTag.innerHTML = twitterName;
							anchorTag.href = twitterURL;
							tweet_div.appendChild(anchorTag);
							idNum++;
						}
					}

					console.log('end twitter js function');
				} else if (header.includes("Weather")) {
					console.log('start weather js function');

					var obj = JSON.parse(data[1]);
					/* console.log(obj); */

					var iconNum = obj.weather[0].icon;
					var description = obj.weather[0].description;
					var temp = Math.trunc(kelvinToFahrenheit(obj.main.temp));

					//Get pic 
					var weather_pic_div = document
							.getElementById("weather_picture_today");
					var weatherIcon = document.createElement("IMG");
					weatherIcon.alt = "Weather.img";
					console.log("http://openweathermap.org/img/w/" + iconNum
							+ "png");
					weatherIcon.src = "http://openweathermap.org/img/w/"
							+ iconNum + ".png";
					weatherIcon.className = "img-responsive";
					weather_pic_div.appendChild(weatherIcon);

					//Get Temperature
					var current_weather_div = document
							.getElementById("current_temperature_today");
					var tempElement = document.createTextNode(temp + "\xB0"
							+ "F");
					var tempHeader = document.createElement("h1");
					tempHeader.className = "weather_text";
					tempHeader.appendChild(tempElement);
					current_weather_div.appendChild(tempHeader);

					//Get Description
					var weather_description_div = document
							.getElementById("current_weather_today")
					var descriptionElement = document
							.createTextNode(description)
					var descriptionHeader = document.createElement("h1");
					descriptionHeader.className = "weather_text";
					descriptionHeader.appendChild(descriptionElement);
					weather_description_div.appendChild(descriptionHeader);

					console.log('end weather js function');
				} else if (header.includes("Gmail")) {
					console.log('start gmail js function');

					var obj = JSON.parse(data[1]);

					/* console.log(obj); */

					for (i = 0; i < 8; i++) {
						$('#email_from_' + i).html(obj[i].from);
						$('#subject_' + i).html(obj[i].subject);
						$('#email_body_' + i).html(obj[i].snippet);
					}

					console.log('end gmail js function');
				} else if (header.includes("Stocks")) {
					var obj = JSON.parse(data[1]);

					var stocks = obj['Stock Quotes'];

					for (var i = 0; i < stocks.length; i++) {
						console.log(stocks[i]['1. symbol']);
						stock_div = document
								.getElementById(stocks[i]['1. symbol']);
						var price_text = document
								.createTextNode(stocks[i]['1. symbol'] + ": "
										+ stocks[i]['2. price']);
						var price = document.createElement("li");
						price.className = "list-item"
						price.appendChild(price_text);
						stock_div.appendChild(price);
					}

				} else if (header.includes("YouTube")) {
					console.log('start youtube js function');

					var obj = JSON.parse(data[1]);
					var numVideo = 0;
					console.log(obj);
					for (i = 0; i < 4; i++) {
						if (obj[i]) {
							console.log(obj[i]);
							document.getElementById("video_" + i).innerHTML = '<iframe width="239" height="250" src='+obj[i]+' allowfullscreen="allowfullscreen"></iframe>';
							numVideo++;
						} else {
							//document.getElementById("video_" + i).innerHTML = ''
							break;
						}
					}
					console.log('end youtube js function');
				} else if (header.includes("Calendar")) {
					console.log('start calendar js function');
					document.getElementById("calendarBody").innerHTML = "<ol>";
					var obj = JSON.parse(data[1]);
					for (i = 0; i < obj.length; i++) {
						/* if(obj[i]==null){
							break;
						} */
						document.getElementById("calendarBody").innerHTML += "<li>"
								+ obj[i].event + "</li>"
						console.log(obj[i].event);
					}
					document.getElementById("calendarBody").innerHTML += "</ol>";
					console.log('end calendar js function');
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
		$('button.youtube-search').click(
				function() {
					window.open('https://www.youtube.com/results?search_query='
							+ $('#youtube-search-input').val(), '_blank')
				});
		$("#google_search").on(
				'keyup',
				function(e) {
					if (e.keyCode == 13) {
						window.open('https://google.com/search?q='
								+ $('#google_search').val(), '_blank')
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
<%
	
%>


</html>