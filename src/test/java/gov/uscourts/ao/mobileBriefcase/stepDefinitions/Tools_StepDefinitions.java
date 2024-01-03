package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.ToolsPage;

public class Tools_StepDefinitions {
	ToolsPage page;

	@Then("^Verify the site table variable 'briefcaseDisplayTools' is being honored$")
	public void verify_the_site_table_variable_briefcaseDisplayTools_is_being_honored() {
		page = new ToolsPage();
		page.getToolsCategory();
	}

	@Then("^Verify that tapping \"([^\"]*)\" without existing clerk generate a message$")
	public void verify_that_tapping_without_existing_clerk_generate_a_message(String arg1) {
		page = new ToolsPage();

	}

}
