package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.io.IOException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.VoteInformationPanelPage;

public class VoteInformationPanel_StepDefinitions {
	VoteInformationPanelPage page;

	@Given("^User selects Judge Colloton  >> Petitions for Rehearing >> \"([^\"]*)\"$")
	public void user_selects_Judge_Colloton_Petitions_for_Rehearing(String caseNumber) {
		page = new VoteInformationPanelPage();
		page.selectCaseNumber(caseNumber);

	}

	@When("^User observes the \"([^\"]*)\" panel displays\\.   This should only display if the referral requires voting$")
	public void user_observes_the_panel_displays_This_should_only_display_if_the_referral_requires_voting(
			String voteInformation) {
		page.getVoteInformationPanel(voteInformation);
	}

	@Then("^for each referral, observes the filer's name  first initial of pr_middle_name gn_display, party type and date filed displays in a light blue heading\\.$")
	public void for_each_referral_observes_the_filer_s_name_first_initial_of_pr_middle_name_gn_display_party_type_and_date_filed_displays_in_a_light_blue_heading()
			throws IOException {
		page.getFilersInformation();

	}

	@Then("^observes the judge's initials display in the same heading$")
	public void observes_the_judge_s_initials_display_in_the_same_heading() {
		page.checkJudgesIntitials();
	}

	@Then("^User checks each relief  that has been  displayed below the filer name\\.$")
	public void user_checks_each_relief_that_has_been_displayed_below_the_filer_name() {
		page.verifyReliefIsDisplayed();
	}

	@Then("^User checks each judge's vote  and the date  displays under their initials$")
	public void user_checks_each_judge_s_vote_and_the_date_displays_under_their_initials() {
		page.getJudgesVoteInfo();
	}

}
