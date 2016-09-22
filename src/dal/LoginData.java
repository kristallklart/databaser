package dal;

public class LoginData {

	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=LU";
	private static String user = "bibbi3";
	private static String pw = "bibbi3";

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
