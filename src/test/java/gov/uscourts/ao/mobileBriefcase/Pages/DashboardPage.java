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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Dialect;
import org.openqa.selenium.remote.RemoteWebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.SiteTableVariable;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
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


	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static WebElement dashboard;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='MasterNavPage']/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell")
	public static List<WebElement> navIcons;


	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'NEW OUT OF')]/preceding::XCUIElementTypeStaticText[1]")
	public static List<WebElement> dashCategories;

	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"nav\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther")
	public static List<WebElement> navCategories;
	
	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Categories\"]/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<WebElement> dashCategoryCell;
	
	
	
	public void verifyPendingTasksPosition(String page) {
	    performPageLoad(driver);

	    List<String> categoryList = new ArrayList<>();

	    if (page.equals("Dashboard")) {
	        for (WebElement dashCategory : dashCategories) {
	            categoryList.add(dashCategory.getAttribute("value").trim());
	        }
	    } else if (page.equals("Navigation")) {
	        List<WebElement> navCategoryCells = navIcons.subList(2, navIcons.size());

	        for (WebElement navCell : navCategoryCells) {
	            List<WebElement> navTexts = navCell.findElements(By.xpath(".//XCUIElementTypeStaticText"));
	            if (navTexts.isEmpty()) continue;

	            String navCategoryName = navTexts.get(0).getText().trim();

	            if (!navCategoryName.equals("Tools") && !navCategoryName.equals("Bookmarked")) {
	                categoryList.add(navCategoryName);
	            }
	        }
	    }

	    // Verify the first category is "Pending Tasks"
	    if (!categoryList.isEmpty()) {
	        assertEquals("Pending Tasks should be the first category on " + page, "Pending Tasks", categoryList.get(0));
	    } else {
	        fail("Category list is empty for page: " + page);
	    }
	    Actions.tap(collapseBtn);
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

	public void verifyReferralCategoriesAreDisplayed(List<UserInputData> userInputData) {
	    String peId = DocumentPage.get_pe_id("jud", userInputData);
	    List<String> dbReferralCategories = executeQuery(getID(Queries.REFERRAL_CATEGORIES, peId), userInputData);

	    dbReferralCategories.sort(String::compareTo);

	    if (!dbReferralCategories.isEmpty()) {
	        performPageLoad(driver);

	        for (String category : dbReferralCategories) {
	            verifyCategoryIsDisplayedAndScrollDown(category);
	        }
	    }
	}

	private void verifyCategoryIsDisplayedAndScrollDown(String category) {
	    boolean found = false;

	    for (int attempt = 0; attempt < 3; attempt++) { 
	        if (isCategoryVisible(category)) {
	            found = true;
	            break;
	        }
	        Utility.scroll(categories, "up"); 
	        performPageLoad(driver);
	    }

	    assertTrue("Category not found on screen: " + category, found);

	    Utility.scroll(categories, "down"); 
	    performPageLoad(driver);
	
}
	
	private boolean isCategoryVisible(String categoryName) {
	    String xpath = String.format(
	        "//XCUIElementTypeOther[@name='Categories']//XCUIElementTypeStaticText[contains(@name, \"%s\")][1]",
	        categoryName
	    );
	    try {
	        List<WebElement> elements = findElements(By.xpath(xpath));
	        return !elements.isEmpty() && elements.get(0).isDisplayed();
	    } catch (WebDriverException e) {
	        System.err.println("Error checking category visibility: " + e.getMessage());
	        return false;
	    }
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
	public void verifyDocumentsForCategory(String category, String peRtCode, String judgeName, List<UserInputData> userData) {
	    performPageLoad(driver);

	    String peID = DocumentPage.get_pe_id(peRtCode, userData);
	    List<String> categories = executeQuery(getID(lbrrpt_CATEGORY, peID), userData);

	    if (!categories.contains(category)) {
	        return; 
	    }

	    String cyvCategory = getAllColumns(getID(replace(lbrrpt_CYV_CATEGORY, "CMR_CYV_CODE", category), peID), userData).trim();

	    verifyAndClickCategory(cyvCategory);

	    assertTrue("*****PLEASE VERIFY ONLY DOCUMENTS DISPLAY ON THE REFERRAL DETAIL PAGE*****", 
	               getDocuments(peID, userData));
	}

	private void verifyAndClickCategory(String categoryName) {
	    for (int attempt = 1; attempt <= 3; attempt++) {
	        if (isCategoryVisible(categoryName)) {
	            clickCategory(categoryName);
	            return;
	        }
	        Utility.scroll(categories, "up");
	        performPageLoad(driver);
	    }
	    fail("Category not found after 3 scroll attempts: " + categoryName);
	}


	private void clickCategory(String categoryName) {
	    String xpath = String.format(
	        "//XCUIElementTypeOther[@name='Categories']//XCUIElementTypeStaticText[contains(@name, \"%s\")][1]",
	        categoryName
	    );
	    WebElement element = Actions.findElement(By.xpath(xpath));
	    element.click();
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

	public void verifyNavBadgeCountsAgainstDashboard() {
	    collapseBtn.click();

	    List<WebElement> navCells = navIcons;
	    List<WebElement> navCategoryCells = navCells.subList(2, navCells.size());

	    int categoriesVerified = 0;

	    for (WebElement navCell : navCategoryCells) {
	        List<WebElement> navTexts = navCell.findElements(By.xpath(".//XCUIElementTypeStaticText"));
	        if (navTexts.isEmpty()) continue;

	        String navCategoryName = navTexts.get(0).getText();

	        // Skip "Tools" and "Bookmarked"
	        if (navCategoryName.equals("Tools") || navCategoryName.equals("Bookmarked")) {
	            continue;
	        }
	        String navBadgeCount = navTexts.size() > 1 ? navTexts.get(1).getText().trim() : "0";
	        String dashBadgeCount = "0";

	        String xpathForDashboardBadge = String.format(
	            "//XCUIElementTypeOther[@name='Categories']//XCUIElementTypeStaticText[contains(@name, \"%s\")]/following::XCUIElementTypeStaticText[contains(@name, 'NEW OUT OF')][1]",
	            navCategoryName
	        );

	        WebElement dashBadge = null;

	        try {
	            dashBadge = driver.findElement(By.xpath(xpathForDashboardBadge));
	        } catch (NoSuchElementException e) {
	            System.out.println("Badge not found for: " + navCategoryName);
	        }
	        if (dashBadge != null) {
	            dashBadgeCount = dashBadge.getText().split(" ")[0].trim();
	        }
	        System.out.println("Comparing [" + navCategoryName + "] - Left Nav Badge: " + navBadgeCount + ", Dashboard Badge: " + dashBadgeCount);

	        if (dashBadgeCount.equals("0")) {
	            assertEquals("Expected no red badge for: " + navCategoryName, "0", navBadgeCount);
	        } else {
	            assertEquals("Mismatch in badge count for: " + navCategoryName, dashBadgeCount, navBadgeCount);
	        }
	        categoriesVerified++;

	        if (categoriesVerified % 10 == 0) {
	        	Utility.scrollPage("up");
	        }
	    }
	}


}