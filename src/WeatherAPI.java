import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


import javax.websocket.Session;

public class WeatherAPI extends Thread{
	private Session mSession;
	
	public WeatherAPI(Session s) {
		this.mSession = s;
		this.start();
	}
	
	public void run() {
		String weatherURL = "http://api.openweathermap.org/data/2.5/weather?id=5368381&APPID=2273fa9708ebadbe74541112635069ba";
			try {
				System.out.println("start weather");
				URL url = new URL(weatherURL);
		        URLConnection yc = url.openConnection();
		        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		        String inputLine;
		        String response = "";
		        while ((inputLine = in.readLine()) != null) {
		        	response += inputLine;
		        }
		        String weatherData = "Weather|" + response;
		        System.out.println("before sending weather");
		        MainServer.sendUpdate(mSession, weatherData);
		        Thread.sleep(600000);  // 600 seconds
			} catch (IOException | InterruptedException | IllegalStateException e) {
				e.printStackTrace();
			}
		
	}
}
