package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Student;

public class DataAccessLayer {

	private static Connection con = null;

	public static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(LoginData.getUrl(), LoginData.getUser(), LoginData.getPw());
	}

	public static Student getStudent(String spnr) {
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
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstate != null) {
				try {
					pstate.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return st;
	}

}
