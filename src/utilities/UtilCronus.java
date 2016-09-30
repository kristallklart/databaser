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
		cronusAccessDocFilesToOpen.add(" ");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query1.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query2.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query3.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query4.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query5.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query6.accdb");
		cronusAccessDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query7.accdb");

		cronusExcelDocFilesToOpen.add(" ");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query1.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query2.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query3.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query4.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query5.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query6.xlsx");
		cronusExcelDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query7.xlsx");

		cronusManagementStudioDocFilesToOpen.add(" ");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query1.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query2.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query3.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query4.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query5.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query6.sql");
		cronusManagementStudioDocFilesToOpen.add("C:\\Program Files\\Cronusfiler\\Query7.sql");

		cronusAccessFormFilesToOpen.add(" ");
		cronusAccessFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportEmployee.accdb");
		cronusAccessFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportCustomer.accdb");

		cronusExcelFormFilesToOpen.add(" ");
		cronusExcelFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportEmployee.xlsx");
		cronusExcelFormFilesToOpen.add("C:\\Program Files\\Cronusfiler\\RapportCustomer.xlsx");

		cronusWordFormFilesToOpen.add(" ");
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
		cronusQueryNames.add("Employee Absence top 20"); // 2
		cronusQueryNames.add("Employee Portal Setup"); // 3
		cronusQueryNames.add("Employee Qualification"); // 4
		cronusQueryNames.add("Employee Relative"); // 5
		cronusQueryNames.add("Employee Statistics Group"); // 6

		return cronusQueryNames;
	}

	public Vector<String> getCronusQueryGetMetaData() {
		Vector<String> cronusQueryNames = new Vector<String>();
		cronusQueryNames.add("Select..."); // 0
		cronusQueryNames.add("Metadata Employee top 20"); // 1
		cronusQueryNames.add("Metadata Employee Absence"); // 2
		cronusQueryNames.add("Metadata Employee Portal Setup top 20"); // 3
		cronusQueryNames.add("Metadata Employee Qualification"); // 4
		cronusQueryNames.add("Metadata Employee Relative"); // 5
		cronusQueryNames.add("Metadata Employee Statistics Group"); // 6
		cronusQueryNames.add("Keys top 20"); // 7
		cronusQueryNames.add("Indexes top 20"); // 8
		cronusQueryNames.add("Table Constrains top 20"); // 9
		cronusQueryNames.add("All tables (InformationSchema)"); // 10
		cronusQueryNames.add("All tables (SysObjects)"); // 11
		cronusQueryNames.add("Employee columns (InformationSchema)"); // 12
		cronusQueryNames.add("Employee columns (SysColumns)"); // 13
		cronusQueryNames.add("Most rows in database"); // 14

		return cronusQueryNames;
	}

	public Vector<String> getCronusFileNameToOpen() {
		Vector<String> cronusQueryFileNames = new Vector<String>();

		cronusQueryFileNames.add("Select..."); // 0
		cronusQueryFileNames.add("100 NOK in SEK"); // 1
		cronusQueryFileNames.add("Most used currency in SEK"); // 2
		cronusQueryFileNames.add("Adress and town for Fotograferna AB"); // 3
		cronusQueryFileNames.add("Name for employees who has been sick"); // 4
		cronusQueryFileNames.add("Name and kinship for all employees' relatives"); // 5
		cronusQueryFileNames.add("Customers handled by Andreas Berglund"); // 6
		cronusQueryFileNames.add("Bank accounts owned by customer nr 10000"); // 7

		return cronusQueryFileNames;
	}

	public void openCronusFile(String nameProgramToUse, int selectedProgramToUse, int selectedCronusFileToOpen) {
		try {
			// Desktop är en klass som används för att bland annat göra så att
			// en Java-applikation kan öppna filer.
			// Därför behöver vi en sådan här. /JAKOB
			Desktop desktop = null;

			// Här kollar vi först om klassen desktop stöds av den lokala
			// datorn, i så fall hämtar vi den instansen.
			// Stöds den inte så kan vi ju inte öppna filer.
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}

			// Efter att ha tagit emot namnet på vilken combobox som används,
			// indexet för comboboxen som väljer program,
			// samt indexet för vilken cronusfil som ska öppnas så öppnas rätt
			// fil med hjälp av desktop-klassen /JAKOB
			if (nameProgramToUse.equals("comboBox_access_excel") && selectedProgramToUse == 1) {
				desktop.open(new File(cronusAccessDocFilesToOpen.get(selectedCronusFileToOpen)));
			} else if (nameProgramToUse.equals("comboBox_access_excel") && selectedProgramToUse == 2) {
				desktop.open(new File(cronusExcelDocFilesToOpen.get(selectedCronusFileToOpen)));
			} else if (nameProgramToUse.equals("comboBox_access_excel") && selectedProgramToUse == 3) {
				desktop.open(new File(cronusManagementStudioDocFilesToOpen.get(selectedCronusFileToOpen)));
			} else if (nameProgramToUse.equals("comboBox_oform_selectProgram") && selectedProgramToUse == 1) {
				desktop.open(new File(cronusAccessFormFilesToOpen.get(selectedCronusFileToOpen)));
			} else if (nameProgramToUse.equals("comboBox_oform_selectProgram") && selectedProgramToUse == 2) {
				desktop.open(new File(cronusExcelFormFilesToOpen.get(selectedCronusFileToOpen)));
			} else if (nameProgramToUse.equals("comboBox_oform_selectProgram") && selectedProgramToUse == 3) {
				desktop.open(new File(cronusWordFormFilesToOpen.get(selectedCronusFileToOpen)));
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
