package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAccessLayer {

	static LoginData loginData = new LoginData();
	static Connection con = null;

	public static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(LoginData.getUrl(), LoginData.getUser(), LoginData.getPw());
	}
}
