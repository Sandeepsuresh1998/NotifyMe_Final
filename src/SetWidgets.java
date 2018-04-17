import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetWidgets")
public class SetWidgets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		
		Boolean useTwitter = Boolean.parseBoolean(request.getParameter("useTwitter"));
		Boolean useGmail = Boolean.parseBoolean(request.getParameter("useGmail"));
		Boolean useWeather = Boolean.parseBoolean(request.getParameter("useWeather"));
		Boolean useCalendar = Boolean.parseBoolean(request.getParameter("useCalendar"));
		Boolean useYouTube = Boolean.parseBoolean(request.getParameter("useYouTube"));
		Boolean useStock = Boolean.parseBoolean(request.getParameter("useStock"));
		
		DBController.updateWidgets(userId, useTwitter, useGmail, useWeather, useCalendar, useYouTube, useStock);
	}
}
