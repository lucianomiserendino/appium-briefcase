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
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getCellCount;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import gov.uscourts.ao.mobileBriefcase.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.common.Utility;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DashboardPage extends AppiumPageFactory {
	CommonPages page = new CommonPages();
	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Pending Tasks\"])[2]/following::XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static MobileElement pendingTasks;

	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement total;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<MobileElement> navIcons;

	public String verifyIfPendingTasksAreDisplayed() {

		performPageLoad(driver);

		return getText(waitForVisibilityOfElement(pendingTasks, driver)).split("OF")[1].split("T")[0].trim();
	}

	/**
	 * A category entitled "Pending Tasks" will display on the dashboard if the
	 * judge has any pending assignments and the site table variable
	 * briefcaseShowPendingTasks = 'y'.
	 */
	public void getPendingTasks(String query, List<UserInputData> userInputData) {
		List<String> DBPendingTasks = executeQuery(query, userInputData);
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

	public void categories(List<UserInputData> userInputData) {

		String name = SystemPropertySetup.getUser(userInputData);

		String query = getID(Queries.DB_LIST_OF_CATEGORIES, getPE_ID("jud", name, userInputData));

		List<String> dbReferralCategories = executeQuery(query, userInputData);
		sort(dbReferralCategories);

		for (int i = 0; i < dbReferralCategories.size(); i++) {
			performPageLoad(driver);
			MobileElement referrals = waitForVisibilityOfElement(findElementBy(Locator.XPATH,

					"(//XCUIElementTypeStaticText[@name='" + dbReferralCategories.get(i) + "'])[2]"),

					driver);
			assertTrue(referrals.isDisplayed());
		}
	}

	/**
	 * Tapping on a referral category that is not orally argued
	 * (chm_reftype_val.cdv_is_oral_arg = 'n'), a list of cases should display for
	 * the judge for that category. This method verifies the correct number of
	 * referrals are being displayed.
	 */
	public void verifyNonOrallyArgCases(String cyvCategory, String pe_id, List<UserInputData> userInputData) {
		getReffCategories(cyvCategory, pe_id, userInputData);

	}

	public static List<String> getReffCategories(String cyvCategory, String pe_id, List<UserInputData> userInputData) {

		List<String> referralCategories = executeQuery(
				getID(replace(NON_ORALLY_ARGUED_CASES, "CMR_CYV_CODE", cyvCategory), pe_id), userInputData);
		sort(referralCategories);
		try {

			for (int i = 0; i < referralCategories.size(); ++i) {
				performPageLoad(driver);
				MobileElement referrals = waitForVisibilityOfElement(
						findElementBy(Locator.XPATH, "(//*[contains(@name, 'Categories')]/child::*//*[contains(@name, '"
								+ referralCategories.get(i) + "')])[2]"),
						driver);
				performPageLoad(driver);
				String dbNonOrgCases = getText(referrals);
				referrals.click();
				
				List<String> briefcaseTargReferral_y = executeQuery(
						replace(getID(BRIEFCASE_TARGET_ONLY_Y, pe_id), "CYV_CATEGORY", dbNonOrgCases), userInputData);

				List<String> briefcaseTargReferral_n = executeQuery(
						replace(getID(BRIEFCASE_TARGET_ONLY_N, pe_id), "CYV_CATEGORY", dbNonOrgCases), userInputData);

				List<String> UInonOrallyarguedCases = asList(
						(getNumOfDisplayedCases(Page.waitForVisibilityOfElement(total, driver))));

				String si_value = CommonPages.getSiValue("briefcaseTargetOnly", userInputData);

				if (si_value.equals("y")) {
					assertTrue("-----RECORD COUNT MISMATCH-----",
							briefcaseTargReferral_y.equals(UInonOrallyarguedCases));

				} else
					assertTrue("-----RECORD COUNT MISMATCH-----",
							briefcaseTargReferral_n.equals(UInonOrallyarguedCases));

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
	public void get_lbrrpt_CATEGORY(String cyvCategory, String PE_RT_CODE, String judgeName,
			List<UserInputData> userInputData) {
		performPageLoad(driver);
		MobileElement lbrrptCategory;
		String peID = getPE_ID(PE_RT_CODE, judgeName, userInputData);

		List<String> cmr_cyv_code = executeQuery(getID(lbrrpt_CATEGORY, peID), userInputData);
		if (cmr_cyv_code.contains(cyvCategory)) {
			String cyv_category = getAllColumns(getID(replace(lbrrpt_CYV_CATEGORY, "CMR_CYV_CODE", cyvCategory), peID),
					userInputData);
			lbrrptCategory = waitForVisibilityOfElement(findElementBy(Locator.XPATH,
					"//*[contains(@name, 'Categories')]/child::*//*[contains(@name, '" + cyv_category.trim() + "')]"),
					driver);

			assertTrue(lbrrptCategory.isDisplayed());
			lbrrptCategory.click();

			assertTrue("*****PLEASE VERIFY ONLY DOCUMENTS DISPLAY ON THE REFERRAL DETAIL PAGE*****",
					getDocuments(peID, userInputData));
		}

	}

	public boolean getDocuments(String peID, List<UserInputData> userInputData) {

		CommonPages.getGroupIcons();
		MobileElement uiDocs = null;

		boolean isDisplayed = false;

		List<String> dbDocCategory = executeQuery(getID(lbrrpt_DOCUMENT_CATEGORY, peID), userInputData);
		sort(dbDocCategory);

		// try {
		for (int i = 0; i < dbDocCategory.size(); ++i) {

			uiDocs = findElementBy(Locator.XPATH, containsElement(dbDocCategory.get(i)));

			if (uiDocs.isDisplayed())
				isDisplayed = true;
			uiDocs.click();

			List<String> docDesc = executeQuery(
					getID(replace(REFERRAL_DOCUMENTS, "CMD_DOC_CATEGORY", dbDocCategory.get(i)), peID), userInputData);

			for (int j = 0; j < docDesc.size(); j++) {

				MobileElement uiResult = findElementBy(Locator.XPATH, containsElement(docDesc.get(j)));
				if (uiResult.isDisplayed())

					isDisplayed = true;
			}
		}
		// } catch (Exception e) {
		// isDisplayed = false;
		// }
		return isDisplayed;

	}

	/**
	 * Verify the number of new items that displays in the red badge in the
	 * navigation match the number of new items listed on the Dashboard page.
	 */

	public void getNewReferralsCount() {

		int navCellSize = navIcons.size();

		List<Integer> dash = getCellCount(1, navCellSize - 1);
		List<Integer> nav = getCellCount(3, navCellSize + 1);

		for (int i = 0; i < navCellSize - 2; i++) {
			try {
				MobileElement navNewReferralCount = navNewRefCount(nav.get(i));
				String dashNewReferralCount = dashNewRefCount(dash.get(i)).getText().split("W")[0].split(" ")[0].trim();

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

	public MobileElement dashNewRefCount(int index) {
		return findElementBy(Locator.XPATH,
				"(//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText)["
						+ index + "]");
	}

	public MobileElement navNewRefCount(int index) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["
						+ index
						+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeStaticText");
	}



}