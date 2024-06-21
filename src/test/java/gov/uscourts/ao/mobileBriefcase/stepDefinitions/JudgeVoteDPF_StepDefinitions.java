
package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage;
import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage.Search;
import gov.uscourts.ao.mobileBriefcase.Pages.JudgeVoteDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.VoteInformationPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class JudgeVoteDPF_StepDefinitions {

	static JudgeVoteDPFPage page;
	CaseQueryPage casequerypage;
	VoteInformationPage votePage = new VoteInformationPage();
	static String vote = "";

	@Then("^user verifies a popup displays\\.  In the red banner, the relief they are voting on should display$")
	public void user_verifies_a_popup_displays_In_the_red_banner_the_relief_they_are_voting_on_should_display() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();
		page.selectViewVotes(Document_StepDefinitions.judCategory, userInputData, Document_StepDefinitions.regularCase);
	}

	@Then("^User verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted$")
	public void user_verifies_each_judges_initials_to_whom_the_referral_was_sent_as_well_as_their_vote_and_date_they_voted() {
		List<UserInputData> userInputData = null;
		page.verifyJudgesVote(Document_StepDefinitions.judCategory, userInputData,
				Document_StepDefinitions.regularCase);
	}

	@Then("^user selects a vote and adds notes to a vote\\.$")
	public void user_selects_a_vote_and_adds_notes_to_a_vote() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();

		vote += page.getVoteSelection(DPF_stepDefinitions.actionName, Document_StepDefinitions.judCategory, "judgeVote",
				userInputData, Document_StepDefinitions.regularCase);
	}

	@Then("^User verifies judge's vote is updated in Vote Information Panel\\.$")
	public void user_verifies_judge_s_vote_is_updated_in_Vote_Information_Panel() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();
		page.verifyNoteText(Document_StepDefinitions.judCategory,  userInputData,
				Document_StepDefinitions.regularCase);
	}

	@Then("^User verifies the court admin doesn't have access to select Actions in Briefcase$")
	public void user_verifies_the_court_admin_doesn_t_have_access_to_select_Actions_in_Briefcase() {
		page = new JudgeVoteDPFPage();
		page.verifyCourtAdminAccess();
	}

	@Then("^User selects a case that has at least one document In the Note/Vote$")
	public void user_selects_a_case_that_has_at_least_one_document_In_the_Note_Vote() {
		page = new JudgeVoteDPFPage();
		List<UserInputData> userInputData = null;
		page.getReferralWithDoc(userInputData);
		casequerypage = new CaseQueryPage();
		casequerypage.searchForACase(page.cyv_category, page.caseid, Search.caseNumber);

	}

	@Then("^User verifies that doc popup is accessible from judgeVote note and document description is correct$")
	public void user_verifies_that_doc_popup_is_accessible_from_judgeVote_note_and_document_description_is_correct() {
		page = new JudgeVoteDPFPage();
		page.verifyDocumentIsDisplayed();
	}
	
	@Then("^User verifies that View Votes button is displayed next to the logged in judge’s vote$")
	public void user_verifies_that_View_Votes_button_is_displayed_next_to_the_logged_in_judge_s_vote() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();
		page.selectViewVotes(Document_StepDefinitions.judCategory,userInputData, Document_StepDefinitions.regularCase);
	}

	@When("^the button is tapped, User verifies that it shows all the judges’ vote in a popup\\.$")
	public void the_button_is_tapped_User_verifies_that_it_shows_all_the_judges_vote_in_a_popup() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();
		page.verifyJudgesVote(Document_StepDefinitions.judCategory,userInputData, Document_StepDefinitions.regularCase);
	}


}
