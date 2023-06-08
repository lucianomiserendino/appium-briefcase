package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.stepDefinitions.Document_StepDefinitions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class StaffAttorneyReferralSortPage extends AppiumPageFactory {

	// @WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sort ↓']")
	public static List<WebElement> sortArrowDownBtn;

	// @WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sort ↑']")
	public static List<WebElement> sortArrowUpBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> caseNum;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther//following::XCUIElementTypeStaticText[contains(@name, 'Referred')]")
	public static List<WebElement> referred;

	public static void selectSortOption(String opt, List<UserInputData> table) {

		int ran = selectSortOption(opt);

		if (opt.equals("Default")) {
			/**
			 * the referrals should be grouped in accordion panels by referral category and
			 * sorted by case number
			 */
			sortedBy(opt, Queries.SAs_REFERRAL_CATEGORIES, ran, table);

			sortedByCase(ran);

		} else if (opt.equals("Case Number")) {

			sortedByCase(ran);

		} else if (opt.equals("Status")) {

			sortedBy(opt, Queries.SMR_STATUS, ran, table);

		} else if (opt.equals("Referred") & opt.equals("Received")) {
			sortByDate(opt, ran);

		}

	}

	public static int selectSortOption(String sortOption) {
		int random = 0;
		getSortButton();
		if (sortOption.equals("Default")) {
			sortBy(sortOption).click();
			random = 1;
		} else {
			random = getRandomInt(sortOption);
		}

		Actions.contains("Apply").click();
		CommonPages page = new CommonPages();
		page.getGroupIcons(GroupIcons.Expand);
		return random;
	}

	public static void getSortButton() {

		if (sortArrowDownBtn.size() > 0) {
			tap(sortArrowDownBtn.get(0));
		} else {
			tap(sortArrowUpBtn.get(0));
		}

	}

	public static WebElement sortBy(String sortOption) {
		return Actions.findElement(By.xpath(Actions.containsElement(sortOption)));
	}

	public static int getRandomInt(String sortOption) {
		String el = "";
		int random = Utility.getRandomNumberInRange(1, 2);
		if (random == 1) {
			el += "Asc";
		} else if (random == 2) {
			el += "Desc";
		}
		Actions.findElement(By.xpath(
				Actions.containsElement(sortOption) + "/following::XCUIElementTypeStaticText[@name='" + el + "'][1]"))
				.click();
		return random;
	}

	public static String getRandomCategory() {
		List<Integer> count = new ArrayList<>();

		List<WebElement> el1 = Actions.findElements(By.xpath(
				"//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther//following::XCUIElementTypeStaticText[contains(@name, '(')]"));

		int s = el1.size();

		for (int i = 0; i < s; i++) {

			count.add(Integer.parseInt(Actions.replace(el1.get(i).getText(), "\\(", "", "\\)", "")));
		}

		Integer max = Collections.max(count);

		String categoryName = Actions.containsElement("(" + max.toString() + ")")
				+ "/preceding:: XCUIElementTypeStaticText[1]";

		String catN = Actions.findElementBy(Locator.XPATH, categoryName).getText().trim();
		Actions.tap(Locator.XPATH, categoryName);
		return catN;
	}

	public void selectRandomCase(String categoryName) {
		DocumentPage page = new DocumentPage();
		page.selectRandomCaseNumber(getSTFReferrals(categoryName));
	}

	public static List<WebElement> getSTFReferrals(String categoryName) {
		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name, '" + categoryName
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '-')]"));
	}

	public static void sortedByCase(int order) {

		List<WebElement> el = getSTFReferrals(getRandomCategory());

		List<String> sortedBy = Utility.retrieveAllReferrals(el, " ", 0);

		if (order == 2) {

			Collections.reverse(sortedBy);

		}
		assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER IN ASCENDING ORDER", Utility.checkIfSorted(sortedBy));
	}

	public static void sortedBy(String opt, String query, int order, List<UserInputData> table) {

		String smr_sfa_code = Document_StepDefinitions.stfCategory;

		String smr_assign_pe_id = DocumentPage.get_pe_id("stf", table);

		List<String> uiRefCategories = new ArrayList<>();

		List<String> dbRefCategories = executeQuery(
				replace(query, "SMR_ASSIGN_PE_ID", smr_assign_pe_id, "SMR_SFA_CODE", smr_sfa_code), table);

		for (int i = 0; i < dbRefCategories.size(); ++i) {

			if (opt.equals("Status") && dbRefCategories.get(i).isEmpty()) {
				dbRefCategories.set(i, "No Status");
			}
			Page.performPageLoad(driver);
			WebElement categoryName = StaffAttorneyReferralPage.getRefCategory(i, 2);

			uiRefCategories.add(categoryName.getText().trim());
		}

		if (order == 2) {

			Collections.reverse(uiRefCategories);

		}
		assertEquals(uiRefCategories, dbRefCategories);
		assertTrue(Utility.checkIfSorted(uiRefCategories));

	}

	public static void sortByDate(String opt, int sortOder) {
		String el = "";
		if (sortOder == 1) {
			el += "Ascending";
		} else if (sortOder == 2) {
			el += "Descending";
		}
		Actions.findElement(By.xpath(Actions.containsElement(opt + " " + el))).click();

		List<String> sortedBy = Utility.retrieveAllReferrals(referred, " ", 1);

		if (sortOder == 2) {

			Collections.reverse(sortedBy);

		}

		assertTrue("REFERRALS ARE NOT SORTED BY " + opt + " IN " + sortOder + " ORDER",
				Utility.checkIfSorted(sortedBy));

	}

}
