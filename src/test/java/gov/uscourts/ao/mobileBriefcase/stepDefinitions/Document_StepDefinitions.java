package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class Document_StepDefinitions {
	DocumentPage page;
	public static String categroy;
	public static String referral;

	@Then("^User selects random stf Aty category$")
	public void user_selects_random_stf_Aty_category(List<UserInputData> userInputData) {
		page=new DocumentPage();
		page.selectRandomSTFCategory(userInputData);
	}

	@Then("^User selects random judge category$")
	public void user_selects_random_judge_category(List<UserInputData> userInputData) {
		page=new DocumentPage();
		categroy = page.selectRandomJudgeCategory(userInputData);
	}

	@Then("^User selects random case number$")
	public void user_selects_random_case_number(List<UserInputData> userInputData) {
		page = new DocumentPage();
		referral = page.selectRandomCaseNumber(userInputData);
	}

}
