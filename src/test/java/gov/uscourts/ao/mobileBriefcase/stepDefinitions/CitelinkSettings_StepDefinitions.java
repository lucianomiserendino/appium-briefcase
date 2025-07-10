package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import gov.uscourts.ao.mobileBriefcase.Pages.AccessingAnnotatedDocuments;
import gov.uscourts.ao.mobileBriefcase.Pages.CitelinkSettings;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CitelinkSettings_StepDefinitions {
	CitelinkSettings page;
	public static String searchEngine;
	AccessingAnnotatedDocuments annotPage;

	@Then("^Verify the cached documents are not deleted and the user is presented with the message if the cite link settings change$")
	public void verify_the_cached_documents_are_not_deleted_and_the_user_is_presented_with_the_message_if_the_cite_link_settings_change() {
		page = new CitelinkSettings();
		page.changesCitelinkSettings();
	}

	@When("^User switches the Use My CM/ECF Settings toggle off, select a random Briefcase citelink preferences$")
	public void user_switches_the_Use_My_CM_ECF_Settings_toggle_off_select_a_random_Briefcase_citelink_preferences() {
				page = new CitelinkSettings();
		searchEngine=page.changesCitelinkSettings();
	}
	
	
	@Then("^User scrolls through the document until finds a citation link, taps it and verifies The link opens to the correct interface$")
	public void user_scrolls_through_the_document_until_finds_a_citation_link_taps_it_and_verifies_The_link_opens_to_the_correct_interface()  {
		
		annotPage=new AccessingAnnotatedDocuments();
		annotPage.randomPage();
		page = new CitelinkSettings();
		page.getCiteLink(searchEngine);
		
	}


}
