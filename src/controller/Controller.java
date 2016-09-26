package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dal.DataAccessLayer;
import model.Course;
import model.Student;
import model.Studying;

public class Controller {

	private DataAccessLayer dal = new DataAccessLayer();

	public Student getStudent(String spnr) throws SQLException {
		return dal.getStudent(spnr);
	}

	public ArrayList<String> getCourses() throws SQLException {
		return dal.getCcodes();
	}

	public Course getCourse(String ccode) throws SQLException {
		return dal.getCourse(ccode);
	}

	public ArrayList<Studying> getStudentStudying(String pnr) throws SQLException {
		return dal.getStudentStudying(pnr);
	}
}