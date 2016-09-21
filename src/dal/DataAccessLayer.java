package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Student;

public class DataAccessLayer {

	static LoginData loginData = new LoginData();
	static Connection con = null;

	public static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(LoginData.getUrl(), LoginData.getUser(), LoginData.getPw());
	}

	public static Student getStudent(String spnr) {
		Connection con = null;
		PreparedStatement pstate = null;
		ResultSet rs = null;
		Student st = new Student();

		try {
			con = DataAccessLayer.createConnection();
			pstate = con.prepareStatement(Util.getStudent());
			pstate.setString(1, spnr);
			rs = pstate.executeQuery();

			while (rs.next()) {
				st.setSname(rs.getString("sname"));
				st.setSaddress(rs.getString("sadress"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return st;
	}
}
