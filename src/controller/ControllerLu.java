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

	public ArrayList<String> getCcodes() throws SQLException {
		return dal.getCcodes();
	}

	public Course getCourse(String ccode) throws SQLException {
		return dal.getCourse(ccode);
	}

	public ArrayList<Studying> getStudentStudying(String spnr) throws SQLException {
		return dal.getStudentStudying(spnr);
	}

	public ArrayList<Studied> getStudentStudied(String spnr) throws SQLException {
		return dal.getStudentStudied(spnr);
	}

	public void deleteCourse(String ccode) throws SQLException {
		dal.deleteCourse(ccode);
	}

	public void deleteStudent(String spnr) throws SQLException, NotFoundException {
		dal.deleteStudent(spnr);
	}

	public void addStudent(Student s) throws SQLException {
		dal.addStudent(s);
	}

	public boolean createCourse(String ccode, String cname, String cpoint) {
		return dal.createCourse(ccode, cname, cpoint);
	}

	public ArrayList<Studied> getCourseResult(String ccode) throws SQLException {
		return dal.getCourseResult(ccode);
	}

	public String acedIt(String ccode) throws SQLException {
		return dal.acedIt(ccode);
	}

	public boolean registerGrade(String semester, String sPnr, String cCode, String grade) {
		return dal.registerGrade(semester, sPnr, cCode, grade);
	}

	public void registerOnCourse(Studying s) throws SQLException {
		dal.registerOnCourse(s);
	}

	public void deleteStudying(String spnr, String ccode) {
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

	public DefaultTableModel getTableAll(ArrayList<String> values, String tableName)
			throws SQLException, NotFoundException {
		return dal.getTableAll(values, tableName);
	}

	public void createAll(ArrayList<Object> values, String studentOrCourse) throws SQLException {
		dal.createAll(values, studentOrCourse);
	}

	public int deleteAll(Object object) throws SQLException, NotFoundException {
		return dal.deleteAll(object);
	}

	public boolean studentExist(String spnr) throws SQLException {
		return dal.studentExist(spnr);
	}

	public int currentPoints(String spnr) throws SQLException, NotFoundException {
		return dal.currentPoints(spnr);
	}
}
