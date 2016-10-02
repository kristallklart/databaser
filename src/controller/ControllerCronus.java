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

	// Anv�nds f�r att visa en tabell f�r respektive query f�r uppgift 2
	public DefaultTableModel getTableModel(String nameComboBox, int selectedIndex) throws SQLException {
		return dalC.getTableModel(nameComboBox, selectedIndex);
	}

	// Anv�nds f�r att s�tta r�tt query-namn i comboboxen f�r uppgift 2.
	public Vector<String> getCronusQueryNamesTables() {
		return utilCronus.getCronusQueryGetTables();
	}

	public Vector<String> getCronusQueryNamesMetaData() {
		return utilCronus.getCronusQueryGetMetaData();
	}

	// Anv�nds f�r att �ppna r�tt fil i Excel, Access eller Word
	public void openCronusFile(String nameProgramToUse, int selectedProgramToUse, int selectedCronusFileToOpen)
			throws IOException, IllegalArgumentException {
		utilCronus.openCronusFile(nameProgramToUse, selectedProgramToUse, selectedCronusFileToOpen);
	}

	public Vector<String> getCronusFileNameToOpen() {
		return utilCronus.getCronusFileNameToOpen();
	}

}
