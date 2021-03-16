package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.dashboard;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.NoSuchElementException;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AppliedCasesPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[2]")
	public MobileElement bookmarkBTN;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Bookmarked'])[2]")
	public MobileElement bookOnDashboard;

	public void getBookmarkedReferral(String caseNumber) {

		try {
			if (bookOnDashboard.isDisplayed()) {
				tap(bookOnDashboard);

				if (isDisplayed(Locator.XPATH, "//XCUIElementTypeOther[@name='ReferralsList']/child::*"
						+ containsElement(caseNumber)) == true) {

					findElementBy(Locator.XPATH, containsElement(caseNumber)
							+ "/following::XCUIElementTypeOther[3]//XCUIElementTypeStaticText").click();
					tap(dashboard);
				}
			}
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
	}

	public void selectDate(String date, String panel) {

		scrollDownIfNotDisplayed("//XCUIElementTypeStaticText[@name='" + date
				+ "']/following::XCUIElementTypeOther/XCUIElementTypeStaticText[@name='" + panel + "']");

	}

	public void getAppliedCaseLink(String caseNumber) {
		verifyAppliedCaseLinkIsDisplayed(caseNumber);
		tap(Locator.XPATH, containsElement(caseNumber));
		tap(bookmarkBTN);
		tap(dashboard);
	}

	public void verifyAppliedCaseLinkIsDisplayed(String caseNumber) {
		assertTrue("******APPLIED CASES LINK ICON DISAPPEARS WHEN BOOKMARKING CASE/REFERRAL******",
				isDisplayed(Locator.XPATH, containsElement(caseNumber)
						+ "/following::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[@name='linked']"));

	}
}
