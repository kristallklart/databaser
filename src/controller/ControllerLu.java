package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import databaseAccess.DataAccessLayerLu;
import model.Course;
import model.Student;
import model.Studied;
import model.Studying;
import utilities.NotFoundException;

public class ControllerLu {

	private DataAccessLayerLu dal = new DataAccessLayerLu();

	public Student getStudent(String spnr) throws SQLException, NotFoundException {
		return dal.getStudent(spnr);
	}

	public Course getCourse(String ccode) throws SQLException, NotFoundException {
		return dal.getCourse(ccode);
	}

	public void deleteCourse(String ccode) throws SQLException, NotFoundException {
		dal.deleteCourse(ccode);
	}

	public void deleteStudent(String spnr) throws SQLException, NotFoundException {
		dal.deleteStudent(spnr);
	}

	public void addStudent(Student s) throws SQLException {
		dal.addStudent(s);
	}

	public void addCourse(Course c) throws SQLException {
		dal.addCourse(c);
	}

	public ArrayList<Studied> getCourseResult(String ccode) throws SQLException {
		return dal.getCourseResult(ccode);
	}

	public String acedIt(String ccode) throws SQLException {
		return dal.acedIt(ccode);
	}

	public void registerGrade(Studied s) throws SQLException {
		dal.registerGrade(s);
	}

	public void registerOnCourse(Studying s) throws SQLException {
		dal.registerOnCourse(s);
	}

	public void deleteStudying(String spnr, String ccode) throws SQLException {
		dal.deleteStudying(spnr, ccode);
	}

	public ArrayList<Studying> notFinished(String ccode) throws SQLException {
		return dal.notFinished(ccode);
	}

	public ArrayList<Course> allCourses() throws SQLException {
		return dal.allCourses();
	}

	public ArrayList<Course> mostThrough() throws SQLException {
		return dal.mostThrough();
	}

	public DefaultTableModel getTable(ArrayList<String> values, String tableName)
			throws SQLException, NotFoundException {
		return dal.getTable(values, tableName);
	}

	public boolean studentExist(String spnr) throws SQLException {
		return dal.studentExist(spnr);
	}

	public int currentPoints(String spnr) throws SQLException, NotFoundException {
		return dal.currentPoints(spnr);
	}
}
