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

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Settings']")
	public static WebElement gearIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Use My CM/ECF Settings']/following:: XCUIElementTypeSwitch[1]")
	public static WebElement citelinkSettings;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='If you change any Citelink settings, documents cached on this device will not reflect the change unless deleted and downloaded again.']")
	public static List<WebElement> message;

	@iOSXCUITFindBy(accessibility = "OK")
	public static WebElement okBTN;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[1]")
	public static WebElement Downloaded;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CiteLinkEngine']/XCUIElementTypeButton/XCUIElementTypeStaticText[1]")
	public static List<WebElement> citeLinkEngineList;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CiteLinkHighlightStyle']/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> citeLinkHighlightStyle;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeLink")
	public static List<WebElement> links;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Page Label']/XCUIElementTypeOther/following::XCUIElementTypeStaticText[1]")
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
	
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=''])[1]/preceding::XCUIElementTypeStaticText[1]")
	public static WebElement selectedciteLinkEngine;

	
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
			okBTN.click();
		}

		int index = Utility.getRandomNumberInRange(3, citeLinkEngineList.size() - 1);
		engine += citeLinkEngineList.get(index).getText().trim();
		citeLinkEngineList.get(index).click();
		if ( message.size()>0) {
			if (okBTN.isDisplayed()) {
			okBTN.click();
		}}
		assertTrue("Verify the check mark is on the right of the selected citelink engine",selectedciteLinkEngine.getText().trim().equals(engine));
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

	    if (lastViewedPage != 1) {
	        for (int i = 0; i < lastViewedPage - 1; i++) {
	        	Utility.swipe(1, "right");
	        }
	    }

	    boolean hyperlinkFound = false;
	    for (int i = 1; i < 10; i++) {
	        if (checkForHyperlink()) {
	            hyperlinkFound = true;
	            System.out.println("Hyperlink found on page " + i + "!");
	            clickFirstHyperlink(); 
	            return;
	        }
	        Utility.swipe(1, "left");
	    }

	    throw new AssertionError("No hyperlinks found after swiping left 10 times.");
	}

	private int getCurrentPage() {
	    String[] size = pageSize.getText().split("of");
	    return Integer.parseInt(size[0].trim());
	}

	public static boolean checkForHyperlink() {
	    return links != null && !links.isEmpty();
	}

	private void clickFirstHyperlink() {
	    if (!links.isEmpty()) {
	        links.get(0).click();
	    }
	}


}
