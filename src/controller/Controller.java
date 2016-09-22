package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dal.DataAccessLayer;
import model.Course;
import model.Student;

public class Controller {

	public static Student getStudent(String spnr) throws SQLException {

		return DataAccessLayer.getStudent(spnr);

	}

	public static ArrayList<Course> getCourses() throws SQLException {
		return DataAccessLayer.getCourses();
	}
}