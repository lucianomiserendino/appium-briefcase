package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.Pages.CalendarPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Then;

public class CalendaredCases_StepDefinitions {
	CalendarPage page;

	@Then("^User selects a session and verifies days are displayed corrcetly in that session, judge's peID is \"([^\"]*)\"$")
	public void user_selects_a_session_and_verifies_days_are_displayed_corrcetly_in_that_session_judge_s_peID_is(
			String peID, List<UserInputData> table) {
		page = new CalendarPage();
		page.getWeeklySession(peID, table);
	}

}
