package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_VoteInformationPanelPage;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_VoteInformationPanelPage.FILERs_INFO;

public class VoteInformationPanel_StepDefinitions {
	iOS_VoteInformationPanelPage page;

	@Then("^User selects judge, category \"([^\"]*)\"  and \"([^\"]*)\"$")
	public void user_selects_judge_category_and(String category, String caseNum) {
		page = new iOS_VoteInformationPanelPage();
		page.selectCase(category, caseNum);
	}

	@Given("^User observes \\( \"([^\"]*)\" \\) the \"([^\"]*)\" panel displays\\.   This should only display if the referral requires voting$")
	public void user_observes_the_panel_displays_This_should_only_display_if_the_referral_requires_voting(String dbType,
			String voteInfo) {

		if (dbType.equals("CMKA")) {
			page.getVoteInformationPanel(DBType.CMKA, voteInfo);
		} else {
			page.getVoteInformationPanel(DBType.CM3A, voteInfo);
		}

	}

	@Then("^for each referral, observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading\\. Use \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\" ,\"([^\"]*)\" , \"([^\"]*)\" \\.$")
	public void for_each_referral_observes_the_filer_s_name_first_initial_of_pr_middle_name_gn_display_party_type_and_date_filed_displays_in_a_light_blue_heading_Use(
			String dbType, String cmr_ju_pe_id, String cmr_cs_caseid, String cmr_cyv_code, String ccr_id) {
		if (dbType.equals("CMKA")) {
			page.getFilersInformation(FILERs_INFO.FILLRES_INFORMATION, DBType.CMKA, cmr_ju_pe_id, cmr_cs_caseid,
					cmr_cyv_code, ccr_id);
			page.getFilersInformation(FILERs_INFO.RELIEF, DBType.CMKA, cmr_ju_pe_id, cmr_cs_caseid, cmr_cyv_code,
					ccr_id);
		} else {
			page.getFilersInformation(FILERs_INFO.FILLRES_INFORMATION, DBType.CM3A, cmr_ju_pe_id, cmr_cs_caseid,
					cmr_cyv_code, ccr_id);
			page.getFilersInformation(FILERs_INFO.RELIEF, DBType.CM3A, cmr_ju_pe_id, cmr_cs_caseid, cmr_cyv_code,
					ccr_id);
		}
	}

	@Then("^observes the judge's initials display in the same heading and db \"([^\"]*)\" using  \"([^\"]*)\"$")
	public void observes_the_judge_s_initials_display_in_the_same_heading_and_db_using(String dbType, String ccr_id) {
		if (dbType.equals("CMKA")) {
			page.getJudgeInitials(DBType.CMKA, ccr_id);
		} else {
			page.getJudgeInitials(DBType.CM3A, ccr_id);
		}
	}

	@Then("^User checks each judge's vote  and the date  displays under their initials$")
	public void user_checks_each_judge_s_vote_and_the_date_displays_under_their_initials() {

		// page.getJudgesVoteInfo();

	}

}
