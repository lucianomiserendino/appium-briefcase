package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_chmAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;

public class CreateStaffAssignments_StepDefinitions extends Base {
	CommonPages page;
	iOS_chmAssignDPFPage page1;
	String staffMember = "";

	@Then("^user creates a new assignment, checks the back-end, edits existing assignment and checks the db  by using \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"\\);$")
	public void user_creates_a_new_assignment_checks_the_back_end_edits_existing_assignment_and_checks_the_db_by_using(
			String dbType, String actionElID, String cha_ju_pe_id, String cmr_cyv_code, String cmr_cs_caseid,
			String caseNum) {
		page = new CommonPages();
		page.verifyElementIsDisplayed("Assignments");
		page.verifyElementIsDisplayed("New Staff Assignment");
		page.verifyElementIsDisplayed("Submit");
		contains("New Staff Assignment").click();
		page.verifyElementIsDisplayed("Create Assignment");
		page1 = new iOS_chmAssignDPFPage();
		page1.createNewStaffAssignment(valueOf(dbType), actionElID, cha_ju_pe_id, cmr_cyv_code, cmr_cs_caseid, caseNum);

	}

}
