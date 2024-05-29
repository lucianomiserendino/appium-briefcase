package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class RedBulletsPage extends AppiumPageFactory {

	@iOSXCUITFindBy(accessibility = "Back")
	public WebElement back;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Total')]")
	public WebElement totalNumOfNewReferrals;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]/preceding::XCUIElementTypeStaticText[contains(@name, 'Viewed')])[1]")
	public List<WebElement> unviewedReferrals;

	public static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	public int getUnviewedReferral() {
		int initialNewReferralCount = getTotalNumOfNewReferrals();
		WebElement newReferral = unviewedReferrals.isEmpty() ? null
				: unviewedReferrals.get(unviewedReferrals.size() - 1);

		if (newReferral != null) {
			tap(newReferral);
			Page.performPageLoad(driver);
			driver.navigate().back();
			Page.performPageLoad(driver);
		}

		int finalNewReferralCount = getTotalNumOfNewReferrals();
		assertEquals(initialNewReferralCount - 1, finalNewReferralCount);
		return finalNewReferralCount;
	}

	public void getCountAfterReopeningTheApp(String refCategory, int count) {
		Utility.scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + refCategory + "')]");
		Page.sleep(10000);
		assertEquals("WHEN CLOSING AND REOPENING THE APP ITEMS APPEAR AS NEW", count, getTotalNumOfNewReferrals());
	}

	public int getTotalNumOfNewReferrals() {
		WebElement element = Page.waitForVisibilityOfElement(totalNumOfNewReferrals, driver);
		String text = element.getText().trim();
		return Integer.parseInt(text.split("N")[0].trim());
	}

}
