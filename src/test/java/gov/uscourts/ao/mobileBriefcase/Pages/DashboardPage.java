package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.BRIEFCASE_TARGET_ONLY_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.BRIEFCASE_TARGET_ONLY_Y;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.NON_ORALLY_ARGUED_CASES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.REFERRAL_DOCUMENTS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.lbrrpt_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.lbrrpt_CYV_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.lbrrpt_DOCUMENT_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.waitForVisibilityOfElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getCellCount;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getNumOfDisplayedCases;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.SiteTableVariable;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DashboardPage extends Base {

	public DashboardPage() {
		initElements(new AppiumFieldDecorator(driver), this);
	}

	CommonPages page = new CommonPages();
	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Pending Tasks\"])[2]/following::XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static WebElement pendingTasks;

	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static WebElement total;

	@iOSXCUITFindBy(id = "Categories")
	public static WebElement categories;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<WebElement> navIcons;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Categories\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]")
	public static List<WebElement> dashCategories;

	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"nav\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther")
	public static List<WebElement> navCategories;

	public void verifyPendingTasksPosition(String page) {
	    performPageLoad(driver);
	    List<String> categoryList = new ArrayList<>();

	    if (page.equals("Dashboard")) {
	        // Get the categories from the dashboard
	        for (WebElement dashCategory : dashCategories) {
	            categoryList.add(dashCategory.getAttribute("value").trim());
	        }

	    } else if (page.equals("Navigation")) {
	        // Get the categories from the navigation page
	        int size = navCategories.size();

	        for (int i = 3; i < size; i++) {
	            WebElement navRefCategories = driver.findElement(By.xpath(
	                    "(//XCUIElementTypeOther[@name=\"nav\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther["
	                            + i
	                            + "]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText)[last()]"));

	            categoryList.add(navRefCategories.getText().trim());
	        }

	    }

	    // Verify the position of "Pending Tasks" in the category list
	    int pendingTasksIndex = categoryList.indexOf("Pending Tasks");
	    if (pendingTasksIndex != -1) {
	        assertTrue("Pending Tasks should be the first category", pendingTasksIndex == 0);
	    } else {
	        fail("Pending Tasks are not available at the top of the list");
	    }
	}


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

	public void getRefCategories(List<UserInputData> userInputData) {
		String peId = DocumentPage.get_pe_id("jud", userInputData);
		List<String> dbReferralCategories = executeQuery(getID(Queries.REFERRAL_CATEGORIES, peId), userInputData);

		dbReferralCategories.sort(String::compareTo);

		if (!dbReferralCategories.isEmpty()) {
			Random rand = new Random();
			String randomCategory = dbReferralCategories.get(rand.nextInt(dbReferralCategories.size()));

			performPageLoad(driver);
			scrollToAction(randomCategory);
			Utility.scroll(categories, "down");
		}
	}

	public static void scrollToAction(String element) {
		String elemXpath = "//XCUIElementTypeStaticText[@name='" + element + "']";
		String elemAlternativeXpath = "(" + elemXpath + ")[2]";
		assertTrue(isElementDisplayed(elemXpath, elemAlternativeXpath));
	}

	public static boolean isElementDisplayed(String primaryXpath, String alternativeXpath) {
		while (true) {
			List<WebElement> elems = findElements(By.xpath(primaryXpath));

			if (elems.size() > 1) {
				try {
					WebElement elem = waitForVisibilityOfElement(findElementBy(Locator.XPATH, alternativeXpath),
							driver);
					if (elem.isDisplayed()) {
						return true;
					}
				} catch (WebDriverException e) {
					System.err.println("Error finding element: " + e.getMessage());
				}
				break;
			} else {
				Utility.scroll(categories, "up");
				performPageLoad(driver);
			}
		}
		return false;
	}

	/**
	 * Tapping on a referral category that is not orally argued
	 * (chm_reftype_val.cdv_is_oral_arg = 'n'), a list of cases should display for
	 * the judge for that category. This method verifies the correct number of
	 * referrals are being displayed.
	 */
	public void verifyNonOrallyArgCases(String cyvCategory, String pe_id, List<UserInputData> userInputData) {
		String category = getRandomReferralCategory(cyvCategory, pe_id, userInputData);
		if (category != null) {
			try {
				performPageLoad(driver);
				assertTrue("Unable to select '" + category + "' from the dashboard",
						 Utility.scrollDownIfNotDisplayed("//XCUIElementTypeOther[@name='Categories']" + containsElement(category)));

				String dbNonOrgCases = category;

				List<String> briefcaseTargReferral_y = executeQuery(
						replace(getID(BRIEFCASE_TARGET_ONLY_Y, pe_id), "CYV_CATEGORY", dbNonOrgCases), userInputData);

				List<String> briefcaseTargReferral_n = executeQuery(
						replace(getID(BRIEFCASE_TARGET_ONLY_N, pe_id), "CYV_CATEGORY", dbNonOrgCases), userInputData);

				performPageLoad(driver);
				List<String> UInonOrallyarguedCases = asList(getNumOfDisplayedCases(total));

				String si_value = CommonPages.getSiValue(SiteTableVariable.targetOnly, userInputData);

				if (si_value.equals("y")) {
					assertEquals(dbNonOrgCases + ":-----RECORD COUNT MISMATCH-----", briefcaseTargReferral_y,
							UInonOrallyarguedCases);
				} else {
					assertEquals(dbNonOrgCases + "-----RECORD COUNT MISMATCH-----", briefcaseTargReferral_n,
							UInonOrallyarguedCases);
				}
				dashboard.click();
				Utility.scroll(categories, "up");

			} catch (org.openqa.selenium.TimeoutException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getRandomReferralCategory(String cyvCategory, String pe_id,
			List<UserInputData> userInputData) {
		List<String> referralCategories = getReffCategories(cyvCategory, pe_id, userInputData);
		Collections.shuffle(referralCategories);
		return referralCategories.isEmpty() ? null : referralCategories.get(0);
	}

	public static List<String> getReffCategories(String cyvCategory, String pe_id, List<UserInputData> userInputData) {
		List<String> referralCategories = executeQuery(
				getID(replace(NON_ORALLY_ARGUED_CASES, "CMR_CYV_CODE", cyvCategory), pe_id), userInputData);
		sort(referralCategories);
		return referralCategories;
	}

	/**
	 * If the chm_mobile_referral.cmr_cyv_code = 'lbrrpt', verify  cyv_category 
	 * displays on the Dashboard page. 
	 */
	public void verifyDocumentsForCategory(String category, String peRtCode, String judgeName,
			List<UserInputData> userData) {
		performPageLoad(driver);
		String peID = DocumentPage.get_pe_id(peRtCode, userData);

		List<String> cmrCyvCode = executeQuery(getID(lbrrpt_CATEGORY, peID), userData);
		if (!cmrCyvCode.contains(category)) {
			return;
		}

		String cyvCategory = getAllColumns(getID(replace(lbrrpt_CYV_CATEGORY, "CMR_CYV_CODE", category), peID),
				userData);

		Utility.scrollDownIfNotDisplayed(
				"//*[contains(@name, 'Categories')]/child::*//*[contains(@name, '" + cyvCategory.trim() + "')]");
		assertTrue("*****PLEASE VERIFY ONLY DOCUMENTS DISPLAY ON THE REFERRAL DETAIL PAGE*****",
				getDocuments(peID, userData));
	}

	public boolean getDocuments(String peID, List<UserInputData> userData) {
		CommonPages.getGroupIcons(GroupIcons.Expand);
		boolean isDisplayed = false;

		List<String> dbDocCategory = executeQuery(getID(lbrrpt_DOCUMENT_CATEGORY, peID), userData);
		sort(dbDocCategory);

		for (String category : dbDocCategory) {
			WebElement uiDocs = findElementBy(Locator.XPATH, containsElement(category));
			if (!uiDocs.isDisplayed()) {
				continue;
			}

			isDisplayed = true;
			uiDocs.click();

			List<String> docDesc = executeQuery(getID(replace(REFERRAL_DOCUMENTS, "CMD_DOC_CATEGORY", category), peID),
					userData);

			boolean categoryDisplayed = false;
			for (String description : docDesc) {
				WebElement uiResult = findElementBy(Locator.XPATH, containsElement(description));
				if (uiResult.isDisplayed()) {
					categoryDisplayed = true;
					break; // Exit the loop once a displayed element is found
				}
			}

			if (!categoryDisplayed) {
				isDisplayed = false; // Update isDisplayed if no elements in the category are displayed
			}
		}

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
				WebElement navNewReferralCount = navNewRefCount(nav.get(i));
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

	public WebElement dashNewRefCount(int index) {
		return findElementBy(Locator.XPATH,
				"(//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText)["
						+ index + "]");
	}

	public WebElement navNewRefCount(int index) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["
						+ index
						+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeStaticText");
	}

}