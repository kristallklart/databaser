package dal;

public class LoginData {

	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=praktikfall";
	private static String user = "bibbi";
	private static String pw = "bibbi";

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
