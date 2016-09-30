package utilities;

public class FeedbackHandler {

	public String studentFound() {
		return "Successfully found the student you searched for.";
	}

	public String courseFound() {
		return "Successfully found the course you searched for.";
	}

	public String noInputPnr() {
		return "Please type a personal number to search for.";
	}

	public String noInputCcode() {
		return "Please type a course code to search for.";
	}

	public String insufficientInput() {
		return "Please fill in all the fields, and make necessary selection.";
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

	public String noStudied(String input) {
		String message = "The student with personal number: " + input + " has not finished any courses.";
		return message;
	}

	public String noStudiedOrStudying(String input) {
		String message = "The student with personal number: " + input + " neither studies or has studied any courses";
		return message;
	}

	public String studentAdded(String input) {
		String message = "Successfully added student with personal number: " + " " + input;
		return message;
	}

	public String studentDeleted(String input) {
		String message = "Successfully deleted student with personal number: " + " " + input;
		return message;
	}

	public String courseAdded(String input) {
		String message = "Successfully added course with course code: " + " " + input;
		return message;
	}

	public String noPnrDelete() {
		return "Please fill in a valid personal number for the student you want to delete.";
	}

	public String noStudentToDelete(String input) {
		String message = "Could not delete student with personal number: " + input + ", student doesn't exist.";
		return message;
	}

	public String availableCoursesFound(String input) {
		String message = "Found available courses for student with personal number: " + input;
		return message;
	}

	public String pointsExceeded(String input, int points) {
		String message = "The student with personal number: " + input + " already studies courses worth: " + points
				+ " maximum allowed is 45";
		return message;
	}

	public String studentRegCourse(String spnr, String ccode) {
		String message = "Student with personal number: " + spnr + " successfully added to course: " + ccode;
		return message;
	}

	public String registeredGrade(String spnr, String grade, String ccode) {
		String message = "Successfully registered grade: " + grade + " on course: " + ccode
				+ " for student with personal number: " + spnr;
		return message;
	}

	public String studentRemovedStudying(String spnr, String ccode) {
		String message = "Successfully removed student with personal number: " + spnr + " from course with ccode: "
				+ ccode;
		return message;
	}

	public String notFullChoiceCronusAccess() {
		String message = "Please choose an option in the drop-down menu.";
		return message;
	}

	public String notFullChoiceCronusFile() {
		String message = "Please choose an option in both drop-down menus.";
		return message;
	}

}
