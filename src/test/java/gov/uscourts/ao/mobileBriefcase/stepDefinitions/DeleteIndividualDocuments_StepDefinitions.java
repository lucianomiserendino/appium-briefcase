package gov.uscourts.ao.mobileBriefcase.stepDefinitions;


import gov.uscourts.ao.mobileBriefcase.Pages.DeleteIndividualDocuments;
import io.cucumber.java.en.Then;

public class DeleteIndividualDocuments_StepDefinitions {

	DeleteIndividualDocuments page;

	@Then("^Verify that tapping the link in the Case Info panel downloads all original documents$")
	public void verify_that_tapping_the_link_in_the_Case_Info_panel_downloads_all_original_documents() {
		page = new DeleteIndividualDocuments();
		page.deleteIndividualDoc();
	}

	@Then("^Verify that swiping over a document in either direction deletes the document$")
	public void verify_that_swiping_over_a_document_in_either_direction_deletes_the_document() {
		page = new DeleteIndividualDocuments();
		page.deleteIndividualDoc();
	}

}
