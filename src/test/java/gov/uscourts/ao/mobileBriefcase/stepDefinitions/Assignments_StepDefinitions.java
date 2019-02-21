package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_AssignmentsPage;

public class Assignments_StepDefinitions {

	iOS_AssignmentsPage page;

	@Then("^User  observes a collapsible panel entitled \"([^\"]*)\" displays and expands the Assignments panel$")
	public void user_observes_a_collapsible_panel_entitled_displays_and_expands_the_Assignments_panel(
			String assignmentPanel) {
		CommonPages.getPanel(Panel.valueOf(assignmentPanel));
	}

	@Then("^User observes there is an assignment for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_observes_there_is_an_assignment_for_and(String assignmentForKyle, String assignmentForEssley) {
		page = new iOS_AssignmentsPage();
		page.getAssignmentLinkedtoTheReferral(assignmentForKyle, assignmentForEssley);

	}

	@Then("^User Observes there is an assignment for \"([^\"]*)\" \\. And verifies this fields in db \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_Observes_there_is_an_assignment_for_And_verifies_this_fields_in_db(String assignmentForCourtney,
			String dbtype, String cmr_cs_caseid, String cha_ju_pe_id, String chd_cha_id) {
		page.getAssignmentLinkedtoCase(assignmentForCourtney, valueOf(dbtype), cmr_cs_caseid, cha_ju_pe_id, chd_cha_id);
	}

}