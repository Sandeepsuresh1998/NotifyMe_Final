//function onSignIn(googleUser) {
//	var profile = googleUser.getBasicProfile();
////	localStorage.setItem('userId', profile.getId());
//	var token = googleUser.getAuthResponse().id_token;
//	var xhr = new XMLHttpRequest();
//	xhr.open('POST', 'http://localhost:8080/NotifyMe_Final/LoginServlet');
//	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//	// xhr.onload = function() {
//	// console.log('Signed in as: ' + xhr.responseText);
//	// };
//	console.log('token = ' + token);
//	xhr.send('token=' + token);
//}

var YOUR_CLIENT_ID = '496920584851-fmjavghisqpvdb9i0s0cs08q5fuvo63u.apps.googleusercontent.com';
var YOUR_REDIRECT_URI = 'http://localhost:8080/NotifyMe_Final/Login.jsp';
var queryString = location.hash.substring(1);
var accessToken = null;
var userId = null;
var email = null;
var given_name = null;
var family_name = null;
var picture = null;

// Parse query string to see if page request is coming from OAuth 2.0 server.
var params = {};
var regex = /([^&=]+)=([^&]*)/g, m;
while (m = regex.exec(queryString)) {
	params[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
	// Try to exchange the param values for an access token.
	exchangeOAuth2Token(params);
}

// If there's an access token, try an API request.
// Otherwise, start OAuth 2.0 flow.
function trySampleRequest() {
	var params = JSON.parse(localStorage.getItem('oauth2-test-params'));
	if (params && params['access_token']) {
		var xhr = new XMLHttpRequest();
		xhr.open('GET',
				'https://www.googleapis.com/drive/v3/about?fields=user&'
						+ 'access_token=' + params['access_token']);
		xhr.onreadystatechange = function(e) {
			console.log(xhr.response);
		};
		xhr.send(null);
	} else {
		oauth2SignIn();
	}
}

// If there's an access token, try an API request.
// Otherwise, start OAuth 2.0 flow.
function trySampleRequest() {
	var params = JSON.parse(localStorage.getItem('oauth2-test-params'));
	console.log(params);
	if (params && params['access_token']) {
		accessToken = params['access_token'];
		console.log('access token ' + accessToken);
		var xhttp = new XMLHttpRequest();
//		xhttp.open("POST", "TokenValidation?accessToken=" + accessToken, false);
		xhttp.open("POST", "TokenValidation?userId=" + userId + 
				"&accessToken=" + accessToken + 
				"&userId=" + userId + 
				"&email=" + email + 
				"&given_name=" + given_name + 
				"&family_name=" + family_name + 
				"&picture=" + picture
				, false);
		xhttp.send();
		localStorage.removeItem('oauth2-test-params');
//		localStorage.removeItem('userId');
	} else {
		console.log('no access token');
		oauth2SignIn();
	}
}

/*
 * Create form to request access token from Google's OAuth 2.0 server.
 */
function oauth2SignIn() {
	// Google's OAuth 2.0 endpoint for requesting an access token
	var oauth2Endpoint = 'https://accounts.google.com/o/oauth2/v2/auth';

	// Create element to open OAuth 2.0 endpoint in new window.
	var form = document.createElement('form');
	form.setAttribute('method', 'GET'); // Send as a GET request.
	form.setAttribute('action', oauth2Endpoint);

	// Parameters to pass to OAuth 2.0 endpoint.
	var params = {
		'client_id' : YOUR_CLIENT_ID,
		'redirect_uri' : YOUR_REDIRECT_URI,
		'scope' : 'https://www.googleapis.com/auth/youtube.force-ssl ' + 
			'https://www.googleapis.com/auth/userinfo.profile ' + 
			'https://www.googleapis.com/auth/userinfo.email ' + 
			'https://mail.google.com/ ' + 
//			'https://www.googleapis.com/auth/gmail.metadata ' + 
			'https://www.googleapis.com/auth/gmail.modify ' + 
			'https://www.googleapis.com/auth/gmail.readonly '+
			'https://www.googleapis.com/auth/calendar',
		'state' : 'try_sample_request',
		'include_granted_scopes' : 'true',
		'response_type' : 'token'
	};

	// Add form parameters as hidden input values.
	for ( var p in params) {
		var input = document.createElement('input');
		input.setAttribute('type', 'hidden');
		input.setAttribute('name', p);
		input.setAttribute('value', params[p]);
		form.appendChild(input);
	}

	// Add form to page and submit it to open the OAuth 2.0 endpoint.
	document.body.appendChild(form);
	form.submit();
}

/* Verify the access token received on the query string. */
function exchangeOAuth2Token(params) {
	/* log.console("in exchangeOAuth2Token"); */
	var oauth2Endpoint = 'https://www.googleapis.com/oauth2/v3/tokeninfo';
	if (params['access_token']) {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', oauth2Endpoint + '?access_token=' + params['access_token']);
		xhr.onreadystatechange = function(e) {
			console.log(xhr.response);
			var response = JSON.parse(xhr.response);
			// When request is finished, verify that the 'aud' property in the
			// response matches YOUR_CLIENT_ID.
			if (xhr.readyState == 4 && xhr.status == 200 && response['aud'] && response['aud'] == YOUR_CLIENT_ID) {
				// Store granted scopes in local storage to facilitate
				// incremental authorization.
				params['scope'] = response['scope'];
				localStorage.setItem('oauth2-test-params', JSON.stringify(params));
				
				console.log('after setting oauth local storage')
				userId = response['sub'];
				email = response['email'];
				given_name = response['given_name'];
				family_name = response['family_name'];
				picture = response['picture'];
				
				console.log('in exchange oauth token userId ' + userId + ' picture ' + picture);
//				localStorage.setItem('userId', response['sub']);
				if (params['state'] == 'try_sample_request') {
					trySampleRequest();
				}
			} else if (xhr.readyState == 4) {
				console.log('There was an error processing the token, another response was returned, or the token was invalid.')
			}
		};
		xhr.send(null);
	}
}

function revokeAccess() {
	// Google's OAuth 2.0 endpoint for revoking access tokens.
	var requeststr = 'https://accounts.google.com/o/oauth2/revoke?token=' + accessToken;
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", requeststr, false);
	xhttp.send();
	localStorage.removeItem('oauth2-test-params');
}

function toHomePage() {
	window.location.href = "homePage.jsp";
}

function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function() {
		console.log('User signed out.');
		localStorage.removeItem('oauth2-test-params');
	});
}
