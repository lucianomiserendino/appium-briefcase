package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_VoteInformationPage;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_VoteInformationPage.FILERs_INFO;

public class VoteInformation_StepDefinitions {
	iOS_VoteInformationPage page;
	CommonPages page1;

	@Given("^User observes \\( \"([^\"]*)\" \\) the \"([^\"]*)\" panel displays\\.   This should only display if the referral requires voting$")
	public void user_observes_the_panel_displays_This_should_only_display_if_the_referral_requires_voting(String dbType,
			String voteInfo) {
		page = new iOS_VoteInformationPage();
		page.getVoteInformationPanel(valueOf(dbType), voteInfo);

	}

	@Then("^for each referral, observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading\\. Use \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\" ,\"([^\"]*)\" , \"([^\"]*)\" \\.$")
	public void for_each_referral_observes_the_filer_s_name_first_initial_of_pr_middle_name_gn_display_party_type_and_date_filed_displays_in_a_light_blue_heading_Use(
			String dbType, String cmr_ju_pe_id, String cmr_cs_caseid, String cmr_cyv_code, String ccr_id) {

		page.getFilersInformation(FILERs_INFO.VOTE_INFO_FILLRES_INFORMATION, valueOf(dbType), cmr_ju_pe_id,
				cmr_cs_caseid, cmr_cyv_code, ccr_id);
		page.getFilersInformation(FILERs_INFO.VOTE_INFO_RELIEF, valueOf(dbType), cmr_ju_pe_id, cmr_cs_caseid,
				cmr_cyv_code, ccr_id);

	}

	@Then("^observes the judge's initials display in the same heading and db \"([^\"]*)\" using  \"([^\"]*)\"$")
	public void observes_the_judge_s_initials_display_in_the_same_heading_and_db_using(String dbType, String ccr_id) {

		page.getJudgeInitials(valueOf(dbType), ccr_id);

	}

	@Then("^User checks each judge's vote  and the date  displays under their initials$")
	public void user_checks_each_judge_s_vote_and_the_date_displays_under_their_initials() {

	}

	@Given("^user selects the \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_selects_the_and(String category, String caseNum) {
		page1 = new CommonPages();
		page1.getCategoryWithCase(category, caseNum);
	}

	@When("^user  selects action \"([^\"]*)\" , user verifies the name of the action , \"([^\"]*)\",a toggle under the Enter Vote banner and the label \"([^\"]*)\"  displays$")
	public void user_selects_action_user_verifies_the_name_of_the_action_a_toggle_under_the_Enter_Vote_banner_and_the_label_displays(
			String action, String enterVote, String label) {
		page = new iOS_VoteInformationPage();
		page.verifyElementsAreDisplayed(action, enterVote, label);
	}

	@Then("^user  verifies the filers information,the relief text, judge's current vote displays, logged in judge's initials using \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" \\.If there is not a current vote, the text \"([^\"]*)\" displays$")
	public void user_verifies_the_filers_information_the_relief_text_judge_s_current_vote_displays_logged_in_judge_s_initials_using_If_there_is_not_a_current_vote_the_text_displays(
			String cmr_ju_pe_id, String cmr_cs_caseid, String cmr_cyv_code, String ccr_id, String arg5) {
		page.getFilersInformation(FILERs_INFO.JUDGE_VOTE_FILLRES_INFORMATION, DBType.CMKA, cmr_ju_pe_id, cmr_cs_caseid,
				cmr_cyv_code, ccr_id);

		page.getFilersInformation(FILERs_INFO.JUDGE_VOTE_RELIEF, DBType.CMKA, cmr_ju_pe_id, cmr_cs_caseid, cmr_cyv_code,
				ccr_id);

	}

}
