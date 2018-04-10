package gov.uscourts.ao.mobileBriefcase.Pages.DBUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gov.uscourts.ao.moibleBriefcase.common.Configuration;

public class DBUtilits {

	final static String dbUrl = Configuration.getProperty("dbUrl");
	final static String dbUsername = Configuration.getProperty("dbUsername");
	final static String dbPwd = Configuration.getProperty("dbPwd");

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	public static void establishConnection(DBType dbType) {
		try {
			switch (dbType) {
			case CMKA:
				Class.forName("com.informix.jdbc.IfxDriver");
				connection = DriverManager.getConnection(dbUrl, dbUsername, dbPwd);
				break;
			default:
				throw new RuntimeException("Invalid Database type");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static List<String[]> runSQLQuery(String sql) {
		List<String[]> queryResult = new ArrayList<>();

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sql);
			ResultSetMetaData rsMetada = resultSet.getMetaData();

			int columnsCount = rsMetada.getColumnCount();
			resultSet.last();
			int recordCount = resultSet.getRow();

			if (columnsCount == 0 || recordCount == 0) {
				return null;
			}

			resultSet.beforeFirst();

			while (resultSet.next()) {
				String[] cellData = new String[columnsCount];

				for (int cell = 1; cell <= columnsCount; cell++) {
					cellData[cell - 1] = resultSet.getString(cell);

				}
				queryResult.add(cellData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return queryResult;

	}

	public static void executeQuery(String query) {
		establishConnection(DBType.CMKA);
		List<String[]> queryResult = runSQLQuery(query);
		assertNotNull(queryResult);
		assertFalse(queryResult.size() == 0);
	}

	public static void closeConnections() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
