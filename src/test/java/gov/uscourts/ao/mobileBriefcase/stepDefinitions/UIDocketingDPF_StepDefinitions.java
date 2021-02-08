package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.UIDocketingDPFPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class UIDocketingDPF_StepDefinitions {
	UIDocketingDPFPage page;

	@Then("^User verifies the text \"([^\"]*)\" displays in the light blue banner\\.$")
	public void user_verifies_the_text_displays_in_the_light_blue_banner(String text) {
		page = new UIDocketingDPFPage();
		page.verifyElementIsDisplayed(text);
	}

	@Then("^user verifies an editable \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" fields are  displayed\\.  The default description is defined in the Default description parameter of the note DPF \\(\"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\"\\)$")
	public void user_verifies_an_editable_fields_are_displayed_The_default_description_is_defined_in_the_Default_description_parameter_of_the_note_DPF(
			String description, String comment, String submit, String dbType, String dpfName, String el_id)
			 {
		List<UserInputData> userInputData = null;
		page.verifyFieldsAreDisplayed(description, comment, submit, dpfName, el_id,userInputData);
	}

}
