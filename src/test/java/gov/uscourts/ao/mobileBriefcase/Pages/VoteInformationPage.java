package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getCode;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILED_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILERS_INOFRMATION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGEs_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.RELIEF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.VOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.changeDateFormat;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.appium.java_client.MobileElement;

public class VoteInformationPage extends AppiumPageFactory {

	public static String dbFiledDate(String pe_id, String caseId, String cyv_code, List<UserInputData> userInputData) {
		return getAllColumns(getCode(getText(getID(FILED_DATE, pe_id), caseId), cyv_code), userInputData);
	}

	/**
	 * For each referral, observe the filer's name (pr_last_name + , + pr_first_name
	 * + first initial of pr_middle_name + , + gn_display) party type
	 * (pt_description) and date filed (de_date_filed) displays in a light blue
	 * heading. The SQL below returns the filer information for the referral in case
	 * 15-3314:
	 */


	public boolean filersInfo(FILERs_INFO info, String peId, String caseId, String cyvCode, String ccr_id,
			List<UserInputData> userInputData) {
		boolean isDisplayed = false;
		List<String> judgesInitials = executeQuery(getID(JUDGEs_INITIALS, ccr_id), userInputData);
		sort(judgesInitials);
		
		MobileElement uiResult = null;
		String filerInfo = getFilerInfo(peId, caseId, cyvCode, userInputData);

		try {
			switch (info) {
			case VOTE_INFO_FILLRES_INFORMATION:
				for (int i = 0; i < judgesInitials.size(); ++i) {

					uiResult = findElementBy(Locator.XPATH,
							"//*[contains(@name, 'Vote Information')]/following:: XCUIElementTypeStaticText[contains(@name, '"
									+ filerInfo + "')]/following::XCUIElementTypeStaticText[contains(@name, '"
									+ judgesInitials.get(i) + "')]");
				}
			case JUDGE_VOTE_DPF_FILLRES_INFORMATION:

				uiResult = findElementBy(Locator.XPATH,
						"//XCUIElementTypeStaticText[contains(@name, '" + filerInfo + "')]");
			default:
				break;
			}
			if (uiResult.isDisplayed())

				isDisplayed = true;

		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public static String getVoteInofrmation(String filersInfo, String peId, String caseId, String cyvCode,
			List<UserInputData> userInputData) {
		String string = "";
		switch (filersInfo) {
		case "FirstName":
			string = "pr_first_name";
			break;
		case "LastName":
			string = "pr_last_name";
			break;
		case "MiddleName":
			string = "pr_middle_name";
			break;
		case "gn_display":
			string = "gn_display";
			break;
		case "pt_display":
			string = "pt_display";
			break;
		default:
			break;
		}
		return getAllColumns(
				getCode(getText(getID(replace(FILERS_INOFRMATION, "FIELD", string), peId), caseId), cyvCode),
				userInputData);
	}

	public static String getFilerInfo(String peId, String caseId, String cyvCode, List<UserInputData> userInputData) {
		String LastName = getVoteInofrmation("LastName", peId, caseId, cyvCode, userInputData).trim();
		String FirstName = getVoteInofrmation("FirstName", peId, caseId, cyvCode, userInputData).trim();
		String MiddleName = getVoteInofrmation("MiddleName", peId, caseId, cyvCode, userInputData).trim();
		String pt_display = getVoteInofrmation("pt_display", peId, caseId, cyvCode, userInputData).trim();
		String voteInfoDbFiledDate = dbFiledDate(peId, caseId, cyvCode, userInputData).trim();
		String gn_display = getVoteInofrmation("gn_display", peId, caseId, cyvCode, userInputData).trim();

		return LastName + ", " + FirstName + " " + MiddleName + " " + gn_display + "(" + pt_display + ") " + "Filed: "
				+ changeDateFormat(voteInfoDbFiledDate, "yyyy-MM-dd", "MM/d/yyyy");

	}

	public void getJudesVote(FILERs_INFO info, String ccr_id, List<UserInputData> userInputData) {
		List<String> cvv_display = new ArrayList<>();
		String noVote = "";
		String uiJudesVote = "";
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

				uiJudesVote +=

						"//XCUIElementTypeStaticText[contains(@name, '" + chv_date_created + "')]/"
								+ "preceding::XCUIElementTypeStaticText[contains(@name, '" + vote
								+ "')]/preceding::XCUIElementTypeStaticText" + "[contains(@name, '" + relief + "')]";
			}
		}
		
		switch (info ) {
		case VOTE_INFO_FILLRES_INFORMATION:
			
			assertTrue("VERIFY THE VOTE DATE (CHV_DATE_CREATED) IS CORRECT",
					findElementBy(Locator.XPATH, uiJudesVote).isDisplayed());

			assertTrue("CVV_DISPLAY IS NULL FOR A JUDGE, BUT THE TEXT 'NO VOTE' DOEN'T DISPLAY UNDER THE JUDGE'S INITIALS",
					findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[@name='" + relief
							+ "']/following::XCUIElementTypeStaticText[@name='" + noVote + "']").isDisplayed());
		break;
		
		
		case JUDGE_VOTE_DPF_FILLRES_INFORMATION:
			assertTrue("VERIFY THE VOTE DATE (CHV_DATE_CREATED) IS CORRECT",
					findElementBy(Locator.XPATH, uiJudesVote).isDisplayed());
			break;
		default:
			break;
		}
	}
	

	
	
	
	
	

	public enum FILERs_INFO {
		VOTE_INFO_FILLRES_INFORMATION, JUDGE_VOTE_DPF_FILLRES_INFORMATION, FILED_DATE, UI_FILER_INFORMATION, DB_FILER_INFORMATION, UI_FILED_DATE, JUDGE_VOTE_RELIEF

	}

}
