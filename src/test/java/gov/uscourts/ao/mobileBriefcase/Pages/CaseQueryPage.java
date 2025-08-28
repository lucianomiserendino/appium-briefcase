package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clicksOn;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.NetworkManager;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CaseQueryPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Search']")
	public static WebElement searchIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	public static WebElement searchTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static WebElement searchBTN;

	@iOSXCUITFindBy(accessibility = "ResultsList")
	public static WebElement category;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CM/ECF']")
	public static WebElement cmecf;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='On Device']")
	public static WebElement on_device;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> cases;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='MasterNavPage']/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static WebElement dashboardIcon;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;

	public String getCaseSearch(String category) {
	    String fullCaseNumber = selectRandomCaseNumber(0);
	    searchForACase(category, fullCaseNumber, Search.wildcard);
	    
	    isDisplayed(Locator.XPATH, containsElement(fullCaseNumber));
		return fullCaseNumber;
	  
	    
	}


	public void searchByCase(String category, String fullCaseNumber, Search searchType) {
	    // Generate the search value based on the search type and full case number
	    String searchValue = searchBy(searchType, fullCaseNumber);
	    
	    // Print the category and case number being searched
	    System.out.println("Searching for category: " + category + ", case number: " + fullCaseNumber);
	    
	    // Enter the search value in the search text field
	    sendKeys(searchTextField, searchValue);
	    
	    // Click on the search button
	    clicksOn(searchBTN);
	    
	    // Wait for the page to load
	    performPageLoad(driver);
	    
	    // Click on the device element
	    clicksOn(on_device);
	    
	    collapseBtn.click();
	    // Scroll down to the element if not displayed
	    scrollDownIfNotDisplayed(containsElement(category) + "/preceding::XCUIElementTypeStaticText[contains(@name, '" + fullCaseNumber + "')]");
	    
	    // Check if the element is downloaded
	    CommonPages.ifDownloaded(inProgress);
	}

	
	



	public void searchForACase(String category, String fullCaseNumber, Search searchType) {
		Page.waitToBeClickable(collapseBtn, driver);
		//collapseBtn.click();
	    if (dashboardIcon.isDisplayed()) {
	    	 clicksOn(dashboardIcon);
	        Page.sleep(5000);
	    }
	    clicksOn(searchIcon);
	    searchByCase(category, fullCaseNumber, searchType);
	}


	public void viewInfo(String caseNum, WebElement el, String text) {
		performPageLoad(driver);
		clicksOn(searchIcon);
		sendKeys(searchTextField, caseNum);
		clicksOn(searchBTN);
		performPageLoad(driver);
		clicksOn(el);
		performPageLoad(driver);
		contains(caseNum).click();
		assertTrue(contains(text).isDisplayed());

	}

	public void viewTheInformationInCMECF(String caseNum) {
		viewInfo(caseNum, cmecf, "Docket Entries");
	}

	public void viewTheInformationOnTheDevice(String caseNum) {
		viewInfo(caseNum, on_device, "Case Information");

	}

	public String searchBy(Search search, String caseN) {
	    String searchType = "";

	    switch (search) {
	        case partyName:
	            String partyN = selectRandomCaseNumber(1);
	            String specialChar = checkForSpecialChar(partyN);
	            searchType = (specialChar != null && !specialChar.isEmpty()) ? partyN.split(specialChar).toString() : partyN;
	            break;

	        case caseNumber:
	            searchType = caseN;
	            break;

	        case wildcard:
	            searchType = caseN.substring(0, caseN.length() - 1) + "*";
	            break;

	        default:
	            break;
	    }
	    return searchType;
	}


	public static String selectRandomCaseNumber(int index) {
		Page.sleep(20000);
		

	    Pattern pattern = Pattern.compile("^\\d{2}-\\d{3,5} .+");

	    List<WebElement> filtered = cases.stream()
	        .filter(el -> pattern.matcher(el.getAttribute("name")).matches())
	        .collect(Collectors.toList());
		
		List<String> list = Utility.retrieveAllReferrals(filtered, " ", index);
		
		   Random random = new Random();
	        return list.get(random.nextInt(list.size()));
	       
	}

	public static String checkForSpecialChar(String inputString) {

		String speChar = "";

		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(inputString);

		int count = 0;
		while (m.find()) {
			count = count + 1;

			speChar = Character.toString(inputString.charAt(m.start()));
		}
		return speChar;

	}
	public void searchFunctionIsDisabled() {
		
		  assertTrue( "Verify the magnifying glass icon is enabled",searchIcon.isEnabled());

			// Switch to offline mode
			try {
			NetworkManager.makeScriptsExecutable();
			NetworkManager.disableInternet();
		} catch (IOException | RuntimeException e) {
			System.err.println("Failed to disable internet: " + e.getMessage());
		}

		DashboardPage page = new DashboardPage();
		
		Page.waitForVisibilityOfElement(page.offline_Indicator, driver);
	        
	    assertFalse("Verify the magnifying glass icon is disabled", searchIcon.isEnabled());

		try {
			NetworkManager.makeScriptsExecutable();
			NetworkManager.enableInternet();
		} catch (IOException | RuntimeException e) {
			System.err.println("Failed to re-enable internet: " + e.getMessage());
		
	}
	}

	public enum Search {
		wildcard, caseNumber, partyName
	}

}