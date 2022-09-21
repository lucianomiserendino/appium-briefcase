
package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.JudgeVoteDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.VoteInformationPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class JudgeVoteDPF_StepDefinitions {

	static JudgeVoteDPFPage page;
	VoteInformationPage votePage = new VoteInformationPage();
	static String vote = "";
	
	

	@Then("^user verifies a popup displays\\.  In the red banner, the relief they are voting on should display$")
	public void user_verifies_a_popup_displays_In_the_red_banner_the_relief_they_are_voting_on_should_display(
			List<UserInputData> userInputData) {
		page = new JudgeVoteDPFPage();
		page.selectViewVotes(userInputData,Document_StepDefinitions.regularCase);
	}

	@Then("^User verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted$")
	public void user_verifies_each_judges_initials_to_whom_the_referral_was_sent_as_well_as_their_vote_and_date_they_voted(
			List<UserInputData> userInputData) {
		page.verifyJudgesVote(userInputData,Document_StepDefinitions.regularCase);
	}

	@Then("^user selects a vote and adds notes to a vote\\.$")
	public void user_selects_a_vote_and_adds_notes_to_a_vote() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();

		if (Common_StepDefinitions.pane == true) {
			vote += page.getVoteSelection("judgeVote", userInputData,Document_StepDefinitions.regularCase);
		} else {
			throw new RuntimeException(
					"------------------- FAILED ------------------> THE SELECTED REFERRAL DOESN'T REQUIRE VOTING");
		}
	}

	@Then("^User verifies judge's vote is updated in Vote Information Panel\\.$")
	public void user_verifies_judge_s_vote_is_updated_in_Vote_Information_Panel() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();
		page.verifyNoteText(vote, page.getTodaysDate(), userInputData,Document_StepDefinitions.regularCase);
	}

	@Then("^User verifies the court admin doesn't have access to select Actions in Briefcase$")
	public void user_verifies_the_court_admin_doesn_t_have_access_to_select_Actions_in_Briefcase() {
		page = new JudgeVoteDPFPage();
		page.verifyCourtAdminAccess();
	}

}
