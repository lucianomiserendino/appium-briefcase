package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CitelinkSettings;

public class CitelinkSettings_StepDefinitions {
	CitelinkSettings page;

	@Then("^Verify the cached documents are not deleted and the user is presented with the message if the cite link settings change$")
	public void verify_the_cached_documents_are_not_deleted_and_the_user_is_presented_with_the_message_if_the_cite_link_settings_change() {
		page = new CitelinkSettings();
		page.changesCitelinkSettings();
	}

}
