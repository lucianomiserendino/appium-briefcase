package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;

public class CreateStaffAssignments_StepDefinitions extends Base {
	static CommonPages page;
	static chmAssignDPFPage page1;
	String staffMember = "";

	@Then("^user creates a new assignment, checks the back-end, edits existing assignment and checks the db  by using \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"\\);$")
	public void user_creates_a_new_assignment_checks_the_back_end_edits_existing_assignment_and_checks_the_db_by_using(
			String dbType, String dpfName, String actionElID, String cha_ju_pe_id, String cmr_cyv_code,
			String cmr_cs_caseid, String caseNum) {
		page = new CommonPages();
		assertTrue(isDisplayed(containsElement("Assignments")));
		scrollDownIfNotDisplayed("(" + containsElement("NewStaffButton") + ")[1]");
		page.verifyElementIsDisplayed("Create Assignment");
		page1 = new chmAssignDPFPage();
		page1.createNewStaffAssignment(valueOf(dbType), dpfName, actionElID, cha_ju_pe_id, cmr_cyv_code, cmr_cs_caseid,
				caseNum);
	}
	
	
	
	
	
	

}
