package model;

public class Student {

	private String spnr;
	private String name;
	private String address;

	public Student() {
	}

	public Student(String spnr) {
		this.spnr = spnr;
	}

	public Student(String spnr, String name, String address) {
		this.spnr = spnr;
		this.name = name;
		this.address = address;
	}

	public String getSpnr() {
		return spnr;
	}

	public void setSpnr(String sPnr) {
		this.spnr = sPnr;
	}

	public String getSname() {
		return name;
	}

	public void setSname(String sName) {
		this.name = sName;
	}

	public String getSaddress() {
		return address;
	}

	public void setSaddress(String sAddress) {
		this.address = sAddress;
	}

}
