package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeDBQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
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
	public static WebElement categorySyncBtn;

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
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static WebElement dashBoard;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Settings']")
	public static WebElement gearIcon;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Cancel Sync']")
	public static  List<WebElement>  cancelBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText")
	public static WebElement collapseBtn;
	
	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Downloaded_Container'])[1]")
	public static WebElement downloaded;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PDF Page View']")
	public static List<WebElement> pdfPageView;
	
	static String dashboardSyncCount ="";

	public void getSyncCountOnDashboard() {
		ifDownloaded(activityIndicator);
		 deleteAllDocuments();
		 dashboardSyncCount = getSyncCount();
		 System.out.println(dashboardSyncCount+"***********************");
	}

	public static void getSync(SyncType page, List<UserInputData> userInputData) {
		String dashboardPage ="";
		String syncPage ="";
		SyncPage page1 = new SyncPage();

		switch (page) {

		case Dashboard:
			ifDownloaded(activityIndicator);
			 deleteAllDocuments();
			dashboardSyncBtn.click();
			ifDownloaded(retrievePendingRefs);
			ifDownloaded(activityIndicator);

			 dashboardPage = getSyncCount();
			 System.out.println(dashboardPage+"***********************");
			 syncPage = viewSyncResults();
			assertEquals("SYNC COUNT MISMATCH: ", dashboardPage, syncPage);

			break;

		case Referral_Category:
//			
//			Page.sleep(5000);
//			gearIcon.click();
//			performPageLoad(driver);
//			deleteAllDocuments.click();
//			Actions.isDisplayed(deleteMessage);
//			performPageLoad(driver);
//			ifDownloaded(deletingDoc);
//		
//		Actions.navigateBack();
//		performPageLoad(driver);
//		
			page1.categorySyncBtn.click();
			ifDownloaded(page1.cancelBtn);

			if (dashBoard.isDisplayed()) {
				dashBoard.click();
			}
			 dashboardPage = getSyncCount();
			 
			// syncPage = viewSyncResults();

			//assertEquals("SYNC COUNT MISMATCH: ", dashboardSyncCount, syncPage);

			break;

		case Case_Detail:


			
			Page.waitToBeClickable(caseSyncBtn, driver);
			
			//Utility.doubleTap(page1.caseSyncBtn);
			ifDownloaded(page1.activityIndicator);
			
			performPageLoad(driver);

			String pageSource = driver.getPageSource();

			String[] docBlocks = pageSource.split("<XCUIElementTypeOther");

			Pattern downloadedPattern = Pattern.compile(
			    "<XCUIElementTypeStaticText[^>]*name=\"Downloaded\"[^>]*label=\"\"[^>]*x=\"(\\d+)\"[^>]*y=\"(\\d+)\""
			);

			Pattern precedingTextPattern = Pattern.compile(
			    "<XCUIElementTypeStaticText[^>]*name=\"([^\"]+)\"[^>]*label=\"[^\"]*\"[^>]*x=\"(\\d+)\"[^>]*y=\"(\\d+)\""
			);

			List<String> seenBlocks = new ArrayList<>();

			for (String block : docBlocks) {
			    if (seenBlocks.contains(block)) continue; // avoid reprocessing
			    seenBlocks.add(block);

			    Matcher match = downloadedPattern.matcher(block);
			    if (match.find()) {
			        String x = match.group(1);
			        String y = match.group(2);
			        System.out.println("Found Downloaded icon at x=" + x + ", y=" + y);

			        // Try to find the preceding static text in the same block
			        Matcher precedingMatch = precedingTextPattern.matcher(block);
			        String lastPrecedingText = null;
			        while (precedingMatch.find()) {
			            lastPrecedingText = precedingMatch.group(1); // name attribute of the last matching preceding element
			        }

			        if (lastPrecedingText != null) {
			            System.out.println("Preceding text: " + lastPrecedingText);
			        }
			    }
			}

			// Step 2: Scroll from the last downloaded icon (if needed)
			Map<String, Object> params = new HashMap<>();
			params.put("direction", "up"); // or "down" depending on your app
			driver.executeScript("mobile: swipe", params);

			break;

		default:
			break;
		}

	}

	public static String viewSyncResults() {
		gearIcon.click();
		viewSyncResults.click();
		performPageLoad(driver);
		WebElement syncResults = Actions.findElement(By.xpath(containsElement("Sync Results")));
		return getText(syncResults).split("\\(")[1].split(" ")[0].trim();
	}

	/** deletes all the documents from the device */
	public static String deleteAllDocuments() {

		if (dashBoard.isDisplayed()) {
			dashBoard.click();
		}
			Page.sleep(5000);
			settingsIcon.click();
			performPageLoad(driver);
			deleteAllDocuments.click();
			Actions.isDisplayed(deleteMessage);
			performPageLoad(driver);
			ifDownloaded(deletingDoc);
		
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
	
	public void collapseGroupIcons(List<UserInputData> userInputData) {
	    String peId = DocumentPage.get_pe_id("jud", userInputData);

	    String query = Queries.UNRESTRICTED_DOCUMENTS.replace("?", peId);

	    List<String[]> foundCases = executeDBQuery(getID(query, peId), userInputData);

	    if (foundCases != null && !foundCases.isEmpty()) {
	        int randomIndex = new Random().nextInt(foundCases.size());
	        String[] randomRecord = foundCases.get(randomIndex);

	        
	        String category = randomRecord[1].trim();
	       
	        
	        String caseNum = randomRecord[2].trim();

	         if (caseNum.matches("\\d-\\d+")) {
	         caseNum = "0" + caseNum;
	        }

		       
	            collapseBtn.click();
		        scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");
		   
		        System.out.println("------------------------------------------------------");
		        System.out.println("Selected category name: " + category);
		        System.out.println("------------------------------------------------------");
		    
		        performPageLoad(driver);
		        scrollDownIfNotDisplayed("//XCUIElementTypeStaticText[contains(@name, '" + caseNum + "')]");
	
		        System.out.println("------------------------------------------------------");
		        System.out.println("Selected case number: " + caseNum);
		        System.out.println("------------------------------------------------------");
		        
		        performPageLoad(driver);

		        collapseBtn.click();
		        CommonPages page=new CommonPages();
		        page.getGroupIcons(GroupIcons.Expand);
		        
		        collapseGroupsFromBottom("Applied Referrals");
                 Utility.scrollPage("down");
		
				}}
	

	
    public static int collapseGroupsFromBottom(String anchorGroupName) {
        ifDownloaded(activityIndicator);

        List<WebElement> groupHeaders = Actions.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@value, '▷')]/following::XCUIElementTypeStaticText[1]"));
       
        
         int s=groupHeaders.size();
         
        int startIndex = -1;

        for (int i = 0; i < groupHeaders.size(); i++) {
            String text = groupHeaders.get(i).getText().trim();
            if (text.equalsIgnoreCase(anchorGroupName)) {
                startIndex = i;
                break;
            }
        }

        if (startIndex == -1 && anchorGroupName.equals("Applied Referrals")) {
            return collapseGroupsFromBottom("Actions");
        } else if (startIndex == -1) {
            System.err.println("Neither 'Applied Referrals' nor 'Actions' found.");
            return 0;
        }
        
        
        for (int i = s; i > startIndex+1; i--) {
   
           Actions.findElement(By.xpath("(//XCUIElementTypeStaticText[contains(@value, '▷')])["+i+"]")).click();

		}
		return startIndex;
        
        
    }

	

	public enum SyncType {
		Dashboard, Referral_Category, Case_Detail;

	}

}
