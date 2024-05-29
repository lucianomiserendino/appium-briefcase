package gov.uscourts.ao.mobileBriefcase.DBUtils;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM2A_DATABASE_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM2A_DBPWD;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM2A_DBURL;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM2A_DBUSERNAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM2A_SERVERNAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM3A_DATABASE_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM3A_DBPWD;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM3A_DBURL;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM3A_DBUSERNAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CM3A_SERVERNAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CMKA_DATABASE_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CMKA_DBPWD;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CMKA_DBURL;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CMKA_DBUSERNAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.CMKA_SERVERNAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.KEYPASS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.PASS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.PORT_NUMBER;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.SSL_LOC;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBVariables.SSL_STORE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PE_ID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.informix.jdbc.IfxDriver;
import com.informix.jdbcx.IfxConnectionPoolDataSource;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;

public class DBUtilities {
	private IfxConnectionPoolDataSource cds;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	static PreparedStatement ps = null;

	public static final String courtId = "courtId";
	public static final String hostname = "hostname";

	public static final String environment = "environment";
	public static final String caseNumber = "caseNumber";
	public static final String refCategory = "refCategory";
	public static final String db_servername = "servername";
	public static final String db_Schema = "schema";
	public static final String db_username = "db.username";
	public static final String db_password = "db.password";


	public static void establishConnection(DBType dbType) {
		try {
			switch (dbType) {
			case CMKA:
				getIFXProperty(SSL_STORE, SSL_LOC, KEYPASS, PASS, CMKA_DBURL, CMKA_SERVERNAME, CMKA_DBUSERNAME,
						CMKA_DBPWD, CMKA_DATABASE_NAME, PORT_NUMBER);
				break;

			case CM3A:
				getIFXProperty(SSL_STORE, SSL_LOC, KEYPASS, PASS, CM3A_DBURL, CM3A_SERVERNAME, CM3A_DBUSERNAME,
						CM3A_DBPWD, CM3A_DATABASE_NAME, PORT_NUMBER);

			case PVTA:
				getIFXProperty(SSL_STORE, SSL_LOC, KEYPASS, PASS, CM3A_DBURL, CM3A_SERVERNAME, CM3A_DBUSERNAME,
						CM3A_DBPWD, CM3A_DATABASE_NAME, PORT_NUMBER);

			case CM2A:
				getIFXProperty(SSL_STORE, SSL_LOC, KEYPASS, PASS, CM2A_DBURL, CM2A_SERVERNAME, CM2A_DBUSERNAME,
						CM2A_DBPWD, CM2A_DATABASE_NAME, PORT_NUMBER);

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
		if (!result.equals(null)) {
			queryResult.forEach(record -> result.add(record[0].trim()));
		} else {
			return null;
		}
		closeConnections();
		return result;

	}

	public static List<String> executeQuery(String query, List<UserInputData> userInputData) {

		DBUtilities dbConnectionPool = new DBUtilities();
		List<String> result = new ArrayList<>();

		try {
			connection = dbConnectionPool.createDbConnection(userInputData);
			if (connection == null) {
				assertTrue("Connection Not Established...", false);

			}
			List<String[]> queryResult = runSQLQuery(query);

			if (!(queryResult == null)) {
				queryResult.forEach(record -> result.add(record[0].trim()));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			closeConnections();

		}

		return result;

	}

	public static List<String> execute(DBType dbType, String query, int column) {
		String a = null;
		establishConnection(dbType);
		List<String> result = new ArrayList<>();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = statement.executeQuery(query);
			ResultSetMetaData rsMetada = resultSet.getMetaData();

			int columnsCount = rsMetada.getColumnCount();
			resultSet.last();
			int recordCount = resultSet.getRow();
			if (columnsCount == 0 || recordCount == 0) {
				return null;
			}

			resultSet.beforeFirst();

			while (resultSet.next()) {
				// try {
				for (int i = 1; i < column; i++) {
					a = resultSet.getString(i);
					if (!(a == null)) {
						a.trim();
					}
				}
//				} catch (NullPointerException e) {
//					// TODO: handle exception
//
//				}

				result.add(a);
			}
			closeConnections();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public static List<String> execute(String query, int column, List<UserInputData> userInputData) {
		String a = null;

		List<String> result = new ArrayList<>();

		DBUtilities dbConnectionPool = new DBUtilities();

		try {
			connection = dbConnectionPool.createDbConnection(userInputData);
			if (connection == null) {
				assertTrue("Connection Not Established...", false);

			}
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(query);
			ResultSetMetaData rsMetada = resultSet.getMetaData();

			int columnsCount = rsMetada.getColumnCount();
			resultSet.last();
			int recordCount = resultSet.getRow();

			if (columnsCount == 0 || recordCount == 0) {
				return null;
			}

			resultSet.beforeFirst();

			while (resultSet.next()) {
				for (int i = 1; i < column; i++) {
					a = resultSet.getString(i);

				}
				if (!(a == null)) {
					a.trim();
				}

				result.add(a);
			}
			closeConnections();

		} catch (Exception e) {
			e.printStackTrace();
		}

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
					allColumns += resultSet.getString(i);
				}
			}
		} catch (Exception e) {
			e.getMessage();
			closeConnections();
		}
		return allColumns;

	}

	public static void insertData(DBType dbType, String query) {
		establishConnection(dbType);
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.getMessage();
			closeConnections();
		}
	}

	public static void insertData(String query, List<UserInputData> userInputData) {

		DBUtilities dbConnectionPool = new DBUtilities();
		try {
			connection = dbConnectionPool.createDbConnection(userInputData);
			if (connection == null) {
				assertTrue("Connection Not Established...", false);
			}
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.getMessage();
			closeConnections();
		}

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

	public static boolean getDBConnection(List<UserInputData> pacerInputData) {
		DBUtilities dbConnectionPool = new DBUtilities();
		try {
			connection = dbConnectionPool.createDbConnection(pacerInputData);
			if (connection == null) {
				assertTrue("Connection Not Established...", false);

			}
			ps = connection.prepareStatement("SELECT * FROM SITE");
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString("si_code") + "\t" + resultSet.getString("si_value"));
				return true;

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			closeConnections();

		}
		return false;

	}

	public static List<String> executeQuery(String query, List<UserInputData> userInputData, int col) {

		DBUtilities dbConnectionPool = new DBUtilities();
		List<String> result = new ArrayList<>();

		try {
			connection = dbConnectionPool.createDbConnection(userInputData);
			if (connection == null) {
				assertTrue("Connection Not Established...", false);

			}
			List<String[]> queryResult = runSQLQuery(query);

			if (!(queryResult == null)) {
				queryResult.forEach(record -> result.add(record[col].trim()));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			closeConnections();

		}

		return result;

	}

	public Connection createDbConnection(List<UserInputData> pacerInputData) {

		String env = SystemPropertySetup.getCourtId(pacerInputData)+".";

		setProperty(getProperty(SSL_STORE), getProperty(SSL_LOC));
		setProperty(getProperty(KEYPASS), getProperty(PASS));

		cds = new IfxConnectionPoolDataSource();

		cds.setIfxIFXHOST(getProperty(env + hostname));

		cds.setServerName(getProperty(env +db_servername));

		cds.setPortNumber(Integer.parseInt(getProperty(PORT_NUMBER)));

		cds.setUser(getProperty(db_username));

		cds.setPassword(getProperty(db_password));

		cds.setDatabaseName(getProperty(env + db_Schema));

		if (getProperty(env + db_servername).split("_")[1].equals("ssl")) {
			cds.setIfxSSLCONNECTION("true");
			System.out.println(" JDBC Driver Version .: " + IfxDriver.getJDBCVersion());
		}
		try {
			connection = cds.getPooledConnection().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

	public static String getAllColumns(String query, List<UserInputData> pacerInputData) {
	    StringBuilder allColumns = new StringBuilder();
	    DBUtilities dbConnectionPool = new DBUtilities();

	    try (Connection connection = dbConnectionPool.createDbConnection(pacerInputData);
	         PreparedStatement ps = connection.prepareStatement(query);
	         ResultSet resultSet = ps.executeQuery()) {

	        if (connection == null) {
	            assertTrue("Connection Not Established...", false);
	        }

	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int numberOfColumns = metaData.getColumnCount();

	        while (resultSet.next()) {
	            for (int i = 1; i <= numberOfColumns; i++) {
	                allColumns.append(resultSet.getString(i));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return allColumns.toString();
	}


	public static String getID(String query, String id) {
		return query.replace("?", id);
	}

	public static String getPE_ID(String PE_RT_CODE, String judgeLName, String judgeFName,
			List<UserInputData> userInputData) {

		return getAllColumns(
				replace(PE_ID, "PE_RT_CODE", PE_RT_CODE, "PR_LAST_NAME", judgeLName, "PR_FIRST_NAME", judgeFName),
				userInputData);

	}

	public static String setProperty(String key, String value) {
		return System.setProperty(key, value);
	}

	public static DBType valueOf(String dbType) {
		return DBType.valueOf(dbType);
	}

	public static String getText(String query, String text) {
		return query.replace("TEXT", text);
	}

	public static String getCode(String query, String text) {
		return query.replace("CODE", text);
	}

	public enum DBType {
		CMKA, CM3A, PVTA, CM2A
	}

}
