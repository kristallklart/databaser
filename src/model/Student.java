package model;

import java.util.ArrayList;

public class Student {

	private String spnr;
	private String name;
	private String address;
	private ArrayList<Studied> studiedList = new ArrayList<Studied>();
	private ArrayList<Studying> studyingList = new ArrayList<Studying>();

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

	public ArrayList<Studied> getStudiedList() {
		return studiedList;
	}

	public void setStudiedList(ArrayList<Studied> studiedList) {
		this.studiedList = studiedList;
	}

	public ArrayList<Studying> getStudyingList() {
		return studyingList;
	}

	public void setStudyingList(ArrayList<Studying> studyingList) {
		this.studyingList = studyingList;
	}

	public void addStudying(Studying s) {
		studyingList.add(s);
	}

	public void addStudied(Studied s) {
		studiedList.add(s);
	}
}
