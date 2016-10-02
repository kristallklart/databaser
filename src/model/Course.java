package model;

public class Course {

	private String ccode;
	private String cname;
	private int cpoint;
	private int total;

	public Course() {
	}

	public Course(String ccode, String cname, int points) {
		this.ccode = ccode;
		this.cname = cname;
		this.cpoint = points;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String cCode) {
		this.ccode = cCode;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cName) {
		this.cname = cName;
	}

	public int getCpoint() {
		return cpoint;
	}

	public void setCpoint(int cpoint) {
		this.cpoint = cpoint;
	}

}
