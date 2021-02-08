package gov.uscourts.ao.mobileBriefcase.model;

public class UserInputData {
	private String courtId;
	private String hostname;
	private String userName;
	private String password;
	private String environment;
	private String caseNumber;
	private String referral_Category;
	private String db_dbservername;
	private String db_port;
	private String db_Schema;
	private String role;
	private String briefcaseUser;
	private String db_username;
	private String db_password;

	public String getPacerInputData(String inputData) {
		String var = "";

		switch (inputData) {
		case "courtId":
			var = courtId;
			break;
		case "hostname":
			var = hostname;
			break;
		case "db_username":
			var = db_username;
			break;
		case "db_password":
			var = db_password;
			break;
		case "db_dbservername":
			var = db_dbservername;
			break;
		case "db_port":
			var = db_port;
			break;
		case "db_Schema":
			var = db_Schema;
			break;
		case "userName":
			var = userName;
			break;
		case "password":
			var = password;
			break;
		default:
			break;
		}
		return var;
	}

	public String getCourtId() {
		return courtId;
	}

	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}

	public String getHost_name() {
		return hostname;
	}

	public void setHost_name(String host_name) {
		this.hostname = host_name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getReferral_Category() {
		return referral_Category;
	}

	public void setReferral_Category(String referral_Category) {
		this.referral_Category = referral_Category;
	}

	public String getServer_name() {
		return db_dbservername;
	}

	public void setServer_name(String server_name) {
		this.db_dbservername = server_name;
	}

	public String getDb_port() {
		return db_port;
	}

	public void setDb_port(String db_port) {
		this.db_port = db_port;
	}

	public String getDb_Schema() {
		return db_Schema;
	}

	public void setDb_Schema(String db_Schema) {
		this.db_Schema = db_Schema;
	}

	public String getRoleType() {
		return role;
	}

	public void setRoleType(String role) {
		this.role = role;
	}

	public String getBriefcaseUser() {
		return briefcaseUser;
	}

	public void setBriefcaseUser(String briefcaseUser) {
		this.briefcaseUser = briefcaseUser;
	}

}
