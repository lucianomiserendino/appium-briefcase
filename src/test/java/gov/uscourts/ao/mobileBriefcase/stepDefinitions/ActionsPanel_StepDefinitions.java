package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_ActionsPanelPage;

public class ActionsPanel_StepDefinitions {
	iOS_ActionsPanelPage page;

	@Given("^User verifies \"([^\"]*)\" is diplayed and  expands the Actions panel$")
	public void user_verifies_is_diplayed_and_expands_the_Actions_panel(String actionsPanel) {
		page = new iOS_ActionsPanelPage();
		page.verifyActionsPanelIsDisplayed(actionsPanel);

	}

	@Then("^User verifies the correct actions display for the selected referral$")
	public void user_verifies_the_correct_actions_display_for_the_selected_referral() {
		page.compareApplicableActions();

	}

	

}
