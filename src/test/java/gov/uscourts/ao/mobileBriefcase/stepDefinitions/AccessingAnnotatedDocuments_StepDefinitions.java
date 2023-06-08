package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.AccessingAnnotatedDocuments;
import gov.uscourts.ao.mobileBriefcase.Pages.BackgroundColor;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class AccessingAnnotatedDocuments_StepDefinitions {

	AccessingAnnotatedDocuments page;
	DocumentPage page1;
	BackgroundColor p;

	@Then("^User observes annotated documents have a green plus next to them and taps on it to expand the original and annotated documents$")
	public void user_observes_annotated_documents_have_a_green_plus_next_to_them_and_taps_on_it_to_expand_the_original_and_annotated_documents() {
		page = new AccessingAnnotatedDocuments();
		page.getAnnotatedDoc();
	}

	@Then("^I verify that the toggles on the settings page under the PDF Options heading entitled Back up Annotations to CM/ECF, and Allow staff to view annotated documents are turned on by default$")
	public void i_verify_that_the_toggles_on_the_settings_page_under_the_PDF_Options_heading_entitled_Back_up_Annotations_to_CM_ECF_and_Allow_staff_to_view_annotated_documents_are_turned_on_by_default() {

//	 page.getToggle();
//		List<UserInputData> userInputData = null;
//		page.annotateDocument("OFF", Document_StepDefinitions.regularCase, userInputData);
//		page.searchForAppendix();
		List<UserInputData> userInputData = null;
//		page1 = new DocumentPage();
//		page1.getDocumentList(userInputData) ;
//		

	}

	@Then("^User verifies both the top banner editing icons and the editing tool palette are easily visible in the PSPDFKit\\.$")
	public void user_verifies_both_the_top_banner_editing_icons_and_the_editing_tool_palette_are_easily_visible_in_the_PSPDFKit() {
		List<UserInputData> userInputData = null;
		page = new AccessingAnnotatedDocuments();
		page.annotateDocument(DocumentPage.randomDocument, Document_StepDefinitions.regularCase, userInputData);

	}

	@Then("^User turns the Single page view mode on, from the settings page$")
	public void user_turns_the_Single_page_view_mode_on_from_the_settings_page() {
		page = new AccessingAnnotatedDocuments();
		page.landscapeMode();
	}

	@Then("^Verifies that it allows the user to view documents on the iPad in landscape mode a single page at a time$")
	public void verifies_that_it_allows_the_user_to_view_documents_on_the_iPad_in_landscape_mode_a_single_page_at_a_time() {
		page = new AccessingAnnotatedDocuments();
		page.verifySinglePageModeIsOn();
	}

}
