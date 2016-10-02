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
	}

	public String getQuery(String comboBoxName, int selectedIndex) {

		if (comboBoxName.equals("comboBox_caccessTables")) {
			return queriesCronus.getQueriesCronusEmployee().get(selectedIndex);
		} else {
			return queriesCronus.getQueriesCronusMetaData().get(selectedIndex);
		}
	}

	public Vector<String> getCronusQueryGetTables() {
		Vector<String> cronusQueryNames = new Vector<String>();

		cronusQueryNames.add("Select..."); // 0
		cronusQueryNames.add("Employee"); // 1
		cronusQueryNames.add("Employee Absence (Top 20)"); // 2
		cronusQueryNames.add("Employee Portal Setup"); // 3
		cronusQueryNames.add("Employee Qualification"); // 4
		cronusQueryNames.add("Employee Relative"); // 5
		cronusQueryNames.add("Employee Statistics Group"); // 6

		return cronusQueryNames;
	}

	public Vector<String> getCronusQueryGetMetaData() {
		Vector<String> cronusQueryNames = new Vector<String>();
		cronusQueryNames.add("Select..."); // 0
		cronusQueryNames.add("Metadata Employee (Top 20)"); // 1
		cronusQueryNames.add("Metadata Employee Absence"); // 2
		cronusQueryNames.add("Metadata Employee Portal Setup (Top 20)"); // 3
		cronusQueryNames.add("Metadata Employee Qualification"); // 4
		cronusQueryNames.add("Metadata Employee Relative"); // 5
		cronusQueryNames.add("Metadata Employee Statistics Group"); // 6
		cronusQueryNames.add("Keys (Top 20)"); // 7
		cronusQueryNames.add("Indexes (Top 20)"); // 8
		cronusQueryNames.add("Table Constraints (Top 20)"); // 9
		cronusQueryNames.add("All tables (Using Information Schema)"); // 10
		cronusQueryNames.add("All tables (Using SysObjects)"); // 11
		cronusQueryNames.add("Employee columns (Using Informatio nSchema)"); // 12
		cronusQueryNames.add("Employee columns (Using SysColumns)"); // 13
		cronusQueryNames.add("Most rows in database"); // 14

		return cronusQueryNames;
	}

	public Vector<String> getCronusFileNameToOpen() {
		Vector<String> cronusQueryFileNames = new Vector<String>();

		cronusQueryFileNames.add("Select..."); // 0
		cronusQueryFileNames.add("100 NOK in SEK"); // 1
		cronusQueryFileNames.add("Most used currency in SEK"); // 2
		cronusQueryFileNames.add("Adress and town for Fotograferna AB"); // 3
		cronusQueryFileNames.add("Name of employees who has been sick"); // 4
		cronusQueryFileNames.add("Name and kinship of all employees' relatives"); // 5
		cronusQueryFileNames.add("Customers handled by Andreas Berglund"); // 6
		cronusQueryFileNames.add("Bank accounts owned by customer nr 10000"); // 7

		return cronusQueryFileNames;
	}

	public void openCronusFile(String nameProgramToUse, int selectedProgramToUse, int selectedCronusFileToOpen)
			throws IOException, IllegalArgumentException {
		// Desktop �r en klass som anv�nds f�r att bland annat g�ra s� att
		// en Java-applikation kan �ppna filer.
		// D�rf�r beh�ver vi en s�dan h�r. /JAKOB
		Desktop desktop = Desktop.getDesktop();

		// Efter att ha tagit emot namnet p� vilken combobox som anv�nds,
		// indexet f�r comboboxen som v�ljer program,
		// samt indexet f�r vilken cronusfil som ska �ppnas s� �ppnas r�tt
		// fil med hj�lp av desktop-klassen /JAKOB
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
