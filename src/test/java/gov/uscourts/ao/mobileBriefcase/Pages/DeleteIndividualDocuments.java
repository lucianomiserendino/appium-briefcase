package gov.uscourts.ao.mobileBriefcase.Pages;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;

public class DeleteIndividualDocuments extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Viewed'])[1]")
	public static WebElement viewed;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[1]")
	public static WebElement Downloaded;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeAlert[@name='Delete Document?']")
	public static WebElement alert;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='OK']")
	public static WebElement okBtn;

	public static void deleteIndividualDoc() {

		Page.performPageLoad(driver);
		Actions.tap(Locator.XPATH, Actions.containsElement("Sync all documents"));
		swipeByCoordinates();

		if (Utility.isDisplayed("//XCUIElementTypeAlert[@name='Delete Document?']"))
			;
		Actions.tap(okBtn);

	}

	public static void swipeByCoordinates() {

		Page.sleep(3000);
		int x1 = getCoordinates(Coordinate.X, viewed);
		int y1 = getCoordinates(Coordinate.Y, viewed);
		int x2 = getCoordinates(Coordinate.X, Downloaded);
		int y2 = getCoordinates(Coordinate.Y, Downloaded);

		new TouchAction(driver).longPress(PointOption.point(x1, y1)).moveTo(PointOption.point(x2, y2)).release()
				.perform();

	}

	public static int getCoordinates(Coordinate dir, WebElement element) {

		org.openqa.selenium.Point point = element.getLocation();

		int cord = 0;

		switch (dir) {
		case X:
			cord = point.getX();
			break;

		case Y:
			cord = point.getY();
			break;

		default:
			break;
		}
		return cord;
	}

	public enum Coordinate {
		X, Y
	}
}
