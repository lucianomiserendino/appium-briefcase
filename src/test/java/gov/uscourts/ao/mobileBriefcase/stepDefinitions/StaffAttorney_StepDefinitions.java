package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.StaffAttorneyReferralPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class StaffAttorney_StepDefinitions {
	StaffAttorneyReferralPage page;

	@Given("^User verifies correct assignment categories  display on the dashboard for Staff Attorneys$")
	public void user_verifies_correct_assignment_categories_display_on_the_dashboard_for_Staff_Attorneys() {
		List<UserInputData> userInputData = null;
		page = new StaffAttorneyReferralPage();
		page.verifyDataOnTheDashboard(userInputData);
	}

	@When("^User observes correct referral categories listed for Staff Attorney$")
	public void user_observes_correct_referral_categories_listed_for_Staff_Attorney() {
		List<UserInputData> userInputData = null;
		page = new StaffAttorneyReferralPage();
		page.osberveReferralCategories(userInputData);
	}

	@Given("^User selects assignment type \"([^\"]*)\"$")
	public void user_selects_assignment_type(String assignmentType) {
		page = new StaffAttorneyReferralPage();
		page.selectAssignmentType(assignmentType);
	}

	@Then("^User selects category \"([^\"]*)\" and \"([^\"]*)\" ,SMR_ASSIGN_PE_ID : \"([^\"]*)\"$")
	public void user_selects_category_and_SMR_ASSIGN_PE_ID(String category, String caseNum, String smr_assign_pe_id) {
		page = new StaffAttorneyReferralPage();
		page.tapOnReferralCategory(category, caseNum, smr_assign_pe_id);
	}

	@Then("^After selecting \"([^\"]*)\" , user verifies the document categories and the number of docs displayed for each category matches the number of docs in the DB \"([^\"]*)\"\\. smr_assign_pe_id: \"([^\"]*)\"$")
	public void after_selecting_user_verifies_the_document_categories_and_the_number_of_docs_displayed_for_each_category_matches_the_number_of_docs_in_the_DB_smr_assign_pe_id(
			String category, String dbType, String smr_assign_pe_id) {
		page.getCategories(valueOf(dbType), category, smr_assign_pe_id);
	}

	@Then("^User selects a sort option and verifies the items on the page is sorted accordingly$")
	public void user_selects_a_sort_option_and_verifies_the_items_on_the_page_is_sorted_accordingly() {
		List<UserInputData> userInputData = null;
		page = new StaffAttorneyReferralPage();
		//page.selectSortOption("Default", userInputData);
		page.selectSortOption("Case Number", userInputData);

	}

}
