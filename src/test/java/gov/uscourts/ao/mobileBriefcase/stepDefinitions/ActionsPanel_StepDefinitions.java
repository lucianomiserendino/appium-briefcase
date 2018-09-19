package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_ActionsPanelPage;

public class ActionsPanel_StepDefinitions {
	iOS_ActionsPanelPage page;

	@Then("^\"([^\"]*)\"\\. User verifies \"([^\"]*)\" is diplayed and  expands the Actions panel$")
	public void user_verifies_is_diplayed_and_expands_the_Actions_panel(String dbType, String actionsPanel) {
		page = new iOS_ActionsPanelPage();
		page.verifyActionsPanelIsDisplayed(DBType.valueOf(dbType), actionsPanel);

	}

	@Then("^User verifies the correct actions display for the selected referral ,using  \"([^\"]*)\"  and \"([^\"]*)\"$")
	public void user_verifies_the_correct_actions_display_for_the_selected_referral_using_and(String dbType,
			String cmr_id) {
		page.compareApplicableActions(DBType.valueOf(dbType), cmr_id);

	}

}
