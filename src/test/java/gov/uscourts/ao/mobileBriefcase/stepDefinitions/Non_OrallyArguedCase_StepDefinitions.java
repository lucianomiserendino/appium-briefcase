package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilits.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.Non_OrallyArguedCasePage;
import static org.junit.Assert.*;
public class Non_OrallyArguedCase_StepDefinitions {

	Non_OrallyArguedCasePage page;

	@Given("^User clicks on Motions/Pettitions$")
	public void user_clicks_on_Motions_Pettitions() {
		page = new Non_OrallyArguedCasePage();
		performPageLoad();
		page.clickOnMotionsPettitions();

	}

	@Then("^finds the valid non-orally argued categories for the judge$")
	public void finds_the_valid_non_orally_argued_categories_for_the_judge() {
		List<String> DBnonOrallyarguedCases = executeQuery(Queries.SI_VALUE_Y);
		List<String> UInonOrallyarguedCases = Arrays.asList(page.getNumOfdisplayedCases());
		assertTrue(DBnonOrallyarguedCases.containsAll(UInonOrallyarguedCases));

	}

	@Then("^User selects case \"([^\"]*)\"$")
	public void user_selects_case(String caseNum) {
		page.selectCase(caseNum);

	}

	@Then("^User taps on pdf doc in cmecf and verifies that it is downloaded from the server and opens in Briefcase$")
	public void user_taps_on_pdf_doc_in_cmecf_and_verifies_that_it_is_downloaded_from_the_server_and_opens_in_Briefcase() throws InterruptedException, IOException
			 {
		page.downloadPDFDoc();
	}

}
