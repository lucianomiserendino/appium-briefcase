package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNEES_CHA_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_DUE_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE_IS_SKIP;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CAV_CODE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CAV_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHAMBERS_ASSIGNMENT;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHAMBERS_ASSIGN_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHA_CAV_CODE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHC_DATE_END;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHD_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHM_ASSIGN_TO_CASE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_MEMBERS_FIRST_NAME;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getPanel;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectAction;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectReferral;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.verifyElementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.clickOnNumberInRange;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.clickOnRandomValue;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getParameter;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.splitBy;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class chmAssignDPFPage extends AppiumPageFactory {

	static String yesBTN = "Yes";
	static String okBTN = "OK";
	static String backBTN = "Back";
	static String comment = "Comment";
	static String apply = "Apply";
	static String cancel = "Cancel";
	static String submit = "Submit";
	static String close = "Close";
	static String staffMember = "";
	static String assignment = "";
	static String assignmentNameAndType = "";
	static String assignmentDate = "";

	@iOSFindBy(xpath = "//XCUIElementTypeOther[@name='OptionList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<MobileElement> optionList;

	public static void getElementNextToDropDown(int index, String element, String dropDowN) {
		tap(Locator.XPATH, getDropDown(index, element, dropDowN));
	}

	public static String getDropDown(int index, String listOfStaff, String dropDowName) {

		return "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["
				+ index
				+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, '"
				+ listOfStaff + "')]/following::XCUIElementTypeOther[2]/XCUIElementTypeButton[contains(@name, '"
				+ dropDowName + "')]";
	}

	public void createNewStaffAssignment(DBType dbType, String dpfName, String elId, String cha_ju_pe_id,
			String cmr_cyv_code, String cmr_cs_caseid, String caseNumber) {

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

		/** STEP 5 --Verify comment, apply and cancel display */
		verifyElementIsDisplayed(comment);
		verifyElementIsDisplayed(apply);
		verifyElementIsDisplayed(cancel);

		/**
		 * // * STEP 6 --If the user clicks the "Apply" button, the popup will close and
		 * // the // * new assignment will display on the chmAssign DPF screen //
		 */
		assignment += newUIAssignment(dbType, cha_ju_pe_id, elId, staffFName, staffLName, caseNumber);

		/******************
		 * @AMB-1137
		 */
		newDBAssignment(dbType, cha_ju_pe_id, elId, staffFName, staffLName, assignment);

		/******************
		 * @AMB-1170, @AMB-1173
		 */
		modifyExistingStaffAssignment(assignmentNameAndType, assignmentDate, dbType, dpfName, elId, cha_ju_pe_id,
				cmr_cs_caseid, cmr_cyv_code, staffFName, staffLName);

	}

	/**
	 * When tapping the drop-down a popup displays a list of staff based on the
	 * screen parameter in the DPF: -----Screen param = ja - only list JAs in the
	 * judge's chambers -----Screen param = lwclk - only list law clerks in the
	 * judge's chambers -----Screen param = lwclk:ja - list both JAs and law clerks
	 * in the judge's chambers -----Screen param = staff - list all staff members in
	 * the judge's chambers
	 */
	public static String getAvailableStaffMembers(DBType dbType, String dpfName, String elId, String peID) {

		String screenTypeParam = getParameter(getAllColumns(dbType, getID(MBR_NOTE, elId)), dpfName, 0);

		String screenParam = "";

		switch (screenTypeParam) {
		case "ja":
			screenParam += "='ja'";
			break;

		case "lwclk":
			screenParam += "='lwclk'";
			break;

		case "lwclk:ja":
			screenParam += "in ('ja','lwclk')";
			break;

		case "staff":
			screenParam += "not in ('aty')";
			break;

		default:
			break;
		}

		getListOfAvailableStaffMembers(dbType, STAFF_MEMBERS_FIRST_NAME, peID, screenParam, 0);
		performPageLoad(driver);
		return getChmAssign(chmAssign.STAFF_MEMBER);
	}

	/**
	 * gets a list of staff based on the screen parameter in the DPF, compares staff
	 * members that are displayed on the ui with db and selects one
	 */
	public static void getListOfAvailableStaffMembers(DBType dbtype, String staffMember, String peID,
			String screenTypeParam, int index) {

		List<String> dbStafMembers = executeQuery(dbtype, getText(getID(staffMember, peID), screenTypeParam));
		sort(dbStafMembers);
		try {
			List<String> uiStaffMembers = new ArrayList<>();

			List<MobileElement> allStaffMembers = optionList;
			for (MobileElement staffMembers : allStaffMembers) {
				uiStaffMembers.add(staffMembers.getText().split(" ")[index]);
				sort(uiStaffMembers);
			}
			assertEquals("********STAFF MEMBERS VALIDATION ERROR!!!********", dbStafMembers, uiStaffMembers);
			clickOnRandomValue(allStaffMembers);

		} catch (AssertionError e) {
			e.getMessage();
		}
	}

	/**
	 * Verify that when you tap the Please Select button next to the Assignment
	 * label, a pop-up displays with valid assignment types
	 */
	public static void getAssignmentType(DBType dbType, String dpfName, String elId, String cha_ju_pe_id,
			String cmr_cs_caseid, String cmr_cyv_code, String pr_first_name, String pr_last_name) {

		List<String> uiAssignmenType = new ArrayList<>();
		try {
			performPageLoad(driver);
			List<MobileElement> allAssignmenTypes = optionList;
			for (MobileElement type : allAssignmenTypes) {
				uiAssignmenType.add(type.getText().trim());
				sort(uiAssignmenType);
			}

			String assignmentType = getParameter(getAllColumns(dbType, getID(MBR_NOTE, elId)), dpfName, 1);
			if (assignmentType.equals("SKIP")) {
				/** If the assignment type parameter is set to SKIP, use this query */
				getValidAssignmentTypes(dbType, ASSIGNMENT_TYPE_IS_SKIP, uiAssignmenType, cha_ju_pe_id, cmr_cs_caseid,
						cmr_cyv_code, pr_first_name, pr_last_name);
			} else {
				/**
				 * If the assignment type parameter contains a colon delimited list, use this
				 * query
				 */
				getValidAssignmentTypes(dbType,
						getText(ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST,
								"'" + assignmentType.replaceAll(":", "','") + "'"),
						uiAssignmenType, cha_ju_pe_id, cmr_cs_caseid, cmr_cyv_code, pr_first_name, pr_last_name);
			}
			clickOnNumberInRange(allAssignmenTypes);

		} catch (Exception e) {
			e.getMessage();
		}
	}

	/** verify assignment types based on assignment types parameter */
	public static void getValidAssignmentTypes(DBType dbType, String query, List<String> uiAssignmenType,
			String cha_ju_pe_id, String cmr_cs_caseid, String cmr_cyv_code, String pr_first_name, String pr_last_name) {

		List<String> dbAssignmentType = executeQuery(dbType, query);
		sort(dbAssignmentType);
		try {
			/**
			 * this line checks if a staff member exists with that assignment type, if yes
			 * that assignment type won't be displayed after you tap the Please Select
			 * button next to the Assignment
			 */
			List<String> cavDescription = executeQuery(dbType,
					replace(replace(getID(CAV_DESCRIPTION, cha_ju_pe_id), "CMR_CS_CASEID", cmr_cs_caseid),
							"CMR_CYV_CODE", cmr_cyv_code, "PR_FIRST_NAME", pr_first_name, "PR_LAST_NAME",
							pr_last_name));
			for (int i = 0; i < cavDescription.size(); i++) {
				if (cavDescription.size() > 0 && dbAssignmentType.contains(cavDescription.get(i))) {
					dbAssignmentType.remove(cavDescription.get(i));
				}
			}
		} catch (NullPointerException e) {
			e.getMessage();
		} finally {
			try {
				assertEquals("*********ASSIGNMENT TYPE VALIDATION ERROR!!!*********", dbAssignmentType,
						uiAssignmenType);
			} catch (AssertionError e) {
				e.getMessage();
			}
		}
	}

	public static String newUIAssignment(DBType dbType, String peId, String elID, String staffMembersFName,
			String staffMembersLName, String caseNumber) {

		String assignment = getChmAssign(chmAssign.ASSIGNMENT);

		contains(apply).click();

		assignmentNameAndType = getCreatedAssignmentNameAndAssignmentType(staffMembersFName + " " + staffMembersLName,
				assignment);
		assignmentDate = getCreatedAssignmentType(staffMember, assignment);

		try {
			clickOn(submit, yesBTN, okBTN);
		} catch (WebDriverException e) {
			e.getMessage();
		}
		contains(backBTN).click();
		selectReferral(caseNumber);
		performPageLoad(driver);
		getPanel(Panel.Assignments);

		// assertTrue(getExistingAssignment(assignmentNameAndType,
		// assignmentDate).isDisplayed());

		assertTrue(Utility.findElementAndScroll("//*[contains(@name, '" + assignmentNameAndType
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + assignmentDate + "')]") == true);

		return assignment;

	}

	/** check the back-end updates when a new staff assignment is created */
	public static void newDBAssignment(DBType dbType, String peId, String elID, String staffMembersFName,
			String staffMembersLName, String assignment) {

		String peID = getAllColumns(dbType, getID("SELECT first 1 pe_id\n"
				+ "FROM group inner join member on gp_id = mb_gp_id_parent \n"
				+ "join personrole on pe_pr_prid = mb_ur_pr_prid \n" + "join person on pe_pr_prid = pr_prid \n"
				+ "join user on ur_pr_prid = pr_prid \n" + "where  pr_first_name='" + staffMembersFName
				+ "' and pr_last_name='" + staffMembersLName
				+ "' and   gp_id in (select gp_id from group inner join member on gp_id = mb_gp_id_parent join person on pr_prid = mb_ur_pr_prid \n"
				+ "join personrole on pe_pr_prid = pr_prid where pe_id = '?' and gp_name like '%Chambers%') and pe_date_end is null and pr_prid <> \n"
				+ "(select pr_prid from personrole join person on pe_pr_prid = pr_prid where pe_id = '?' and ur_date_disabled is null ) order by pe_date_created desc",
				peId));

		String dbAssignmentType = getAllColumns(dbType, "SELECT cav_code  FROM chm_assign_type_val WHERE cav_display='"
				+ assignment + "' and cav_chm_role in ('staff', 'all') and cav_date_end is null ORDER BY cav_display");

		String dbChambersAssignment = getAllColumns(dbType, getID(
				"select first 1 cha_id,cha_date_created from chambers_assignment where cha_ju_pe_id = ? and cha_assigner_ju_pe_id = ?  and "
						+ "cha_chm_pe_id = '" + peID + "' and cha_cav_code = '" + dbAssignmentType
						+ "' order by cha_date_created desc",
				peId));
		/***
		 * After creating a new assignment it will verify the following records are
		 * created in cmecf
		 */
		getCreatedRecords(dbType, dbChambersAssignment, getID(CHAMBERS_ASSIGNMENT, peId));
		getCreatedRecords(dbType, dbChambersAssignment, CHM_ASSIGN_TO_CASE);
		getCreatedRecords(dbType, dbChambersAssignment, CHAMBERS_ASSIGN_DATE);

	}

	public void modifyExistingStaffAssignment(String assgnNameAndType, String assgnDate, DBType dbType, String dpfName,
			String elId, String cha_ju_pe_id, String cmr_cs_caseid, String cmr_cyv_code, String pr_first_name,
			String pr_last_name) {

		/** After the new assignment is created it clicks on it */
		clickOnExistingAssignment(assgnNameAndType, assgnDate, dbType, elId);

		modifyExistingStaffAssignment(dbType, dpfName, cha_ju_pe_id, elId, cha_ju_pe_id, cmr_cs_caseid, cmr_cyv_code,
				pr_first_name, pr_last_name);
	}

	/**
	 * verify the new assignment is displayed on the chmassign dpf screen and modify
	 */
	public static void modifyExistingStaffAssignment(DBType dbType, String dpfName, String peID, String elId,
			String cha_ju_pe_id, String cmr_cs_caseid, String cmr_cyv_code, String pr_first_name, String pr_last_name) {

		String uiAssignedDate = getChmAssign(chmAssign.ASSIGNED_DATE);

		String uiAssignmentType = getChmAssign(chmAssign.ASSIGNMENT);

		String uiAssignmentDueDate = getChmAssign(chmAssign.ASSIGNMENT_DUE);
		//
		String dbAssignmentDueDate = changeDateFormat(
				getAllColumns(dbType,
						getID(ASSIGNMENT_DUE_DATE,
								getCreatedAssignment(dbType, ASSIGNEES_CHA_ID, peID, cmr_cs_caseid))),

				"yyyy-MM-dd", "M/d/yyyy");

		assertEquals("******PLEASE MAKE SURE ASSIGNMENT DUE DATE IS CORRECT*******", dbAssignmentDueDate,
				uiAssignmentDueDate);

		String cha_id = getCreatedAssignment(dbType, ASSIGNEES_CHA_ID, peID, cmr_cs_caseid);

		contains(uiAssignmentType).click();

		/**
		 * * @AMB-1170 Modifying existing assignment
		 */

		getAssignmentType(dbType, dpfName, elId, cha_ju_pe_id, cmr_cs_caseid, cmr_cyv_code, pr_first_name,
				pr_last_name);
		performPageLoad(driver);
		String modAssignmentType = getChmAssign(chmAssign.ASSIGNMENT);

		getElementNextToDropDown(7, "Assigned", uiAssignedDate);
		// selectADate(3);
		performPageLoad(driver);
		Utility.tapByCoordinate("modifiedAssignedDateX", "modifiedAssignedDateX");
		performPageLoad(driver);

		String modAssignedDate = getChmAssign(chmAssign.ASSIGNED_DATE);

		getElementNextToDropDown(9, "Assignment Due", dbAssignmentDueDate);
		// selectADate(3);
		// performPageLoad(driver);
		performPageLoad(driver);
		Utility.tapByCoordinate("modifiedAssignedDueDateX", "modifiedAssignedDueDateY");
		performPageLoad(driver);

		String modAssignmentDueDate = getChmAssign(chmAssign.ASSIGNMENT_DUE);

		contains(apply).click();

		try {

			MobileElement modifiedAssignedDate = getExistingAssignment(staffMember + ", " + modAssignmentType,
					"Assigned " + modAssignedDate);
			assertTrue(modifiedAssignedDate.isDisplayed());
			tap(modifiedAssignedDate);

		} catch (Exception e) {
			MobileElement modifiedAssignmentDueDate = getExistingAssignment(staffMember + ", " + modAssignmentType,
					"Assignment Due " + modAssignedDate);
			assertTrue(modifiedAssignmentDueDate.isDisplayed());
			tap(modifiedAssignmentDueDate);

		}

		/** Terminating existing assignment */
		getElementNextToDropDown(11, "Assignment Completed", "Select Date");
		selectADate(1);
		performPageLoad(driver);
		String modAssignmCompletedDate = getChmAssign(chmAssign.ASSIGNMENT_COMPLETED);

		contains("Apply").click();
		try {
			clickOn(submit, yesBTN, okBTN);
		} catch (WebDriverException e) {
			e.getMessage();
		}

		/*****
		 * @AMB-1173
		 * 
		 * Back-end modify assignment updates
		 */
		String uiAssignmenType = getAllColumns(dbType, getText(CAV_CODE, modAssignmentType));
		String dbAssignmenType = getAllColumns(dbType, getID(CHA_CAV_CODE, cha_id));
		assertEquals("******ASSIGNMENT TYPE ISN'T MODIFIED CORRECTLY*****", dbAssignmenType, uiAssignmenType);

		String dbAssignmentDue = changeDateFormat(getAllColumns(dbType, getID(CHD_DATE, cha_id)), "yyyy-MM-dd",
				"M/d/yyyy");

		assertEquals("*****ASSIGNMENT DUE DATE ERROR******", dbAssignmentDue, modAssignmentDueDate);

		String dbAssignmentCompletedDate = changeDateFormat(getAllColumns(dbType, getID(CHC_DATE_END, cha_id)),
				"yyyy-MM-dd", "M/d/yyyy");
		assertEquals("******ASSIGNMENT COMPLETED DATE ERROR*****", dbAssignmentCompletedDate, modAssignmCompletedDate);

	}

	public static void selectADate(int index) {
		performPageLoad(driver);
		List<MobileElement> date = driver.findElements(By.xpath(
				"//XCUIElementTypeApplication[@name='Briefcase [Test]']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther"));
		date.get(date.size() - index).click();

	}

	public static String getSelectedAssignment(String assignment, int index) {

		return "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["
				+ index
				+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, '"
				+ assignment + "')]/following::XCUIElementTypeOther[2]/XCUIElementTypeButton";
	}

	public static String getCreatedAssignmentNameAndAssignmentType(String staffMember, String assignmnetType) {
		return getText(Locator.XPATH, "//*[contains(@name, '" + staffMember + ", " + assignmnetType + "')]");

	}

	public static String getCreatedAssignmentType(String staffMember, String assignmnetType) {
		return getText(Locator.XPATH, "//*[contains(@name, '" + staffMember + ", " + assignmnetType
				+ "')]/following:: XCUIElementTypeOther[2]/XCUIElementTypeStaticText");

	}

	/** Verify the records are created in CM/ECF */
	public static void getCreatedRecords(DBType dbType, String expected, String actual) {
		String CMECF_TABLES = getAllColumns(dbType, actual);
		assertEquals("********PLEASE VERIFY THAT RECORDS IN CMECF ARE CREATED CORRECTLY!!!********", expected,
				CMECF_TABLES);

	}

	public static String getCreatedAssignment(DBType dbType, String query, String peId, String caseId) {
		return getAllColumns(dbType, replace(getID(query, peId), "CMR_CS_CASEID", caseId));

	}

	public static String getSelectedText(String xpath) {
		return getText(Locator.XPATH, "//*[contains(@name, 'Staff Member')]" + xpath);
	}

	public static MobileElement getExistingAssignment(String assineeName, String AssignmentTypeAndDate) {
		return findElement(By.xpath("//*[contains(@name, '" + assineeName
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + AssignmentTypeAndDate + "')]"));
	}

	public static String getExistingAssignmentDateType(String assignDateType, int index) {
		return findElement(By.xpath("(//*[contains(@name, '" + assignDateType
				+ "')]/preceding-sibling:: XCUIElementTypeStaticText)[" + index + "]")).getText().trim();
	}

	public void clickOnExistingAssignment(String AssignName, String AssignType, DBType dbType, String elID) {
		selectAction(dbType, "Actions", elID);
		Utility.findElementAndScrollDown(Locator.XPATH, "//*[contains(@name, '" + AssignName
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + AssignType + "')]");
		// getExistingAssignment(AssignName, AssignType).click();
	}

	public static void clickOn(String submit, String yes, String ok) {

		Utility.findElementAndScrollDown(Locator.XPATH, containsElement(submit));
		contains(yes).click();
		contains(ok).click();

	}

	public static String getChmAssign(chmAssign asmnt) {
		String assignment = "";
		int index = 0;
		switch (asmnt) {

		case STAFF_MEMBER:
			assignment += "Staff Member";
			index += 3;
			break;

		case ASSIGNMENT:
			assignment += "Assignment";
			index += 5;
			break;

		case ASSIGNMENT_DUE:
			assignment += "Assignment Due";
			index += 9;
			break;

		case ASSIGNMENT_COMPLETED:
			assignment += "Assignment Completed";
			index += 11;
			break;

		case ASSIGNED_DATE:
			assignment += "Assigned";
			index += 7;
			break;

		default:
			break;
		}
		return findElement(By.xpath(getSelectedAssignment(assignment, index))).getText();
	}

	public enum chmAssign {
		STAFF_MEMBER, ASSIGNMENT, ASSIGNED_DATE, ASSIGNMENT_DUE, ASSIGNMENT_COMPLETED
	}

}
