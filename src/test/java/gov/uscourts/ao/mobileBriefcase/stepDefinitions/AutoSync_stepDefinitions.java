package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.AutoSyncPage;

public class AutoSync_stepDefinitions {

	AutoSyncPage page;
	String syncCount;

	@Then("^Verify the individual case referrals have a Sync link in the Case Information panel that downloads the documents in that particular case\\.$")
	public void verify_the_individual_case_referrals_have_a_Sync_link_in_the_Case_Information_panel_that_downloads_the_documents_in_that_particular_case()
			throws Throwable {
		page = new AutoSyncPage();
		assertTrue(page.getReferralSync());

	}

	@Then("^note the total documents available for download count in the Sync button on the Dashboard$")
	public void note_the_total_documents_available_for_download_count_in_the_Sync_button_on_the_Dashboard() {
		page = new AutoSyncPage();
		//syncCount = page.getCounter();
	}

	@Then("^Verify that tapping the link in the Case Info panel downloads all original documents, decreases the total count in the device Sync button correctly$")
	public void verify_that_tapping_the_link_in_the_Case_Info_panel_downloads_all_original_documents_decreases_the_total_count_in_the_device_Sync_button_correctly() {
		page = new AutoSyncPage();
		page.getSyncCount(Integer.parseInt(syncCount));
	}

}
