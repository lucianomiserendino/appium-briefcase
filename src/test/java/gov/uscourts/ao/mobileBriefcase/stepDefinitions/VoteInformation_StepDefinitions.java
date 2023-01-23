package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.VoteInformationPage;
import gov.uscourts.ao.mobileBriefcase.Pages.VoteInformationPage.FILERs_INFO;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;

public class VoteInformation_StepDefinitions {
	VoteInformationPage page;
	CommonPages page1;
	String cmr_cyv_code;

	@Given("^User observes the \"([^\"]*)\" panel displays\\.   This should only display if the referral requires voting$")
	public void user_observes_the_panel_displays_This_should_only_display_if_the_referral_requires_voting(
			String voteInfo) {
		page = new VoteInformationPage();
		page1 = new CommonPages();
		page1.getPanel(Panel.valueOf(voteInfo));
	}

	@Then("^for each referral, observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading\\.$")
	public void for_each_referral_observes_the_filer_s_name_first_initial_of_pr_middle_name_gn_display_party_type_and_date_filed_displays_in_a_light_blue_heading() {
		page = new VoteInformationPage();
		String caseNum = Document_StepDefinitions.regularCase;
		List<UserInputData> userInputData = null;

		String ccr_id = CommonPages.getCCRID(caseNum, userInputData);
		String name = SystemPropertySetup.getJudge(userInputData);
		String cmr_ju_pe_id = DocumentPage.get_pe_id("jud",userInputData);
		String cmr_cs_caseid = CommonPages.getCaseID(caseNum, userInputData);

		cmr_cyv_code = CommonPages.cmr_cyv_code(Document_StepDefinitions.judCategory, cmr_cs_caseid, userInputData).trim();

		assertTrue(page.filersInfo(FILERs_INFO.VOTE_INFO_FILLRES_INFORMATION, cmr_ju_pe_id, cmr_cs_caseid, cmr_cyv_code,
				ccr_id, userInputData));
	}

	@Then("^User checks each judge's vote  and the date  displays under their initials, using  \"([^\"]*)\"$")
	public void user_checks_each_judge_s_vote_and_the_date_displays_under_their_initials_using(String ccr_id,
			List<UserInputData> userInputData) {
		page.getJudesVote(FILERs_INFO.VOTE_INFO_FILLRES_INFORMATION, ccr_id, userInputData);
	}

	@Then("^In the judgeVoteDPf, the user observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading\\. Use  \"([^\"]*)\" , \"([^\"]*)\" ,\"([^\"]*)\" , \"([^\"]*)\" \\.$")
	public void in_the_judgeVoteDPf_the_user_observes_the_filer_s_name_first_initial_of_pr_middle_name_gn_display_party_type_and_date_filed_displays_in_a_light_blue_heading_Use(
			String cmr_ju_pe_id, String cmr_cs_caseid, String cmr_cyv_code, String ccr_id,
			List<UserInputData> userInputData) {
		page = new VoteInformationPage();
		assertTrue(page.filersInfo(FILERs_INFO.JUDGE_VOTE_DPF_FILLRES_INFORMATION, cmr_ju_pe_id, cmr_cs_caseid,
				cmr_cyv_code, ccr_id, userInputData));
	}

	@Then("^In the judgeVoteDPf, the user checks each judge's vote and the date displays under their initials, using  \"([^\"]*)\"$")
	public void in_the_judgeVoteDPf_the_user_checks_each_judge_s_vote_and_the_date_displays_under_their_initials_using(
			String ccr_id, List<UserInputData> userInputData) {
		page.getJudesVote(FILERs_INFO.JUDGE_VOTE_DPF_FILLRES_INFORMATION, ccr_id, userInputData);

	}

	@Then("^User verifies that the judges' initials in the \"([^\"]*)\" are sorted based on panel_to_judge\\.pj_judge_order or by judge\\.ju_seniority_sort, \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\",$")
	public void user_verifies_that_the_judges_initials_in_the_are_sorted_based_on_panel_to_judge_pj_judge_order_or_by_judge_ju_seniority_sort(
			String panel, String caseNum, String pe_id, String cmr_cyv_code, List<UserInputData> userInputData) {
		page = new VoteInformationPage();
		page.getJudgeInitials(panel, caseNum, pe_id, cmr_cyv_code, userInputData);
	}

}
