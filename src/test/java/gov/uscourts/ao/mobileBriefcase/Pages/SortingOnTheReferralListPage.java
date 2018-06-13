package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveAllReferrals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SortingOnTheReferralListPage {

	public SortingOnTheReferralListPage() {
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

	public void clickOnMotionsPetitions() {
		motionsPetitions.click();
		performPageLoad();

	}

	public void selectSortBtn() {
		performPageLoad();
		waitToBeClickable(sortArrowBtn);
	}

	public List<String> referralsSortedByDatesInDescendingOrder() {
		return referralsSortedByDatesInDefaultOrder(Actions.SORT_DATES_IN_DESCENDING_ORDER, dates, "Date: ", 1);

	}

	public List<String> referralsSortedByDateInAscendingOrder() {
		return referralsSortedByDatesInDefaultOrder(Actions.SORT_DATES_IN_ASCENDING_ORDER, dates, "Date: ", 1);

	}

	public List<String> referralsSortedByCasesInDescendingOrder() {
		return retrieveCases(Actions.SORT_CASES_IN_DESCENDING_ORDER, cases, " ", 0);

	}

	public List<String> referralsSortedByCasesInAscendingOrder() {
		return retrieveCases(Actions.SORT_CASES_IN_ASCENDING_ORDER, cases, " ", 0);

	}

	public List<String> retrieveCases(Actions sort, List<MobileElement> element, String substr, int index) {
		getSortPage(sort);
		performPageLoad();
		return retrieveAllReferrals(element, substr, index);
	}

	public List<String> referralsSortedByDatesInDefaultOrder(Actions sort, List<MobileElement> element, String substr,
			int index) {
		getSortPage(sort);
		performPageLoad();
		return retrieveDates(element, substr, index);

	}

	public static List<String> retrieveDates(List<MobileElement> elements, String split, int index) {
		String[] dates;
		List<String> referrals = new ArrayList<>();

		List<MobileElement> element = elements;

		Iterator<MobileElement> itr = element.iterator();
		while (itr.hasNext()) {
			try {
				dates = itr.next().getText().split(split);
				referrals.add(changeDateFormat(dates[index].trim(), "yyyy/MM/dd"));

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return referrals;

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
