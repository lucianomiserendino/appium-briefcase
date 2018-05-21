package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

public class Panels {

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

	public static String getVoteInformation(Actions action,int index) {

		String filer = "//XCUIElementTypeTable[@name='DocumentList']/XCUIElementTypeCell["+index+"]/XCUIElementTypeStaticText";

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


	public static List<String> getJudgeInitials(int index) {
		List<String> judgeInitials = new ArrayList<>();
		String smc = voteInformationPanel(Actions.SMC,index);
		String rlw = voteInformationPanel(Actions.RLW,index);
		String rwg = voteInformationPanel(Actions.RWG,index);
		judgeInitials.add(smc);
		judgeInitials.add(rlw);
		judgeInitials.add(rwg);
		sort(judgeInitials);
		return judgeInitials;

	}


	public static String voteInformationPanel(Actions initial,int index) {
		return findElement(By.xpath(getVoteInformation(initial,index))).getText();
	}

}
