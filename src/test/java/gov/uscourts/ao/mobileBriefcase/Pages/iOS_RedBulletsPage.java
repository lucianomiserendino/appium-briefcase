package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ReferralsList;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectReferral;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrolldown;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_RedBulletsPage extends AppiumPageFactory {
	@WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Back")
	public MobileElement back;

	@WithTimeout(time = 200, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='ReferralsList']/XCUIElementTypeCell/XCUIElementTypeStaticText[contains(@name, 'Viewed')]")
	public List<MobileElement> unviewedReferrals;

	static String unviewedReferral = "(//XCUIElementTypeTable[@name='ReferralsList']/XCUIElementTypeCell/XCUIElementTypeStaticText[contains(@name, 'Viewed')])[1]/preceding-sibling::XCUIElementTypeStaticText[contains(@name, '-')]";

	public int getUnviewedReferral() {
		return unviewedReferrals.size();

	}

	public String getViewedReferral(int unviewedRef) {
		String redBullet = "";
		if (unviewedRef > 0)
			redBullet += findElementAndScrollDown(Locator.XPATH, unviewedReferral, ReferralsList);
		tap(back);
		assertEquals("VERIFY THE RED BULLET IS REMOVED", unviewedRef - 1, unviewedReferrals.size());
		return redBullet;
	}

	public static int getRedBullet(String xpath) {
		List<MobileElement> redBullet = driver.findElements(By.xpath(xpath));
		return redBullet.size();

	}

	public void verifyRedBulletIsRemoved(int unviewedRef, String element) {
		driver.closeApp();
		Base.getInstance(Drivers.IOS);
		selectReferral("Motions/Petitions", ReferralsList);
		Page.performPageLoad(driver);
		 assertEquals("VERIFIES THE RED BULLET IS REMOVED", unviewedRef - 1,
		 unviewedReferrals.size());
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
