package model;

public class Studying {

	private String sPnr;
	private String cCode;
	private String semester;

	public Studying(String ccode, String semester) {

		this.cCode = ccode;
		this.semester = semester;

	}

	public Studying() {

	}

	public String getsPnr() {
		return sPnr;
	}

	public void setsPnr(String sPnr) {
		this.sPnr = sPnr;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

}
