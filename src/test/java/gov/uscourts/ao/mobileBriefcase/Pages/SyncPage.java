package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SyncPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Categories\"]/following::XCUIElementTypeButton")
	public static WebElement dashboardSyncBtn;

	@iOSXCUITFindBy(xpath = "//*[@name='ReferralsList' or @name='SessionGroups']/following::XCUIElementTypeButton")
	public static WebElement categroySyncBtn;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Sync all documents for case #')]")
	public static WebElement caseSyncBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[2]")
	public static WebElement settingsIcon;

	@iOSXCUITFindBy(id = "Delete all Briefcase Documents")
	public static WebElement deleteAllDocuments;

	@iOSXCUITFindBy(id = "Tap Delete to delete all documents")
	public static WebElement deleteMessage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Delete\"]")
	public static WebElement deletBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Progress halted' or @name='In progress']")
	public static List<WebElement> activityIndicator;

	@iOSXCUITFindBy(id = "Deleting all documents...")
	public static List<WebElement> deletingDoc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='View Sync Results']")
	public static WebElement viewSyncResults;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Retrieving pending referrals\"]")
	public static List<WebElement> retrievePendingRefs;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[1]")
	public static WebElement Downloaded;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[2]")
	public static WebElement Downloaded2;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[3]")
	public static WebElement Downloaded3;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[4]")
	public static WebElement Downloaded4;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[5]")
	public static WebElement Downloaded5;
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[6]")
	public static WebElement Downloaded6;

	public void getSync(SyncType page) {
		switch (page) {

		case Dashboard:
			// deleteAllDocuments();
			dashboardSyncBtn.click();
			ifDownloaded(retrievePendingRefs);
			ifDownloaded(activityIndicator);

			String dashboardPage = getSyncCount();
			String syncPage = viewSyncResults();
			assertEquals("SYNC COUNT MISMATCH: ", dashboardPage, syncPage);

			break;

		case Referral_Category:
			categroySyncBtn.click();
			ifDownloaded(activityIndicator);

			break;

		case Case_Detail:

			Utility.doubleTap(caseSyncBtn);
			ifDownloaded(activityIndicator);

			break;

		default:
			break;
		}

	}

	public String viewSyncResults() {
		settingsIcon.click();
		viewSyncResults.click();
		performPageLoad(driver);
		WebElement syncResults = Actions.findElement(By.xpath(containsElement("Sync Results")));
		return getText(syncResults).split("\\(")[1].split(" ")[0].trim();
	}

	/** deletes all the documents from the device */
	public static String deleteAllDocuments() {

		if (contains("Dashboard").isDisplayed()) {
			contains("Dashboard").click();
			Page.sleep(5000);
			settingsIcon.click();
			performPageLoad(driver);
			deleteAllDocuments.click();
			Actions.isDisplayed(deleteMessage);
			performPageLoad(driver);
			ifDownloaded(deletingDoc);
		}
		Actions.navigateBack();
		performPageLoad(driver);
		return getSyncCount();
	}

	/**
	 * Gets the number of new documents from the counter on the sync button
	 */
	public static String getSyncCount() {
		WebElement el = Actions.findElement(By.xpath(containsElement("Available for download")));
		return getText(el).split(",")[1].trim().split(" ")[0].trim();

	}

	public enum SyncType {
		Dashboard, Referral_Category, Case_Detail;

	}

}
