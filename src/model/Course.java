package model;

public class Course {

	private String cCode;
	private String cName;
	private int cpoint;
	private int total;

	public Course() {

	}

	public Course(String ccode, String cname, int points) {
		this.cCode = ccode;
		this.cName = cname;
		this.cpoint = points;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCcode() {
		return cCode;
	}

	public void setCcode(String cCode) {
		this.cCode = cCode;
	}

	public String getCname() {
		return cName;
	}

	public void setCname(String cName) {
		this.cName = cName;
	}

	public int getCpoint() {
		return cpoint;
	}

	public void setCpoint(int cpoint) {
		this.cpoint = cpoint;
	}

}
