package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clicksOn;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.StaleElementReferenceException;

import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CitelinkSettings extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[4]")
	public static MobileElement gearIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CiteLinkSettings']/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeSwitch")
	public static MobileElement citelinkSettings;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='If you change any Citelink settings, documents cached on this device will not reflect the change unless deleted and downloaded again.']")
	public static MobileElement message;

	@iOSXCUITFindBy(accessibility = "OK")
	public static MobileElement okBTN;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[1]")
	public static MobileElement Downloaded;

	public void changesCitelinkSettings() {

		clicksOn(gearIcon);
		clicksOn(citelinkSettings);

		try {
			assertTrue(message.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("USER IS NOT PRESENTED WITH THE MESSAGE IF THE CITELINKS PREFERENCES CHANGE IN CM/ECF"
					+ e.getMessage());

		} finally {
			clicksOn(okBTN);
			driver.navigate().back();

			assertTrue(Downloaded.isDisplayed());
		}

	}

}
