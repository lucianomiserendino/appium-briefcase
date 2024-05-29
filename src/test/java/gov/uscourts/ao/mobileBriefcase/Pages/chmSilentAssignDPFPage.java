package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHM_SILENT_ASSIGN_DPF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getPanel;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getParameter;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.Assignment;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.chmAssign;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup.Variables;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class chmSilentAssignDPFPage extends AppiumPageFactory {
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> caseNum;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"PendingTasksList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[6]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]")
	public static WebElement assignmentType;

	@iOSXCUITFindBy(id = "My Assignments")
	public static WebElement MyAssignments;

	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<WebElement> GroupIcon;

	@iOSXCUITFindBy(accessibility = "//XCUIElementTypeStaticText[@name=\"Assignments\"]//following::XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> judgeAssignments;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='OptionList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<WebElement> optionList;

	@iOSXCUITFindBy(xpath = "label[id='settings-checkbox-1']")
	public static WebElement checkBox;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Assignments']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<WebElement> assignTable;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Alert']")
	public static List<WebElement>  alert;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Submit']")
	public static WebElement submit;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Yes']")
	public static List<WebElement> yes;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'View Case Info')]")
	public static WebElement viewCaseInfo;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Docket Entries ')]")
	public static WebElement docketEntries;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Panel:')]")
	public static WebElement panel;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'related to')]")
	public static WebElement relatedTo;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='OK']")
	public static WebElement ok;

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

	public static WebElement getExistingAssignment(String assineeName, String AssignmentTypeAndDate) {
		return findElement(By.xpath("//*[contains(@name, '" + assineeName
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + AssignmentTypeAndDate + "')]"));
	}

	public void getJudgeAssignment(List<UserInputData> userInputData, String assineeName,
			String AssignmentTypeAndDate) {
		MyAssignments.click();

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

		WebElement uiResult = findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '"
				+ list.get(Utility.getRandomInt(list.size() - 1)) + "')]");

		uiResult.click();

		boolean isDisplayed = false;

		try {
			WebElement el = getExistingAssignment(assineeName, AssignmentTypeAndDate);
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
		String cha_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData) ;

		chmAssignDPFPage.getStaffAssignment(Assignment.NEW, chmAssign.CREATE, dpfName, elId, cha_ju_pe_id, caseNumber,
				cmr_cyv_code, userInputData);

		assertTrue(getDuplicateAssignments(chmAssignDPFPage.optionList));
	}

	public boolean getDuplicateAssignments(List<WebElement> assignments) {

		String[] txt = new String[assignments.size()];
		int k = 0;

		for (WebElement a : assignments) {
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

	public static void getChmAssignDpf(List<UserInputData> userInputData) {
		List<String> uiAssignments = new ArrayList<>();
		String el_list_text = "";
		String el_functions = "";

		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();

		List<String> col1 = findchmsilentAssignDPF(userInputData, 2, "term");
		List<String> col2 = findchmsilentAssignDPF(userInputData, 3, "term");

		int size = col1.size();

		for (int i = 0; i < size; i++) {

			list1.add(col1.get(i).trim());
			list2.add(col2.get(i).trim());
		}

		for (int k = 0; k < size; k++) {

			if (list2.get(k).contains("judgeVote")) {
				el_list_text = list1.get(k);
				el_functions = list2.get(k);
				break;
			} else {
				el_list_text = list1.get(0);
				el_functions = list2.get(0);
			}

		}

		List<WebElement> uiJudgeList = assignTable;
		for (int i = 0; i < uiJudgeList.size(); i++) {
			uiAssignments.add(uiJudgeList.get(i).getText());
		}

		getJudgeAssignment(el_functions, userInputData, uiAssignments);

	}

	public static List<String> findchmsilentAssignDPF(List<UserInputData> userInputData, int col,
			String silentAssignType) {

		return execute(getID(CHM_SILENT_ASSIGN_DPF, silentAssignType), col,
				userInputData);
	}

	public static String getLoggedInJudge(List<UserInputData> userInputData) {
		return SystemPropertySetup.getVariable(Variables.JUD, userInputData);

	}

	public static void getJudgeAssignment(String dpf, List<UserInputData> userInputData, List<String> uiJudgeList) {

		String param = getParameter(dpf, "chmSilentAssign", 0);

		String loggedInJudge = getLoggedInJudge(userInputData);

		switch (param) {

		/**
		 * term - the assignment will be terminated based on the assignment type and
		 * involvement code parameters values for the logged in judge. 
		 */

		case "create":
			// getPanel(Panel.Assignments);

			// String judgeAssign = getRowFromTable(assignTable, mode, 4).getText();

//			Assert.assertTrue(Actions.isDisplayed(Locator.XPATH,
//					"//XCUIElementTypeStaticText[contains(@name, '" + judgeAssign + "')]"));
			break;

		case "term":
			uiJudgeList.remove(loggedInJudge);
			break;

		case "termPanel":
			// uiJudgeList.remove(dbJudgeList);
			break;

		case "termAnyRelief":
			Assert.assertTrue(getDPF(dpf));
			break;

		case "termAnyReliefPanel":
			Assert.assertTrue(getDPF(dpf));
			break;

		case "termAllRelief":

			Assert.assertTrue(getDPF(dpf));

			break;

		case "termAllReliefsPanel":
			Assert.assertTrue(getDPF(dpf));

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

	public void retrieveChmSilentAssignText(List<UserInputData> userInputData) {
	    String panelJudges = extractPanelJudges();
	    List<String> judgeInitials = findPanelJudges(panelJudges);

	    String createSilentAssignment = findchmsilentAssignDPF(userInputData, 3, "create").get(0).trim();

	    Utility.scrollDownIfNotDisplayed(Actions.containsElement("Actions"));
	    CommonPages.getActionName(createSilentAssignment);

	    handleAlerts();

	    Page.waitToBeClickable(viewCaseInfo, driver);
	    Page.waitToBeClickable(docketEntries, driver);

	    contains(createSilentAssignment).click();

	    verifyJudgeInitials(judgeInitials);
	}

	private String extractPanelJudges() {
	    String panelText = panel.getText();
	    String[] panelSplit = panelText.split(" Date");
	    return panelSplit[0].split("Panel: ")[1];
	}

	private void handleAlerts() {
	    Page.sleep(1000);
	    if (alert.size() >= 1) {
	        ok.click();
	    } else {
	        submit.click();
	        if (yes.size() >= 1) {
	            yes.get(0).click();
	        }
	    }
	}

	private void verifyJudgeInitials(List<String> judgeInitials) {
	    for (String judgeInitial : judgeInitials) {
	        judgeInitial = judgeInitial.contains("*") ? judgeInitial.replace("*", "").trim() : judgeInitial.trim();
	        assertTrue("Verify chmSilentAssignText TPF enables docket text information",
	                Actions.isDisplayed(Locator.XPATH, Actions.containsElement(judgeInitial + " related to")));
	    }
	}

	public static List<String> findPanelJudges(String panelJudges) {
	    String[] judgeNames = panelJudges.split(",");
	    return new ArrayList<>(Arrays.asList(judgeNames));
	}




	
	

}