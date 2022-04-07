package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.VoteClosingDate;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class VoteClosingDate_StepDefinitions {
	VoteClosingDate page;

	@Then("^If there is a vote closing date for a referral, user verifies that Briefcase displays Vote Closing: and the vote closing date in bold font under the filed date, using  \"([^\"]*)\"$")
	public void if_there_is_a_vote_closing_date_for_a_referral_user_verifies_that_Briefcase_displays_Vote_Closing_and_the_vote_closing_date_in_bold_font_under_the_filed_date_using(
			String ccr_id, List<UserInputData> userInputData) {
		page = new VoteClosingDate();
		page.getVoteClosingDate(ccr_id, userInputData);
	}
}
