package gov.uscourts.ao.mobileBriefcase.models;

public class CourtUserAccessInfo {

	private String emailAddress;
	private String aoCode;
	private String userName;
	private String password;
	private String courtType;
	private String court;
	private String roleInCourt;
	private String reasonForAccess;
	private String pacerAccess;
	private String lastName;
	private String firstName;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAoCode() {
		return aoCode;
	}

	public void setAoCode(String aoCode) {
		this.aoCode = aoCode;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCourtType() {
		return courtType;
	}

	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getRoleInCourt() {
		return roleInCourt;
	}

	public void setRoleInCourt(String roleInCourt) {
		this.roleInCourt = roleInCourt;
	}

	public String getReasonForAccess() {
		return reasonForAccess;
	}

	public void setReasonForAccess(String reasonForAccess) {
		this.reasonForAccess = reasonForAccess;
	}

	public String getPacerAccess() {
		return pacerAccess;
	}

	public void setPacerAccess(String pacerAccess) {
		this.pacerAccess = pacerAccess;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
