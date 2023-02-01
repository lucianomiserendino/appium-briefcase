package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.stepDefinitions.Document_StepDefinitions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class StaffAttorneyReferralSortPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "XCUIElementTypeStaticText[@name='Asc'][1]")

	@iOSXCUITFindBy(xpath = "XCUIElementTypeStaticText[@name='Desc'][1]")
	public static WebElement desc;

	public static void selectSortOption(String opt, List<UserInputData> table) {

		selectSortOption(opt);

		if (opt.equals("Default")) {
			/**
			 * the referrals should be grouped in accordion panels by referral category and
			 * sorted by case number
			 */
			getReferralCategoryList(table);
			sortedInAscendingOrder(1);

		} else if (opt.equals("Case Number")) {
			int order = getRandomInt(opt);
			sortedInAscendingOrder(order);

		}

	}

	public static void selectSortOption(String sortOption) {
		getSortButton();
		sortBy(sortOption).click();
		Actions.contains("Apply").click();
		CommonPages page = new CommonPages();
		page.getGroupIcons();
	}

	public static void getSortButton() {
		ReferralSortOrderPage page = new ReferralSortOrderPage();
		page.selectSortBtn();
		assertTrue("A SORT POP-UP DOESN'T DISPLAY FOR STAFF ATTORNEYS",
				Actions.isDisplayed(Actions.contains("Sorting Options")));

	}

	public static WebElement sortBy(String sortOption) {
		return Actions.findElement(By.xpath(Actions.containsElement(sortOption)));
	}

	public static void getReferralCategoryList(List<UserInputData> table) {
		String smr_sfa_code = Document_StepDefinitions.stfCategory;

		String smr_assign_pe_id = DocumentPage.get_pe_id("stf", table);
		List<String> uiRefCategories = new ArrayList<>();

		List<String> dbRefCategories = executeQuery(replace(Queries.SAs_REFERRAL_CATEGORIES, "SMR_ASSIGN_PE_ID",
				smr_assign_pe_id, "SMR_SFA_CODE", smr_sfa_code), table);
		sort(dbRefCategories);

		for (int i = 1; i < dbRefCategories.size() + 1; ++i) {
			WebElement categoryName = StaffAttorneyReferralPage.getRefCategory(i, 2);

			uiRefCategories.add(categoryName.getText().trim());
		}
		assertTrue(Utility.ifSortedInAlphabeticalOrder(uiRefCategories));

	}

	public static int getRandomInt(String sortOption) {
		String el = "";
		int random = Utility.getRandomInt(2);
		if (random == 1) {
			el += "Asc";
		} else if (random == 2) {
			el += "Desc";
		}
		Actions.findElement(By.xpath(
				Actions.containsElement(sortOption) + "/following::XCUIElementTypeStaticText[@name='" + el + "'][1]"));
		return random;
	}

	public static void sortedInAscendingOrder(int order) {

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

		List<WebElement> el = Actions.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name, '" + catN
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '-')]"));

		List<String> sortedBy = Utility.retrieveAllReferrals(el, " ", 0);

		if (order == 2) {

			Collections.reverse(sortedBy);

		}

		assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER IN ASCENDING ORDER", Utility.checkIfSorted(sortedBy));
	}

}
