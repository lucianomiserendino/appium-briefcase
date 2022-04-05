package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CaseQueryPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
	public static MobileElement searchIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	public static MobileElement searchTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static MobileElement searchBTN;

	@iOSXCUITFindBy(accessibility = "ResultsList")
	public static MobileElement category;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CM/ECF']")
	public static MobileElement cmecf;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='On Device']")
	public static MobileElement on_device;

	public void searchForACase(String caseNum) {

		searchIcon.click();
		searchByCase(caseNum, 6);
		tap(Locator.XPATH, containsElement("Back"));
		searchTextField.clear();
		searchByCase(caseNum, 7);
	}

	public void searchByCase(String caseN, int index) {
		sendKeys(searchTextField, caseN.substring(0, index) + "*");
		searchBTN.click();
		performPageLoad(driver);
		scrollDownIfNotDisplayed(containsElement(caseN));
		performPageLoad(driver);
		assertTrue("APP IS NOT RETURNING CASE LIST FOR SOME WILDCARD SEARCHES",
				contains("Case #" + caseN).isDisplayed());
	}

	public void viewInfo(String caseNum, MobileElement el, String text) {

		searchIcon.click();
		sendKeys(searchTextField, caseNum);
		searchBTN.click();
		performPageLoad(driver);
		el.click();
		performPageLoad(driver);
		contains(caseNum).click();
		assertTrue(contains(text).isDisplayed());

	}

	public void viewTheInformationInCMECF(String caseNum) {
		viewInfo(caseNum, cmecf, "Docket Entries");
	}

	public void viewTheInformationOnTheDevice(String caseNum) {
		viewInfo(caseNum, on_device, "Case Information");

	}

}