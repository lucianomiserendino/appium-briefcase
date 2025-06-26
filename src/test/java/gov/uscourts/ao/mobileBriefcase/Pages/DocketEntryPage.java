package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.searchForACase;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DocketEntryPage extends AppiumPageFactory {

	
	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static WebElement collapseBtn;
	
	public String getDocketEntries() {
		//searchForACase(caseNum);
		//contains(caseNum).click();
		performPageLoad(driver);
		contains("View Case Info").click();
		//contains("Docket Entries").click();
		String docketEntry= contains("Docket Entries - ").getText().split("-")[1].trim();
		collapseBtn.click();
		return docketEntry;
	}
	
	public void viewDocketEntries(String actioName) {
		contains("View Case Info").click();
		contains("Docket Entries").click();
		contains(actioName).click();
		
	}
	
	
	
	

}
