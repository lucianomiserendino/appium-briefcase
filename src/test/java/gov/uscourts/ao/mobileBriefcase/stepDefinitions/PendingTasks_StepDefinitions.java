package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.PendingTasksPage;

public class PendingTasks_StepDefinitions {

	PendingTasksPage pending;

	@When("^Verify the sub-folder cases are sorted by date descending order$")
	public void verify_the_sub_folder_cases_are_sorted_by_date_descending_order() {
		pending = new PendingTasksPage();
		pending.sortedInDescendingOrder();
	}

	@Then("^User taps on sub - folder and verify the referral categories are sorted in the same order as the left-hand navigation$")
	public void user_taps_on_sub_folder_and_verify_the_referral_categories_are_sorted_in_the_same_order_as_the_left_hand_navigation() {
		pending = new PendingTasksPage();
		pending.leftNavAndPendingTasksCategoriesAreSorted();
	}

}
