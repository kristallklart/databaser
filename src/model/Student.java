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

	public void setSaSddress(String sAddress) {
		this.saddress = sAddress;
	}

	public String getsPCode() {
		return spcode;
	}

	public void setsPCode(String sPCode) {
		this.spcode = sPCode;
	}

	public String getsCity() {
		return scity;
	}

	public void setsCity(String sCity) {
		this.scity = sCity;
	}

	public String getsCountry() {
		return scountry;
	}

	public void setsCountry(String sCountry) {
		this.scountry = sCountry;
	}

}
