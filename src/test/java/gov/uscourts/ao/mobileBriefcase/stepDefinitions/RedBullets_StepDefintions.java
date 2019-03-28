package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.Page.sleep;
import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.LoginPage;
import gov.uscourts.ao.mobileBriefcase.Pages.RedBulletsPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;

public class RedBullets_StepDefintions extends Base {
	RedBulletsPage page;
	LoginPage page1;
	int afterViewingReferral = 0;
	int afterViewingReferral1 = 0;

	@Then("^User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals$")
	public void user_selects_a_category_that_has_unviewed_referrals_and_verifies_that_the_red_bullet_icon_displays_next_to_any_unviewed_referrals() {
		page = new RedBulletsPage();
		page.getUnviewedReferral();

	}

	@Then("^User taps on a referral and then gets back to the referral list page\\.  Verifies the red bullet is removed indicating the referral has been viewed$")
	public void user_taps_on_a_referral_and_then_gets_back_to_the_referral_list_page_Verifies_the_red_bullet_is_removed_indicating_the_referral_has_been_viewed() {
		afterViewingReferral += page.getViewedReferral();

	}

	@Then("^User closes the app and reopen and go back to the category that contains the referral that was just viewed$")
	public void user_closes_the_app_and_reopen_and_go_back_to_the_category_that_contains_the_referral_that_was_just_viewed() {
		driver.closeApp();
		Base.getInstance(Driver.IOS);
		page = new RedBulletsPage();
		assertEquals("WHEN CLOSING AND REOPENING THE APP ITEMS APPEAR AS NEW", afterViewingReferral,
				afterViewingReferral1 += page.verifyRedBulletIsRemoved());
		sleep(30000);
		page1 = new LoginPage();
		page1.logout();
		safariInstance();
		driver.close();
	}

	@Then("^User goes back to the category that contains the referral that was just viewed and verify the bullet does not display$")
	public void user_goes_back_to_the_category_that_contains_the_referral_that_was_just_viewed_and_verify_the_bullet_does_not_display() {
		page = new RedBulletsPage();
		assertEquals("WHEN CLOSING AND REOPENING THE APP ITEMS APPEAR AS NEW", afterViewingReferral,
				page.verifyRedBulletIsRemoved());
	}

}
