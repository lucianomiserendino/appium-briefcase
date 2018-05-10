package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveAllReferrals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Sort;
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

	public void selectSortBtn() {
		waitToBeClickable(sortArrowBtn);
	}

	public List<String> referralsSortedByDatesInDescendingOrder() {
		return referralsSortedByDatesInDefaultOrder(Sort.DATES_IN_DESCENDING_ORDER, dates, "Date: ", 1);

	}

	public List<String> referralsSortedByDateInAscendingOrder() {
		return referralsSortedByDatesInDefaultOrder(Sort.DATES_IN_ASCENDING_ORDER, dates, "Date: ", 1);

	}

	public List<String> referralsSortedByCasesInDescendingOrder() {
		return retrieveCases(Sort.CASES_IN_DESCENDING_ORDER, cases, " ", 0);

	}

	public List<String> referralsSortedByCasesInAscendingOrder() {
		return retrieveCases(Sort.CASES_IN_ASCENDING_ORDER, cases, " ", 0);

	}

	public List<String> retrieveCases(Sort sort, List<MobileElement> element, String substr, int index) {
		getSortPage(sort);
		return retrieveAllReferrals(element, substr, index);
	}

	public List<String> referralsSortedByDatesInDefaultOrder(Sort sort, List<MobileElement> element, String substr,
			int index) {
		getSortPage(sort);

		return retrieveDates(element, substr, index);

	}

	public static List<String> retrieveDates(List<MobileElement> elements, String split, int index) {
		String[] dest;
		List<String> referrals = new ArrayList<>();

		List<MobileElement> el = elements;

		Iterator<MobileElement> itr = el.iterator();
		while (itr.hasNext()) {
			try {
				dest = itr.next().getText().split(split);
				referrals.add(changeDateFormat(dest[index].trim()));

			} catch (ParseException e) {

				e.printStackTrace();
			}

		}
		return referrals;

	}

	public void getSortPage(Sort sort) {
		switch (sort) {
		case DATES_IN_DESCENDING_ORDER:
			clickOn(dateArrowDownBtn);

			break;
		case DATES_IN_ASCENDING_ORDER:
			clickOn(dateArrowUpBtn);
			break;
		case CASES_IN_DESCENDING_ORDER:
			clickOn(caseDownArrowBtn);
			break;
		case CASES_IN_ASCENDING_ORDER:
			clickOn(caseUpArrowBtn);

		default:
			break;
		}

	}

}
