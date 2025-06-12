package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.Pages.AccessingAnnotatedDocuments;
import gov.uscourts.ao.mobileBriefcase.Pages.BackgroundColor;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Then;

public class AccessingAnnotatedDocuments_StepDefinitions {

	AccessingAnnotatedDocuments page;
	DocumentPage page1;
	BackgroundColor p;

	@Then("^User observes annotated documents have a green plus next to them and taps on it to expand the original and annotated documents$")
	public void user_observes_annotated_documents_have_a_green_plus_next_to_them_and_taps_on_it_to_expand_the_original_and_annotated_documents() {
//		page = new AccessingAnnotatedDocuments();
//		page.getAnnotatedDoc();
	}

	@Then("^User verifies both the top banner editing icons and the editing tool palette are easily visible in the PSPDFKit\\.$")
	public void user_verifies_both_the_top_banner_editing_icons_and_the_editing_tool_palette_are_easily_visible_in_the_PSPDFKit() {
		List<UserInputData> userInputData = null;
		page = new AccessingAnnotatedDocuments();
		page.annotateDocument(DocumentPage.randomDocument, DocumentPage.caseNum, userInputData);
	
		
	}

}
