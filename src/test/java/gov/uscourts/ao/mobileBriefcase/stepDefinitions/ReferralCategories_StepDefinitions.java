package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_ReferralCategoriesPage;
import gov.uscourts.ao.mobileBriefcase.common.Servers.servers;

public class ReferralCategories_StepDefinitions {
	static iOS_ReferralCategoriesPage page;

	@Given("^User Observes the referral categories that display on the dashboard and DB \\(CMKA\\)$")
	public void user_Observes_the_referral_categories_that_display_on_the_dashboard_and_DB_CMKA() {
		page = new iOS_ReferralCategoriesPage();

		page.getServer(servers.CMKA);
	}

	@Given("^User Observes the referral categories that display on the dashboard and DB \\(CM(\\d+)A\\)$")
	public void user_Observes_the_referral_categories_that_display_on_the_dashboard_and_DB_CM_A(int arg1) {
		page = new iOS_ReferralCategoriesPage();

		page.getServer(servers.CM5A);
	}



	@Given("^User  Observes the categories on the dashboard page\\. The Petitions for Rehearing, Motions/Petitions and Screening Panels categories are all non-orally argued categories\\.$")
	public void user_Observes_the_categories_on_the_dashboard_page_The_Petitions_for_Rehearing_Motions_Petitions_and_Screening_Panels_categories_are_all_non_orally_argued_categories() {

		page = new iOS_ReferralCategoriesPage();

		page.verifyNonOrallyArgCases();

	}
}
