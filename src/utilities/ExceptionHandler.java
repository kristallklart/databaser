package utilities;

import java.io.IOException;
import java.sql.SQLException;

public class ExceptionHandler {

	public String handleException(Exception ex) {
		String message = null;

		if (ex instanceof NotFoundException) {
			message = getCause(((NotFoundException) ex).getError());
		} else if (ex instanceof NullPointerException) {
			message = "Error trying to access something that doesn't exist.";
		} else if (ex instanceof IndexOutOfBoundsException) {
			message = "Something went wrong, if you're trying to register a student, course or grade please make sure you've made a selection from a list";
		} else if (ex instanceof NumberFormatException) {
			message = "Something went wrong, if you're trying to add a course make sure you use numbers to enter the course points.";
		} else if (ex instanceof IOException) {
			message = "A input/output problem occured.";
		} else if (ex instanceof IllegalArgumentException) {
			message = "The file could not be found.";

		} else if (ex instanceof SQLException) {
			int errorCode = ((SQLException) ex).getErrorCode();

			switch (errorCode) {
			case 2627: // Unique key violation
				message = "The student or course you tried to add aldready exists.";
				break;
			case 547:
				message = "Could not delete course because there are still students tied to that course.";
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

	private String getCause(String error) {
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
		case "Failed to delete course":
			message = "The course you tried to delete doesn't exist";
			break;
		case "No course found":
			message = "Could not find the course you were searching for.";
			break;
		case "notFinished":
			message = "There are no students who hasn't finished the course you searched for.";
			break;
		case "getCourseResult":
			message = "No students had finished the course you searched for.";
			break;
		case "allCourses":
			message = "There are no courses registered";
			break;
		case "mostThrough":
			message = "asdfasdfasdf";
		}
		return message;
	}
}
