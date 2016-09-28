package utilities;

public class UtilLu {
	public String getTableQuery(String tableName) {
		String sqlQuery = null;

		switch (tableName) {
		case "table_stud_regOnCourse_courseList":
			sqlQuery = getStudentStudying();
			break;

		case "table_stud_foundStud":
			sqlQuery = getStudent();
			break;

		case "table_stud_currentCourses":
			sqlQuery = getStudentStudying();
			break;

		case "table_stud_finishedCourses":
			sqlQuery = getStudentStudied();
			break;
		}
		return sqlQuery;
	}

	public String getCreateQuery(String studentOrCourse) {
		String sqlQuery = null;

		if (studentOrCourse.equals("Student")) {
			sqlQuery = createStudent();
		} else if (studentOrCourse.equals("Course")) {
			sqlQuery = createCourse();
		}
		return sqlQuery;
	}

	public String getTextFieldQuery(String studentOrCourse) {
		String sqlQuery = null;

		if (studentOrCourse.equals("Student")) {
			sqlQuery = getStudent();
		} else if (studentOrCourse.equals("Course")) {
			sqlQuery = getCourse();
		}
		return sqlQuery;
	}

	public String getStudent() {
		return "select spnr as 'Personal Number', sname as 'Name', sadress as 'City' from student where spnr = ?";
	}

	// allt från en viss kurs
	public String getCourse() {
		return "select * from course where ccode = ?";
	}

	public String getCcodes() {
		return "select ccode from course ";
	}

	public String getStudentStudying() {
		return "select ccode as 'Course Code', semester as 'Semester' from studies where spnr = ?";
	}

	// Visa resultat för angiven kurs (alla studenter som tagit kursen och deras
	// betyg)
	public String courseResult() {
		return "select spnr,semester,grade from studied where ccode = ? order by semester asc";
	}

	public String getStudentStudied() {
		return "select  ccode as 'Course Code', semester as 'Semester', grade as 'Grade' from studied where spnr = ?";
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

	public String deleteStudying() {
		return "delete from studies where spnr =? and ccode=?";
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

	public String allCourses() {
		return "select * from course";
	}
}
