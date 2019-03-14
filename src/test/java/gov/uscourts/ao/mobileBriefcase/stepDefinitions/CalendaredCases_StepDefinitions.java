package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CalendarPage;

public class CalendaredCases_StepDefinitions {
	CalendarPage page;

	@Then("^User selects a session$")
	public void user_selects_a_session() {
		page = new CalendarPage();
		page.getWeeklySession(valueOf("CMKA"), "32");
	}

	@Then("^User selects a session and verifies days are displayed corrcetly in that session,DB is  \"([^\"]*)\" and judge's peID is \"([^\"]*)\"$")
	public void user_selects_a_session_and_verifies_days_are_displayed_corrcetly_in_that_session_DB_is_and_judge_s_peID_is(
			String dbType, String peID) {
		page = new CalendarPage();
		page.getWeeklySession(valueOf(dbType), peID);
	}

}
