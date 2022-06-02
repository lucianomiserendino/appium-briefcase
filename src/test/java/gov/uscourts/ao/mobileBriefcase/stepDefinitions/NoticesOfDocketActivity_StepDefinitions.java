package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.Pages.NoticesOfDocketActivityPage;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;

public class NoticesOfDocketActivity_StepDefinitions extends Base {
	NoticesOfDocketActivityPage page;
	JenieLoginPage page1;

	@Then("^user  selects  case \"([^\"]*)\" that is in Briefcase for a judge\\.\\( \"([^\"]*)\" \\) Verifies the docket entry page in Briefcase displays the docket entry\\( Open a Docket Entry in Briefcase from the NDA link\\)$")
	public void user_selects_case_that_is_in_Briefcase_for_a_judge_Verifies_the_docket_entry_page_in_Briefcase_displays_the_docket_entry_Open_a_Docket_Entry_in_Briefcase_from_the_NDA_link(
			String caseNum, String dbType) {
		//page = new NoticesOfDocketActivityPage();
		page.openADktEntryInBriefcase(Document_StepDefinitions.referral, dbType, "briefcaseAppLinkRoot");
	}

	@Then("^user verifies the document opens in briefcase by using the same \"([^\"]*)\" and \"([^\"]*)\" \\(Open a document in Briefcase from the NDA link\\)\\.$")
	public void user_verifies_the_document_opens_in_briefcase_by_using_the_same_and_Open_a_document_in_Briefcase_from_the_NDA_link(
			String caseNum, String dbType) {
		page = new NoticesOfDocketActivityPage();
		page.openADocumentInBriefCase(Document_StepDefinitions.referral, dbType, "briefcaseAppLinkRoot");
	}

	@Then("^user verifies the note opens in Briefcase by using the same \"([^\"]*)\" and \"([^\"]*)\"  \\(Open a note in Briefcase from the NDA link\\)$")
	public void user_verifies_the_note_opens_in_Briefcase_by_using_the_same_and_Open_a_note_in_Briefcase_from_the_NDA_link(
			String caseNum, String dbType) {
		page.openANoteInBriefcase(Document_StepDefinitions.referral, dbType, "briefcaseAppLinkRoot");

	}

	@Then("^User deletes all docs from the device$")
	public void user_deletes_all_docs_from_the_device() {
		CommonPages cmPages = new CommonPages();
		cmPages.deleteDocs();
	}

	@Then("^User taps document pdf doc in cmecf and verify that it is downloaded from the server and opens in Briefcase$")
	public void user_taps_document_pdf_doc_in_cmecf_and_verify_that_it_is_downloaded_from_the_server_and_opens_in_Briefcase() {
		page = new NoticesOfDocketActivityPage();
		page.verifyPDFIsDownloaded();
	}

}
