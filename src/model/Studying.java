package model;

public class Studying {

	private String sPnr;
	private String cCode;
	private String semester;

	public Studying() {
	}

	public Studying(String ccode, String semester) {
		this.cCode = ccode;
		this.semester = semester;
	}

	public Studying(String sPnr, String cCode, String semester) {
		this.sPnr = sPnr;
		this.cCode = cCode;
		this.semester = semester;
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
