package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_AssignmentNotesPage;

public class AssignmentNotes_StepDefinitions {

	iOS_AssignmentNotesPage page;

	@Given("^User selects \"([^\"]*)\"$")
	public void user_selects(String assignment) {
		page = new iOS_AssignmentNotesPage();
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
		page = new iOS_AssignmentNotesPage();
		page.selectAUser();
		page.getCase(caseNum);
	}





}
