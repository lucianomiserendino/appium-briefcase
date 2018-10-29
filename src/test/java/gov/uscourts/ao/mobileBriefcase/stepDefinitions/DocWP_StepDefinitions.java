package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_DocWPPage;
import gov.uscourts.ao.mobileBriefcase.models.ElListText;

public class DocWP_StepDefinitions {
	iOS_DocWPPage page;

	@Then("^User  selects action in \"([^\"]*)\" and verifies the name of the action displays in the dark blue banner$")
	public void user_selects_action_in_and_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner(
			String dbType, List<ElListText> table) {
		page = new iOS_DocWPPage();
		page.selectAction(table, 0, dbType);
	}

	@Then("^User verifies  the text \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\" display on the page$")
	public void user_verifies_the_text_display_on_the_page(String UploadDocuments, String notSelected,
			String enterDescription, String selectFile, String removeFile, String submit) {
		page.verifyUploadDocPageIsDisplayed(UploadDocuments, notSelected, enterDescription, selectFile, removeFile,
				submit);

	}
}
