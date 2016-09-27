package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Course;
import model.Student;
import model.Studied;
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
			closeAll(rSet, pStatement, con);
		}
		return s;
	}

	public Course getCourse(String ccode) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		Course c = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.getCourse());
			pStatement.setString(1, ccode);
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return c;
			} else {
				c = new Course();
				while (rSet.next()) {
					c.setCcode(rSet.getString("ccode"));
					c.setCname(rSet.getString("cname"));
					c.setCpoint(rSet.getInt("points"));
				}
			}
		} finally {
			closeAll(rSet, pStatement, con);
		}
		return c;
	}

	public ArrayList<String> getCcodes() throws SQLException {
		Connection con = null;
		ArrayList<String> c = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.getCcodes());
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return c;
			} else {
				c = new ArrayList<String>();
				while (rSet.next()) {
					c.add(rSet.getString("ccode"));
				}
			}
		} finally {
			closeAll(rSet, pStatement, con);
		}
		return c;
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
			closeAll(rSet, pStatement, con);
		}
		return studying;
	}

	public ArrayList<Studied> getStudentStudied(String spnr) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<Studied> studied = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.getStudentStudied());
			pStatement.setString(1, spnr);
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return studied;
			} else {
				studied = new ArrayList<Studied>();
				while (rSet.next()) {
					studied.add(new Studied(rSet.getString("semester"), (rSet.getString("ccode")),
							(rSet.getString("grade"))));
				}
			}
		} finally {
			closeAll(rSet, pStatement, con);
		}
		return studied;
	}

	public void deleteStudent(String spnr) {
		Connection con = null;
		PreparedStatement pStatement = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.deleteStudent());
			pStatement.setString(1, spnr);
			pStatement.execute();

		} catch (SQLException e) {
		}

	}

	public void deleteCourse(String ccode) {
		Connection con = null;
		PreparedStatement pStatement = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.deleteCourse());
			pStatement.setString(1, ccode);
			pStatement.execute();

		} catch (SQLException e) {
		}

	}

	// returnerar en student med alla attribut och kurser som studenten läser
	// och har läst
	public Student getStudentAll(String spnr) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		Student student = null;
		Studying studying = null;
		Studied studied = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.getStudent());
			pStatement.setString(1, spnr);
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return student;
			} else {
				student = new Student();
				setStudent(rSet, student);

				pStatement.close();
				pStatement = con.prepareStatement(util.getStudentStudying());
				pStatement.setString(1, spnr);
				rSet = pStatement.executeQuery();
				if (!rSet.isBeforeFirst()) {
					return student;
				} else {
					while (rSet.next()) {
						studying = new Studying();
						studying.setcCode(rSet.getString("ccode"));
						studying.setSemester(rSet.getString("semester"));
						student.addStudying(studying);
					}
					pStatement.close();
					pStatement = con.prepareStatement(util.getStudentStudied());
					pStatement.setString(1, spnr);
					rSet = pStatement.executeQuery();
					if (!rSet.isBeforeFirst()) {
						return student;
					} else {
						while (rSet.next()) {
							studied = new Studied();
							studied.setSemester(rSet.getString("semester"));
							studied.setcCode(rSet.getString("ccode"));
							studied.setGrade(rSet.getString("grade"));
							student.addStudied(studied);
						}
					}
				}
			}
		} finally {
			closeAll(rSet, pStatement, con);
		}
		return student;
	}

	private void closeAll(ResultSet rSet, PreparedStatement pStatement, Connection con) throws SQLException {
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

	private void setStudent(ResultSet rSet, Student s) throws SQLException {
		while (rSet.next()) {
			s.setSpnr(rSet.getString("spnr"));
			s.setSname(rSet.getString("sname"));
			s.setSaddress(rSet.getString("sadress"));
		}
	}

	public boolean createStudent(String spnr, String sname, String saddress) {
		Connection con = null;
		PreparedStatement pStatement = null;
		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.createStudent());
			pStatement.setString(1, spnr);
			pStatement.setString(2, sname);
			pStatement.setString(3, saddress);

			pStatement.execute();
			return true;

		} catch (SQLException e) {
			return false;

		}
	}

	public boolean createCourse(String ccode, String cname, String cpoint) {
		Connection con = null;
		PreparedStatement pStatement = null;
		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.createCourse());
			pStatement.setString(1, ccode);
			pStatement.setString(2, cname);
			pStatement.setString(3, cpoint);

			pStatement.execute();
			return true;

		} catch (SQLException e) {
			return false;

		}
	}

	public ArrayList<Studied> getCourseResult(String ccode) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<Studied> results = null;
		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.courseResult());
			pStatement.setString(1, ccode);
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return results;
			} else {
				results = new ArrayList<Studied>();
				while (rSet.next()) {
					results.add(new Studied(rSet.getString("spnr"), (rSet.getString("semester")),
							(rSet.getString("grade"))));
				}
			}
		} finally {
			closeAll(rSet, pStatement, con);
		}
		return results;

	}

	public String acedIt(String ccode) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		String percent = new String();

		try {
			con = createConnection();
			pStatement = con.prepareStatement(util.AcedIt());
			pStatement.setString(1, ccode);
			pStatement.setString(2, ccode);
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return percent;
			} else {
				while (rSet.next()) {

					percent = rSet.getString(1);
				}

			}

		} finally {
			closeAll(rSet, pStatement, con);
		}
		return percent;
	}
}
