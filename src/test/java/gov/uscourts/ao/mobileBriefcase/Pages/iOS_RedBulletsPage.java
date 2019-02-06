package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ReferralsList;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectReferral;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.split;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrolldown;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_RedBulletsPage extends AppiumPageFactory {
	@WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Back")
	public MobileElement back;

	@WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public MobileElement unViewed;

	@WithTimeout(time = 200, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='ReferralsList']/XCUIElementTypeCell/XCUIElementTypeStaticText[contains(@name, 'Viewed')]")
	public List<MobileElement> unviewedReferrals;

	static String unviewedReferral = "(//XCUIElementTypeTable[@name='ReferralsList']/XCUIElementTypeCell/XCUIElementTypeStaticText[contains(@name, 'Viewed')])[1]/preceding-sibling::XCUIElementTypeStaticText[contains(@name, '-')]";

	public int getUnviewedReferral() {
		return getSizeOfNewReferrals();
	}

	public int getSizeOfNewReferrals() {
		String newReferrals = split(unViewed.getText(), "N", 0).trim();
		assertEquals((int) new Integer(newReferrals), unviewedReferrals.size());
		return unviewedReferrals.size();
	}

	public int getViewedReferral() {
		findElementAndScrollDown(Locator.XPATH, unviewedReferral, ReferralsList);
		tap(back);
		return getSizeOfNewReferrals();
	}

	public int verifyRedBulletIsRemoved() {
		selectReferral("Motions/Petitions", ReferralsList);
		Page.performPageLoad(driver);
		return getSizeOfNewReferrals();
	}

	public static String findElementAndScrollDown(Locator locator, String element, MobileElement el) {
		String text = "";
		Boolean elementNotFound = true;
		while (elementNotFound) {
			try {
				MobileElement elem = findElementBy(locator, element);
				if (elem.isDisplayed()) {
					text += elem.getText().split(" ")[0];
					elem.click();
					break;
				} else {
					scrolldown(el);
				}
			} catch (NoSuchElementException e) {
				scrolldown(el);
			}
		}
		return text;

	}

}
