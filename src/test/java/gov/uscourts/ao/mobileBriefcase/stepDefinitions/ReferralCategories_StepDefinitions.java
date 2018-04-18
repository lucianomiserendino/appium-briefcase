package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilits.executeQuery;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import cucumber.api.java.en.Given;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralCategoriesPage;

public class ReferralCategories_StepDefinitions {
	ReferralCategoriesPage p;

	@Given("^User Observes the referral categories that display on the dashboard and DB$")
	public void user_Observes_the_referral_categories_that_display_on_the_dashboard_and_DB() {

		p = new ReferralCategoriesPage();
		List<String> DBrefCatlist = executeQuery(Queries.DB_LIST_OF_CATEGORIES);
		List<String> UIrefCatlist = p.UIreferralCategoriesList();
		Collections.sort(DBrefCatlist);
		assertEquals(UIrefCatlist, DBrefCatlist);

	}

}
