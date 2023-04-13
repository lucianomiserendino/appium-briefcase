package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.split;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class RedBulletsPage extends AppiumPageFactory {
	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "Back")
	public WebElement back;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Total')]")
	public WebElement totalNumOfNewReferrals;
//(//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, 'Viewed')])[1]/preceding::XCUIElementTypeStaticText[contains(@name, '-')]
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]/preceding::XCUIElementTypeStaticText[contains(@name, 'Viewed')])[1]")
	public List<WebElement> unviewedReferrals;
	
	public static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";


	public int getUnviewedReferral() {
		WebElement newRef = null;
		int newReferrals = getTotalNumOfNewReferrals();
		if (unviewedReferrals.size() > 1) {
			newRef = unviewedReferrals.get(unviewedReferrals.size() - 1);
		} else {
			newRef = unviewedReferrals.get(0);
		}
		tap(newRef);
		Page.performPageLoad(driver);
		driver.navigate().back();
		Page.performPageLoad(driver);

		int totalNewReferrals =getTotalNumOfNewReferrals();
		assertEquals(newReferrals - 1, totalNewReferrals);
		return totalNewReferrals;

	}

	public void getCountAfterReopeningTheApp(String refCategory, int count) {
	
		Utility.scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + refCategory+ "')]");

		Page.sleep(10000);
		assertEquals("WHEN CLOSING AND REOPENING THE APP ITEMS APPEAR AS NEW", count, getTotalNumOfNewReferrals());
	}

	public int getTotalNumOfNewReferrals() {
		return new Integer(
				split(Page.waitForVisibilityOfElement(totalNumOfNewReferrals, driver).getText(), "N", 0).trim());
	}

}
