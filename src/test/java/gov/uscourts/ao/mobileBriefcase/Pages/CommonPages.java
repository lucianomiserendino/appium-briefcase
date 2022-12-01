package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.insertData;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CMR_CCR_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SET_SITE_TABLE_VARIABLE_VALUE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SITE_TABLE_VARIABLE_VALUE;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.splitBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import cucumber.api.DataTable;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup.Variables;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CommonPages extends Base {
	public CommonPages() {

		// initElements(new AppiumFieldDecorator(driver), this);

		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);

	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"JENIE Single Sign On\"]/XCUIElementTypeOther[5]/XCUIElementTypeTextField")
	public static WebElement userName;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"JENIE Single Sign On\"]/XCUIElementTypeOther[6]/XCUIElementTypeSecureTextField")
	public static WebElement password;

	@iOSXCUITFindBy(id = "SIGN ON")
	public static WebElement submButton;
	static String okButton = "OK";
	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'NavigationRenderer')]/XCUIElementTypeButton[2]")
	public static WebElement settingsIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Logout of Briefcase']")
	public static WebElement logout;

	@iOSXCUITFindBy(accessibility = "PendingTasksList")
	public WebElement PendingTasksList;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "ReferralsList")
	public WebElement ReferralsList;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "SessionGroups")
	public WebElement SessionGroups;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "DocumentList")
	public WebElement DocumentList;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='GroupIcon']")
	public static List<WebElement> GroupIcon;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Judge:')]")
	public static WebElement judge;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public List<WebElement> Categories;

	public void getCategory(Category category, String caseNumber) {

		String categories = "";
		WebElement list = null;

		switch (category) {
		case PENDING:
			categories = "Pending";
			list = PendingTasksList;
			break;

		case PETITION:
			categories = "Petitions for Rehearing";
			list = ReferralsList;
			break;

		case CASES_ON:
			categories = "Cases on Calendar";
			list = SessionGroups;
			break;

		case MOTION:
			categories = "Motion";
			list = ReferralsList;
			break;

		case SCREENING:
			categories = "Screening";
			list = ReferralsList;
			break;

		case REFERENCE:
			categories = "Reference Documents";
			list = DocumentList;
			break;

		case TEST_AUTOMATION:
			categories = "Test Automation";
			list = ReferralsList;
			break;

		case APPLICATION_FOR_COA:
			categories = "Application";
			list = ReferralsList;

		case JURISDICTIONAL:
			categories = "Jurisdictional";
			list = ReferralsList;
			break;

		default:
			break;
		}

		selectReferralCategory(categories);
		selectReferral(containsElement(caseNumber));
	}

	public static void selectReferral(String category) {
		scrollDownIfNotDisplayed(category);

	}

	public static void selectReferralCategory(String category) {
		selectReferral("//XCUIElementTypeOther[@name='Categories']" + containsElement(category));
	}

	public static String getDataTable(DataTable data, int index1, int index2) {
		List<List<String>> table = data.raw();
		return table.get(index1).get(index2);
	}

	public void getCategoryWithCase(List<UserInputData> userInputData) {
		String category = SystemPropertySetup.getVariable(Variables.REF_CATEGORY, userInputData);
		String caseNumber = SystemPropertySetup.getVariable(Variables.CASE_NUMBER, userInputData);
		performPageLoad(driver);
		getCategory(Category.valueOf(category), caseNumber);
		performPageLoad(driver);
	}

	public void getCategoryAndCase(String category, String caseNumber) {
		performPageLoad(driver);
		getCategory(Category.valueOf(category), caseNumber);
		performPageLoad(driver);
	}

	public static void getPanel(Panel panel) {

		String panels = "";
		switch (panel) {

		case Assignments:
			panels += "Assignments";
			break;

		case Actions:
			panels += "Actions";
			break;

		case Vote_Information:
			panels += "Vote Information";
			break;

		case Applied_Referrals:
			panels += "Applied Referrals";
			break;

		case Briefs:
			panels += "Briefs";
			break;

		case Proposed_Orders:
			panels += "Proposed Orders";
			break;

		default:
			break;
		}
		getGroupIcons();
		scrollDownIfNotDisplayed(containsElement(panels));
	}

	public static void selectAction(String panel, String el_id, List<UserInputData> userInputData) {
		getGroupIcons();
		getPanel(Panel.valueOf(panel));
		String actionName1 = "";
		String actionName2 = getAllColumns(getID(ACTION_NAME, el_id), userInputData);
		if (actionName2.contains("'")) {
			actionName1 += actionName2.split("'")[0];
			getActionName(actionName1);
		} else {
			getActionName(actionName2);

		}
	}

	public static void selectAction(String panel, List<UserInputData> userInputData) {
		getGroupIcons();
		getPanel(Panel.valueOf(panel));
		// getActionName("mbr multiple DMI");
		getActionName("Auto Test");

	}

	public static void getActionName(String element) {
		scrollDownIfNotDisplayed(
				"//XCUIElementTypeOther[@name='DocumentList']//XCUIElementTypeStaticText[contains(@name, '" + element
						+ "')]");
		performPageLoad(driver);
		assertTrue("VERIFY THE NAME OF THE ACTION DISPLAYS IN THE DARK BLUE BANNER: ",
				Actions.isDisplayed(Locator.XPATH, containsElement(element)));
	}

	public static String getCMRID(DBType dbType, String id, String caseNum, String peId, String cmr_cyv_code) {
		String caseYear = splitBy(caseNum, 0);
		String caseNumber = splitBy(caseNum, 1);

		return getAllColumns(dbType,
				replace(replace(CMR_CCR_ID, "CS_YEAR", caseYear, "CS_NUMBER", caseNumber, "CMR_JU_PE_ID", peId), "ID",
						id, "CMR_CYV_CODE", cmr_cyv_code));
	}

	public static String getCMRID(String id, String caseNum, String peId, String cmr_cyv_code,
			List<UserInputData> userInputData) {
		String caseYear = splitBy(caseNum, 0);
		String caseNumber = splitBy(caseNum, 1);

		return getAllColumns(
				replace(replace(CMR_CCR_ID, "CS_YEAR", caseYear, "CS_NUMBER", caseNumber, "CMR_JU_PE_ID", peId), "ID",
						id, "CMR_CYV_CODE", cmr_cyv_code),
				userInputData);
	}

	public static String getCMRID(List<UserInputData> userInputData) {

		String cha_ju_pe_id = DocumentPage.get_pe_id( userInputData) ;

		String caseId = CommonPages.getCaseID(userInputData);

		return getAllColumns(replace(Queries.CMR_ID, "CMR_CS_CASEID", caseId, "CMR_JU_PE_ID", cha_ju_pe_id),
				userInputData);
	}

	public static String getCMRID(String caseNum, List<UserInputData> userInputData) {

		String cha_ju_pe_id = DocumentPage.get_pe_id( userInputData) ;

		String caseId = CommonPages.getCaseID(caseNum, userInputData);

		return getAllColumns(replace(Queries.CMR_ID, "CMR_CS_CASEID", caseId, "CMR_JU_PE_ID", cha_ju_pe_id),
				userInputData);
	}

	public static String getCCRID(String caseNum, List<UserInputData> userInputData) {

		String cha_ju_pe_id = DocumentPage.get_pe_id( userInputData) ;

		// String caseId = CommonPages.getCaseID(userInputData);
		String caseId = CommonPages.getCaseID(caseNum, userInputData);
		return getAllColumns(replace(Queries.CCR_ID, "CMR_CS_CASEID", caseId, "CMR_JU_PE_ID", cha_ju_pe_id),
				userInputData);
	}

	public static void verifyElementIsDisplayed(String element) {
		assertTrue(" PLEASE ENSURE THAT " + element.toUpperCase() + " IS DISPLAYED ",
				isDisplayed(containsElement(element)) == true);
	}

	public static String getCaseID(WebElement uiCaseNumber, List<UserInputData> table) {
		return getAllColumns(replace(CASE_ID, "CS_YEAR", getCase(Case.CASE_YEAR, uiCaseNumber.getText()), "CS_NUMBER",
				getCase(Case.CASE_NUMBER, uiCaseNumber.getText())), table);
	}

	public static String getCaseID(List<UserInputData> table) {
		String caseNumber = SystemPropertySetup.getVariable(Variables.CASE_NUMBER, table);

		return getAllColumns(replace(CASE_ID, "CS_YEAR", getCase(Case.CASE_YEAR, caseNumber), "CS_NUMBER",
				getCase(Case.CASE_NUMBER, caseNumber)), table);
	}

	public static String getCaseID(String caseNumber, List<UserInputData> table) {

		return getAllColumns(replace(CASE_ID, "CS_YEAR", getCase(Case.CASE_YEAR, caseNumber), "CS_NUMBER",
				getCase(Case.CASE_NUMBER, caseNumber)), table);
	}

	public static String cmr_cyv_code(String category, String caseID, List<UserInputData> userInputData) {

		String cha_ju_pe_id = DocumentPage.get_pe_id(userInputData);

		return getAllColumns(replace(Queries.CMR_CYV_CODE, "CMR_JU_PE_ID", cha_ju_pe_id, "CYV_CATEGORY",
				category.trim(), "CMR_CS_CASEID", caseID), userInputData);
	}

	public static String getCaseNumber(String caseNumber, int index) {
		return caseNumber.split(" ")[0].split("-")[index];

	}

	public static String getSiValue(String value, List<UserInputData> pacerInputData) {
		return getAllColumns(getID(SITE_TABLE_VARIABLE_VALUE, value), pacerInputData);
	}

	public static String getSiValue(String dbType, String value) {
		return getAllColumns(valueOf(dbType), getID(SITE_TABLE_VARIABLE_VALUE, value));
	}

	/**
	 * This method changes the value of the site table variable
	 * 
	 */

	public static void setValue(String si_value, String SI_CODE, List<UserInputData> userInputData) {

		insertData(replace(SET_SITE_TABLE_VARIABLE_VALUE, "SI_VALUE", si_value, "SI_CODE", SI_CODE), userInputData);
		String value = getAllColumns(replace(SITE_TABLE_VARIABLE_VALUE, "SI_CODE", SI_CODE), userInputData);
		assertEquals(si_value, value);
	}

	public static String getCase(Case caseN, String uiCaseNumber) {
		int index = 0;
		switch (caseN) {
		case CASE_YEAR:
			index += 0;
			break;
		case CASE_NUMBER:
			index += 1;
			break;
		default:
			break;
		}
		return getCaseNumber(uiCaseNumber, index);

	}

	public static void getGroupIcons() {
		List<WebElement> icons = GroupIcon;

		for (int i = 0; i < icons.size(); i++) {

			while (icons.get(i).getAttribute("value").equals("▽")) {
				icons.get(i).click();
			}
		}

	}

	public static void sendCredentials(String Username, String Password) {
		Page.sleep(10000);
		Actions.findElementBy(Locator.NAME, "usernameEntered").sendKeys(Username);
		Actions.findElementBy(Locator.NAME, "password").sendKeys(Password);
		Actions.findElementBy(Locator.NAME, "SUBMIT2").click();
		Page.sleep(20000);
	}

	public static void updateSi_value(String si_code, String si_value) {
		List<WebElement> el = Actions.findElements(By.xpath("//select[@name='table']/option"));
		for (int i = 0; i < el.size(); i++) {
			if (el.get(i).getText().equals("site")) {
				el.get(i).click();

			}
		}
		Actions.findElementBy(Locator.XPATH, "//input[@name='submitButton']").click();
		Page.sleep(10000);
		Actions.findElementBy(Locator.XPATH, "//input[@name='si_code']").sendKeys(si_code);
		Actions.findElementBy(Locator.XPATH, "(//input[@name='searchButton'])[2]").click();
		Actions.findElementBy(Locator.XPATH, "//textarea[@name='si_value__________1']").clear();
		Actions.findElementBy(Locator.XPATH, "//textarea[@name='si_value__________1']").sendKeys(si_value);
		Actions.findElementBy(Locator.XPATH, "(//input[@name='updateAllB'])[2]").click();

	}

	public void deleteDocs() {
		tap(dashboard);
		tap(settingsIcon);
		tap(Locator.XPATH, "//XCUIElementTypeStaticText[@name='Delete all Briefcase Documents']");
		performPageLoad(driver);
		driver.navigate().back();
		tap(dashboard);
	}

	public static boolean ifPaneExists(String element) {
		boolean isDisplayed = false;

		List<WebElement> elems = findElements(By.xpath(Actions.containsElement(element)));
		try {
			if (elems.size() > 0)
				isDisplayed = true;
		} catch (WebDriverException e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public static void ifDownloaded(List<WebElement> el) {
		performPageLoad(driver);
		Boolean elementNotFound = true;
		while (elementNotFound) {
			if (!(el.size() == 0)) {

				elementNotFound = true;
				Utility.tapAndSwipe(Direction.UP);

			} else {
				elementNotFound = false;
				break;
			}

		}
	}

	public enum Case {
		CASE_YEAR, CASE_NUMBER
	}

	public enum Panel {
		Assignments, Vote_Information, Actions, Judgment_Filed, Petition_Filed, Briefs, Applied_Referrals,
		Proposed_Orders
	}

	public enum Category {
		PENDING, PETITION, CASES_ON, MOTION, SCREENING, REFERENCE, TEST_AUTOMATION, APPLICATION_FOR_COA, JURISDICTIONAL
	}

}
