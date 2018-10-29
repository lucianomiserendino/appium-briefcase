package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DB_LIST_OF_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PENDING_TASK_ASSIGNMENTS;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_ReferralCategoriesPage;

public class ReferralCategories_StepDefinitions {
	static iOS_ReferralCategoriesPage page;

	@Given("^User Observes the referral categories that display on \"([^\"]*)\" and on the dashboard using  \"([^\"]*)\" \\.$")
	public void user_Observes_the_referral_categories_that_display_on_and_on_the_dashboard_using(String dbType,
			String judge) {
		page = new iOS_ReferralCategoriesPage();
		page.getReferralCategories(valueOf(dbType), getID(DB_LIST_OF_CATEGORIES, getPE_ID(valueOf(dbType), judge)));

	}

	@Then("^User  Observes the categories on db \"([^\"]*)\"  and on the dashboard page \\( \"([^\"]*)\" \\) with judgeName \"([^\"]*)\"$")
	public void user_Observes_the_categories_on_db_and_on_the_dashboard_page_with_judgeName(String dbType,
			String cmr_cyv_code, String judge) {
		page = new iOS_ReferralCategoriesPage();
		page.verifyNonOrallyArgCases(valueOf(dbType), cmr_cyv_code, getPE_ID(valueOf(dbType), judge));
	}

	@Given("^If The judge has any pending assignments it will validate the total num of pending task on UI with DB \"([^\"]*)\" and  \"([^\"]*)\"$")
	public void if_The_judge_has_any_pending_assignments_it_will_validate_the_total_num_of_pending_task_on_UI_with_DB_and(
			String dbType, String pe_id) {
		page = new iOS_ReferralCategoriesPage();
		page.getPendingTasks(valueOf(dbType), PENDING_TASK_ASSIGNMENTS, pe_id);

	}

	@Given("^In \"([^\"]*)\" , If the chm_mobile_referral\\.cmr_cyv_code = \"([^\"]*)\" , verify  cyv_category  displays on the Dashboard page\\. Verify case number don't display for  referrals where the chm_mobile_referral\\.cmr_cyv_code = 'lbrrpt', Verify only  documents display the referral detail page\\. Verify any actions, assignment, or additional case information don't  display\\.Use judge \"([^\"]*)\"$")
	public void in_If_the_chm_mobile_referral_cmr_cyv_code_verify_cyv_category_displays_on_the_Dashboard_page_Verify_case_number_don_t_display_for_referrals_where_the_chm_mobile_referral_cmr_cyv_code_lbrrpt_Verify_only_documents_display_the_referral_detail_page_Verify_any_actions_assignment_or_additional_case_information_don_t_display_Use_judge(
			String dbType, String lbrrpt, String peId) {
		page = new iOS_ReferralCategoriesPage();
		page.get_lbrrpt_CATEGORY(valueOf(dbType), lbrrpt, peId);
	}
}
