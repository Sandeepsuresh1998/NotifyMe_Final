import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.gson.Gson;
import com.google.api.services.gmail.model.Message;

public class GmailAPI extends java.lang.Thread {
	private Session session;
	private String accessToken;
	
	public GmailAPI(Session s, String token) {
		this.session = s;
		this.accessToken = token;
		this.start();
	}

//	public List<Label> listLabels(String accessToken) throws IOException {
//		// Build a new authorized API client service.
//		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
//
//		Gmail service = new Gmail.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
//				.setApplicationName("NotifyMe").build();
//
//		String user = "me";
//		ListLabelsResponse listResponse = service.users().labels().list(user).execute();
//		List<Label> labels = listResponse.getLabels();
//		if (labels.size() == 0) {
//			System.out.println("No labels found.");
//		} else {
//			System.out.println("Labels:");
//		}
//		for (Label label : labels) {
//			System.out.printf("- %s\n", label.getName());
//		}
//		return labels;
//	}
	
	/**
	 * Get Message with given ID.
	 *
	 * @param service Authorized Gmail API instance.
	 * @param userId User's email address. The special value "me" can be used to indicate the authenticated user.
	 * @param messageId ID of Message to retrieve.
	 * @return Message Retrieved Message.
	 * @throws IOException
	 */
	public Message getMessage(Gmail service, String userId, String messageId) throws IOException {
		List<String> metadataHeaders = new ArrayList<>();
		metadataHeaders.add("From");
		metadataHeaders.add("Subject");
//		Message message = service.users().messages().get(userId, messageId).setFormat("metadata").setMetadataHeaders(metadataHeaders).execute();
		Message message = service.users().messages().get(userId, messageId).execute();
		if (message == null) {
			System.out.println("null message");
		}
		else {
			System.out.println("not null message");
			System.out.println(message.toPrettyString());
		}
		System.out.println("Message snippet: " + message.getSnippet());
		return message;
	}
	
	/**
	 * List all Messages of the user's mailbox matching the query.
	 *
	 * @param service Authorized Gmail API instance.
	 * @param userId User's email address. The special value "me" can be used to indicate the authenticated user.
	 * @param query String used to filter the Messages listed.
	 * @throws IOException
	 */
	public List<Header> listMessages(String accessToken) throws IOException {
		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

		Gmail service = new Gmail.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName("NotifyMe").build();
		
		String userId = "me";
		ListMessagesResponse response = service.users().messages().list(userId).setMaxResults((long) 8).execute();

		List<Message> messages = new ArrayList<Message>();

		if (response.getMessages() != null) {
			messages.addAll(response.getMessages());
		}
		for (Message message : messages) {
			System.out.println(message.toPrettyString());
		}
		
		ArrayList<Header> headers = new ArrayList<Header>();
		for (int i = 0; i < 8; i++) {
			String messageId = messages.get(i).getId();
			System.out.println(messageId);
			Message message = getMessage(service, userId, messageId);
//			Message message = getMessage(messageId);
			int size = message.getPayload().getHeaders().size();
			String from = null;
			String subject = null;
//			String snippet = null;
			String snippet = message.getSnippet();
			if (snippet.length() > 50) {
				snippet = (snippet.substring(0, 45) + "...");
			}
			for (int j = 0; j < size; j++) {
				String temp = message.getPayload().getHeaders().get(j).getName();
				if (temp.equals("From")) {
					from = message.getPayload().getHeaders().get(j).getValue();
				}
				else if (temp.equals("Subject")) {
					subject = message.getPayload().getHeaders().get(j).getValue();
				}
			}
			headers.add(new Header(from, subject, snippet));
			System.out.println("message index " + i + " from " + from + " subject " + subject + " snippet " + snippet);
		}
		
		return headers;
	}

	public void run() {
		while (session.isOpen()) {
			try {
//				List<Label> labels = listLabels(accessToken);
				List<Header> headers = listMessages(accessToken);
				Gson json = new Gson();
//				String gson = json.toJson(labels);
				String gson = json.toJson(headers);
				String message = "Gmail|" + gson;
//				session.getBasicRemote().sendText(message);
				MainServer.sendUpdate(session, message);
				java.lang.Thread.sleep(10000); // 10 seconds
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
