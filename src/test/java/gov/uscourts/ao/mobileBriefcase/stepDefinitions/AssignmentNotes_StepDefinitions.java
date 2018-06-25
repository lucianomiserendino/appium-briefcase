package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.AssignmentNotesPage;

public class AssignmentNotes_StepDefinitions {

	AssignmentNotesPage page;

	@Given("^User selects \"([^\"]*)\"$")
	public void user_selects(String assignment) {
		page = new AssignmentNotesPage();
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

	@Given("^User selects Judge Colloton >> Motions/Petitions >> case \"([^\"]*)\"$")
	public void user_selects_Judge_Colloton_Motions_Petitions_case(String caseNum) {
		page = new AssignmentNotesPage();
		page.selectAUser();
		page.getCase(caseNum);
	}

	@Then("^User  observes a collapsible panel entitled \"([^\"]*)\" displays and expands the Assignments panel$")
	public void user_observes_a_collapsible_panel_entitled_displays_and_expands_the_Assignments_panel(
			String assignmentPanel) {
		page.verifyAssignmentIsDisplayed(assignmentPanel);
	}

	@Then("^User observes there is an assignment for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_observes_there_is_an_assignment_for_and(String assignmentForKyle, String assignmentForEssley) {
		page.getAssignmentLinkedtoTheReferral(assignmentForKyle, assignmentForEssley);

	}

	@And("^User observes there is an assignment for \"([^\"]*)\"$")
	public void user_observes_there_is_an_assignment_for(String assignmentForCourtney) {
		page.getAssignmentLinkedtoCase(assignmentForCourtney);

	}

}
