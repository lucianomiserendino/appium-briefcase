package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHM_SILENT_ASSIGN_DPF;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getParameter;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup.Variables;
import gov.uscourts.ao.mobileBriefcase.stepDefinitions.Document_StepDefinitions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class A_chmsilentAssignPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Assignments']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<WebElement> assignTable;

	public static void getChmAssignDpf(List<UserInputData> userInputData) {
		List<String> uiAssignments = new ArrayList<>();
		String el_list_text = "";
		String el_functions = "";

		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();

		List<String> col1 = findchmsilentAssignDPF(userInputData, 2);
		List<String> col2 = findchmsilentAssignDPF(userInputData, 3);

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

	public static List<String> findchmsilentAssignDPF(List<UserInputData> userInputData, int col) {
		String cmr_id = CommonPages.getCMRID(Document_StepDefinitions.regularCase, userInputData);

		return execute(getID(CHM_SILENT_ASSIGN_DPF, cmr_id), col, userInputData);
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

}
