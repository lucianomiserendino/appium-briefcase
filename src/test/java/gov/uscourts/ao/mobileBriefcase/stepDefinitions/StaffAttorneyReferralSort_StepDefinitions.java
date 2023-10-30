package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.StaffAttorneyReferralSortPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class StaffAttorneyReferralSort_StepDefinitions {

	StaffAttorneyReferralSortPage page;
	
	
	@Then("^User selects a sort option and verifies the items on the page is sorted accordingly$")
	public void user_selects_a_sort_option_and_verifies_the_items_on_the_page_is_sorted_accordingly() {
		List<UserInputData> userInputData = null;
		page = new StaffAttorneyReferralSortPage();
		page.selectSortOption("Default", userInputData);
		page.selectSortOption("Case Number", userInputData);
		page.selectSortOption("Status", userInputData);
		page.selectSortOption("Referred", userInputData);
	}
}
