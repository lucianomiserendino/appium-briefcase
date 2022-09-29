package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CaseDetailPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class CaseDetail_StepDefinitions {

	CaseDetailPage page;

	@Then("^User verifies that there's an option for judges to create an internal note\\.$")
	public void user_verifies_that_there_s_an_option_for_judges_to_create_an_internal_note() {
		page = new CaseDetailPage();
		List<UserInputData> pacerInputData=null;
		page.ifInternalNoteExists(pacerInputData);
	}

}
