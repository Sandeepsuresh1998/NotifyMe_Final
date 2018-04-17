import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/GetWidgets")
public class GetWidgets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		
		ArrayList<Boolean> widgets = DBController.getWidgets(userId);
		Gson json = new Gson();
		String gson = json.toJson(widgets); 
		
		PrintWriter out = response.getWriter();
		out.print(gson);
	}
}
