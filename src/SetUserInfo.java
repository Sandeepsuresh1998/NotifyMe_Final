import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetUserInfo")
public class SetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");		
		
		String givenName = request.getParameter("givenName");		
		String familyName = request.getParameter("familyName");		
		String pictureUrl = request.getParameter("pictureUrl");		
		
		DBController.updateUserInfo(userId, givenName, familyName, pictureUrl);
	}
}
