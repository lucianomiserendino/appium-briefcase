package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cucumber.api.java.en.Given;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralCategoriesPage;

public class ReferralCategories_StepDefinitions {
	static ReferralCategoriesPage page;

	@Given("^User Observes the referral categories that display on the dashboard and DB$")
	public void user_Observes_the_referral_categories_that_display_on_the_dashboard_and_DB() {

		page = new ReferralCategoriesPage();

		List<String> DBrefCatlist = executeQuery(Queries.DB_LIST_OF_CATEGORIES);
		List<String> UIrefCatlist = page.UIreferralCategoriesList();
		Collections.sort(DBrefCatlist);
		assertEquals("-----RECORD COUNT MISMATCHED-----", UIrefCatlist, DBrefCatlist);

	}

	@Given("^If The judge has any pending assignments it will validate the total num of pending task on UI with DB$")
	public void if_The_judge_has_any_pending_assignments_it_will_validate_the_total_num_of_pending_task_on_UI_with_DB() {
		page = new ReferralCategoriesPage();
		List<String> DBPendingTasks = executeQuery(Queries.PENDING_TASK_ASSIGNMENTS);

		if (DBPendingTasks.size() > 0) {
			List<String> UIPendingTasks = Arrays.asList(page.verifyIfPendingTasksAreDisplayed());
			assertEquals("-----RECORD COUNT MISMATCHED-----", DBPendingTasks, UIPendingTasks);

		}

	}

	@Given("^User  Observes the categories on the dashboard page\\. The Petitions for Rehearing, Motions/Petitions and Screening Panels categories are all non-orally argued categories\\.$")
	public void user_Observes_the_categories_on_the_dashboard_page_The_Petitions_for_Rehearing_Motions_Petitions_and_Screening_Panels_categories_are_all_non_orally_argued_categories()
			 {

		page = new ReferralCategoriesPage();
		page.verifyNonOrallyArgCases();
	}
}
