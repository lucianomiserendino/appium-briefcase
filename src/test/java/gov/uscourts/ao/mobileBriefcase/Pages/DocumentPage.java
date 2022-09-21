package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DocumentPage extends Base {

	public DocumentPage() {
		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> regCaseNum;

	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, 'Actions')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static List<WebElement> docCategories;

	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<WebElement> GroupIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Applied Referrals')]")
	public static List<WebElement> appliedRefs;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'linked')]/preceding::XCUIElementTypeStaticText[@name='Viewed'][1]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static List<WebElement> targetCase;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Applied Referrals']/following::XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> appliedCase;

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

	public static List<String> getDocumentCategories() {

		List<String> categories = new ArrayList<>();

		for (int i = 0; i < docCategories().size(); i++) {
			categories.add(docCategories().get(i).getText());
		}
		String docName = "";
		if (categories.size() >= 1) {
			docName = categories.get(0);
			contains(categories.get(0)).click();
		} else {

			int randomDoc = Utility.getRandomNumberInRange(1, categories.size() - 1);
			docName = categories.get(randomDoc).trim();
			contains(docName).click();
		}

		int randomDoc = getRandomDocument(docName);

		click(randomDoc, docName);

		return categories;
	}

	public static List<WebElement> getDocName(String text, String categoryName) {
		int index;
		if (text.equals("docCategory")) {
			index = 1;
		} else {
			index = 2;
		}
		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + categoryName
				+ "']/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther["
				+ index + "]/XCUIElementTypeStaticText"));
	}

	public static int getSize(String categoryName) {

		return getDocName("docCategory", categoryName).size();
	}

	public static int getRandomDocument(String categoryName) {
		return Utility.getRandomInt(getSize(categoryName));
	}

	public static String getText(String text, int i, String categoryName) {
		return getDocName(text, categoryName).get(i).getText();
	}

	public static void click(int index, String categoryName) {
		getDocName("docCategory", categoryName).get(index).click();
	}

	public static List<WebElement> docCategories() {
		String pane = "";
		if (appliedRefs.size() > 0) {
			pane = "Applied Referrals";
		} else {
			pane = "Actions";
		}
		return Actions.findElements(By.xpath(
				"//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, '"
						+ pane + "')]"
						+ "/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText"));
	}

	public String selectRandomCaseNumber(List<WebElement> element) {
		String referral = "";
		Page.performPageLoad(driver);
		List<String> list = Utility.retrieveAllReferrals(element, " ", 0);
		WebElement uiResult = findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '"
				+ list.get(Utility.getRandomInt(list.size() - 1)) + "')]");
		referral = uiResult.getText();
		uiResult.click();
		Page.performPageLoad(driver);
		return referral;

	}
	public static void getAppliedCase() {
		CommonPages.getPanel(Panel.Applied_Referrals);
	}
	public String getRandomCase(Category cat) {

		List<WebElement> element = null;

		switch (cat) {
		case RegularCase:
			element = regCaseNum;
			break;

		case TargetCase:
			element = targetCase;
			break;

		case AppliedCase:
			CommonPages.getGroupIcons();
			getAppliedCase();
			element = appliedCase;
			break;

		default:
			break;
		}
		return selectRandomCaseNumber(element);
	}

	public enum Category {
		Referral_Category, Referral, Panel, RegularCase, TargetCase, AppliedCase
	}

}
