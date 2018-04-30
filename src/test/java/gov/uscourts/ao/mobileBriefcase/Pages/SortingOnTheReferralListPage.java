package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.pageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveAllRefererrals;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SortingOnTheReferralListPage {

	public SortingOnTheReferralListPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='Background'])[4]")
	public static MobileElement motionsPetitions;

	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='ReferralsList']/child::*//*[contains(@name, 'Date')]")
	public static List<MobileElement> dates;

	@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sort ↓']")
	public static MobileElement sortArrowBtn;

	@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=' ↓ Date']")
	public static MobileElement dateArrowDownBtn;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name=' ↑ Date']")
	public static MobileElement dateArrowUpBtn;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name=' ↓ Case #']")
	public static MobileElement caseDownArrowBtn;

	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='ReferralsList']/child::*//*[contains(@name, '-')]")
	public static List<MobileElement> cases;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name=' ↑ Case #']")
	public static MobileElement caseUpArrowBtn;

	public void clickOnMotionsPetitions() {
		waitToBeClickable(motionsPetitions);
		performPageLoad();

	}

	public List<String> referralsInDefaultOrder() {
		return retrieveAllRefererrals(dates, "Date: ", 1);

	}

	public void selectSortBtn() {
		waitToBeClickable(sortArrowBtn);
	}

	public List<String> referralsInDescendingOrder() {
		clickOn(dateArrowDownBtn);
		pageLoad();
		return retrieveAllRefererrals(dates, "Date: ", 1);

	}

	public List<String> verifyReferralsInAscendingOrder() {
		clickOn(dateArrowUpBtn);
		pageLoad();
		List<String> ascending = retrieveAllRefererrals(dates, "Date: ", 1);

		Collections.sort(ascending);
		Collections.reverse(ascending);
		return ascending;

	}

	public List<String> referralsSortedByCaseNumInDescOrder() {
		clickOn(caseDownArrowBtn);
		return retrieveAllRefererrals(cases, " ", 0);

	}

	public List<String> referralsSortedByCaseNumInAscOrder() {
		clickOn(caseUpArrowBtn);
		List<String> ascOrder = retrieveAllRefererrals(cases, " ", 0);
		Collections.sort(ascOrder);
		Collections.reverse(ascOrder);
		return ascOrder;

	}
}
