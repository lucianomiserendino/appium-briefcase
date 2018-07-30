package gov.uscourts.ao.mobileBriefcase.DBUtils;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PE_ID;
import static gov.uscourts.ao.mobileBriefcase.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.DATABASE_NAME_CM3A;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.DATABASE_NAME_CMKA;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.DBPWD_CM3A;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.DBPWD_CMKA;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.DBURL_CM3A;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.DBURL_CMKA;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.DBUSERNAME_CM3A;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.DBUSERNAME_CMKA;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.KEYPASS;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.PASS;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.PORT_NUMBER_CM3A;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.PORT_NUMBER_CMKA;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.SERVERNAME_CM3A;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.SERVERNAME_CMKA;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.SSL_LOC;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.SSL_STORE;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getRestrictParam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.informix.jdbcx.IfxConnectionPoolDataSource;

public class DBUtilities {

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	public static void establishConnection(DBType dbType) {
		try {
			switch (dbType) {
			case CMKA:
				getIFXProperty(SSL_STORE, SSL_LOC, KEYPASS, PASS, DBURL_CMKA, SERVERNAME_CMKA, DBUSERNAME_CMKA,
						DBPWD_CMKA, DATABASE_NAME_CMKA, PORT_NUMBER_CMKA);
				break;

			case CM3A:
				getIFXProperty(SSL_STORE, SSL_LOC, KEYPASS, PASS, DBURL_CM3A, SERVERNAME_CM3A, DBUSERNAME_CM3A,
						DBPWD_CM3A, DATABASE_NAME_CM3A, PORT_NUMBER_CM3A);

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

	public static void getIFXProperty(String sslStore, String sslLoc, String keyPass, String valuePass, String dbURL,
			String serverName, String dbUsername, String dbPwd, String databaseName, String port) {
		setProperty(getProperty(sslStore), getProperty(sslLoc));
		setProperty(getProperty(keyPass), getProperty(valuePass));
		try {
			IfxConnectionPoolDataSource conPool = new IfxConnectionPoolDataSource();
			conPool.setIfxIFXHOST(getProperty(dbURL));
			conPool.setServerName(getProperty(serverName));
			conPool.setUser(getProperty(dbUsername));
			conPool.setPassword(getProperty(dbPwd));
			conPool.setDatabaseName(getProperty(databaseName));
			conPool.setPortNumber(Integer.parseInt(getProperty(port)));
			conPool.setIfxSSLCONNECTION("true");
			connection = conPool.getPooledConnection().getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static String setProperty(String key, String value) {
		return System.setProperty(key, value);
	}

	public static String getID(String query, String id) {
		return query.replace("?", id);
	}

	public static String getText(String query, String text) {
		return query.replace("text", text);
	}
	
	public static String getCyv_code(String query, String text) {
		return query.replace("Code", text);
	}

	public static String getPE_ID(DBType dbType, String judgeName) {

		return getAllColumns(dbType, getText(PE_ID, judgeName));

	}

	public enum DBType {
		CMKA, CM3A
	}
	


}
