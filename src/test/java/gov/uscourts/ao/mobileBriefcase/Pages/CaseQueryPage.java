package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clicksOn;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CaseQueryPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
	public static MobileElement searchIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	public static MobileElement searchTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static MobileElement searchBTN;

	@iOSXCUITFindBy(accessibility = "ResultsList")
	public static MobileElement category;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CM/ECF']")
	public static MobileElement cmecf;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='On Device']")
	public static MobileElement on_device;

	// @WithTimeout(time = 2500, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<MobileElement> caseNum;

	public void searchForACase() {
		
		
		String caseNum=searchBy(Search.caseNumber);
		
		performPageLoad(driver);
		
		if(contains("Dashboard").isDisplayed()) {
			contains("Dashboard").click();
			Page.sleep(5000);
		}
		clicksOn(searchIcon);
		searchByCase(caseNum);

	}

	public void searchByCase(String caseN) {
		sendKeys(searchTextField, caseN);
		clicksOn(searchBTN);
		performPageLoad(driver);
		scrollDownIfNotDisplayed(containsElement(caseN));
		performPageLoad(driver);

		assertTrue("APP IS NOT RETURNING CASE LIST FOR SOME WILDCARD SEARCHES",
				isDisplayed(Locator.XPATH, containsElement("Case #" + caseN)));
	}

	public void viewInfo(String caseNum, MobileElement el, String text) {
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

	public String  searchBy(Search search) {

		String caseN = selectRandomCaseNumber(0).split(" ")[0];

		String searchType = "";

		switch (search) {

		case partyName:

			String partyN = selectRandomCaseNumber(1).split(" ")[1];

			if (checkForSpecialChar(partyN) != null && !checkForSpecialChar(partyN).isEmpty()) {
				searchType = partyN.split(checkForSpecialChar(partyN)).toString();
			} else {
				searchType = partyN;
			}
			break;

		case caseNumber:
			searchType = caseN;
			break;

		case wildcard:

			searchType = caseN.substring(0, 6) + "*";

		default:
			break;
		}
		return searchType;
	}

	
	
	
	public static String selectRandomCaseNumber(int index) {
		String referral = "";
		Page.sleep(20000);
		List<String> list = Utility.retrieveAllReferrals(caseNum, " ", index);
		MobileElement uiResult = findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '"
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