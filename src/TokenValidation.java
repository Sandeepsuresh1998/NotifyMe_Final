import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Collection<String> SCOPES = Arrays.asList("YouTubeScopes.https://www.googleapis.com/auth/youtube.force-ssl YouTubeScopes.https://www.googleapis.com/auth/youtubepartner");
		String accessToken = request.getParameter("accessToken");
		System.out.println("In TokenValidation");
		System.out.println(accessToken);
		
		YouTubeAPI  myYouTubeAPI = new YouTubeAPI(accessToken);
		
		
		
		
		
		
		//Get profile info from ID token
//		GoogleIdToken idToken = tokenResponse.parseIdToken();
//		GoogleIdToken.Payload payload = idToken.getPayload();
//		String userId = payload.getSubject();  // Use this value as a key to identify a user.
//		String email = payload.getEmail();
//		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//		String name = (String) payload.get("name");
//		String pictureUrl = (String) payload.get("picture");
//		String locale = (String) payload.get("locale");
//		String familyName = (String) payload.get("family_name");
//		String givenName = (String) payload.get("given_name");
	}
	

}
