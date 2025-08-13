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

}
