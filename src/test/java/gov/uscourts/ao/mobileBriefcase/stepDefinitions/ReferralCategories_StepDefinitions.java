package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PENDING_TASK_ASSIGNMENTS;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.DashboardPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

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
		page.categories(table);

	}

	@Given("^If The judge has any pending assignments it will validate the total num of pending task on UI with DB\\. Use  judge's \"([^\"]*)\" and  \"([^\"]*)\" to retrieve pending tasks from db$")
	public void if_The_judge_has_any_pending_assignments_it_will_validate_the_total_num_of_pending_task_on_UI_with_DB_Use_judge_s_and_to_retrieve_pending_tasks_from_db(
			String pe_id, String PE_RT_CODE, List<UserInputData> userInputData) {
		page = new DashboardPage();

		page.getPendingTasks(getID(PENDING_TASK_ASSIGNMENTS, getPE_ID(PE_RT_CODE, pe_id, userInputData)),
				userInputData);

	}

	@Then("^User  Observes the categories on db and on the dashboard page \\( \"([^\"]*)\" \\) with judgeName \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_Observes_the_categories_on_db_and_on_the_dashboard_page_with_judgeName_and(String cmr_cyv_code,
			String judge, String PE_RT_CODE, List<UserInputData> userInputData) {
		page = new DashboardPage();
		page.verifyNonOrallyArgCases(cmr_cyv_code, getPE_ID(PE_RT_CODE, judge, userInputData), userInputData);
	}

	@Given("^In \"([^\"]*)\" , If the chm_mobile_referral\\.cmr_cyv_code = \"([^\"]*)\" , verify  cyv_category  displays on the Dashboard page\\. Verify case number don't display for  referrals where the chm_mobile_referral\\.cmr_cyv_code = 'lbrrpt', Verify only  documents display the referral detail page\\. Verify any actions, assignment, or additional case information don't  display\\.Use PE_RT_CODE \"([^\"]*)\" and judge \"([^\"]*)\"$")
	public void in_If_the_chm_mobile_referral_cmr_cyv_code_verify_cyv_category_displays_on_the_Dashboard_page_Verify_case_number_don_t_display_for_referrals_where_the_chm_mobile_referral_cmr_cyv_code_lbrrpt_Verify_only_documents_display_the_referral_detail_page_Verify_any_actions_assignment_or_additional_case_information_don_t_display_Use_PE_RT_CODE_and_judge(
			String dbType, String lbrrpt, String PE_RT_CODE, String judgeName) {
		page = new DashboardPage();
		List<UserInputData> userInputData = null;
		page.get_lbrrpt_CATEGORY(lbrrpt, PE_RT_CODE, judgeName, userInputData);
	}

	@Given("^Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page\\.$")
	public void verify_the_number_of_new_items_that_displays_in_the_red_badge_in_the_navigation_match_the_number_of_new_items_listed_on_the_Dashboard_page() {
		page = new DashboardPage();
		page.getNewReferralsCount();
	}

}
