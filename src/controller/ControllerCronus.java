package controller;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import databaseAccess.DataAccessLayerCronus;
import utilities.UtilCronus;

public class ControllerCronus {

	private DataAccessLayerCronus dalC = new DataAccessLayerCronus();
	private UtilCronus utilCronus = new UtilCronus();

	public DefaultTableModel getTableModel(int selectedIndex) throws SQLException {
		return dalC.getTableModel(selectedIndex);
	}

	public Vector<String> getCronusQueryNames() {
		return utilCronus.getCronusQueryNames();
	}

}
