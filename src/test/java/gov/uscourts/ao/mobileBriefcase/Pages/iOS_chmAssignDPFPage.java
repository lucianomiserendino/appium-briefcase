package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNEES_CAV_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNEES_CHA_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNEEs_FIRST_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNEEs_LAST_NAME;
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
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectAction;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.clickOnNumberInRange;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.clickOnRandomValue;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getParameter;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.splitBy;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;

public class iOS_chmAssignDPFPage extends AppiumPageFactory {

	static String yesBTN = "Yes";
	static String okBTN = "OK";
	static String backBTN = "Back";

	public static void getElementNextToDropDown(String element, String dropDowN) {
		tap(Locator.XPATH, getDropDown(element, dropDowN));
	}

	public static String getDropDown(String listOfStaff, String dropDowName) {
		return containsElement(
				listOfStaff + "')]/preceding-sibling:: XCUIElementTypeStaticText[contains(@name, '" + dropDowName + "");
	}

	public String verifyListOfStaff(DBType dbType, String elId, String peID) {

		String screenTypeParam = getParameter(getAllColumns(dbType, getID(MBR_NOTE, elId)), 0);

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
		getStaffMembers(dbType, STAFF_MEMBERS_FIRST_NAME, peID, screenParam, 0);
		Page.performPageLoad(driver);
		return getStaffMember();
	}

	/** get a list of staff based on the screen parameter in the DPF */
	public void getStaffMembers(DBType dbtype, String staffMember, String peID, String screenTypeParam, int index) {

		List<String> dbStafMembers = executeQuery(dbtype, getText(getID(staffMember, peID), screenTypeParam));
		sort(dbStafMembers);
		try {
			List<String> uiStaffMembers = new ArrayList<>();
			List<MobileElement> allStaffMembers = driver.findElements(By
					.xpath("//XCUIElementTypeTable[@name='OptionList']/XCUIElementTypeCell/XCUIElementTypeStaticText"));
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
	public void getAssignmentType(DBType dbType, String elId, String cha_ju_pe_id, String cmr_cs_caseid,
			String cmr_cyv_code, String pr_first_name, String pr_last_name) {

		List<String> uiAssignmenType = new ArrayList<>();
		try {
			List<MobileElement> allAssignmenTypes = driver.findElements(By
					.xpath("//XCUIElementTypeTable[@name='OptionList']/XCUIElementTypeCell/XCUIElementTypeStaticText"));
			for (MobileElement type : allAssignmenTypes) {
				uiAssignmenType.add(type.getText().trim());
				sort(uiAssignmenType);
			}
			String assignmentType = getParameter(getAllColumns(dbType, getID(MBR_NOTE, elId)), 1);
			if (assignmentType.equals("SKIP")) {
				/** If the assignment types parameter is set to SKIP, use this query */
				getValidAssignmentTypes(dbType, ASSIGNMENT_TYPE_IS_SKIP, uiAssignmenType, cha_ju_pe_id, cmr_cs_caseid,
						cmr_cyv_code, pr_first_name, pr_last_name);
			} else {
				/**
				 * If the assignment types parameter contains a colon delimited list, use this
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
			assertEquals("*********ASSIGNMENT TYPE VALIDATION ERROR!!!*********", dbAssignmentType, uiAssignmenType);
		}
	}

	public static void selectADate(int index) {
		List<MobileElement> date = driver.findElements(By.xpath(
				"//XCUIElementTypeApplication[@name='Briefcase [Test]']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther"));
		date.get(date.size() - index).click();

	}

	public static String getSelectedAssignment(String assignment) {
		String xpath = "";
		if (assignment.equals("Staff Member")) {
			return xpath += getSelectedText("/preceding-sibling:: XCUIElementTypeStaticText");
		} else if (assignment.equals("Assignment")) {
			return xpath += getSelectedText("/following:: XCUIElementTypeStaticText[contains(@name, '" + assignment
					+ "')][1]/preceding-sibling:: XCUIElementTypeStaticText");
		} else {
			return xpath += getSelectedText("/following:: XCUIElementTypeStaticText[contains(@name, '" + assignment
					+ "')]/preceding-sibling:: XCUIElementTypeStaticText");

		}
	}

	public static String getCreatedAssignmentNameAndAssignmentType(String staffMember, String assignmnetType) {
		return getText(Locator.XPATH, "//*[contains(@name, '" + staffMember + ", " + assignmnetType + "')]");

	}

	public String getCreatedAssignmentType(String staffMember, String assignmnetType) {
		return getText(Locator.XPATH, "//*[contains(@name, '" + staffMember + ", " + assignmnetType
				+ "')]/following-sibling:: XCUIElementTypeStaticText[1]");

	}

	public static String getStaffMember() {
		return getSelectedAssignment("Staff Member");
	}

	public static String getStaffMembersFullName(int index) {
		return splitBy(getSelectedAssignment("Staff Member"), index);
	}

	public static String getAssignment() {
		return getSelectedAssignment("Assignment");
	}

	public static String getAssignedDate() {
		return getSelectedAssignment("Assigned");
	}

	public static String getAssignmentDue() {
		return getSelectedAssignment("Assignment Due");
	}

	public static String getAssignmentCompleted() {
		return getSelectedAssignment("Assignment Completed");
	}

	public void clickOn(String submit, String yes, String ok) {

		contains(submit).click();
		contains(yes).click();
		contains(ok).click();

	}

	public void getExistingAssignment(String apply, String submit, DBType dbType, String peId, String elID) {

		String staffMember = getStaffMember();
		String staffMembersFName = getStaffMembersFullName(0);
		String staffMembersLName = getStaffMembersFullName(1);
		String assignment = getAssignment();

		contains(apply).click();

		String createdAssignmentNamechmAssignPage = getCreatedAssignmentNameAndAssignmentType(staffMember, assignment);
		String createdAssignmentTypechmAssignPage = getCreatedAssignmentType(staffMember, assignment);

		try {
			clickOn(submit, yesBTN, okBTN);
		} catch (WebDriverException e) {
			e.getMessage();
		} finally {

			String peID = getAllColumns(dbType, getID("SELECT first 1 pe_id\n"
					+ "FROM group inner join member on gp_id = mb_gp_id_parent \n"
					+ "join personrole on pe_pr_prid = mb_ur_pr_prid \n" + "join person on pe_pr_prid = pr_prid \n"
					+ "join user on ur_pr_prid = pr_prid \n" + "where  pr_first_name='" + staffMembersFName
					+ "' and pr_last_name='" + staffMembersLName
					+ "' and   gp_id in (select gp_id from group inner join member on gp_id = mb_gp_id_parent join person on pr_prid = mb_ur_pr_prid \n"
					+ "join personrole on pe_pr_prid = pr_prid where pe_id = '?' and gp_name like '%Chambers%') and pe_date_end is null and pr_prid <> \n"
					+ "(select pr_prid from personrole join person on pe_pr_prid = pr_prid where pe_id = '?' and ur_date_disabled is null ) order by pe_date_created desc",
					peId));

			String dbAssignmentType = getAllColumns(dbType,
					"SELECT cav_code  FROM chm_assign_type_val WHERE cav_display='" + assignment
							+ "' and cav_chm_role in ('staff', 'all') and cav_date_end is null ORDER BY cav_display");

			String dbChambersAssignment = getAllColumns(dbType, getID(
					"select first 1 cha_id,cha_date_created from chambers_assignment where cha_ju_pe_id = ? and cha_assigner_ju_pe_id = ?  and "
							+ "cha_chm_pe_id = '" + peID + "' and cha_cav_code = '" + dbAssignmentType
							+ "' order by cha_date_created desc",
					peId));

			/*** verify the following records are created in cmecf */
			getCreatedRecords(dbType, dbChambersAssignment, getID(CHAMBERS_ASSIGNMENT, peId));
			getCreatedRecords(dbType, dbChambersAssignment, CHM_ASSIGN_TO_CASE);
			getCreatedRecords(dbType, dbChambersAssignment, CHAMBERS_ASSIGN_DATE);

			/** once the assignment is created, validate ui with db */
			clickOnExistingAssignment(createdAssignmentNamechmAssignPage, createdAssignmentTypechmAssignPage, dbType,
					elID);

			verifyExistingStaffAssignment(dbType, peId, "CMR_CS_CASEID", elID);
		}

	}

	public static String getSelectedText(String xpath) {
		return getText(Locator.XPATH, "//*[contains(@name, 'Staff Member')]" + xpath);
	}

	public MobileElement getExistingAssignment(String assineeName, String AssignmentTypeAndDate) {
		return findElement(By.xpath("//*[contains(@name, '" + assineeName
				+ "')]/following-sibling:: XCUIElementTypeStaticText[contains(@name, '" + AssignmentTypeAndDate
				+ "')]"));
	}

	public static String getExistingAssignmentDateType(String assignDateType, int index) {
		return findElement(By.xpath("(//*[contains(@name, '" + assignDateType
				+ "')]/preceding-sibling:: XCUIElementTypeStaticText)[" + index + "]")).getText().trim();
	}

	/** verify the new assignment is displayed on the chmassign dpf screen */
	public static void verifyExistingStaffAssignment(DBType dbType, String peID, String textToReplaceWith,
			String elId) {

		String uiStaffMembersFName = getStaffMembersFullName(0);
		String dbStaffMembersFName = getCreatedAssignment(dbType, ASSIGNEEs_FIRST_NAME, peID, textToReplaceWith);
		assertEquals("******PLEASE MAKE SURE STAFF MEMBER'S FIRST NAME IS CORRECT*******", dbStaffMembersFName,
				uiStaffMembersFName);

		String uiStaffMembersLName = getStaffMembersFullName(1);
		String dbStaffMembersLName = getCreatedAssignment(dbType, ASSIGNEEs_LAST_NAME, peID, textToReplaceWith);
		assertEquals("******PLEASE MAKE SURE STAFF MEMBER'S LAST NAME IS CORRECT*******", dbStaffMembersLName,
				uiStaffMembersLName);

		String uiAssignedDate = getAssignedDate();
		String uiAssignmentType = getAssignment();
		String dbAssignmentType = getCreatedAssignment(dbType, ASSIGNEES_CAV_DESCRIPTION, peID, textToReplaceWith);
		assertEquals("******PLEASE MAKE SURE ASSIGNMENT TYPE IS CORRECT*******", dbAssignmentType, uiAssignmentType);

		String uiAssignmentDueDate = getAssignmentDue();
		String dbAssignmentDueDate = changeDateFormat(
				getAllColumns(dbType,
						getID(ASSIGNMENT_DUE_DATE,
								getCreatedAssignment(dbType, ASSIGNEES_CHA_ID, peID, textToReplaceWith))),
				"yyyy-MM-dd", "MM/d/yyyy");
		assertEquals("******PLEASE MAKE SURE ASSIGNMENT DUE DATE IS CORRECT*******", dbAssignmentDueDate,
				uiAssignmentDueDate);

		String cha_id = getCreatedAssignment(dbType, ASSIGNEES_CHA_ID, peID, "CMR_CS_CASEID");

		contains(uiAssignmentType).click();
		removeExistingAssignmentType(dbType, elId, 1, uiAssignmentType);

		getElementNextToDropDown("Assigned", uiAssignedDate);
		selectADate(3);

		getElementNextToDropDown("Assignment Due", dbAssignmentDueDate);
		selectADate(2);

		getElementNextToDropDown("Assignment Completed", "Select Date");
		selectADate(1);

		contains("Apply").click();
		contains(uiStaffMembersFName).click();

		String assignmenType = getExistingAssignmentDateType("Assignment", 1);
		String assignmentDueDate = getExistingAssignmentDateType("Assignment Due", 1);
		String assignmentCompletedDate = getExistingAssignmentDateType("Assignment Completed", 1);

		contains("Apply").click();
		contains("Submit").click();
		contains(yesBTN).click();
		contains(okBTN).click();

		String uiAssignmenType = getAllColumns(dbType, getText(CAV_CODE, assignmenType));
		String dbAssignmenType = getAllColumns(dbType, getID(CHA_CAV_CODE, cha_id));
		assertEquals("******ASSIGNMENT TYPE ISN'T MODIFIED CORRECTLY*****", dbAssignmenType, uiAssignmenType);

		String dbAssignmentCompletedDate = changeDateFormat(getAllColumns(dbType, getID(CHC_DATE_END, cha_id)),
				"yyyy-MM-dd", "MM/d/yyyy");
		assertEquals("******ASSIGNMENT COMPLETED DATE ERROR*****", dbAssignmentCompletedDate, assignmentCompletedDate);

		String dbAssignmentDue = changeDateFormat(getAllColumns(dbType, getID(CHD_DATE, cha_id)), "yyyy-MM-dd",
				"MM/d/yyyy");
		assertEquals("*****ASSIGNMENT DUE DATE ERROR******", dbAssignmentDue, assignmentDueDate);

	}

	public static void removeExistingAssignmentType(DBType dbType, String elId, int index, String text) {
		List<String> uiAssignmenType = new ArrayList<>();

		List<MobileElement> allAssignmenTypes = driver.findElements(
				By.xpath("//XCUIElementTypeTable[@name='OptionList']/XCUIElementTypeCell/XCUIElementTypeStaticText"));
		for (MobileElement type : allAssignmenTypes) {
			uiAssignmenType.add(type.getText().trim());
			sort(uiAssignmenType);
		}
		try {
			String assignmentType = getParameter(getAllColumns(dbType, getID(MBR_NOTE, elId)), 1);
			if (assignmentType.equals("SKIP")) {
				List<String> dbAssignmentTypeSKIP = executeQuery(dbType, ASSIGNMENT_TYPE_IS_SKIP);
				removeText(dbAssignmentTypeSKIP, text, uiAssignmenType);

			} else {
				List<String> dbAssignmentTypeColonDelimitedList = executeQuery(dbType, getText(
						ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST, "'" + assignmentType.replaceAll(":", "','") + "'"));
				removeText(dbAssignmentTypeColonDelimitedList, text, uiAssignmenType);
			}

			clickOnRandomValue(allAssignmenTypes);
		} catch (Exception e) {
			e.getMessage();

		}
	}

	/** Verify the records are created in CM/ECF */
	public void getCreatedRecords(DBType dbType, String expected, String actual) {
		String CMECF_TABLES = getAllColumns(dbType, actual);
		assertEquals("********PLEASE VERIFY THAT RECORDS IN CMECF ARE CREATED CORRECTLY!!!********", expected,
				CMECF_TABLES);

	}

	public static String getCreatedAssignment(DBType dbType, String query, String peId, String textToReplaceWith) {
		// String latestCase = getAllColumns(dbType, LATEST_CREATED_CASE);
		return getAllColumns(dbType, replace(getID(query, peId), textToReplaceWith, "82226"));

	}

	public void clickOnExistingAssignment(String AssignName, String AssignType, DBType dbType, String elID) {
		selectAction(dbType, "Actions", elID);
		getExistingAssignment(AssignName, AssignType).click();
	}

	public static void removeText(List<String> dbAssignmentType, String text, List<String> uiAssignmenType) {
		if (dbAssignmentType.contains(text)) {
			dbAssignmentType.remove(text);
		}
		assertEquals("*********ASSIGNMENT TYPE VALIDATION ERROR!!!*********", dbAssignmentType, uiAssignmenType);
	}

}
