import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SubscriptionListResponse;

/**
 * Servlet implementation class TokenValidation
 */
@WebServlet("/TokenValidation")
public class TokenValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TokenValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String accessToken = request.getParameter("accessToken");
//		System.out.println("In TokenValidation access token " + accessToken);
//
//		HttpSession hs = request.getSession(false);
//		String userId = (String) hs.getAttribute("userId");
//		System.out.println("in token validation user id " + hs.getAttribute("userId"));
//		
//		DBController.addAccessToken(userId, accessToken);
//		System.out.println("in token validation access token " + DBController.getAccessToken(userId));
		
		String userId = request.getParameter("userId");
		String accessToken = request.getParameter("accessToken");
		System.out.println("In TokenValidation access token " + accessToken);

		if (DBController.isReturningUser(userId)) {
			System.out.println("in token validation This is a returning user");
		}
		else {
			String email = request.getParameter("email");
			String pictureUrl = request.getParameter("picture");
			String familyName = request.getParameter("family_name");
			String givenName = request.getParameter("given_name");
			System.out.println("in token validation This is a new user");
			
			DBController.signUpNewUser(userId, givenName, familyName, pictureUrl, email);
		}
		
		HttpSession hs = request.getSession(true);
		hs.setAttribute("userId", userId);
		
		System.out.println("in token validation user id " + hs.getAttribute("userId"));
		
		DBController.addAccessToken(userId, accessToken);
		System.out.println("in token validation access token " + DBController.getAccessToken(userId));

		// YoutubeAPI myYouTubeAPI = new YoutubeAPI(accessToken);

	}

}
