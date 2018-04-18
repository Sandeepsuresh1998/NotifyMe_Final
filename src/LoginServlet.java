import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final HttpTransport transport = new NetHttpTransport();
	private static final JacksonFactory jsonFactory = new JacksonFactory();
	private static final String clientID = "943857468024-saaqgfpqu23qqlnc7ce0i5nok6na6tif.apps.googleusercontent.com";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //to verify the token/user and get the userID
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoogleIdTokenVerifier check = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singletonList(clientID))
				.build();
		
		String token = request.getParameter("token");
		GoogleIdToken id;
		try {
			id = check.verify(token);
			Payload payload = id.getPayload();
			String userId = payload.getSubject();
			System.out.println("User ID: " + userId);
			
			if (DBController.isReturningUser(userId)) {
				System.out.println("in login servlet This is a returning user");
			}
			else {
				String email = payload.getEmail();
				String pictureUrl = (String) payload.get("picture");
				String familyName = (String) payload.get("family_name");
				String givenName = (String) payload.get("given_name");
				System.out.println("in login servlet This is a new user");
				
				DBController.signUpNewUser(userId, givenName, familyName, pictureUrl, email);
			}
			HttpSession hs = request.getSession(true);
			hs.setAttribute("userId", userId);
			System.out.println("in login servlet user id " + hs.getAttribute("userId"));
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}