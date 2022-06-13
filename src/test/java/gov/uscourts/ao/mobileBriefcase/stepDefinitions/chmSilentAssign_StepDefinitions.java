package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.chmSilentAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class chmSilentAssign_StepDefinitions {
	chmSilentAssignDPFPage page;

	@Then("^user checks the chmSilentAssign mode$")
	public void user_checks_the_chmSilentAssign_mode(List<UserInputData> userInputData) {
		page = new chmSilentAssignDPFPage();

		page.getJudgeAssignment("3068", userInputData);
		
	}

}
