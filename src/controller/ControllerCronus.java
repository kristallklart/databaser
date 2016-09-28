package controller;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import databaseAccess.DataAccessLayerCronus;
import utilities.UtilCronus;

public class ControllerCronus {

	private DataAccessLayerCronus dalC = new DataAccessLayerCronus();
	private UtilCronus utilCronus = new UtilCronus();

	// Anv�nds f�r att visa en tabell f�r respektive query f�r uppgift 2
	public DefaultTableModel getTableModel(int selectedIndex) throws SQLException {
		return dalC.getTableModel(selectedIndex);
	}
	public DefaultTableModel getTableModelMeta(int selectedIndex) throws SQLException {
		return dalC.getTableModelMeta(selectedIndex);
	}

	// Anv�nds f�r att s�tta r�tt query-namn i comboboxen f�r uppgift 2.
	public Vector<String> getCronusQueryNamesTables() {
		return utilCronus.getCronusQueryGetTables();
	}
	public Vector<String> getCronusQueryNamesMetaData() {
		return utilCronus.getCronusQueryGetMetaData();
	}

	// Anv�nds f�r att �ppna r�tt fil i Excel eller Access
	public void openCronusFile(String nameProgramToUse, int selectedProgramToUse, int selectedCronusFileToOpen) {
		utilCronus.openCronusFile(nameProgramToUse, selectedProgramToUse, selectedCronusFileToOpen);
	}

	public Vector<String> getCronusFileNameToOpen() {
		return utilCronus.getCronusFileNameToOpen();
	}

}
