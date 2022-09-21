package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.AccessingAnnotatedDocuments;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class AccessingAnnotatedDocuments_StepDefinitions {

	AccessingAnnotatedDocuments page;

	@Then("^User observes annotated documents have a green plus next to them and taps on it to expand the original and annotated documents$")
	public void user_observes_annotated_documents_have_a_green_plus_next_to_them_and_taps_on_it_to_expand_the_original_and_annotated_documents() {
		page = new AccessingAnnotatedDocuments();
		page.getAnnotatedDoc();
	}

	@Then("^I verify that the toggles on the settings page under the PDF Options heading entitled Back up Annotations to CM/ECF, and Allow staff to view annotated documents are turned on by default$")
	public void i_verify_that_the_toggles_on_the_settings_page_under_the_PDF_Options_heading_entitled_Back_up_Annotations_to_CM_ECF_and_Allow_staff_to_view_annotated_documents_are_turned_on_by_default() {
		page = new AccessingAnnotatedDocuments();
	 page.getToggle();
		List<UserInputData> userInputData = null;
		page.annotateDocument("OFF", Document_StepDefinitions.regularCase, userInputData);
		page.searchForAppendix();
	}

}
