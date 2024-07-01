package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.EnBancPage;
import gov.uscourts.ao.mobileBriefcase.Pages.JudgeVoteDPFPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class EnBanc_StepDefinitions {

	EnBancPage page;
	DocumentPage docPage;
	JudgeVoteDPFPage page2;
	List<UserInputData> userInputData = null;
	String ccrId = "";

	@Then("^user selects a category and case with en banc panel$")
	public void user_selects_a_category_and_case_with_en_banc_panel() {
		List<UserInputData> userInputData = null;

		docPage = new DocumentPage();
		docPage.selectRandomCategory("y", userInputData);
		docPage.selectRandomReferral();

	}

	@Then("^User verifies that a button is displayed next to the logged in judge’s vote$")
	public void user_verifies_that_a_button_is_displayed_next_to_the_logged_in_judge_s_vote() {

		page2 = new JudgeVoteDPFPage();
		ccrId += page2.selectViewVotes(userInputData);
	}

	@When("^it's tapped, it shows all the judges’ vote in a popup\\.$")
	public void it_s_tapped_it_shows_all_the_judges_vote_in_a_popup() {
		page2 = new JudgeVoteDPFPage();
		page2.verifyJudgesVote(ccrId, userInputData);

	}

}
