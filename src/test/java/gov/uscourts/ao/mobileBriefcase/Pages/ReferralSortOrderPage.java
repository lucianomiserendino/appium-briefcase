package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.retrieveAllCases;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.Actions;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import gov.uscourts.ao.mobileBriefcase.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ReferralSortOrderPage extends AppiumPageFactory {

	// @WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, 'Date')]")
	public static List<MobileElement> dates;

	// @WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Sort ↓')]")
	public static MobileElement sortArrowBtn;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Date')])[1]")
	public static MobileElement dateArrowDownBtn;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Date')])[2]")
	public static MobileElement dateArrowUpBtn;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Case')])[1]")
	public static MobileElement caseDownArrowBtn;

	// @WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<MobileElement> cases;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[contains(@name, 'Case')])[2]")
	public static MobileElement caseUpArrowBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, 'Actions')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static List<MobileElement> docCategories;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<MobileElement> navIcons;

	public void selectReferralCategory(List<UserInputData> userInputData) {

		String name = SystemPropertySetup.getUser(userInputData);

		String query = getID(Queries.DB_LIST_OF_CATEGORIES, getPE_ID("jud", name, userInputData));

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
		tap(sortArrowBtn);
		if (Actions.isDisplayed(Locator.XPATH, "//*[contains(@name, 'Sort ↓')]") == true) {
			tap(sortArrowBtn);
		}
	}

	public List<String> referralsSortedByDate() {

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

	public void getDocumentCategories(String dbType, String cmr_cyv_code, String cmr_ju_pe_id, String cmr_cs_caseid) {
		getGroupIcons();
		Page.performPageLoad(driver);
		List<String> uiDoCategories = new ArrayList<>();

		for (int i = 0; i < docCategories.size(); i++) {
			uiDoCategories.add(docCategories.get(i).getText());
		}

		List<String> dbDoCategories = executeQuery(valueOf(dbType), Actions.replace(DOCUMENT_CATEGORIES, "CMR_CYV_CODE",
				cmr_cyv_code, "CMR_JU_PE_ID", cmr_ju_pe_id, "CMR_CS_CASEID", cmr_cs_caseid));

		assertEquals(" DOCUMENT CATEGORIES ARE NOT SORTED ON THE REFERRAL DETAIL PAGE ", dbDoCategories,
				uiDoCategories);

	}

	public void sortPendingFolders() {

		int navCellSize = navIcons.size();
		
		List<Integer> dash = getCellCount(1, navCellSize - 1);
		List<Integer> nav = getCellCount(3, navCellSize + 1);
	}
	
	public List<Integer> getCellCount(int time, int navCellSize) {
		List<Integer> cellSize = new ArrayList<>();
		for (int i = time; i < navCellSize; i++) {
			cellSize.add(i);
		}
		return cellSize;
	}

	public enum Sort {
		SORT_DATES_IN_DESCENDING_ORDER, SORT_DATES_IN_ASCENDING_ORDER, SORT_CASES_IN_DESCENDING_ORDER, SORT_CASES_IN_ASCENDING_ORDER
	}

}
