package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dal.DataAccessLayer;
import model.Course;
import model.Student;
import model.Studied;
import model.Studying;

public class Controller {

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

	public ArrayList<Studying> getStudentStudying(String pnr) throws SQLException {
		return dal.getStudentStudying(pnr);
	}

	public ArrayList<Studied> getStudentStudied(String pnr) throws SQLException {
		return dal.getStudentStudied(pnr);
	}

	public void deleteCourse(String ccode) throws SQLException {
		dal.deleteCourse(ccode);
	}

	public void deleteStudent(String spnr) throws SQLException {
		dal.deleteStudent(spnr);
	}
}