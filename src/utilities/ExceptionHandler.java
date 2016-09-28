package utilities;

import java.sql.SQLException;

public class ExceptionHandler {

	public String handleException(Exception ex) {
		String message;

		if (ex instanceof NotFoundException) {
			message = ex.getMessage();
		} else if (ex instanceof NullPointerException) {
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
}
