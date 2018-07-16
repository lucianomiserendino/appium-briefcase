package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnCategory;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.MOTIONS_PETITIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SORT_CASES_IN_ASCENDING_ORDER;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SORT_CASES_IN_DESCENDING_ORDER;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SORT_DATES_IN_ASCENDING_ORDER;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SORT_DATES_IN_DESCENDING_ORDER;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.refresh;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveAllReferrals;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveDates;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_SortingOnTheReferralListPage {

	public iOS_SortingOnTheReferralListPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "//*[@label='Motions/Petitions']")
	public static MobileElement motionsPetitions;

	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='ReferralsList']/child::*//*[contains(@name, 'Date')]")
	public static List<MobileElement> dates;

	@WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Sort')]")
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

	public static String cmka = "Motions/Petitions";
	public static String cm5a = "Motion/Petition";

	public void clickOnMotionsPetitions() {
		refresh();

		clickOnCategory(MOTIONS_PETITIONS);
	}

	public static void clickOnPanel(String element1, String element2) {
		if (elementIsDisplayed(element1) == true) {
			click(locateElement(element1));
		} else {
			click(locateElement(element2));
		}
	}

	public void selectSortBtn() {
		performPageLoad();
		waitToBeClickable(sortArrowBtn);
		performPageLoad();
		referralsSortedByDatesInDescendingOrder();
	}

	public List<String> referralsSortedByDatesInDescendingOrder() {
		return referralsSortedByDatesInDefaultOrder(SORT_DATES_IN_DESCENDING_ORDER, dates, "Date: ", 1);

	}

	public List<String> referralsSortedByDateInAscendingOrder() {
		return referralsSortedByDatesInDefaultOrder(SORT_DATES_IN_ASCENDING_ORDER, dates, "Date: ", 1);

	}

	public List<String> referralsSortedByCasesInDescendingOrder() {
		return retrieveCases(SORT_CASES_IN_DESCENDING_ORDER, cases, " ", 0);

	}

	public List<String> referralsSortedByCasesInAscendingOrder() {
		return retrieveCases(SORT_CASES_IN_ASCENDING_ORDER, cases, " ", 0);

	}

	public List<String> retrieveCases(Actions sort, List<MobileElement> element, String substr, int index) {
		getSortPage(sort);
		performPageLoad();
		return retrieveAllReferrals(element, substr, index);
	}

	public List<String> referralsSortedByDatesInDefaultOrder(Actions sort, List<MobileElement> element, String substr,
			int index) {
		getSortPage(sort);
		Page.pageLoad();
		return retrieveDates(element, substr, index, "yyyy/MM/dd");

	}

	public void getSortPage(Actions sort) {
		switch (sort) {
		case SORT_DATES_IN_DESCENDING_ORDER:
			clickOn(dateArrowDownBtn);

			break;
		case SORT_DATES_IN_ASCENDING_ORDER:
			clickOn(dateArrowUpBtn);
			break;
		case SORT_CASES_IN_DESCENDING_ORDER:
			clickOn(caseDownArrowBtn);
			break;
		case SORT_CASES_IN_ASCENDING_ORDER:
			clickOn(caseUpArrowBtn);

		default:
			break;
		}

	}

}
