package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveAllCases;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveDates;

import java.util.List;
import java.util.concurrent.TimeUnit;

import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_SortingOnTheReferralListPage extends AppiumPageFactory {

	@WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
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

		clickOnElement(category);
	}

	public void selectSortBtn() {
		waitToBeClickable(sortArrowBtn);
	}

	public List<String> referralsSortedByDate(Actions action) {
		getSortPage(action);
		return retrieveDates(dates, "Date: ", 1, "yyyy/MM/dd");
	}

	public List<String> referralsSortedByCase(Actions action) {
		getSortPage(action);
		return retrieveAllCases(cases, " ", 0);
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
