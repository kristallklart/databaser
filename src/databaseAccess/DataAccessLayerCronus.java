package databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import utilities.UtilCronus;
import utilities.UtilDatabaseAccess;

public class DataAccessLayerCronus {
	private LoginDataCronus login = new LoginDataCronus();
	private UtilCronus util = new UtilCronus();
	private UtilDatabaseAccess utilDatabaseAccess = new UtilDatabaseAccess();
	private PreparedStatement pStatement = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	private Connection con = null;

	public Connection createConnection() throws SQLException {
		return DriverManager.getConnection(login.getUrl(), login.getUser(), login.getPw());
	}

	public DefaultTableModel getTableModel(String nameComboBox, int selectedIndex) throws SQLException {
		Vector<Vector<Object>> sendData = new Vector<Vector<Object>>();
		Vector<String> sendColumnNames = new Vector<String>();

		try {
			con = this.createConnection();
			pStatement = con.prepareStatement(util.getQuery(nameComboBox, selectedIndex));
			rs = pStatement.executeQuery();
			rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++) {
				sendColumnNames.add(rsmd.getColumnName(i));
			}

			while (rs.next()) {
				Vector<Object> columnData = new Vector<Object>();
				for (int i = 1; i <= numberOfColumns; i++) {
					columnData.add(rs.getObject(i));
				}
				sendData.add(columnData);
			}
			
			DefaultTableModel model = new DefaultTableModel(sendData, sendColumnNames) {
				private static final long serialVersionUID = -5462317740326355112L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

			};

			return model;
			
		} finally {
			utilDatabaseAccess.closeAll(pStatement, con);
		}

	}

}
