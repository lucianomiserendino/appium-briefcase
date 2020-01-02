package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getCode;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILED_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILERS_INOFRMATION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGEs_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGEs_VOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.RELIEF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.VOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.changeDateFormat;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;

public class VoteInformationPage extends AppiumPageFactory {

	public static String dbFiledDate(DBType dbType, String pe_id, String caseId, String cyv_code) {
		return getAllColumns(dbType, getCode(getText(getID(FILED_DATE, pe_id), caseId), cyv_code));

	}

	/**
	 * For each referral, observe the filer's name (pr_last_name + , + pr_first_name
	 * + first initial of pr_middle_name + , + gn_display) party type
	 * (pt_description) and date filed (de_date_filed) displays in a light blue
	 * heading. The SQL below returns the filer information for the referral in case
	 * 15-3314:
	 */

	public boolean filersInfo(DBType dbtype, String peId, String caseId, String cyvCode, String ccr_id) {
		boolean isDisplayed = false;
		List<String> judgesInitials = executeQuery(dbtype, getID(JUDGEs_INITIALS, ccr_id));
		sort(judgesInitials);
		try {
			for (int i = 0; i < judgesInitials.size(); ++i) {
				String filerInfo = getFilerInfo(dbtype, peId, caseId, cyvCode);

				MobileElement uiResult = findElementBy(Locator.XPATH,
						"//*[contains(@name, 'Vote Information')]/following:: XCUIElementTypeStaticText[contains(@name, '"
								+ filerInfo + "')]/following::XCUIElementTypeStaticText[contains(@name, '"
								+ judgesInitials.get(i) + "')]");

				if (uiResult.isDisplayed())
					isDisplayed = true;
			}
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public static String getVoteInofrmation(String filersInfo, DBType dbType, String peId, String caseId,
			String cyvCode) {
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

		return getAllColumns(dbType,
				getCode(getText(getID(replace(FILERS_INOFRMATION, "FIELD", string), peId), caseId), cyvCode));

	}

	public static String getFilerInfo(DBType dbType, String peId, String caseId, String cyvCode) {
		String LastName = getVoteInofrmation("LastName", dbType, peId, caseId, cyvCode);
		String FirstName = getVoteInofrmation("FirstName", dbType, peId, caseId, cyvCode);
		String MiddleName = getVoteInofrmation("MiddleName", dbType, peId, caseId, cyvCode);
		String pt_display = getVoteInofrmation("pt_display", dbType, peId, caseId, cyvCode);
		String voteInfoDbFiledDate = dbFiledDate(dbType, peId, caseId, cyvCode);
		String gn_display = getVoteInofrmation("gn_display", dbType, peId, caseId, cyvCode);
		return LastName + ", " + FirstName + " " + MiddleName + " " + gn_display + "(" + pt_display + ") " + "Filed: "
				+ changeDateFormat(voteInfoDbFiledDate, "yyyy-MM-dd", "MM/dd/yyyy");

	}

	public void getJudesVote(DBType dbType, String ccr_id) {
		List<String> cvv_display = new ArrayList<>();
		String noVote = "";
		String uiJudesVote = "";
		String chv_date_created = "";
		String relief = getAllColumns(dbType, getID(RELIEF, ccr_id));
		List<String> inits = executeQuery(dbType, getID(JUDGEs_INITIALS, ccr_id));
		for (int i = 0; i < inits.size(); i++) {

			String date = getAllColumns(dbType, getID(replace(VOTE_DATE, "JU_INITIALS", inits.get(i)), ccr_id));
			String vote = getAllColumns(dbType, getID(replace(JUDGEs_VOTE, "CHV_DATE_CREATED", date), ccr_id));

			if (date.equals("")) {
				noVote = date += "No Vote";
				cvv_display.add(noVote);
			} else {
				chv_date_created = changeDateFormat(date.split(" ")[0], "yyyy-MM-dd", "M/dd/yyyy");
				uiJudesVote += "//XCUIElementTypeStaticText[@name='" + relief
						+ "']/following::XCUIElementTypeStaticText[@name='" + vote
						+ "']/following::XCUIElementTypeStaticText[@name='" + chv_date_created + "']";
			}
		}
		assertTrue("VERIFY THE VOTE DATE (CHV_DATE_CREATED) IS CORRECT",
				findElementBy(Locator.XPATH, uiJudesVote).isDisplayed());

		assertTrue("CVV_DISPLAY IS NULL FOR A JUDGE, BUT THE TEXT 'NO VOTE' DOEN'T DISPLAY UNDER THE JUDGE'S INITIALS",
				findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[@name='" + relief
						+ "']/following::XCUIElementTypeStaticText[@name='" + noVote + "']").isDisplayed());

	}

	public enum FILERs_INFO {
		VOTE_INFO_FILLRES_INFORMATION, JUDGE_VOTE_FILLRES_INFORMATION, FILED_DATE, UI_FILER_INFORMATION, DB_FILER_INFORMATION, UI_FILED_DATE, JUDGE_VOTE_RELIEF

	}

}
