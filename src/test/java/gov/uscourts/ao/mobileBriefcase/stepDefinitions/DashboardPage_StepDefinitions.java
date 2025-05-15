package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PENDING_TASK_ASSIGNMENTS;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.DashboardPage;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;

public class DashboardPage_StepDefinitions {
	static DashboardPage page;

	@Then("^user taps on left-hand navigation \"([^\"]*)\" arrows$")
	public void user_taps_on_left_hand_navigation_arrows(String expand_collapse) {
		//tap(contains(expand_collapse));
		page = new DashboardPage();
		tap(page.collapseBtn);

	}


	@Given("^If The judge has any pending assignments it will validate the total num of pending task on UI with DB\\.$")
	public void if_The_judge_has_any_pending_assignments_it_will_validate_the_total_num_of_pending_task_on_UI_with_DB_Use_judge_s_and_to_retrieve_pending_tasks_from_db(
			 List<UserInputData> userInputData) {
		page = new DashboardPage();
		page.getPendingTasks(getID(PENDING_TASK_ASSIGNMENTS, DocumentPage.get_pe_id("jud",userInputData)), userInputData);

	}

	@Then("^I find the valid non-orally argued categories for the judge$")
	public void i_find_the_valid_non_orally_argued_categories_for_the_judge(List<UserInputData> userInputData) {
		page = new DashboardPage();
		page.verifyNonOrallyArgCases("lbrrpt", DocumentPage.get_pe_id("jud",userInputData), userInputData);
	}


}
