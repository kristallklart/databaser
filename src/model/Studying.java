package model;

public class Studying {

	private String spnr;
	private String ccode;
	private String semester;

	public Studying() {
	}

	public Studying(String ccode, String semester) {
		this.ccode = ccode;
		this.semester = semester;
	}

	public Studying(String sPnr, String cCode, String semester) {
		this.spnr = sPnr;
		this.ccode = cCode;
		this.semester = semester;
	}

	public String getsPnr() {
		return spnr;
	}

	public void setsPnr(String sPnr) {
		this.spnr = sPnr;
	}

	public String getcCode() {
		return ccode;
	}

	public void setcCode(String cCode) {
		this.ccode = cCode;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

}
