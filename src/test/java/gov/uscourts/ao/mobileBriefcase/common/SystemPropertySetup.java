package gov.uscourts.ao.mobileBriefcase.common;

import java.util.List;


import org.apache.log4j.Logger;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class SystemPropertySetup {
	private static Logger log = Logger.getLogger(SystemPropertySetup.class);

	// this controls the runtime configuration that is passed by maven -D parameter
	public static final String courtId = "courtId";
	public static final String hostname = "hostname";

	public static final String environment = "environment";
	public static final String caseNumber = "caseNumber";
	public static final String referral_Category = "referral_Category";
	public static final String db_servername = "db.servername";
	public static final String db_port = "db.port";
	public static final String db_Schema = "db.schema";
	public static final String db_username = "db.username";
	public static final String db_password = "db.password";
	public static final String role = "role";
	public static final String briefcaseUser = "briefcaseUser";

	public static final String userName = "userName";
	public static final String password = "password";

	public static final String getVariable(Variables variable, List<UserInputData> pacerInputData) {
		String var = "";

		switch (variable) {
		case COURTID:
			var = courtId;

			break;
			
		case ROLE:
			var = role;

			break;

		case HOSTNAME:
			var = hostname;
			break;

		case CASE_NUMBER:
			var = caseNumber;
			break;

		case DB_USERNAME:
			var = db_username;
			break;

		case DB_PASSWORD:
			var = db_password;
			break;

		case DB_SERVERNAME:
			var = db_servername;
			break;

		case DB_PORT:
			var = db_port;
			break;

		case DB_SCHEMA:
			var = db_Schema;
			break;

		case USERNAME:
			var = userName;
			break;

		case PASSWORD:
			var = password;
			break;

		default:
			break;
		}

		if (System.getProperty(var) != null)
			return System.getProperty(var);
		log.info(var + " from Input File " + pacerInputData.get(0).getPacerInputData(var));
		return pacerInputData.get(0).getPacerInputData(var);

	}

	public static final String getCourtId(List<UserInputData> userInputData) {

		if (System.getProperty(courtId) != null)
			return System.getProperty(courtId);
		log.info("Court Id from Property File " + System.getProperty(courtId));
		log.info("Court Id from Input File " + userInputData.get(0).getCourtId());
		return userInputData.get(0).getCourtId();
	}

	public static final String getRoleType(List<UserInputData> userInputData) {

		if (System.getProperty(role) != null)
			return System.getProperty(role);
		log.info("Role type from Property File " + System.getProperty(role));
		log.info("Role type from Input File " + userInputData.get(0).getRoleType());
		return userInputData.get(0).getRoleType();
	}

	public static final String getUser(List<UserInputData> userInputData) {

		if (System.getProperty(briefcaseUser) != null)
			return System.getProperty(briefcaseUser);
		log.info("Role type from Property File " + System.getProperty(briefcaseUser));
		log.info("Role type from Input File " + userInputData.get(0).getBriefcaseUser());
		return userInputData.get(0).getBriefcaseUser();
	}

	public static final String getEnvironment(List<UserInputData> userInputData) {

		if (System.getProperty(environment) != null)
			return System.getProperty(environment);

		log.info("Environment from Property File " + System.getProperty(environment));
		log.info("Environment from Input File " + userInputData.get(0).getEnvironment());
		return userInputData.get(0).getEnvironment();
	}

	public static final String db_username() {
		if (System.getProperty(db_username) != null) {
			log.info("host Name from Property File " + System.getProperty(db_username));
			return System.getProperty(db_username);
		}
		return null;

	}

	public static final String db_password() {
		if (System.getProperty(db_password) != null) {
			log.info("host Name from Property File " + System.getProperty(db_password));
			return System.getProperty(db_password);
		}
		return null;

	}

	public static final String getHost_name() {
		if (System.getProperty(hostname) != null) {
			log.info("host Name from Property File " + System.getProperty(hostname));
			return System.getProperty(hostname);
		}
		return null;

	}

	public static final String getServer_name() {
		log.info("server_name from Property File " + System.getProperty(db_servername));

		if (System.getProperty(db_servername) != null)
			return System.getProperty(db_servername);
		return null;
	}

	public static final int getDb_port() {
		log.info("db_port from Property File " + System.getProperty(db_port));
		if (System.getProperty(db_port) != null)
			return Integer.valueOf(System.getProperty(db_port));
		return 0;
	}

	public static final String getDb_Schema() {
		log.info("db_Schema from Property File " + System.getProperty(db_Schema));
		if (System.getProperty(db_Schema) != null)
			return System.getProperty(db_Schema);
		return null;
	}

	public static final String getUserName(List<UserInputData> userInputData) {

		if (System.getProperty(userName) != null)
			return System.getProperty(userName);
		log.info("Username from Property File " + System.getProperty(userName));
		log.info("Username from Input File " + userInputData.get(0).getUserName());
		return userInputData.get(0).getUserName();
	}

	public static final String getPassword(List<UserInputData> userInputData) {

		if (System.getProperty(password) != null)
			return System.getProperty(password);
		log.info("Password from Property File " + System.getProperty(password));
		log.info("Password from Input File " + userInputData.get(0).getPassword());
		return userInputData.get(0).getPassword();
	}

	public static final String getCaseNumber(List<UserInputData> userInputData) {

		if (System.getProperty(caseNumber) != null)
			return System.getProperty(caseNumber);
		log.info("Case number from Property File " + System.getProperty(caseNumber));
		log.info("Case number from Input File " + userInputData.get(0).getCaseNumber());
		return userInputData.get(0).getCaseNumber();
	}

	public static final String getReferralCategory(List<UserInputData> userInputData) {

		if (System.getProperty(referral_Category) != null)
			return System.getProperty(referral_Category);
		log.info("Court Id from Property File " + System.getProperty(referral_Category));
		log.info("Court Id from Input File " + userInputData.get(0).getReferral_Category());
		return userInputData.get(0).getReferral_Category();
	}

	public enum Variables {
		CASE_NUMBER, COURTID,ROLE, HOSTNAME, DB_USERNAME, DB_PASSWORD, DB_SERVERNAME, DB_PORT, DB_SCHEMA, USERNAME, PASSWORD
	}

}
