package dal;

public class Util {

	public String getStudent() {
		return "select * from student where spnr = ?";
	}

	// allt från en viss kurs
	public String getCourse() {
		return "select * from course where ccode = ?";
	}

	public String getCcodes() {
		return "select ccode from course ";
	}

	public String getStudentStudying() {
		return "select ccode, semester from studies where spnr = ?";
	}

	// Visa resultat för angiven kurs (alla studenter som tagit kursen och deras
	// betyg)
	public String courseResult() {
		return "select spnr,semester,grade from studied where ccode = ? order by semester asc";
	}

	public String getStudentStudied() {
		return "select semester, ccode, grade from studied where spnr = ?";
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

	public String deleteStudent() {
		return "delete from student where spnr=?";
	}

	public String deleteCourse() {
		return "delete from course where ccode =?";
	}

	public String createStudent() {
		return "insert into student values (?,?,?)";
	}

	public String createCourse() {
		return "insert into course values (?,?,?)";
	}

	public String registerGrade() {
		return "insert into studied values (?,?,?,?)";

	}

	public String registerOnCourse() {
		return "insert into studies values (?,?,?)";
	}

}
