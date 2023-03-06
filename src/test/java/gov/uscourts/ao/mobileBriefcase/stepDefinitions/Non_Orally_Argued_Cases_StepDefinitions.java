package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.Non_Orally_Argued_Cases;
import gov.uscourts.ao.mobileBriefcase.Pages.Non_Orally_Argued_Cases.ReferralDate;

public class Non_Orally_Argued_Cases_StepDefinitions {

	Non_Orally_Argued_Cases page;

	@Then("^User verifies if there is more than one referral in the same category for a case, the case is displayed only once and the referral date that is displayed is for the latest referral\\.$")
	public void user_verifies_if_there_is_more_than_one_referral_in_the_same_category_for_a_case_the_case_is_displayed_only_once_and_the_referral_date_that_is_displayed_is_for_the_latest_referral() {

		page = new Non_Orally_Argued_Cases();

		page.ifReferralDateDisplayed(ReferralDate.Banner, Document_StepDefinitions.regularCase);
	}

}
