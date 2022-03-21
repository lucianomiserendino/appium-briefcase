package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.AutoSyncPage;

public class AutoSync_stepDefinitions {

	AutoSyncPage page;
	
	@Then("^Verify the individual case referrals have a Sync link in the Case Information panel that downloads the documents in that particular case\\.$")
	public void verify_the_individual_case_referrals_have_a_Sync_link_in_the_Case_Information_panel_that_downloads_the_documents_in_that_particular_case() throws Throwable {
		page= new AutoSyncPage ();
		assertTrue(page.getReferralSync());
	}
	
	
	


}
