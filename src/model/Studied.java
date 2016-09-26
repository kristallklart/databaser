package model;

public class Studied {

	private String semester;
	private String sPnr;
	private String cCode;
	private String grade;

	public Studied(String semester, String cCode, String grade) {

		this.semester = semester;
		this.cCode = cCode;
		this.grade = grade;

	}

	public Studied() {

	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
