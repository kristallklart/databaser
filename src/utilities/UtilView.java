package utilities;

import java.util.ArrayList;

import javax.swing.JTextField;

public class UtilView {

	private long startTime = 0;

	public ArrayList<JTextField> clearFields(ArrayList<JTextField> fields) {
		for (JTextField field : fields) {
			field.setText("");
		}
		return fields;
	}

	public void startTime() {
		startTime = System.nanoTime();
	}

	public String computeTimeDifference() {
		long currentTime = System.nanoTime();
		long x = currentTime - startTime;
		double milliseconds = x / 1000000.0;
		String time = "Response time: " + String.valueOf(milliseconds) + " ms";
		return time;

	}
}
