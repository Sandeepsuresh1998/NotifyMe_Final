import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.websocket.Session;

public class StockAPI extends Thread {
	private Session mSession;
	public StockAPI(Session s) {
		mSession = s;
		this.start();
	}
	
	public void run() {
		String stockURL = "https://www.alphavantage.co/query?function=BATCH_STOCK_QUOTES&symbols=AAPL,NFLX,MSFT,TSLA,FB,GOOGL,AMZN&apikey=6709TV0OTTNCIU2L";
		while(true) {
			try {
				URL url = new URL(stockURL);
		        URLConnection yc = url.openConnection();
		        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		        String inputLine;
		        String response = "";
		        while ((inputLine = in.readLine()) != null) {
		        		response += inputLine;
		        }
		        System.out.println(response);
		        String stockData = "Stocks|" + response;
		        mSession.getBasicRemote().sendText(stockData);
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
