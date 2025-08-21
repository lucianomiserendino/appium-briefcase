
package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;


import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage.Search;
import gov.uscourts.ao.mobileBriefcase.Pages.JudgeVoteDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.VoteInformationPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Then;

public class JudgeVoteDPF_StepDefinitions {

	static JudgeVoteDPFPage page;
	CaseQueryPage casequerypage;
	VoteInformationPage votePage = new VoteInformationPage();
	static String vote = "";
	String caseNum = DocumentPage.caseNum;
	String refCategory = DocumentPage.category;
	static String  ccr_id="";
	
	@Then("^user verifies a popup displays\\.  In the red banner, the relief they are voting on should display$")
	public void user_verifies_a_popup_displays_In_the_red_banner_the_relief_they_are_voting_on_should_display() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();
		String caseId = DocumentPage.cs_caseid;
		String cmr_cyv_code = DocumentPage.cmr_cyv_code;
		ccr_id=page.selectViewVotes(caseId,cmr_cyv_code, userInputData);
	}

	@Then("^User verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted$")
	public void user_verifies_each_judges_initials_to_whom_the_referral_was_sent_as_well_as_their_vote_and_date_they_voted() {
		List<UserInputData> userInputData = null;
		page.verifyJudgesVote(ccr_id,userInputData);
	}

	@Then("^user selects a vote and adds notes to a vote\\.$")
	public void user_selects_a_vote_and_adds_notes_to_a_vote() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();
		String caseId = DocumentPage.cs_caseid;
		String cmr_cyv_code = DocumentPage.cmr_cyv_code;
		
		vote += page.getVoteSelection( caseId, cmr_cyv_code, DPF_stepDefinitions.actionName, refCategory, "judgeVote",
				userInputData);
	}

	@Then("^User verifies judge's vote is updated in Vote Information Panel\\.$")
	public void user_verifies_judge_s_vote_is_updated_in_Vote_Information_Panel() {
		List<UserInputData> userInputData = null;
		page = new JudgeVoteDPFPage();
		page.verifyNoteText(page.relief,page.foundCcrId,refCategory,  userInputData,
				caseNum);
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
	

}
