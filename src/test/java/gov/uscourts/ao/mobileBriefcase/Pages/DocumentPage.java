package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
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
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup.Variables;
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

	static String panel = "";
	String randomCategory = "";
	public static String randomDocument = "";

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
		list.remove("Oral Arguments");
		sort(list);

		category = list.get(Utility.getRandomInt(list.size() - 1));
		scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");

		return category;

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

		String fName = "";
		String lName = "";

		if (pe_rt_code.equals("jud")) {

			lName = SystemPropertySetup.getVariable(Variables.JUD, userInputData);
			fName = SystemPropertySetup.getVariable(Variables.JUD_FIRST_NAME, userInputData);

		} else if (pe_rt_code.equals("stf")) {
			lName = SystemPropertySetup.getVariable(Variables.STF, userInputData);
			fName = SystemPropertySetup.getVariable(Variables.STF_FIRST_NAME, userInputData);
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
		List<String> list = Utility.retrieveAllReferrals(element, " ", 0);

		/** This might change in 1.8 - AMB-3399 */
		assertFalse(
				"VERIFY IF THERE IS MORE THAN ONE REFERRAL IN THE SAME CATEGORY FOR A CASE, THE CASE IS DISPLAYED ONLY ONCE",
				Utility.hasDublicates(list));

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
		Page.performPageLoad(driver);
		return referral;

	}

	public static void getAppliedCase() {
		scrollDownIfNotDisplayed("//*[@name='Applied Referrals' or @name='Associated Cases']");
	}

	public String getRandomCase(Category cat) {

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

	public static List<String> getDocumentCategoryList(List<UserInputData> userInputData) {

		String caseNum = Document_StepDefinitions.regularCase;
		String cmr_cs_caseid = CommonPages.getCaseID(caseNum, userInputData);
		String cmr_cyv_code = CommonPages
				.cmr_cyv_code(Document_StepDefinitions.judCategory, cmr_cs_caseid, userInputData).trim();
		String cmr_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);

		List<String> uiDocCategories = new ArrayList<>();
		if (appliedRefs.size() > 0) {
			panel = "Applied";
		} else {
			panel = "Actions";
		}

		for (int i = 0; i < getDocCategoryLocator(panel).size(); i++) {
			uiDocCategories.add(getDocCategoryLocator(panel).get(i).getText());
		}

		List<String> dbDoCategories = executeQuery(Actions.replace(DOCUMENT_CATEGORIES, "CMR_CYV_CODE", cmr_cyv_code,
				"CMR_JU_PE_ID", cmr_ju_pe_id, "CMR_CS_CASEID", cmr_cs_caseid), userInputData);

		assertEquals(" DOCUMENT CATEGORIES ARE NOT SORTED ON THE REFERRAL DETAIL PAGE ", dbDoCategories,
				uiDocCategories);

		return dbDoCategories;

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

	public enum Category {
		Referral_Category, Referral, Panel, judgeRegularCase, targetCase, appliedCase, CaseOnCalendar, EnBanc
	}

}
