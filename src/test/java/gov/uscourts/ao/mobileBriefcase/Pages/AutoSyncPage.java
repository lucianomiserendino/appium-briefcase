package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
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
