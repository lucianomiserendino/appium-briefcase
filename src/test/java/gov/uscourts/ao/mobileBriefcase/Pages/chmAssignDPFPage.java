package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE_IS_SKIP;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CAV_CODE;
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
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.verifyElementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.clickOnNumberInRange;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getParameter;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.*;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup.Variables;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class chmAssignDPFPage extends AppiumPageFactory {

	static String yes = "Yes";
	static String ok = "OK";
	static String back = "Back";
	static String comment = "Comment";
	static String apply = "Apply";
	static String cancel = "Cancel";
	static String submit = "Submit";
	static String close = "Close";
	static String staffMember = "";
	static String assignment = "";
	static String assignmentNameAndType = "";
	static String assignmentDate = "";
	static String assignmentCompleted = "";
	static String cha_id = "";

	String createSTF = "New Staff Assignment";
	String actionName = "Auto Test";
	String cmr_cyv_code = "prhr";
	static String calendarRows = "//XCUIElementTypeOther[@name='Calendar']/XCUIElementTypeOther[4]/XCUIElementTypeOther";

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='OptionList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<MobileElement> optionList;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[13]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextView")
	public static MobileElement commentField1;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"DocumentList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[15]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextView")
	public static MobileElement commentField2;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Calendar\"]/XCUIElementTypeOther[4]/XCUIElementTypeOther[row]/XCUIElementTypeOther[column]")
	public static MobileElement calendarColumn;

	@iOSXCUITFindBy(accessibility = "CalendarForward")
	public static MobileElement nextPage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='OptionList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeStaticText")
	public static List<MobileElement> note;

	public static String selectADate(chmAssign assign) {

		String date = "";
		getChmAssign(assign, "tap");
		performPageLoad(driver);
		date = selectDate();
		performPageLoad(driver);
		return date;
	}

	public static String selectDate() {
		performPageLoad(driver);
		List<MobileElement> rows = Actions.findElements(By.xpath(calendarRows));
		List<MobileElement> columns = null;
		String date = "";
		for (int i = 1; i < rows.size() + 1; ++i) {

			columns = Actions.findElements(
					By.xpath("//XCUIElementTypeOther[@name='Calendar']/XCUIElementTypeOther[4]/XCUIElementTypeOther["
							+ i + "]/XCUIElementTypeOther"));

		}
		date += clickOnNumberInRange(columns);
		return changeDateFormat(date.split(" ")[1], "dd/MMMM/yyyy", "M/d/yyyy");
	}

	public void createNewStaffAssignment(String caseNumber, List<UserInputData> userInputData) {

		String dpfName = "chmAssign";
		String elId = getAllColumns(getID(Queries.EL_ID, actionName), userInputData);
		String name = SystemPropertySetup.getVariable(Variables.JUD, userInputData);
		String cha_ju_pe_id = DBUtilities.getPE_ID("jud", name, userInputData);
		String cmr_cs_caseid = CommonPages.getCaseID(caseNumber, userInputData);

		createNewSTF(Assignment.NEW, chmAssign.CREATE, dpfName, elId, cha_ju_pe_id, caseNumber, cmr_cyv_code,
				userInputData);

		//cha_id = getCreatedAssignment(Queries.ASSIGNEES_CHA_ID, cha_ju_pe_id, cmr_cs_caseid, userInputData);

		//createNewSTF(Assignment.NEW, chmAssign.MODIFY, dpfName, elId, cha_ju_pe_id, caseNumber, cmr_cyv_code,
		//		userInputData);
		//terminateStaffAssignment(userInputData);

	}

	/**
	 * When tapping the drop-down a popup displays a list of staff based on the
	 * screen parameter in the DPF: -----Screen param = ja - only list JAs in the
	 * judge's chambers -----Screen param = lwclk - only list law clerks in the
	 * judge's chambers -----Screen param = lwclk:ja - list both JAs and law clerks
	 * in the judge's chambers -----Screen param = staff - list all staff members
	 * inthe judge's chambers
	 */
	public static String getAvailableStaffMembers(String stf, String dpfName, String elId, String peID,
			List<UserInputData> userInputData) {

		String screenTypeParam = getParameter(getAllColumns(getID(MBR_NOTE, elId), userInputData), dpfName, 0);

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

		getListOfAvailableStaffMembers(stf, STAFF_MEMBERS_FIRST_NAME, peID, screenParam, 0, userInputData);
		performPageLoad(driver);
		return getChmAssign(chmAssign.STAFF_MEMBER, "text");
	}

	/**
	 * gets the list of staff based on the screen parameter in the DPF, compares
	 * staff members that are displayed on the ui with db and selects one
	 */
	public static void getListOfAvailableStaffMembers(String stf, String staffMember, String peID,
			String screenTypeParam, int index, List<UserInputData> userInputData) {

		List<String> dbStafMembers = executeQuery(getText(getID(staffMember, peID), screenTypeParam), userInputData);
		sort(dbStafMembers);

		List<String> uiStaffMembers = new ArrayList<>();

		List<MobileElement> allStaffMembers = optionList;
		for (MobileElement staffMembers : allStaffMembers) {
			uiStaffMembers.add(staffMembers.getText().split(" ")[index]);
			sort(uiStaffMembers);
		}
		if (stf.equals("new")) {
			assertEquals("********STAFF MEMBERS VALIDATION ERROR!!!********", dbStafMembers, uiStaffMembers);
		}
		clickOnNumberInRange(allStaffMembers);

	}

	/**
	 * Verify when you tap the Please Select button next to the Assignment label, a
	 * pop-up displays with valid assignment types
	 */
	public static void getAssignmentType(String dpfName, String elId, String cha_ju_pe_id, String caseNumber,
			String cmr_cyv_code, String pr_first_name, String pr_last_name, List<UserInputData> userInputData) {

		List<String> uiAssignmenType = new ArrayList<>();
		try {
			performPageLoad(driver);
			List<MobileElement> allAssignmenTypes = optionList;
			for (MobileElement type : allAssignmenTypes) {
				uiAssignmenType.add(type.getText().trim());
				sort(uiAssignmenType);
			}

			String assignmentType = getParameter(getAllColumns(getID(MBR_NOTE, elId), userInputData), dpfName, 1);
			if (assignmentType.equals("SKIP")) {
				/** If the assignment type parameter is set to SKIP, use this query */
				getValidAssignmentTypes(ASSIGNMENT_TYPE_IS_SKIP, uiAssignmenType, cha_ju_pe_id, caseNumber,
						cmr_cyv_code, pr_first_name, pr_last_name, userInputData);
			} else {
				/**
				 * If the assignment type parameter contains a colon delimited list, use this
				 * query
				 */
				getValidAssignmentTypes(
						getText(ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST,
								"'" + assignmentType.replaceAll(":", "','") + "'"),
						uiAssignmenType, cha_ju_pe_id, caseNumber, cmr_cyv_code, pr_first_name, pr_last_name,
						userInputData);
			}
			clickOnNumberInRange(allAssignmenTypes);

		} catch (Exception e) {
			e.getMessage();
		}
	}

	/** verify assignment types based on assignment type parameter */
	public static void getValidAssignmentTypes(String query, List<String> uiAssignmenType, String cha_ju_pe_id,
			String caseNumber, String cmr_cyv_code, String pr_first_name, String pr_last_name,
			List<UserInputData> userInputData) {

		List<String> dbAssignmentType = executeQuery(query, userInputData);
		sort(dbAssignmentType);

		if (dbAssignmentType.contains("(Please Select)"))
			dbAssignmentType.remove("(Please Select)");

		String cmr_id = AssignmentsPage.getCMR_ID(caseNumber, cha_ju_pe_id, cmr_cyv_code, userInputData);

		try {
			/**
			 * this line checks if a staff member exists with that assignment type, that
			 * assignment type won't be displayed after you tap the Please Select button
			 * next to the Assignment
			 */
			List<String> cavDescription = executeQuery(replace(Queries.EXISTING_STAFF_ASSIGNMENTS, "CMR_ID", cmr_id,
					"PR_FIRST_NAME", pr_first_name, "PR_LAST_NAME", pr_last_name), userInputData);
			for (int i = 0; i < cavDescription.size(); i++) {
				if (cavDescription.size() > 0 && dbAssignmentType.contains(cavDescription.get(i))) {
					dbAssignmentType.remove(cavDescription.get(i));
				}
			}
			assertEquals("********ASSIGNMENT TYPE VALIDATION ERROR!!!********", dbAssignmentType, uiAssignmenType);

		} catch (NullPointerException e) {

			assertEquals("********ASSIGNMENT TYPE VALIDATION ERROR!!!********", dbAssignmentType, uiAssignmenType);
		}
	}

	public static void submiTransaction() {
		try {
			clickOn(submit, yes, ok);
		} catch (WebDriverException e) {
			e.getMessage();
		}
	}

	public void terminateStaffAssignment(List<UserInputData> userInputData) {
		selectADate(chmAssign.ASSIGNMENT_COMPLETED);
		assignmentCompleted = getChmAssign(chmAssign.ASSIGNMENT_COMPLETED, "text");
		contains(apply).click();
		submiTransaction();
		getCreatedRecords(changeFormat(assignmentCompleted), getID(CHC_DATE_END, cha_id), userInputData);

	}

	public static String changeFormat(String date) {
		return changeDateFormat(date, "M/d/yyyy", "yyyy-MM-dd");
	}

	public static String getANewStaffAssignment(String assignment, String assignedDate, String assignmentDue) {
		try {
			MobileElement modifiedAssignedDate = getExistingAssignment(staffMember + ", " + assignment,
					"Assigned " + assignedDate);

			assertTrue(modifiedAssignedDate.isDisplayed());

		} catch (Exception e) {

			MobileElement modifiedAssignmentDueDate = getExistingAssignment(staffMember + ", " + assignment,
					"Assignment Due " + assignmentDue);

			assertTrue(modifiedAssignmentDueDate.isDisplayed());
		}

		List<String> notes = new ArrayList<>();

		for (int i = 1; i < note.size() + 1; ++i) {
			notes.add(note.get(i).getText());
		}

		checkDatesForDescOrder(notes);

		return assignment;
	}

	/** check the back-end updates when a new staff assignment is created */
	public static void newDBAssignment(String peId, String elID, String staffMembersFName, String staffMembersLName,
			String assignment, List<UserInputData> userInputData) {

		String peID = getAllColumns(getID("SELECT first 1 pe_id\n"
				+ "FROM group inner join member on gp_id = mb_gp_id_parent \n"
				+ "join personrole on pe_pr_prid = mb_ur_pr_prid \n" + "join person on pe_pr_prid = pr_prid \n"
				+ "join user on ur_pr_prid = pr_prid \n" + "where  pr_first_name='" + staffMembersFName
				+ "' and pr_last_name='" + staffMembersLName
				+ "' and   gp_id in (select gp_id from group inner join member on gp_id = mb_gp_id_parent join person on pr_prid = mb_ur_pr_prid \n"
				+ "join personrole on pe_pr_prid = pr_prid where pe_id = '?' and gp_name like '%Chambers%') and pe_date_end is null and pr_prid <> \n"
				+ "(select pr_prid from personrole join person on pe_pr_prid = pr_prid where pe_id = '?' and ur_date_disabled is null ) order by pe_date_created desc",
				peId), userInputData);

		String dbAssignmentType = getAllColumns(
				"SELECT cav_code  FROM chm_assign_type_val WHERE cav_display='" + assignment
						+ "' and cav_chm_role in ('staff', 'all') and cav_date_end is null ORDER BY cav_display",
				userInputData);

		String dbChambersAssignment = getAllColumns(getID(
				"select first 1 cha_id,cha_date_created from chambers_assignment where cha_ju_pe_id = ? and cha_assigner_ju_pe_id = ?  and "
						+ "cha_chm_pe_id = '" + peID + "' and cha_cav_code = '" + dbAssignmentType
						+ "' order by cha_date_created desc",
				peId), userInputData);
		/***
		 * After creating a new assignment it will verify the following records are
		 * created in cmecf
		 */
		getCreatedRecords(dbChambersAssignment, getID(CHAMBERS_ASSIGNMENT, peId), userInputData);
		getCreatedRecords(dbChambersAssignment, CHM_ASSIGN_TO_CASE, userInputData);
		getCreatedRecords(dbChambersAssignment, CHAMBERS_ASSIGN_DATE, userInputData);

	}

	public static String dropDownIndex(String assignment, int index) {
		return "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["
				+ index
				+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, '"
				+ assignment + "')]/following::XCUIElementTypeOther[2]/XCUIElementTypeButton";
	}

	public static MobileElement getCreatedAssignmentNameAndAssignmentType(String staffMember, String assignmnetType) {
		return Actions.findElementBy(Locator.XPATH,
				"//*[contains(@name, '" + staffMember + ", " + assignmnetType + "')]");

	}

	public static String getCreatedAssignmentType(String staffMember, String assignmnetType) {
		return getText(Locator.XPATH, "//*[contains(@name, '" + staffMember + ", " + assignmnetType
				+ "')]/following:: XCUIElementTypeOther[2]/XCUIElementTypeStaticText");
	}

	/** Verify the records are created in CM/ECF */
	public static void getCreatedRecords(String expected, String actual, List<UserInputData> userInputData) {
		String CMECF_TABLES = getAllColumns(actual, userInputData);

		String exp = Actions.split(expected, ":", 0);
		String act = Actions.split(CMECF_TABLES, ":", 0);

		assertEquals("********PLEASE VERIFY THAT RECORDS IN CMECF ARE CREATED CORRECTLY!!!********", exp, act);
	}

	public static String getCreatedAssignment(String query, String peId, String caseId,
			List<UserInputData> userInputData) {
		return getAllColumns(replace(getID(query, peId), "CMR_CS_CASEID", caseId), userInputData);

	}

	public static MobileElement getExistingAssignment(String assineeName, String AssignmentTypeAndDate) {
		return findElement(By.xpath("//*[contains(@name, '" + assineeName
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + AssignmentTypeAndDate + "')]"));
	}

	public static void clickOnExistingAssignment(String staffMember, String elID, List<UserInputData> userInputData) {
		// selectAction(dbType, "Actions", elID);
		selectAction("Actions", elID, userInputData);
		scrollDownIfNotDisplayed(containsElement(staffMember));
	}

	public static void clickOn(String submit, String yes, String ok) {
		scrollDownIfNotDisplayed(containsElement(submit));
		tap(Locator.XPATH, containsElement(yes));
		tap(Locator.XPATH, containsElement(ok));

	}

	public static String getChmAssign(chmAssign asmnt, String action) {
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
			assignment += "Due";
			index += 9;
			break;

		case ASSIGNMENT_COMPLETED:
			assignment += "Assignment Completed";
			index += 13;
			break;

		case ASSIGNED_DATE:
			assignment += "Assigned";
			index += 7;
			break;

		default:
			break;
		}
		String act = "";
		if (action.equals("tap")) {
			tap(Locator.XPATH, dropDownIndex(assignment, index));
		} else {
			act = findElement(By.xpath(dropDownIndex(assignment, index))).getText();
		}
		return act;
	}

	public enum chmAssign {
		STAFF_MEMBER, ASSIGNMENT, ASSIGNED_DATE, ASSIGNMENT_DUE, ASSIGNMENT_COMPLETED, CREATE, MODIFY, TERMINATE,
		MULTIPLE_DPFs,
	}

	public enum Assignment {
		NEW, EXISTING
	}

	public static void createNewSTF(Assignment Assignment, chmAssign assign, String dpfName, String elId,
			String cha_ju_pe_id, String caseNumber, String cmr_cyv_code, List<UserInputData> userInputData) {
		/** STEP 2 --Select an assignment */

		/******************
		 * @AMB-1123 ***
		 */
		/***
		 * STEP 1 --Select a staff member
		 */
		getChmAssign(chmAssign.STAFF_MEMBER, "tap");

		String staffFName = "";
		String staffLName = "";

		switch (assign) {
		case CREATE:
			staffMember = getAvailableStaffMembers("new", dpfName, elId, cha_ju_pe_id, userInputData);

			break;

		case MODIFY:

			staffMember = getAvailableStaffMembers("existing", dpfName, elId, cha_ju_pe_id, userInputData);

		default:
			break;

		}
		staffFName = splitBy(staffMember, 0);
		staffLName = splitBy(staffMember, 1);

		getChmAssign(chmAssign.ASSIGNMENT, "tap");

		switch (Assignment) {
		case NEW:
			getAssignmentType(dpfName, elId, cha_ju_pe_id, caseNumber, cmr_cyv_code, staffFName, staffLName,
					userInputData);
			break;

		case EXISTING:

			performPageLoad(driver);
			List<MobileElement> allAssignmenTypes = optionList;
			clickOnNumberInRange(allAssignmenTypes);

			break;
		}

		/** STEP 3 --Select an Assigned Date */
		String assignedDate = selectADate(chmAssign.ASSIGNED_DATE);

		/** STEP 4 --Select Assignment Due Date */
		String assignmentDueDate = selectADate(chmAssign.ASSIGNMENT_DUE);

		/** STEP 5 --Verify comment, apply and cancel display */
		verifyElementIsDisplayed(comment);
		verifyElementIsDisplayed(apply);
		verifyElementIsDisplayed(cancel);

		String assignment = getChmAssign(chmAssign.ASSIGNMENT, "text");

		try {
			commentField1.sendKeys("$$$$$$$$$$$$$");
		} catch (NoSuchElementException e) {

			if (!commentField2.getText().isEmpty()) {
				commentField2.clear();
				commentField2.sendKeys("$$$$$$$$$$$$$");
			}
		}
		switch (assign) {
		case CREATE:
			contains(apply).click();
			getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);

			submiTransaction();

			getPanel(Panel.Assignments);

			getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);

			/******************
			 * @AMB-1137
			 */

			newDBAssignment(cha_ju_pe_id, elId, staffFName, staffLName, assignment, userInputData);

			clickOnExistingAssignment(staffMember + ", " + assignment, elId, userInputData);

			break;

		case MODIFY:
			contains(apply).click();
			/** After the new assignment is created it clicks on it */
			getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);
			submiTransaction();
			clickOnExistingAssignment(staffMember + ", " + assignment, elId, userInputData);

			/*****
			 * @AMB-1173
			 * 
			 * Back-end modify assignment updates
			 */
			getCreatedRecords(getAllColumns(getText(CAV_CODE, assignment), userInputData), getID(CHA_CAV_CODE, cha_id),
					userInputData);
			try {

				getCreatedRecords(changeFormat(assignedDate), getID(CHD_DATE, cha_id), userInputData);
			} catch (AssertionError e) {
				getCreatedRecords(changeFormat(assignmentDueDate), getID(CHD_DATE, cha_id), userInputData);
			}
			break;

		case MULTIPLE_DPFs:
			contains(apply).click();
			getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);

		default:
			break;
		}

	}

}