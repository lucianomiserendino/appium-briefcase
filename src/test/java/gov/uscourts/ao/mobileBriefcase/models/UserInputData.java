package gov.uscourts.ao.mobileBriefcase.models;

public class UserInputData {

	private String userName;
	private String password;
	private String courtId;
	private String roleType;
	private String environment;
	private String caseNumber;
	private String referralCategory;
	
	
	public String getReferralCategory() {
		return referralCategory;
	}

	public void setReferralCategory(String referralCategory) {
		this.referralCategory = referralCategory;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleType() {
		return roleType;
	}


	public String getCourtId() {
		return courtId;
	}

	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String briefUserName) {
		this.userName = briefUserName;
	}

	public String getPasswd() {
		return password;
	}

	public void setPasswd(String briefPwd) {
		this.password = briefPwd;
	}



}
