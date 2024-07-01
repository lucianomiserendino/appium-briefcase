package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.AssignmentsPage;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class StaffAssignments_StepDefinitions {
	AssignmentsPage assig;
	CommonPages page;
	static String cmr_cyv_code;
	boolean displayed;

	@Then("^User observes a collapsible panel entitled \"([^\"]*)\" displays$")
	public void user_observes_a_collapsible_panel_entitled_displays(String assignment) {
		assig = new AssignmentsPage();
		displayed = assig.isAssignmentsExist();
	}

	@Then("^User verifies that the Staff Assignments are Displayed correctly and tap on a random assignment$")
	public void user_verifies_that_the_Staff_Assignments_are_Displayed_correctly() {

		List<UserInputData> userInputData = null;

		String cmr_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);

		String cmr_cs_caseid = DocumentPage.cs_caseid;

		cmr_cyv_code = DocumentPage.cmr_cyv_code;
			
		assig = new AssignmentsPage();

		assig.getAssignmentsLinkedToReferral(userInputData, cmr_cs_caseid, cmr_ju_pe_id, cmr_cyv_code);
		// assig.ifAssignmentsSorted(list, userInputData);

	}

	@Then("^verifies the information and notes that display on the page$")
	public void verifies_the_information_and_notes_that_display_on_the_page()  {
		assig = new AssignmentsPage();
		assig.verifyAssignmentInfoAndNoteDatesDisplayed();
	}

}
