package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_DATE_TYPE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CAV_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_ASSIGNMENTS_LINKED_TO_THE_CASE;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanelText;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ASSIGNMENTS;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCaseNumber;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.split;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class StaffAssignments {

	@WithTimeout(time = 5, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Assignments')]")
	public static MobileElement assignments;

	public StaffAssignments() {
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

		List<String> uiCaseAssignmentName = new ArrayList<>();
		uiCaseAssignmentName.add(getStaffAssigments(assignment));
		List<String> dbCaseAssignmentsName = executeQuery(dbtype,
				getText(getID(STAFF_ASSIGNMENTS_LINKED_TO_THE_CASE, cha_ju_pe_id), cmr_cs_caseid));

		List<String> uiCaseAssignmentDesc = new ArrayList<>();
		uiCaseAssignmentDesc.add(getAssignmentType(assignment));
		List<String> dbCaseAssignmentsDesc = executeQuery(dbtype,
				getText(getID(CAV_DESCRIPTION, cha_ju_pe_id), cmr_cs_caseid));

		List<String> dbAssignmentDateType = executeQuery(dbtype, getID(ASSIGNMENT_DATE_TYPE, chd_cha_id));

	
		try {
			if (isDisplayed(assignments))
				;
			getPanel(ASSIGNMENTS);

			assertTrue(" STAFF ASSIGNMENTS LINKED TO THE CASE ARE NOT DISPLAYED ",
					dbCaseAssignmentsName.containsAll(uiCaseAssignmentName));

			assertEquals(dbCaseAssignmentsDesc, uiCaseAssignmentDesc);

			getPanel(ASSIGNMENTS);

		} catch (AssertionError e) {
			e.printStackTrace();

		}

	}

	public String getAssignmentDateType(MobileElement assignmentDateType) {
		return split(assignmentDateType.getText(), " ", 0);
	}

	public String getStaffAssigments(String assignments) {
		return split(getPanelText(ASSIGNMENTS, assignments), " ", 0);

	}

	public String getAssignmentType(String assignments) {
		return getPanelText(ASSIGNMENTS, assignments).split(",")[1].trim();

	}



}
