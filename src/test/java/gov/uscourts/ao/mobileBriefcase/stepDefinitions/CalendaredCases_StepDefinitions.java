package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.CalendarPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class CalendaredCases_StepDefinitions {
	CalendarPage page;
	String hearingDate="";

	@When("^User verifies that the main headers display Month Year and sorted accordingly$")
	public void user_verifies_that_the_main_headers_display_Month_Year_and_sorted_accordingly() {
		page = new CalendarPage();
		page.isSortedByMonthAndYear();

	}

	@Then("^User verifies that there're Dates and Day of the week under main header$")
	public void user_verifies_that_there_re_Dates_and_Day_of_the_week_under_main_header() {
		page = new CalendarPage();
		hearingDate+=page.expandSubAccordion();
	}

	@When("^accordion for a date are expanded User should see Case number, Case Title, Panel, also Order$")
	public void accordion_for_a_date_are_expanded_User_should_see_Case_number_Case_Title_Panel_also_Order() {
		page = new CalendarPage();
		List<UserInputData> userInputData = null;
		page.selectRandomCase(hearingDate,userInputData);
	}

}
