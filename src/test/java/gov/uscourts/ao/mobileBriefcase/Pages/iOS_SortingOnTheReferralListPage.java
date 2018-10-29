package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SORT_CASES_IN_ASCENDING_ORDER;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SORT_CASES_IN_DESCENDING_ORDER;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SORT_DATES_IN_ASCENDING_ORDER;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SORT_DATES_IN_DESCENDING_ORDER;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveAllReferrals;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveDates;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.update;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_SortingOnTheReferralListPage {

	public iOS_SortingOnTheReferralListPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@WithTimeout(time = 1500, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='ReferralsList']/child::*//*[contains(@name, 'Date')]")
	public static List<MobileElement> dates;

	@WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Sort')]")
	public static MobileElement sortArrowBtn;

	@iOSFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Date')])[1]")
	public static MobileElement dateArrowDownBtn;

	@iOSFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Date')])[2]")
	public static MobileElement dateArrowUpBtn;

	@iOSFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Case')])[1]")
	public static MobileElement caseDownArrowBtn;

	@WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='ReferralsList']/child::*//*[contains(@name, '-')]")
	public static List<MobileElement> cases;

	@iOSFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Case')])[2]")
	public static MobileElement caseUpArrowBtn;

	public void clickOnMotionsPetitions(String category) {
		performPageLoad();
		update();
		clickOnElement(category);
	}

	public void selectSortBtn() {
		waitToBeClickable(sortArrowBtn);
		referralsSortedByDatesInDescendingOrder();
	}

	public List<String> referralsSortedByDate(Actions action) {
		return referralsSortedByDatesInDefaultOrder(action, dates, "Date: ", 1);

	}

	public List<String> referralsSortedByCase(Actions action) {
		return retrieveCases(action, cases, " ", 0);

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
		return retrieveAllReferrals(element, substr, index);
	}

	public List<String> referralsSortedByDatesInDefaultOrder(Actions sort, List<MobileElement> element, String substr,
			int index) {
		getSortPage(sort);
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
