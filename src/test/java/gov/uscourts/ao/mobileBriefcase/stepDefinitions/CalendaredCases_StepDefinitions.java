package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.CalendarPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class CalendaredCases_StepDefinitions {
	CalendarPage page;
	String hearingDate = "";
	String oralArgView = "";
	String courtSession = "";

	@When("^User verifies that the main headers display Month Year and sorted accordingly$")
	public void user_verifies_that_the_main_headers_display_Month_Year_and_sorted_accordingly() {
		page = new CalendarPage();
		List<UserInputData> userInputData = null;
		oralArgView = page.getbriefcaseOralArgsView("briefcaseOralArgsView", userInputData);
		courtSession = page.getbriefcaseOralArgsView("briefcaseUseCourtSession", userInputData);

		page.isSortedByMonthAndYear();

	}

	@Then("^User verifies that there're Dates and Day of the week under main header$")
	public void user_verifies_that_there_re_Dates_and_Day_of_the_week_under_main_header() {
		page = new CalendarPage();
		List<UserInputData> userInputData = null;
		oralArgView = page.getbriefcaseOralArgsView("briefcaseOralArgsView", userInputData);
		courtSession = page.getbriefcaseOralArgsView("briefcaseUseCourtSession", userInputData);
		hearingDate += page.getSubHeader(oralArgView, courtSession);
	}

	@When("^accordion for a date are expanded User should see Case number, Case Title, Panel, also Order$")
	public void accordion_for_a_date_are_expanded_User_should_see_Case_number_Case_Title_Panel_also_Order() {
		page = new CalendarPage();
		List<UserInputData> userInputData = null;
		assertTrue("CASES NOT APPEARING UNDER THE CORRECT DATE BUCKET: " + hearingDate.toUpperCase(),
				page.selectRandomCase(page.caseN,hearingDate, userInputData));
	}

}
