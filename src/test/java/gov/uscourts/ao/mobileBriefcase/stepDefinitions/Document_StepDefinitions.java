package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage.Category;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class Document_StepDefinitions {
	DocumentPage page;
	public static String categroy;
	public static String regularCase;
	public static String targetCase;
	public static String appliedCase;

	@Then("^User selects random stf Aty category$")
	public void user_selects_random_stf_Aty_category(List<UserInputData> userInputData) {
		page = new DocumentPage();
		page.selectRandomSTFCategory(userInputData);
	}

	@Then("^User selects random judge category$")
	public void user_selects_random_judge_category(List<UserInputData> userInputData) {
		page = new DocumentPage();
		categroy = page.selectRandomJudgeCategory(userInputData);
	}

	@Then("^User selects random case number$")
	public void user_selects_random_case_number(List<UserInputData> userInputData) {
		page = new DocumentPage();
		regularCase = page.getRandomCase(Category.RegularCase);
	}

	@Then("^User selects random document$")
	public void user_selects_random_document() {
		CommonPages common = new CommonPages();
		common.getGroupIcons();
		page = new DocumentPage();
		page.getDocumentCategories();
	}

	@Then("^User selects random target case$")
	public void user_selects_random_target_case() {
		targetCase = page.getRandomCase(Category.TargetCase);
		System.out.println(targetCase + "*****************************************targetCase");
	}

	@Then("^User selects random applied case$")
	public void user_selects_random_applied_case() {
		appliedCase = page.getRandomCase(Category.AppliedCase);
		System.out.println(appliedCase + "*****************************************appliedCase");

	}

}
