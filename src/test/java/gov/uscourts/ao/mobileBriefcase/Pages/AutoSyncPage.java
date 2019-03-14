package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase.findWebElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.common.Page.sleep;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

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

	@iOSFindBy(xpath = "//*[contains(@name, 'Available for download')]")
	public static MobileElement docsAvailableForDownload;

	/** Get number of new documents from the counter on the sync button */
	public String getCounter() {
		String availableDocs = "";
		if (isDisplayed(Locator.XPATH, containsElement("Available for download")) == true) {
			availableDocs += getText(Locator.XPATH,
					containsElement("Available for download").split(",")[1].trim().split(" ")[0]);
		}
		return availableDocs;
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
		// have to complete this step
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


}
