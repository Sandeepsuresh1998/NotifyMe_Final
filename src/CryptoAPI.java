import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.websocket.Session;

public class CryptoAPI extends Thread {
	private Session mSession;
	public CryptoAPI(Session s) {
		this.mSession = s;
		this.start();
	}
	
	public void run() {
		String cryptoURL = "https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_DAILY&symbol=BTC&market=CNY&apikey=6709TV0OTTNCIU2L";
		while(true) {
			try {
				URL url = new URL(cryptoURL);
		        URLConnection yc = url.openConnection();
		        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		        String inputLine;
		        String response = "";
		        while ((inputLine = in.readLine()) != null) {
		        		response += inputLine;
		        }
		        System.out.println(response);
		        String stockData = "Crypto|" + response;
		        mSession.getBasicRemote().sendText(stockData);
		        Thread.sleep(60000); //Refresh every minute seconds
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
