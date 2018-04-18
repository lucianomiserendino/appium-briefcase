package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilits.executeQuery;
import static gov.uscourts.ao.moibleBriefcase.common.Page.performPageLoad;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.Non_OrallyArguedCasePage;

public class Non_OrallyArguedCase_StepDefinitions {

	Non_OrallyArguedCasePage page;

	@Given("^User clicks on Motions/Pettitions$")
	public void user_clicks_on_Motions_Pettitions() throws Throwable {
		page = new Non_OrallyArguedCasePage();
		performPageLoad();
		page.clickOnMotionsPettitions();

	}

	@Then("^finds the valid non-orally argued categories for the judge$")
	public void finds_the_valid_non_orally_argued_categories_for_the_judge() {
		List<String> DBnonOrallyarguedCases = executeQuery(Queries.SI_VALUE_N);
		List<String> UInonOrallyarguedCases = Arrays.asList(page.getNumOfdisplayedCases());
		assertTrue(DBnonOrallyarguedCases.containsAll(UInonOrallyarguedCases));

	}

}
