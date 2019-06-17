package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class CaseQueryPage extends AppiumPageFactory {

	@iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
	public static MobileElement searchIcon;

	@iOSFindBy(xpath = "//XCUIElementTypeTextField")
	public static MobileElement searchTextField;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static MobileElement searchBTN;

	@iOSFindBy(accessibility = "ResultsList")
	public static MobileElement category;

	public void searchForACase() {
		String caseN = "";
		for (int i = 0; i < 1; i++) {
			caseN += "15-2594".split(" ")[0];
		}
		searchIcon.click();
		searchByCase(caseN, 6);
		tap(Locator.XPATH, containsElement("Back"));
		searchTextField.clear();
		searchByCase(caseN, 7);
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
}