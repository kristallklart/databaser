package databaseAccess;

public class LoginDataLu {

	private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=lu";
	private String user = "bibbi";
	private String pw = "bibbi";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
