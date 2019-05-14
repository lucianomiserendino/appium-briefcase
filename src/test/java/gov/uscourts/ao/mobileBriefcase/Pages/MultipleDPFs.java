package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.apply;

import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.assignmentDate;
import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.assignmentNameAndType;
import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.getAssignmentType;
import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.getAvailableStaffMembers;
import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.getChmAssign;
import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.getCreatedAssignmentNameAndAssignmentType;
import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.getCreatedAssignmentType;
import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.getElementNextToDropDown;
import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.*;
import static gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.staffMember;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.splitBy;

import java.util.concurrent.TimeUnit;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.chmAssign;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class MultipleDPFs extends AppiumPageFactory {

	//@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTextView[2]")
	public static MobileElement commentField;

	public void createAnAssignment(DBType dbType,String dpfName, String elId, String cha_ju_pe_id, String cmr_cyv_code,
			String cmr_cs_caseid) {

		/******************
		 * @AMB-1123 ****** STEP 1 --Select a staff member
		 */
		getElementNextToDropDown(3, "Staff Member", "Please Select");
		staffMember += getAvailableStaffMembers(dbType, dpfName, elId, cha_ju_pe_id);
		String staffFName = splitBy(staffMember, 0);
		String staffLName = splitBy(staffMember, 1);

		/** STEP 2 --Select an assignment */
		getElementNextToDropDown(5, "Assignment", "Please Select");
		getAssignmentType(dbType, dpfName, elId, cha_ju_pe_id, cmr_cs_caseid, cmr_cyv_code, staffFName, staffLName);

		/** STEP 3 --Select an Assigned Date */
		getElementNextToDropDown(7, "Assigned", "Select Date");
		// // selectADate(2);
		performPageLoad(driver);
		Utility.tapByCoordinate("assignedDateX", "assignedDateY");
		performPageLoad(driver);

		/** STEP 4 --Select Assignment Due Date */
		getElementNextToDropDown(9, "Assignment Due", "Select Date");
		// selectADate(1);
		performPageLoad(driver);
		Utility.tapByCoordinate("assignedDueDateX", "assignedDueDateY");
		performPageLoad(driver);

		contains(apply).click();
		/**
		 * STEP 5 --If the user clicks the "Apply" button, the popup will close and the
		 * new assignment will display on the chmAssign DPF screen
		 */
		assignmentNameAndType = getCreatedAssignmentNameAndAssignmentType(staffFName + " " + staffLName, assignment);
		assignmentDate = getCreatedAssignmentType(staffMember, assignment);

	}

	public void addANote() {
		sendKeys(commentField, "Test");
	}
}
