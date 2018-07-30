package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getCyv_code;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILED_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILERS_INOFRMATION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILERS_MIDDLE_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGEs_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_EVENT;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.RELIEF;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.VOTE_INFORMATION;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCaseNumber;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class iOS_VoteInformationPanelPage {

	public iOS_VoteInformationPanelPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void selectCase(String category, String caseNum) {
		selectCaseNumber(category, caseNum);
	}

	/** Observe the Vote Information Panel displays */
	public void getVoteInformationPanel(DBType dbType, String element) {

		getPanel(dbType, MBR_EVENT, "VOTE INFORMATION PANEL IS NOT DISPLAYED", element);

	}

	/** Observers filer's information */

	public void getFilersInformation(FILERs_INFO info, DBType dbType, String pe_id, String caseId, String cyv_code,
			String ccr_id) {
		switch (info) {
		case FILLRES_INFORMATION:

			String uiFilerInformation = uiFilerInformation(FILERs_INFO.UI_FILER_INFORMATION, dbType, pe_id, caseId,
					cyv_code);
			String dbFilerInformation = dbFilerInformation(FILERs_INFO.DB_FILER_INFORMATION, dbType, pe_id, caseId,
					cyv_code);

			assertEquals("FILER's INFO MISMATCH", dbFilerInformation, uiFilerInformation);

			String uiFiledDate = uiFilerInformation(FILERs_INFO.UI_FILED_DATE, dbType, pe_id, caseId, cyv_code);
			String dbFiledDate = dbFiledDate(dbType, pe_id, caseId, cyv_code);

			assertEquals("FILED DATE MISMATCH", dbFiledDate, uiFiledDate);

			break;
		case RELIEF:

			String uiRelief = getVoteInf(VOTE_INFORMATION, getAllColumns(dbType, getID(RELIEF, ccr_id)));
			String dbRelief = getAllColumns(dbType, getID(RELIEF, ccr_id));

			assertEquals("RELIEF MISMATCH", dbRelief, uiRelief);
			break;

		default:
			break;
		}
	}

	public String uiFilerInformation(FILERs_INFO info, DBType dbType, String pe_id, String caseId, String cyv_code) {
		return filersInfo(info, getVoteInf(VOTE_INFORMATION,
				getAllColumns(dbType, getCyv_code(getText(getID(FILERS_MIDDLE_NAME, pe_id), caseId), cyv_code))));

	}

	public String dbFilerInformation(FILERs_INFO info, DBType dbType, String pe_id, String caseId, String cyv_code) {
		return filersInfo(info,
				(getAllColumns(dbType, getCyv_code(getText(getID(FILERS_INOFRMATION, pe_id), caseId), cyv_code))));

	}

	public String dbFiledDate(DBType dbType, String pe_id, String caseId, String cyv_code) {
		return getAllColumns(dbType, getCyv_code(getText(getID(FILED_DATE, pe_id), caseId), cyv_code));

	}

	public void getJudgeInitials(DBType dbType, String ccr_id) {
		isDisplayed(dbType, getID(JUDGEs_INITIALS, ccr_id),
				"//*[contains(@name, 'Vote Information')]/following:: XCUIElementTypeStaticText");

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
			information += changeDateFormat(filersInfo.split("Filed:")[1].trim(), "yyyy-MM-dd");
			break;
		}
		return information;

	}

	public static String getVoteInf(Actions panel, String voteInfo) {
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
		return findElement(By.xpath(
				"//*[contains(@name, 'Vote Information')]/following:: XCUIElementTypeStaticText[contains(@name, '"
						+ element + "')]")).getText();

	}

	public static boolean voteInfoIsDisplayed(String element) {
		return isDisplayed(By.xpath(
				"//*[contains(@name, 'Vote Information')]/following:: XCUIElementTypeStaticText[contains(@name, '"
						+ element + "')]"));
	}

	public enum FILERs_INFO {
		FILLRES_INFORMATION, FILED_DATE, UI_FILER_INFORMATION, DB_FILER_INFORMATION, UI_FILED_DATE, RELIEF, JUDGE_INITIALS
	}

}
