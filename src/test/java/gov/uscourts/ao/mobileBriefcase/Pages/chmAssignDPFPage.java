package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE_AND_ASSIGNED_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE_IS_SKIP;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CAV_CODE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHA_CAV_CODE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHC_DATE_END;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHD_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_MEMBERS_FIRST_NAME;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectBriefcaseAction;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.verifyElementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
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

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.stepDefinitions.DPF_stepDefinitions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class chmAssignDPFPage extends AppiumPageFactory {

	static String yes = "Yes";
	static String ok = "OK";
	static String submit = "Submit";
	static String back = "Back";
	static String comment = "Comment";
	static String apply = "Apply";
	static String cancel = "Cancel";
	static String close = "Close";
	static String staffMember = "";
	static String assignment = "";
	static String assignedDate = "";
	static String assignmentDueDate = "";
	static String assignmentCompleted = "";
	static String cha_id = "";

	String dpfName = "chmAssign";

	static String elId = "";
	static String name = "";
	static String cha_ju_pe_id = "";
	static String cmr_cs_caseid = "";
	static String cmr_cyv_code = "";

	public static boolean existing = false;
	public static String exitsingStaffMember = "";
	public static String exitsingAssignmentType = "";
	public static List<String> staffMembers = new ArrayList<>();

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='OptionList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> optionList;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Comments')]/preceding:: XCUIElementTypeTextView[1]")
	public static WebElement commentField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Sending...\"]")
	public static List<WebElement> sending;

	@iOSXCUITFindBy(xpath = "XCUIElementTypeStaticText[contains(@name, 'Assignments')]")
	public static List<WebElement> assignments;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Due')]/following::XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeButton")
	public static WebElement dueDate;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply ruling to all reliefs']")
	public static List<WebElement> applyRulling;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"DocketingDPFList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[9]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeSwitch")
	public static WebElement toggle;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Assignment Notes']/following::XCUIElementTypeStaticText[2][string-length(@name) > 0] | //XCUIElementTypeTextView[string-length(@value) > 0]")
	public static List<WebElement> marksAndSybmols;

	public static String selectADate(chmAssign assign) {

		// String date = "";
		getChmAssign(assign, "tap");
		performPageLoad(driver);
		selectDate(assign);
		performPageLoad(driver);
		return getChmAssign(assign, "act");
	}

	public static void getCaseDetails(String caseNum, String category, String actionName,
			List<UserInputData> userInputData) {

		elId = getAllColumns(getID(Queries.EL_ID, actionName), userInputData);
		cha_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);
		cmr_cs_caseid = DocumentPage.cs_caseid;

		cmr_cyv_code = DocumentPage.cmr_cyv_code;

	}

	public void createSingleSTF(String caseNumber, String category, List<UserInputData> userInputData) {

		getStaffAssignment(Assignment.NEW, chmAssign.CREATE_SINGLE_ASSIGNMENT, dpfName, elId, cha_ju_pe_id, caseNumber,
				cmr_cyv_code, userInputData);
		cha_id = getCha_id(userInputData).get(0);

		getCreatedRecords(changeFormat(assignedDate), getID(CHD_DATE, cha_id), userInputData);
		getCreatedRecords(getAllColumns(getText(CAV_CODE, assignment), userInputData), getID(CHA_CAV_CODE, cha_id),
				userInputData);
	}

	public void createMultipleSTFs(String caseNumber, String category, List<UserInputData> userInputData) {
		existing = false;
		getStaffAssignment(Assignment.NEW, chmAssign.MULTIPLE_ASSIGNMENTS, dpfName, elId, cha_ju_pe_id, caseNumber,
				cmr_cyv_code, userInputData);

		String assignDate1 = assignedDate;
		String assignment1 = assignment;
		String assignmentDueDate1 = assignmentDueDate;

		scrollDownIfNotDisplayed("(" + containsElement("NewStaffButton") + ")[1]");
		CommonPages.verifyElementIsDisplayed("Create Assignment");

		getCaseDetails(caseNumber, category, DPF_stepDefinitions.actionName, userInputData);

		existing = true;

		getStaffAssignment(Assignment.NEW, chmAssign.MULTIPLE_ASSIGNMENTS, dpfName, elId, cha_ju_pe_id, caseNumber,
				cmr_cyv_code, userInputData);

		String assignDate2 = assignedDate;
		String assignment2 = assignment;
		String assignmentDueDate2 = assignmentDueDate;
		submitTransaction();

		CommonPages page = new CommonPages();
		page.getPanel(Panel.Assignments);

		collapseBtn.click();

		getANewStaffAssignment(assignment1, assignDate1, assignmentDueDate1);
		getANewStaffAssignment(assignment2, assignDate2, assignmentDueDate2);

		collapseBtn.click();

		List<String[]> firstQueryResult = DBUtilities.executeDBQuery(ASSIGNMENT_TYPE_AND_ASSIGNED_DATE, userInputData);

		if (firstQueryResult != null && firstQueryResult.size() > 1) {
			String chdDate0 = firstQueryResult.get(0)[0];
			String cavCode0 = firstQueryResult.get(0)[1];

			String chdDate1 = firstQueryResult.get(1)[0];
			String cavCode1 = firstQueryResult.get(1)[1];

			assertEquals("********VERIFY ASSIGNED DATE IS CORRECT for FIRST SATFF ASSIGNMENT********",
					changeFormat(assignDate2).trim(), chdDate0.trim());

			assertEquals("********VERIFY ASSIGNED DATE IS CORRECT for FIRST SATFF ASSIGNMENT********",
					getAllColumns(getText(CAV_CODE, assignment2), userInputData).trim(), cavCode0.trim());

			assertEquals("********VERIFY ASSIGNED DATE IS CORRECT for SECOND SATFF ASSIGNMENT********",
					changeFormat(assignDate1).trim(), chdDate1.trim());

			assertEquals("********VERIFY ASSIGNED DATE IS CORRECT for SECOND SATFF ASSIGNMENT********",
					getAllColumns(getText(CAV_CODE, assignment1), userInputData).trim(), cavCode1.trim());

		}

	}

	public List<String> getCha_id(List<UserInputData> userInputData) {
		List<String> id = new ArrayList<String>();
		List<String> chaId = executeQuery(
				replace(getID(Queries.ASSIGNEES_CHA_IDs, cha_ju_pe_id), "CMR_CS_CASEID", cmr_cs_caseid), userInputData);

		for (int i = 0; i < chaId.size(); i++) {
			id.add(chaId.get(i));
		}
		return chaId;
	}

	public void modifySTF(String caseNumber, String category, List<UserInputData> userInputData) {
		existing = true;
		getStaffAssignment(Assignment.EXISTING, chmAssign.MODIFY, dpfName, elId, cha_ju_pe_id, caseNumber, cmr_cyv_code,
				userInputData);

	}

	public void modify_and_terminate_STF(String caseNumber, String category, List<UserInputData> userInputData) {
		existing = true;
		getStaffAssignment(Assignment.EXISTING, chmAssign.MODIFY_TERMINATE, dpfName, elId, cha_ju_pe_id, caseNumber,
				cmr_cyv_code, userInputData);

	}

	/**
	 * When tapping the drop-down a popup displays a list of staff based on the
	 * screen parameter in the DPF: -----Screen param = ja - only list JAs in the
	 * judge's chambers -----Screen param = lwclk - only list law clerks in the
	 * judge's chambers -----Screen param = lwclk:ja - list both JAs and law clerks
	 * in the judge's chambers -----Screen param = staff - list all staff members
	 * inthe judge's chambers
	 */
	public static String getAvailableStaffMembers(String dpfName, String elId, String peID,
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

		getListOfAvailableStaffMembers(STAFF_MEMBERS_FIRST_NAME, peID, screenParam, 0, userInputData);
		performPageLoad(driver);
		return getChmAssign(chmAssign.STAFF_MEMBER, "text");
	}

	/**
	 * gets the list of staff based on the screen parameter in the DPF, compares
	 * staff members that are displayed on the ui with db and selects one
	 */
	public static void getListOfAvailableStaffMembers(String staffMember, String peID, String screenTypeParam,
			int index, List<UserInputData> userInputData) {
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

		if (existing == false) {
			assertEquals("********STAFF MEMBERS VALIDATION ERROR!!!********", dbStaffMembers, uiStaffMembers);

		}
		exitsingStaffMember = clickOnNumberInRange(allStaffMembers);
		staffMembers.add(exitsingStaffMember);
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
			if (existing == false) {
				exitsingAssignmentType = clickOnNumberInRange(allAssignmenTypes);
			} else {
				Actions.contains(exitsingAssignmentType).click();
			}

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

	public static void submitTransaction() {
		try {
			clickOn(submit, yes, ok);
		} catch (WebDriverException e) {
			System.err.println("Error during submission click: " + e.getMessage());
			return;
		}

		Utility.scrollPage("down");

		final int maxScrollAttempts = 5;
		int scrollCount = 0;
		boolean elementFound = false;

		while (scrollCount < maxScrollAttempts && !elementFound) {
			List<WebElement> elements = findElements(By.xpath("//*[contains(@name, 'Case Information')]"));

			if (!elements.isEmpty()) {
				WebElement lastElement = elements.get(elements.size() - 1);
				try {
					lastElement.click();
					elementFound = true;
					System.out.println("Clicked on 'Case Information' element.");
				} catch (WebDriverException e) {
					System.err.println("Error clicking on 'Case Information' element: " + e.getMessage());
				}
			} else {
				Utility.scrollPage("down");
				performPageLoad(driver);
				scrollCount++;
				System.out.println("Scroll attempt #" + scrollCount + " - 'Case Information' not found.");
			}
		}

		if (!elementFound) {
			System.err.println(
					"Failed to find and click on 'Case Information' element after " + maxScrollAttempts + " scrolls.");
		}
	}

	public static void terminateStaffAssignment(List<UserInputData> userInputData) {
		assignmentCompleted = selectADate(chmAssign.ASSIGNMENT_COMPLETED);
		contains(apply).click();
		submitTransaction();

		getCreatedRecords(changeFormat(assignmentCompleted), getID(CHC_DATE_END, cha_id), userInputData);

	}

	public static String changeFormat(String date) {
		return changeDateFormat(date, "M/d/yyyy", "yyyy-MM-dd");
	}

	public static String getANewStaffAssignment(String assignment, String assignedDate, String assignmentDue) {

		collapseBtn.click();
		performPageLoad(driver);
		try {
			WebElement modifiedAssignedDate = getExistingAssignment(staffMember + ", " + assignment,
					"Assigned " + assignedDate);

			assertTrue(modifiedAssignedDate.isDisplayed());
			modifiedAssignedDate.click();

		} catch (Exception e) {

			WebElement modifiedAssignmentDueDate = getExistingAssignment(staffMember + ", " + assignment,
					"Due " + assignmentDue);

			assertTrue(modifiedAssignmentDueDate.isDisplayed());
			modifiedAssignmentDueDate.click();

		}
		assertTrue("Verify that invalid characters are discards in chmAssign note",getPunctuationMarks(marksAndSybmols)
				.equals(". , ! ? : ; ' \\\" - ( ) [ ] { } /@ # $ % ^ & * _ + = < > | ~ `"));

		Actions.navigateBack();
		collapseBtn.click();

		return assignment;
	}

	public static String getPunctuationMarks(List<WebElement> marksAndSymbols) {
		Page.waitForVisibilityOfAllElements(marksAndSymbols, driver);
		for (WebElement el : marksAndSymbols) {
			String txt = el.getText();
			if (txt != null && txt.matches(".*[.,!?:;'\"\\-()\\[\\]{}@#$%^&*_+=<>|~/`].*")) {
				txt = txt.replaceAll("[^.,!?:;'\"\\-()\\[\\]{}@#$%^&*_+=<>|~/` ]", "").trim();

				txt = txt.replace("\"", "\\\"");

				return txt;
			}
		}
		return "";
	}

	/** Verify the records are created in CM/ECF */
	public static void getCreatedRecords(String expected, String actual, List<UserInputData> userInputData) {
		String CMECF_TABLES = getAllColumns(actual, userInputData);

		String exp = Actions.split(expected, ":", 0);
		String act = Actions.split(CMECF_TABLES, ":", 0);

		assertEquals("********PLEASE VERIFY THAT RECORDS IN CMECF ARE CREATED CORRECTLY!!!********", exp, act);
	}

	public static WebElement getExistingAssignment(String assineeName, String AssignmentTypeAndDate) {

		return Page.waitForPresenceOfElementLocated(By.xpath("//*[contains(@name, '" + assineeName
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + AssignmentTypeAndDate.trim() + "')]"),
				driver);
	}

	public static void clickOnExistingAssignment(String staffMember) {

		String actionName = DPF_stepDefinitions.actionName;

		selectBriefcaseAction("Actions", actionName);

		scrollDownIfNotDisplayed(containsElement(staffMember));
	}

	public static void clickOn(String submit, String yes, String ok) {
		Utility.swipe(1, "up");
		if (applyRulling.size() == 1) {
			if (Utility.getToggleState(toggle) == false) {
				toggle.click();

			}
		}
		scrollDownIfNotDisplayed(containsElement(submit));
		ifDownloaded(sending);

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
		String baseXPath = "//*[contains(@name, '" + button + "')]";
		String xpathVisible = baseXPath + "/following::XCUIElementTypeOther[1]/" + text;
		String xpathFallback = baseXPath + "/following::XCUIElementTypeOther[1]/XCUIElementTypeOther/" + text;

		if (Actions.isDisplayed(Locator.XPATH, xpathVisible)) {
			return Page.waitForVisibilityOfElement(findElement(By.xpath(xpathVisible)), driver);
		} else {
			return findElement(By.xpath(xpathFallback));
		}
	}

	public enum chmAssign {
		STAFF_MEMBER, ASSIGNMENT, ASSIGNED_DATE, ASSIGNMENT_DUE, DRAFT_PREPARED, ASSIGNMENT_COMPLETED,
		CREATE_SINGLE_ASSIGNMENT, MODIFY, TERMINATE, MULTIPLE_ASSIGNMENTS, MODIFY_TERMINATE
	}

	public enum Assignment {
		NEW, EXISTING
	}

	public static void getStaffAssignment(Assignment Assignment, chmAssign assign, String dpfName, String elId,
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

		performPageLoad(driver);

		switch (assign) {
		case CREATE_SINGLE_ASSIGNMENT:
			staffMember = getAvailableStaffMembers(dpfName, elId, cha_ju_pe_id, userInputData);

			break;

		case MODIFY:

			staffMember = getAvailableStaffMembers(dpfName, elId, cha_ju_pe_id, userInputData);
			break;

		case MODIFY_TERMINATE:

			staffMember = getAvailableStaffMembers(dpfName, elId, cha_ju_pe_id, userInputData);
			break;

		case MULTIPLE_ASSIGNMENTS:
			staffMember = getAvailableStaffMembers(dpfName, elId, cha_ju_pe_id, userInputData);
			break;

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
		assignedDate = selectADate(chmAssign.ASSIGNED_DATE);

		/** STEP 4 --Select Assignment Due Date */
		// String assignmentDueDate = "";
		if (Actions.isDisplayed(dueDate) == true) {
			assignmentDueDate = selectADate(chmAssign.ASSIGNMENT_DUE);
		}
		/** STEP 4 --Select Assignment Due Date */
		// String draftPrepared = selectADate(chmAssign.DRAFT_PREPARED);

		/** STEP 6 --Verify comment, apply and cancel display */
		verifyElementIsDisplayed(comment);
		verifyElementIsDisplayed(apply);
		verifyElementIsDisplayed(cancel);

		assignment = getChmAssign(chmAssign.ASSIGNMENT, "text");

		if (!commentField.getText().isEmpty()) {
			commentField.clear();
		}
		commentField.sendKeys(". , ! ? : ; ' \\\" - ( ) [ ] { } /@ # $ % ^ & * _ + = < > | ~ `£€😀");
		// }

		switch (assign) {
		case CREATE_SINGLE_ASSIGNMENT:
			contains(apply).click();

			collapseBtn.click();

			getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);
			collapseBtn.click();
			submitTransaction();

			ifDownloaded(assignments);

			if (existing == false) {

				CommonPages page = new CommonPages();
				page.getPanel(Panel.Assignments);

				collapseBtn.click();

				getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);

				// collapseBtn.click();
				/******************
				 * @AMB-1137
				 */

				// newDBAssignment(cha_ju_pe_id, elId, staffFName, staffLName, assignment,
				// userInputData);

				clickOnExistingAssignment(staffMember + ", " + assignment);
			} else {

				String actionName = DPF_stepDefinitions.actionName;

				selectBriefcaseAction("Actions", actionName);
				scrollDownIfNotDisplayed("(" + containsElement("NewStaffButton") + ")[1]");
			}

			break;

		case MODIFY:

			contains(apply).click();
			/** After the new assignment is created it clicks on it */
			collapseBtn.click();
			getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);
			collapseBtn.click();
			submitTransaction();
			clickOnExistingAssignment(staffMember + ", " + assignment);

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

		case MULTIPLE_ASSIGNMENTS:
			contains(apply).click();

			getANewStaffAssignment(assignment, assignedDate, assignmentDueDate);

			break;

		case MODIFY_TERMINATE:

			terminateStaffAssignment(userInputData);
			getCreatedRecords(changeFormat(assignedDate), getID(CHD_DATE, cha_id), userInputData);
			getCreatedRecords(getAllColumns(getText(CAV_CODE, assignment), userInputData), getID(CHA_CAV_CODE, cha_id),
					userInputData);
			break;

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

	public void verifyChmAssignTextSupport() {

		Actions.isDisplayed(Locator.XPATH, Actions.containsElement(assignment));
		Actions.isDisplayed(Locator.XPATH, Actions.containsElement(assignedDate));
		Actions.isDisplayed(Locator.XPATH, Actions.containsElement(assignmentDueDate));

	}

}