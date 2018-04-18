import com.google.api.client.util.DateTime;

public class CalendarEvent {
	DateTime start;
	DateTime end;
	String event;
	/**
	 * @param start
	 * @param end
	 * @param event
	 */
	public CalendarEvent(DateTime start, DateTime end, String event) {
		super();
		this.start = start;
		this.end = end;
		this.event = event;
	}
	
	
	
}
