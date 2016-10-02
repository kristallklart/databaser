package model;

public class Studied {

	private String semester;
	private String spnr;
	private String ccode;
	private String grade;

	public Studied() {
	}

	public Studied(String spnr, String semester, String grade) {
		this.semester = semester;
		this.spnr = spnr;
		this.grade = grade;
	}

	public Studied(String spnr, String semester, String grade, String ccode) {
		this.spnr = spnr;
		this.grade = grade;
		this.semester = semester;
		this.ccode = ccode;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
