package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import databaseAccess.DataAccessLayerCronus;
import utilities.UtilCronus;

public class ControllerCronus {

	private DataAccessLayerCronus dalC = new DataAccessLayerCronus();
	private UtilCronus utilCronus = new UtilCronus();

	// Används för att visa en tabell för respektive query för uppgift 2
	public DefaultTableModel getTableModel(String nameComboBox, int selectedIndex) throws SQLException {
		return dalC.getTableModel(nameComboBox, selectedIndex);
	}

	// Används för att sätta rätt query-namn i comboboxen för uppgift 2.
	public Vector<String> getCronusQueryNamesTables() {
		return utilCronus.getCronusQueryGetTables();
	}

	public Vector<String> getCronusQueryNamesMetaData() {
		return utilCronus.getCronusQueryGetMetaData();
	}

	// Används för att öppna rätt fil i Excel, Access eller Word
	public void openCronusFile(String nameProgramToUse, int selectedProgramToUse, int selectedCronusFileToOpen)
			throws IOException, IllegalArgumentException {
		utilCronus.openCronusFile(nameProgramToUse, selectedProgramToUse, selectedCronusFileToOpen);
	}

	public Vector<String> getCronusFileNameToOpen() {
		return utilCronus.getCronusFileNameToOpen();
	}

}
