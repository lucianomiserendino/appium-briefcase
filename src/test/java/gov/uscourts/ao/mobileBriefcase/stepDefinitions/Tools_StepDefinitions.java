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

	@Then("^User ensures there is a law clerk assignment of a specific type in a specific referral$")
	public void user_ensures_there_is_a_law_clerk_assignment_of_a_specific_type_in_a_specific_referral() {

		List<UserInputData> userInputData = null;
		page1 = new chmAssignDPFPage();
		page1.existing = false;
		page1.createNewSTF(caseNum, category, userInputData);
		page1.get_cha_id(caseNum, category, userInputData);
	}

	@Then("^User ensures there is another law clerk assignment in the same referral of the same type, but assigned to a different law clerk$")
	public void user_ensures_there_is_another_law_clerk_assignment_in_the_same_referral_of_the_same_type_but_assigned_to_a_different_law_clerk() {

		List<UserInputData> userInputData = null;
		page1 = new chmAssignDPFPage();
		page1.existing = true;
		page1.createNewSTF(caseNum, category, userInputData);
		page1.get_cha_id(caseNum, category, userInputData);

	}

	@Then("^Navigates to Tools category and selects one of the existing law clerks$")
	public void navigates_to_Tools_category_and_selects_one_of_the_existing_law_clerks() {
		page = new ToolsPage();

		page.selectExistingClerk(page1.staffMembers.get(0));
		page.submitDublicatedAssignment(caseNum,  page1.staffMembers.get(1));
	}

}
