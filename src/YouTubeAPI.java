import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.Session;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.SubscriptionListResponse;
import com.google.gson.Gson;

/**
 * 
 */

/**
 * @author yutianli
 *
 */
public class YouTubeAPI extends Thread {
	//private String accessToken;
	private GoogleCredential credential;
	private YouTube youtube;
	private Session session;
	
	/**
	 * 
	 */
	public YouTubeAPI(Session s, String accessToken) {
		this.session = s;
		System.out.println("In YouTube API Thread");
		// TODO Auto-generated constructor stub
		//this.accessToken = accessToken;
		credential = new GoogleCredential().setAccessToken(accessToken);
		youtube = new YouTube.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName("NotifyMe")
		        .build();
		start();
	}
	public void run(){
		//Use access token to call API
		while(session.isOpen()) {
			System.out.println("start youtube");
			SubscriptionListResponse slr = getSubscription();
			//System.out.println(slr);
			List<String> subscriptionList = new ArrayList<String>();
			List<String> videoList = new ArrayList<String>();
			int subscriptionNumber = 6;
			if(subscriptionNumber>slr.getItems().size()) {
				subscriptionNumber = slr.getItems().size();
			}
			for(int i=0; i<subscriptionNumber; i++) {
				System.out.println(i);
				//System.out.println(slr.getItems().get(i).getSnippet().getTitle());
				String channelId = slr.getItems().get(i).getSnippet().getResourceId().getChannelId();
				String firstVideoId = "https://www.youtube.com/embed/"+getFirstVideoIdFromAChannel(channelId)+"?autoplay=1";
				System.out.println(firstVideoId);
				videoList.add(firstVideoId);
			}
			Gson json = new Gson();
			String gson = json.toJson(videoList);
			String message = "YouTube|"+gson;
			try {
				System.out.println("before sending youtube");
				MainServer.sendUpdate(session, message);
				java.lang.Thread.sleep(10000000);
			} catch (InterruptedException | IllegalStateException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private SubscriptionListResponse getSubscription() {
		HashMap<String, String> parameters = new HashMap<>();
        parameters.put("part", "snippet, contentDetails");
        parameters.put("mine", "true");

        YouTube.Subscriptions.List subscriptionsListMySubscriptionsRequest = null;
		try {
			subscriptionsListMySubscriptionsRequest = youtube.subscriptions().list(parameters.get("part").toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if (parameters.containsKey("mine") && parameters.get("mine") != "") {
            boolean mine = (parameters.get("mine") == "true") ? true : false;
            subscriptionsListMySubscriptionsRequest.setMine(mine);
        }

        SubscriptionListResponse r=null;
		try {
			r = subscriptionsListMySubscriptionsRequest.execute();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //System.out.println(r);
        return r;
	}
	private String getFirstVideoIdFromAChannel(String ChannelId) {
		HashMap<String, String> parameters = new HashMap<>();
        parameters.put("part", "snippet,contentDetails,statistics");
        parameters.put("id", ChannelId);

        YouTube.Channels.List channelsListByIdRequest = null;
		try {
			channelsListByIdRequest = youtube.channels().list(parameters.get("part").toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if (parameters.containsKey("id") && parameters.get("id") != "") {
            channelsListByIdRequest.setId(parameters.get("id").toString());
        }
        String ChannelUploadListId=null;
        try {
			ChannelListResponse response = channelsListByIdRequest.execute();
			
			//System.out.println(response);
			ChannelUploadListId =  response.getItems().get(0).getContentDetails().getRelatedPlaylists().getUploads();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return getFirstItemOfPlayList(ChannelUploadListId); 
	}
	
	private String getFirstItemOfPlayList(String playListId) {
		HashMap<String, String> parameters = new HashMap<>();
        parameters.put("part", "snippet,contentDetails");
        parameters.put("maxResults", "25");
        parameters.put("playlistId", playListId);

        YouTube.PlaylistItems.List playlistItemsListByPlaylistIdRequest=null;
		try {
			playlistItemsListByPlaylistIdRequest = youtube.playlistItems().list(parameters.get("part").toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(playlistItemsListByPlaylistIdRequest);
        if (parameters.containsKey("maxResults")) {
            playlistItemsListByPlaylistIdRequest.setMaxResults(Long.parseLong(parameters.get("maxResults").toString()));
        }

        if (parameters.containsKey("playlistId") && parameters.get("playlistId") != "") {
            playlistItemsListByPlaylistIdRequest.setPlaylistId(parameters.get("playlistId").toString());
        }

        PlaylistItemListResponse response = null;
		try {
			response = playlistItemsListByPlaylistIdRequest.execute();
			//System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.getItems().get(0).getContentDetails().getVideoId();
	}
}
