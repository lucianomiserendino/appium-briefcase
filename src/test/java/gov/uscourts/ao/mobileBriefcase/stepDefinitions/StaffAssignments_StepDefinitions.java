package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_StaffAssignmentsPage;

public class StaffAssignments_StepDefinitions {

	iOS_StaffAssignmentsPage page;

	@Then("^User  observes a collapsible panel entitled \"([^\"]*)\" displays and expands the Assignments panel$")
	public void user_observes_a_collapsible_panel_entitled_displays_and_expands_the_Assignments_panel(
			String assignments) {
		page = new iOS_StaffAssignmentsPage();
		page.verifyAssignmentIsDisplayed(assignments);
	}

	@Then("^User observes there is an assignment for \"([^\"]*)\" , \"([^\"]*)\",  where  cmr_cs_caseid equals\"([^\"]*)\" ,cha_ju_pe_id equals \"([^\"]*)\" and chd_cha_id equals \"([^\"]*)\"$")
	public void user_observes_there_is_an_assignment_for_where_cmr_cs_caseid_equals_cha_ju_pe_id_equals_and_chd_cha_id_equals(
			String assignment, String dbtype, String cmr_cs_caseid, String cha_ju_pe_id, String chd_cha_id) {
		page.getAssignmentLinkedtoCase(assignment, valueOf(dbtype), cmr_cs_caseid, cha_ju_pe_id, chd_cha_id);

	}

}
