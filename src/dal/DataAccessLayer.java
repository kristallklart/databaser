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

	private Connection con = null;
	private LoginData login = new LoginData();
	private Util util = new Util();

	public Connection createConnection() throws SQLException {

		return DriverManager.getConnection(login.getUrl(), login.getUser(), login.getPw());

	}

	public Student getStudent(String spnr) throws SQLException {

		PreparedStatement pstate = null;
		ResultSet rs = null;
		Student st = new Student();

		con = createConnection();
		pstate = con.prepareStatement(util.getStudent());
		pstate.setString(1, spnr);
		rs = pstate.executeQuery();

		while (rs.next()) {
			st.setSname(rs.getString("sname"));
			st.setSaddress(rs.getString("sadress"));

		}
		return st;
	}

	public Course getCourse(String ccode) throws SQLException {

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

	public ArrayList<Studying> getStudentStudying(String pnr) throws SQLException {

		ArrayList<Studying> stud = new ArrayList<Studying>();
		PreparedStatement pstate = null;
		ResultSet rs = null;

		con = createConnection();
		pstate = con.prepareStatement(util.getStudentStudying());
		pstate.setString(1, pnr);
		rs = pstate.executeQuery();

		while (rs.next()) {
			stud.add(new Studying(rs.getString("ccode"), (rs.getString("semester"))));

		}
		return stud;

	}
}
