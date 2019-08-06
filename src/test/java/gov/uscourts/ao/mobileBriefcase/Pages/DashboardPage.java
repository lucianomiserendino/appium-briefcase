package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.BRIEFCASE_TARGET_ONLY_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.BRIEFCASE_TARGET_ONLY_Y;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.NON_ORALLY_ARGUED_CASES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.REFERRAL_DOCUMENTS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.lbrrpt_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.lbrrpt_CYV_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.lbrrpt_DOCUMENT_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.dashboard;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForVisibilityOfElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getNumOfDisplayedCases;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class DashboardPage extends AppiumPageFactory {
	CommonPages page = new CommonPages();
	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Pending Tasks")
	public static MobileElement pendingTasks;

	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement total;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<MobileElement> navIcons;

	public String verifyIfPendingTasksAreDisplayed() {
		if (pendingTasks.isDisplayed()) {
			tap(pendingTasks);
		}
		performPageLoad(driver);
		return getNumOfDisplayedCases(total);
	}

	/**
	 * A category entitled "Pending Tasks" will display on the dashboard if the
	 * judge has any pending assignments and the site table variable
	 * briefcaseShowPendingTasks = 'y'.
	 */
	public void getPendingTasks(DBType dbtype, String query) {
		List<String> DBPendingTasks = executeQuery(dbtype, query);
		if (DBPendingTasks.size() > 0) {
			List<String> UIPendingTasks = asList(verifyIfPendingTasksAreDisplayed());
			assertEquals("-----RECORD COUNT MISMATCH-----", DBPendingTasks, UIPendingTasks);

		}
	}

	/**
	 * Referral categories are court definable in the chm_reftype_val table.
	 * Categories (cyv_category) that have the cyv_is_briefcase field = 'y' will
	 * display in briefcase if there are referrals created in that category.
	 * Referrals are stored in the chm_mobile_referral table. There is a FK to the
	 * chm_reftype_val table (cmr_cyv_code). This is how the category is obtained.
	 */
	public void getReferralCategories(DBType dbtype, String query) {
		categories(dbtype, query);
	}

	public static List<String> categories(DBType dbtype, String query) {
		List<String> categories = new ArrayList<>();
		List<String> dbReferralCategories = executeQuery(dbtype, query);
		sort(dbReferralCategories);
		try {
			for (int i = 0; i < dbReferralCategories.size(); ++i) {
				performPageLoad(driver);
				MobileElement referrals = waitForVisibilityOfElement(
						findElementBy(Locator.XPATH, "//*[contains(@name, 'Categories')]/child::*//*[contains(@name, '"
								+ dbReferralCategories.get(i) + "')]"),
						driver);
				assertTrue(
						"*****" + dbReferralCategories.get(i).toUpperCase() + " IS NOT DISPLAYED ON THE DASHBOARD*****",
						referrals.isDisplayed());
			}

		} catch (org.openqa.selenium.TimeoutException e) {

			e.printStackTrace();
		}
		categories.addAll(dbReferralCategories);
		sort(categories);
		return dbReferralCategories;
	}

	/**
	 * Tapping on a referral category that is not orally argued
	 * (chm_reftype_val.cdv_is_oral_arg = 'n'), a list of cases should display for
	 * the judge for that category. This method verifies the correct number of
	 * referrals are being displayed.
	 */
	public void verifyNonOrallyArgCases(DBType dbtype, String cyvCategory, String pe_id) {
		getReffCategories(dbtype, cyvCategory, pe_id);

	}

	public static List<String> getReffCategories(DBType dbtype, String cyvCategory, String pe_id) {

		List<String> referralCategories = executeQuery(dbtype,
				getID(replace(NON_ORALLY_ARGUED_CASES, "CMR_CYV_CODE", cyvCategory), pe_id));

		sort(referralCategories);
		try {

			for (int i = 0; i < referralCategories.size(); ++i) {
				performPageLoad(driver);
				MobileElement referrals = waitForVisibilityOfElement(
						findElementBy(Locator.XPATH, "//*[contains(@name, 'Categories')]/child::*//*[contains(@name, '"
								+ referralCategories.get(i) + "')]"),
						driver);
				performPageLoad(driver);
				String dbNonOrgCases = getText(referrals);
				referrals.click();
				List<String> briefcaseTargReferral_y = executeQuery(dbtype,
						replace(getID(BRIEFCASE_TARGET_ONLY_Y, pe_id), "CYV_CATEGORY", dbNonOrgCases));

				List<String> briefcaseTargReferral_n = executeQuery(dbtype,
						replace(getID(BRIEFCASE_TARGET_ONLY_N, pe_id), "CYV_CATEGORY", dbNonOrgCases));

				List<String> UInonOrallyarguedCases = asList(
						(getNumOfDisplayedCases(Page.waitForVisibilityOfElement(total, driver))));

				assertTrue("-----RECORD COUNT MISMATCH-----", briefcaseTargReferral_n.equals(UInonOrallyarguedCases)
						|| briefcaseTargReferral_y.equals(UInonOrallyarguedCases));

				tap(dashboard);
			}
		} catch (org.openqa.selenium.TimeoutException e) {

			e.printStackTrace();
		}

		return referralCategories;
	}

	/**
	 * If the chm_mobile_referral.cmr_cyv_code = 'lbrrpt', verify  cyv_category 
	 * displays on the Dashboard page. 
	 */
	public void get_lbrrpt_CATEGORY(DBType dbType, String cyvCategory, String PE_RT_CODE, String judgeName) {
		performPageLoad(driver);
		MobileElement lbrrptCategory;
		String peID = getPE_ID(dbType, PE_RT_CODE, judgeName);

		List<String> cmr_cyv_code = executeQuery(dbType, getID(lbrrpt_CATEGORY, peID));
		if (cmr_cyv_code.contains(cyvCategory)) {
			String cyv_category = getAllColumns(dbType,
					getID(replace(lbrrpt_CYV_CATEGORY, "CMR_CYV_CODE", cyvCategory), peID));
			lbrrptCategory = waitForVisibilityOfElement(findElementBy(Locator.ID, cyv_category), driver);
			assertTrue(lbrrptCategory.isDisplayed());
			lbrrptCategory.click();
			assertTrue("*****PLEASE VERIFY ONLY DOCUMENTS DISPLAY ON THE REFERRAL DETAIL PAGE*****",
					getDocuments(peID, dbType));
		}

	}

	public boolean getDocuments(String peID, DBType dbType) {

		//page.getGroupIcons();
		MobileElement uiDocs = null;

		boolean isDisplayed = false;

		List<String> dbDocCategory = executeQuery(dbType, getID(lbrrpt_DOCUMENT_CATEGORY, peID));
		sort(dbDocCategory);

		try {
			for (int i = 0; i < dbDocCategory.size(); ++i) {

				uiDocs = findElementBy(Locator.XPATH, containsElement(dbDocCategory.get(i)));

				if (uiDocs.isDisplayed())
					isDisplayed = true;
				uiDocs.click();

				List<String> docDesc = executeQuery(dbType,
						getID(replace(REFERRAL_DOCUMENTS, "CMD_DOC_CATEGORY", dbDocCategory.get(i)), peID));

				for (int j = 0; j < docDesc.size(); j++) {

					MobileElement uiResult = findElementBy(Locator.XPATH, containsElement(docDesc.get(j)));
					if (uiResult.isDisplayed())
						isDisplayed = true;
				}
			}
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	/**
	 * Verify the number of new items that displays in the red badge in the
	 * navigation match the number of new items listed on the Dashboard page.
	 */

	public void getNewReferralsCount() {

		int navCellSize = navIcons.size();

		List<Integer> nav = getCellCount(1, navCellSize - 1);
		List<Integer> dash = getCellCount(3, navCellSize + 1);

		for (int i = 0; i < navCellSize - 2; i++) {
			try {
				String dashNewReferralCount = dashNewRefCount(nav.get(i)).getText().split("W")[0].split(" ")[0].trim();
				MobileElement navNewReferralCount = navNewRefCount(dash.get(i));

				if (dashNewReferralCount.equals("0")) {
					assertTrue(!(navNewReferralCount.isDisplayed()));
				} else {
					assertEquals(dashNewReferralCount, navNewReferralCount.getText().trim());
				}
			} catch (org.openqa.selenium.TimeoutException e) {
				e.getMessage();
			}
		}
	}

	public List<Integer> getCellCount(int time, int navCellSize) {
		List<Integer> cellSize = new ArrayList<>();
		for (int i = time; i < navCellSize; i++) {
			cellSize.add(i);
		}
		return cellSize;
	}

	public MobileElement navNewRefCount(int index) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["
						+ index
						+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeStaticText");
	}

	public MobileElement dashNewRefCount(int index) {
		return findElementBy(Locator.XPATH,
				"(//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText)["
						+ index + "]");
	


	}
	
	
	public static void main(String[] args) {
		List<String> dbDocCategory = executeQuery(DBType.CMKA, getID(lbrrpt_DOCUMENT_CATEGORY, "32"));
		sort(dbDocCategory);;
		System.out.println(dbDocCategory.size());
		
		for (int i = 0; i < dbDocCategory.size(); ++i) {

		
			List<String> docDesc = executeQuery(DBType.CMKA,
					getID(replace(REFERRAL_DOCUMENTS, "CMD_DOC_CATEGORY", dbDocCategory.get(i)), "32"));
			System.out.println(docDesc);
		}
	}
}