package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.TerminateReferrals;
import gov.uscourts.ao.mobileBriefcase.Pages.TerminateReferrals.ReferralTermination;

public class ReferralTermination_StepDefinitions {
	TerminateReferrals page;

	@Then("^User  sets the chambers_case_to_referral\\.ccr_date_end date field to today's date  in \"([^\"]*)\" , using  \"([^\"]*)\" and  \"([^\"]*)\", indicating the referral \"([^\"]*)\" is terminated\\.$")
	public void user_sets_the_chambers_case_to_referral_ccr_date_end_date_field_to_today_s_date_in_using_and_indicating_the_referral_is_terminated(
			String dbType, String peID, String cmr_cyv_code, String caseNum) {
		page = new TerminateReferrals();
		
		page.terminateReferral(ReferralTermination.TERMINATE, caseNum, dbType, peID, cmr_cyv_code);
	}

}
