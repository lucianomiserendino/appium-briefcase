package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_AssignmentNotesPage;

public class AssignmentNotes_StepDefinitions {

	iOS_AssignmentNotesPage page;

	@When("^User selects Judge, then   \"([^\"]*)\" and  \"([^\"]*)\"$")
	public void user_selects_Judge_then_and(String category, String caseNum)  {
		page = new iOS_AssignmentNotesPage();
		page.selectCase(category, caseNum);

	}
	
	@Then("^User  observes a collapsible panel entitled \"([^\"]*)\" is displayed and expands the Assignments panel$")
	public void user_observes_a_collapsible_panel_entitled_is_displayed_and_expands_the_Assignments_panel(String assignmentOnReferral)  {
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



}
