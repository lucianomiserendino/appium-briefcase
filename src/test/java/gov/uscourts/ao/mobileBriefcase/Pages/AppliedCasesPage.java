package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASE_NUMBER;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.NON_ORALLY_ARGUED_CASES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.TARGET_AND_APPLIED_CASES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.TARGET_CASES;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage.Search;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.SiteTableVariable;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Filter;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AppliedCasesPage extends AppiumPageFactory {


	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[2]")
	public WebElement bookmarkBTN;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Bookmarked'])[2]")
	public WebElement bookOnDashboard;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'linked')]/preceding::XCUIElementTypeStaticText[@name='Viewed'][1]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static List<WebElement> targetCase;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Applied Referrals']/following::XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> appliedCase;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Viewed']/following::XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> redBullet;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[2]")
	public static WebElement searchIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Search by Case Number or Party Name:']/following:: XCUIElementTypeTextField[1]")
	public static WebElement searchTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='SEARCH']")
	public static WebElement searchBTN;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='On Device']")
	public static WebElement on_device;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static WebElement total;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(id = "Categories")
	public static WebElement categories;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	static String dbAppliedCase = "";
	public static String category = "";
	String targetReferral = "";
	String uiAppliedCase = "";

	public static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	public void getBookmarkedReferral(String caseNumber) {

		try {
			if (bookOnDashboard.isDisplayed()) {

				tap(bookOnDashboard);
				if (isDisplayed(Locator.XPATH, "//XCUIElementTypeOther[@name='ReferralsList']/child::*"
						+ containsElement(caseNumber)) == true) {

					findElementBy(Locator.XPATH, containsElement(caseNumber)
							+ "/following::XCUIElementTypeOther[3]//XCUIElementTypeStaticText").click();
					tap(dashboard);
				}
			}
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
	}

	public void selectDate(String date, String panel) {

		scrollDownIfNotDisplayed("//XCUIElementTypeStaticText[@name='" + date
				+ "']/following::XCUIElementTypeOther/XCUIElementTypeStaticText[@name='" + panel + "']");
	}

	public void getAppliedCaseLink(String caseNumber) {
		verifyAppliedCaseLinkIsDisplayed(caseNumber);
		tap(Locator.XPATH, containsElement(caseNumber));
		tap(bookmarkBTN);
		tap(dashboard);
	}

	public void verifyAppliedCaseLinkIsDisplayed(String caseNumber) {
		Assert.assertTrue("******APPLIED CASES LINK ICON DISAPPEARS WHEN BOOKMARKING CASE/REFERRAL******",
				isDisplayed(Locator.XPATH, containsElement(caseNumber)
						+ "/following::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[@name='linked']"));

	}

	public void getAdditionalCaseInfoScreen(List<UserInputData> userInputData) {

		Assert.assertTrue(
				"Additional case information screen is not displayed when selecting an applied case: " + dbAppliedCase,
				Actions.isDisplayed(Locator.XPATH, containsElement("Case Caption")));
		Assert.assertTrue(Actions.isDisplayed(Locator.XPATH, containsElement("Docket Entries")));
		Assert.assertTrue(Actions.isDisplayed(Locator.XPATH, containsElement("Associated Cases")));

	}

	public static List<String> caseList(List<WebElement> element) {
		String[] dest;
		List<String> referrals = new ArrayList<>();
		performPageLoad(driver);
		Iterator<WebElement> itr = element.iterator();
		while (itr.hasNext()) {
			dest = itr.next().getText().split(" ");
			referrals.add(dest[0].trim());
		}

		return referrals;
	}

	public static String getRandomTargetCase(List<String> caseList) {
		int randomCase = Utility.getRandomInt(caseList.size() - 1);

		String caseNum = caseList.get(randomCase);

		Actions.tap(Locator.XPATH, Actions.containsElement(caseNum));

		return caseNum;

	}

	public void changeSiValue(String val, List<UserInputData> userInputData) {
		CommonPages.setValue(val, "briefcaseTargetOnly", userInputData);
	}

	public static String getSiVal(List<UserInputData> userInputData) {
		return CommonPages.getSiValue(SiteTableVariable.targetOnly, userInputData);
	}

	/**
	 * If briefcaseTargetOnly is set to 'y', the referral will be listed for the
	 * target case only and an indicator will display if the referral was sent to
	 * multiple cases. If the site table variable is set to 'n' or does not exist,
	 * the referral will be listed for each case.
	 */

	public static void searchForAppliedCase(List<UserInputData> userInputData) {

		String pe_id = DocumentPage.get_pe_id("jud", userInputData);

		Boolean elementNotFound = true;

		List<String> referralCategories = executeQuery(
				getID(replace(NON_ORALLY_ARGUED_CASES, "CMR_CYV_CODE", "lbrrpt"), pe_id), userInputData);
		sort(referralCategories);

		while (elementNotFound) {

			for (int i = 0; i < referralCategories.size(); ++i) {

				category = referralCategories.get(i);

				List<String> briefcaseTargReferral_y = executeQuery(
						replace(getID(TARGET_CASES, pe_id), "CYV_CATEGORY", category), userInputData);

				List<String> briefcaseTargReferral_n = executeQuery(
						replace(getID(TARGET_AND_APPLIED_CASES, pe_id), "CYV_CATEGORY", category), userInputData);

				List<String> appliedCases = Utility.filterArraylistItems(Filter.UNIQUE_VALUES, briefcaseTargReferral_y,
						briefcaseTargReferral_n);

				int size = appliedCases.size();

				if (!(size == 0)) {

					int randomAppliedCase = 0;

					if (size > 1) {

						randomAppliedCase = Utility.getRandomNumberInRange(1, appliedCases.size() - 1);
					} else {
						randomAppliedCase = 1;
					}

					dbAppliedCase = DBUtilities.getAllColumns(
							replace(CASE_NUMBER, "CS_CASEID", appliedCases.get(randomAppliedCase)), userInputData);
					elementNotFound = false;
					break;

				} else {
					elementNotFound = true;
				}

			}

		}
	}

	public void navigateToAppliedReferral(List<UserInputData> userInputData) {

		if (getSiVal(userInputData).equals("y")) {
			CaseQueryPage casequerypage = new CaseQueryPage();
			casequerypage.searchForACase(category, dbAppliedCase, Search.caseNumber);

			CommonPages page = new CommonPages();
			page.getGroupIcons(GroupIcons.Expand);
			scrollDownIfNotDisplayed(containsElement("Applied"));

			Assert.assertTrue(
					"THE USER IS DIRECTED TO THE APPLIED CASE DETAIL PAGE, SHOULD BE DIRECTED TO THE TARGET CASE DETAIL PAGE",
					scrollDownIfNotDisplayed(containsElement(dbAppliedCase)));
			ifDownloaded(inProgress);
			getAdditionalCaseInfoScreen(userInputData);

		} else {
			scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");

			Assert.assertTrue("Failed to verify that the applied case '" + dbAppliedCase
					+ "' is listed on the referral list page when 'briefcaseTargetOnly' is set to 'n', category: "
					+ category, Utility.isDisplayed(Actions.containsElement(dbAppliedCase)));

		}
	}

}
