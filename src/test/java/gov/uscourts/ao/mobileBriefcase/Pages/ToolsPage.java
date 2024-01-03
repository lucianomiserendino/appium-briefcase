package gov.uscourts.ao.mobileBriefcase.Pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ToolsPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tools']")
	public static List<WebElement> tools;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"nav\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]")
	public static WebElement leftNav;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Categories\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]")
	public static WebElement dash;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply All']")
	public static WebElement applyAll;

	public void getToolsCategory() {

		Utility.scroll(dash, "up");
		Utility.scroll(leftNav, "up");

		int toolSize = tools.size();

		if (CommonPages.siVal.equalsIgnoreCase("n")) {
			assertTrue("The left nav displays the Tools category, even when briefcaseDisplayTools is set to 'n'. ",
					toolSize == 0);
		} else {

			assertTrue(
					"The left nav doesn't display the Tools category, even when briefcaseDisplayTools is set to 'y'. ",
					toolSize >= 1);
		}
	
	}

}
