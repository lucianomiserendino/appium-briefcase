package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.Constants.CASE_NUM_CM5A;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.CASE_NUM_CMKA;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.CMR_ID_CM5A;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.CMR_ID_CMKA;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_ActionsPanelPage;

public class ActionsPanel_StepDefinitions {
	iOS_ActionsPanelPage page;

	@When("^User selects Judge \"([^\"]*)\"  >> Motions/Petitions >> and anycase$")
	public void user_selects_Judge_Motions_Petitions_and_anycase(String arg1) {
		page = new iOS_ActionsPanelPage();
		page.selectCaseNumber(iOS_ActionsPanelPage.motoionCMKA, CASE_NUM_CMKA);
	}

	@Then("^User verifies \"([^\"]*)\" is diplayed and  expands the Actions panel \\(CMKA\\)$")
	public void user_verifies_is_diplayed_and_expands_the_Actions_panel_CMKA(String actionsPanel) {
		page.verifyActionsPanelIsDisplayed(DBType.CMKA, actionsPanel);
	}

	@Then("^User verifies the correct actions display for the selected referral \\(CMKA\\)$")
	public void user_verifies_the_correct_actions_display_for_the_selected_referral_CMKA() {
		page.compareApplicableActions(DBType.CMKA, CMR_ID_CMKA);
	}

	@When("^User selects \"([^\"]*)\"  >> Motion/Petition >> and anycase$")
	public void user_selects_Motion_Petition_and_anycase(String arg1) {
		page = new iOS_ActionsPanelPage();
		page.selectCaseNumber(iOS_ActionsPanelPage.motoionCM5A, CASE_NUM_CM5A);
	}

	@Then("^User verifies \"([^\"]*)\" is diplayed and  expands the Actions panel \\(CM(\\d+)A\\)$")
	public void user_verifies_is_diplayed_and_expands_the_Actions_panel_CM_A(String actionsPanel, int arg2) {
		page.verifyActionsPanelIsDisplayed(DBType.CM5A, actionsPanel);
	}

	@Then("^User verifies the correct actions display for the selected referral \\(CM(\\d+)A\\)$")
	public void user_verifies_the_correct_actions_display_for_the_selected_referral_CM_A(int arg1) {
		page.compareApplicableActions(DBType.CM5A, CMR_ID_CM5A);
	}

}
