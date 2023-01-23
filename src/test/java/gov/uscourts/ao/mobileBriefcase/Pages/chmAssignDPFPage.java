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
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
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
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.splitBy;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
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

	String dpfName = "chmAssign";
	String createSTF = "New Staff Assignment";
	String actionName = "Auto Test";
	static String elId = "";
	static String name = "";
	static String cha_ju_pe_id = "";
	static String cmr_cs_caseid = "";
	static String cmr_cyv_code = "";

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='OptionList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<WebElement> optionList;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Comments')]/following:: XCUIElementTypeTextView[1]")
	public static WebElement commentField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Calendar\"]/XCUIElementTypeOther[4]/XCUIElementTypeOther[row]/XCUIElementTypeOther[column]")
	public static WebElement calendarColumn;

	@iOSXCUITFindBy(accessibility = "CalendarForward")
	public static WebElement nextPage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='OptionList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeStaticText")
	public static List<WebElement> note;

	@iOSXCUITFindBy(xpath = "//*[@name='ReferralsList' or @name='SessionGroups']/following::XCUIElementTypeButton")
	public static WebElement categroySyncBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Sending...\"]")
	public static List<WebElement> sending;

	@iOSXCUITFindBy(xpath = "XCUIElementTypeStaticText[contains(@name, 'Assignments')]")
	public static List<WebElement> assignments;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Due')]/following::XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeButton")
	public static WebElement dueDate;

	public static String selectADate(chmAssign assign) {

		// String date = "";
		getChmAssign(assign, "tap");
		performPageLoad(driver);
		selectDate(assign);
		performPageLoad(driver);
		return getChmAssign(assign, "act");
	}

	public void getCaseDetails(String caseNumber, String category, List<UserInputData> userInputData) {

		elId += getAllColumns(getID(Queries.EL_ID, actionName), userInputData);
		cha_ju_pe_id += DocumentPage.get_pe_id("jud",userInputData);
		cmr_cs_caseid += CommonPages.getCaseID(caseNumber, userInputData);

		cmr_cyv_code += CommonPages.cmr_cyv_code(category, cmr_cs_caseid, userInputData).trim();

	}

	public void createNewSTF(String caseNumber, String category, List<UserInputData> userInputData) {

		createNewSTF(Assignment.NEW, chmAssign.CREATE, dpfName, elId, cha_ju_pe_id, caseNumber, cmr_cyv_code,
				userInputData);
	}

	public String get_cha_id(String caseNumber, String category, List<UserInputData> userInputData) {

		return cha_id = getCreatedAssignment(Queries.ASSIGNEES_CHA_ID, cha_ju_pe_id, cmr_cs_caseid, userInputData);
	}

	public void modifySTF(String caseNumber, String category, List<UserInputData> userInputData) {

		createNewSTF(Assignment.NEW, chmAssign.MODIFY, dpfName, elId, cha_ju_pe_id, caseNumber, cmr_cyv_code,
				userInputData);

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

		List<String> dbStaffMembers = new ArrayList<>();
		String fName = "";
		String lName = "";

		List<String> dbStafMemberFName = executeQuery(getText(getID(staffMember, peID), screenTypeParam), userInputData,
				0);
		List<String> dbStafMemberLName = executeQuery(getText(getID(staffMember, peID), screenTypeParam), userInputData,
				1);

		for (int i = 0; i < dbStafMemberFName.size(); i++) {

			fName = dbStafMemberFName.get(i).trim();

			lName = dbStafMemberLName.get(i).trim();

			dbStaffMembers.add(fName + " " + lName);
		}

		sort(dbStaffMembers);

		List<String> uiStaffMembers = new ArrayList<>();
		performPageLoad(driver);
		List<WebElement> allStaffMembers = optionList;
		for (WebElement staffMembers : allStaffMembers) {
			uiStaffMembers.add(staffMembers.getText().trim());
			sort(uiStaffMembers);
		}

		if (stf.equals("new")) {

			assertEquals("********STAFF MEMBERS VALIDATION ERROR!!!********", dbStaffMembers, uiStaffMembers);
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
			List<WebElement> allAssignmenTypes = optionList;
			for (WebElement type : allAssignmenTypes) {
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
		assignmentCompleted = selectADate(chmAssign.ASSIGNMENT_COMPLETED);
		// assignmentCompleted = getChmAssign(chmAssign.ASSIGNMENT_COMPLETED, "text");
		contains(apply).click();
		submiTransaction();
		getCreatedRecords(changeFormat(assignmentCompleted), getID(CHC_DATE_END, cha_id), userInputData);

	}

	public static String changeFormat(String date) {
		return changeDateFormat(date, "M/d/yyyy", "yyyy-MM-dd");
	}

	public static String getANewStaffAssignment(String assignment, String assignedDate, String assignmentDue) {
		try {
			WebElement modifiedAssignedDate = getExistingAssignment(staffMember + ", " + assignment,
					"Assigned " + assignedDate);

			assertTrue(modifiedAssignedDate.isDisplayed());

		} catch (Exception e) {

			WebElement modifiedAssignmentDueDate = getExistingAssignment(staffMember + ", " + assignment,
					"Due " + assignmentDue);

			assertTrue(modifiedAssignmentDueDate.isDisplayed());
		}

		// List<String> notes = new ArrayList<>();

//		for (int i = 1; i < note.size() + 1; ++i) {
//			notes.add(note.get(i).getText());
//		}
//
//		checkDatesForDescOrder(notes, "M/d/yyyy");

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

	public static WebElement getCreatedAssignmentNameAndAssignmentType(String staffMember, String assignmnetType) {
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

	public static WebElement getExistingAssignment(String assineeName, String AssignmentTypeAndDate) {
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
		ifDownloaded(sending);
		// tap(Locator.XPATH, containsElement(yes));
		tap(Locator.XPATH, containsElement(ok));

	}

	public static String getChmAssign(chmAssign asmnt, String action) {
		String assignment = "";
		switch (asmnt) {

		case STAFF_MEMBER:
			assignment = "Staff Member";

			break;

		case ASSIGNMENT:
			assignment = "Assignment";
			break;

		case ASSIGNMENT_DUE:
			assignment = "Due";
			break;

		case ASSIGNMENT_COMPLETED:
			assignment = "Assignment Completed";
			break;

		case ASSIGNED_DATE:
			assignment = "Assigned";
			break;

		case DRAFT_PREPARED:
			assignment = "Draft";
			break;

		default:
			break;
		}
		String act = "";
		if (action.equals("tap")) {
			getButton(assignment, "XCUIElementTypeButton").click();

		} else {
			act = getButton(assignment, "XCUIElementTypeButton/XCUIElementTypeStaticText").getText();
		}

		return act;
	}

	public static WebElement getButton(String button, String text) {
		return findElement(By.xpath("//*[contains(@name, '" + button
				+ "')]/following::XCUIElementTypeOther[1]/XCUIElementTypeOther/" + text));
	}

	public enum chmAssign {
		STAFF_MEMBER, ASSIGNMENT, ASSIGNED_DATE, ASSIGNMENT_DUE, DRAFT_PREPARED, ASSIGNMENT_COMPLETED, CREATE, MODIFY,
		TERMINATE, MULTIPLE_DPFs,
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
			performPageLoad(driver);
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
			List<WebElement> allAssignmenTypes = optionList;
			clickOnNumberInRange(allAssignmenTypes);

			break;
		}

		/** STEP 3 --Select an Assigned Date */
		String assignedDate = selectADate(chmAssign.ASSIGNED_DATE);

		/** STEP 4 --Select Assignment Due Date */
		String assignmentDueDate = "";
		if (Actions.isDisplayed(dueDate) == true) {
			assignmentDueDate = selectADate(chmAssign.ASSIGNMENT_DUE);
		}
		/** STEP 4 --Select Assignment Due Date */
		// String draftPrepared = selectADate(chmAssign.DRAFT_PREPARED);

		/** STEP 6 --Verify comment, apply and cancel display */
		verifyElementIsDisplayed(comment);
		verifyElementIsDisplayed(apply);
		verifyElementIsDisplayed(cancel);

		String assignment = getChmAssign(chmAssign.ASSIGNMENT, "text");

//		try {
//			commentField1.sendKeys("$$$$$$$$$$$$$");
//		} catch (NoSuchElementException e) {

		if (!commentField.getText().isEmpty()) {
			commentField.clear();
		}
		commentField.sendKeys("$$$$$$$$$$$$$");
		// }

		switch (assign) {
		case CREATE:
			contains(apply).click();
			getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);

			submiTransaction();
			ifDownloaded(assignments);
			CommonPages page = new CommonPages();
			page.getPanel(Panel.Assignments);

			getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);

			/******************
			 * @AMB-1137
			 */

			// newDBAssignment(cha_ju_pe_id, elId, staffFName, staffLName, assignment,
			// userInputData);

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

	public static void selectDate(chmAssign assign) {
		performPageLoad(driver);

		LocalDate currentdate = LocalDate.now();

		int currentDay = currentdate.getDayOfMonth();

		Month currentMonth = currentdate.getMonth();

		int currentYear = currentdate.getYear();

		LocalDate date = LocalDate.of(currentYear, currentMonth, 1);

		int totalDayCount = date.lengthOfMonth();

		int from = 0;
		int to = 0;
		int day = 0;
		switch (assign) {

		case ASSIGNED_DATE:
			from = 1;
			to = 15;
			day = Utility.getRandomNumberInRange(from, to);
			break;

		case ASSIGNMENT_DUE:
			from = 15;
			to = totalDayCount;
			day = Utility.getRandomNumberInRange(from, to);

			break;

		case DRAFT_PREPARED:

			from = 1;
			to = totalDayCount;
			day = Utility.getRandomNumberInRange(from, to);

			break;

		case ASSIGNMENT_COMPLETED:
			if (currentDay > 01) {
				day = currentDay - 1;
			} else {
				day = currentDay;
			}
			break;

		default:
			break;

		}

		String selectedDate = day + "/" + currentMonth + "/" + currentYear;

		Actions.findElement(By
				.xpath("//*[contains(@name, '" + changeDateFormat(selectedDate, "d/MMMM/yyyy", "dd/MMMM/yyyy") + "')]"))
				.click();

	}

}