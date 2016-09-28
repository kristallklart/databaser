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

public class DataAccessLayerCronus {
	private LoginDataCronus login = new LoginDataCronus();
	private UtilCronus util = new UtilCronus();

	public Connection createConnection() throws SQLException {
		return DriverManager.getConnection(login.getUrl(), login.getUser(), login.getPw());
	}

	public DefaultTableModel getTableModel(int selectedIndex) {
		Vector<Vector<Object>> sendData = new Vector<Vector<Object>>();
		Vector<String> sendColumnNames = new Vector<String>();
		UtilCronus utilCronus = new UtilCronus();

		Connection con = null;
		try {
			con = this.createConnection();
			if (con != null) {

				PreparedStatement pstate = null;
				ResultSet rs = null;
				ResultSetMetaData rsmd = null;

				pstate = con.prepareStatement(utilCronus.getQuery(selectedIndex));
				rs = pstate.executeQuery();
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
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		DefaultTableModel model = new DefaultTableModel(sendData, sendColumnNames);

		return model;
	}
	
	public DefaultTableModel getTableModelMeta(int selectedIndex) {
		Vector<Vector<Object>> sendData = new Vector<Vector<Object>>();
		Vector<String> sendColumnNames = new Vector<String>();
		UtilCronus utilCronus = new UtilCronus();

		Connection con = null;
		try {
			con = this.createConnection();
			if (con != null) {

				PreparedStatement pstate = null;
				ResultSet rs = null;
				ResultSetMetaData rsmd = null;

				pstate = con.prepareStatement(utilCronus.getQueryMeta(selectedIndex));
				rs = pstate.executeQuery();
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
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		DefaultTableModel model = new DefaultTableModel(sendData, sendColumnNames);

		return model;
	}

}
