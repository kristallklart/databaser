package databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.Course;
import model.Student;
import model.Studied;
import model.Studying;
import utilities.NotFoundException;
import utilities.UtilDatabaseAccess;
import utilities.UtilLu;

public class DataAccessLayerLu {

	private LoginDataLu login = new LoginDataLu();
	private QueriesLu queriesLu = new QueriesLu();
	private UtilDatabaseAccess utilDatabaseAccess = new UtilDatabaseAccess();
	private UtilLu utilLu = new UtilLu();

	public Connection createConnection() throws SQLException {
		return DriverManager.getConnection(login.getUrl(), login.getUser(), login.getPw());
	}

	public Student getStudent(String spnr) throws SQLException, NotFoundException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.getStudent());
			pStatement.setString(1, spnr);
			rSet = pStatement.executeQuery();

			if (!rSet.isBeforeFirst()) {
				throw new NotFoundException("No student found");
			}

			Student s = new Student();
			setStudent(rSet, s);

			return s;

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public Course getCourse(String ccode) throws SQLException, NotFoundException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.getCourse());
			pStatement.setString(1, ccode);
			rSet = pStatement.executeQuery();

			if (!rSet.isBeforeFirst()) {
				throw new NotFoundException("No course found");
			}

			Course c = new Course();
			setCourse(rSet, c);

			return c;

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public ArrayList<String> getCcodes() throws SQLException {
		Connection con = null;
		ArrayList<String> c = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.getCcodes());
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
			utilDatabaseAccess.closeAll(pStatement, con);
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
			pStatement = con.prepareStatement(queriesLu.getStudentStudying());
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
			utilDatabaseAccess.closeAll(pStatement, con);
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
			pStatement = con.prepareStatement(queriesLu.getStudentStudied());
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
			utilDatabaseAccess.closeAll(pStatement, con);
		}
		return studied;
	}

	public void deleteStudent(String spnr) throws SQLException, NotFoundException {
		Connection con = null;
		PreparedStatement pStatement = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.deleteStudent());
			pStatement.setString(1, spnr);
			int i = pStatement.executeUpdate();
			if (i < 1) {
				throw new NotFoundException("Failed to delete student");
			}
		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}

	}

	public void deleteCourse(String ccode) {
		Connection con = null;
		PreparedStatement pStatement = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.deleteCourse());
			pStatement.setString(1, ccode);
			pStatement.execute();

		} catch (SQLException e) {
		}

	}

	private void setStudent(ResultSet rSet, Student s) throws SQLException {
		while (rSet.next()) {
			s.setSpnr(rSet.getString("spnr"));
			s.setSname(rSet.getString("sname"));
			s.setSaddress(rSet.getString("sadress"));
		}
	}

	private void setCourse(ResultSet rSet, Course c) throws SQLException {
		while (rSet.next()) {
			c.setCcode(rSet.getString("ccode"));
			c.setCname(rSet.getString("cname"));
			c.setCpoint(rSet.getInt("points"));
		}
	}

	public void addStudent(Student s) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.addStudent());
			pStatement.setString(1, s.getSpnr());
			pStatement.setString(2, s.getSname());
			pStatement.setString(3, s.getSaddress());
			pStatement.executeUpdate();
		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public boolean createCourse(String ccode, String cname, String cpoint) {
		Connection con = null;
		PreparedStatement pStatement = null;
		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.createCourse());
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
			pStatement = con.prepareStatement(queriesLu.courseResult());
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
			utilDatabaseAccess.closeAll(pStatement, con);
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
			pStatement = con.prepareStatement(queriesLu.AcedIt());
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
			utilDatabaseAccess.closeAll(pStatement, con);
		}
		return percent;
	}

	public void registerGrade(Studied s) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.registerGrade());
			pStatement.setString(1, s.getsPnr());
			pStatement.setString(2, s.getcCode());
			pStatement.setString(3, s.getGrade());
			pStatement.setString(4, s.getSemester());

			pStatement.executeUpdate();

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public void registerOnCourse(Studying s) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.registerOnCourse());
			pStatement.setString(1, s.getsPnr());
			pStatement.setString(2, s.getcCode());
			pStatement.setString(3, s.getSemester());
			pStatement.executeUpdate();
		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public void deleteStudying(String spnr, String ccode) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.deleteStudying());
			pStatement.setString(1, spnr);
			pStatement.setString(2, ccode);
			pStatement.executeUpdate();

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}

	}

	public ArrayList<Studying> notFinished(String ccode) throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<Studying> nf = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.notFinished());
			pStatement.setString(1, ccode);
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return nf;
			} else {
				nf = new ArrayList<Studying>();
				while (rSet.next()) {
					Studying s = new Studying();
					s.setsPnr(rSet.getString("spnr"));
					s.setSemester((rSet.getString("semester")));
					nf.add(s);
				}
			}
		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
		return nf;
	}

	public ArrayList<Course> allCourses() throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<Course> c = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.allCourses());
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return c;
			} else {
				c = new ArrayList<Course>();
				while (rSet.next()) {
					Course co = new Course();
					co.setCcode(rSet.getString("ccode"));
					co.setCname(rSet.getString("cname"));
					co.setCpoint(rSet.getInt("points"));
					c.add(co);
				}
			}
		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
		return c;
	}

	public ArrayList<Course> mostThrough() throws SQLException {
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<Course> c = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.mostThrough());
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				return c;
			} else {
				c = new ArrayList<Course>();
				while (rSet.next()) {
					Course co = new Course();
					co.setCcode(rSet.getString("ccode"));
					co.setTotal(rSet.getInt("totalt"));
					c.add(co);
				}
			}
		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
		return c;
	}

	public DefaultTableModel getTableAll(ArrayList<String> values, String tableName)
			throws SQLException, NotFoundException {
		Vector<Vector<Object>> sendData = new Vector<Vector<Object>>();
		Vector<String> sendColumnNames = new Vector<String>();

		String query = utilLu.getTableQuery(tableName);
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ResultSetMetaData rSetMeta = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(query);

			int x = 0;
			while (x < values.size()) {
				pStatement.setString(x + 1, values.get(x));
				x++;
			}
			rSet = pStatement.executeQuery();
			if (!rSet.isBeforeFirst()) {
				throw new NotFoundException(tableName);
			}
			rSetMeta = rSet.getMetaData();
			int numberOfColumns = rSetMeta.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++) {
				sendColumnNames.add(rSetMeta.getColumnName(i));
			}

			while (rSet.next()) {
				Vector<Object> columnData = new Vector<Object>();
				for (int i = 1; i <= numberOfColumns; i++) {
					columnData.add(rSet.getObject(i));
				}
				sendData.add(columnData);
			}

			DefaultTableModel model = new DefaultTableModel(sendData, sendColumnNames);

			return model;

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public void createAll(ArrayList<Object> values, String studentOrCourse) throws SQLException {
		String query = utilLu.getCreateQuery(studentOrCourse);
		Connection con = null;
		PreparedStatement pStatement = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(query);

			int x = 0;
			while (x < values.size()) {
				if (values.get(x) instanceof String) {
					pStatement.setString(x + 1, values.get(x).toString());
					x++;
				} else if (values.get(x) instanceof Integer) {
					int z = (int) values.get(x);
					pStatement.setInt(x + 1, z);
					x++;
				}
			}

			pStatement.execute();

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public boolean studentExist(String spnr) throws SQLException {
		String query = utilLu.getStudent();
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(query);
			pStatement.setString(1, spnr);
			rSet = pStatement.executeQuery();

			if (!rSet.isBeforeFirst()) {
				return false;
			}
			return true;

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}

	}

	public int currentPoints(String spnr) throws SQLException, NotFoundException {
		String query = utilLu.currentPoints();
		Connection con = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		int points = 0;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(query);
			pStatement.setString(1, spnr);
			rSet = pStatement.executeQuery();

			while (rSet.next()) {
				points += rSet.getInt("points");
			}
			return points;

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public int deleteAll(Object object) throws SQLException, NotFoundException {
		String query;
		Connection con = null;
		PreparedStatement pStatement = null;
		Student s = null;
		Course c = null;

		if (object instanceof Student) {
			s = (Student) object;
			query = utilLu.deleteStudent();
		} else if (object instanceof Course) {
			c = (Course) object;
			query = utilLu.deleteCourse();
		} else {
			throw new NotFoundException("sadfasdfasdfasdfasdf");
		}

		try {
			con = createConnection();
			pStatement = con.prepareStatement(query);
			if (s != null) {
				pStatement.setString(1, s.getSpnr());
			} else if (c != null) {
				pStatement.setString(1, c.getCcode());
			}

			return pStatement.executeUpdate();

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}
}
