package utilities;

import java.util.ArrayList;

import javax.swing.JTextField;

public class UtilView {
	public static ArrayList<JTextField> clearAllFields(ArrayList<JTextField> fields) {
		for (JTextField field : fields) {
			field.setText("");
		}
		return fields;
	}

	public static ArrayList<JTextField> clearNonSearchFields(ArrayList<JTextField> fields) {
		for (JTextField field : fields) {
			if (!field.isEditable()) {
				field.setText("");
			}
		}
		return fields;
	}

	public static String studentFound(String input) {
		String message = "Student with personal number: " + input + " found.";
		return message;
	}
}
