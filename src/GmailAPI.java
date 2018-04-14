import java.io.IOException;
import java.util.List;

import javax.websocket.Session;

import com.google.api.services.gmail.model.Label;
import com.google.gson.Gson;

public class GmailAPI extends Thread {
	private Session session;
	private String accessToken;
	
	public GmailAPI(Session s, String accessToken) {
		this.session = s;
		this.accessToken = accessToken;
		this.start();
	}
	
	public void run() {
		while(true) {
			try {
				List<Label> labels = GmailLabels.listLabels(accessToken);
				Gson json = new Gson();
				String gson = json.toJson(labels);
				String message = "gmail|" + gson;
				session.getBasicRemote().sendText(message);
				Thread.sleep(10000); //Ten seconds 
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
