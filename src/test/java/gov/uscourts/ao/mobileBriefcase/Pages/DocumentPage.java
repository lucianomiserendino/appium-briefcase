package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static java.util.Collections.sort;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DocumentPage extends AppiumPageFactory {

	// @WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<MobileElement> caseNum;

	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	public static String selectRandomItem(String query, String xpath, List<UserInputData> userInputData) {
		String category = "";
		List<String> list = null;

		List<String> dbResult = executeQuery(query, userInputData);
		list = dbResult;
		if (list.contains("Reference Documents") | list.contains("Pending Tasks") | list.contains("Cases on Calendar"))
			list.remove("Reference Documents");
		list.remove("Pending Tasks");
		list.remove("Cases on Calendar");

		sort(list);

		MobileElement uiResult = findElementBy(Locator.XPATH,
				xpath + "[contains(@name, '" + list.get(Utility.getRandomInt(list.size() - 1)) + "')]");
		category = uiResult.getText();
		uiResult.click();
		return category;

	}

	public static void selectRandomSTFCategory(List<UserInputData> userInputData) {

		selectRandomItem(Actions.replace(Queries.SAs_ASSIGNMENT_CATEGORIES, "RA_PE_ID", "434"), xpath, userInputData);

	}

	public static String selectRandomJudgeCategory(List<UserInputData> userInputData) {

		String name = SystemPropertySetup.getJudge(userInputData);
		return selectRandomItem(getID(Queries.REFERRAL_CATEGORIES, getPE_ID("jud", name, userInputData)), xpath,
				userInputData);

	}

	public void selectRandomCaseNumber(List<UserInputData> userInputData) {
		Page.sleep(20000);
		List<String> list = Utility.retrieveAllReferrals(caseNum, " ", 0);
		MobileElement uiResult = findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '"
				+ list.get(Utility.getRandomInt(list.size() - 1)) + "')]");
		uiResult.click();

	}

	public enum Category {
		Referral_Category, Referral, Panel
	}

}
