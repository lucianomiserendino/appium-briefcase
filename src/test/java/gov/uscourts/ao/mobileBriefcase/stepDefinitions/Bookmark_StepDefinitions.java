package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.BookmarkPage;

public class Bookmark_StepDefinitions {

	BookmarkPage page;

	@Given("^User selects a referral category on the dashboard page \\(i\\.e\\., Motions/Petitions, Petitions for Rehearing, etc\\.\\)$")
	public void user_selects_a_referral_category_on_the_dashboard_page_i_e_Motions_Petitions_Petitions_for_Rehearing_etc() {
		page = new BookmarkPage();
		page.selectReferralCategory();
	}

	@When("^User selects a case and tap on the bookmark icon in the top red banner$")
	public void user_selects_a_case_and_tap_on_the_bookmark_icon_in_the_top_red_banner() {
		page.tapOnTheBookmark(1);
		page.tapOnTheBookmark(5);
		page.tapOnTheBookmark(8);

	}

	@Then("^User Verifies the bookmark icon displays in the navigation and on the dashboard page\\.$")
	public void user_Verifies_the_bookmark_icon_displays_in_the_navigation_and_on_the_dashboard_page() {
		page.verifyBookmarkedDisplyedOnDashboard();
	}

	@Then("^User  Verifies tapping on the bookmark icon in the navigation or on the dashboard page displays the bookmarked referral under the referral category heading\\.$")
	public void user_Verifies_tapping_on_the_bookmark_icon_in_the_navigation_or_on_the_dashboard_page_displays_the_bookmarked_referral_under_the_referral_category_heading() {
		page.verifyBookmarkedDisplyedUnderTheRefCategory();
	}

	@Then("^User  taps on the bookmark icon next to the case you just bookmarked and verify the case is removed from the bookmark category\\.$")
	public void user_taps_on_the_bookmark_icon_next_to_the_case_you_just_bookmarked_and_verify_the_case_is_removed_from_the_bookmark_category() {

	}

}
