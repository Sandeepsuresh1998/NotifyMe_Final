//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//
//import com.google.api.services.gmail.model.*;
//import com.google.api.services.gmail.Gmail;
//
//import java.io.IOException;
//import java.util.List;
//
//public class GmailLabels {
//
//  /**
//   * List the Labels in the user's mailbox.
//   *
//   * @param service Authorized Gmail API instance.
//   * @param userId User's email address. The special value "me"
//   * can be used to indicate the authenticated user.
//   * @throws IOException
//   */
//  public static List<Label> listLabels(String accessToken) throws IOException {
//	  // Build a new authorized API client service.
//	  GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
//	  
//	  Gmail service = new Gmail.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
//              .setApplicationName("NotifyMe")
//              .build();
//	
//	  // Print the labels in the user's account.
//	  String user = "me";
//	  ListLabelsResponse listResponse =service.users().labels().list(user).execute();
//	  List<Label> labels = listResponse.getLabels();
//	  if (labels.size() == 0) {
//	      System.out.println("No labels found.");
//	  }
//	  else {
//	      System.out.println("Labels:");
//	  }
//	  for (Label label : labels) {
//	      System.out.printf("- %s\n", label.getName());
//	  }
//	  return labels;
//  }
//}
