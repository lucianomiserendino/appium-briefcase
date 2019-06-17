package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.Pages.RedBulletsPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Page;

public class RedBullets_StepDefintions extends Base {
	RedBulletsPage page;
	JenieLoginPage page1;
	CommonPages commonPages;
	int afterViewingReferral = 0;

	@Then("^User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals, taps on a referral and then gets back to the referral list page$")
	public void user_selects_a_category_that_has_unviewed_referrals_and_verifies_that_the_red_bullet_icon_displays_next_to_any_unviewed_referrals_taps_on_a_referral_and_then_gets_back_to_the_referral_list_page() {
		page = new RedBulletsPage();
		afterViewingReferral = page.getUnviewedReferral();
	}

	@Then("^User closes the app and reopen and go back to the \"([^\"]*)\" that contains the referral that was just viewed$")
	public void user_closes_the_app_and_reopen_and_go_back_to_the_that_contains_the_referral_that_was_just_viewed(
			String refCategory) {
		driver.closeApp();
		Base.getInstance(Driver.IOS);
		Page.performPageLoad(driver);
		commonPages = new CommonPages();
		commonPages.selectReferral("//XCUIElementTypeOther[@name='Categories']" + containsElement(refCategory));
		page = new RedBulletsPage();
		assertEquals("WHEN CLOSING AND REOPENING THE APP ITEMS APPEAR AS NEW", afterViewingReferral,
				page.getTotalNumOfNewReferrals());
		commonPages = new CommonPages();
		commonPages.logout();
		safariInstance();
		driver.close();
	}

	@Then("^User goes back to the \"([^\"]*)\" that contains the referral that was just viewed and verify the bullet does not display$")
	public void user_goes_back_to_the_that_contains_the_referral_that_was_just_viewed_and_verify_the_bullet_does_not_display(
			String refCategory) {
		commonPages = new CommonPages();
		commonPages.selectReferral("//XCUIElementTypeOther[@name='Categories']" + containsElement(refCategory));
		Page.performPageLoad(driver);
		page = new RedBulletsPage();
		assertEquals("WHEN CLOSING AND REOPENING THE APP ITEMS APPEAR AS NEW", afterViewingReferral,
				page.getTotalNumOfNewReferrals());
	}

}
