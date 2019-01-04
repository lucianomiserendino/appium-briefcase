package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_NoticesOfDocketActivityPage;

public class NoticesOfDocketActivity_StepDefinitions {
	iOS_NoticesOfDocketActivityPage page;

	@Then("^user  selects  case \"([^\"]*)\" that is in Briefcase for a judge\\.\\( \"([^\"]*)\" \\) Verifies the docket entry page in Briefcase displays the docket entry\\( Open a Docket Entry in Briefcase from the NDA link\\)$")
	public void user_selects_case_that_is_in_Briefcase_for_a_judge_Verifies_the_docket_entry_page_in_Briefcase_displays_the_docket_entry_Open_a_Docket_Entry_in_Briefcase_from_the_NDA_link(
			String caseNum, String dbType) {
		page = new iOS_NoticesOfDocketActivityPage();
		page.openADktEntryInBriefcase(caseNum, dbType);
	}

	@Then("^user verifies the document opens in briefcase by using the same \"([^\"]*)\" and \"([^\"]*)\" \\(Open a document in Briefcase from the NDA link\\)\\.$")
	public void user_verifies_the_document_opens_in_briefcase_by_using_the_same_and_Open_a_document_in_Briefcase_from_the_NDA_link(
			String caseNum, String dbType) {
		page.openADocumentInBriefCase(caseNum, dbType);
	}

	@Then("^user verifies the note opens in Briefcase by using the same \"([^\"]*)\" and \"([^\"]*)\"  \\(Open a note in Briefcase from the NDA link\\)$")
	public void user_verifies_the_note_opens_in_Briefcase_by_using_the_same_and_Open_a_note_in_Briefcase_from_the_NDA_link(
			String caseNum, String dbType) {
		page.openANoteInBriefcase(caseNum, dbType);
	}

}
