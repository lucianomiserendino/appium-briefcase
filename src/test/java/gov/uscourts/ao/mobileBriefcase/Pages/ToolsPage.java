package gov.uscourts.ao.mobileBriefcase.Pages;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ToolsPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tools']")
	public static List<WebElement> tools;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tools']/following::XCUIElementTypeStaticText[@name='']")
	public static List<WebElement> redBullet;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"nav\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]")
	public static WebElement leftNav;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Categories\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]")
	public static WebElement dash;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply All']")
	public static WebElement applyAll;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Apply All']/preceding:: XCUIElementTypeButton[2]")
	public static WebElement exsitingClerkBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel")
	public static WebElement dropDown;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
	public static WebElement done;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Submit']")
	public static WebElement submit;

	public void getToolsCategory() {

		scrollUp();

		int toolSize = tools.size();
		int redBulletCount = redBullet.size();

		if (CommonPages.siVal.equalsIgnoreCase("n")) {
			assertTrue("The left nav displays the Tools category, even when briefcaseDisplayTools is set to 'n'. ",
					toolSize == 0);
		} else {

			assertTrue(
					"The left nav doesn't display the Tools category, even when briefcaseDisplayTools is set to 'y'. ",
					toolSize >= 1);
			assertTrue("the Tools icon displays a red badge with a count in it ", redBulletCount == 0);
		}
	}

	public void applyWithoutExistingClerk() {
		tools.get(0).click();
		if (Actions.isDisplayed(applyAll) == true) {
			applyAll.click();

			boolean progressBar = driver.getPageSource().contains("Please select an existing clerk");
			assertTrue("Tapping 'Apply All' without existing clerk is not generating a message", progressBar);

		}
	}

	public void scrollUp() {
		Utility.scroll(dash, "up");
		Utility.scroll(leftNav, "up");
	}


	public void scrollThroughTheList(String existingClerk) {
		exsitingClerkBtn.click();
		Page.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("order", "next");
		params.put("offset", 0.1);
		params.put("element", ((RemoteWebElement) dropDown).getId());

		if (existingClerk.equals("")) {
			js.executeScript("mobile: selectPickerWheelValue", params);
		} else {
			Boolean elementNotFound = true;
			while (elementNotFound) {

				js.executeScript("mobile: selectPickerWheelValue", params);
				if (dropDown.getText().trim().equals(existingClerk)) {
					elementNotFound = false;

					break;

				} else {
					elementNotFound = true;

				}
			}
		}
		done.click();
	}

	public void selectExistingClerk(String existingClerk) {
		dashboard.click();
		scrollUp();

		int toolSize = tools.size();
		if (CommonPages.siVal.equalsIgnoreCase("n")) {
			assertTrue("The left nav displays the Tools category, even when briefcaseDisplayTools is set to 'n'. ",
					toolSize == 0);
		} else {
			tools.get(0).click();
			scrollThroughTheList(existingClerk);
		}

	}

	public void submitDublicatedAssignment(String caseNum, String lwk) {
		Actions.tap(Locator.XPATH, "//XCUIElementTypeStaticText[@name='input']/preceding::XCUIElementTypeStaticText"
				+ Actions.contains("caseNum"));

		scrollThroughTheList(lwk);
		submit.click();
		Actions.isDisplayed(Locator.XPATH, Actions.containsElement("Duplicated Assignment found for " + lwk));
	}

}
