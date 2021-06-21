package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class BookmarkedListPage extends AppiumPageFactory {
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public static MobileElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Bookmarked']")
	public static List<MobileElement> bookOnDashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Bookmark']")
	public static List<MobileElement> BookmarkedReferrals;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<MobileElement> cases;

	public void getBookmarkedReferrals() {
		try {
			if (bookOnDashboard.size() > 0) {
				tap(bookOnDashboard.get(bookOnDashboard.size() - 1));
				getBookmarkIcons();
			} else {
				assertEquals(0, bookOnDashboard.size());
			}
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
		tap(dashboard);
	}

	public void getBookmarkIcons() {
		while (BookmarkedReferrals.size() > 0) {
			tap(BookmarkedReferrals.get(0));
		}
		assertEquals(0, BookmarkedReferrals.size());
	}

	public String getReferrals() {
		String bookmark = getText(cases.get(0)).split(" ")[0];
		bookmarkReferral(bookmark, 1);
		return bookmark;

	}

	public void removeBookmarkedReferral(String referral) {
		tap(bookOnDashboard.get(0));
		assertTrue(contains(referral).isDisplayed());
		bookmarkReferral(referral, 0);
	}

	public void bookmarkReferral(String referral, int bookmarkedReferral) {
		tap(Locator.XPATH,
				"(" + containsElement(referral) + "/following::XCUIElementTypeStaticText[@name='Bookmark'])[1]");
		tap(dashboard);
		assertEquals(bookmarkedReferral, bookOnDashboard.size());
	}

}
