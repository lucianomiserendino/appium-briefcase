package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.PendingTasksPage;

public class PendingTasks_StepDefinitions {

	PendingTasksPage pending;

	@When("^Verify the \"([^\"]*)\" cases are sorted by date descending order$")
	public void verify_the_cases_are_sorted_by_date_descending_order(String folder) {
		pending = new PendingTasksPage();
		pending.sortedInDescendingOrder(folder);
	}

	@Then("^User taps on \"([^\"]*)\" and verify the referral categories are sorted in the same order as the left-hand navigation$")
	public void user_taps_on_and_verify_the_referral_categories_are_sorted_in_the_same_order_as_the_left_hand_navigation(
			String folder) {
		pending = new PendingTasksPage();
		pending.leftNavAndPendingTasksCategoriesAreSorted(folder);
	}

}
