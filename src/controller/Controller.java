package controller;

import java.sql.SQLException;

import dal.DataAccessLayer;
import model.Student;

public class Controller {

	public static Student getStudent(String spnr) throws SQLException {

		return DataAccessLayer.getStudent(spnr);

	}
}
