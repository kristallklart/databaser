package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dal.DataAccessLayer;
import model.Course;
import model.Student;
import model.Studied;
import model.Studying;

public class ControllerLu {

	private DataAccessLayer dal = new DataAccessLayer();

	public Student getStudent(String spnr) throws SQLException {
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

	public Student getStudentAll(String spnr) throws SQLException {
		return dal.getStudentAll(spnr);
	}

	public void deleteCourse(String ccode) throws SQLException {
		dal.deleteCourse(ccode);
	}

	public void deleteStudent(String spnr) throws SQLException {
		dal.deleteStudent(spnr);
	}

	public boolean createStudent(String spnr, String sname, String saddress) {
		return dal.createStudent(spnr, sname, saddress);
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
}
