package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilDatabaseAccess {

	public void closeAll(PreparedStatement pStatement, Connection con) throws SQLException {

		if (pStatement != null && !pStatement.isClosed()) {
			pStatement.close();
		}
		if (con != null && !con.isClosed()) {
			con.close();
		}
	}
}
