package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.retrieveAllCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_SortingOnTheReferralListPage extends AppiumPageFactory {

	//@WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='ReferralsList']/child::*//*[contains(@name, 'Date')]")
	public static List<MobileElement> dates;

	//@WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Sort')]")
	public static MobileElement sortArrowBtn;

	@iOSFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Date')])[1]")
	public static MobileElement dateArrowDownBtn;

	@iOSFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Date')])[2]")
	public static MobileElement dateArrowUpBtn;

	@iOSFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Case')])[1]")
	public static MobileElement caseDownArrowBtn;

	//@WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='ReferralsList']/child::*//*[contains(@name, '-')]")
	public static List<MobileElement> cases;

	@iOSFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Case')])[2]")
	public static MobileElement caseUpArrowBtn;


	/**
	 * There is a sorting feature on the referral list page that enables users to
	 * sort referrals by date referred or case number. The default is by date
	 * referred in descending order (newest first).
	 */
	public void selectSortBtn() {
		waitToBeClickable(sortArrowBtn, driver);
	}

	public List<String> referralsSortedByDate(Sort sort) {
		getSortPage(sort);
		return retrieveAllCases(dates, "Date: ", 1);
	}

	public List<String> referralsSortedByCase(Sort sort) {
		getSortPage(sort);
		return retrieveAllCases(cases, " ", 0);
	}

	public void getSortPage(Sort sort) {
		switch (sort) {
		case SORT_DATES_IN_DESCENDING_ORDER:
			tap(dateArrowDownBtn);
			break;

		case SORT_DATES_IN_ASCENDING_ORDER:
			tap(dateArrowUpBtn);
			break;

		case SORT_CASES_IN_DESCENDING_ORDER:
			tap(caseDownArrowBtn);
			break;

		case SORT_CASES_IN_ASCENDING_ORDER:
			tap(caseUpArrowBtn);
			break;

		default:
			break;
		}

	}

	public enum Sort {
		SORT_DATES_IN_DESCENDING_ORDER, SORT_DATES_IN_ASCENDING_ORDER, SORT_CASES_IN_DESCENDING_ORDER, SORT_CASES_IN_ASCENDING_ORDER
	}

}
