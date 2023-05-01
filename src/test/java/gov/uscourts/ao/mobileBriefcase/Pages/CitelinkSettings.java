package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clicksOn;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CitelinkSettings extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[2]")
	public static WebElement gearIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Use My CM/ECF Settings']/following:: XCUIElementTypeSwitch[1]")
	public static WebElement citelinkSettings;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='If you change any Citelink settings, documents cached on this device will not reflect the change unless deleted and downloaded again.']")
	public static List<WebElement> message;

	@iOSXCUITFindBy(accessibility = "OK")
	public static WebElement okBTN;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[1]")
	public static WebElement Downloaded;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CiteLinkEngine']/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> citeLinkEngineList;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CiteLinkHighlightStyle']/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> citeLinkHighlightStyle;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PDF Page View']/XCUIElementTypeLink")
	public static List<WebElement> links;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Page Label']/XCUIElementTypeStaticText")
	public static WebElement pageSize;

	@iOSXCUITFindBy(accessibility = "Done")
	public static WebElement done;

	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='Search Results']/XCUIElementTypeCell")
	public static List<WebElement> searchResult;

	@iOSXCUITFindBy(accessibility = "Search")
	public static WebElement searchIcon;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeSearchField[@name=\"Search Document\"])[1]")
	public static WebElement searchTextField;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'FindAppendix?')]")
	public static List<WebElement> appxLink;

	public static int getCurrentPageNumber;

	public static String changesCitelinkSettings() {
		String engine = "";
		Page.performPageLoad(driver);
		gearIcon.click();

		if (Utility.getToggleState(citelinkSettings) == true) {
			citelinkSettings.click();

			List<WebElement> linkSearch = message;
			int checkLink = linkSearch.size();
			assertTrue("USER IS NOT PRESENTED WITH THE MESSAGE IF THE CITELINKS PREFERENCES CHANGE IN CM/ECF",
					checkLink > 0);

		}

		int index = Utility.getRandomNumberInRange(3, citeLinkEngineList.size() - 1);
		engine += citeLinkEngineList.get(index).getText().trim();
		citeLinkEngineList.get(index).click();
		driver.navigate().back();

		return engine;

	}

	public void searchForAppendix() {
		String text = "Appx";
		performPageLoad(driver);
		clicksOn(searchIcon);
		searchTextField.clear();
		Actions.sendKeys(searchTextField, text);
		String index = Utility.clickOnNumberInRange(searchResult);

		if (!(index == null)) {
			Utility.clickOnNumberInRange(appxLink);
			performPageLoad(driver);
			Assert.assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(text)));
		} else {
			throw new RuntimeException("THIS DOCUMENT DOES NOT CONTAIN ANY HYPERLINKS");
		}
	}

	public void getCiteLink(String searchEngine) {

		String[] size = pageSize.getText().split("of");
		int totalPageSize = Integer.parseInt(size[1].trim());
		int lastViewedPage = Integer.parseInt(size[0].trim());
		int randomNum;

		do {
			List<WebElement> icons = links;
			if (icons.size() == 0) {

				// if (totalPageSize>1) {

				if (totalPageSize == lastViewedPage) {
					Utility.swipe(1, "left");

				} else if (totalPageSize == lastViewedPage) {
					Utility.swipe(1, "right");
				}
				// }

				break;
			}

		} while (true);

		List<WebElement> icons = links;

		icons.get(0).click();
		Page.performPageLoad(driver);
		assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(searchEngine)));

		done.click();
		close.click();
	}

	public void getCiteLink() {

		String[] size = pageSize.getText().split("of");
		int totalPageSize = Integer.parseInt(size[1].trim());
		int lastViewedPage = Integer.parseInt(size[0].trim());

		// Check for hyperlink on page 11
		if (checkForHyperlink()) {
			System.out.println("Hyperlink found on page ----->>>>>>>>>>" + lastViewedPage + "!");

		} else {
			boolean hyperlinkFound = false;
			// Swipe left to find hyperlink
			while (!hyperlinkFound) {
				//Utility.swipe(1, "left");
				
				Utility.tapAndSwipe(Direction.LEFT) ;
				hyperlinkFound = checkForHyperlink();
				if (driver.getPageSource().contains(totalPageSize + " of " + totalPageSize)) {
					break; // reached end of document
				}
			}
			// If no hyperlink found, swipe right and search from page 11
			if (!hyperlinkFound) {
				for (int i = 1; i < lastViewedPage; i++) {
					//Utility.swipe(1, "right");
					Utility.tapAndSwipe(Direction.RIGHT) ;

				}
				while (!hyperlinkFound) {
					//Utility.swipe(1, "right");
					Utility.tapAndSwipe(Direction.RIGHT) ;

					hyperlinkFound = checkForHyperlink();
					if (driver.getPageSource().contains(1 + " of " + totalPageSize)) {
						break; // searched all pages, no hyperlink found
					}
				}
			}
			if (hyperlinkFound) {
				System.out.println("Hyperlink found on page ------------------");
			} else {
				System.out.println("No hyperlink found. Closing the PDF...");
			}
		}

	}

	public static boolean checkForHyperlink() {
		List<WebElement> link = links;
		return !link.isEmpty();
	}

}
