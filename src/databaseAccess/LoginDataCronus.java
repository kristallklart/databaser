package databaseAccess;

public class LoginDataCronus {

		private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Demo Database NAV (5-0)";
		private String user = "bibbi2";
		private String pw = "bibbi2";

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
