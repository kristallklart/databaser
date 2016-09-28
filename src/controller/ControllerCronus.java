package controller;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import databaseAccess.DataAccessLayerCronus;
import utilities.UtilCronus;

public class ControllerCronus {

	private DataAccessLayerCronus dalC = new DataAccessLayerCronus();
	private UtilCronus utilCronus = new UtilCronus();

	// Används för att visa en tabell för respektive query för uppgift 2
	public DefaultTableModel getTableModel(int selectedIndex) throws SQLException {
		return dalC.getTableModel(selectedIndex);
	}
	public DefaultTableModel getTableModelMeta(int selectedIndex) throws SQLException {
		return dalC.getTableModelMeta(selectedIndex);
	}

	// Används för att sätta rätt query-namn i comboboxen för uppgift 2.
	public Vector<String> getCronusQueryNamesTables() {
		return utilCronus.getCronusQueryGetTables();
	}
	public Vector<String> getCronusQueryNamesMetaData() {
		return utilCronus.getCronusQueryGetMetaData();
	}

	// Används för att öppna rätt fil i Excel eller Access
	public void openCronusFile(String nameProgramToUse, int selectedProgramToUse, int selectedCronusFileToOpen) {
		utilCronus.openCronusFile(nameProgramToUse, selectedProgramToUse, selectedCronusFileToOpen);
	}

	public Vector<String> getCronusFileNameToOpen() {
		return utilCronus.getCronusFileNameToOpen();
	}

}
