package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage.Category;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.Pages.StaffAttorneyReferralSortPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

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
	public static String dpfName;

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

	@Then("^User verifies the document shows a green checkmark, swipe to delete it, and confirm the icon reverts to a download arrow\\.$")
	public void user_verifies_the_document_shows_a_green_checkmark_swipe_to_delete_it_and_confirm_the_icon_reverts_to_a_download_arrow() {
		page = new DocumentPage();
		page.deleteDocument(DocumentPage.randomDocument);

	}

	@Then("^User verifies downloaded \\(\"([^\"]*)\"\\) document has a green checkmark next to it$")
	public void user_verifies_downloaded_document_has_a_green_checkmark_next_to_it(String open) {
		page = new DocumentPage();
		Assert.assertTrue("Verify the viewed document: " + DocumentPage.randomDocument + " shows a green checkmark",
				page.verifyGreenCheckmark(DocumentPage.randomDocument, open));
	}

	@Then("^User selects sub Category$")
	public void user_selects_sub_Category(List<UserInputData> userInputData) {
		stfPage = new StaffAttorneyReferralSortPage();
		stfSubCategory = stfPage.getRandomCategory();

	}

	@Then("^User selects a random stf Referral$")
	public void user_selects_a_random_stf_Referral() {
		stfPage = new StaffAttorneyReferralSortPage();
		stfPage.selectRandomCase(stfSubCategory);
	}

	@Then("^User navigates to View Case Info, then taps Docket Entries$")
	public void user_navigates_to_View_Case_Info_then_taps_Docket_Entries() {
		String actionName = DPF_stepDefinitions.actionName;
		page = new DocumentPage();
		page.navigateToViewCaseInfo(actionName);
	}

	@Then("^User selects a random category$")
	public void user_selects_a_random_category_and_target_case() {
		List<UserInputData> userInputData = null;
		page = new DocumentPage();
		page.selectRandomCategory("n", userInputData);
	}

	@Then("^User selects a random case$")
	public void user_selects_a_random_case() {
		page = new DocumentPage();
		page.selectRandomReferral();
	}

	@Given("I set the site table variable {string} to {string}")
	public void i_set_the_site_table_variable_to(String siteCode, String siVal) {
		List<UserInputData> userInputData = new ArrayList<>();
		JenieLoginPage page = new JenieLoginPage();
		page.changeSiteVariableValue(siteCode, siVal, userInputData);
	}

	@Then("User opens the document again verifies the document re-loads and opens as expected")
	public void user_opens_the_document_again_verifies_the_document_re_loads_and_opens_as_expected() {
		page = new DocumentPage();
		page.reopenDocument(DocumentPage.randomDocument);
	}

}
