/**
 * 
 */
var useTwitter;
var useGmail;
var useWeather;
var useCalendar;
var useYouTube;
var useStock;

function getPreferences() {
	var userId = localStorage.getItem('userId');
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "GetPreferences?userId=" + userId, false);
	xhr.send();
	if (xhr.responseText.trim().length > 0) {
		console.log(xhr.responseText);
		var response = responseText.split('|');
		useTwitter = response[0];
		useGmail = response[1];
		useWeather = response[2];
		useCalendar = response[3];
		useYouTube = response[4];
		useStock = response[5];
		// update html
	}
} 

function setPreferences() {
	var userId = localStorage.getItem('userId');
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "SetPreferences?userId=" + userId 
			+ "&useTwitter=" + useTwitter
			+ "&useGmail=" + useGmail
			+ "&useWeather=" + useWeather
			+ "&useCalendar=" + useCalendar
			+ "&useYouTube=" + useYouTube
			+ "&useStock=" + useStock, false);
	xhr.send();
	window.location.href = "homePage.jsp";
} 