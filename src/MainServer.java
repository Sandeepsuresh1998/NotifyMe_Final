
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws", configurator = GetHttpSessionConfigurator.class)
public class MainServer {

	// Static so that there is only one instance of it.
	// private static Vector<Session> sessionVector = new Vector<Session>();
	
	@OnOpen
    public void open(Session session, EndpointConfig config) {
		System.out.println("Client connected");
		HttpSession hs = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		String userId = (String) hs.getAttribute("userId");
		System.out.println("in main server start loading widgets for userId " + userId);
		// index 0 = twitter
		// index 1 = gmail
		// index 2 = weather
		// index 3 = calendar
		// index 4 = youtube
		// index 5 = stock
		if(userId == null) {
			TwitterApi tApi = new TwitterApi(session);
			WeatherAPI wApi = new WeatherAPI(session);
			StockAPI sAPI = new StockAPI(session);
		} else {
			ArrayList<Boolean> widgets = DBController.getWidgets(userId);
			if (widgets.get(0)) {
				TwitterApi twitterApi = new TwitterApi(session);
			}
			if (widgets.get(1)) {
				GmailAPI gmailAPI = new GmailAPI(session, DBController.getAccessToken(userId));
			}
			if (widgets.get(2)) {
				WeatherAPI weatherAPI = new WeatherAPI(session);
			}
			if (widgets.get(3)) {
				CalendarAPI calendarAPI = new CalendarAPI(session, DBController.getAccessToken(userId));
			}
			if (widgets.get(4)) {
				YouTubeAPI youTubeAPI = new YouTubeAPI(session, DBController.getAccessToken(userId));
			}
			if (widgets.get(5)) {
				StockAPI stockAPI = new StockAPI(session);
			}
		}
		
		
    }
	
//	@OnOpen
//	public void open(Session session) {
//		System.out.println("Client connected");
//	}

	@OnMessage
	public void onMessage(String userId, Session session) {
//		System.out.println("in main server start loading widgets for userId " + userId);
//		// index 0 = twitter
//		// index 1 = gmail
//		// index 2 = weather
//		// index 3 = calendar
//		// index 4 = youtube
//		// index 5 = stock
//		ArrayList<Boolean> widgets = DBController.getWidgets(userId);
//		if (widgets.get(0)) {
//			TwitterApi tApi = new TwitterApi(session);
//		}
//		if (!widgets.get(1)) {
//			GmailAPI gApi = new GmailAPI(session, DBController.getAccessToken(userId));
//		}
//		if (widgets.get(2)) {
//			WeatherAPI wApi = new WeatherAPI(session);
//		}
//		if (widgets.get(3)) {
//
//		}
//		if (widgets.get(4)) {
//			
//		}
//		if (widgets.get(5)) {
//
//		}
//		
		

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
	
	synchronized public static void sendUpdate(Session session, String content) {
		try {
			session.getBasicRemote().sendText(content);
		} catch (IOException e) {
			System.out.println("tab closed when sending updates, stop sending and disconnect");
		}
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Client disconnected");
		// sessionVector.remove(session);
	}
}
