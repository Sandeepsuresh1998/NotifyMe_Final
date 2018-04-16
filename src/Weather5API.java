import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.websocket.Session;

public class Weather5API extends Thread {
	private Session mSession;
	
	public Weather5API(Session s) {
		this.mSession = s;
		this.start();
	}
	
	public void run() {
		String fiveURL = "http://api.openweathermap.org/data/2.5/forecast?id=5368381&APPID=afbcfd3e650101530d7d00ed5956e2e0";
		while(true) {
			try {
				URL url = new URL(fiveURL);
		        URLConnection yc = url.openConnection();
		        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		        String inputLine;
		        String response = "";
		        while ((inputLine = in.readLine()) != null) {
		        		response += inputLine;
		        }
		        System.out.println(response);
		        String weatherData = "five|" + response;
		        mSession.getBasicRemote().sendText(weatherData);
		            
		        Thread.sleep(600000);
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
				ioe.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
