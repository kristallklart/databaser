package model;

import java.util.ArrayList;

public class Student {
	private String spnr;
	private String sname;
	private String saddress;
	private String spcode;
	private String scity;
	private String scountry;
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

	public String getSpcode() {
		return spcode;
	}

	public void setsPcode(String sPCode) {
		this.spcode = sPCode;
	}

	public String getScity() {
		return scity;
	}

	public void setScity(String sCity) {
		this.scity = sCity;
	}

	public String getScountry() {
		return scountry;
	}

	public void setScountry(String sCountry) {
		this.scountry = sCountry;
	}

}
