package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.DashboardPage;
import gov.uscourts.ao.mobileBriefcase.Pages.PendingTasksPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class PendingTasks_StepDefinitions {

	PendingTasksPage pending;
	DashboardPage page;

	@When("^Verify the sub-folder cases are sorted by date descending order$")
	public void verify_the_sub_folder_cases_are_sorted_by_date_descending_order() {
		pending = new PendingTasksPage();
		List<UserInputData> userInputData = null;
		pending.sortedInDescendingOrder(userInputData);
	}

	@Then("^User taps on sub - folder and verify the referral categories are sorted in the same order as the left-hand navigation$")
	public void user_taps_on_sub_folder_and_verify_the_referral_categories_are_sorted_in_the_same_order_as_the_left_hand_navigation() {
		pending = new PendingTasksPage();
		pending.leftNavAndPendingTasksCategoriesAreSorted();
	}

	@Then("^Verify the Pending Tasks category \\(if available\\) will always display at the top of the Dashboard$")
	public void verify_the_Pending_Tasks_category_if_available_will_always_display_at_the_top_of_the_Dashboard() {
		page = new DashboardPage();
		page.pendingTaskPosition("Dashboard");
	}

	@Then("^Verify the Pending Tasks category \\(if available\\) will always display at the top of the left navigation$")
	public void verify_the_Pending_Tasks_category_if_available_will_always_display_at_the_top_of_the_left_navigation() {
		page = new DashboardPage();
		page.pendingTaskPosition("Navigation");
	}

}
