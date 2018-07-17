package gov.uscourts.ao.mobileBriefcase.DBUtils;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PE_ID;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getRestrictParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.informix.jdbcx.IfxConnectionPoolDataSource;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Configuration;

public class DBUtilities {

	final static String CMKA = Configuration.getProperty("dbUrl_CMKA");
	final static String CM5A = Configuration.getProperty("dbUrl_CM5A");
	final static String dbUsername = Configuration.getProperty("dbUsername");
	final static String dbPwd = Configuration.getProperty("dbPwd");
	final static String serverName = Configuration.getProperty("serverName");
	final static String databaseName = Configuration.getProperty("databaseName");
	final static int portNumber = Integer.parseInt(Configuration.getProperty("portNumber"));

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	public static void establishConnection(DBType dbType) {
		try {
			switch (dbType) {
			case CMKA:
				Class.forName("com.informix.jdbc.IfxDriver");
				connection = DriverManager.getConnection(CMKA, dbUsername, dbPwd);
				break;
			case CM5A:
				System.setProperty("javax.net.ssl.trustStore", "/Users/saltanakasabolotova/Desktop/cacerts.jks");
				System.setProperty("javax.net.ssl.trustStorePassword", "password");

				/* Instantiate Informix connection pooled data source */
				IfxConnectionPoolDataSource cds = new IfxConnectionPoolDataSource();

				/*
				 * Set SSLConnection property to true and port pointing to SSL port on the
				 * server
				 */
				cds.setIfxIFXHOST(CM5A);
				cds.setServerName(serverName);
				cds.setUser(dbUsername);
				cds.setPassword(dbPwd);
				cds.setDatabaseName(databaseName);
				cds.setPortNumber(portNumber);
				cds.setIfxSSLCONNECTION("true");
				connection = cds.getPooledConnection().getConnection();

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
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

	public static List<String> executeQuery(DBType dbType, String query) {
		establishConnection(dbType);
		List<String[]> queryResult = runSQLQuery(query);
		List<String> result = new ArrayList<>();
		queryResult.forEach(record -> result.add(record[0].trim()));
		closeConnections();
		return result;

	}

	public static String getAllColumns(DBType dbType, String query) {
		establishConnection(dbType);
		ResultSetMetaData metaData;
		String allColumns = "";
		try {

			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(query);
			metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					allColumns += resultSet.getString(i).trim();
				}
			}
		} catch (Exception e) {
			e.getMessage();
			closeConnections();
		}
		return allColumns;

	}

	public static String getEL_Function(String mbrNoteCourtUsersElId, int index) {
		final String mbrNote = MBR_NOTE + mbrNoteCourtUsersElId;
		return getRestrictParam(getAllColumns(DBType.CMKA, mbrNote), index);

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

	public static String getID(String query, String id) {
		return query.replace("?", id);
	}

	public static String getText(String query, String text) {
		return query.replace("text", text);
	}

	public static String getPE_ID(DBType dbType, String judgeName) {

		return getAllColumns(dbType, getText(PE_ID, judgeName));

	}

	public enum DBType {
		CMKA, CM5A
	}

}
