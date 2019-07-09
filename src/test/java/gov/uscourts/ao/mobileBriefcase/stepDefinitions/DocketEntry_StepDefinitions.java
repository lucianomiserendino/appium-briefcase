package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocketEntryPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;

public class DocketEntry_StepDefinitions extends Base {

	DocketEntryPage page;
	String judgesEntries = "";
	CommonPages pages;

	@Then("^user gets the entries of the judge \\( \"([^\"]*)\" \\) and logs out$")
	public void user_gets_the_entries_of_the_judge_and_logs_out(String caseNum) {
		page = new DocketEntryPage();
		judgesEntries += page.getDocketEntries(caseNum);
		pages = new CommonPages();
		pages.logout();
		safariInstance();
		//driver.close();
	}

	@Then("^user verifies a JA or law clerk can see the same entries as their judge  \\( \"([^\"]*)\" \\)$")
	public void user_verifies_a_JA_or_law_clerk_can_see_the_same_entries_as_their_judge(String caseNum) {
		page = new DocketEntryPage();
		assertEquals("******A JA OR LAW CLERK CAN'T SEE THE SAME ENTRIES AS THEIR JUDGE******", judgesEntries,
				page.getDocketEntries(caseNum));
	}

}
