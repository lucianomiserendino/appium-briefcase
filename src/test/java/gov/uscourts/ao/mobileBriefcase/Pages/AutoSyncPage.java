package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCasePage.findWebElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.sleep;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import io.appium.java_client.MobileElement;
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
	public static MobileElement plusIcon;

	@iOSBy(xpath = "//*[contains(@name, 'Available for download')]")
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
		Actions.tap(Locator.XPATH, "Sync all documents for case");

		assertTrue(isDisplayed);
		
		try {
			Page.performPageLoad(driver);
			MobileElement element = (MobileElement) driver
					.findElementByIosNsPredicate("label == \"\"");
			
			if (element.isDisplayed()& element.getText().equals("Downloaded"));

				isDisplayed = true;

		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}
	
	


	
	
	
	
	
	
	
	
	
	
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}

}
