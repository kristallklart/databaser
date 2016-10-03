package databaseAccess;

public class QueriesLu {

	public String getTableQuery(String queryName) {
		String sqlQuery = null;

		switch (queryName) {
		case "table_stud_regOnCourse_courseList":
			sqlQuery = allPossibleCourses();
			break;
		case "table_stud_foundStud":
			sqlQuery = getStudentToTable();
			break;
		case "table_stud_currentCourses":
			sqlQuery = getStudentStudying();
			break;
		case "table_stud_finishedCourses":
			sqlQuery = getStudentStudied();
			break;
		case "notFinished":
			sqlQuery = notFinished();
			break;
		case "getCourseResult":
			sqlQuery = courseResult();
			break;
		case "allCourses":
			sqlQuery = allCourses();
			break;
		case "mostThrough":
			sqlQuery = mostThrough();
			break;
		}
		return sqlQuery;
	}

	public String getStudent() {
		return "select * from student where spnr = ?";
	}

	public String getStudentToTable() {
		return "select spnr as 'Personal Number', upper(sname) as 'Name', upper(sadress) as 'Address' from student where spnr = ?";
	}

	public String getCourse() {
		return "select * from course where ccode = ?";
	}

	public String getStudentStudying() {
		return "select upper(ccode) as 'Course Code', upper(semester) as 'Semester' from studies where spnr = ?";
	}

	public String courseResult() {
		return "select spnr as 'Personal Number', upper(semester) as 'Semester', upper(grade) as 'Grade' from studied where ccode = ? order by semester asc";
	}

	public String getStudentStudied() {
		return "select upper(semester) as 'Semester', upper(ccode) as 'Course Code', upper(grade) as 'Grade' from studied where spnr = ?";
	}

	public String notFinished() {
		return "select spnr as 'Personal Number', upper(semester) as 'Semester' from studies where ccode = ?";
	}

	public String acedIt() {
		return "select count (grade)*100 /(select count (*) from studied where ccode =?) from studied group by ccode, grade having ccode = ? and grade='a'";
	}

	public String mostThrough() {
		return "select upper(ccode) as 'Course Code', (sum(case when grade !='U' then 1 else 0 end)*100) / count (ccode) as 'Percent Passed' from studied group by ccode order by 'Percent Passed' desc";

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
		return "select upper(ccode) as 'Course Code', upper(rtrim(cname)) as 'Course Name', points as 'Points' from course";
	}

	public String allPossibleCourses() {
		return "select upper(ccode) as 'Course Code', upper(cname) as 'Course Name', points as 'Points' from course where ccode not in (select ccode from studied where spnr = ? and grade != 'U') and ccode not in (select ccode from studies where spnr = ?)";
	}

	public String currentPoints() {
		return "select points from studies as s inner join course as c on c.ccode = s.ccode where spnr = ?";
	}

}