
import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws")
public class MainServer {

	// Static so that there is only one instance of it.
	// private static Vector<Session> sessionVector = new Vector<Session>();

	@OnOpen
	public void open(Session session) {
		System.out.println("Client connected");
	}

	@OnMessage
	public void onMessage(String userId, Session session) {
		System.out.println("in main server start loading widgets for userId " + userId);
		// index 0 = twitter
		// index 1 = gmail
		// index 2 = weather
		// index 3 = calendar
		// index 4 = youtube
		// index 5 = stock
		ArrayList<Boolean> widgets = DBController.getPreferences(userId);
		if (widgets.get(0)) {
			TwitterApi tApi = new TwitterApi(session);
		}
		if (!widgets.get(1)) {
			GmailAPI gApi = new GmailAPI(session, DBController.getAccessToken(userId));
		}
		if (widgets.get(2)) {
			WeatherAPI wApi = new WeatherAPI(session);
		}
		if (widgets.get(3)) {

		}
		if (widgets.get(4)) {
			
		}
		if (widgets.get(5)) {

		}
		
		

		// sessionVector.add(session);

		// try {
		// for(Session s : sessionVector) {
		// s.getBasicRemote().sendText(message);
		//
		// }
		// } catch (IOException ioe) {
		// System.out.println("ioe: " + ioe.getMessage());
		// }
	}
	
	synchronized public static void sendUpdate(Session session, String content) throws IOException {
		session.getBasicRemote().sendText(content);
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Client disconnected");
		// sessionVector.remove(session);
	}
}
