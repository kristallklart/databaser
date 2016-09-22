package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Course;
import model.Student;

public class DataAccessLayer {

	private static Connection con = null;

	public static Connection createConnection() throws SQLException {

		return DriverManager.getConnection(LoginData.getUrl(), LoginData.getUser(), LoginData.getPw());

	}

	public static Student getStudent(String spnr) throws SQLException {

		PreparedStatement pstate = null;
		ResultSet rs = null;
		Student st = new Student();

		con = DataAccessLayer.createConnection();
		pstate = con.prepareStatement(Util.getStudent());
		pstate.setString(1, spnr);
		rs = pstate.executeQuery();

		while (rs.next()) {
			st.setSname(rs.getString("sname"));
			st.setSaddress(rs.getString("sadress"));

		}
		return st;
	}

	public static Course getCourse(String ccode) throws SQLException {

		PreparedStatement pstate = null;
		ResultSet rs = null;
		Course cc = new Course();

		con = DataAccessLayer.createConnection();
		pstate = con.prepareStatement(Util.getCourse());
		pstate.setString(1, ccode);
		rs = pstate.executeQuery();

		while (rs.next()) {
			cc.setCcode(rs.getString("ccode"));
			cc.setCname(rs.getString("cname"));
			cc.setCpoint(rs.getInt("points"));
		}
		return cc;
	}

	public static ArrayList<Course> getCourses() throws SQLException {
		ArrayList<Course> courses = new ArrayList<Course>();
		PreparedStatement pstate = null;
		ResultSet rs = null;

		con = DataAccessLayer.createConnection();
		pstate = con.prepareStatement(Util.getCourseInfo());
		rs = pstate.executeQuery();

		while (rs.next()) {
			Course c = new Course();
			c.setCcode(rs.getString(1));
			c.setCname(rs.getString(2));
			c.setCpoint(rs.getInt(3));
			courses.add(c);
		}
		return courses;
	}
}
