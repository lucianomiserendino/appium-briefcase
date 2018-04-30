package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getListOfCategories;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getNumOfDisplayedCases;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ReferralCategoriesPage {
	public ReferralCategoriesPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='Background'])[1]")
	public static MobileElement pendingTasks;

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='Background'])[4]")
	public static MobileElement MotionsPetitions;

	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement numberOfNonOrallyARGCases;

	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement totalNumOfPendingTasks;

	@iOSFindBy(accessibility = "Screening Panels")
	public static MobileElement screeningPanels;

	@iOSFindBy(accessibility = "Petitions for Rehearing")
	public static MobileElement petitionsForRehearing;

	@iOSFindBy(accessibility = "Cases on Calendar")
	public static MobileElement casesOnCalendar;

	@iOSFindBy(accessibility = "Motions/Petitions")
	public static MobileElement motionsPetitions;

	public String verifyIfPendingTasksAreDisplayed() {

		if (pendingTasks.isDisplayed()) {
			clickOn(pendingTasks);
		}
		performPageLoad();
		return getNumOfDisplayedCases(totalNumOfPendingTasks, "start", "peningTskEnd");

	}

	public List<String> UIreferralCategoriesList() {
		return getListOfCategories(motionsPetitions, casesOnCalendar, petitionsForRehearing, screeningPanels);

	}

	public String getNumOfdisplayedCases() {
		clickOn(MotionsPetitions);
		performPageLoad();
		return getNumOfDisplayedCases(numberOfNonOrallyARGCases, "start", "end");
	}




}