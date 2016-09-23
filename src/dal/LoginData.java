package dal;

public class LoginData {

	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=LU";
	private static String user = "bibbi4";
	private static String pw = "bibbi4";

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
