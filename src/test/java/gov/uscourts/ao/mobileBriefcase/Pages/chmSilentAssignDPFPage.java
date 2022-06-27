package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.Assignment;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.chmAssign;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup.Variables;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class chmSilentAssignDPFPage extends AppiumPageFactory {
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<MobileElement> caseNum;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"PendingTasksList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[6]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]")
	public static MobileElement assignmentType;

	@iOSXCUITFindBy(id = "My Assignments")
	public static MobileElement MyAssignments;

	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<MobileElement> GroupIcon;

	@iOSXCUITFindBy(accessibility = "//XCUIElementTypeStaticText[@name=\"Assignments\"]//following::XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<MobileElement> judgeAssignments;

	String actionName = "Auto Test";
	String cmr_cyv_code = "prhr";

	public void createChmSilentAssign(List<UserInputData> userInputData) {

		chmAssignDPFPage.submiTransaction();
		if (contains("Dashboard").isDisplayed())
			contains("Dashboard").click();

		String assignment = Utility.splitBy(assignmentType.getText(), 0);
		String assignmentDate = Utility.splitBy(assignmentType.getText(), 1);

		getJudgeAssignment(userInputData, assignment, assignmentDate);
	}

	public static MobileElement getExistingAssignment(String assineeName, String AssignmentTypeAndDate) {
		return findElement(By.xpath("//*[contains(@name, '" + assineeName
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + AssignmentTypeAndDate + "')]"));
	}

	public void getJudgeAssignment(List<UserInputData> userInputData, String assineeName,
			String AssignmentTypeAndDate) {
		PendingTasksPage.getPendingSubFolder("MyAssignments");

		for (int i = 1; i < GroupIcon.size() + 1; i++) {
			String groupIcon = "(//XCUIElementTypeStaticText[@name='GroupIcon'])[";
			while (findElements(
					By.xpath(groupIcon + i + "]/following::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]"))
					.size() == 0) {
				tap(Locator.XPATH, groupIcon + i + "]");
			}
		}

		List<String> list = Utility.retrieveAllReferrals(caseNum, " ", 0);

		Page.sleep(20000);

		MobileElement uiResult = findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '"
				+ list.get(Utility.getRandomInt(list.size() - 1)) + "')]");

		uiResult.click();

		boolean isDisplayed = false;

		try {
			MobileElement el = getExistingAssignment(assineeName, AssignmentTypeAndDate);
			if (el.isDisplayed())
				isDisplayed = true;
		} catch (WebDriverException e) {
			isDisplayed = false;
		}
		assertTrue(isDisplayed);

	}

	public void submitChmSilentAssign(String caseNumber, List<UserInputData> userInputData) {
		String dpfName = "chmAssign";
		String elId = getAllColumns(getID(Queries.EL_ID, actionName), userInputData);
		String name = getLoggedInJudge(userInputData);
		String cha_ju_pe_id = DBUtilities.getPE_ID("jud", name, userInputData);

		chmAssignDPFPage.createNewSTF(Assignment.NEW, chmAssign.CREATE, dpfName, elId, cha_ju_pe_id, caseNumber,
				cmr_cyv_code, userInputData);

		assertTrue(getDuplicateAssignments(chmAssignDPFPage.optionList));
	}

	public boolean getDuplicateAssignments(List<MobileElement> assignments) {

		String[] txt = new String[assignments.size()];
		int k = 0;

		for (MobileElement a : assignments) {
			txt[k] = a.getText();
			k++;
		}

		String[] array = { txt[k] };
		ArrayList<String> str = new ArrayList<String>();
		for (String s : array) {
			str.add(s);
		}
		boolean asn = false;
		for (int i = 0; i < array.length; i++) {
			str.remove(array[i]);
			for (int j = 0; j < str.size(); j++) {
				if (array[j].equals(str.get(j))) {
					System.out.println(str.get(j) + " " + array[j]);
					asn = true;
				}
			}
		}
		return asn;
	}

	public String getLoggedInJudge(List<UserInputData> userInputData) {
		return SystemPropertySetup.getVariable(Variables.JUD, userInputData);

	}

	public void getJudgeAssignment(String elID, List<UserInputData> userInputData) {
		List<String> uiJudgeList = new ArrayList<>();
		List<String> dbJudgeList = new ArrayList<>();
		String loggedInJudge = getLoggedInJudge(userInputData);
		String dpf = getAllColumns(getID(MBR_NOTE, elID), userInputData);
		String mode = Utility.getParameter(getAllColumns(getID(MBR_NOTE, elID), userInputData), "chmSilentAssign", 0);

		String param = "";

		switch (param) {

		/**
		 * term - the assignment will be terminated based on the assignment type and
		 * involvement code parameters values for the logged in judge. 
		 */
		case "term":
			uiJudgeList.remove(loggedInJudge);
			break;

		case "termPanel":
			uiJudgeList.remove(dbJudgeList);
			break;

		case "termAnyRelief":
			getDPF(dpf);
			break;

		case "termAnyReliefPanel":
			getDPF(dpf);
			break;

		case "termAllReliefs":
			getDPF(dpf);

			break;

		case "termAllReliefsPanel":
			getDPF(dpf);

			break;

		default:
			break;
		}
	}

	/**
	 * The following method checks if the parameter is set to termAnyRelief or
	 * termAllReliefs, it is used in conjunction with the judgeVote DPF.
	 */

	public static boolean getDPF(String param) {
		boolean isDisplayed = false;
		String vote = "judgeVote";
		String assign = "chmSilentAssign";
		List<String> dpf = new ArrayList<>();

		String[] items = param.split(";");
		int itemCount = items.length;

		if (itemCount > 1) {

			for (int i = 0; i < itemCount; i++) {

				dpf.add(items[i].split("\\('")[0].trim());
			}

			if (dpf.contains(vote) && dpf.contains(assign)) {

				assertTrue(dpf.indexOf(vote) < dpf.indexOf(assign));
				isDisplayed = true;

			} else {
				isDisplayed = false;
			}
		}

		return isDisplayed;

	}

}