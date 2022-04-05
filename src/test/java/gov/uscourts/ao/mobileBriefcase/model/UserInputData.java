package gov.uscourts.ao.mobileBriefcase.model;


public class UserInputData {
	private String courtId;
	private String hostname;
	private String userName;
	private String password;
	private String environment;
	private String caseNumber;
	private String refCategory;
	private String db_servername;
	private String db_port;
	private String db_Schema;
	private String personrole;
	private String jud;
	private String db_username;
	private String db_password;
	private String stf;
	private String userType;

	public String getPacerInputData(String inputData) {
		String var = "";

		switch (inputData) {

		case "courtId":
			var = courtId;
			break;

		case "environment":
			var = environment;
			break;

		case "userName":
			var = userName;
			break;

		case "userType":
			var = userType;
			break;

		case "password":
			var = password;
			break;

		case "jud":
			var = jud;
			break;

		case "stf":
			var = stf;
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
			var = db_servername;
			break;
		case "db_port":
			var = db_port;
			break;
		case "db_Schema":
			var = db_Schema;
			break;

		case "personrole":
			var = personrole;
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
		return refCategory;
	}

	public void setReferral_Category(String refCategory) {
		this.refCategory = refCategory;
	}

	public String getServer_name() {
		return db_servername;
	}

	public void setServer_name(String server_name) {
		this.db_servername = server_name;
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

	public String getPersonrole() {
		return personrole;
	}

	public void setPersonrole(String personrole) {
		this.personrole = personrole;
	}

	public String getJud() {
		return jud;
	}

	public void setJud(String jud) {
		this.jud = jud;
	}

	public String getStf() {
		return stf;
	}

	public void setStf(String stf) {
		this.stf = stf;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
