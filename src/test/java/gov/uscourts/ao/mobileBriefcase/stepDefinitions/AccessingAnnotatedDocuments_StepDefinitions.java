package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.AccessingAnnotatedDocuments;

public class AccessingAnnotatedDocuments_StepDefinitions {

	AccessingAnnotatedDocuments page;

	@Then("^User observes annotated documents have a green plus next to them and taps on it to expand the original and annotated documents$")
	public void user_observes_annotated_documents_have_a_green_plus_next_to_them_and_taps_on_it_to_expand_the_original_and_annotated_documents() {
		page = new AccessingAnnotatedDocuments();
		page.getAnnotatedDoc();
	}


}
