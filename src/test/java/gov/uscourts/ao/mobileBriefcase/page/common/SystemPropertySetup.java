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
	public static final String refCategory = "refCategory";
	public static final String db_servername = "db.servername";
	public static final String db_port = "db.port";
	public static final String db_Schema = "db.schema";
	public static final String db_username = "db.username";
	public static final String db_password = "db.password";
	public static final String personrole = "personrole";
	public static final String userType = "userType";
	public static final String jud = "jud";
	public static final String stf = "stf";
	public static final String userName = "userName";
	public static final String password = "password";

	public static final String getVariable(Variables variable, List<UserInputData> pacerInputData) {
		String var = "";

		switch (variable) {
		case REF_CATEGORY:
			var = refCategory;
			break;
			
		case JUD:
			var = jud;
			break;

		case STF:
			var = stf;
			break;

		case PERSONROLE:
			var = personrole;
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

	public static final String getPersonrole(List<UserInputData> userInputData) {

		if (System.getProperty(personrole) != null)
			return System.getProperty(personrole);
		log.info("Role type from Property File " + System.getProperty(personrole));
		log.info("Role type from Input File " + userInputData.get(0).getPersonrole());
		return userInputData.get(0).getPersonrole();
	}

	public static final String getUserType(List<UserInputData> userInputData) {

		if (System.getProperty(userType) != null)
			return System.getProperty(userType);
		log.info("Role type from Property File " + System.getProperty(userType));
		log.info("Role type from Input File " + userInputData.get(0).getUserType());
		return userInputData.get(0).getUserType();
	}

	public static final String getJudge(List<UserInputData> userInputData) {

		if (System.getProperty(jud) != null)
			return System.getProperty(jud);
		log.info("Role type from Property File " + System.getProperty(jud));
		log.info("Role type from Input File " + userInputData.get(0).getJud());
		return userInputData.get(0).getJud();
	}

	public static final String getStf(List<UserInputData> userInputData) {

		if (System.getProperty(stf) != null)
			return System.getProperty(stf);
		log.info("Role type from Property File " + System.getProperty(stf));
		log.info("Role type from Input File " + userInputData.get(0).getStf());
		return userInputData.get(0).getStf();
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

		if (System.getProperty(refCategory) != null)
			return System.getProperty(refCategory);
		log.info("Court Id from Property File " + System.getProperty(refCategory));
		log.info("Court Id from Input File " + userInputData.get(0).getReferral_Category());
		return userInputData.get(0).getReferral_Category();
	}

	public enum Variables {
		JUD, STF, CASE_NUMBER, REF_CATEGORY, COURTID, PERSONROLE, HOSTNAME, DB_USERNAME, DB_PASSWORD, DB_SERVERNAME, DB_PORT, DB_SCHEMA, USERNAME, PASSWORD
	}

}
