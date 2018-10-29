package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Base.getInstance;
import static gov.uscourts.ao.mobileBriefcase.common.Base.webDriver;
import static gov.uscourts.ao.mobileBriefcase.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Page.sleep;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfWebElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findWebElement;
import static gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities.ATTORNEY_FILLING_URL;
import static gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities.ATTORNEY_FIRST_NAME;
import static gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities.ATTORNEY_LAST_NAME;
import static gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities.CMECF_HELP_DESK_UTILITY;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Base.Drivers;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_AutoSyncPage {

	public iOS_AutoSyncPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	private static String PACERUser = "//*[text()='PACER User']";
	private static String currentSetting = "//div[@id='current']";
	private static String documentFilingSystem = "CM/ECF Document Filing System";
	private static String userName = "loginName";
	private static String password = "password";
	private static String loginBTN = "fbtnLogin";
	private static String checkBox = "//*[contains(@class, 'ckboxlCol1')]";
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
		if (elementIsDisplayed("Available for download")) {
			availableDocs += getText("Available for download").split(",")[1].trim().split(" ")[0];
		}
		return availableDocs;
	}

	/**
	 * Use the CM/ECF Help Desk utility on cmka to change your user type from court
	 * user to public user
	 */
	public static void getCMECFHelpDeskUtility(String element) {
		getUrl(CMECF_HELP_DESK_UTILITY);
		findWebElement(By.xpath(element)).click();
		String currentSettingText = findWebElement(By.xpath(currentSetting)).getText();
		assertTrue("*********USER TYPE WASN'T CHANGED FROM COURT USER TO PUBLIC USER***********",
				currentSettingText.equals("PACER User - CSO ID required"));
	}

	
	public static void getUrl(String url) {
		getInstance(Drivers.WEBRIVER);
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
		containsName(userName).sendKeys(getProperty(ATTORNEY_FIRST_NAME));
		containsName(password).sendKeys(getProperty(ATTORNEY_LAST_NAME));
		containsName(loginBTN).click();
		click(By.xpath(checkBox));
		click(By.xpath(continueBTN));

	}

	public static void enterCaseNumber(String caseNum) {
		click(By.linkText(attorneyFillingLink));
		sendKeys(By.id(caseNumberFiled), caseNum);
		click(By.xpath(eventName));
		click(By.xpath(continueFillingPage));
	}

	public static void enterDescriptionAndselectPDFDoc() {
		sendKeys(By.xpath(descriptionField), "Attorney Documnet");
		click(By.xpath(browseBTN));
		//have to complete this step
		click(By.xpath(filer));
		click(By.xpath(continueUploadPage));
		click(By.xpath(continueUploadPage));
		click(By.xpath("Submit"));
	}

	public static void click(By by) {
		findWebElement(waitForPresenceOfWebElement(by)).click();
		sleep(1200);
	}

	public static void sendKeys(By by, String text) {
		findWebElement(waitForPresenceOfWebElement(by)).sendKeys(text);
	}

	public static WebElement containsName(String name) {
		return findWebElement(waitForPresenceOfWebElement(By.xpath("//*[contains(@name, '" + name + "')]")));
	}

}
