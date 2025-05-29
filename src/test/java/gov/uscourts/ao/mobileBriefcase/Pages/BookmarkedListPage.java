package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class BookmarkedListPage extends AppiumPageFactory {
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Bookmarked']")
	public static List<WebElement> bookOnDashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Bookmark\"]")
	public static List<WebElement> BookmarkedReferrals;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> cases;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'linked')]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static WebElement targetCase;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'linked')]/following::XCUIElementTypeButton[@name='Bookmark'][1]")
	public static WebElement bookmarkIcon;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;
	

	public void getBookmarkedReferrals() {
		collapseBtn.click();
		
		if (bookOnDashboard.size() > 0) {
			tap(bookOnDashboard.get(bookOnDashboard.size() - 1));
			collapseBtn.click();
			getBookmarkIcons();
			collapseBtn.click();
			collapseBtn.click();
		} else {
			assertEquals(0, bookOnDashboard.size());
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
		Page.waitForVisibilityOfElement(collapseBtn, driver).click();
		bookmarkReferral(bookmark, 1);
		return bookmark;

	}

	public void removeBookmarkedReferral(String referral) {
		tap(bookOnDashboard.get(0));
		collapseBtn.click();
		assertTrue(contains(referral).isDisplayed());
		bookmarkReferral(referral, 0);
	}

	public void bookmarkReferral(String referral, int expectedCount) {
	    tap(Locator.XPATH,
	            "(" + containsElement(referral) + "/following::XCUIElementTypeButton[@name='Bookmark'])[1]");
	    collapseBtn.click();
       if (expectedCount==0) {
    	 collapseBtn.click();
       }
	    new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> bookOnDashboard.size() == expectedCount);

	    assertEquals(expectedCount, bookOnDashboard.size());
	}


	public void bookmarkAcase() {

		String referralDeatilPage = getReferral();
		bookmarkIcon.click();
		dashboard.click();
		bookOnDashboard.get(0).click();
		collapseBtn.click();
		String bookmarkDeatilPage = getReferral();

		assertEquals(
				"APPLIED CASES LINK ICON DISAPPEARS WHEN BOOKMARKING CASE/REFERRAL",referralDeatilPage, bookmarkDeatilPage);

	}

	public String getReferral() {
		return targetCase.getText().split(" ")[0].trim();
	}

}
