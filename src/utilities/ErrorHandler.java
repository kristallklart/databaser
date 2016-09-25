package utilities;

import java.sql.SQLException;

public class ErrorHandler {

	public String handleException(Exception ex) {
		String message;

		if (ex instanceof NullPointerException) {
			message = "Nullpointer.";
		} else if (ex instanceof IndexOutOfBoundsException) {
			message = "Index out of bounds.";
		} else if (ex instanceof NumberFormatException) {
			message = "Number format exception.";
		} else if (ex instanceof SQLException) {
			int errorCode = ((SQLException) ex).getErrorCode();

			switch (errorCode) {
			case 2627: // Unique key violation
				message = "Student or course aldready exists.";
				break;
			case 547: // Foreign key violation
				message = "Student or course does not exist.";
				break;
			case 17: // No connection
				message = "Connection to database failed, please try again. If the error persists contact support.";
				break;
			case 0: // Login time expired
				message = "Login to the database timeout, please check your connection and try again.";
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

	public String noInput() {
		return "Please type something to search for.";
	}

	public String noStudentFound(String input) {
		String message = "Could not find student with personal number: " + input;
		return message;
	}

	public String noCourseFound(String input) {
		String message = "Could not find course with course code: " + input;
		return message;
	}

	public String noStudying(String input) {
		String message = "The student with personal number: " + input + " is not registered on any courses.";
		return message;
	}
}
