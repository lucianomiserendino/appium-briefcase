package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.*;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
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

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name=\"Progress halted\"]")
	public static List<WebElement> cancelSync;

	public static void getChmAssign(SyncType page) {
		switch (page) {

		case Dashboard:
			syncingWithDashboard();
			dashboardSyncBtn.click();
			Page.performPageLoad(driver);
			performPageLoad(driver);
			ifDownloaded(cancelSync);
			getSyncCount();

			break;

		case Referral_Category:
			categroySyncBtn.click();
			ifDownloaded(cancelSync);

			break;

		case Case_Detail:
			caseSyncBtn.click();

			break;

		default:
			break;
		}

	}

	/** deletes all documents the documents from the device */
	public static String syncingWithDashboard() {

		if (contains("Dashboard").isDisplayed()) {
			contains("Dashboard").click();
			Page.sleep(5000);
			settingsIcon.click();
			Page.performPageLoad(driver);
			deleteAllDocuments.click();
			if (Actions.isDisplayed(deleteMessage)) {
				deletBtn.click();
				Page.performPageLoad(driver);
			}
		}
		Actions.navigateBack();
		return getSyncCount();
	}

	

	/**
	 * Gets the number of new documents from the counter on the sync button
	 */
	public static String getSyncCount() {
		WebElement el = Actions.findElement(By.xpath(containsElement("Available for download")));
		return getText(el).split(",")[1].trim().split(" ")[0];

	}

	public enum SyncType {
		Dashboard, Referral_Category, Case_Detail;

	}

}
