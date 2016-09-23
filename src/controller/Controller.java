package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dal.DataAccessLayer;
import model.Course;
import model.Student;
import model.Studying;

public class Controller {

	public static Student getStudent(String spnr) throws SQLException {

		return DataAccessLayer.getStudent(spnr);

	}

	public static ArrayList<String> getCourses() throws SQLException {
		return DataAccessLayer.getCcodes();
	}

	public static Course getCourse(String ccode) throws SQLException {

		return DataAccessLayer.getCourse(ccode);

	}

	public static ArrayList<Studying> getStudentStudying() throws SQLException {

		return DataAccessLayer.getStudying();
	}
}