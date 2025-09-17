package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeDBQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clicksOn;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CitelinkSettings extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Settings']")
	public static WebElement gearIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Use My CM/ECF Settings']/following:: XCUIElementTypeSwitch[1]")
	public static WebElement citelinkSettings;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='If you change any Citelink settings, documents cached on this device will not reflect the change unless deleted and downloaded again.']")
	public static List<WebElement> message;

	@iOSXCUITFindBy(accessibility = "OK")
	public static WebElement okBTN;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='OK']")
	public static WebElement okBtn;
	
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Downloaded'])[1]")
	public static WebElement Downloaded;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CiteLinkEngine']/XCUIElementTypeButton/XCUIElementTypeStaticText[1]")
	public static List<WebElement> citeLinkEngineList;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CiteLinkHighlightStyle']/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> citeLinkHighlightStyle;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeLink")
	public static List<WebElement> links;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Page Label']/XCUIElementTypeOther/following::XCUIElementTypeStaticText[1]")
	public static WebElement pageSize;

	@iOSXCUITFindBy(accessibility = "Done")
	public static WebElement done;

	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='Search Results']/XCUIElementTypeCell")
	public static List<WebElement> searchResult;

	@iOSXCUITFindBy(accessibility = "Search")
	public static WebElement searchIcon;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeSearchField[@name=\"Search Document\"])[1]")
	public static WebElement searchTextField;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'FindAppendix?')]")
	public static List<WebElement> appxLink;
	
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=''])[1]/preceding::XCUIElementTypeStaticText[1]")
	public static WebElement selectedciteLinkEngine;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText")
	public static WebElement collapseBtn;
	
	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Downloaded_Container'])[1]")
	public static WebElement downloaded;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PDF Page View']")
	public static List<WebElement> pdfPageView;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	@iOSXCUITFindBy(id = "Delete all Briefcase Documents")
	public static WebElement deleteAllDocuments;
	
	@iOSXCUITFindBy(id = "Tap Delete to delete all documents")
	public static WebElement deleteMessage;

	@iOSXCUITFindBy(id = "Deleting all documents...")
	public static List<WebElement> deletingDoc;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static WebElement dashBoard;
	
	public static int getCurrentPageNumber;

	public static String changesCitelinkSettings() {
		String engine = "";
		Page.performPageLoad(driver);
		gearIcon.click();

		if (Utility.getToggleState(citelinkSettings) == true) {
			citelinkSettings.click();

			List<WebElement> linkSearch = message;
			int checkLink = linkSearch.size();
			assertTrue("USER IS NOT PRESENTED WITH THE MESSAGE IF THE CITELINKS PREFERENCES CHANGE IN CM/ECF",
					checkLink > 0);
			okBTN.click();
		}

		int index = Utility.getRandomNumberInRange(3, citeLinkEngineList.size() - 1);
		engine += citeLinkEngineList.get(index).getText().trim();
		citeLinkEngineList.get(index).click();
		if ( message.size()>0) {
			if (okBTN.isDisplayed()) {
			okBTN.click();
		}}
		assertTrue("Verify the check mark is on the right of the selected citelink engine",selectedciteLinkEngine.getText().trim().equals(engine));
		
		deleteAllDocuments.click();
		Actions.isDisplayed(deleteMessage);
		performPageLoad(driver);
		ifDownloaded(deletingDoc);
		
		driver.navigate().back();

		return engine;

	}

	public void searchForAppendix() {
		String text = "Appx";
		performPageLoad(driver);
		clicksOn(searchIcon);
		searchTextField.clear();
		Actions.sendKeys(searchTextField, text);
		String index = Utility.clickOnNumberInRange(searchResult);

		if (!(index == null)) {
			Utility.clickOnNumberInRange(appxLink);
			performPageLoad(driver);
			Assert.assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(text)));
		} else {
			throw new RuntimeException("THIS DOCUMENT DOES NOT CONTAIN ANY HYPERLINKS");
		}
	}

	public void getCiteLink(String searchEngine) {

		String[] size = pageSize.getText().split("of");
		int totalPageSize = Integer.parseInt(size[1].trim());
		int lastViewedPage = Integer.parseInt(size[0].trim());

		do {
			List<WebElement> icons = links;
			if (icons.size() == 0) {

				// if (totalPageSize>1) {

				if (totalPageSize == lastViewedPage) {
					Utility.swipe(1, "left");

				} else if (totalPageSize == lastViewedPage) {
					Utility.swipe(1, "right");
				}
				// }

				break;
			}

		} while (true);

		List<WebElement> icons = links;

		icons.get(0).click();
		Page.performPageLoad(driver);
		assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(searchEngine)));

		done.click();
		close.click();
	}

	
	public void openCiteLinkLink(List<UserInputData> userInputData, String searchEngine) {
	    int maxAttempts = 2;

	    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
	        getRandomROADocument(userInputData);
	        CitelinkSettings page = new CitelinkSettings();

	        if (page.tryGetCiteLink()) {
	            Page.performPageLoad(driver);
	            System.out.println("Selected search Engine: " + searchEngine);

	            boolean searchEngineVisible = Actions.isDisplayed(Locator.XPATH, Actions.containsElement(searchEngine));
	            if (searchEngineVisible) {
	                assertTrue(searchEngineVisible);
	                return; 
	            } else {
	                System.out.println("Search engine not found. Trying another document.");
	            }
	        } else {
	            System.out.println("No hyperlink found. Trying another random document.");
	        }

	        System.out.println("Retrying... (Attempt " + attempt + " of " + maxAttempts + ")");
	        
	        close.click();
	        
	        try {
		        if (close.isDisplayed()) {
		        	close.click();
		        }
			} catch (Exception e) {
			
			}

	        dashBoard.click();
	        
	    }

	    throw new AssertionError("Failed to find a hyperlink and matching search engine after " + maxAttempts + " attempts.");
	}


	public boolean tryGetCiteLink() {
	    String[] size = pageSize.getText().split("of");
	    int totalPageSize = Integer.parseInt(size[1].trim());
	    int currentPage = Integer.parseInt(size[0].trim());

	    // If not on page 1, swipe back to page 1 first
	    if (currentPage != 1) {
	        for (int i = 0; i < currentPage - 1; i++) {
	            Utility.swipe(1, "right");
	        }
	    }

	    // Now swipe left through all pages until totalPageSize
	    for (int page = 1; page <= totalPageSize; page++) {
	        if (checkForHyperlink()) {
	            System.out.println("Hyperlink found on page " + page + "!");
	            clickFirstHyperlink();
	            return true;
	        }

	        if (page < totalPageSize) {
	            Utility.swipe(1, "left");
	        }
	    }

	    return false; // No hyperlink found on any page
	}



	public static boolean checkForHyperlink() {
	    return links != null && !links.isEmpty();
	}

	private void clickFirstHyperlink() {
	    if (!links.isEmpty()) {
	        links.get(0).click();
	    }
	}

	

	
	public void getRandomROADocument(List<UserInputData> userInputData) {
	    String peId = DocumentPage.get_pe_id("jud", userInputData);

	    String query = Queries.DOCUMENTS_WITH_CITATION_LINKS.replace("?", peId);

	    List<String[]> foundCases = executeDBQuery(getID(query, peId), userInputData);

	    if (foundCases != null && !foundCases.isEmpty()) {
	        int randomIndex = new Random().nextInt(foundCases.size());
	        String[] randomRecord = foundCases.get(randomIndex);

	        String documentCategory = randomRecord[0].trim();
	        String document = randomRecord[1].trim();
	        
	        String caseNum = randomRecord[2].trim();

	         if (caseNum.matches("\\d-\\d+")) {
	         caseNum = "0" + caseNum;
	        }
	        String category = randomRecord[3].trim();

		       
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
		        
		        CommonPages.getGroupIcons(GroupIcons.Expand);
		        
		        
		        scrollDownIfNotDisplayed(Actions.containsElement(documentCategory));
		        
		        System.out.println("------------------------------------------------------");
		        System.out.println("Selected document Category: " + documentCategory);
		        System.out.println("------------------------------------------------------");
		        performPageLoad(driver);

		        
			        scrollDownIfNotDisplayed("//*[contains(@name, '" + document + "')]");
		        		        
		        System.out.println("------------------------------------------------------");
		        System.out.println("Selected document Category: " + document);
		        System.out.println("------------------------------------------------------");
		        
		        
		        Utility.ifLoaded(inProgress);
				
				Boolean elementNotFound = true;
				int attemptCount = 0;

				while (elementNotFound && attemptCount < 3) {
					if (!(pdfPageView.size() == 1)) {
						elementNotFound = true;
						Utility.scrollPage("down");
						attemptCount++;
					} else {
						elementNotFound = false;
						break;
					}
				}
	    	   
	    
	}
	
	    
	}
}
