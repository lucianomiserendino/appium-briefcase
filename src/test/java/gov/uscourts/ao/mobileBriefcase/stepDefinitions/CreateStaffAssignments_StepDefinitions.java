package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.DPFs;
import gov.uscourts.ao.mobileBriefcase.page.common.DPFs.DPF;

public class CreateStaffAssignments_StepDefinitions extends Base {
	static CommonPages page;
	static chmAssignDPFPage page1;
	String staffMember = "";
	String caseNum = DocumentPage.caseNum;
	String category =DocumentPage.category;
     String action=DPF_stepDefinitions.actionName;
	@Then("^user  verifies that briefcase events include the chmAssign DPF$")
	public void user_verifies_that_briefcase_events_include_the_chmSilentAssign_DPF(List<UserInputData> userInputData) {
		
		page = new CommonPages();
		assertTrue(isDisplayed(containsElement("Assignments")));
		scrollDownIfNotDisplayed("(" + containsElement("NewStaffButton") + ")[1]");
		page.verifyElementIsDisplayed("Create Assignment");

		page1 = new chmAssignDPFPage();

		page1.getCaseDetails(caseNum, category, action, userInputData);
	}

	@Then("^User creates a new staff assignment$")
	public void user_creates_a_new_staff_assignment(List<UserInputData> userInputData) {

		page1 = new chmAssignDPFPage();
		page1.existing = false;
		page1.createNewSTF(caseNum, category, userInputData);
		page1.get_cha_id(caseNum, category, userInputData);

	}

	@Then("^User edits existing staff assignment$")
	public void user_edits_existing_staff_assignment(List<UserInputData> userInputData) {
		page1 = new chmAssignDPFPage();
		page1.modifySTF(caseNum, category, userInputData);
	}

	@Then("^User terminates the assignment$")
	public void user_terminates_the_assignment(List<UserInputData> userInputData) {
		page1 = new chmAssignDPFPage();
		page1.terminateStaffAssignment(userInputData);

	}

	@Then("^User verifies that Briefcase supports the chmAssignText TPF$")
	public void user_verifies_that_Briefcase_supports_the_chmAssignText_TPF() {
		page1 = new chmAssignDPFPage();
		page1.verifyChmAssignTextSupport();
	}

	@Then("^User modifies the existing dates and completes the assignment in the same transaction$")
	public void user_modifies_the_existing_dates_and_completes_the_assignment_in_the_same_transaction() {
		List<UserInputData> userInputData = null;
		 page1 = new chmAssignDPFPage();
		 page1.modify_terminate_STF(caseNum, category, userInputData);
		 

	}
	


}
