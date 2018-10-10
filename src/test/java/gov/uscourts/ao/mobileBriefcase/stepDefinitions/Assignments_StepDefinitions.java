package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_AssignmentsPage;

public class Assignments_StepDefinitions {

	iOS_AssignmentsPage page;

	
	@Then("^User  observes a collapsible panel entitled \"([^\"]*)\" displays and expands the Assignments panel$")
	public void user_observes_a_collapsible_panel_entitled_displays_and_expands_the_Assignments_panel(String assignmentOnReferral) {
		page = new iOS_AssignmentsPage();
		page.verifyAssignmentIsDisplayed(assignmentOnReferral);
	}


	@Given("^User selects \"([^\"]*)\"$")
	public void user_selects(String assignment) {
		page.selectAssignment(assignment);
	}

	@Then("^User verifies the \"([^\"]*)\" banner displays$")
	public void user_verifies_the_banner_displays(String assignmentNotes) {
		page.verifyAssignmentNotes(assignmentNotes);
	}

	@Then("^User verifies the date of the assignment note displays,left justified$")
	public void user_verifies_the_date_of_the_assignment_note_displays_left_justified() {
		page.verifyAssignmentDates();

	}

	@Then("^User verifies the note description displays next to the date$")
	public void user_verifies_the_note_description_displays_next_to_the_date() {
		page.verifyNoteDescription();
	}

	@Then("^User verifies text displays under the description$")
	public void user_verifies_text_displays_under_the_description() {
		page.verifyText();
	}

	@Then("^User observes there is an assignment for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_observes_there_is_an_assignment_for_and(String assignmentForKyle, String assignmentForEssley) {
		page = new iOS_AssignmentsPage();
		page.getAssignmentLinkedtoTheReferral(assignmentForKyle, assignmentForEssley);
	}

	@Then("^User observes there is an assignment for \"([^\"]*)\" , \"([^\"]*)\",  where  cmr_cs_caseid equals\"([^\"]*)\" ,cha_ju_pe_id equals \"([^\"]*)\" and chd_cha_id equals \"([^\"]*)\"$")
	public void user_observes_there_is_an_assignment_for_where_cmr_cs_caseid_equals_cha_ju_pe_id_equals_and_chd_cha_id_equals(
			String assignment, String dbtype, String cmr_cs_caseid, String cha_ju_pe_id, String chd_cha_id) {
		page.getAssignmentLinkedtoCase(assignment, valueOf(dbtype), cmr_cs_caseid, cha_ju_pe_id, chd_cha_id);

	}

}
