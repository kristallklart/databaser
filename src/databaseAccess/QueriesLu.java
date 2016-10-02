package databaseAccess;

public class QueriesLu {

	public String getTableQuery(String tableName) {
		String sqlQuery = null;

		switch (tableName) {
		case "table_stud_regOnCourse_courseList":
			sqlQuery = allPossibleCourses();
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

	public String getStudent() {
		return "select * from student where spnr = ?";
	}

	public String getCourse() {
		return "select * from course where ccode = ?";
	}

	public String getCcodes() {
		return "select ccode from course ";
	}

	public String getStudentStudying() {
		return "select ccode, semester from studies where spnr = ?";
	}

	public String courseResult() {
		return "select spnr,semester, grade from studied where ccode = ? order by semester asc";
	}

	public String getStudentStudied() {
		return "select semester, ccode, grade from studied where spnr = ?";
	}

	public String notFinished() {
		return "select spnr, semester from studies where ccode = ?";
	}

	public String AcedIt() {
		return "select count (grade)*100 /(select count (*) from studied where ccode =?) from studied group by ccode, grade having ccode = ? and grade='a'";
	}

	public String studentResult() {
		return "select grade from studied where ccode = ? and spnr = ?";
	}

	public String mostThrough() {
		return "select top 5 ccode, count(*)Totalt from studied where grade != 'U' group by ccode order by totalt desc";
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

	public String addStudent() {
		return "insert into student values (?,?,?)";
	}

	public String addCourse() {
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

	public String allPossibleCourses() {
		return "select ccode as 'Course Code', cname as 'Course Name', points as 'Points' from course where ccode not in (select ccode from studied where spnr = ? and grade != 'U') and ccode not in (select ccode from studies where spnr = ?)";
	}

	public String createStudent() {
		return "insert into student values (?,?,?)";
	}

	public String createCourse() {
		return "insert into course values (?,?,?)";
	}

	public String currentPoints() {
		return "select points from studies as s inner join course as c on c.ccode = s.ccode where spnr = ?";
	}

}