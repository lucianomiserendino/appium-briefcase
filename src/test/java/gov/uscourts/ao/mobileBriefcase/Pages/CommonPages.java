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

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup.Variables;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CommonPages extends AppiumPageFactory {
	// public CommonPages() {

	// initElements(new AppiumFieldDecorator(driver), this);
//
//		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
//
//	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"JENIE Single Sign On\"]/XCUIElementTypeOther[5]/XCUIElementTypeTextField")
	public static WebElement userName;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"JENIE Single Sign On\"]/XCUIElementTypeOther[6]/XCUIElementTypeSecureTextField")
	public static WebElement password;

	@iOSXCUITFindBy(id = "SIGN ON")
	public static WebElement submButton;

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

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Progress halted' or @name='In progress']")
	public static List<WebElement> activityIndicator;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;

	static String okButton = "OK";

	static String siCode = "";

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
		collapseBtn.click();
		scrollDownIfNotDisplayed(category);
		 collapseBtn.click();

	}

	public static void selectReferralCategory(String category) {
		selectReferral("//XCUIElementTypeOther[@name='Categories']" + containsElement(category));
	}
	public static String getDataTable(DataTable data, int index1, int index2) {
	    List<List<String>> table = data.asLists(); 
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
		int maxScrollAttempts = 4;

		for (int attempt = 0; attempt < maxScrollAttempts; attempt++) {
		    Utility.scrollPage("down");
		}
		try {
			String panelName;
			switch (panel) {
			case Assignments:
				panelName = "Assignments";
				break;
			case Actions:
				panelName = "Actions";
				break;
			case Vote_Information:
				panelName = "Vote Information";
				break;
			case Applied_Referrals:
				panelName = "Applied Referrals";
				break;
			case Briefs:
				panelName = "Briefs";
				break;
			case Proposed_Orders:
				panelName = "Proposed Orders";
				break;
			default:
				panelName = "";
				break;
			}

			if (!panelName.isEmpty()) {
				getGroupIcons(GroupIcons.Expand);
				performPageLoad(driver);
				scrollDownIfNotDisplayed(containsElement(panelName));
				System.out.println("------------------------------------------------------");
				System.out.println("Selected panel: " + panelName);
				System.out.println("------------------------------------------------------");
			}
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}

	public static void selectAction(String panel, String el_id, List<UserInputData> userInputData) {
		getGroupIcons(GroupIcons.Expand);
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


	public static void selectBriefcaseAction(String panel, String actionName) {

		getPanel(Panel.valueOf(panel));

		String actionName1 = "";

		if (actionName.contains("'")) {
			actionName1 += actionName.split("'")[0];
			getActionName(actionName1);
		} else {
			getActionName(actionName);

		}
	}

	public static void getActionName(String element) {
		System.out.println("Searching for action name: " + element);

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

		String cha_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);

		String caseId = DocumentPage.cs_caseid;
		String cmr_cyv_code = DocumentPage.cmr_cyv_code;
		return getAllColumns(replace(Queries.CMR_ID, "CMR_CS_CASEID", caseId, "CMR_JU_PE_ID", cha_ju_pe_id,"CMR_CYV_CODE",cmr_cyv_code),
				userInputData);
	}

//	public static String getCMRID(String caseNum, List<UserInputData> userInputData) {
//
//		String cha_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);
//
//		String caseId = CommonPages.getCaseID(caseNum, userInputData);
//
//		String cmr_cyv_code = DocumentPage.cmr_cyv_code;
//		
//		return getAllColumns(replace(Queries.CMR_ID, "CMR_CS_CASEID", caseId, "CMR_JU_PE_ID", cha_ju_pe_id),
//				userInputData);
//	}

	public static String getCCRID(List<UserInputData> userInputData) {
		String cha_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);
		String caseId = DocumentPage.cs_caseid;
		String cmr_cyv_code = DocumentPage.cmr_cyv_code;

		return getAllColumns(replace(Queries.CCR_ID, "CMR_CS_CASEID", caseId, "CMR_JU_PE_ID", cha_ju_pe_id,
				"CMR_CYV_CODE", cmr_cyv_code), userInputData);
	}

	public static List<String> findCCRID(List<UserInputData> userInputData,String caseId,String cmr_cyv_code) {
		String cha_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);

		return DBUtilities.executeQuery(replace(Queries.CCR_ID, "CMR_CS_CASEID", caseId, "CMR_JU_PE_ID", cha_ju_pe_id,
				"CMR_CYV_CODE", cmr_cyv_code), userInputData);
	}

	public static void verifyElementIsDisplayed(String element) {
		assertTrue(" PLEASE ENSURE THAT " + element.toUpperCase() + " IS DISPLAYED ",
				isDisplayed(containsElement(element)) == true);
	}

	public static String getCaseID(String caseNumber, List<UserInputData> table) {

		return getAllColumns(replace(CASE_ID, "CS_YEAR", getCase(Case.CASE_YEAR, caseNumber), "CS_NUMBER",
				getCase(Case.CASE_NUMBER, caseNumber)), table);
	}

	public static String cmr_cyv_code(String category, String caseID, List<UserInputData> userInputData) {
      String query="";
		String cha_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);
		
		if (category.equals("Cases on Calendar")||category.equals("Oral Argument")){
			query=Queries.ORAL_ARG_CMR_CYV_CODE;
		}else {
			query=Queries.CMR_CYV_CODE;
		}

		return getAllColumns(replace(query, "CMR_JU_PE_ID", cha_ju_pe_id, "CYV_CATEGORY",
				category.trim(), "CMR_CS_CASEID", caseID), userInputData);
	}

	public static String getCaseNumber(String caseNumber, int index) {
		return caseNumber.split(" ")[0].split("-")[index];

	}

	public static String getSiValue(SiteTableVariable var, List<UserInputData> pacerInputData) {
		String br = "briefcase";
		String value = "";
		switch (var) {
		case targetOnly:
			value = "TargetOnly";
			break;
		case internalNote:
			value = "InternalNote";
			break;
		case oralArgsView:
			value = "OralArgsView";
			break;
		case useCourtSession:
			value = "UseCourtSession";
			break;
		case ctAdminDkt:
			value = "CtAdminDkt";
			break;
		case displayTools:
			value = "DisplayTools";
			break;
		case AppLinkRoot:
			value = "AppLinkRoot";
			break;
		default:
			break;
		}

		return siCode = getAllColumns(getID(SITE_TABLE_VARIABLE_VALUE, br + value), pacerInputData);
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

	public static int getGroupIcons(GroupIcons icon) {
		ifDownloaded(activityIndicator);

		String grIcon;
		switch (icon) {
			case Collapse:
				grIcon = "▷";
				break;
			case Expand:
				grIcon = "▽";
				break;
			default:
				return 0;
		}

		int clickCount = 0;
		int noChangeCounter = 0;

		while (noChangeCounter < 3) {
			boolean iconFound = false;

			List<WebElement> icons = Actions.findElements(By.xpath("//*[contains(@value, '" + grIcon + "')]"));

			for (WebElement element : icons) {
				if (element.isDisplayed()) {
					try {
						element.click();
						clickCount++;
						iconFound = true;
						Thread.sleep(500);
						break;
					} catch (Exception e) {
						System.err.println("Click failed: " + e.getMessage());
					}
				}
			}

			if (!iconFound) {
				noChangeCounter++;
			} else {
				noChangeCounter = 0;
			}
		}

		return clickCount;
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
				Utility.scrollPage("down");

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

	public enum SiteTableVariable {

		targetOnly, internalNote, oralArgsView, useCourtSession, ctAdminDkt, displayTools, AppLinkRoot
	}

	public enum GroupIcons {
		Collapse, Expand

	}

}
