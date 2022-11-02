package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;

public class CreateStaffAssignments_StepDefinitions extends Base {
	static CommonPages page;
	static chmAssignDPFPage page1;
	String staffMember = "";
	String caseNum = Document_StepDefinitions.regularCase;
	String categroy = Document_StepDefinitions.categroy;

	@Then("^user  verifies that briefcase events include the chmSilentAssign DPF$")
	public void user_verifies_that_briefcase_events_include_the_chmSilentAssign_DPF(List<UserInputData> userInputData) {
		getInstance(Driver.IOS);
		page = new CommonPages();
		assertTrue(isDisplayed(containsElement("Assignments")));
		scrollDownIfNotDisplayed("(" + containsElement("NewStaffButton") + ")[1]");
		page.verifyElementIsDisplayed("Create Assignment");

		page1 = new chmAssignDPFPage();

		page1.getCaseDetails(caseNum, categroy, userInputData);
		page1.createNewSTF(caseNum, categroy, userInputData);
		page1.get_cha_id(caseNum, categroy, userInputData);
		page1.modifySTF(caseNum, categroy, userInputData);
	}

}
