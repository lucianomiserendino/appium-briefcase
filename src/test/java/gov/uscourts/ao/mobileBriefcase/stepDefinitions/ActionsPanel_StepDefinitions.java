package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;


import gov.uscourts.ao.mobileBriefcase.Pages.ActionsListViewPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Then;

public class ActionsPanel_StepDefinitions {
	ActionsListViewPage page;


	@Then("^User verifies that Action displays if the assignment type specified in mbr_event record is = judge only$")
	public void user_verifies_that_Action_displays_if_the_assignment_type_specified_in_mbr_event_record_is_judge_only() {
		page = new ActionsListViewPage();
		List<UserInputData> userInputData = null;
		page.verifyMbrdocWPisDisplayed(userInputData);
	}

}
