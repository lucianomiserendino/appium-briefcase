package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_JudgeVoteDPFPage;

public class JudgeVoteDPF_StepDefinitions {

	iOS_JudgeVoteDPFPage page;

	@When("^select  action \"([^\"]*)\"$")
	public void select_action(String action) {
		page = new iOS_JudgeVoteDPFPage();
		page.selectAction(action);
	}

	@Then("^user selects the \"([^\"]*)\" button next to the relief\\. User verifies  a popup displays\\.  In the red banner, the relief they are voting , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_selects_the_button_next_to_the_relief_User_verifies_a_popup_displays_In_the_red_banner_the_relief_they_are_voting(
			String viewVotes, String dbType, String ccrId) {

		if (dbType.equals("cmka")) {
			page.selectViewVotes(DBType.CMKA, Queries.JUDGE_VOTE_DPF_RELIEF, ccrId, viewVotes);
		} else {
			page.selectViewVotes(DBType.CM3A, Queries.JUDGE_VOTE_DPF_RELIEF, ccrId, viewVotes);
		}
	}

	@Then("^user verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted, \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_verifies_each_judges_initials_to_whom_the_referral_was_sent_as_well_as_their_vote_and_date_they_voted(
			String dbType, String ccrId) {

		if (dbType.equals("cmka")) {
			page.verifyJudgesInfo(DBType.CMKA, ccrId);
		} else {
			page.verifyJudgesInfo(DBType.CM3A, ccrId);
		}
	}

}
