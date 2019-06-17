package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.split;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static org.junit.Assert.assertEquals;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class RedBulletsPage extends AppiumPageFactory {
	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Back")
	public MobileElement back;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public MobileElement totalNumOfNewReferrals;

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, 'Viewed')])[1]/preceding::XCUIElementTypeStaticText[contains(@name, '-')]")
	public List<MobileElement> unviewedReferral;

	public int getUnviewedReferral() {
		MobileElement newRef = unviewedReferral.get(unviewedReferral.size() - 1);

		int newReferrals = getTotalNumOfNewReferrals();
		tap(newRef);
		Page.performPageLoad(driver);
		driver.navigate().back();
		assertEquals(newReferrals - 1, getTotalNumOfNewReferrals());
		return getTotalNumOfNewReferrals();
	}

	public int getTotalNumOfNewReferrals() {
		return new Integer(split(totalNumOfNewReferrals.getText(), "N", 0).trim());
	}

}
