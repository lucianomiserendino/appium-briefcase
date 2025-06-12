package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;



import gov.uscourts.ao.mobileBriefcase.Pages.OtherSubmissions;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Then;

public class OtherSubmissions_StepDefinitions {
	OtherSubmissions page;

	@Then("^I navigate to a case with multiple referrals and verify that the case displays the Other Submissions in Case panel and contains the date, type of submission, the panel it was sent to and the status\\.$")
	public void i_navigate_to_a_case_with_multiple_referrals_and_verify_that_the_case_displays_the_Other_Submissions_in_Case_panel_and_contains_the_date_type_of_submission_the_panel_it_was_sent_to_and_the_status() {
		page = new OtherSubmissions();

		List<UserInputData> userInputData = null;
		page.otherSubmissionsDisplayed();
	}

}
