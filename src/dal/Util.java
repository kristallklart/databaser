package dal;

public class Util {

	public static String getStudent() {
		return "select * from student where spnr = ?";
	}

	// allt från en viss kurs
	public static String getCourse() {
		return "select * from course where ccode = ?";
	}

<<<<<<< HEAD
	public static String getStudying() {
		return "select ccode, semester from studying where spnr = ?";
=======
	public static String getStudentStudying() {
		return "select ccode, semester from studies where spnr = ?";
>>>>>>> branch 'master' of https://github.com/paulssonkalle/databaser.git
	}

	// Visa resultat för angiven kurs (alla studenter som tagit kursen och deras
	// betyg)
	public static String courseResult() {
		return "select spnr, grade from studied where ccode = ?";
	}

	// studenter som inte är klara med en viss kurs - fråga erre
	public static String notFinished() {
		return "select spnr, sname from studying where ccode = ?";
	}

	// procent studenter som fått a på en särskild kurs
	public static String AcedIt() {
		return "select count (grade)*100 /(select count (*) from studied where ccode =?) from studied group by ccode, grade having ccode = ? and grade='a'";
	}

	// resultat för angiven student på en viss kurs
	public static String studentResult() {
		return "select grade from studied where ccode = ? and spnr = ?";
	}

	// högst genomströmmning
	public static String mostThrough() {
		return "select ccode, count(grade) from studied group by ccode,grade having grade !='u'";
	}

}
