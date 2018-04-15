<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<script>
  var YOUR_CLIENT_ID = '943857468024-saaqgfpqu23qqlnc7ce0i5nok6na6tif.apps.googleusercontent.com';
  var YOUR_REDIRECT_URI = 'http://localhost:8080/Oauth2/Oauth2.jsp';
  var queryString = location.hash.substring(1);
  var accessToken;
  console.log(queryString);

  // Parse query string to see if page request is coming from OAuth 2.0 server.
  var params = {};
  var regex = /([^&=]+)=([^&]*)/g, m;
  var flag=false;
  while (m = regex.exec(queryString)) {
	  /* console.log(decodeURIComponent(m[1])+" "+decodeURIComponent(m[2])); */
    params[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
    flag=true;
  }
  if(flag==true){
	// Try to exchange the param values for an access token.
	    exchangeOAuth2Token(params);
  }

  // If there's an access token, try an API request.
  // Otherwise, start OAuth 2.0 flow.
  function trySampleRequest() {
    var params = JSON.parse(localStorage.getItem('oauth2-test-params'));
    console.log(params);
    if (params && params['access_token']) {
	    	accessToken = params['access_token'];
 	    	var xhttp = new XMLHttpRequest();
	    	xhttp.open("GET", "TokenValidation?accessToken="+accessToken, false);
	    	xhttp.send();
	
      /* var xhr = new XMLHttpRequest();
      xhr.open('GET',
          'https://www.googleapis.com/youtube/v3/subscriptions?part=snippet&mine=true&' +
          'access_token=' + params['access_token']);
      xhr.onreadystatechange = function (e) {
        console.log(xhr.response);
      };
      xhr.send(null); */
      localStorage.removeItem('oauth2-test-params');
    } else {
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
    var params = {'client_id': YOUR_CLIENT_ID,
                  'redirect_uri': YOUR_REDIRECT_URI,
                  'scope': 'https://www.googleapis.com/auth/youtube.force-ssl https://www.googleapis.com/auth/gmail.compose',
                  'state': 'try_sample_request',
                  'include_granted_scopes': 'true',
                  'response_type': 'token'};

    // Add form parameters as hidden input values.
    for (var p in params) {
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
      xhr.onreadystatechange = function (e) {
    	  console.log(xhr.response);
        var response = JSON.parse(xhr.response);
        // When request is finished, verify that the 'aud' property in the
        // response matches YOUR_CLIENT_ID.
        if (xhr.readyState == 4 &&
            xhr.status == 200 &&
            response['aud'] &&
            response['aud'] == YOUR_CLIENT_ID) {
          // Store granted scopes in local storage to facilitate
          // incremental authorization.
          params['scope'] = response['scope'];
          localStorage.setItem('oauth2-test-params', JSON.stringify(params) );
          if (params['state'] == 'try_sample_request') {
            trySampleRequest();
          }
        } else if (xhr.readyState == 4) {
          console.log('There was an error processing the token, another ' +
                      'response was returned, or the token was invalid.')
        }
      };
      xhr.send(null);
    }
  }
	function revokeAccess() {
	  // Google's OAuth 2.0 endpoint for revoking access tokens.
		var requeststr = 'https://accounts.google.com/o/oauth2/revoke?token='+accessToken;
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET", requeststr, false);
		xhttp.send();
		
	  // Create <form> element to use to POST data to the OAuth 2.0 endpoint.
	  /* var form = document.createElement('form');
	  form.setAttribute('method', 'post');
	  form.setAttribute('action', revokeTokenEndpoint);
	
	  // Add access token to the form so it is set as value of 'token' parameter.
	  // This corresponds to the sample curl request, where the URL is:
	  //      https://accounts.google.com/o/oauth2/revoke?token={token}
	  var tokenField = document.createElement('input');
	  tokenField.setAttribute('type', 'hidden');
	  tokenField.setAttribute('name', 'token');
	  tokenField.setAttribute('value', accessToken);
	  form.appendChild(tokenField);
	
	  // Add form to page and submit it to actually revoke the token.
	  document.body.appendChild(form);
	  form.submit(); */
	}
</script>

<button onclick="trySampleRequest();">Get Access</button>
<button onclick="revokeAccess();">Revoke Access</button>
	</body>
</html>