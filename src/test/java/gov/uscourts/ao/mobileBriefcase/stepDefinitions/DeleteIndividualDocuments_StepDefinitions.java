package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.DeleteIndividualDocuments;

public class DeleteIndividualDocuments_StepDefinitions {
	DeleteIndividualDocuments page;

	@Then("^hjgdf$")
	public void hjgdf() {
		page=new	DeleteIndividualDocuments ();
		page.deleteDoc();
	}
}
