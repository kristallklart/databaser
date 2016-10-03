package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import databaseAccess.QueriesCronus;

public class UtilCronus {

	private ArrayList<String> cronusAccessDocFilesToOpen = new ArrayList<String>();
	private ArrayList<String> cronusExcelDocFilesToOpen = new ArrayList<String>();
	private ArrayList<String> cronusManagementStudioDocFilesToOpen = new ArrayList<String>();
	private ArrayList<String> cronusAccessFormFilesToOpen = new ArrayList<String>();
	private ArrayList<String> cronusExcelFormFilesToOpen = new ArrayList<String>();
	private ArrayList<String> cronusWordFormFilesToOpen = new ArrayList<String>();

	private Vector<String> cronusQueryFileNames = new Vector<String>();
	private Vector<String> cronusQueryTableNames = new Vector<String>();
	private Vector<String> cronusQueryMetaDataNames = new Vector<String>();

	private QueriesCronus queriesCronus = new QueriesCronus();

	public UtilCronus() {
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query1.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query2.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query3.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query4.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query5.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query6.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query7.accdb");

		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query1.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query2.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query3.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query4.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query5.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query6.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query7.xlsx");

		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query1.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query2.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query3.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query4.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query5.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query6.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query7.sql");

		cronusAccessFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportEmployee.accdb");
		cronusAccessFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportCustomer.accdb");

		cronusExcelFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportEmployee.xlsx");
		cronusExcelFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportCustomer.xlsx");

		cronusWordFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportEmployee.docx");
		cronusWordFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportCustomer.docx");

		cronusQueryFileNames.add("Select...");
		cronusQueryFileNames.add("100 NOK in SEK");
		cronusQueryFileNames.add("Most used currency in SEK");
		cronusQueryFileNames.add("Adress and town for Fotograferna AB");
		cronusQueryFileNames.add("Name of employees who has been sick");
		cronusQueryFileNames.add("Name and kinship of all employees' relatives");
		cronusQueryFileNames.add("Customers handled by Andreas Berglund");
		cronusQueryFileNames.add("Bank accounts owned by customer nr 10000");

		cronusQueryMetaDataNames.add("Select...");
		cronusQueryMetaDataNames.add("Metadata Employee (Top 20)");
		cronusQueryMetaDataNames.add("Metadata Employee Absence");
		cronusQueryMetaDataNames.add("Metadata Employee Portal Setup (Top 20)");
		cronusQueryMetaDataNames.add("Metadata Employee Qualification");
		cronusQueryMetaDataNames.add("Metadata Employee Relative");
		cronusQueryMetaDataNames.add("Metadata Employee Statistics Group");
		cronusQueryMetaDataNames.add("Keys (Top 20)");
		cronusQueryMetaDataNames.add("Indexes (Top 20)");
		cronusQueryMetaDataNames.add("Table Constraints (Top 20)");
		cronusQueryMetaDataNames.add("All tables (Using Information Schema)");
		cronusQueryMetaDataNames.add("All tables (Using SysObjects)");
		cronusQueryMetaDataNames.add("Employee columns (Using Information Schema)");
		cronusQueryMetaDataNames.add("Employee columns (Using SysColumns)");
		cronusQueryMetaDataNames.add("Most rows in database");

		cronusQueryTableNames.add("Select...");
		cronusQueryTableNames.add("Employee");
		cronusQueryTableNames.add("Employee Absence (Top 20)");
		cronusQueryTableNames.add("Employee Portal Setup");
		cronusQueryTableNames.add("Employee Qualification");
		cronusQueryTableNames.add("Employee Relative");
		cronusQueryTableNames.add("Employee Statistics Group");
	}

	public String getQuery(String comboBoxName, int selectedIndex) {

		if (comboBoxName.equals("comboBox_caccessTables")) {
			return queriesCronus.getQueriesCronusEmployee().get(selectedIndex - 1);
		} else {
			return queriesCronus.getQueriesCronusMetaData().get(selectedIndex - 1);
		}
	}

	public Vector<String> getCronusQueryGetTables() {
		return cronusQueryTableNames;
	}

	public Vector<String> getCronusQueryGetMetaData() {
		return cronusQueryMetaDataNames;
	}

	public Vector<String> getCronusFileNameToOpen() {
		return cronusQueryFileNames;
	}

	public void openCronusFile(String nameProgramToUse, int selectedProgramToUse, int selectedCronusFileToOpen)
			throws IOException, IllegalArgumentException {

		Desktop desktop = Desktop.getDesktop();

		if (nameProgramToUse.equals("comboBox_access_excel") && selectedProgramToUse == 1) {

			desktop.open(new File(cronusAccessDocFilesToOpen.get(selectedCronusFileToOpen - 1)));

		} else if (nameProgramToUse.equals("comboBox_access_excel") && selectedProgramToUse == 2) {

			desktop.open(new File(cronusExcelDocFilesToOpen.get(selectedCronusFileToOpen - 1)));

		} else if (nameProgramToUse.equals("comboBox_access_excel") && selectedProgramToUse == 3) {

			desktop.open(new File(cronusManagementStudioDocFilesToOpen.get(selectedCronusFileToOpen - 1)));

		} else if (nameProgramToUse.equals("comboBox_oform_selectProgram") && selectedProgramToUse == 1) {

			desktop.open(new File(cronusAccessFormFilesToOpen.get(selectedCronusFileToOpen - 1)));

		} else if (nameProgramToUse.equals("comboBox_oform_selectProgram") && selectedProgramToUse == 2) {

			desktop.open(new File(cronusExcelFormFilesToOpen.get(selectedCronusFileToOpen - 1)));

		} else if (nameProgramToUse.equals("comboBox_oform_selectProgram") && selectedProgramToUse == 3) {

			desktop.open(new File(cronusWordFormFilesToOpen.get(selectedCronusFileToOpen - 1)));

		}
	}
}
