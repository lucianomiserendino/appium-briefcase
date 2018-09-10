package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE_IS_SKIP;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_MEMBERS_FIRST_NAME;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanelText;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ASSIGNMENTS;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
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

	public iOS_CreateStaffAssignmentsPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	String yesBTN = "Yes";
	String okBTN = "OK";

	public void getElementNextToDropDown(String element, String dropDowN) {
		click(getDropDown(element, dropDowN));

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
			assertEquals("*******************STAFF MEMBERS VALIDATION ERROR!!!*******************", dbStafMembers,
					uiStaffMembers);

			selectAssignment(allStaffMembers);

		} catch (AssertionError e) {
			e.getMessage();
		}

	}

	public void getAssignmentType(DBType dbType, String elId) {

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

				assertEquals("*******************ASSIGNMENT TYPE VALIDATION ERROR!!!*******************",
						dbAssignmentTypeSKIP, uiAssignmenType);

			} else {
				List<String> dbAssignmentTypeColonDelimitedList = executeQuery(dbType, getText(
						ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST, "'" + assignmentType.replaceAll(":", "','") + "'"));
				sort(dbAssignmentTypeColonDelimitedList);

				assertEquals("*******************ASSIGNMENT TYPE VALIDATION ERROR!!!*******************",
						dbAssignmentTypeColonDelimitedList, uiAssignmenType);
			}
			selectAssignment(allAssignmenTypes);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void selectADate(int index) {
		List<MobileElement> date = driver.findElements(By.xpath(
				"//XCUIElementTypeApplication[@name='Briefcase [Test]']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther"));
		date.get(date.size() - index).click();

	}

	public void selectAssignment(List<MobileElement> list) {
		if (list.size() > 1) {
			list.get(1).click();
		} else {
			list.get(0).click();

		}
	}

	public String getDropDown(String listOfStaff, String dropDowName) {
		return locateElement(
				listOfStaff + "')]/preceding-sibling:: XCUIElementTypeStaticText[contains(@name, '" + dropDowName + "");
	}

	public String getSelectedText(String xpath) {
		return findElementAndGetText(By.xpath("//*[contains(@name, 'Staff Member')]" + xpath));
	}

	public String getSelectedAssignment(String assignment) {
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

	public String getCreatedAssignmentNameAndAssignmentType(String staffMember, String assignmnetType) {
		return findElementAndGetText(By.xpath("//*[contains(@name, '" + staffMember + ", " + assignmnetType + "')]"));

	}

	public String getCreatedAssignmentDueDate(String staffMember, String assignmnetType) {
		return findElementAndGetText(By.xpath("//*[contains(@name, '" + staffMember + ", " + assignmnetType
				+ "')]/following-sibling:: XCUIElementTypeStaticText[1]"));

	}

	public void getTextOfSelectedOption(String apply, String submit, DBType dbType, String peId) {
		String staffMember = getSelectedAssignment("Staff Member");
		String satffMembersFName = splitBy(getSelectedAssignment("Staff Member"), 0);
		String satffMembersLName = splitBy(getSelectedAssignment("Staff Member"), 1);
		String assignment = getSelectedAssignment("Assignment");
		clickOnElement(apply);
		String createdAssignmentNamechmAssignPage = getCreatedAssignmentNameAndAssignmentType(staffMember, assignment);
		String createdAssignmentTypechmAssignPage = getCreatedAssignmentDueDate(staffMember, assignment);

		clickOnElement(submit);
		clickOnElement(yesBTN);
		clickOnElement(okBTN);
		getPanel(ACTIONS);
		assertEquals(createdAssignmentNamechmAssignPage, getPanelText(ASSIGNMENTS, createdAssignmentNamechmAssignPage));
		assertEquals(createdAssignmentTypechmAssignPage, getPanelText(ASSIGNMENTS, createdAssignmentTypechmAssignPage));

		getPanel(ASSIGNMENTS);

		String peID = getAllColumns(DBType.CMKA, getID("SELECT first 1 pe_id\n"
				+ "FROM group inner join member on gp_id = mb_gp_id_parent \n"
				+ "join personrole on pe_pr_prid = mb_ur_pr_prid \n" + "join person on pe_pr_prid = pr_prid \n"
				+ "join user on ur_pr_prid = pr_prid \n" + "where  pr_first_name='" + satffMembersFName
				+ "' and pr_last_name='" + satffMembersLName
				+ "' and   gp_id in (select gp_id from group inner join member on gp_id = mb_gp_id_parent join person on pr_prid = mb_ur_pr_prid \n"
				+ "join personrole on pe_pr_prid = pr_prid where pe_id = '?' and gp_name like '%Chambers%') and pe_date_end is null and pr_prid <> \n"
				+ "(select pr_prid from personrole join person on pe_pr_prid = pr_prid where pe_id = '?' and ur_date_disabled is null ) order by pe_date_created desc",
				peId));

		String dbAssignmentType = getAllColumns(dbType, "SELECT cav_code  FROM chm_assign_type_val WHERE cav_display='"
				+ assignment + "' and cav_chm_role in ('staff', 'all') and cav_date_end is null ORDER BY cav_display");

		List<String> ui = executeQuery(dbType, getID(
				"select first 1 cha_id,cha_date_created from chambers_assignment where cha_ju_pe_id = ? and cha_assigner_ju_pe_id = ?  and "
						+ "cha_chm_pe_id = '" + peID + "' and cha_cav_code = '" + dbAssignmentType
						+ "' order by cha_date_created desc",
				peId));

		List<String> db = executeQuery(dbType, getID(
				"select first 1 cha_id,cha_date_created  from chambers_assignment where cha_ju_pe_id = ? and cha_assigner_ju_pe_id =? order by cha_date_created desc",
				peId));
		assertEquals(db, ui);

	}

	public static String replaceTo(String oldText, String newText) {
		return oldText.replace(oldText, newText);

	}

}
