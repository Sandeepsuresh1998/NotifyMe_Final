import java.io.IOException;
import java.util.HashMap;

import javax.crypto.spec.GCMParameterSpec;
import javax.websocket.Session;

import org.apache.http.impl.client.EntityEnclosingRequestWrapper;

import com.google.gson.Gson;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApi extends Thread{
	private Session mSession;
	
	public TwitterApi(Session s) {
		this.mSession = s;
		this.start();
	}
	
	public HashMap<String, String> getTwitterTreading() throws TwitterException {		
		ConfigurationBuilder cBuilder = new ConfigurationBuilder();
		
		cBuilder.setDebugEnabled(true)
			.setOAuthConsumerKey("kyiffI0dnreGvvUgzBjggvriF")
			.setOAuthConsumerSecret("c9sfg7fawFF8tfvVgxmKmorC6eWzczy4RFF1EwsWwUthSYdkJS")
			.setOAuthAccessToken("3456064813-uHnTlpHi5pFqzbePpWXM9QDPh0XCfovsDMmCngl")
			.setOAuthAccessTokenSecret("gi2WJ7oNK6s1JacCRmN69HhUneZnn3njWJyFOLESNsNsd");
		
		TwitterFactory tFactory = new TwitterFactory(cBuilder.build());
		twitter4j.Twitter twitter = tFactory.getInstance();
		
		
		Trends trends = twitter.getPlaceTrends(1);
		
		HashMap<String, String> twitterData = new HashMap<String, String>();
		for(Trend t : trends.getTrends()) {
			twitterData.put(t.getName(), t.getURL());
		}
		
		return twitterData;
	}
	
	public void run() {
		while(mSession.isOpen()) {
			try {
				System.out.println("start twitter");
				HashMap<String, String> twitterData = getTwitterTreading();
				Gson gson = new Gson();
				String twitterJson = gson.toJson(twitterData);
				String data = "Twitter|" + twitterJson;
//				mSession.getBasicRemote().sendText(data);
				System.out.println("before sending twitter");
				MainServer.sendUpdate(mSession, data);
				Thread.sleep(60000); // 60 seconds 
			} catch (TwitterException | InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		
	}
}
