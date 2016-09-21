package controller;

import dal.DataAccessLayer;
import model.Student;

public class Controller {

	public static Student getStudent(String spnr) {

		return DataAccessLayer.getStudent(spnr);

	}
}
