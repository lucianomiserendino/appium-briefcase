package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
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
}
