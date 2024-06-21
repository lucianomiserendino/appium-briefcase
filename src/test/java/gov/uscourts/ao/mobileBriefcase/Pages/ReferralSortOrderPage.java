package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ReferralSortOrderPage extends AppiumPageFactory {

	// @WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, 'Date')]")
	public static List<WebElement> dates;

	// @WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Sort ↓')]")
	public static WebElement sortArrowBtn;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Date')])[1]")
	public static WebElement dateArrowDownBtn;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Date')])[2]")
	public static WebElement dateArrowUpBtn;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Case')])[1]")
	public static WebElement caseDownArrowBtn;

	// @WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> cases;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Case')])[2]")
	public static WebElement caseUpArrowBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, 'Actions')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static List<WebElement> docCategories;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<WebElement> navIcons;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Applied Referrals']")
	public static List<WebElement> appliedRefs;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> toolCases;

	public void selectReferralCategory(List<UserInputData> userInputData) {

		String query = getID(Queries.REFERRAL_CATEGORIES, DocumentPage.get_pe_id("jud", userInputData));

		List<String> dbReferralCategories = executeQuery(query, userInputData);
		if (dbReferralCategories.contains("Reference Documents")
				|| dbReferralCategories.contains("Cases on Calendar")) {
			dbReferralCategories.remove("Reference Documents");
			dbReferralCategories.remove("Cases on Calendar");
		}

		int randomAttorneyIndex = dbReferralCategories.size() - 1;
		randomAttorneyIndex = generateRandomNumber(randomAttorneyIndex);
		if (randomAttorneyIndex == 0)
			randomAttorneyIndex = randomAttorneyIndex + 1;

		tap(Locator.XPATH, "//*[contains(@name, 'Categories')]/child::*//*[contains(@name, '"
				+ dbReferralCategories.get(randomAttorneyIndex) + "')]");
	}

	public static int generateRandomNumber(int bound) {
		return new Random().nextInt(bound);
	}

	/**
	 * There is a sorting feature on the referral list page that enables users to
	 * sort referrals by date referred or case number. The default is by date
	 * referred in descending order (newest first).
	 */
	public void selectSortBtn() {

		if (Actions.isDisplayed(Locator.XPATH, "//*[contains(@name, 'Sort ↓')]") == true) {
			tap(sortArrowBtn);
		}
	}

	public List<String> referralsSortedByDate() {
		return Utility.retrieveAllReferrals(dates, "Date: ", 1);
	}

	public List<String> referralsSortedByCase() {
		return Utility.retrieveAllReferrals(cases, " ", 0);
	}

	public List<String> assignmentsSortedByCase() {
		return Utility.retrieveAllReferrals(toolCases, " ", 0);
	}

	public void getSortPage(Sort sort) {
		Map<Sort, WebElement> sortElements = new HashMap<>();
		sortElements.put(Sort.REFERRAL_DATE_DESCENDING, dateArrowDownBtn);
		sortElements.put(Sort.REFERRAL_DATE_ASCENDING, dateArrowUpBtn);
		sortElements.put(Sort.CASE_NUMBER_DESCENDING, caseDownArrowBtn);
		sortElements.put(Sort.CASE_NUMBER_ASCENDING, caseUpArrowBtn);

		WebElement elementToTap = sortElements.get(sort);
		if (elementToTap != null) {
			tap(elementToTap);
		}
	}


	public List<Integer> getCellCount(int time, int navCellSize) {
		List<Integer> cellSize = new ArrayList<>();
		for (int i = time; i < navCellSize; i++) {
			cellSize.add(i);
		}
		return cellSize;
	}

	public enum Sort {
		REFERRAL_DATE_DESCENDING, REFERRAL_DATE_ASCENDING, CASE_NUMBER_DESCENDING, CASE_NUMBER_ASCENDING
	}

}
