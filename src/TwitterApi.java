import java.io.IOException;

import javax.websocket.Session;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApi extends Thread{
	private Session mSession;
	
	public TwitterApi(Session s) {
		this.mSession = s;
		this.start();
	}
	
	public void run() {
		while(true) {
			ConfigurationBuilder cBuilder = new ConfigurationBuilder();
			
			cBuilder.setDebugEnabled(true)
				.setOAuthConsumerKey("kyiffI0dnreGvvUgzBjggvriF")
				.setOAuthConsumerSecret("c9sfg7fawFF8tfvVgxmKmorC6eWzczy4RFF1EwsWwUthSYdkJS")
				.setOAuthAccessToken("3456064813-uHnTlpHi5pFqzbePpWXM9QDPh0XCfovsDMmCngl")
				.setOAuthAccessTokenSecret("gi2WJ7oNK6s1JacCRmN69HhUneZnn3njWJyFOLESNsNsd");
			
			TwitterFactory tFactory = new TwitterFactory(cBuilder.build());
			twitter4j.Twitter twitter = tFactory.getInstance();
			
			
			Trends trends;
			try {
				trends = twitter.getPlaceTrends(1);
				for(Trend trend : trends.getTrends()) {
					mSession.getBasicRemote().sendText(trend.getName());
				}

			} catch (TwitterException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(10000); //Ten seconds 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
