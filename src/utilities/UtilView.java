package utilities;

import java.util.ArrayList;

import javax.swing.JTextField;

public class UtilView {
	public static ArrayList<JTextField> clearFields(ArrayList<JTextField> fields) {
		for (JTextField field : fields) {
			field.setText("");
		}
		return fields;
	}
}
