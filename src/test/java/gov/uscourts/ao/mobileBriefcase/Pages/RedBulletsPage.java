package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.split;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static org.junit.Assert.assertEquals;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class RedBulletsPage extends AppiumPageFactory {
	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "Back")
	public MobileElement back;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Total')]")
	public MobileElement totalNumOfNewReferrals;
//(//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, 'Viewed')])[1]/preceding::XCUIElementTypeStaticText[contains(@name, '-')]
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]/preceding::XCUIElementTypeStaticText[contains(@name, 'Viewed')])[1]")
	public List<MobileElement> unviewedReferrals;

	public int getUnviewedReferral() {

		int newReferrals = getTotalNumOfNewReferrals();
		MobileElement newRef = unviewedReferrals.get(unviewedReferrals.size() - 1);
		tap(newRef);
		Page.performPageLoad(driver);
		driver.navigate().back();
		Page.performPageLoad(driver);
		assertEquals(newReferrals - 1, getTotalNumOfNewReferrals());
		return getTotalNumOfNewReferrals();

	}

	public void getCountAfterReopeningTheApp(String refCategory, int count) {
		
		CommonPages.selectReferralCategory(refCategory);
		Page.sleep(10000);
		assertEquals("WHEN CLOSING AND REOPENING THE APP ITEMS APPEAR AS NEW", count, getTotalNumOfNewReferrals());
	}

	public int getTotalNumOfNewReferrals() {
		return new Integer(split(totalNumOfNewReferrals.getText(), "N", 0).trim());
	}

}
