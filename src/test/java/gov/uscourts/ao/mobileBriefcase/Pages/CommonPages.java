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
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrollDownIfNotDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.splitBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class CommonPages extends AppiumPageFactory {

	static String okButton = "OK";
	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public static MobileElement dashboard;

	@iOSFindBy(xpath = "//*[contains(@name, 'NavigationRenderer')]/XCUIElementTypeButton[2]")
	public static MobileElement settingsIcon;

	@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name='Logout of Briefcase']")
	public static MobileElement logout;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "PendingTasksList")
	public MobileElement PendingTasksList;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "ReferralsList")
	public static MobileElement ReferralsList;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "SessionGroups")
	public MobileElement SessionGroups;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "DocumentList")
	public static MobileElement DocumentList;

	@iOSFindBy(accessibility = "GroupIcon")
	public static List<MobileElement> GroupIcon;

	@iOSFindBy(xpath = "//*[contains(@name, 'Judge:')]")
	public static MobileElement judge;
	@iOSFindBy(xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<MobileElement> Categories;

	public void getCategory(Category category, String caseNumber) {

		String categories = "";
		MobileElement list = null;

		switch (category) {
		case PENDING_TASKS:
			categories = "Pending Tasks";
			list = PendingTasksList;
			break;

		case PETITIONS_FOR_REHEARING:
			categories = "Petitions for Rehearing";
			list = ReferralsList;
			break;

		case CASES_ON_CALENDAR:
			categories = "Cases on Calendar";
			list = SessionGroups;
			break;

		case MOTIONS_PETITIONS:
			categories = "Motions/Petitions";
			list = ReferralsList;
			break;

		case SCREENING_PANELS:
			categories = "Screening Panels";
			list = ReferralsList;
			break;

		case REFERENCE_DOCUMENTS:
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

	public void getCategoryWithCase(String category, String caseNumber) {
		performPageLoad(driver);
		getCategory(Category.valueOf(category), caseNumber);
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

	public static void selectAction(DBType dbType, String panel, String el_id) {
		getGroupIcons();
		getPanel(Panel.valueOf(panel));
		String actionName1 = "";
		String actionName2 = getAllColumns(dbType, getID(ACTION_NAME, el_id));
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
		// performPageLoad(driver);
		// assertTrue("VERIFY THE NAME OF THE ACTION DISPLAYS IN THE DARK BLUE BANNER",
		// isDisplayed(Locator.XPATH, containsElement(element)));
	}

	public static String getCMRID(DBType dbType, String id, String caseNum, String peId, String cmr_cyv_code) {
		String caseYear = splitBy(caseNum, 0);
		String caseNumber = splitBy(caseNum, 1);

		return getAllColumns(dbType,
				replace(replace(CMR_CCR_ID, "CS_YEAR", caseYear, "CS_NUMBER", caseNumber, "CMR_JU_PE_ID", peId), "ID",
						id, "CMR_CYV_CODE", cmr_cyv_code));
	}

	public static void verifyElementIsDisplayed(String element) {
		assertTrue(" PLEASE ENSURE THAT " + element.toUpperCase() + " IS DISPLAYED ",
				isDisplayed(containsElement(element)) == true);
	}

	public static String getCaseID(DBType dbType, MobileElement uiCaseNumber) {
		return getAllColumns(dbType, replace(CASE_ID, "CS_YEAR", getCase(Case.CASE_YEAR, uiCaseNumber), "CS_NUMBER",
				getCase(Case.CASE_NUMBER, uiCaseNumber)));
	}

	public static String getCaseNumber(MobileElement caseNumber, int index) {
		return caseNumber.getText().split(" ")[0].split("-")[index];

	}

	public static String getSiValue(String dbType, String value) {
		return getAllColumns(valueOf(dbType), getID(SITE_TABLE_VARIABLE_VALUE, value));
	}

	/**
	 * This method changes the value of the site table variable
	 * 
	 */

	public static void setValue(DBType dbType, String si_value, String SI_CODE) {
		insertData(dbType, replace(SET_SITE_TABLE_VARIABLE_VALUE, "SI_VALUE", si_value, "SI_CODE", SI_CODE));
		String value = getAllColumns(dbType, replace(SITE_TABLE_VARIABLE_VALUE, "SI_CODE", SI_CODE));
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

	public void logout() {
		try {
			dashboard.click();
			settingsIcon.click();
			logout.click();
			if (findElements(By.xpath(containsElement("Press OK to logout"))).size() > 0) {
				contains(okButton).click();
			} else {
				Page.sleep(55000);
				logout.click();
				contains(okButton).click();
			}
		} catch (ElementNotVisibleException e) {
			e.getMessage();
		}
	}

	public void deleteDocs() {
		tap(dashboard);
		tap(settingsIcon);
		scrollDownIfNotDisplayed(containsElement("Delete all documents on device"));
		tap(dashboard);
	}

	public enum Case {
		CASE_YEAR, CASE_NUMBER
	}

	public enum Panel {
		Assignments, Vote_Information, Actions, Judgment_Filed, Petition_Filed, Briefs
	}

	public enum Category {
		PENDING_TASKS, PETITIONS_FOR_REHEARING, CASES_ON_CALENDAR, MOTIONS_PETITIONS, SCREENING_PANELS, REFERENCE_DOCUMENTS, TEST_AUTOMATION
	}

}
