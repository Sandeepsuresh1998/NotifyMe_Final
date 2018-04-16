import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.gson.Gson;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Thread;

public class GmailAPI extends java.lang.Thread {
	private Session session;
	private String accessToken;

	public GmailAPI(Session s, String token) {
		this.session = s;
		this.accessToken = token;
		this.start();
	}

	public List<Label> listLabels(String accessToken) throws IOException {
		// Build a new authorized API client service.
		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

		Gmail service = new Gmail.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName("NotifyMe").build();

		// Print the labels in the user's account.
		String user = "me";
		ListLabelsResponse listResponse = service.users().labels().list(user).execute();
		List<Label> labels = listResponse.getLabels();
		if (labels.size() == 0) {
			System.out.println("No labels found.");
		} else {
			System.out.println("Labels:");
		}
		for (Label label : labels) {
			System.out.printf("- %s\n", label.getName());
		}
		return labels;
	}

	/**
	 * List all Threads of the user's mailbox matching the query.
	 *
	 * @param service
	 *            Authorized Gmail API instance.
	 * @param userId
	 *            User's email address. The special value "me" can be used to
	 *            indicate the authenticated user.
	 * @param query
	 *            String used to filter the Threads listed.
	 * @throws IOException
	 */
	public void listThreadsMatchingQuery(Gmail service, String userId, String query) throws IOException {
		ListThreadsResponse response = service.users().threads().list(userId).setQ(query).execute();
		List<Thread> threads = new ArrayList<Thread>();
		while (response.getThreads() != null) {
			threads.addAll(response.getThreads());
			if (response.getNextPageToken() != null) {
				String pageToken = response.getNextPageToken();
				response = service.users().threads().list(userId).setQ(query).setPageToken(pageToken).execute();
			} else {
				break;
			}
		}

		for (Thread thread : threads) {
			System.out.println(thread.toPrettyString());
		}
	}

	/**
	 * List all Threads of the user's mailbox with labelIds applied.
	 *
	 * @param service
	 *            Authorized Gmail API instance.
	 * @param userId
	 *            User's email address. The special value "me" can be used to
	 *            indicate the authenticated user.
	 * @param labelIds
	 *            String used to filter the Threads listed.
	 * @throws IOException
	 */
	public void listThreadsWithLabels(Gmail service, String userId, List<String> labelIds) throws IOException {
		ListThreadsResponse response = service.users().threads().list(userId).setLabelIds(labelIds).execute();
		List<Thread> threads = new ArrayList<Thread>();
		while (response.getThreads() != null) {
			threads.addAll(response.getThreads());
			if (response.getNextPageToken() != null) {
				String pageToken = response.getNextPageToken();
				response = service.users().threads().list(userId).setLabelIds(labelIds).setPageToken(pageToken)
						.execute();
			} else {
				break;
			}
		}

		for (Thread thread : threads) {
			System.out.println(thread.toPrettyString());
		}
	}

	public void run() {
		while (true) {
			try {
				List<Label> labels = listLabels(accessToken);
				Gson json = new Gson();
				String gson = json.toJson(labels);
				String message = "Gmail|" + gson;
				session.getBasicRemote().sendText(message);
				java.lang.Thread.sleep(10000); // Ten seconds
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
