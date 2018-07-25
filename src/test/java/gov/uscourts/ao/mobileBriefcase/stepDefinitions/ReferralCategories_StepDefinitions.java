package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PENDING_TASK_ASSIGNMENTS;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_ReferralCategoriesPage;

public class ReferralCategories_StepDefinitions {
	static iOS_ReferralCategoriesPage page;

	@Given("^User Observes the referral categories that display on \"([^\"]*)\" and on the dashboard using  \"([^\"]*)\" \\.$")
	public void user_Observes_the_referral_categories_that_display_on_and_on_the_dashboard_using(String dbType,
			String judge) {
		page = new iOS_ReferralCategoriesPage();
		if (dbType.equals("CMKA")) {
			page.getReferralCategories(DBType.CMKA, getPE_ID(DBType.CMKA, judge));
		} else {
			page.getReferralCategories(DBType.CM3A, getPE_ID(DBType.CM3A, judge));
		}
	}
	
	
	@Then("^User  Observes the categories on db \"([^\"]*)\"  and on the dashboard page  with judgeName \"([^\"]*)\"$")
	public void user_Observes_the_categories_on_db_and_on_the_dashboard_page_with_judgeName(String dbType, String judge) {
		page = new iOS_ReferralCategoriesPage();
		if (dbType.equals("CMKA")) {
			page.verifyNonOrallyArgCases(DBType.CMKA, getPE_ID(DBType.CMKA, judge));
		} else {
			page.verifyNonOrallyArgCases(DBType.CM3A, getPE_ID(DBType.CM3A, judge));
		}
	}
	
	

	@Given("^If The judge has any pending assignments it will validate the total num of pending task on UI with DB \"([^\"]*)\" and  \"([^\"]*)\"$")
	public void if_The_judge_has_any_pending_assignments_it_will_validate_the_total_num_of_pending_task_on_UI_with_DB_and(String dbType, String pe_id) {
		page = new iOS_ReferralCategoriesPage();
		if (dbType.equals("CMKA")) {
			page.getPendingTasks(DBType.CMKA,PENDING_TASK_ASSIGNMENTS,  pe_id);
		} else {
			page.getPendingTasks(DBType.CM3A,PENDING_TASK_ASSIGNMENTS,  pe_id);
		}
	}


}
