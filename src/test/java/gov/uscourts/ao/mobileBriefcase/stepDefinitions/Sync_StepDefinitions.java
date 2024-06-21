package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.AutoSyncPage;
import gov.uscourts.ao.mobileBriefcase.Pages.SyncPage;
import gov.uscourts.ao.mobileBriefcase.Pages.SyncPage.SyncType;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class Sync_StepDefinitions {

	SyncPage page;

	@Then("^sync all the documents for the selected case$")
	public void sync_all_the_documents_for_the_selected_case() {
		page = new SyncPage();
		List<UserInputData> userInputData = null;
		page.getSync(SyncType.Case_Detail, userInputData);
	}
	
	@Then("^User taps Sync with CM/ECF button on the Dashboard page and verify the Sync completes\\.$")
	public void user_taps_Sync_with_CM_ECF_button_on_the_Dashboard_page_and_verify_the_Sync_completes() {
		page = new SyncPage();	
		List<UserInputData> userInputData = null;
		page.getSync(SyncType.Dashboard, userInputData);
	}
	@Then("^User taps Sync button in the Referral List Page and verifies that all original documents are downloaded$")
	public void user_taps_Sync_button_in_the_Referral_List_Page_and_verifies_that_all_original_documents_are_downloaded()  {
		page = new SyncPage();	
		List<UserInputData> userInputData = null;
		page.getSync(SyncType.Referral_Category, userInputData);
	}

	
	@Then("^User taps Sync button in the Case Info panel and verifies that all original documents are downloaded$")
	public void user_taps_Sync_button_in_the_Case_Info_panel_and_verifies_that_all_original_documents_are_downloaded() {
		page = new SyncPage();	
		List<UserInputData> userInputData = null;
		page.getSync(SyncType.Case_Detail, userInputData);
	}
}
