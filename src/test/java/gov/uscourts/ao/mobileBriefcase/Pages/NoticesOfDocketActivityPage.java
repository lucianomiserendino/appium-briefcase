package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CMD_DM_DLS_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DKT_ENTRY_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DLS_ID;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getPanel;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getSiValue;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.verifyElementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.open;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Coordinates.select;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static org.junit.Assert.assertTrue;

import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Coordinates.BriefcaseCoordinates;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NoticesOfDocketActivityPage extends AppiumPageFactory {

	String backBTN = "Back";

	String event = "AO - chmSendRef";

	String docketText = "Docket Text";

	static String close = "Close";

	static String PDFPageView = "PDF View";

	String transactionNote = "Transaction Note";

	String addANote = "Adding a note to display in briefcase";

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public MobileElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
	public static MobileElement searchIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	public static MobileElement searchTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static MobileElement searchBTN;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='TestingAutosync']")
	public static MobileElement pdf;

	public static String getCase(String caseNum, String index) {
		return caseNum.split("-")[Integer.valueOf(index)];
	}

	/** Find the case id by running this query */

	public static String findACaseID(String caseNum, String dbType) {
		return getAllColumns(valueOf(dbType),
				replace(CASE_ID, "CS_YEAR", getCase(caseNum, "0"), "CS_NUMBER", getCase(caseNum, "1")));
	}

	public static String getDocID(ID Id, String caseNum, String dbType) {

		String id = "";

		switch (Id) {
		/**
		 * Find a docket entry for that case by querying the chm_mobile_referral table
		 */
		case DOCKETENTRY_ID:
			id += getID(DKT_ENTRY_ID, findACaseID(caseNum, dbType));
			break;

		/** Query for a document ID by running the following query */

		case DOCUMNET_ID:
			id += getID(CMD_DM_DLS_ID, findACaseID(caseNum, dbType));
			break;

		/** Query for a note by running the following query */

		case NOTE_ID:
			id += getID(DM_DLS_ID, findACaseID(caseNum, dbType));
			break;
		default:
			break;
		}
		return getAllColumns(valueOf(dbType), id);
	}

	/**
	 * Find the value of the site table variable "briefcaseAppLinkRoot" by executing
	 * this query
	 */
	/** Run the following URLS in the browser on the iPad */

	public static String getDktentryid(String caseNum, String dbType, String value) {

		return getSiValue(dbType, value) + "queryecf?caseid=" + findACaseID(caseNum, dbType) + "&dktentryid=4352678";
	}

	public static String getDocumentAndNoteID(String caseNum, String dbType, String id, String value) {
		return getSiValue(dbType, value) + "viewdocument?dmdlsid=" + id + "&caseid=" + findACaseID(caseNum, dbType);
	}

	/** Open a Docket Entry in Briefcase from the NDA link */

	public void openADktEntryInBriefcase(String caseNum, String dbType, String value) {
		loadNDALinksInBriefcase(getDktentryid(caseNum, dbType, value));
		verifyElementsAreDisplayed("DOCKET ENTRY", docketText);

		driver.navigate().back();
	}

	/** Open a document in Briefcase from the NDA link */

	public void openADocumentInBriefCase(String caseNum, String dbType, String value) {
		loadNDALinksInBriefcase(getDocumentAndNoteID(caseNum, dbType, "2832314", value));
		Page.sleep(30000);
		select(BriefcaseCoordinates.DISMISS);

		verifyElementsAreDisplayed("DOCUMENT", PDFPageView);

		tap(Locator.NAME, close);

		driver.navigate().back();
	}

	/** Open a note in Briefcase from the NDA link */

	public void openANoteInBriefcase(String caseNum, String dbType, String value) {
		loadNDALinksInBriefcase(getDocumentAndNoteID(caseNum, dbType, getDocID(ID.NOTE_ID, caseNum, dbType), value));
		verifyElementsAreDisplayed("NOTE", addANote);

	}

	public void loadNDALinksInBriefcase(String ndaLink) {
		try {
			safariInstance();
			changeWindow("WEBVIEW");
			driver.get(ndaLink);
			changeWindow("NATIVE");
			open();
			performPageLoad(driver);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickBack(int backBtn) {
		try {
			for (int i = 0; i < backBtn; i++) {
				tap(Locator.ID, backBTN);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void verifyElementsAreDisplayed(String link, String el2) {
		assertTrue("*********CAN'T OPEN THE " + link + " IN BRIEFCASE FROM THE NDA LINK*********",
				isDisplayed(Locator.XPATH, containsElement(el2)) == true);
	}

	public void verifyPDFIsDownloaded() {
		getPanel(Panel.Briefs);
		tap(pdf);
		performPageLoad(driver);
		verifyElementIsDisplayed(PDFPageView);
		tap(Locator.NAME, close);
		tap(dashboard);

	}

	public enum ID {
		DOCKETENTRY_ID, DOCUMNET_ID, NOTE_ID
	}

}
