
package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_JudgeVoteDPFPage;

public class JudgeVoteDPF_StepDefinitions {

	iOS_JudgeVoteDPFPage page;

	String vote = "";

	@Then("^user selects the \"([^\"]*)\" button next to the relief\\. User verifies  a popup displays\\.  In the red banner, the relief they are voting , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_selects_the_button_next_to_the_relief_User_verifies_a_popup_displays_In_the_red_banner_the_relief_they_are_voting(
			String viewVotes, String dbType, String ccrId) {
		page = new iOS_JudgeVoteDPFPage();
		page.selectViewVotes(valueOf(dbType), ccrId, viewVotes);
	}

	@Then("^User verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted\\. Use \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_verifies_each_judges_initials_to_whom_the_referral_was_sent_as_well_as_their_vote_and_date_they_voted_Use(
			String dbType, String ccrId) {
		page.verifyJudgesVote(valueOf(dbType), ccrId);
	}

	@Then("^user selects a vote and adds notes to a vote\\. Use  db \"([^\"]*)\" ,ccrID \"([^\"]*)\" , elID  \"([^\"]*)\"$")
	public void user_selects_a_vote_and_adds_notes_to_a_vote_Use_db_ccrID_elID(String dbType, String ccr_id,
			String elId) {
		page = new iOS_JudgeVoteDPFPage();
		vote += page.getVoteSelection(valueOf(dbType), ccr_id, elId);

	}

	@Then("^User verifies judge's vote is updated in Vote Information Panel\\. Use  db \"([^\"]*)\" ,ccrID \"([^\"]*)\"$")
	public void user_verifies_judge_s_vote_is_updated_in_Vote_Information_Panel_Use_db_ccrID(String dbType,
			String ccr_id) {
		page = new iOS_JudgeVoteDPFPage();
		page.verifyNoteText(valueOf(dbType), ccr_id, vote, page.getTodaysDate());
	}

}
