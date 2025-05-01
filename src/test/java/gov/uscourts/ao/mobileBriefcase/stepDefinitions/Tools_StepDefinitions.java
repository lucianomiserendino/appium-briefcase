package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.ToolsPage;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class Tools_StepDefinitions {
	ToolsPage page;
	
	
	@Then("^Verify that user is prevented from creating duplicate lwk assignments$")
	public void verify_that_user_is_prevented_from_creating_duplicate_lwk_assignments() {
		List<UserInputData> userInputData = null;
		page = new ToolsPage();
		page.duplicateAssignments(userInputData);
	}

}
