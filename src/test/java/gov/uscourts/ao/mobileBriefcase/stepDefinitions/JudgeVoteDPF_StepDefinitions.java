
package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.JudgeVoteDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.VoteInformationPage;
import gov.uscourts.ao.mobileBriefcase.common.Actions;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;

public class JudgeVoteDPF_StepDefinitions {

	static JudgeVoteDPFPage page;
	VoteInformationPage votePage = new VoteInformationPage();
	static String vote = "";

	@Then("^user selects the \"([^\"]*)\" button next to the relief\\. User verifies  a popup displays\\.  In the red banner, the relief they are voting , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_selects_the_button_next_to_the_relief_User_verifies_a_popup_displays_In_the_red_banner_the_relief_they_are_voting(
			String viewVotes, String dbType, String ccrId) {
		votePage = new VoteInformationPage();
		assertTrue(Actions.isDisplayed(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '"
				+ votePage.getFilerInfo(DBType.valueOf(dbType), "34", "81452", "rhr") + "')]"));
		page = new JudgeVoteDPFPage();
		page.selectViewVotes(valueOf(dbType), ccrId, viewVotes);
	}

	@Then("^User verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted\\. Use \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_verifies_each_judges_initials_to_whom_the_referral_was_sent_as_well_as_their_vote_and_date_they_voted_Use(
			String dbType, String ccrId) {
		page.verifyJudgesVote(valueOf(dbType), ccrId);
	}

	@Then("^user selects a vote and adds notes to a vote\\. Use  db \"([^\"]*)\" ,ccrID \"([^\"]*)\" , elID  \"([^\"]*)\" , and dpf \"([^\"]*)\"$")
	public void user_selects_a_vote_and_adds_notes_to_a_vote_Use_db_ccrID_elID_and_dpf(String dbType, String ccr_id,
			String elId, String dpfName) throws Throwable {
		page = new JudgeVoteDPFPage();
		vote += page.getVoteSelection(valueOf(dbType), dpfName, ccr_id, elId);
	}

	@Then("^User verifies judge's vote is updated in Vote Information Panel\\. Use  db \"([^\"]*)\" ,ccrID \"([^\"]*)\"$")
	public void user_verifies_judge_s_vote_is_updated_in_Vote_Information_Panel_Use_db_ccrID(String dbType,
			String ccr_id) {
		page = new JudgeVoteDPFPage();
		page.verifyNoteText(valueOf(dbType), ccr_id, vote, page.getTodaysDate());
	}

	@Then("^User verifies the court admin doesn't have access to select Actions in Briefcase$")
	public void user_verifies_the_court_admin_doesn_t_have_access_to_select_Actions_in_Briefcase() {
		page = new JudgeVoteDPFPage();
		page.verifyCourtAdminAccess();
	}

}
