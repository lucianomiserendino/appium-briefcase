package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCasePage.findWebElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.sleep;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AutoSyncPage extends AppiumPageFactory {

	private static String PACERUser = "//*[text()='PACER User']";
	private static String currentSetting = "//div[@id='current']";
	private static String documentFilingSystem = "CM/ECF Document Filing System";
	private static String userName = "//input[@id='loginForm:userName' or @id='login:loginName' or @id='loginForm:loginName']";
	private static String password = "//input[@id='login:password' or @id='loginForm:password']";
	private static String loginBTN = "//button/span[contains(text(),'Login')]";
	private static String checkBox = "span[class='ui-chkbox-icon ui-icon ui-icon-blank ui-c']";
	private static String continueBTN = "//div[@id='regmsg:pgroup']/button[@id='regmsg:bpmConfirm']";
	private static String attorneyFillingLink = "Filing";
	private static String eventName = "//table[@role='grid']/tbody/tr/td/span[text()='AO - brief filed ']";
	private static String continueFillingPage = "//button[@id='mainForm:docketBtn']";
	private static String descriptionField = "//input[@role='textbox']";
	private static String caseNumberFiled = "mainForm:caseNumber";
	private static String browseBTN = "//span[@role='button']";
	private static String filer = "(//*[text()='Filer']/following::div/table[@role='grid']/tbody/tr/td)[1]";
	private static String continueUploadPage = "//*[text()='Continue']";
	private static String syncAllDocs = "//*[text()='Sync all documents for case']";
	private static String downloaded = "//XCUIElementTypeStaticText[@name='Downloaded]";

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/preceding::XCUIElementTypeStaticText[@name='+']")
	public static WebElement plusIcon;

	@iOSBy(xpath = "//*[contains(@name, 'Available for download')]")
	public static WebElement docsAvailableForDownload;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Proposed Orders']/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> proposedOrder;

	@iOSXCUITFindBy(accessibility = "PDF View")
	public static WebElement pdfView;

	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, 'Actions')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static List<WebElement> docCategories;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Progress halted' or @name='In progress']")
	public static List<WebElement> activityIndicator;
	/**
	 * Get number of new documents from the counter on the sync button
	 * 
	 * @return
	 */
	public static String getCounter() {
		ifDownloaded(activityIndicator);
		WebElement el = Actions.findElement(By.xpath(containsElement("Available for download")));
		return getText(el).split(",")[1].trim().split(" ")[0];

	}

	/**
	 * Use the CM/ECF Help Desk utility on cmka to change your user type from court
	 * user to public user
	 */
	public static void getCMECFHelpDeskUtility(String element) {
		getUrl(CMECF_HELP_DESK_UTILITY);
		Page.sleep(10000);
		findWebElement(By.xpath(PACERUser)).click();
		String currentSettingText = findWebElement(By.xpath(currentSetting)).getText();
		assertTrue("*********USER TYPE WASN'T CHANGED FROM COURT USER TO PUBLIC USER***********",
				currentSettingText.equals("PACER User - CSO ID required"));
	}

	public static void getUrl(String url) {
		getInstance(Driver.WEBRIVER);
		webDriver.get(getProperty(url));
	}

	/**
	 * Go to the attorney filing URL and login, Click the CM/ECF Document Filing
	 * System link and enter the user name and password on the next screen (leave
	 * the Client Code field blank). Accept the Redaction Agreement and click
	 * Continue
	 */
	public static void getAttorneyFillingURL() {
		getCMECFHelpDeskUtility(PACERUser);
		webDriver.get(getProperty(ATTORNEY_FILLING_URL));
		findWebElement(By.linkText(documentFilingSystem)).click();
		Page.sleep(10000);
		findWebElement(By.xpath(userName)).sendKeys(getProperty(ATTORNEY_FIRST_NAME));
		findWebElement(By.xpath(password)).sendKeys(getProperty(ATTORNEY_LAST_NAME));
		findWebElement(By.xpath(loginBTN)).click();
		Page.sleep(10000);
		click(By.cssSelector(checkBox));
		click(By.xpath(continueBTN));
		click(By.linkText(attorneyFillingLink));
	}

	public static void enterCaseNumber(String caseNum) {
		Page.sleep(10000);
		sendKeys(By.id(caseNumberFiled), caseNum);
		click(By.xpath(eventName));
		click(By.xpath(continueFillingPage));
	}

	public static void enterDescriptionAndselectPDFDoc() {
		sendKeys(By.xpath(descriptionField), "Attorney Documnet");
		click(By.xpath(browseBTN));
		click(By.xpath(filer));
		click(By.xpath(continueUploadPage));
		click(By.xpath(continueUploadPage));
		click(By.xpath("Submit"));
	}

	public static void click(By by) {
		findWebElement(by).click();
		sleep(1200);
	}

	public static void sendKeys(By by, String text) {
		findWebElement(by).sendKeys(text);
	}

	public static WebElement containsName(String name) {
		return findWebElement(By.xpath(containsElement("//*[contains(@name, '" + name + "')]")));
	}

	public boolean getReferralSync() {
		boolean isDisplayed = false;
		Page.performPageLoad(driver);

		Actions.tap(Locator.XPATH, containsElement("Sync all documents for case"));

		try {
			CommonPages.getPanel(Panel.Proposed_Orders);
			if (Actions.isDisplayed(Locator.XPATH, "(//XCUIElementTypeStaticText[@name='Downloaded'])[1]"))
				;

			isDisplayed = true;

		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public List<String> getDocumentCategories() {

		getGroupIcons(GroupIcons.Expand);
		Page.performPageLoad(driver);
		List<String> categories = new ArrayList<>();

		for (int i = 0; i < docCategories.size(); i++) {
			categories.add(docCategories.get(i).getText());
		}
		return categories;
	}

	public static void getSyncCount(int syncCount) {
		
		AccessingAnnotatedDocuments a = new AccessingAnnotatedDocuments();
		List<String> categories = a.getDocumentCategories();

		int randomDoc = Utility.getRandomNumberInRange(1, categories.size() - 1);
		String docName = categories.get(randomDoc).trim();
		contains(docName).click();

		Page.performPageLoad(driver);

		assertTrue(Actions.isDisplayed(pdfView));
		close.click();
		dashboard.click();
		assertTrue(syncCount == syncCount - 1);

	}

}
