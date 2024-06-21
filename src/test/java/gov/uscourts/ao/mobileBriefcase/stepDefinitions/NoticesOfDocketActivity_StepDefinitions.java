package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.Pages.NoticesOfDocketActivityPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;

public class NoticesOfDocketActivity_StepDefinitions extends Base {
	NoticesOfDocketActivityPage page;
	JenieLoginPage page1;

	@Then("^user verifies that following items open in Briefcase from the NDA link$")
	public void user_verifies_that_following_items_open_in_Briefcase_from_the_NDA_link(DataTable dataTable) {
		List<UserInputData> userInputData = null;
		page = new NoticesOfDocketActivityPage();
		String peId = DocumentPage.get_pe_id("jud", userInputData);
		page.openNDALink(dataTable, peId, userInputData);

	}

}
