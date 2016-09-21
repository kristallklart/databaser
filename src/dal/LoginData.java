package dal;

public class LoginData {

	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=DB";
	private static String user = "test";
	private static String pw = "test";

	static String getUrl() {
		return url;
	}

	static String getUser() {
		return user;
	}

	static String getPw() {
		return pw;
	}

}
