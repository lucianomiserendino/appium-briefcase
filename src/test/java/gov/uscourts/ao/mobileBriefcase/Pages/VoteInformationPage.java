package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getCode;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILERS_INOFRMATION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGEs_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.RELIEF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.VOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class VoteInformationPage extends AppiumPageFactory {

	@iOSXCUITFindBy(id = "Close")
	public static WebElement close;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Vote Information')]/following::XCUIElementTypeStaticText[1]")
	public static WebElement filler;

	/**
	 * For each referral, observe the filer's name (pr_last_name + , + pr_first_name
	 * + first initial of pr_middle_name + , + gn_display) party type
	 * (pt_description) and date filed (de_date_filed) displays in a light blue
	 * heading. The SQL below returns the filer information for the referral in case
	 * :
	 */

	public boolean filersInfo(FILERs_INFO info, String peId, String caseId, String cyvCode, String ccr_id,
			List<UserInputData> userInputData) {
		boolean isDisplayed = false;
		List<String> judgesInitials = executeQuery(getID(JUDGEs_INITIALS, ccr_id), userInputData);
		sort(judgesInitials);

		WebElement uiResult = null;
		String filerInfo = getFilerInfo(peId, caseId, cyvCode, userInputData);
		
		String xpath = "";
		if (!filler.getText().contains(",")) {
			xpath = filerInfo.replace(",", "");

		}else {
			xpath = filerInfo;
		}

		try {
			switch (info) {
			case VOTE_INFO_FILLRES_INFORMATION:
				for (int i = 0; i < judgesInitials.size(); ++i) {

		
					uiResult = findElementBy(Locator.XPATH,
							"//*[contains(@name, 'Vote Information')]/following::XCUIElementTypeStaticText[contains(@name, '"
									+ xpath + "')]/following::XCUIElementTypeStaticText[contains(@name, '"+judgesInitials.get(i)+"')]");


				}
				break;
			case JUDGE_VOTE_DPF_FILLRES_INFORMATION:

				uiResult = findElementBy(Locator.XPATH,
						"//XCUIElementTypeStaticText[contains(@name, '" + xpath + "')]");
				break;
			default:
				break;
			}

			if (uiResult != null && uiResult.isDisplayed())
				isDisplayed = true;

		} catch (Exception e) {
			isDisplayed = false;
		}

		return isDisplayed;

	}

	public static String getVoteInofrmation(String filersInfo, String peId, String caseId, String cyvCode,
			List<UserInputData> userInputData) {
		String string = "";

		String replaced = "";

		switch (filersInfo) {
		case "FirstName":
			string = "pr_first_name";
			replaced = " ";
			break;
		case "LastName":
			string = "pr_last_name";
			replaced = ", ";
			break;
		case "MiddleName":
			string = "pr_middle_name";
			replaced = " ";
			break;
		case "gn_display":
			string = "gn_display";
			break;

		case "pt_display":
			string = "pt_display";

			break;

		case "de_date_filed":
			string = "de_date_filed";

			break;

		default:
			break;
		}

		String k = "";

		String a = getAllColumns(
				getCode(getText(getID(replace(FILERS_INOFRMATION, "FIELD", string), peId), caseId), cyvCode),
				userInputData).trim();

		if (a.equals("null")) {
			return "";
		} else {

			if (string.equals("de_date_filed")) {
				k = changeDateFormat(a, "yyyy-MM-dd", "M/d/yyyy");
			} else {
				k = a;
			}
			if (string.equals("pt_display"))
				k = "(" + a + ") ";

			return k + replaced;
		}
	}

	public static String getFilerInfo(String peId, String caseId, String cyvCode, List<UserInputData> userInputData) {
		String LastName = getVoteInofrmation("LastName", peId, caseId, cyvCode, userInputData);
		String FirstName = getVoteInofrmation("FirstName", peId, caseId, cyvCode, userInputData);
		String MiddleName = getVoteInofrmation("MiddleName", peId, caseId, cyvCode, userInputData);
		String pt_display = getVoteInofrmation("pt_display", peId, caseId, cyvCode, userInputData);
		String voteInfoDbFiledDate = getVoteInofrmation("de_date_filed", peId, caseId, cyvCode, userInputData);
		String gn_display = getVoteInofrmation("gn_display", peId, caseId, cyvCode, userInputData);

		return LastName + FirstName + MiddleName + gn_display + pt_display + "Filed: " + voteInfoDbFiledDate;

	}

	public void getJudesVote(FILERs_INFO info, String ccr_id, List<UserInputData> userInputData) {
		List<String> cvv_display = new ArrayList<>();
		String noVote = "";
		List<String> uiJudesVote = null;
		// String chv_date_created = "";
		String relief = getAllColumns(getID(RELIEF, ccr_id), userInputData);

		List<String> inits = executeQuery(getID(JUDGEs_INITIALS, ccr_id), userInputData);

		for (int i = 0; i < inits.size(); i++) {

			String date = getAllColumns(getID(replace(VOTE_DATE, "JU_INITIALS", inits.get(i)), ccr_id), userInputData);

			if (date.equals("null")) {
				noVote = date = "No Vote";
				cvv_display.add(noVote);

			} else {
				String vote = getAllColumns(
						getID(replace(Queries.JUDGEs_VOTE, "CHV_DATE_CREATED", date, "JU_INITIALS", inits.get(i)),
								ccr_id),
						userInputData).trim();

				String chv_date_created = changeDateFormat(date.split(" ")[0], "yyyy-MM-dd", "M/d/yyyy");

				uiJudesVote.add(

						"//XCUIElementTypeStaticText[contains(@name, '" + chv_date_created + "')]/"
								+ "preceding::XCUIElementTypeStaticText[contains(@name, '" + vote
								+ "')]/preceding::XCUIElementTypeStaticText" + "[contains(@name, '" + relief + "')]");
			}
		}

		switch (info) {
		case VOTE_INFO_FILLRES_INFORMATION:

			for (int i = 0; i < uiJudesVote.size(); i++) {
				System.out.println(uiJudesVote.get(i) + "**********************************");

				assertTrue("VERIFY THE VOTE DATE (CHV_DATE_CREATED) IS CORRECT",
						findElementBy(Locator.XPATH, uiJudesVote.get(i)).isDisplayed());
			}

			assertTrue(
					"CVV_DISPLAY IS NULL FOR A JUDGE, BUT THE TEXT 'NO VOTE' DOEN'T DISPLAY UNDER THE JUDGE'S INITIALS",
					findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[@name='" + relief
							+ "']/following::XCUIElementTypeStaticText[@name='" + noVote + "']").isDisplayed());
			break;

		case JUDGE_VOTE_DPF_FILLRES_INFORMATION:
			for (int i = 0; i < uiJudesVote.size(); i++) {
				System.out.println(uiJudesVote.get(i) + "**********************************");

				assertTrue("VERIFY THE VOTE DATE (CHV_DATE_CREATED) IS CORRECT",
						findElementBy(Locator.XPATH, uiJudesVote.get(i)).isDisplayed());
			}
			break;
		default:
			break;
		}
	}

	public static void getJudgeInitials(String panel, String caseNum, String peId, String cmr_cyv_code,
			List<UserInputData> userInputData) {
		String cmr_ccr_id = getCMRID("cmr_ccr_id", caseNum, peId, cmr_cyv_code, userInputData);
		WebElement uiInits = null;

		String judgeInitials = getPANEL_MEMBERS(cmr_ccr_id, userInputData);
		ArrayList<String> dbInitials = new ArrayList<>(Arrays.asList(judgeInitials.split(", ")));

		int k = 0;

		ArrayList<String> uiJudgeInitials = new ArrayList<>();

		if (panel.equals("Vote_Information")) {
			k += 2;
		} else {
			k += 1;
		}

		for (int i = k; i < dbInitials.size() + k; ++i) {

			uiInits = findElementBy(Locator.XPATH, initials(panel, i));
			uiJudgeInitials.add(uiInits.getText().trim());

		}

		assertEquals("JUDGES NOT LISTED IN SENIORITY ORDER", dbInitials, uiJudgeInitials);
		if (panel.equals("viewVotes")) {
			tap(close);
		}

	}

	public static List<WebElement> getVoteInfoTable() {
		return Actions.findElements(By.xpath(judgeInitial()));

	}

	public static String judgeInitial() {
		return "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther"
				+ "/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther";
	}

	public static String initials(String panel, int index) {
		if (panel.equals("Vote_Information")) {
			return "//XCUIElementTypeStaticText[@name=\"Vote Information\"]/following:: XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther["
					+ index + "]/XCUIElementTypeStaticText";
		} else {
			return "//XCUIElementTypeOther[@name='JudgesVotesList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]"
					+ "/XCUIElementTypeOther[" + index
					+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText";
		}
	}

	public static String getPANEL_MEMBERS(String cmr_ccr_id, List<UserInputData> userInputData) {
		String pj_judge_order = Queries.by_PJ_JUDGE_ORDER;
		String ju_seniority_sort = Queries.by_JU_SENIORITY_SORT;

		String cmr_panel_members = getCMR_PANEL_MEMBERS(pj_judge_order, cmr_ccr_id, userInputData);
		if (!cmr_panel_members.equals("null")) {

			return cmr_panel_members;

		} else {

			return getCMR_PANEL_MEMBERS(ju_seniority_sort, cmr_ccr_id, userInputData);
		}
	}

	public static String getCMR_PANEL_MEMBERS(String field, String cmr_ccr_id, List<UserInputData> userInputData) {
		return getAllColumns(Actions.replace(field, "CMR_CCR_ID", cmr_ccr_id), userInputData);
	}

	public enum FILERs_INFO {
		VOTE_INFO_FILLRES_INFORMATION, JUDGE_VOTE_DPF_FILLRES_INFORMATION, FILED_DATE, UI_FILER_INFORMATION,
		DB_FILER_INFORMATION, UI_FILED_DATE, JUDGE_VOTE_RELIEF

	}

}
