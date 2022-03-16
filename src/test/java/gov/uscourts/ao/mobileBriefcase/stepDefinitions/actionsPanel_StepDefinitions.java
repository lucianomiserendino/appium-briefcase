package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.ActionsListViewPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class actionsPanel_StepDefinitions {
	ActionsListViewPage page;

	@Then("^User verifies the correct \"([^\"]*)\" display for the selected referral$")
	public void user_verifies_the_correct_display_for_the_selected_referral(String actions, DataTable data) {
		
		page = new ActionsListViewPage();
		
		List<UserInputData> userInputData = null;

		page.getApplicableActions(actions, userInputData);
	}

}
