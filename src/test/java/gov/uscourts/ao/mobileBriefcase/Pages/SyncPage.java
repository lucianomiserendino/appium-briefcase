package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage.Category;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
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

	@iOSXCUITFindBy(accessibility = "PDF View")
	public static WebElement pdfView;

	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, 'Actions')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static List<WebElement> docCategories;

	public static void getSync(SyncType page, List<UserInputData> userInputData) {
		DocumentPage docPage = new DocumentPage();

		SyncPage page1 = new SyncPage();

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

			docPage.selectRandomJudgeCategory(userInputData);

			page1.categroySyncBtn.click();
			ifDownloaded(page1.activityIndicator);

			docPage.getRandomCase(Category.judgeRegularCase);

			break;

		case Case_Detail:

			docPage.selectRandomJudgeCategory(userInputData);
			docPage.getRandomCase(Category.judgeRegularCase);

			Utility.doubleTap(page1.caseSyncBtn);
			ifDownloaded(page1.activityIndicator);

			List<String> categories = getDocumentCategories();

			int randomDoc = Utility.getRandomNumberInRange(1, categories.size() - 1);
			 categories.get(randomDoc).trim();

			ifViewed();

			break;

		default:
			break;
		}

	}

	public static List<String> getDocumentCategories() {

		getGroupIcons();
		Page.performPageLoad(driver);
		List<String> categories = new ArrayList<>();

		for (int i = 0; i < docCategories.size(); i++) {
			categories.add(docCategories.get(i).getText());
		}
		return categories;
	}

	public static String viewSyncResults() {
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

	public static void ifViewed() {
		selectRandomPage();
		pdfView.click();
		close.click();
		assertTrue(Actions.isDisplayed(Downloaded));

	}

	public static void selectRandomPage() {
		int pageNum = Integer.parseInt(splitBy(1));
		int lastViewPage = Integer.parseInt(splitBy(0));
		int randomNum;

		if (pageNum == lastViewPage) {
			randomNum = Utility.getRandomNumberInRange(1, pageNum);
			Utility.swipe(randomNum, "left");
		} else {
			randomNum = Utility.getRandomNumberInRange(lastViewPage, pageNum);
			Utility.swipe(randomNum, "right");
		}
	}

	public static String splitBy(int index) {
		AccessingAnnotatedDocuments p = new AccessingAnnotatedDocuments();
		return p.pageNumber.getText().split(" of ")[index];
	}

	public static void getLastViewedPage(int randomNum, int splitBy, String expectedPageNum, String categoryName) {
		DocumentPage.click(randomNum, categoryName);
		String actualPageNum = splitBy(splitBy);
		Assert.assertEquals(expectedPageNum, actualPageNum);
	}

	public enum SyncType {
		Dashboard, Referral_Category, Case_Detail;

	}

}
