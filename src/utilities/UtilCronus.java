package utilities;

import java.util.Vector;

public class UtilCronus {

	public String getQuery(int selectedIndex) {
		String sqlQuery = null;

		switch (selectedIndex) {
		case 1:
			sqlQuery = getEmployee();
			break;

		case 2:
			sqlQuery = getEmployeeAbsence();
			break;

		case 3:
			sqlQuery = getEmployeePortalSetup();
			break;

		case 4:
			sqlQuery = getEmployeeQualification();
			break;

		case 5:
			sqlQuery = getEmployeeRelative();
			break;

		case 6:
			sqlQuery = getEmployeeStatisticsGroup();
			break;

		case 7:
			sqlQuery = getMetaDataEmployee();
			break;

		case 8:
			sqlQuery = getMetaDataEmployeeAbsence();
			break;

		case 9:
			sqlQuery = getMetaDataEmployeePortalSetup();
			break;

		case 10:
			sqlQuery = getMetaDataEmployeeQualification();
			break;

		case 11:
			sqlQuery = getMetaDataEmployeeRelative();
			break;

		case 12:
			sqlQuery = getMetaDataEmployeeStatisticsGroup();
			break;

		case 13:
			sqlQuery = getKeys();
			break;

		case 14:
			sqlQuery = getIndex();
			break;

		case 15:
			sqlQuery = getTableConstrains();
			break;

		case 16:
			sqlQuery = getAllTablesUsingInformationSchema();
			break;

		case 17:
			sqlQuery = getAllTablesUsingSysObjects();
			break;

		case 18:
			sqlQuery = getEmployeeColumns1();
			break;

		case 19:
			sqlQuery = getEmployeeColumns2();
			break;

		case 20:
			sqlQuery = getMostRows();
			break;

		default:
			break;
		}
		return sqlQuery;
	}

	public Vector<String> getCronusQueryNames() {
		Vector<String> cronusQueryNames = new Vector<String>();

		cronusQueryNames.add(" ");
		cronusQueryNames.add("Employee");
		cronusQueryNames.add("Employee Absence");
		cronusQueryNames.add("Employee Portal Setup");
		cronusQueryNames.add("Employee Qualification");
		cronusQueryNames.add("Employee Relative");
		cronusQueryNames.add("Employee Statistics Group");
		cronusQueryNames.add("Metadata Employee");
		cronusQueryNames.add("Metadata Employee Absence");
		cronusQueryNames.add("Metadata Employee Portal Setup");
		cronusQueryNames.add("Metadata Employee Relative");
		cronusQueryNames.add("Metadata Employee Statistics Group");
		cronusQueryNames.add("Keys");
		cronusQueryNames.add("Indexes");
		cronusQueryNames.add("Table Constrains");
		cronusQueryNames.add("All tables 1");
		cronusQueryNames.add("All tables 2");
		cronusQueryNames.add("Employee columns 1");
		cronusQueryNames.add("Employee columns 2");
		cronusQueryNames.add("Most rows in database");

		return cronusQueryNames;
	}

	// hämtar 5 columner från EMPLOYEE
	public String getEmployee() {
		return "select [First Name], [Last Name], Initials, [Job Title], Address from [CRONUS Sverige AB$Employee]";
	}

	// hämtar 5 columner från EMPLOYEE ABSENCE
	public String getEmployeeAbsence() {
		return "select [Entry No_], [Employee No_], [Cause of Absence Code], Description, Quantity"
				+ " from [CRONUS Sverige AB$Employee Absence]";
	}

	// hämtar 5 columner från EMPLOYEE PORTAL SETUP
	public String getEmployeePortalSetup() {
		return "select [Config TP WP Request Capt ID], [Config TP Initial Req_ Capt ID], [Config TP Group Capt ID], [Search Tool Pane Caption ID], [Search Config Table ID]"
				+ " from [CRONUS Sverige AB$Employee Portal Setup]";
	}

	// hämtar 5 columner från EMPLOYEE QUALIFICATION
	public String getEmployeeQualification() {
		return "select [Employee No_], [Qualification Code], Description, Institution_Company,Type"
				+ " from [CRONUS Sverige AB$Employee Qualification]";
	}

	// hämtar 5 columner från EMPLOYEE RELATIVE
	public String getEmployeeRelative() {
		return "select [Employee No_], [First Name], [Last Name], [Relative Code]"
				+ " from [CRONUS Sverige AB$Employee Relative]";
	}

	// hämtar 5 columner från EMPLOYEE STATISTICS GROUP
	public String getEmployeeStatisticsGroup() {
		return "select [timestamp], [Code], [Description]" + "from [CRONUS Sverige AB$Employee Statistics Group]";
	}

	public String getMetaDataEmployee() {
		return "select top 5 TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee'";
	}

	public String getMetaDataEmployeeAbsence() {
		return "select top 5 TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Absence'";
	}

	public String getMetaDataEmployeePortalSetup() {
		return "select top 10 TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Portal Setup'";
	}

	public String getMetaDataEmployeeQualification() {
		return "select top 10 TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Qualification'";
	}

	public String getMetaDataEmployeeRelative() {
		return "select top 10 TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Relative'";
	}

	public String getMetaDataEmployeeStatisticsGroup() {
		return "select top 10 TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Statistics Group'";
	}

	// hämtar både pk & fk
	public String getKeys() {
		return "select top 50 table_name as [Table Name], column_name as 'Key'"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.KEY_COLUMN_USAGE";
	}

	// hämtar 5 columner från Index
	public String getIndex() {
		return "select top 10 object_id, name, index_id, type, type_desc" + " from sys.indexes order by object_id ";
	}

	// hämtar 3 columner fron Table Constrains
	public String getTableConstrains() {
		return "select top 10 CONSTRAINT_CATALOG, CONSTRAINT_NAME, TABLE_CATALOG"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.TABLE_CONSTRAINTS" + " order by TABLE_NAME";
	}

	// hämtar alla tables från databasen
	public String getAllTablesUsingInformationSchema() {
		return "select TABLE_NAME as [All tables in CROUNS Sverige AB] from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.TABLES";
	}

	public String getAllTablesUsingSysObjects() {
		return "use [Demo Database NAV (5-0)] select name as [All tables in CROUNS Sverige AB] from sysobjects where xtype ='U'";
	}

	// samtliga kolumner från Employee
	public String getEmployeeColumns1() {
		return "select name as [Employee Columns] from sys.columns "
				+ "where object_id=object_ID('CRONUS Sverige AB$Employee')";
	}

	public String getEmployeeColumns2() {
		return "select COLUMN_NAME as [Employee Columns] from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee'";
	}

	// tabell som innehåller flest rader
	public String getMostRows() {
		return "select top 1 object_name(object_id)as Tabellnamn," + " st.row_count as [Antal Rader]"
				+ " from  sys.dm_db_partition_stats st order by st.row_count desc";
	}

}
