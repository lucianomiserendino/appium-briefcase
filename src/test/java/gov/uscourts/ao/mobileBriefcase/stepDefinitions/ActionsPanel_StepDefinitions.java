package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.ActionsPage;

public class ActionsPanel_StepDefinitions {
	ActionsPage page;

	@Then("^User verifies the correct \"([^\"]*)\" display for the selected referral ,using  \"([^\"]*)\"  and \"([^\"]*)\"$")
	public void user_verifies_the_correct_display_for_the_selected_referral_using_and(String actions, String dbType,
			String cmr_id) {
		page = new ActionsPage();
		page.getApplicableActions(dbType, actions, cmr_id);
	}

}
