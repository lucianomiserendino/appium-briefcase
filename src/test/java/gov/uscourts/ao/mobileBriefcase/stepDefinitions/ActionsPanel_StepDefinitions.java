package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_ActionsPanelPage;

public class ActionsPanel_StepDefinitions {
	iOS_ActionsPanelPage page;

	@When("^User selects Judge,  \"([^\"]*)\" and  \"([^\"]*)\"$")
	public void user_selects_Judge_and(String category, String caseNum) {
		page = new iOS_ActionsPanelPage();
		page.selectCase(category, caseNum);
	}

	@Then("^\"([^\"]*)\"\\. User verifies \"([^\"]*)\" is diplayed and  expands the Actions panel$")
	public void user_verifies_is_diplayed_and_expands_the_Actions_panel(String dbType, String actionsPanel) {
		if (dbType.equals("CMKA")) {
			page.verifyActionsPanelIsDisplayed(DBType.CMKA, actionsPanel);
		} else {
			page.verifyActionsPanelIsDisplayed(DBType.CM3A, actionsPanel);
		}
	}

	@Then("^User verifies the correct actions display for the selected referral ,using  \"([^\"]*)\"  and \"([^\"]*)\"$")
	public void user_verifies_the_correct_actions_display_for_the_selected_referral_using_and(String dbType,
			String cmr_id) throws Throwable {
		if (dbType.equals("CMKA")) {
			page.compareApplicableActions(DBType.CMKA, cmr_id);
		} else {
			page.compareApplicableActions(DBType.CM3A, cmr_id);
		}
	}

}
