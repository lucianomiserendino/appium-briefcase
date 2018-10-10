package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CMD_DM_DLS_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DKT_ENTRY_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SI_VALUE;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_LoginPage.open;
import static gov.uscourts.ao.mobileBriefcase.common.Base.changeWindow;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Base.safariInstance;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.select;
import static gov.uscourts.ao.mobileBriefcase.common.Page.pageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.sendKeys;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.Coordinates;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_NoticesOfDocketActivityPage {

	@iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
	public static MobileElement searchIcon;

	@iOSFindBy(xpath = "//XCUIElementTypeTextField")
	public static MobileElement searchTextField;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static MobileElement searchBTN;

	String backBTN = "Back";

	String event = "Event";

	String docketText = "Docket Text";

	String close = "Close";

	String PDFPageView = "PDF View";

	public iOS_NoticesOfDocketActivityPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static String findACaseID(String caseNum, String index) {
		return caseNum.split("-")[Integer.valueOf(index)];

	}

	/** Find the caseid by running this query */

	public static String getCaseID(String caseNum, String dbType) {
		return getAllColumns(valueOf(dbType),
				replace(CASE_ID, "CS_YEAR", findACaseID(caseNum, "0"), "CS_NUMBER", findACaseID(caseNum, "1")));
	}

	/**
	 * Find a docket entry for that case by querying the chm_mobile_referral table
	 */
	public static String getDocketEntry(String caseNum, String dbType) {
		return getAllColumns(valueOf(dbType), getID(DKT_ENTRY_ID, getCaseID(caseNum, dbType)));
	}

	/**
	 * Find the value of the site table variable "briefcaseAppLinkRoot" by executing
	 * this query
	 */

	public static String getSiValue(String dbType) {
		return getAllColumns(valueOf(dbType), SI_VALUE);
	}

	/** Run the following URLS in the browser on the iPad */

	public static String openDocketEntryInBriefcase(String caseNum, String dbType) {
		String docketEntry = getSiValue(dbType) + "queryecf?caseid=" + getCaseID(caseNum, dbType) + "&dktentryid="
				+ getDocketEntry(caseNum, dbType);
		return docketEntry;

	}

	public static String openDocInBriefcase(String caseNum, String dbType) {
		String docketEntry = getSiValue(dbType) + "viewdocument?dmdlsid=" + getDocumnetID(caseNum, dbType) + "&caseid="
				+ getCaseID(caseNum, dbType);
		return docketEntry;

	}

	/** Query for a document ID by running the following query */

	public static String getDocumnetID(String caseNum, String dbType) {
		return getAllColumns(valueOf(dbType), getID(CMD_DM_DLS_ID, getCaseID(caseNum, dbType)));
	}

	public void searchForACase(String caseNum) {
		searchIcon.click();
		sendKeys(searchTextField, caseNum);
		searchBTN.click();

	}

	public void openDktEntryInBriefcase(String caseNum, String dbType) {

		searchForACase(caseNum);
		loadNDALinksInBriefcase(openDocketEntryInBriefcase(caseNum, dbType));
		assertTrue("*********CAN'T OPEN A DOCKET ENTRY IN BRIEFCASE FROM THE NDA LINK*********",
				findElement(By.id(event)).isDisplayed() && findElement(By.id(docketText)).isDisplayed());
		for (int i = 0; i < 3; i++) {
			findElement(By.name(backBTN)).click();
		}

	}

	public void openADocumentInBriefCase(String caseNum, String dbType) {
		loadNDALinksInBriefcase(openDocInBriefcase(caseNum, dbType));
		select(Coordinates.DISMISS);
		performPageLoad();
		assertTrue("********CAN'T OPEN A DOCUMENT IN BRIEFCASE FROM THE NDA LINK*********",
				findElement(By.id(PDFPageView)).isDisplayed());
		select(Coordinates.DISMISS);
		findElement(By.id(close)).click();
		findElement(By.name(backBTN)).click();
	}

	public void loadNDALinksInBriefcase(String ndaLink) {
		try {
			safariInstance();
			changeWindow("WEBVIEW");
			driver.get(ndaLink);
			changeWindow("NATIVE");
			open();
			pageLoad();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
