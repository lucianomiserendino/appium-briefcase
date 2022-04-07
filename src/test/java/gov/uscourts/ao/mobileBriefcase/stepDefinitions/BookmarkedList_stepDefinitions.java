package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.BookmarkedListPage;

public class BookmarkedList_stepDefinitions {
	String caseNumber = "";
	BookmarkedListPage page = new BookmarkedListPage();

	@Then("^User verifies there's no bookmark icon displays in the navigation and on the dashboard$")
	public void user_verifies_there_s_no_bookmark_icon_displays_in_the_navigation_and_on_the_dashboard() {
		page.getBookmarkedReferrals();
	}

	@Then("^user  taps on the bookmark icon next to a case  and verifies the bookmark icon displays in the navigation and on the dashboard page\\.$")
	public void user_taps_on_the_bookmark_icon_next_to_a_case_and_verifies_the_bookmark_icon_displays_in_the_navigation_and_on_the_dashboard_page() {
		caseNumber = page.getReferrals();
	}

	@Then("^user taps on bookmark icon in the navigation or on the dashboard then taps on the bookmark icon next to the case he just bookmarked and verifies the case is removed from the bookmark category$")
	public void user_taps_on_bookmark_icon_in_the_navigation_or_on_the_dashboard_then_taps_on_the_bookmark_icon_next_to_the_case_he_just_bookmarked_and_verifies_the_case_is_removed_from_the_bookmark_category() {
		page.removeBookmarkedReferral(caseNumber);
	}

}
