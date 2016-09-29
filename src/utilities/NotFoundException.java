package utilities;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {
	private String error = null;

	public NotFoundException() {
	}

	public NotFoundException(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
}
