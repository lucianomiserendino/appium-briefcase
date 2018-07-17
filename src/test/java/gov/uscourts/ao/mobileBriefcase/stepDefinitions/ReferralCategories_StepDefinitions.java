package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_ReferralCategoriesPage;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.*;

public class ReferralCategories_StepDefinitions {
	static iOS_ReferralCategoriesPage page;

	@Given("^User Observes the referral categories that display on the dashboard and DB \\(CMKA\\)$")
	public void user_Observes_the_referral_categories_that_display_on_the_dashboard_and_DB_CMKA() {

		page = new iOS_ReferralCategoriesPage();
		page.getReferralCategories(DBType.CMKA, getPE_ID(DBType.CMKA, JUDGE_COLLOTON));
	}

	@Given("^User Observes the referral categories that display on the dashboard and DB \\(CM(\\d+)A\\)$")
	public void user_Observes_the_referral_categories_that_display_on_the_dashboard_and_DB_CM_A(int arg1) {

		page = new iOS_ReferralCategoriesPage();
		page.getReferralCategories(DBType.CM5A, getPE_ID(DBType.CM5A, JUDGE_WILLIAMS));
	}

	@Then("^User  Observes the categories on the dashboard page \\(CMKA\\)$")
	public void user_Observes_the_categories_on_the_dashboard_page_CMKA() {

		page = new iOS_ReferralCategoriesPage();
		page.verifyNonOrallyArgCases(DBType.CMKA, getPE_ID(DBType.CMKA, JUDGE_COLLOTON));
	}

	@Then("^User  Observes the categories on the dashboard page \\(CM(\\d+)A\\)$")
	public void user_Observes_the_categories_on_the_dashboard_page_CM_A(int arg1) {

		page = new iOS_ReferralCategoriesPage();
		page.verifyNonOrallyArgCases(DBType.CM5A, getPE_ID(DBType.CM5A, JUDGE_WILLIAMS));
	}

}
