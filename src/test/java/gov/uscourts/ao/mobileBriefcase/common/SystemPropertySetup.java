package gov.uscourts.ao.mobileBriefcase.common;

import java.util.List;

import org.apache.log4j.Logger;

import gov.uscourts.ao.mobileBriefcase.models.UserInputData;

public class SystemPropertySetup {
	private static Logger log = Logger.getLogger(SystemPropertySetup.class);
	// this controls the runtime configuration that is passed by maven -D parameter
	public static final String courtId = "courtId";
	public static final String host_name = "hostname";
	public static final String userName = "userName";
	public static final String password = "password";
	public static final String environment = "environment";
	public static final String roleType = "roleType";
	public static final String caseNumber = "caseNumber";
	public static final String referral_Category = "referral_Category";
	public static final String server_name = "db.dbservername";
	public static final String db_port = "db.port";
	public static final String db_Schema = "db.schema";

	public static final String getCourtId(List<UserInputData> userInputData) {

		if (System.getProperty(courtId) != null)
			return System.getProperty(courtId);
		log.info("Court Id from Property File " + System.getProperty(courtId));
		log.info("Court Id from Input File " + userInputData.get(0).getCourtId());
		return userInputData.get(0).getCourtId();
	}

	public static final String getRoleType(List<UserInputData> userInputData) {

		if (System.getProperty(roleType) != null)
			return System.getProperty(roleType);
		log.info("Role type from Property File " + System.getProperty(roleType));
		log.info("Role type from Input File " + userInputData.get(0).getRoleType());
		return userInputData.get(0).getRoleType();
	}

	public static final String getEnvironment(List<UserInputData> userInputData) {

		if (System.getProperty(environment) != null)
			return System.getProperty(environment);

		log.info("Environment from Property File " + System.getProperty(courtId));
		log.info("Environment from Input File " + userInputData.get(0).getEnvironment());
		return userInputData.get(0).getEnvironment();
	}

	public static final String getEnvironment() {
		return System.getProperty(courtId);
	}

	public static final String getHost_name() {
		if (System.getProperty(host_name) != null) {
			log.info("host Name from Property File " + System.getProperty(host_name));
			return System.getProperty(host_name);
		}
		return null;

	}

	public static final String getServer_name() {
		log.info("server_name from Property File " + System.getProperty(server_name));

		if (System.getProperty(server_name) != null)
			return System.getProperty(server_name);
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
		log.info("Password from Input File " + userInputData.get(0).getPasswd());
		return userInputData.get(0).getPasswd();
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
		log.info("Court Id from Input File " + userInputData.get(0).getReferralCategory());
		return userInputData.get(0).getReferralCategory();
	}

}
