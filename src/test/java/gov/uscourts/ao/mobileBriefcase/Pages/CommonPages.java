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
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrollDownIfNotDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.splitBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.List;

import org.openqa.selenium.By;

import cucumber.api.DataTable;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CommonPages extends Base {
	public CommonPages() {
		
		initElements(new AppiumFieldDecorator(driver), this);
		
	}

	static String okButton = "OK";
	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public static MobileElement dashboard;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'NavigationRenderer')]/XCUIElementTypeButton[2]")
	public static MobileElement settingsIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Logout of Briefcase']")
	public static MobileElement logout;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "PendingTasksList")
	public MobileElement PendingTasksList;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "ReferralsList")
	public MobileElement ReferralsList;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "SessionGroups")
	public MobileElement SessionGroups;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "DocumentList")
	public MobileElement DocumentList;

	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<MobileElement> GroupIcon;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Judge:')]")
	public static MobileElement judge;
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public List<MobileElement> Categories;

	public void getCategory(Category category, String caseNumber) {

		String categories = "";
		MobileElement list = null;

		switch (category) {
		case PENDIN:
			categories = "Pending Tasks";
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

		default:
			break;
		}

		selectReferral("//XCUIElementTypeOther[@name='Categories']" + containsElement(categories));
		selectReferral(containsElement(caseNumber));
	}

	public static void selectReferral(String category) {
		scrollDownIfNotDisplayed(category);

	}

	public static String getDataTable(DataTable data, int index1, int index2) {
		List<List<String>> table = data.raw();
		return table.get(index1).get(index2);
	}

	public void getCategoryWithCase(String category, String caseNumber) {
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

		case Briefs:
			panels += "Briefs";
			break;

		default:
			break;
		}
		getGroupIcons();
		scrollDownIfNotDisplayed(containsElement(panels));
	}

	public static void selectAction(String panel, String el_id,List<UserInputData> userInputData) {
		getGroupIcons();
		getPanel(Panel.valueOf(panel));
		String actionName1 = "";
		String actionName2 = getAllColumns( getID(ACTION_NAME, el_id),userInputData);
		if (actionName2.contains("'")) {
			actionName1 += actionName2.split("'")[0];
			getActionName(actionName1);
		} else {
			getActionName(actionName2);

		}
	}

	public static void getActionName(String element) {
		scrollDownIfNotDisplayed(
				"//XCUIElementTypeOther[@name='DocumentList']//XCUIElementTypeStaticText[contains(@name, '" + element
						+ "')]");
		performPageLoad(driver);
		assertTrue("VERIFY THE NAME OF THE ACTION DISPLAYS IN THE DARK BLUE BANNER",
				isDisplayed(Locator.XPATH, containsElement(element)));
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

	public static void verifyElementIsDisplayed(String element) {
		assertTrue(" PLEASE ENSURE THAT " + element.toUpperCase() + " IS DISPLAYED ",
				isDisplayed(containsElement(element)) == true);
	}

	public static String getCaseID( MobileElement uiCaseNumber,List<UserInputData> table) {
		return getAllColumns( replace(CASE_ID, "CS_YEAR", getCase(Case.CASE_YEAR, uiCaseNumber), "CS_NUMBER",
				getCase(Case.CASE_NUMBER, uiCaseNumber)),table);
	}

	public static String getCaseNumber(MobileElement caseNumber, int index) {
		return caseNumber.getText().split(" ")[0].split("-")[index];

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

	public static void setValue(String si_value, String SI_CODE,List<UserInputData> userInputData) {
		//insertData(dbType, replace(SET_SITE_TABLE_VARIABLE_VALUE, "SI_VALUE", si_value, "SI_CODE", SI_CODE));
		insertData(replace(SET_SITE_TABLE_VARIABLE_VALUE, "SI_VALUE", si_value, "SI_CODE", SI_CODE), userInputData) ;
		String value = getAllColumns(replace(SITE_TABLE_VARIABLE_VALUE, "SI_CODE", SI_CODE),userInputData);
		System.out.println(si_value+"******************");
		System.out.println(value+"******************");

		assertEquals(si_value, value);
	}

	public static String getCase(Case caseN, MobileElement uiCaseNumber) {
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
		for (int i = 1; i < GroupIcon.size() + 1; i++) {
			String groupIcon = "(//XCUIElementTypeStaticText[@name='GroupIcon'])[";
			while (findElements(
					By.xpath(groupIcon + i + "]/following::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]"))
							.size() == 0) {
				tap(Locator.XPATH, groupIcon + i + "]");
			}
		}
	}

	public void deleteDocs() {
		tap(dashboard);
		tap(settingsIcon);
		tap(Locator.XPATH, "//XCUIElementTypeStaticText[@name='Delete all Briefcase Documents']");
		performPageLoad(driver);
		tap(dashboard);
	}

	public enum Case {
		CASE_YEAR, CASE_NUMBER
	}

	public enum Panel {
		Assignments, Vote_Information, Actions, Judgment_Filed, Petition_Filed, Briefs
	}

	public enum Category {
		PENDIN, PETITION, CASES_ON, MOTION, SCREENING, REFERENCE, TEST_AUTOMATION
	}

}
