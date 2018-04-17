var givenName;
var familyName;
var pictureUrl;
var email; // display only, should not be allowed to change

var useTwitter;
var useGmail;
var useWeather;
var useCalendar;
var useYouTube;
var useStock;

function getPreferences() {
	var userId = localStorage.getItem('userId');
	
	// get user info
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "GetUserInfo?userId=" + userId, false);
	xhr.send();
	if (xhr.responseText.trim().length > 0) {
		console.log(xhr.responseText);
		// string arraylist
		givenName = response[0];
		familyName = response[1];
		pictureUrl = response[2];
		email = response[3]; 
	}
	
	// get widgets
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "GetWidgets?userId=" + userId, false);
	xhr.send();
	if (xhr.responseText.trim().length > 0) {
		console.log(xhr.responseText);
		// string arraylist value = "true" or "false"
		useTwitter = response[0];
		useGmail = response[1];
		useWeather = response[2];
		useCalendar = response[3];
		useYouTube = response[4];
		useStock = response[5];
	}
	
	// update html
} 

function setPreferences() {
	var userId = localStorage.getItem('userId');
	
	// update user info
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "SetUserInfo?userId=" + userId 
			+ "&givenName=" + givenName
			+ "&familyName=" + familyName
			+ "&pictureUrl=" + pictureUrl
			, false);
	xhr.send();
	
	// update widgets
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "SetWidgets?userId=" + userId 
			+ "&useTwitter=" + useTwitter
			+ "&useGmail=" + useGmail
			+ "&useWeather=" + useWeather
			+ "&useCalendar=" + useCalendar
			+ "&useYouTube=" + useYouTube
			+ "&useStock=" + useStock
			, false);
	xhr.send();
	window.location.href = "homePage.jsp";
} 