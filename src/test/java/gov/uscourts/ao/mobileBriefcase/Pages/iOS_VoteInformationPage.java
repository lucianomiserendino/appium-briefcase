package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getCode;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILED_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILERS_INOFRMATION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILERS_MIDDLE_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGEs_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.RELIEF;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.changeDateFormat;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;

public class iOS_VoteInformationPage extends AppiumPageFactory {

	/** Verify the Vote Information Panel displays */
	public void getVoteInformationPanel(DBType dbType, String element) {
		tap(Locator.XPATH, containsElement(element));

	}

	/**
	 * For each referral, observe the filer's name (pr_last_name + , + pr_first_name
	 * + first initial of pr_middle_name + , + gn_display) party type
	 * (pt_description) and date filed (de_date_filed) displays in a light blue
	 * heading. The SQL below returns the filer information for the referral in case
	 * 15-3314:
	 */
	public static List<String> returnTheFillerInformation(DBType dbType, String field, String cmr_cs_caseid,
			String cmr_ju_pe_id, String cmr_cyv_code) {
		return executeQuery(dbType, replace(replace(Queries.FILLERs_INFORMATION, "FIELD", field), "CMR_CS_CASEID",
				cmr_cs_caseid, "CMR_JU_PE_ID", cmr_ju_pe_id, "CMR_CYV_CODE", cmr_cyv_code));
	}

	/** Observers filer's information */

	public void getFilersInformation(FILERs_INFO info, DBType dbType, String pe_id, String caseId, String cyv_code,
			String ccr_id) {
		switch (info) {
		case VOTE_INFO_FILLRES_INFORMATION:

			String voteInfoUifilerinformation = uiFilerInformation(FILERs_INFO.UI_FILER_INFORMATION, dbType, pe_id,
					caseId, cyv_code);
			String voteInfoDbFilerInformation = dbFilerInformation(FILERs_INFO.DB_FILER_INFORMATION, dbType, pe_id,
					caseId, cyv_code);

			assertEquals("FILER's INFO MISMATCH", voteInfoDbFilerInformation, voteInfoUifilerinformation);

			String voteInfoUiFiledDate = uiFilerInformation(FILERs_INFO.UI_FILED_DATE, dbType, pe_id, caseId, cyv_code);
			String voteInfoDbFiledDate = dbFiledDate(dbType, pe_id, caseId, cyv_code);

			assertEquals("FILED DATE MISMATCH", voteInfoDbFiledDate, voteInfoUiFiledDate);

			break;
		case VOTE_INFO_RELIEF:

			String voteInfoUiRelief = getVoteInf(Panel.Vote_Information, getAllColumns(dbType, getID(RELIEF, ccr_id)));
			String voteInfoDBRelief = getAllColumns(dbType, getID(RELIEF, ccr_id));

			assertEquals("RELIEF MISMATCH", voteInfoDBRelief, voteInfoUiRelief);

			break;

		case JUDGE_VOTE_FILLRES_INFORMATION:
			String judgeVoteUifilerinformation = uiFilerInformatiONJudgeVotePage(FILERs_INFO.UI_FILER_INFORMATION,
					dbType, pe_id, caseId, cyv_code);

			String judgeVoteDbfilerinformation = dbFilerInformation(FILERs_INFO.DB_FILER_INFORMATION, dbType, pe_id,
					caseId, cyv_code);
			assertEquals("VOTE INFORMATION MISMATCH ON JUDGE VOTE DPF PAGE", judgeVoteDbfilerinformation,
					judgeVoteUifilerinformation);

			String judgeVoteUiFiledDate = uiFilerInformatiONJudgeVotePage(FILERs_INFO.UI_FILED_DATE, dbType, pe_id,
					caseId, cyv_code);
			String judgeVoteDbFiledDate = dbFiledDate(dbType, pe_id, caseId, cyv_code);

			assertEquals("FILED DATE MISMATCH", judgeVoteDbFiledDate, judgeVoteUiFiledDate);

			break;

		case JUDGE_VOTE_RELIEF:

			reliefsAreDisplayed(dbType, getID(RELIEF, ccr_id));

			break;

		default:
			break;
		}
	}

	public static void reliefsAreDisplayed(DBType dbtype, String query) {

		List<String> dbResult = executeQuery(dbtype, query);
		sort(dbResult);
		try {
			for (int i = 0; i < dbResult.size(); ++i) {

				MobileElement uiResult = findElementBy(Locator.XPATH, "[contains(@name, '" + dbResult.get(i) + "')]");

				if (uiResult.isDisplayed()) {

					assertTrue(uiResult.isDisplayed());
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public String uiFilerInformation(FILERs_INFO info, DBType dbType, String pe_id, String caseId, String cyv_code) {
		return filersInfo(info, getVoteInf(Panel.Vote_Information,
				getAllColumns(dbType, getCode(getText(getID(FILERS_MIDDLE_NAME, pe_id), caseId), cyv_code))));

	}

	public String dbFilerInformation(FILERs_INFO info, DBType dbType, String pe_id, String caseId, String cyv_code) {
		return filersInfo(info,
				(getAllColumns(dbType, getCode(getText(getID(FILERS_INOFRMATION, pe_id), caseId), cyv_code))));

	}

	public String dbFiledDate(DBType dbType, String pe_id, String caseId, String cyv_code) {
		return getAllColumns(dbType, getCode(getText(getID(FILED_DATE, pe_id), caseId), cyv_code));

	}

	public void getJudgeInitials(DBType dbType, String ccr_id) {
		assertTrue(exist(dbType, getID(JUDGEs_INITIALS, ccr_id),
				"//*[contains(@name, 'Vote Information')]/following:: XCUIElementTypeStaticText"));

	}

	public String filersInfo(FILERs_INFO info, String filersInfo) {

		String information = "";
		switch (info) {
		case UI_FILER_INFORMATION:
			information += filersInfo.replace("(", "").replace(")", "").replaceAll(",", "").split("Filed")[0]
					.replaceAll(" ", "");
			break;
		case DB_FILER_INFORMATION:
			information += filersInfo.replaceAll(" ", "");
		default:
			break;
		case UI_FILED_DATE:
			information += changeDateFormat(filersInfo.split("Filed:")[1].trim(), "MM/dd/yyyy", "yyyy-MM-dd");
			break;
		}
		return information;

	}

	public static String getVoteInf(Panel panel, String voteInfo) {
		String info = "";

		if (voteInfoIsDisplayed(voteInfo) == true) {
			info += getVoteInfoText(voteInfo);
		} else {
			try {
				getPanel(panel);
				info += getVoteInfoText(voteInfo);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}

		}
		return info;

	}

	public static String getVoteInfoText(String element) {
		return findElementBy(Locator.XPATH,
				"//*[contains(@name, 'Vote Information')]/following:: XCUIElementTypeStaticText[contains(@name, '"
						+ element + "')]").getText();

	}

	public static boolean voteInfoIsDisplayed(String element) {
		return isDisplayed(Locator.XPATH,
				"//*[contains(@name, 'Vote Information')]/following:: XCUIElementTypeStaticText[contains(@name, '"
						+ element + "')]");
	}

	/** verify it contains the judgeVote DPF */

	public void verifyElementsAreDisplayed(String action, String enterVote, String label) {
		// clickOnPanel(ACTIONS, action);
		assertElementIsDisplayed(action);
		assertElementIsDisplayed(enterVote);
		assertElementIsDisplayed(label);
		assertTrue(getLabel(label).isDisplayed() == true);

	}

	public void assertElementIsDisplayed(String element) {
		assertTrue(isDisplayed(Locator.XPATH, element) == true);
	}

	public static MobileElement getLabel(String toggle) {
		return findElementBy(Locator.XPATH, containsElement(toggle) + "//following-sibling::XCUIElementTypeSwitch");
	}

	public String uiFilerInformatiONJudgeVotePage(FILERs_INFO info, DBType dbType, String pe_id, String caseId,
			String cyv_code) {
		return filersInfo(info,
				getAllColumns(dbType, getCode(getText(getID(FILERS_MIDDLE_NAME, pe_id), caseId), cyv_code)));

	}

	public static boolean exist(DBType dbtype, String query, String xpath) {
		boolean isDisplayed = false;
		List<String> dbResult = executeQuery(dbtype, query);
		sort(dbResult);
		try {
			for (int i = 0; i < dbResult.size(); ++i) {

				MobileElement uiResult = findElementBy(Locator.XPATH,
						xpath + "[contains(@name, '" + dbResult.get(i) + "')]");

				if (uiResult.isDisplayed())
					isDisplayed = true;
			}
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public enum FILERs_INFO {
		VOTE_INFO_FILLRES_INFORMATION, JUDGE_VOTE_FILLRES_INFORMATION, FILED_DATE, UI_FILER_INFORMATION, DB_FILER_INFORMATION, UI_FILED_DATE, VOTE_INFO_RELIEF, JUDGE_VOTE_RELIEF, JUDGE_INITIALS
	}

}
