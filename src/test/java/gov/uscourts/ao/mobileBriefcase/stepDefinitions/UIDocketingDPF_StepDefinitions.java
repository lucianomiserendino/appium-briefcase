package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.UIDocketingDPFPage;

public class UIDocketingDPF_StepDefinitions {
	UIDocketingDPFPage page;

	@Given("^User selects Motions/Petitions \"([^\"]*)\"$")
	public void user_selects_Motions_Petitions(String caseNumber) {
		page = new UIDocketingDPFPage();
		page.getCase(caseNumber);
	}

	@When("^User expands the Actions panel, selects an action$")
	public void user_expands_the_Actions_panel_selects_an_action() {
		page.getActionsPanel();
	}

	@Then("^User verifies the name of the action displays in the dark blue banner\\.$")
	public void user_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner() {

		  page.verifyActionName();
	}

	@Then("^User verifies the text \"([^\"]*)\" displays in the light blue banner\\.$")
	public void user_verifies_the_text_displays_in_the_light_blue_banner(String addNewNote) {
		page.verifyAddNewNoteDisplayed(addNewNote);
	}

	@Then("^user verifies an editable \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" fields are  displayed\\.  The default description is defined in the Default description parameter of the note DPF$")
	public void user_verifies_an_editable_fields_are_displayed_The_default_description_is_defined_in_the_Default_description_parameter_of_the_note_DPF(
			String description, String comment, String submit) {
		page.verifyFieldsAreDisplayed(description, comment, submit);
	}

}
