package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clicksOn;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CaseQueryPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
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

	// @WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> caseNum;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	public void getCaseSearch(String category) {
	    String fullCaseNumber = selectRandomCaseNumber(0).split(" ")[0];
	    searchForACase("target",category, fullCaseNumber, Search.wildcard);
	}


	public void searchByCase(String targetOrApplied,String category, String fullCaseNumber, Search searchType) {
	    String searchValue = searchBy(searchType, fullCaseNumber);
	    sendKeys(searchTextField, searchValue);
	    clicksOn(searchBTN);
	    performPageLoad(driver);
	    clicksOn(on_device);

	    scrollDownIfNotDisplayed(
	        containsElement(category) + "/preceding::XCUIElementTypeStaticText[contains(@name, '" + fullCaseNumber + "')]");
	    CommonPages.ifDownloaded(inProgress);
	    
	    if (targetOrApplied.equals("target")) {

	    assertTrue("APP IS NOT RETURNING CASE LIST FOR SOME WILDCARD SEARCHES",
	        isDisplayed(Locator.XPATH, containsElement("Case #" + fullCaseNumber)));
	    }
	}
	
	



	public void searchForACase(String targetOrApplied,String category, String fullCaseNumber, Search searchType) {
	    performPageLoad(driver);

	    if (contains("Dashboard").isDisplayed()) {
	        contains("Dashboard").click();
	        Page.sleep(5000);
	    }
	    clicksOn(searchIcon);
	    searchByCase(targetOrApplied,category, fullCaseNumber, searchType);
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
	            String partyN = selectRandomCaseNumber(1).split(" ")[1];
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
		String referral = "";
		Page.sleep(20000);
		List<String> list = Utility.retrieveAllReferrals(caseNum, " ", index);
		WebElement uiResult = findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '"
				+ list.get(Utility.getRandomInt(list.size() - 1)) + "')]");
		referral = uiResult.getText();
		return referral;

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

	public enum Search {
		wildcard, caseNumber, partyName
	}

}