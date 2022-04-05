package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PENDING_TASK_ASSIGNMENTS;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.Pages.DashboardPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ReferralCategories_StepDefinitions {
	static DashboardPage page;

	@Then("^user taps on left-hand navigation \"([^\"]*)\" arrows$")
	public void user_taps_on_left_hand_navigation_arrows(String expand_collapse) {
		tap(contains(expand_collapse));
	}

	@Then("^User observes the referral categories that display on the dashboard\\. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user\\.$")
	public void user_observes_the_referral_categories_that_display_on_the_dashboard_Query_the_chm_mobile_referral_and_chm_reftype_val_table_to_get_valid_categories_for_the_logged_in_user(
			List<UserInputData> table) {
		page = new DashboardPage();
		page.getRefCategories(table);

	}

	@Given("^If The judge has any pending assignments it will validate the total num of pending task on UI with DB\\. Use  judge's \"([^\"]*)\" and  \"([^\"]*)\" to retrieve pending tasks from db$")
	public void if_The_judge_has_any_pending_assignments_it_will_validate_the_total_num_of_pending_task_on_UI_with_DB_Use_judge_s_and_to_retrieve_pending_tasks_from_db(
			String pe_id, String PE_RT_CODE, List<UserInputData> userInputData) {
		page = new DashboardPage();
		page.getPendingTasks(getID(PENDING_TASK_ASSIGNMENTS, getPE_ID(PE_RT_CODE, pe_id, userInputData)),
				userInputData);

	}

	@Then("^I find the valid non-orally argued categories for the judge$")
	public void i_find_the_valid_non_orally_argued_categories_for_the_judge(List<UserInputData> userInputData) {
		page = new DashboardPage();
		String judge = SystemPropertySetup.getJudge(userInputData);
		page.verifyNonOrallyArgCases("lbrrpt", getPE_ID("jud", judge, userInputData), userInputData);
	}

	@Then("^I verify that the referral detail page only displays documents if chm_mobile_referral\\.cmr_cyv_code = lbrrpt$")
	public void i_verify_that_the_referral_detail_page_only_displays_documents_if_chm_mobile_referral_cmr_cyv_code_lbrrpt(
			List<UserInputData> userInputData) {
		page = new DashboardPage();
		String judge = SystemPropertySetup.getJudge(userInputData);
		page.get_lbrrpt_CATEGORY("lbrrpt", "jud", judge, userInputData);
	}

	@Given("^Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page\\.$")
	public void verify_the_number_of_new_items_that_displays_in_the_red_badge_in_the_navigation_match_the_number_of_new_items_listed_on_the_Dashboard_page() {
		page = new DashboardPage();
		page.getNewReferralsCount();
	}

}
