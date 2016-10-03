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

public class DataAccessLayerLu {

	private LoginDataLu login = new LoginDataLu();
	private QueriesLu queriesLu = new QueriesLu();
	private UtilDatabaseAccess utilDatabaseAccess = new UtilDatabaseAccess();
	private Connection con = null;
	private PreparedStatement pStatement = null;
	private ResultSet rSet = null;
	private ResultSetMetaData rSetMeta = null;

	public Connection createConnection() throws SQLException {
		return DriverManager.getConnection(login.getUrl(), login.getUser(), login.getPw());
	}

	public Student getStudent(String spnr) throws SQLException, NotFoundException {

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.getStudent());
			pStatement.setString(1, spnr);
			rSet = pStatement.executeQuery();

			if (!rSet.isBeforeFirst()) {
				throw new NotFoundException("No student found");
			}

			Student s = new Student();
			while (rSet.next()) {
				s.setSpnr(rSet.getString("spnr"));
				s.setSname(rSet.getString("sname"));
				s.setSaddress(rSet.getString("sadress"));
			}

			return s;

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public Course getCourse(String ccode) throws SQLException, NotFoundException {

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.getCourse());
			pStatement.setString(1, ccode);
			rSet = pStatement.executeQuery();

			if (!rSet.isBeforeFirst()) {
				throw new NotFoundException("No course found");
			}

			Course c = new Course();
			while (rSet.next()) {
				c.setCcode(rSet.getString("ccode"));
				c.setCname(rSet.getString("cname"));
				c.setCpoint(rSet.getInt("points"));
			}

			return c;

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public void deleteStudent(String spnr) throws SQLException, NotFoundException {

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

	public void deleteCourse(String ccode) throws SQLException, NotFoundException {

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.deleteCourse());
			pStatement.setString(1, ccode);
			int i = pStatement.executeUpdate();
			if (i < 1) {
				throw new NotFoundException("Failed to delete course");
			}

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}

	}

	public void addStudent(Student s) throws SQLException {

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

	public void addCourse(Course c) throws SQLException {

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.addCourse());
			pStatement.setString(1, c.getCcode());
			pStatement.setString(2, c.getCname());
			pStatement.setInt(3, c.getCpoint());
			pStatement.executeUpdate();

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public String acedIt(String ccode) throws SQLException {
		String percent = null;

		try {
			con = createConnection();
			pStatement = con.prepareStatement(queriesLu.acedIt());
			pStatement.setString(1, ccode);
			pStatement.setString(2, ccode);
			rSet = pStatement.executeQuery();

			while (rSet.next()) {
				percent = rSet.getString(1);
			}

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
		return percent;
	}

	public void registerGrade(Studied s) throws SQLException {

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

	public DefaultTableModel getTable(ArrayList<String> values, String queryName)
			throws SQLException, NotFoundException {
		Vector<Vector<Object>> sendData = new Vector<Vector<Object>>();
		Vector<String> sendColumnNames = new Vector<String>();

		String query = queriesLu.getTableQuery(queryName);

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
				throw new NotFoundException(queryName);
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

			DefaultTableModel model = new DefaultTableModel(sendData, sendColumnNames) {
				private static final long serialVersionUID = 2107598765076969503L;

				@Override
				public boolean isCellEditable(int row, int column) {

					return false;
				}

			};

			return model;

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public DefaultTableModel updateTable(ArrayList<String> values, String tableName) throws SQLException {
		Vector<Vector<Object>> sendData = new Vector<Vector<Object>>();
		Vector<String> sendColumnNames = new Vector<String>();

		String query = queriesLu.getTableQuery(tableName);

		try {
			con = createConnection();
			pStatement = con.prepareStatement(query);

			int x = 0;
			while (x < values.size()) {
				pStatement.setString(x + 1, values.get(x));
				x++;
			}
			rSet = pStatement.executeQuery();
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

			DefaultTableModel model = new DefaultTableModel(sendData, sendColumnNames) {
				private static final long serialVersionUID = 3735113673457707626L;

				@Override
				public boolean isCellEditable(int row, int column) {

					return false;
				}

			};

			return model;

		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}
	}

	public boolean studentExist(String spnr) throws SQLException {
		String query = queriesLu.getStudent();

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
		String query = queriesLu.currentPoints();
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
}
