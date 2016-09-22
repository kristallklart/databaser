package controller;

import java.sql.SQLException;

import dal.DataAccessLayer;
import model.Course;
import model.Student;

public class Controller {

	public static Student getStudent(String spnr) throws SQLException {

		return DataAccessLayer.getStudent(spnr);

	}

	public static Course getCourse(String ccode) throws SQLException {

		return DataAccessLayer.getCourse(ccode);

	}

	/*
	 * public static void regStudent (String spnr) throws SQLException {
	 *
	 * }
	 */
}