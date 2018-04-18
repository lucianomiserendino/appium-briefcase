package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.moibleBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.moibleBriefcase.common.Utilities.getListOfDisplayedCases;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.moibleBriefcase.common.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Non_OrallyArguedCasePage extends Base {

	private static int strat = 7;
	private static int end = 11;

	public Non_OrallyArguedCasePage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='Background'])[4]")
	public static MobileElement motionsPetitions;

	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement numberOfNonOrallyARGCases;

	
	public void clickOnMotionsPettitions() {
		clickOn(motionsPetitions);

	}

	public String getNumOfdisplayedCases() {
		return getListOfDisplayedCases(numberOfNonOrallyARGCases, strat, end);
	}

}
