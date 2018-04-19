import java.sql.*;
import java.util.ArrayList;

public class DBController {
	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;

	public static void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/FPDB?user=root&password=Huskers25!!&useSSL=false");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if (ps != null) {
				ps = null;
			}
		} catch (SQLException sqle) {
			System.out.println("connection close error");
			sqle.printStackTrace();
		}
	}

	// isReturningUser: check if the user has registered before
	// parameter String userId
	// return true if it is returning user
	// return false if it is new user
	public static boolean isReturningUser(String userId) {
		connect();
		try {
			ps = conn.prepareStatement("select exists (select userId from UserInfo where userId=?)");
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) == 1) {
					System.out.println("true");
					return true;
				}
				System.out.println("false");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("SQLException in function \"isReturningUser\"");
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// signUpNewUser: add the new user into the database
	// parameter String userId, String givenName, String familyName, String
	// pictureUrl, String email
	// return none
	public static void signUpNewUser(String userId, String givenName, String familyName, String pictureUrl,
			String email) {
		connect();
		try {
			ps = conn.prepareStatement("insert into UserInfo (userId, givenName, familyName, pictureUrl, email, "
					+ "useTwitter, useGmail, useWeather, useCalendar, useYouTube, useStock) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, userId);
			ps.setString(2, givenName);
			ps.setString(3, familyName);
			ps.setString(4, pictureUrl);
			ps.setString(5, email);
			ps.setBoolean(6, true);
			ps.setBoolean(7, false);
			ps.setBoolean(8, true);
			ps.setBoolean(9, true);
			ps.setBoolean(10, true);
			ps.setBoolean(11, true);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException in function \"signUpNewUser\"");
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println("sign up success");
	}

	// updateUserInfo: update user info stored in database
	// parameter String userId, String givenName, String familyName, String
	// pictureUrl
	// return none
	public static void updateUserInfo(String userId, String givenName, String familyName, String pictureUrl) {
		connect();
		try {
			ps = conn.prepareStatement("update UserInfo set givenName=?, familyName=?, pictureUrl=? where userId=?");
			ps.setString(1, givenName);
			ps.setString(2, familyName);
			ps.setString(3, pictureUrl);
			ps.setString(4, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException in function \"updateUserInfo\"");
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println("update user info success");
	}

	// getUserInfo: get user info
	// parameter String userId
	// return ArrayList<String>
	// index 0 = first name
	// index 1 = last name
	// index 2 = profile picture url
	// index 3 = email
	public static ArrayList<String> getUserInfo(String userId) {
		connect();
		ArrayList<String> UserInfo = null;
		try {
			ps = conn.prepareStatement("select givenName, familyName, pictureUrl, email from UserInfo where userId=?");
			ps.setString(1, userId);
			rs = ps.executeQuery();
			UserInfo = new ArrayList<>();
			while (rs.next()) {
				UserInfo.add(rs.getString("givenName"));
				UserInfo.add(rs.getString("familyName"));
				UserInfo.add(rs.getString("pictureUrl"));
				UserInfo.add(rs.getString("email"));
			}
		} catch (SQLException e) {
			System.out.println("SQLException in function \"getUserInfo\"");
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println("get user info success");
		return UserInfo;
	}

	// updatePreferences: update user preference on if he wants a widget to display
	// or not
	// parameter String userId, boolean useTwitter, boolean useGmail, boolean
	// useWeather,
	// boolean useCalendar, boolean useYouTube, boolean useStock
	// return none
	public static void updateWidgets(String userId, boolean useTwitter, boolean useGmail, boolean useWeather,
			boolean useCalendar, boolean useYouTube, boolean useStock) {
		connect();
		try {
			ps = conn.prepareStatement("update UserInfo set useTwitter=?, useGmail=?, useWeather=?, "
					+ "useCalendar=?, useYouTube=?, useStock=? where userId=?");
			ps.setBoolean(1, useTwitter);
			ps.setBoolean(2, useGmail);
			ps.setBoolean(3, useWeather);
			ps.setBoolean(4, useCalendar);
			ps.setBoolean(5, useYouTube);
			ps.setBoolean(6, useStock);
			ps.setString(7, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException in function \"updatePreferences\"");
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println("update preference success");
	}

	// getPreferences: get user preference on if he wants a widget to display or not
	// parameter String userId
	// return ArrayList<Boolean>
	// index 0 = twitter
	// index 1 = gmail
	// index 2 = weather
	// index 3 = calendar
	// index 4 = youtube
	// index 5 = stock
	public static ArrayList<Boolean> getWidgets(String userId) {
		connect();
		ArrayList<Boolean> preferences = null;
		try {
			ps = conn.prepareStatement("select useTwitter, useGmail, useWeather, "
					+ "useCalendar, useYouTube, useStock from UserInfo where userId=?");
			ps.setString(1, userId);
			rs = ps.executeQuery();
			preferences = new ArrayList<>();
			while (rs.next()) {
				preferences.add(rs.getBoolean("useTwitter"));
				preferences.add(rs.getBoolean("useGmail"));
				preferences.add(rs.getBoolean("useWeather"));
				preferences.add(rs.getBoolean("useCalendar"));
				preferences.add(rs.getBoolean("useYouTube"));
				preferences.add(rs.getBoolean("useStock"));
			}
		} catch (SQLException e) {
			System.out.println("SQLException in function \"getPreferences\"");
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println("get preference success");
		return preferences;
	}

	public static void addAccessToken(String userId, String accessToken) {
		connect();
		try {
			ps = conn.prepareStatement("update UserInfo set accessToken=? where userId=?");
			ps.setString(1, accessToken);
			ps.setString(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException in function \"addAccessToken\"");
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println("add accessToken success");
	}

	public static String getAccessToken(String userId) {
		connect();
		String accessToken = null;
		try {
			ps = conn.prepareStatement("select accessToken from UserInfo where userId=?");
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				accessToken = rs.getString("accessToken");
			}
		} catch (SQLException e) {
			System.out.println("SQLException in function \"getAccessToken\"");
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println("get accessToken success");
		return accessToken;
	}

	// main for testing above mothods
	// public static void main(String[] args) {
	// isReturningUser("runwei");
	// isReturningUser("qwerty");
	// signUpNewUser("runwei", "Runwei", "Lin",
	// "https://pbs.twimg.com/profile_images/752912459388841984/aKLwROi__400x400.jpg",
	// "runweili@usc.edu");
	// isReturningUser("runwei");
	// isReturningUser("qwerty");
	// isReturningUser("miller");
	// signUpNewUser("miller", "Jeff", "Miller",
	// "http://www-scf.usc.edu/~csci201/images/jeffrey_miller.jpg",
	// "fakemiller@usc.edu");
	// isReturningUser("miller");
	// ArrayList<String> userinfo = getUserInfo("miller");
	// String fname = userinfo.get(0);
	// String lname = userinfo.get(1);
	// String url = userinfo.get(2);
	// System.out.println(fname + " " + lname + " " + url);
	// updateUserInfo("miller", "Jeffery", lname, url);
	// userinfo = getUserInfo("miller");
	// fname = userinfo.get(0);
	// lname = userinfo.get(1);
	// url = userinfo.get(2);
	// System.out.println(fname + " " + lname + " " + url);
	// ArrayList<Boolean> preferences = getPreferences("runwei");
	// boolean twitter = preferences.get(0);
	// boolean gmail = preferences.get(1);
	// boolean weather = preferences.get(2);
	// boolean calendar = preferences.get(3);
	// boolean youtube = preferences.get(4);
	// boolean stock = preferences.get(5);
	// System.out.println(twitter + " " + gmail + " " + weather + " " + calendar + "
	// " + youtube + " " + stock);
	// updatePreferences("runwei", twitter, true, false, calendar, youtube, false);
	// preferences = getPreferences("runwei");
	// twitter = preferences.get(0);
	// gmail = preferences.get(1);
	// weather = preferences.get(2);
	// calendar = preferences.get(3);
	// youtube = preferences.get(4);
	// stock = preferences.get(5);
	// System.out.println(twitter + " " + gmail + " " + weather + " " + calendar + "
	// " + youtube + " " + stock);
	// }
	//
	// Twitter API Widget
	// Sandeep Suresh

	// Gmail API
	// Runwei Lin

	// OpenWeatherMap API
	// Zach Harju

	// Google Calendar API
	// Sandeep Suresh

	// YouTube API
	// Sandeep Suresh

	// News API by CNN
	// Runwei Lin

}