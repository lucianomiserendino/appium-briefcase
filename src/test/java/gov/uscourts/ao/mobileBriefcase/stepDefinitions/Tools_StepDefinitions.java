package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.Pages.ToolsPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Then;

public class Tools_StepDefinitions {
	ToolsPage page;

	@Then("^Verify that user is prevented from creating duplicate lwk assignments$")
	public void verify_that_user_is_prevented_from_creating_duplicate_lwk_assignments() {
		List<UserInputData> userInputData = null;
		page = new ToolsPage();
		page.duplicateAssignments(userInputData);
	}

	@Then("user selects an Existing Law Clerk from Tools, opens the target case, and verifies the referral detail page.")
	public void user_selects_an_existing_law_clerk_from_tools_opens_the_target_case_and_verifies_the_referral_detail_page() {
		page = new ToolsPage();
	 assertTrue("Law Clerk Reassign - assignment link directs to applied case", page.isNavigatedToTargetCasePage());
	}
	
	@Then("user selects an existing clerk, then confirms that clerk’s name does not appear in the “Select a New Clerk” menu")
	public void user_selects_an_existing_clerk_then_confirms_that_clerk_s_name_does_not_appear_in_the_select_a_new_clerk_menu() {
		
		page = new ToolsPage();
		assertTrue("Able to select the same law clerk from both existing and new lwk menus",page.isExistingClerkExcludedFromNewClerkList());
	}




}
