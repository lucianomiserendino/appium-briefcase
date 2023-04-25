package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.AssignmentsPage;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class A_chmAssign_StepDefs {
	AssignmentsPage assig;
	CommonPages page;
	String cmr_cyv_code;
	boolean displayed;

	@Then("^User observes a collapsible panel entitled \"([^\"]*)\" displays$")
	public void user_observes_a_collapsible_panel_entitled_displays(String assignment) {
		assig = new AssignmentsPage();
		displayed = assig.isAssignmentsExist();
	}

	@Then("^User verifies that the Staff Assignments are Displayed correctly$")
	public void user_verifies_that_the_Staff_Assignments_are_Displayed_correctly() {
		if (displayed == true) {
			String caseNum = Document_StepDefinitions.regularCase;

			List<UserInputData> userInputData = null;

			String cmr_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);

			String cmr_cs_caseid = CommonPages.getCaseID(caseNum, userInputData);

			cmr_cyv_code = CommonPages.cmr_cyv_code(Document_StepDefinitions.judCategory, cmr_cs_caseid, userInputData)
					.trim();
			assig = new AssignmentsPage();
			List<String> list = assig.getAssignmentsLinkedToReferral(userInputData, cmr_cs_caseid, cmr_ju_pe_id,
					cmr_cyv_code);
			assig.ifAssignmentsSorted(list, userInputData);

		} else {

		}
	}

}
