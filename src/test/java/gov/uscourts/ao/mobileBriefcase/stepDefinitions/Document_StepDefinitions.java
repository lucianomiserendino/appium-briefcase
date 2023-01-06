package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage.Category;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class Document_StepDefinitions {
	DocumentPage page;
	public static String category;
	public static String regularCase;
	public static String targetCase;
	public static String appliedCase;
	public static String cmr_cyv_code;

	@Then("^User selects random stf Aty category$")
	public void user_selects_random_stf_Aty_category(List<UserInputData> userInputData) {
		page = new DocumentPage();
		page.selectRandomSTFCategory(userInputData);
	}

	@Then("^User selects random judge category$")
	public void user_selects_random_judge_category(List<UserInputData> userInputData) {
		page = new DocumentPage();
		category = page.selectRandomJudgeCategory(userInputData);
	}

	@Then("^User selects random case number$")
	public void user_selects_random_case_number(List<UserInputData> userInputData) {
		page = new DocumentPage();
		regularCase = page.getRandomCase(Category.judgeRegularCase);
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
		page = new DocumentPage();
		targetCase = page.getRandomCase(Category.targetCase);
	}

	@Then("^User selects random applied case$")
	public void user_selects_random_applied_case() {
		page = new DocumentPage();
		appliedCase = page.getRandomCase(Category.appliedCase);

	}


}
