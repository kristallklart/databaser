package utilities;

public class UtilCronus {

	// samtliga kolumner från Employee
	public String getEmployee() {
		return "select name from sys.columns " + "where object_id=object_ID('CRONUS Sverige AB$Employee')";
	}
	
	public String getEmployee2(){
		return "select * from \"CRONUS Sverige AB$Employee\"";
	}

	// hämtar alla tables från databasen
	public String getTables() {
		return "select table_name " + "from [Demo Database NAV (5-0)].information_schema.tables "
				+ "where table_type = 'base table'";
	}

	// tabell som innehåller flest rader
	public String getMostRows() {
		return "select top 1 object_name(object_id)as Tabellnamn " + ", st.row_count as [Antal Rader] "
				+ "from  sys.dm_db_partition_stats st " + "order by st.row_count desc";
	}

	public String getTableConstrains() {
		return "select * from [Demo Database NAV (5-0)]" + ".information_schema.table_constrains"
				+ "order by table_name";
	}

	public String getIndex() {
		return "select * from sys.indexes order by object_id";
	}

	// hämtar både pk & fk
	public String getKeys() {
		return "select table_name as Tabellnamn, " + "column_name as Nyckelattribut "
				+ "from [Demo Database NAV (5-0)].information_schema.key_column_usage";
	}
		
	//hämtar 5 attribut från EMPLOYEE ABSENCE
	public String getEmployeAbsence(){
		return "select [Entry No_], [Employee No_], [Cause of Absence Code], Description, Quantity"  
				+ "from [CRONUS Sverige AB$Employee Absence]";
	}
	
	//hämtar 5 attribut från EMPLOYEE PORTAL SETUP
	public String getEmployeePortalSetup(){
		return "select [Config TP WP Request Capt ID], [Config TP Initial Req_ Capt ID], [Config TP Group Capt ID], [Search Tool Pane Caption ID], [Search Config Table ID]"
				+"from [CRONUS Sverige AB$Employee Portal Setup]";
	}
	
	//hämtar 5 attribut från EMPLOYEE QUALIFICATION
	public String getEmployeeQualification(){
		return "select [Employee No_], [Qualification Code], Description, Institution_Company,Type"
				+ "from [CRONUS Sverige AB$Employee Qualification]";
	}
	
	//hämtar 5 attribut från EMPLOYEE RELATIVE
	public String getEmployeeRelative(){
		return "select [Employee No_], [First Name], [Last Name], [Relative Code], [Phone No_]"
				+ "from [CRONUS Sverige AB$Employee Relative]";
	}
	
	//hämtar 5 attribut från EMPLOYEE STATISTICS GROUP
	public String getEmployeeStatisticsGroup(){
		return "select [timestamp], [Code], [Description]"
				+"from [CRONUS Sverige AB$Employee Statistics Group]";
	}
}
