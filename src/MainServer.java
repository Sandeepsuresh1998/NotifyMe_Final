import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	String accessToken;
	ObjectOutputStream objectOutputStream;
	ObjectInputStream objectInputStream;
	Socket socket;
	
	public MainServer(int port) {
		
		ServerSocket ss = new ServerSocket(port);
		
		//Connect to Database
		
		//Get List of APIs and Access Tokens
		
		//Make threads for each API
		
		//Execute each thread to make call to said API
	}
	
	public static void main(String[] args) {
		new MainServer(6789);
	}
	
	public void updateAPI(String api_name) {
		
		//Switch statement
		switch(api_name) {
			case "twitter" :
				
			case "youtube" :
				
			case "gmail" :
				
			case "calendar" : 
				
			case "stocks" :
				
			case "weather" : 
				
		}
	}

}
