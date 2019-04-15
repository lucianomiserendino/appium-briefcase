package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.insertData;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CMR_CCR_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SET_SITE_TABLE_VARIABLE_VALUE;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForVisibilityOfElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.expandPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.findElementAndScrollDown;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.splitBy;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class CommonPages extends AppiumPageFactory {

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

	// @WithTimeout(time = 20, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Categories")
	public static MobileElement Categories;

	@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name='▷']")
	public static List<MobileElement> right;

	@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name='▽']")
	public static List<MobileElement> down;

	@iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name='▽'])[1]")
	public static MobileElement viewed;

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
		selectReferral(categories);
		selectReferral(caseNumber);
	}

	public static void selectReferral(String category) {
		performPageLoad(driver);
		findElementAndScrollDown(Locator.XPATH, containsElement(category));
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
		expandPanel(panels);
	}

	public  void getCollapsiblePanel(DBType dbType, String refCategory) {
		List<String> category = executeQuery(dbType, refCategory);
		try {
			while (right.size() < category.size() && down.size() > 0) {

				// for (int i = 0; i < category.size()-1; i++) {
				waitForVisibilityOfElement(viewed, driver).click();
			}
			// }
		} catch (NoSuchElementException e) {
			assertTrue(right.size() == category.size());

		}
	}

	public static void selectAction(DBType dbType, String panel, String el_id) {

		performPageLoad(driver);
		hideCollapsiblePanels();
		findElementAndScrollDown(Locator.XPATH, containsElement(panel));

		try {
			replace(panel);
			// getPanel(Panel.valueOf(panel));

		} catch (AssertionError e) {
			e.getMessage();
		} finally {
			String actionName1 = "";
			String actionName2 = getAllColumns(dbType, getID(ACTION_NAME, el_id));
			if (actionName2.contains("'")) {
				actionName1 += actionName2.split("'")[0];
				getActionName(actionName1);
			} else {
				getActionName(actionName2);

			}

		}
	}

	public static void getActionName(String element) {
		findElementAndScrollDown(Locator.XPATH,
				"//XCUIElementTypeOther[@name='DocumentList']//XCUIElementTypeStaticText[contains(@name, '" + element
						+ "')]");
	}

	public static String getCMRID(DBType dbType, String caseNum, String peId, String cmr_cyv_code) {
		String caseYear = splitBy(caseNum, 0);
		String caseNumber = splitBy(caseNum, 1);
		return getAllColumns(dbType,
				replace(replace(CMR_CCR_ID, "CS_YEAR", caseYear, "CS_NUMBER", caseNumber, "CMR_JU_PE_ID", peId),
						"CMR_CYV_CODE", cmr_cyv_code));
	}

	public static void verifyElementIsDisplayed(String element) {
		assertTrue(" PLEASE ENSURE THAT " + element.toUpperCase() + " IS DISPLAYED ", contains(element).isDisplayed());
	}

	public static String getCaseID(DBType dbType, MobileElement uiCaseNumber) {
		return getAllColumns(dbType, replace(CASE_ID, "CS_YEAR", getCase(Case.CASE_YEAR, uiCaseNumber), "CS_NUMBER",
				getCase(Case.CASE_NUMBER, uiCaseNumber)));
	}

	public static String getCaseNumber(MobileElement caseNumber, int index) {
		return caseNumber.getText().split(" ")[0].split("-")[index];

	}

	/**
	 * This method changes the value of the site table variable
	 * 
	 */

	public static void setValue(DBType dbType, String si_value, String si_code) {
		insertData(dbType, replace(SET_SITE_TABLE_VARIABLE_VALUE, "SI_VALUE", si_value, "SI_CODE", si_code));
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

	public static void hideCollapsiblePanels() {

		try {
			while (down.size() > 0) {
				waitForVisibilityOfElement(viewed, driver).click();
			}
		} catch (NoSuchElementException e) {
			assertTrue(down.size() == 0);

		}
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
