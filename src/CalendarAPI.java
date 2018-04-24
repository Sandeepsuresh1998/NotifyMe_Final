import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
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
	@SuppressWarnings("null")
	public void run() {
		System.out.println("Calendar API running");
		while(session.isOpen()) {
			// Iterate over the events in the specified calendar
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String d = dateFormat.format(date);
			System.out.println(d);
			DateTime start = new DateTime(d+"T00:00:00-04:00");
			
			DateTime end = new DateTime(d+"T23:59:59-04:00");
			String pageToken = null;
			List<CalendarEvent> CalendarEvents = new ArrayList<CalendarEvent>();
			do {
			  com.google.api.services.calendar.model.Events events = null;
			try {
				events = service.events().list("primary")
							.setPageToken(pageToken)
							.setTimeMin(start)
							.setTimeMax(end)
							.execute();
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
				  
				  
				  
				  if(ce!=null) {
					  CalendarEvents.add(ce);
				  }
			  }
			  pageToken = events.getNextPageToken();
			} while (pageToken != null);
			
			Gson json = new Gson();
			String gson = json.toJson(CalendarEvents);
			String message = "Calendar|"+gson;
			try {
				MainServer.sendUpdate(session, message);
				java.lang.Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
