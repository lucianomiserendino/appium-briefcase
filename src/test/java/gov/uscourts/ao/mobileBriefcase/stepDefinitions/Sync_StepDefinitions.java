package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_SyncPage;

public class Sync_StepDefinitions {

	iOS_SyncPage page;

	@Then("^User licks/Tap Sync with CM/ECF button on the Dashboard page and verify the Sync completes\\.$")
	public void user_licks_Tap_Sync_with_CM_ECF_button_on_the_Dashboard_page_and_verify_the_Sync_completes()
		 {
		page = new iOS_SyncPage();

		page.verifySyncCompletes();
	}
}
