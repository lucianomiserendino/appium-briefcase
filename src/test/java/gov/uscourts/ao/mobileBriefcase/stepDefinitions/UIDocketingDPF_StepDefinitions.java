package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_UIDocketingDPFPage;

public class UIDocketingDPF_StepDefinitions {
	iOS_UIDocketingDPFPage page;

	@Given("^User selects category \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_selects_category_and(String category, String caseNum) {
		page = new iOS_UIDocketingDPFPage();
		page.selectCase(category, caseNum);
	}

	@When("^User expands the Actions panel, selects an action from \"([^\"]*)\" using \"([^\"]*)\"$")
	public void user_expands_the_Actions_panel_selects_an_action_from_using(String dbType, String actionsPanel) {
		if (dbType.equals("CMKA")) {

			page.getActionsPanel(DBType.CMKA, actionsPanel);
		} else {
			page.getActionsPanel(DBType.CM3A, actionsPanel);
		}
	}

	@Then("^User verifies the name of the action displays in the dark blue banner,use  \"([^\"]*)\" and  \"([^\"]*)\"\\.$")
	public void user_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner_use_and(String dbType,
			String el_id) {
		if (dbType.equals("CMKA")) {

			page.verifyActionName(DBType.CMKA, el_id);
		} else {
			page.verifyActionName(DBType.CM3A, el_id);
		}
	}

	@Then("^User verifies the text \"([^\"]*)\" displays in the light blue banner\\.$")
	public void user_verifies_the_text_displays_in_the_light_blue_banner(String text) {
		page.verifyAddNewNoteDisplayed(text);
	}

	@Then("^user verifies an editable \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" fields are  displayed\\.  The default description is defined in the Default description parameter of the note DPF \\(\"([^\"]*)\" and \"([^\"]*)\"\\)$")
	public void user_verifies_an_editable_fields_are_displayed_The_default_description_is_defined_in_the_Default_description_parameter_of_the_note_DPF_and(
			String description, String comment, String submit, String dbType, String el_id) {
		if (dbType.equals("CMKA")) {

			page.verifyFieldsAreDisplayed(description, comment, submit, DBType.CMKA, el_id);
		} else {
			page.verifyFieldsAreDisplayed(description, comment, submit, DBType.CM3A, el_id);
		}

	}

}
