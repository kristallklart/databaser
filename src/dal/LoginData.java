package dal;

public class LoginData {

	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=DB";
	private static String user = "test";
	private static String pw = "test";
	private static String test = "test";
	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}

	public static String getPw() {
		return pw;
	}

}
