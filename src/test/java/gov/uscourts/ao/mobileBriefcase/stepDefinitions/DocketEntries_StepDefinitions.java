package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout;
import static gov.uscourts.ao.mobileBriefcase.common.Base.safariInstance;
import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.DocketEntriesPage;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;

public class DocketEntries_StepDefinitions {

	DocketEntriesPage page;
	String judgesEntries = "";

	@Then("^user gets the entries of the judge \\( \"([^\"]*)\" \\) and logs out$")
	public void user_gets_the_entries_of_the_judge_and_logs_out(String caseNum) {
		page = new DocketEntriesPage();
		judgesEntries += page.getDocketEntries(caseNum);
		JenieLoginPage l=new JenieLoginPage();
		l.logout();
		safariInstance();
	}

	@Then("^user verifies a JA or law clerk can see the same entries as their judge  \\( \"([^\"]*)\" \\)$")
	public void user_verifies_a_JA_or_law_clerk_can_see_the_same_entries_as_their_judge(String caseNum) {
		page = new DocketEntriesPage();
		assertEquals("******A JA OR LAW CLERK CAN'T SEE THE SAME ENTRIES AS THEIR JUDGE******", judgesEntries,
				page.getDocketEntries(caseNum));
	}

	@Given("^User sets the value of the site table variable in \"([^\"]*)\" : briefcaseCtAdminDkt  to \"([^\"]*)\"$")
	public void user_sets_the_value_of_the_site_table_variable_in_briefcaseCtAdminDkt_to(String dbType, String value) {
		page = new DocketEntriesPage();
		page.changeValue(valueOf(dbType), value);
	}

}
