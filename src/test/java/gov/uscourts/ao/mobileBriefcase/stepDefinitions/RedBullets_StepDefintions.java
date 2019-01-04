package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_RedBulletsPage;

public class RedBullets_StepDefintions {
	iOS_RedBulletsPage page;

	int unviewedReferrals = 0;
	String viewedReferrals = "";

	@Then("^User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals$")
	public void user_selects_a_category_that_has_unviewed_referrals_and_verifies_that_the_red_bullet_icon_displays_next_to_any_unviewed_referrals() {
		page = new iOS_RedBulletsPage();
		unviewedReferrals += page.getUnviewedReferral();
		

	}

	@Then("^User taps on a referral and then gets back to the referral list page\\.  Verifies the red bullet is removed indicating the referral has been viewed$")
	public void user_taps_on_a_referral_and_then_gets_back_to_the_referral_list_page_Verifies_the_red_bullet_is_removed_indicating_the_referral_has_been_viewed() {
		viewedReferrals += page.getViewedReferral(unviewedReferrals);

	}

	@Then("^User closes the app and reopen and go back to the category that contains the referral that was just viewed$")
	public void user_closes_the_app_and_reopen_and_go_back_to_the_category_that_contains_the_referral_that_was_just_viewed() {
		page = new iOS_RedBulletsPage();
		page.verifyRedBulletIsRemoved(unviewedReferrals,viewedReferrals);

	}

}
