package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNED_DATES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_DATE_TYPE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CAV_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_ASSIGNMENTS_LINKED_TO_THE_CASE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL_NAME;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.ASSIGNMENT_ASSIGNED_NOTE;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.ASSIGNMENT_ASSIGNED_NOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.ASSIGNMENT_ASSIGNED_NOTE_TEXT;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.ASSIGNMENT_NOTE;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.ASSIGNMENT_NOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.ASSIGNMENT_NOTE_TEXT;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanelText;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ASSIGNMENTS;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.retrieveDates;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.split;
import static java.util.Collections.reverse;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_AssignmentsPage extends AppiumPageFactory {

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='Assignments']/XCUIElementTypeCell[5]/XCUIElementTypeStaticText")
	public static MobileElement assignmentNoteDate;

	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='Assignments']/XCUIElementTypeCell[6]/XCUIElementTypeStaticText")
	public static MobileElement assignmentAssignedNoteDate;

	@iOSFindBy(accessibility = "Assignment Note")
	public static MobileElement assignmentNote;

	@iOSFindBy(accessibility = "Assignment Assigned Note")
	public static MobileElement assignmentAssignedNote;

	@iOSFindBy(xpath = "//XCUIElementTypeOther[@name='Private Note - Assignment Note']/XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
	public static MobileElement assignmentNoteText;

	@iOSFindBy(xpath = "//XCUIElementTypeOther[@name='Private Note - Assignment Assigned Note']/XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
	public static MobileElement assignmentAssignedNoteText;

	@WithTimeout(time = 5, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Assignments')]")
	public static MobileElement assignments;

	@iOSFindBy(xpath = "//*[contains(@name, 'Assigned')]")
	public static List<MobileElement> assignedDates;

	public void verifyAssignmentIsDisplayed(String assignmentOnReferral) {
		performPageLoad();
		assertTrue(isDisplayed(assignments));
		assertTrue(split(assignments.getText(), " ", 1).equals(assignmentOnReferral));

	}

	public void selectAssignment(String assignment) {
		clickOnPanel(Actions.ASSIGNMENTS, assignment);
	}

	public void verifyAssignmentNotes(String assignmentNotes) {
		assertTrue(elementIsDisplayed(assignmentNotes) == true);
	}

	public void verifyAssignmentDates() {
		assertAssignmentNotes(" THE DATE OF THE ASSIGNMENT NOTE IS NOT DISPLAYED ", assignmentNoteDate,
				ASSIGNMENT_NOTE_DATE, assignmentAssignedNoteDate, ASSIGNMENT_ASSIGNED_NOTE_DATE);
	}

	public void verifyNoteDescription() {

		assertAssignmentNotes("NOTE DESCRIPTION IS NOT DISPLYED", assignmentNote, ASSIGNMENT_NOTE,
				assignmentAssignedNote, ASSIGNMENT_ASSIGNED_NOTE);
	}

	public void verifyText() {
		assertAssignmentNotes("TEXT IS NOT DISPLYED", assignmentNoteText, ASSIGNMENT_NOTE_TEXT,
				assignmentAssignedNoteText, ASSIGNMENT_ASSIGNED_NOTE_TEXT);

	}

	public void getAssignmentLinkedtoTheReferral(String assignmentForKyle, String assignmentForEssley) {

		List<String> dbReferralAssignments = executeQuery(DBType.CMKA, STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL_NAME);
		try {
			if (isDisplayed(assignments))
				;
			getPanel(ASSIGNMENTS);

			assertTrue("STAFF ASSIGNMENTS LINKED TO THE REFERRAL ARE NOT DISPLAYED",
					dbReferralAssignments.containsAll(listOfAssignments(assignmentForKyle, assignmentForEssley)));

			List<String> dbCaseAssignments = executeQuery(DBType.CMKA, ASSIGNED_DATES);
			reverse(dbCaseAssignments);

			assertEquals(" ASSIGNED DATES MISMATCH ", dbCaseAssignments,
					retrieveDates(assignedDates, " ", 1, "yyyy-MM-dd"));

			getPanel(ASSIGNMENTS);

		} catch (Exception e) {
			e.getMessage();

		}

	}

	public List<String> listOfAssignments(String assignmentForKyle, String assignmentForEssley) {
		List<String> uiReferralAssignments = new ArrayList<>();
		uiReferralAssignments.add(getStaffAssigments(assignmentForKyle));
		uiReferralAssignments.add(getStaffAssigments(assignmentForEssley));
		sort(uiReferralAssignments);
		return uiReferralAssignments;
	}

	public String getStaffAssigments(String assignments) {
		return split(getPanelText(ASSIGNMENTS, assignments), " ", 0);

	}

	public String getAssignmentType(String assignments) {
		return getPanelText(ASSIGNMENTS, assignments).split(",")[1].trim();

	}

	public static void assertAssignmentNotes(String message, MobileElement el1, String assign1, MobileElement el2,
			String assign2) {

		try {
			assertEquals(getText(el1), assign1);
			assertEquals(getText(el2), assign2);
		} catch (Exception e) {
			e.printStackTrace();
		}

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

	public static MobileElement getAssignment(String assignment) {
		return findElement(By.xpath(locateElement(assignment) + "/following-sibling::XCUIElementTypeStaticText[1]"));

	}

	public static String getAssignmentDateType(String assignmentDateType) {
		return getAssignment(assignmentDateType).getText()
				.substring(0, getAssignment(assignmentDateType).getText().length() - 9).trim();
	}

	public static String getAssignedDate(String assignmentDate) {
		return changeDateFormat(getAssignment(assignmentDate).getText()
				.substring(getAssignment(assignmentDate).getText().length() - 9).trim(), "MM/dd/yyyy", "yyyy-MM-dd");
	}

}
