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

public class MultipleDPFs_StepDefinitions {
	static CommonPages page;
	static chmAssignDPFPage page1;

	@Then("^Create a staff assignment, add a note and then vote and add notes to your votes\\.$")
	public void create_a_staff_assignment_add_a_note_and_then_vote_and_add_notes_to_your_votes() {

		page = new CommonPages();
		List<UserInputData> userInputData = null;
		assertTrue(isDisplayed(containsElement("Assignments")));
		scrollDownIfNotDisplayed("(" + containsElement("NewStaffButton") + ")[1]");
		page.verifyElementIsDisplayed("Create Assignment");
		page1 = new chmAssignDPFPage();
		page1.createNewStaffAssignment(Document_StepDefinitions.referral,userInputData);
	}
}
