package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_DATE_TYPE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CAV_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_ASSIGNMENTS_LINKED_TO_THE_CASE;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanelText;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ASSIGNMENTS;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCaseNumber;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.split;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_StaffAssignments {

	@iOSFindBy(xpath = "//*[contains(@name, 'Assignments')]")
	public static MobileElement assignments;

	public iOS_StaffAssignments() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void selectCase(String category, String caseNum) {
		selectCaseNumber(category, caseNum);
	}

	public void verifyAssignmentIsDisplayed(String assignmentOnReferral) {
		performPageLoad();
		assertTrue(isDisplayed(assignments));
		assertTrue(split(assignments.getText(), " ", 1).equals(assignmentOnReferral));

	}

	public void getAssignmentLinkedtoCase(String assignment, DBType dbtype, String cmr_cs_caseid, String cha_ju_pe_id,
			String chd_cha_id) {

		try {
			if (isDisplayed(assignments))
				;
			getPanel(ASSIGNMENTS);

			compareDBwithUI(getStaffAssigments(assignment), dbtype,
					getText(getID(STAFF_ASSIGNMENTS_LINKED_TO_THE_CASE, cha_ju_pe_id), cmr_cs_caseid));

			compareDBwithUI(getAssignmentType(assignment), dbtype,
					getText(getID(CAV_DESCRIPTION, cha_ju_pe_id), cmr_cs_caseid));

			compareDBwithUI(getAssignmentDateType(ASSIGNMENTS, assignment), dbtype,
					getID(ASSIGNMENT_DATE_TYPE, chd_cha_id));

			compareDBwithUI(getAssignmentDate(ASSIGNMENTS, assignment), dbtype, getID(ASSIGNMENT_DATE, chd_cha_id));

			getPanel(ASSIGNMENTS);

		} catch (AssertionError e) {
			e.printStackTrace();

		}

	}

	public static void compareDBwithUI(String assignment, DBType dbtype, String query) {
		List<String> uiCaseAssignment = new ArrayList<>();
		uiCaseAssignment.add(assignment);
		List<String> dbCaseAssignment = executeQuery(dbtype, query);
		assertTrue("RECORD COUNT MISMATCH ", dbCaseAssignment.containsAll(uiCaseAssignment));
	}

	public static String getAssignmentDateType(Actions action, String assignment) {
		MobileElement dateType = getAssignment(assignment);

		if (dateType.isDisplayed()) {
			return getAssignmentDateType(assignment);

		} else {
			getPanel(action);
			return getAssignmentDateType(assignment);
		}
	}

	public static String getAssignmentDate(Actions action, String assignment) {
		MobileElement date = getAssignment(assignment);

		if (date.isDisplayed()) {
			return getAssignedDate(assignment);

		} else {
			getPanel(action);
			return getAssignedDate(assignment);
		}
	}

	public String getStaffAssigments(String assignments) {
		return split(getPanelText(ASSIGNMENTS, assignments), " ", 0);

	}

	public String getAssignmentType(String assignments) {
		return getPanelText(ASSIGNMENTS, assignments).split(",")[1].trim();

	}

	public static MobileElement getAssignment(String assignment) {
		return findElement(By.xpath(locateElement(assignment) + "/following-sibling::XCUIElementTypeStaticText[1]"));

	}

	public static String getAssignmentDateType(String assignmentDateType) {
		return getAssignment(assignmentDateType).getText()
				.substring(0, getAssignment(assignmentDateType).getText().length() - 9).trim();
	}

	public static String getAssignedDate(String assignmentDate) {
		return changeDateFormat(getAssignment(assignmentDate).getText()
				.substring(getAssignment(assignmentDate).getText().length() - 9).trim(), "yyyy-MM-dd");
	}

}
