package utilities;

import java.io.IOException;
import java.sql.SQLException;

public class ExceptionHandler {

	public String handleException(Exception ex) {
		String message = null;

		if (ex instanceof NotFoundException) {
			message = whatsUp(((NotFoundException) ex).getError());
		} else if (ex instanceof NullPointerException) {
			message = "Nullpointer.";
		} else if (ex instanceof IndexOutOfBoundsException) {
			message = "Something went wrong, if you're trying to register a student, course or grade please make sure you've made a selection from a list";
		} else if (ex instanceof NumberFormatException) {
			message = "Number format exception.";
		} else if (ex instanceof IOException) {
			message = "Some fucking IOException happened!.";
		} else if (ex instanceof IllegalArgumentException) {
			message = "Some fucking IllegalArgumentException happened!";
		} else if (ex instanceof SQLException) {
			int errorCode = ((SQLException) ex).getErrorCode();

			switch (errorCode) {
			case 2627: // Unique key violation
				message = "The student or course you tried to add aldready exists.";
				break;
			case 547:
				message = "Could not add course because the value of course points was too high, maximum is 30.";
				break;
			case 17: // No connection
				message = "Connection to database failed, please try again. If the error persists contact support.";
				break;
			case 0: // Login time expired
				message = "Login to the database timed out, please check your connection and try again.";
				break;
			default:
				message = "An unknown error occured, please contact support.";
				break;
			}
		} else {
			message = "An unknown exception occured, please contact support.";
		}
		return message;
	}

	private String whatsUp(String error) {
		String message = null;

		switch (error) {
		case "table_stud_regOnCourse_courseList":
			message = "Could not find the student you were searching for.";
			break;

		case "table_stud_foundStud":
			message = "Could not find the student you were searching for.";
			break;

		case "table_stud_currentCourses":
			message = "The student you searched for is currently not studying any courses.";
			break;

		case "table_stud_finishedCourses":
			message = "The student you searched for has not finished any courses.";
			break;
		case "No student found":
			message = "Could not find the student you were searching for.";
			break;
		case "Failed to delete student":
			message = "The student you tried to delete doesn't exist";
			break;
		case "No course found":
			message = "The course you tried to delete doesn't exist";
			break;
		}
		return message;
	}
}
