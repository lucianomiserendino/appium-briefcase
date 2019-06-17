package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.Pages.VoteInformationPage;

public class VoteInformation_StepDefinitions {
	VoteInformationPage page;
	CommonPages page1;

	@Given("^User observes \\( \"([^\"]*)\" \\) the \"([^\"]*)\" panel displays\\.   This should only display if the referral requires voting$")
	public void user_observes_the_panel_displays_This_should_only_display_if_the_referral_requires_voting(String dbType,
			String voteInfo) {
		page = new VoteInformationPage();
		page1.getPanel(Panel.valueOf(voteInfo));
	}

	@Then("^for each referral, observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading\\. Use \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\" ,\"([^\"]*)\" , \"([^\"]*)\" \\.$")
	public void for_each_referral_observes_the_filer_s_name_first_initial_of_pr_middle_name_gn_display_party_type_and_date_filed_displays_in_a_light_blue_heading_Use(
			String dbType, String cmr_ju_pe_id, String cmr_cs_caseid, String cmr_cyv_code, String ccr_id) {
		page = new VoteInformationPage();
		assertTrue(page.filersInfo(DBType.valueOf(dbType), cmr_ju_pe_id, cmr_cs_caseid, cmr_cyv_code, ccr_id));
	}

	@Then("^User checks each judge's vote  and the date  displays under their initials, \"([^\"]*)\" using  \"([^\"]*)\"$")
	public void user_checks_each_judge_s_vote_and_the_date_displays_under_their_initials_using(String dbType,
			String ccr_id) {
		page.getJudesVote(DBType.valueOf(dbType), ccr_id);

	}

}
