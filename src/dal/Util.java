package dal;

public class Util {

	public String getStudent() {
		return "select * from student where spnr = ?";
	}

	// allt från en viss kurs
	public String getCourse() {
		return "select * from course where ccode = ?";
	}

	public static String getCcodes() {
		return "select ccode from course ";
	}

	public String getStudentStudying() {
		return "select ccode, semester from studies where spnr = ?";
	}

	// Visa resultat för angiven kurs (alla studenter som tagit kursen och deras
	// betyg)
	public String courseResult() {
		return "select spnr, grade from studied where ccode = ?";
	}

	// studenter som inte är klara med en viss kurs - fråga erre
	public String notFinished() {
		return "select spnr, sname from studying where ccode = ?";
	}

	// procent studenter som fått a på en särskild kurs
	public String AcedIt() {
		return "select count (grade)*100 /(select count (*) from studied where ccode =?) from studied group by ccode, grade having ccode = ? and grade='a'";
	}

	// resultat för angiven student på en viss kurs
	public String studentResult() {
		return "select grade from studied where ccode = ? and spnr = ?";
	}

	// högst genomströmmning
	public String mostThrough() {
		return "select ccode, count(grade) from studied group by ccode,grade having grade !='u'";
	}

}
