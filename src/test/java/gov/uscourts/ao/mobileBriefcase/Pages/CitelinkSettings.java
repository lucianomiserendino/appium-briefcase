package gov.uscourts.ao.mobileBriefcase.Pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CitelinkSettings extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[2]")
	public static MobileElement gearIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Use My CM/ECF Settings']/following:: XCUIElementTypeSwitch[1]")
	public static MobileElement citelinkSettings;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='If you change any Citelink settings, documents cached on this device will not reflect the change unless deleted and downloaded again.']")
	public static List<MobileElement> message;

	@iOSXCUITFindBy(accessibility = "OK")
	public static MobileElement okBTN;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[1]")
	public static MobileElement Downloaded;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CiteLinkEngine']/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<MobileElement> citeLinkEngineList;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CiteLinkHighlightStyle']/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<MobileElement> citeLinkHighlightStyle;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PDF Page View']/XCUIElementTypeLink")
	public static List<MobileElement> links;

	@iOSXCUITFindBy(accessibility = "Done")
	public static MobileElement done;

	@iOSXCUITFindBy(accessibility = "Close")
	public static MobileElement close;

	public static String changesCitelinkSettings() {
		String engine = "";
		Page.performPageLoad(driver);
		gearIcon.click();
		citelinkSettings.click();
		try {
			List<MobileElement> linkSearch = message;
			int checkLink = linkSearch.size();
			assertTrue(checkLink > 0);

		} catch (StaleElementReferenceException e) {

			System.out.println("USER IS NOT PRESENTED WITH THE MESSAGE IF THE CITELINKS PREFERENCES CHANGE IN CM/ECF"
					+ e.getMessage());

		} finally {

			int index = Utility.getRandomNumberInRange(3, citeLinkEngineList.size() - 1);
			engine += citeLinkEngineList.get(index).getText().trim();
			citeLinkEngineList.get(index).click();
			driver.navigate().back();
		}
		return engine;

	}

	public void getCiteLink(String searchEngine) {

		do {
			List<MobileElement> icons = links;
			if (icons.size() == 0) {
				Utility.swipe(1, "right");

			} else {
				break;
			}

		} while (true);

		List<MobileElement> icons = links;

		icons.get(0).click();
		Page.performPageLoad(driver);
		assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(searchEngine)));

		done.click();
		close.click();
	}

}
