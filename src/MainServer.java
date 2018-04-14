
import java.io.IOException;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import twitter4j.Twitter;

@ServerEndpoint(value = "/ws")
public class MainServer extends Thread {
	
	//Static so that there is only one instance of it.
	private static Vector<Session> sessionVector = new Vector<Session>();
	
	@OnOpen
	public void open(Session session) {
		System.out.println("Client connected");
		System.out.println("Twitter!");
		
		TwitterApi tApi = new TwitterApi(session);
		sessionVector.add(session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);
		try {
			for(Session s : sessionVector) {
				s.getBasicRemote().sendText(message);
				
			}
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
	
	@OnClose
	public void close(Session session) {
		System.out.println("Client disconnected");
		sessionVector.remove(session);
	}
	
	
	@Override
	public void run() {
		//Create Threads for each API.  
		
		//
	}
}
