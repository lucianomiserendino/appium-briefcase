package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_JudgeVoteDPFPage;

public class JudgeVoteDPF_StepDefinitions {

	iOS_JudgeVoteDPFPage page;

	@Given("^user selects the \"([^\"]*)\" and case \"([^\"]*)\"$")
	public void user_selects_the_and_case(String category, String caseNum) {
		page = new iOS_JudgeVoteDPFPage();
		page.selectCase(category, caseNum);
	}

	@When("^select  action \"([^\"]*)\"$")
	public void select_action(String action) {
		page.selectAction(action);
	}

	@Then("^user selects the \"([^\"]*)\" button next to the relief\\. User verifies  a popup displays\\.  In the red banner, the relief they are voting , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_selects_the_button_next_to_the_relief_User_verifies_a_popup_displays_In_the_red_banner_the_relief_they_are_voting(
			String viewVotes, String dbType, String ccrId) {
		page.selectViewVotes(DBType.valueOf(dbType), ccrId, viewVotes);
	}

	@Then("^user verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted, \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_verifies_each_judges_initials_to_whom_the_referral_was_sent_as_well_as_their_vote_and_date_they_voted(
			String dbType, String ccrId) {
		page.verifyJudgesInfo(DBType.valueOf(dbType), ccrId);
	}

	@Then("^user selects a vote and adds notes to a vote\\. Use  db \"([^\"]*)\" ,ccrID \"([^\"]*)\" , elID  \"([^\"]*)\" , actionName \"([^\"]*)\"$")
	public void user_selects_a_vote_and_adds_notes_to_a_vote_Use_db_ccrID_elID_actionName(String dbType, String ccr_id,
			String elID, String actionName) {
		page.getVoteSelection(DBType.valueOf(dbType), ccr_id, elID, actionName);

	}

}
