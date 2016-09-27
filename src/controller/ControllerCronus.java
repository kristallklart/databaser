package controller;

import javax.swing.table.DefaultTableModel;
import databaseAccess.DataAccessLayerCronus;
import java.sql.SQLException;

public class ControllerCronus {

	private DataAccessLayerCronus dalC = new  DataAccessLayerCronus();
	
	public DefaultTableModel getTableModel(int selectedIndex) throws SQLException{
		return dalC.getTableModel(selectedIndex);
	}
	
}
