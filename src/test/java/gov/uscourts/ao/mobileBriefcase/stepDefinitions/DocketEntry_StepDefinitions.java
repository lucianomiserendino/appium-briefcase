package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertEquals;


import java.util.List;

import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocketEntryPage;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.Pages.chmSilentAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage.Search;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import io.cucumber.java.en.Then;

public class DocketEntry_StepDefinitions extends Base {
	chmSilentAssignDPFPage silentAssignPage;
	DocketEntryPage page;
	String judgesEntries = "";
	CommonPages pages;


	@Then("^view docket entries$")
	public void view_docket_entries() {
		List<UserInputData> userInputData = null;
		silentAssignPage = new chmSilentAssignDPFPage();
		silentAssignPage.retrieveChmSilentAssignText(userInputData);
	}

}
