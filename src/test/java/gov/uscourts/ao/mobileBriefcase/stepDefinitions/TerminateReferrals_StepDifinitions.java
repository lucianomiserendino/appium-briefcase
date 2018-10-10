package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase.closeWebDriver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.sleep;
import static gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities.DATA_UPDATER;

import org.apache.poi.hpsf.NoSingleSectionException;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_TerminateReferrals;

public class TerminateReferrals_StepDifinitions {

	iOS_TerminateReferrals page;
	CopyDeleteCase page1;
	iOS_CommonPages page2;

	@Then("^User  sets the chambers_case_to_referral\\.ccr_date_end date field to today's date  in \"([^\"]*)\" , using  \"([^\"]*)\"  , indicating the referral \"([^\"]*)\" is terminated\\.$")
	public void user_sets_the_chambers_case_to_referral_ccr_date_end_date_field_to_today_s_date_in_using_indicating_the_referral_is_terminated(
			String dbType, String peId, String caseNum) {
		page = new iOS_TerminateReferrals();
		page.terminateReferral(caseNum, dbType, peId);
	}

	@Then("^User goes to the Dashboard page in Briefcase\\.User  executes MobileBriefcaseDataUpdater\\.Waits the number of minutes stored in the briefcaseAutoSyncMinutes site table variable$")
	public void user_goes_to_the_Dashboard_page_in_Briefcase_User_executes_MobileBriefcaseDataUpdater_Waits_the_number_of_minutes_stored_in_the_briefcaseAutoSyncMinutes_site_table_variable()
			{
		page1 = new CopyDeleteCase();
		try {
			page1.getURLandLogin(DATA_UPDATER);

		} catch (Exception e) {
			e.getMessage();
		} finally {
			sleep(90000);
			closeWebDriver();
		}
	}

	@Then("^User navigates to the \"([^\"]*)\" and verify the referral \"([^\"]*)\" does not display anymore in UI because it was terminated and \"([^\"]*)\" CCR_DATE_END is not null, use \"([^\"]*)\"$")
	public void user_navigates_to_the_and_verify_the_referral_does_not_display_anymore_in_UI_because_it_was_terminated_and_CCR_DATE_END_is_not_null_use(
			String refCategory, String referral, String dbType, String peId) {
		page = new iOS_TerminateReferrals();
		try {
			page.verifyReferralIsTerminated(refCategory, referral, dbType, peId);

		} catch (NoSingleSectionException e) {
			e.getMessage();
		} finally {

			page2 = new iOS_CommonPages();
			page2.logOut();

		}
	
	}
}
