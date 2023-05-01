package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage.Category;
import gov.uscourts.ao.mobileBriefcase.Pages.StaffAttorneyReferralSortPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class Document_StepDefinitions {
	DocumentPage page;
	StaffAttorneyReferralSortPage stfPage;
	public static String judCategory;
	public static String regularCase;
	public static String targetCase;
	public static String appliedCase;
	public static String cmr_cyv_code;
	public static String stfCategory;
	public static String stfSubCategory;

	@Then("^User selects random stf Aty category$")
	public void user_selects_random_stf_Aty_category(List<UserInputData> userInputData) {
		page = new DocumentPage();
		stfCategory = page.selectRandomSTFCategory(userInputData);
	}

	@Then("^User selects random judge category$")
	public void user_selects_random_judge_category(List<UserInputData> userInputData) {
		page = new DocumentPage();
		judCategory = page.selectRandomJudgeCategory(userInputData);
	}

	@Then("^User selects random case number$")
	public void user_selects_random_case_number(List<UserInputData> userInputData) {
		page = new DocumentPage();
		regularCase = page.getRandomCase(Category.judgeRegularCase);
	}

	@Then("^User selects random document$")
	public void user_selects_random_document() {
		CommonPages common = new CommonPages();
		common.getGroupIcons(GroupIcons.Expand);
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

	@Then("^User downloads/opens a random document from Case Detail page$")
	public void user_downloads_opens_a_random_document_from_Case_Detail_page() {
		List<UserInputData> userInputData = null;
		CommonPages common = new CommonPages();
		common.getGroupIcons(GroupIcons.Expand);
		page = new DocumentPage();
		page.getDocumentList(userInputData);
	}

	@Then("^User selects sub Category$")
	public void user_selects_sub_Category(List<UserInputData> userInputData) {
		stfPage = new StaffAttorneyReferralSortPage();
		stfSubCategory=stfPage.getRandomCategory();

	}

	@Then("^User selects a random stf Referral$")
	public void user_selects_a_random_stf_Referral() {
		stfPage = new StaffAttorneyReferralSortPage();
		stfPage.selectRandomCase(stfSubCategory);
	}

}
