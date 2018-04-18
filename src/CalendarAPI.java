import java.io.IOException;
import java.util.List;

import javax.websocket.Session;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.gson.Gson;

/**
 * @author yutianli
 *
 */
public class CalendarAPI extends Thread {
	private GoogleCredential credential;
	Calendar service;
	Session session;
	/**
	 * 
	 */
	public CalendarAPI(Session s, String accessToken) {
		this.session = s;
		credential = new GoogleCredential().setAccessToken(accessToken);
		service = new Calendar.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
			    .setApplicationName("NotifyMe").build();
		start();
	}
	public void run() {
		System.out.println("Calendar API running");
		while(session.isOpen()) {
			// Iterate over the events in the specified calendar
			String pageToken = null;
			List<CalendarEvent> CalendarEvents = null;
			do {
			  com.google.api.services.calendar.model.Events events = null;
			try {
				events = service.events().list("primary").setPageToken(pageToken).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  List<Event> items = events.getItems();
			  
			  for (Event event : items) {
				  CalendarEvent ce = new CalendarEvent(event.getStart().getDateTime(), event.getEnd().getDateTime(), event.getSummary());
				  System.out.println(event.getStart().getDateTime());
			     System.out.println(event.getSummary());
			     
				  System.out.println(event.getEnd().getDateTime());
				  CalendarEvents.add(ce);
			  }
			  pageToken = events.getNextPageToken();
			} while (pageToken != null);
			
			Gson json = new Gson();
			String gson = json.toJson(CalendarEvents);
			String message = "Calendar|"+gson;
			try {
				MainServer.sendUpdate(session, message);
				java.lang.Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
