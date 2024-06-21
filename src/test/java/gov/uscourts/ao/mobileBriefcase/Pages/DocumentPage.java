package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeDBQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.SiteTableVariable;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import gov.uscourts.ao.mobileBriefcase.stepDefinitions.Document_StepDefinitions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DocumentPage extends AppiumPageFactory {

//	public DocumentPage() {
//		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
//	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> regCaseNum;

	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, 'Actions')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static List<WebElement> docCategories;

	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<WebElement> GroupIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Applied Referrals')]")
	public static List<WebElement> appliedRefs;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'linked')]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static List<WebElement> targetCase;

	@iOSXCUITFindBy(xpath = "//*[@name='Applied Referrals' or @name='Associated Cases']/following::XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> appliedCase;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Bookmark_Container']/preceding:: XCUIElementTypeStaticText[contains(@name, 'Panel:')]/preceding:: XCUIElementTypeStaticText[1]")
	public static List<WebElement> caseOncalender;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'EN BANC ')]")
	public static List<WebElement> enBanc;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Downloaded_Container'])[1]")
	public static WebElement downloaded;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PDF Page View']")
	public static List<WebElement> activityIndicator;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'View Case Info')]")
	public static List<WebElement> viewCaseInfo;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Docket Entries ')]")
	public static WebElement docketEntries;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Progress halted' or @name='In progress']")
	public static List<WebElement> progressInd;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'EN BANC ')]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static List<WebElement> enBancCases;

	static String panel = "";
	String randomCategory = "";
	public static String randomDocument = "";
	public final static String jud = "jud";
	public final static String stf = "stf";
	public final static String judFirstName = "judFirstName";
	public final static String stfFirstName = "stfFirstName";

	public static String cs_caseid = "";
	public static String caseNum = "";
	public static String category = "";
	public static String cmr_cyv_code = "";

	public static String selectRandomItem(String query, String xpath, List<UserInputData> userInputData) {
		String category = "";
		List<String> list = null;

		List<String> dbResult = executeQuery(query, userInputData);
		list = dbResult;
		if (list.contains("Reference Documents") | list.contains("Pending Tasks") | list.contains("Cases on Calendar")
				| list.contains("No Argument Case"))
			list.remove("Reference Documents");
		list.remove("Pending Tasks");
		list.remove("Cases on Calendar");
		list.remove("No Argument Case");
		list.remove("Oral Argument");
		sort(list);

		category = list.get(Utility.getRandomInt(list.size() - 1));
		scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");

		return category;

	}

	public void selectRandomCategoryAndCase(List<UserInputData> userInputData) {
		String peId = DocumentPage.get_pe_id("jud", userInputData);
		String query;
		String si_value = CommonPages.getSiValue(SiteTableVariable.targetOnly, userInputData);

		if ("y".equals(si_value)) {
			query = Queries.FIND_ALL_NON_ORALLY_ARGUED_CASES;
		} else {
			query = Queries.FIND_ALL_ORALLY_ARGUED_CASES;
		}

		List<String[]> findCase = executeDBQuery(getID(query, peId), userInputData);

		if (findCase != null && !findCase.isEmpty()) {
			int randomIndex = new Random().nextInt(findCase.size());
			String[] randomRecord = findCase.get(randomIndex);
			cs_caseid = randomRecord[0].trim();
			caseNum = randomRecord[1].trim();
			category = randomRecord[2].trim();
			cmr_cyv_code = randomRecord[3].trim();
		}

		scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");
		scrollDownIfNotDisplayed("//XCUIElementTypeStaticText[contains(@name, '"+caseNum+"')]");
	}

	public String selectRandomSTFCategory(List<UserInputData> userInputData) {

		String category = "";
		String category_code = "";

		List<String> sfa_display = getAssignmentCategories(2, userInputData);
		List<String> smr_sfa_code = getAssignmentCategories(3, userInputData);

		sort(sfa_display);

		int randomCat = Utility.getRandomNumberInRange(1, sfa_display.size() - 1);

		category = sfa_display.get(randomCat);
		category_code = smr_sfa_code.get(randomCat);

		scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category.trim() + "')]");

		return category_code.trim();

	}

	public static List<String> getAssignmentCategories(int col, List<UserInputData> userInputData) {

		return execute(Actions.replace(Queries.SAs_ASSIGNMENT_CATEGORIES, "RA_PE_ID", get_pe_id("stf", userInputData)),
				col, userInputData);
	}

	public String selectRandomJudgeCategory(List<UserInputData> userInputData) {
		return selectRandomItem(getID(Queries.REFERRAL_CATEGORIES, get_pe_id("jud", userInputData)), xpath,
				userInputData);

	}

	public static String get_pe_id(String pe_rt_code, List<UserInputData> userInputData) {
		String courtId = SystemPropertySetup.getCourtId(userInputData) + ".";

		String fName = "";
		String lName = "";

		if (pe_rt_code.equals("jud")) {

			lName = getProperty(courtId + jud);
			fName = getProperty(courtId + judFirstName);

		} else if (pe_rt_code.equals("stf")) {
			lName = getProperty(courtId + stf);
			fName = getProperty(courtId + stfFirstName);
		}

		return getPE_ID(pe_rt_code, lName, fName, userInputData);

	}

	public static List<String> getDocumentCategories() {

		List<String> categories = new ArrayList<>();

		for (int i = 0; i < docCategories().size(); i++) {
			categories.add(docCategories().get(i).getText());
		}
		String docName = "";
		if (categories.size() >= 1) {
			docName = categories.get(0);
			contains(categories.get(0)).click();
		} else {

			int randomDoc = Utility.getRandomNumberInRange(1, categories.size() - 1);
			docName = categories.get(randomDoc).trim();
			contains(docName).click();
		}

		int randomDoc = getRandomDocument(docName);

		click(randomDoc, docName);

		return categories;
	}

	public static List<WebElement> getDocName(String text, String categoryName) {
		int index;
		if (text.equals("docCategory")) {
			index = 1;
		} else {
			index = 2;
		}
		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + categoryName
				+ "']/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther["
				+ index + "]/XCUIElementTypeStaticText"));
	}

	public static int getSize(String categoryName) {

		return getDocName("docCategory", categoryName).size();
	}

	public static int getRandomDocument(String categoryName) {
		return Utility.getRandomInt(getSize(categoryName));
	}

	public static String getText(String text, int i, String categoryName) {
		return getDocName(text, categoryName).get(i).getText();
	}

	public static void click(int index, String categoryName) {
		getDocName("docCategory", categoryName).get(index).click();
	}

	public static List<WebElement> docCategories() {
		String pane = "";
		if (appliedRefs.size() > 0) {
			pane = "Applied Referrals";
		} else {
			pane = "Actions";
		}
		return Actions.findElements(By.xpath(
				"//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, '"
						+ pane + "')]"
						+ "/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText"));
	}

	public static String selectRandomCaseNumber(List<WebElement> element) {
		String referral = "";
		Page.performPageLoad(driver);

		// Filter out en banc cases
		List<WebElement> filteredElements = element.stream()
				.filter(e -> enBancCases.stream().noneMatch(enBanc -> enBanc.getText().equals(e.getText())))
				.collect(Collectors.toList());

		List<String> list = Utility.retrieveAllReferrals(filteredElements, " ", 0);

		int caseN = 0;
		if (list.size() > 1) {
			caseN = Utility.getRandomInt(list.size() - 1);
		} else {
			caseN = 0;
		}

		WebElement uiResult = findElementBy(Locator.XPATH,
				"//XCUIElementTypeStaticText[contains(@name, '" + list.get(caseN) + "')]");

		referral = list.get(caseN);
		uiResult.click();
		return referral;
	}

	public static void getAppliedCase() {
		scrollDownIfNotDisplayed("//*[@name='Applied Referrals' or @name='Associated Cases']");
	}

	public static String getRandomCase(Category cat) {

		List<WebElement> element = null;

		switch (cat) {
		case judgeRegularCase:
			element = regCaseNum;
			break;

		case targetCase:
			element = targetCase;
			break;

		case appliedCase:
			CommonPages page = new CommonPages();
			page.getGroupIcons(GroupIcons.Expand);
			getAppliedCase();
			DocumentPage p = new DocumentPage();
			element = p.appliedCase;
			break;

		case CaseOnCalendar:

			element = caseOncalender;
			break;

		case EnBanc:

			element = enBanc;
			break;

		default:
			break;
		}
		return selectRandomCaseNumber(element);
	}

	public void getDocumentCategories(List<UserInputData> userInputData) {

		DocumentPage.getDocumentCategoryList(userInputData);
		String pane = DocumentPage.panel;

		List<String> uiDocList = new ArrayList<>();

		for (int k = 0; k < getDocList(pane).size(); k++) {
			uiDocList.add(getDocList(pane).get(k).getText().split(",")[1].split("Pages")[0]);
		}
		assertTrue("DOCUMENTS ARE NOT ORDERED BY THE FILED DATE: ", Utility.checkIfSorted(uiDocList));

	}

	public List<WebElement> getDocList(String panel) {

		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + panel
				+ "']/following::XCUIElementTypeStaticText[contains(@name, ',')]"));

	}

	public static List<String> getDocumentCategoryList(List<UserInputData> userInputData) {
		String caseNum = Document_StepDefinitions.regularCase;
		String cmr_cs_caseid = CommonPages.getCaseID(caseNum, userInputData);
		String cmr_cyv_code = CommonPages
				.cmr_cyv_code(Document_StepDefinitions.judCategory, cmr_cs_caseid, userInputData).trim();
		String cmr_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);

		List<String> uiDocCategories = new ArrayList<>();
		panel = (appliedRefs.size() > 0) ? "Applied" : "Actions";

		for (WebElement element : getDocCategoryLocator(panel)) {
			uiDocCategories.add(element.getText());
		}

		List<String> dbDocCategoriesRaw = executeQuery(Actions.replace(DOCUMENT_CATEGORIES, "CMR_CYV_CODE",
				cmr_cyv_code, "CMR_JU_PE_ID", cmr_ju_pe_id, "CMR_CS_CASEID", cmr_cs_caseid), userInputData);

		if (dbDocCategoriesRaw == null || dbDocCategoriesRaw.isEmpty()) {
			fail("No document categories returned from database.");
		}

		List<String> dbCategoryNames = new ArrayList<>();
		Map<String, Integer> dbCategorySortMap = new HashMap<>();

		for (String record : dbDocCategoriesRaw) {
			String[] parts = record.split(",");
			String category = parts[0].trim();
			int sortOrder = (parts.length > 1) ? Integer.parseInt(parts[1].trim()) : 0; // Default sort order to 0 if
																						// not provided
			dbCategoryNames.add(category);
			dbCategorySortMap.put(category, sortOrder);
		}

		for (int i = 1; i < dbCategoryNames.size(); i++) {
			String prevDbCategory = dbCategoryNames.get(i - 1);
			int prevDbSortOrder = dbCategorySortMap.get(prevDbCategory);
			int prevUiIndex = uiDocCategories.indexOf(prevDbCategory);

			String dbCategory = dbCategoryNames.get(i);
			int dbSortOrder = dbCategorySortMap.get(dbCategory);
			int uiIndex = uiDocCategories.indexOf(dbCategory);

			if (dbSortOrder > prevDbSortOrder && uiIndex < prevUiIndex) {
				fail("DOCUMENT CATEGORIES ARE NOT SORTED ON THE REFERRAL DETAIL PAGE, EXPECTED: " + dbCategoryNames
						+ " ACTUAL: " + uiDocCategories);
			}
		}

		assertEquals("UI categories do not match expected categories from database", dbCategoryNames, uiDocCategories);

		return dbCategoryNames;
	}

	public void getDocumentList(List<UserInputData> userInputData) {

		if (getDocumentCategoryList(userInputData).size() > 0) {

			randomCategory = Utility.clickOnNumberInRange(getDocCategoryLocator(panel));

			randomDocument = Utility.clickOnNumberInRange(getDocListLocator(randomCategory));

			performPageLoad(driver);
			Boolean elementNotFound = true;
			while (elementNotFound) {
				if (!(activityIndicator.size() == 1)) {

					elementNotFound = true;
					Utility.tapAndSwipe(Direction.UP);

				} else {
					elementNotFound = false;
					break;
				}

			}

		}

	}

	public List<WebElement> getDocListLocator(String categoryName) {
		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + categoryName + "']/following::"
				+ "XCUIElementTypeOther[@name='Downloaded_Container']/preceding::XCUIElementTypeStaticText[2]"));
	}

	public static List<WebElement> getDocCategoryLocator(String panel) {

		return Actions.findElements(By.xpath(
				"//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, '"
						+ panel
						+ "')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText"));

	}

	public void navigateToViewCaseInfo(String actionName) {
		// Scroll until viewCaseInfo is visible
		while (viewCaseInfo.size() != 1) {
			Utility.tapAndSwipe(Direction.DOWN);
		}

		// Click on the first viewCaseInfo element
		viewCaseInfo.get(0).click();

		ifDownloaded(inProgress);

		// Wait for docketEntries to be clickable
		Page.waitToBeClickable(docketEntries, driver);

		ifDownloaded(inProgress);
		// Wait for actionName to be clickable
		// Remove single quote if present
		actionName = actionName.contains("'") ? actionName.split("'")[0] : actionName;
		Utility.tapAndSwipe(Direction.DOWN);
		Page.waitForPresenceOfElementLocated(By.xpath("(//*[contains(@name, '" + actionName + "')])[1]"), driver)
				.click();
		// Page.waitToBeClickable(contains(actionName), driver);
	}

	public enum Category {
		Referral_Category, Referral, Panel, judgeRegularCase, targetCase, appliedCase, CaseOnCalendar, EnBanc
	}

}
