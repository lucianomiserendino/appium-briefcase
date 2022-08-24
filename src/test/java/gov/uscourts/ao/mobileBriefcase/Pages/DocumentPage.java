package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DocumentPage extends Base {

	public DocumentPage() {

		// initElements(new AppiumFieldDecorator(driver), this);

		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);

	}

	// @WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<MobileElement> caseNum;

	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	public static String selectRandomItem(String query, String xpath, List<UserInputData> userInputData) {
		String category = "";
		List<String> list = null;

		List<String> dbResult = executeQuery(query, userInputData);
		list = dbResult;
		if (list.contains("Reference Documents") | list.contains("Pending Tasks") | list.contains("Cases on Calendar")
				| list.contains("No Argument Case"))
			list.remove("Reference Documents");
		list.remove("Pending Tasks");
		list.remove("Cases on Calendar");
		list.remove("No Argument Case");

		sort(list);

		category = scrollDownIfNotDisplayed(
				xpath + "[contains(@name, '" + list.get(Utility.getRandomInt(list.size() - 1)) + "')]");

		return category;

	}

	public void selectRandomSTFCategory(List<UserInputData> userInputData) {

		selectRandomItem(Actions.replace(Queries.SAs_ASSIGNMENT_CATEGORIES, "RA_PE_ID", "434"), xpath, userInputData);

	}

	public String selectRandomJudgeCategory(List<UserInputData> userInputData) {

		String name = SystemPropertySetup.getJudge(userInputData);
		return selectRandomItem(getID(Queries.REFERRAL_CATEGORIES, getPE_ID("jud", name, userInputData)), xpath,
				userInputData);

	}

	public String selectRandomCaseNumber() {
		String referral = "";
		// Page.sleep(50000);
		Page.performPageLoad(driver);
		List<String> list = Utility.retrieveAllReferrals(caseNum, " ", 0);
		MobileElement uiResult = findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '"
				+ list.get(Utility.getRandomInt(list.size() - 1)) + "')]");
		referral = uiResult.getText();
		uiResult.click();
		Page.performPageLoad(driver);
		return referral;

	}

	public enum Category {
		Referral_Category, Referral, Panel
	}

}
