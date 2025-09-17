package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.Pages.AccessingAnnotatedDocuments;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Then;

public class AccessingAnnotatedDocuments_StepDefinitions {

	AccessingAnnotatedDocuments page;

	@Then("^User verifies both the top banner editing icons and the editing tool palette are easily visible in the PSPDFKit\\.$")
	public void user_verifies_both_the_top_banner_editing_icons_and_the_editing_tool_palette_are_easily_visible_in_the_PSPDFKit() {
		List<UserInputData> userInputData = null;
		page = new AccessingAnnotatedDocuments();
		page.annotateDocument(DocumentPage.randomDocument, DocumentPage.caseNum, userInputData);

	}

}
