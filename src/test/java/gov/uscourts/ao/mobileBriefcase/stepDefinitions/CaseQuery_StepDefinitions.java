package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage;

public class CaseQuery_StepDefinitions {

	CaseQueryPage page;

	@Then("^User taps on magnifying glass icon and searches for case and  verifies the app returns a result$")
	public void user_taps_on_magnifying_glass_icon_and_searches_for_case_and_verifies_the_app_returns_a_result() {
		page = new CaseQueryPage();
		page.searchForACase();
	}

}
