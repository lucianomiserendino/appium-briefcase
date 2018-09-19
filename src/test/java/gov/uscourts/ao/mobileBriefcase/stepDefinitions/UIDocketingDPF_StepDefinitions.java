package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_UIDocketingDPFPage;

public class UIDocketingDPF_StepDefinitions {
	iOS_UIDocketingDPFPage page;


	@Then("^User verifies the text \"([^\"]*)\" displays in the light blue banner\\.$")
	public void user_verifies_the_text_displays_in_the_light_blue_banner(String text) {
		page = new iOS_UIDocketingDPFPage();
		page.verifyAddNewNoteDisplayed(text);
	}

	@Then("^user verifies an editable \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" fields are  displayed\\.  The default description is defined in the Default description parameter of the note DPF \\(\"([^\"]*)\" and \"([^\"]*)\"\\)$")
	public void user_verifies_an_editable_fields_are_displayed_The_default_description_is_defined_in_the_Default_description_parameter_of_the_note_DPF_and(
			String description, String comment, String submit, String dbType, String el_id) {
		page.verifyFieldsAreDisplayed(description, comment, submit, valueOf(dbType), el_id);

	}

}
