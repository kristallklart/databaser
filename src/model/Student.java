package model;

import java.util.ArrayList;

public class Student {

	private String spnr;
	private String sname;
	private String saddress;
	ArrayList<Studied> studiedList;

	public ArrayList<Studied> getStudiedList() {
		return studiedList;
	}

	public void setStudiedList(ArrayList<Studied> studiedList) {
		this.studiedList = studiedList;
	}

	public String getSpnr() {
		return spnr;
	}

	public void setSpnr(String sPnr) {
		this.spnr = sPnr;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sName) {
		this.sname = sName;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String sAddress) {
		this.saddress = sAddress;
	}

}
