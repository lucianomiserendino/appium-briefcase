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
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHAMBERS_ASSIGNMENT;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHAMBERS_ASSIGN_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHA_CAV_CODE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHC_DATE_END;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHD_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHM_ASSIGN_TO_CASE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.LATEST_CREATED_CASE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_MEMBERS_FIRST_NAME;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages.clickOnPanel;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages.getActionName;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElementAndGetText;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElements;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getParameter;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.splitBy;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class iOS_CreateStaffAssignmentsPage {

	static String yesBTN = "Yes";
	static String okBTN = "OK";
	static String backBTN = "Back";

	public iOS_CreateStaffAssignmentsPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static void getElementNextToDropDown(String element, String dropDowN) {
		click(getDropDown(element, dropDowN));

	}

	public static String getDropDown(String listOfStaff, String dropDowName) {
		return locateElement(
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
		return screenParam;

	}

	/** get a list of staff based on the screen parameter in the DPF */
	public void getStaffMembers(DBType dbtype, String staffMember, String peID, String screenTypeParam, int index) {

		List<String> dbStafMembers = executeQuery(dbtype, getText(getID(staffMember, peID), screenTypeParam));
		sort(dbStafMembers);
		try {

			List<String> uiStaffMembers = new ArrayList<>();
			List<MobileElement> allStaffMembers = findElements(By
					.xpath("//XCUIElementTypeTable[@name='OptionList']/XCUIElementTypeCell/XCUIElementTypeStaticText"));
			for (MobileElement staffMembers : allStaffMembers) {
				uiStaffMembers.add(staffMembers.getText().split(" ")[index]);
				sort(uiStaffMembers);
			}
			assertEquals("********STAFF MEMBERS VALIDATION ERROR!!!********", dbStafMembers, uiStaffMembers);
			selectAssignment(allStaffMembers, 1);

		} catch (AssertionError e) {
			e.getMessage();
		}
	}

	public static void selectAssignment(List<MobileElement> list, int index) {
		if (list.size() > 1) {

			list.get(list.size() - index).click();
		} else {
			list.get(0).click();
		}
	}

	/** verify assignment types based on assignment types parameter */
	public static void getAssignmentType(DBType dbType, String elId, int index) {

		List<String> uiAssignmenType = new ArrayList<>();
		try {
			List<MobileElement> allAssignmenTypes = findElements(By
					.xpath("//XCUIElementTypeTable[@name='OptionList']/XCUIElementTypeCell/XCUIElementTypeStaticText"));
			for (MobileElement type : allAssignmenTypes) {
				uiAssignmenType.add(type.getText().trim());
				sort(uiAssignmenType);
			}

			String assignmentType = getParameter(getAllColumns(dbType, getID(MBR_NOTE, elId)), 1);
			if (assignmentType.equals("SKIP")) {
				List<String> dbAssignmentTypeSKIP = executeQuery(dbType, ASSIGNMENT_TYPE_IS_SKIP);
				sort(dbAssignmentTypeSKIP);
				assertEquals("*********ASSIGNMENT TYPE VALIDATION ERROR!!!*********", dbAssignmentTypeSKIP,
						uiAssignmenType);
			} else {
				List<String> dbAssignmentTypeColonDelimitedList = executeQuery(dbType, getText(
						ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST, "'" + assignmentType.replaceAll(":", "','") + "'"));
				sort(dbAssignmentTypeColonDelimitedList);
				assertEquals("*********ASSIGNMENT TYPE VALIDATION ERROR!!!*********",
						dbAssignmentTypeColonDelimitedList, uiAssignmenType);
			}
			selectAssignment(allAssignmenTypes, index);
		} catch (Exception e) {
			e.getMessage();
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
		return findElementAndGetText(By.xpath("//*[contains(@name, '" + staffMember + ", " + assignmnetType + "')]"));

	}

	public String getCreatedAssignmentType(String staffMember, String assignmnetType) {
		return findElementAndGetText(By.xpath("//*[contains(@name, '" + staffMember + ", " + assignmnetType
				+ "')]/following-sibling:: XCUIElementTypeStaticText[1]"));

	}

	public String getStaffMember() {
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
		clickOnElement(submit);
		clickOnElement(yes);
		clickOnElement(ok);
	}

	public void getExistingAssignment(String apply, String submit, DBType dbType, String peId, String elID) {
		try {
			String staffMember = getStaffMember();
			String satffMembersFName = getStaffMembersFullName(0);
			String satffMembersLName = getStaffMembersFullName(1);
			String assignment = getAssignment();

			clickOnElement(apply);

			String createdAssignmentNamechmAssignPage = getCreatedAssignmentNameAndAssignmentType(staffMember,
					assignment);
			String createdAssignmentTypechmAssignPage = getCreatedAssignmentType(staffMember, assignment);

			clickOn(submit, yesBTN, okBTN);
			String peID = getAllColumns(dbType, getID("SELECT first 1 pe_id\n"
					+ "FROM group inner join member on gp_id = mb_gp_id_parent \n"
					+ "join personrole on pe_pr_prid = mb_ur_pr_prid \n" + "join person on pe_pr_prid = pr_prid \n"
					+ "join user on ur_pr_prid = pr_prid \n" + "where  pr_first_name='" + satffMembersFName
					+ "' and pr_last_name='" + satffMembersLName
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

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static String getSelectedText(String xpath) {
		return findElementAndGetText(By.xpath("//*[contains(@name, 'Staff Member')]" + xpath));
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

		click(locateElement(uiAssignmentType));
		removeExistingAssignmentType(dbType, elId, 1, uiAssignmentType);

		getElementNextToDropDown("Assigned", uiAssignedDate);
		selectADate(3);

		getElementNextToDropDown("Assignment Due", dbAssignmentDueDate);
		selectADate(2);

		getElementNextToDropDown("Assignment Completed", "Select Date");
		selectADate(1);

		clickOnElement("Apply");

		click(locateElement(uiStaffMembersFName));

		String assignmenType = getExistingAssignmentDateType("Assignment", 1);
		String assignmentDueDate = getExistingAssignmentDateType("Assignment Due", 1);
		String assignmentCompletedDate = getExistingAssignmentDateType("Assignment Completed", 1);

		clickOnElement("Apply");
		clickOnElement("Submit");
		clickOnElement(yesBTN);
		clickOnElement(okBTN);

		String uiAssignmenType = getAllColumns(dbType, getText(CAV_CODE, assignmenType));
		String dbAssignmenType = getAllColumns(dbType, getID(CHA_CAV_CODE, cha_id));
		assertEquals("******ASSIGNMENT TYPE ISN'T MODIFIED CORRECTLY*****", dbAssignmenType, uiAssignmenType);

		String dbAssignmentCompletedDate = changeDateFormat(getAllColumns(dbType, getID(CHC_DATE_END, cha_id)),
				"yyyy-MM-dd", "MM/d/yyyy");
		assertEquals("******ASSIGNMENT COMPLETED DATE ERROR*****", dbAssignmentCompletedDate, assignmentCompletedDate);

		String dbAssignmentDue = changeDateFormat(getAllColumns(dbType, getID(CHD_DATE, cha_id)), "yyyy-MM-dd",
				"MM/d/yyyy");
		assertEquals("*****ASSIGNMENT DUE DATE ERROR******", dbAssignmentDue, assignmentDueDate);

		getPanel(ACTIONS);
	}

	public static void removeExistingAssignmentType(DBType dbType, String elId, int index, String text) {
		List<String> uiAssignmenType = new ArrayList<>();

		List<MobileElement> allAssignmenTypes = findElements(
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
			selectAssignment(allAssignmenTypes, index);
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
		String latestCase = getAllColumns(dbType, LATEST_CREATED_CASE);
		return getAllColumns(dbType, replace(getID(query, peId), textToReplaceWith, latestCase));

	}

	public void clickOnExistingAssignment(String AssignName, String AssignType, DBType dbType, String elID) {
		clickOnPanel(ACTIONS, getActionName(dbType, elID));
		getExistingAssignment(AssignName, AssignType).click();
	}

	public static String replace(String text, String oldText, String newText) {
		return text.replace(oldText, newText);
	}

	public static void removeText(List<String> dbAssignmentType, String text, List<String> uiAssignmenType) {
		if (dbAssignmentType.contains(text)) {
			dbAssignmentType.remove(text);
		}
		assertEquals("*********ASSIGNMENT TYPE VALIDATION ERROR!!!*********", dbAssignmentType, uiAssignmenType);
	}
	
	
	

}
