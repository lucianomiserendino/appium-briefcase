package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;

import static gov.uscourts.ao.mobileBriefcase.common.Constants.*;

import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

public class Helper {

	public static String selectReferralCategory(Actions action) {

		String xpath = "//XCUIElementTypeTable[@name='Categories']/XCUIElementTypeCell";

		switch (action) {
		case PENDING_TASKS:
			xpath += "[1]";
			break;
		case PETITIONS_FOR_REHEARING:
			xpath += "[2]";
			break;

		case CASES_ON_CALENDAR:
			xpath += "[3]";
			break;
		case MOTIONS_PETITIONS:
			xpath += "[4]";
			break;
		case SCREENING_PANELS:
			xpath += "[5]";
			break;
		default:
			break;
		}
		return xpath;

	}

	public static String locateElement(String element) {
		return "//*[contains(@name, '" + element + "')]";
	}

	public static boolean panelIsDisplayed(String element) {
		return isDisplayed(By.xpath(locateElement(element)));

	}

	public static void clickOnPanel(String element) {
		if (panelIsDisplayed(element) == true)
			;
		click(locateElement(element));
	}

	public static String getVoteInformation(Actions action, int index) {

		String filer = "//XCUIElementTypeTable[@name='DocumentList']/XCUIElementTypeCell[" + index
				+ "]/XCUIElementTypeStaticText";

		switch (action) {
		case SMC:
			filer += "[1]";
			break;

		case RLW:
			filer += "[2]";
			break;

		case RWG:
			filer += "[3]";
			break;

		case FILERs_INFORMATION:
			filer += "[4]";
			break;
		default:
			break;
		}
		return filer;
	}

	public static List<String> getColumnCount(Actions judgeInitial, int init, Actions judgeVote, int vote,
			Actions voteDate, int date) {

		List<String> voteInf = new ArrayList<>();
		String smc = voteInformationPanel(judgeInitial, init);
		String rlw = voteInformationPanel(judgeVote, vote);
		String rwg = voteInformationPanel(voteDate, date);
		voteInf.add(smc);
		voteInf.add(rlw);
		voteInf.add(rwg);
		sort(voteInf);
		return voteInf;

	}

	public static String voteInformationPanel(Actions initial, int index) {
		return findElement(By.xpath(getVoteInformation(initial, index))).getText();
	}

	public static List<String> getCategories(String name, int startRow, int endRow, int cellIndex) {

		List<String> uiCategories = new ArrayList<>();

		for (int i = startRow; i <= endRow; ++i) {

			String actions = driver.findElement(By.xpath("//XCUIElementTypeTable[@name='" + name
					+ "']/XCUIElementTypeCell[" + i + "]/XCUIElementTypeStaticText[" + cellIndex + "]")).getText()
					.trim();
			uiCategories.add(actions);
			sort(uiCategories);
		}
		return uiCategories;

	}

	public static List<String> getReferralCategories() {
		List<String> variables = new ArrayList<>();
		variables.add(ANDERS_CASES);
		variables.add(IFP_MOTION_IN_THIS_COURT);
		variables.add(NO_ARGUMENT_REFERRALS);
		variables.add(PRO_SE_REFS);
		variables.add(SUMMARY_DISPOSITION);
		variables.add(UNASSIGNED_REFERRALS);
		return variables;

	}

	public enum Actions {

		SORT_DATES_IN_ASCENDING_ORDER, SORT_DATES_IN_DESCENDING_ORDER, SORT_CASES_IN_ASCENDING_ORDER, SORT_CASES_IN_DESCENDING_ORDER,

		/** Referral categories */
		PENDING_TASKS, PETITIONS_FOR_REHEARING, CASES_ON_CALENDAR, MOTIONS_PETITIONS, SCREENING_PANELS,

		/** Panels */
		ASSIGNMENTS, VOTE_INFORMATION, ACTIONS, JUDGMENT_FILED, PETITION_FILED,

		/** Judge's initials */
		SMC, RLW, RWG,

		FILERs_INFORMATION
	}
}
