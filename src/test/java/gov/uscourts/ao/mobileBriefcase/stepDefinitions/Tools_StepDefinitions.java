package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.ToolsPage;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class Tools_StepDefinitions {
	ToolsPage page;
	static chmAssignDPFPage page1;
	String caseNum = Document_StepDefinitions.regularCase;
	String category = Document_StepDefinitions.judCategory;

	@Then("^Verify the site table variable 'briefcaseDisplayTools' is being honored$")
	public void verify_the_site_table_variable_briefcaseDisplayTools_is_being_honored() {
		page = new ToolsPage();
		page.getToolsCategory();
	}

	@Then("^Verify that tapping \"([^\"]*)\" without existing clerk generate a message$")
	public void verify_that_tapping_without_existing_clerk_generate_a_message(String arg1) {
		page = new ToolsPage();
		page.applyWithoutExistingClerk();

	}

	@Then("^user taps the Tools icon, then select an Existing Law Clerk and verifies the Assignments are sorted by case number descending$")
	public void user_taps_the_Tools_icon_then_select_an_Existing_Law_Clerk_and_verifies_the_Assignments_are_sorted_by_case_number_descending() {
		List<UserInputData> userInputData = null;
		page = new ToolsPage();
		page.isSortedInDescending(userInputData);

	}
	
	@Then("^Verify that user is prevented from creating duplicate lwk assignments$")
	public void verify_that_user_is_prevented_from_creating_duplicate_lwk_assignments() {
		List<UserInputData> userInputData = null;
		page = new ToolsPage();
		page.duplicateAssignments(userInputData);
	}

}
