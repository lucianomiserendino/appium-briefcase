package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
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

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther//following::XCUIElementTypeStaticText[contains(@name, 'Received')]")
	public static List<WebElement> received;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Default']")
	public static WebElement defaultBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"SortOptions\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]")
	public static List<WebElement> sortOptions;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, '(')]/preceding::XCUIElementTypeStaticText[1]")
	public static List<WebElement> subCategory;

	static String opt = "";

	public static void selectSortOption(List<UserInputData> table) {

		int ran = selectSortOption();

		switch (opt) {
		case "Default":
			sortedBy(opt, Queries.SAs_REFERRAL_CATEGORIES, ran, table);
			sortedByCase(ran);
			break;

		case "Case Number":
			sortedByCase(ran);
			break;

		case "Status":
			sortedBy(opt, Queries.SMR_STATUS, ran, table);
			break;

		case "Referred":
		case "Received":
		case "Due":
			sortByDate(opt, ran);
			break;
		}
	}

	public static int selectSortOption() {
		int random;
		getSortButton();

		int ran = Utility.getRandomNumberInRange(0, sortOptions.size() - 1);
		opt = sortOptions.get(ran).getText().trim();

		if (opt.equals("Default")) {
			defaultBtn.click();
			random = 1;
		} else {
			random = getRandomInt(opt);
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
		el += (random == 1) ? "Asc" : "Desc";
		Actions.findElement(By.xpath("//XCUIElementTypeStaticText[@name='" + sortOption
				+ "']/following::XCUIElementTypeStaticText[@name='" + el + "'][1]")).click();

		return random;
	}

	public static String getRandomCategory() {
		List<Integer> count = new ArrayList<>();

		List<WebElement> el1 = Actions.findElements(By.xpath(
				"//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther//following::XCUIElementTypeStaticText[contains(@name, '(')]"));

		for (WebElement element : el1) {
			count.add(Integer.parseInt(Actions.replace(element.getText(), "\\(", "", "\\)", "")));
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
		return Actions.findElements(By
				.xpath("//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]"
						+ "/XCUIElementTypeOther/following::XCUIElementTypeStaticText[contains(@name, '-')][1]"));
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

		List<String> dbRefCategories = executeQuery(
				replace(query, "SMR_ASSIGN_PE_ID", smr_assign_pe_id, "SMR_SFA_CODE", smr_sfa_code), table);

		List<String> uiRefCategories = new ArrayList<>();

		for (WebElement element : subCategory) {
			uiRefCategories.add(element.getText());
		}

		if (order == 2) {
			Collections.reverse(uiRefCategories);
		}

		assertEquals("Record count mismatch", uiRefCategories.size(), dbRefCategories.size());
		assertTrue("Categories are not sorted by status", Utility.checkIfSorted(uiRefCategories));
	}

	public static void sortByDate(String opt, int sortOrder) {
		String order = sortOrder == 1 ? "Ascending" : "Descending";

		Actions.findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[@name='" + opt + " " + order + "']").click();

		List<WebElement> dates = getDates(opt, order);
		List<String> sortedBy = retrieveAllReferrals(dates, opt);

		if (sortOrder == 2) {
			Collections.reverse(sortedBy);
		}

		String format = "M/d/yyyy";

		assertTrue("REFERRALS ARE NOT SORTED BY " + opt.toUpperCase() + "DATE IN " + order + " ORDER",
				Utility.checkDatesForAscOrder(sortedBy, format));
	}

	public static List<WebElement> getDates(String opt, String order) {
		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + opt + " " + order
				+ "']/following::" + "XCUIElementTypeStaticText[contains(@name, '" + opt + "')]"));
	}

	public static List<String> retrieveAllReferrals(List<WebElement> elements, String opt) {
		List<String> referrals = new ArrayList<>();
		if (elements.size() > 0) {
			performPageLoad(driver);
			for (WebElement element : elements) {
				String text = element.getText();
				String date = "";
				if (opt.equals("Referred")) {
					int referredIndex = text.indexOf("Referred ");
					if (referredIndex != -1) {
						int receivedIndex = text.indexOf("Received ", referredIndex);
						int dueIndex = text.indexOf("Due ", referredIndex);
						if (receivedIndex != -1 && (dueIndex == -1 || receivedIndex < dueIndex)) {
							date = text.substring(referredIndex + 9, receivedIndex);
						} else if (dueIndex != -1 && (receivedIndex == -1 || dueIndex < receivedIndex)) {
							date = text.substring(referredIndex + 9, dueIndex);
						} else {
							date = text.substring(referredIndex + 9);
						}
					}
				} else if (opt.equals("Received")) {
					date = text.substring(text.indexOf("Received ") + 9);
				} else if (opt.equals("Due")) {
					int dueIndex = text.indexOf("Due ");
					if (dueIndex != -1) {
						date = text.substring(dueIndex + 4);
					}
				}
				referrals.add(date.trim());
			}
		} else {
			throw new RuntimeException("Verify " + opt + " dates are displayed on the page");
		}
		return referrals;
	}

}
