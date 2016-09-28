package utilities;

public class FeedbackHandler {

	public String studentFound() {
		return "Successfully found the student you searched for.";
	}

	public String noInput() {
		return "Please type something to search for.";
	}

	public String insufficientInput() {
		return "Please fill in all the fields";
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

	public String courseAdded(String input) {
		String message = "Successfully added course with course code: " + " " + input;
		return message;
	}
}
