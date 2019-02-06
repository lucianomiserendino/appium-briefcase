package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CMR_CCR_ID;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.expandPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.findElementAndScrollDown;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.splitBy;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class CommonPages extends AppiumPageFactory {

	@WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "PendingTasksList")
	public MobileElement PendingTasksList;

	@WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "ReferralsList")
	public static MobileElement ReferralsList;

	@WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "SessionGroups")
	public MobileElement SessionGroups;

	@WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "DocumentList")
	public static MobileElement DocumentList;

	@WithTimeout(time = 20, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Categories")
	public static MobileElement Categories;

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
		selectReferral(categories, Categories);
		selectReferral(caseNumber, list);
	}

	public static void selectReferral(String category, MobileElement element) {
		performPageLoad(driver);
		findElementAndScrollDown(Locator.XPATH, containsElement(category), element);
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

	public static void selectAction(DBType dbType, String panel, String el_id) {

		performPageLoad(driver);
		findElementAndScrollDown(Locator.XPATH, containsElement(panel), DocumentList);
		try {
			replace(panel);
			getPanel(Panel.valueOf(panel));

		} catch (AssertionError e) {
			e.getMessage();
		} finally {
			getActionName(getAllColumns(dbType, getID(ACTION_NAME, el_id)));

		}
	}

	public static void getActionName(String element) {
		findElementAndScrollDown(Locator.XPATH,
				"//XCUIElementTypeTable[@name='DocumentList']/XCUIElementTypeCell//*[contains(@name, '" + element
						+ "')]",
				DocumentList);
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
