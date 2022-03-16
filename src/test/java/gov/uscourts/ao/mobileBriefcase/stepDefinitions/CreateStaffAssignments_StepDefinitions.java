package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.Assignment;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;

public class CreateStaffAssignments_StepDefinitions extends Base {
	static CommonPages page;
	static chmAssignDPFPage page1;
	String staffMember = "";



@Then("^user creates a new assignment, checks the back-end, edits existing assignment and verifies db is updated properly$")
public void user_creates_a_new_assignment_checks_the_back_end_edits_existing_assignment_and_verifies_db_is_updated_properly() {
	page = new CommonPages();
	List<UserInputData> userInputData = null;
	assertTrue(isDisplayed(containsElement("Assignments")));
	scrollDownIfNotDisplayed("(" + containsElement("NewStaffButton") + ")[1]");
	page.verifyElementIsDisplayed("Create Assignment");
	page1 = new chmAssignDPFPage();
	page1.createNewStaffAssignment(
			userInputData);
}

	

	@Then("^User clicks on create New Staff Assignment$")
	public void user_clicks_on_create_New_Staff_Assignment() {
		page = new CommonPages();
		assertTrue(isDisplayed(containsElement("Assignments")));
		scrollDownIfNotDisplayed("(" + containsElement("NewStaffButton") + ")[1]");
		page.verifyElementIsDisplayed("Create Assignment");

	}

	@Then("^User selects a staff member, assignment type, and at least one date, \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"\\);$")
	public void user_select_a_staff_member_assignment_type_and_at_least_one_date(String dbType, String dpfName,
			String elId, String cha_ju_pe_id, String cmr_cyv_code, String cmr_cs_caseid, String caseNumber) {
		page1 = new chmAssignDPFPage();
		List<UserInputData> userInputData = null;
		page1.createStaffAssignment(Assignment.NEW, dpfName, elId, cha_ju_pe_id, cmr_cyv_code, cmr_cs_caseid,
				caseNumber, userInputData);
	}

	@Then("^User selects the same staff member, a different assignment type, and at least one date, \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"\\);$")
	public void user_selects_the_same_staff_member_a_different_assignment_type_and_at_least_one_date(String dbType,
			String dpfName, String elId, String cha_ju_pe_id, String cmr_cyv_code, String cmr_cs_caseid,
			String caseNumber) {
		page1 = new chmAssignDPFPage();
		List<UserInputData> userInputData = null;
		page1.createStaffAssignment(Assignment.EXISTING, dpfName, elId, cha_ju_pe_id, cmr_cyv_code, cmr_cs_caseid,
				caseNumber, userInputData);
		page1.submiTransaction();
	}

}
