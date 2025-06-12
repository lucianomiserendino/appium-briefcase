package gov.uscourts.ao.mobileBriefcase.stepDefinitions;


import gov.uscourts.ao.mobileBriefcase.Pages.TerminateReferrals;
import gov.uscourts.ao.mobileBriefcase.Pages.TerminateReferrals.ReferralTermination;
import io.cucumber.java.en.Then;

public class ReferralTermination_StepDefinitions {
	TerminateReferrals page;
	int numOfReferrals;

	@Then("^User selects a  referral\\.  Makes note of the case number and referral type$")
	public void user_selects_a_referral_Makes_note_of_the_case_number_and_referral_type() {
		page = new TerminateReferrals();
		numOfReferrals = page.getTotalNumOfReferrals();

	}

	@Then("^User  sets the chambers_case_to_referral\\.ccr_date_end date field to today's date  in \"([^\"]*)\" , using  \"([^\"]*)\" and  \"([^\"]*)\", indicating the referral \"([^\"]*)\" is terminated\\.$")
	public void user_sets_the_chambers_case_to_referral_ccr_date_end_date_field_to_today_s_date_in_using_and_indicating_the_referral_is_terminated(
			String dbType, String peID, String cmr_cyv_code, String caseNum) {
		page = new TerminateReferrals();

		page.terminateReferral(ReferralTermination.TERMINATE, caseNum, dbType, peID, cmr_cyv_code);

	}

	@Then("^User goes to the Dashboard page in Briefcase\\.User  executes MobileBriefcaseDataUpdater\\.Waits the number of minutes stored in the briefcaseAutoSyncMinutes site table variable$")
	public void user_goes_to_the_Dashboard_page_in_Briefcase_User_executes_MobileBriefcaseDataUpdater_Waits_the_number_of_minutes_stored_in_the_briefcaseAutoSyncMinutes_site_table_variable() {
		// page = new TerminateReferrals();
		page.runDataUpdater();
	}

	@Then("^User navigates to the referral category and verify the referral does not display anymore because it was terminated\\.$")
	public void user_navigates_to_the_referral_category_and_verify_the_referral_does_not_display_anymore_because_it_was_terminated() {

		page = new TerminateReferrals();

	}

}
