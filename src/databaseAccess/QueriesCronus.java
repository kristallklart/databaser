package databaseAccess;

import java.util.ArrayList;

public class QueriesCronus {

	ArrayList<String> queriesCronusEmployee = new ArrayList<String>();
	ArrayList<String> queriesCronusMetaData = new ArrayList<String>();

	public QueriesCronus() {

		queriesCronusEmployee.add("NOT IN USE BECAUSE OF INDEXING");
		queriesCronusEmployee
				.add("select [First Name], [Last Name], [Job Title], City from [CRONUS Sverige AB$Employee]");
		queriesCronusEmployee
				.add("select top 20 [Entry No_], [Employee No_], [Cause of Absence Code], Description, Quantity"
						+ " from [CRONUS Sverige AB$Employee Absence]");
		queriesCronusEmployee
				.add("select [Config TP WP Request Capt ID], [Config TP Initial Req_ Capt ID], [Config TP Group Capt ID], [Search Tool Pane Caption ID], [Search Config Table ID]"
						+ " from [CRONUS Sverige AB$Employee Portal Setup]");
		queriesCronusEmployee.add("select [Employee No_], [Qualification Code], Description, Institution_Company,Type"
				+ " from [CRONUS Sverige AB$Employee Qualification]");
		queriesCronusEmployee.add("select [Employee No_], [First Name], [Last Name], [Relative Code]"
				+ " from [CRONUS Sverige AB$Employee Relative]");
		queriesCronusEmployee.add(
				"select [timestamp], [Code], [Description]" + "from [CRONUS Sverige AB$Employee Statistics Group]");

		queriesCronusMetaData.add("NOT IN USE BECAUSE OF INDEXING");
		queriesCronusMetaData.add("select top 20 TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee'");
		queriesCronusMetaData.add("select TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Absence'");
		queriesCronusMetaData.add("select top 20 TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Portal Setup'");
		queriesCronusMetaData.add("select TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Qualification'");
		queriesCronusMetaData.add("select TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Relative'");
		queriesCronusMetaData.add("select TABLE_CATALOG, TABLE_SCHEMA, DATA_TYPE, ORDINAL_POSITION"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
				+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee Statistics Group'");
		queriesCronusMetaData.add("select top 20 table_name as [Table Name], column_name as [Key]"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.KEY_COLUMN_USAGE");
		queriesCronusMetaData.add(
				"select top 20 object_id, name, index_id, type, type_desc" + " from sys.indexes order by object_id");
		queriesCronusMetaData.add("select top 20 CONSTRAINT_CATALOG, CONSTRAINT_NAME, TABLE_CATALOG"
				+ " from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.TABLE_CONSTRAINTS" + " order by TABLE_NAME");
		queriesCronusMetaData.add(
				"select TABLE_NAME as [All tables in CROUNS Sverige AB] from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.TABLES");
		queriesCronusMetaData.add(
				"use [Demo Database NAV (5-0)] select name as [All tables in CROUNS Sverige AB] from sysobjects where xtype ='U'");
		queriesCronusMetaData
				.add("select COLUMN_NAME as [Employee Columns] from [Demo Database NAV (5-0)].INFORMATION_SCHEMA.COLUMNS"
						+ " where TABLE_NAME = 'CRONUS Sverige AB$Employee'");
		queriesCronusMetaData.add("select name as [Employee Columns] from sys.columns "
				+ "where object_id=object_ID('CRONUS Sverige AB$Employee')");
		queriesCronusMetaData.add("select top 1 object_name(object_id)as [Table name]," + " st.row_count as [Rows]"
				+ " from  sys.dm_db_partition_stats st order by st.row_count desc");

	}

	public ArrayList<String> getQueriesCronusEmployee() {
		return queriesCronusEmployee;
	}

	public void setQueriesCronusEmployee(ArrayList<String> queriesCronusEmployee) {
		this.queriesCronusEmployee = queriesCronusEmployee;
	}

	public ArrayList<String> getQueriesCronusMetaData() {
		return queriesCronusMetaData;
	}

	public void setQueriesCronusMetaData(ArrayList<String> queriesCronusMetaData) {
		this.queriesCronusMetaData = queriesCronusMetaData;
	}

}
