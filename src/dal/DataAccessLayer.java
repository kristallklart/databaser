package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Course;
import model.Student;
import model.Studying;

public class DataAccessLayer {
	private LoginData login = new LoginData();
	private Util util = new Util();

	public Connection createConnection() throws SQLException {
		return DriverManager.getConnection(login.getUrl(), login.getUser(), login.getPw());
	}

	public Student getStudent(String spnr) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		Student s = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.getStudent());
			pStatement.setString(1, spnr);
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return s;
			} else {
				s = new Student();
				while (rSet.next()) {
					s.setSpnr(rSet.getString("spnr"));
					s.setSname(rSet.getString("sname"));
					s.setSaddress(rSet.getString("sadress"));
				}
			}
		} finally {
			if (rSet != null && !rSet.isClosed()) {
				rSet.close();
			}
			if (pStatement != null && !pStatement.isClosed()) {
				pStatement.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
		}
		return s;
	}

	public Course getCourse(String ccode) throws SQLException {
		Connection con = null;
		PreparedStatement pstate = null;
		ResultSet rs = null;
		Course cc = new Course();

		con = createConnection();
		pstate = con.prepareStatement(util.getCourse());
		pstate.setString(1, ccode);
		rs = pstate.executeQuery();

		while (rs.next()) {
			cc.setCcode(rs.getString("ccode"));
			cc.setCname(rs.getString("cname"));
			cc.setCpoint(rs.getInt("points"));
		}
		return cc;
	}

	public ArrayList<String> getCcodes() throws SQLException {
		Connection con = null;
		ArrayList<String> courses = new ArrayList<String>();
		PreparedStatement pstate = null;
		ResultSet rs = null;

		con = createConnection();
		pstate = con.prepareStatement(util.getCourse());
		rs = pstate.executeQuery();

		while (rs.next()) {
			courses.add(rs.getString("ccname"));
		}
		return courses;
	}

	public ArrayList<Studying> getStudentStudying(String spnr) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<Studying> studying = null;
		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.getStudentStudying());
			pStatement.setString(1, spnr);
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return studying;
			} else {
				studying = new ArrayList<Studying>();
				while (rSet.next()) {
					studying.add(new Studying(rSet.getString("ccode"), (rSet.getString("semester"))));
				}
			}
		} finally {
			if (rSet != null && !rSet.isClosed()) {
				rSet.close();
			}
			if (pStatement != null && !pStatement.isClosed()) {
				pStatement.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
		}
		return studying;
	}
}
