package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.*;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.AppliedCasesPage;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;

public class AppliedCases_StepDefinitions {
	CommonPages page1;
	static AppliedCasesPage page;
	static String caseN = "";

	@Then("^user checks if \"([^\"]*)\" is bookmarked$")
	public void user_checks_if_is_bookmarked(String caseNumber) {
		page = new AppliedCasesPage();
		caseN += caseNumber;
		page.getBookmarkedReferral(caseN);
	}

	@When("^User selects \"([^\"]*)\" for panel \"([^\"]*)\"$")
	public void user_selects_for_panel(String date, String panel) {
		page = new AppliedCasesPage();
		page.selectDate(date, panel);
	}

	@Then("^User verifies there is a link icon next to case \"([^\"]*)\" \\. Selects that case and bookmark the case on the referral document page$")
	public void user_verifies_there_is_a_link_icon_next_to_case_Selects_that_case_and_bookmark_the_case_on_the_referral_document_page(
			String caseNumber) {
		page.getAppliedCaseLink(caseNumber);
	}

	@Then("^User  goes back to the \"([^\"]*)\" page for \"([^\"]*)\" for panel \"([^\"]*)\" and verifies the link is there\\.$")
	public void user_goes_back_to_the_page_for_for_panel_and_verifies_the_link_is_there(String category, String date,
			String panel) {
		page1 = new CommonPages();
		page1.selectReferral("//XCUIElementTypeOther[@name='Categories']" + containsElement(category));
		page = new AppliedCasesPage();
		page.selectDate(date, panel);
		page.verifyAppliedCaseLinkIsDisplayed(caseN);
	}

	@When("^User selects an applied case \"([^\"]*)\" in the Applied cases panel, the additional case information screen displays$")
	public void user_selects_an_applied_case_in_the_Applied_cases_panel_the_additional_case_information_screen_displays(
			String caseNum) {
		page = new AppliedCasesPage();
		page.getAdditionalCaseInfoScreen(caseNum);
	}

}
