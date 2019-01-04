package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CMD_DM_DLS_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DKT_ENTRY_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DLS_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SI_VALUE;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_LoginPage.open;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_LoginPage.searchForACase;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Coordinates.select;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static org.junit.Assert.assertTrue;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Coordinates.BriefcaseCoordinates;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_NoticesOfDocketActivityPage extends AppiumPageFactory {

	String backBTN = "Back";

	String event = "Event";

	String docketText = "Docket Text";

	String close = "Close";

	String PDFPageView = "PDF View";

	String transactionNote = "Transaction Note";

	String addANote = "Adding a note to display in briefcase";

	@iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
	public static MobileElement searchIcon;

	@iOSFindBy(xpath = "//XCUIElementTypeTextField")
	public static MobileElement searchTextField;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static MobileElement searchBTN;

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

	public static String getSiValue(String dbType) {
		return getAllColumns(valueOf(dbType), SI_VALUE);
	}

	/** Run the following URLS in the browser on the iPad */

	public static String getDktentryid(String caseNum, String dbType) {
		return getSiValue(dbType) + "queryecf?caseid=" + findACaseID(caseNum, dbType) + "&dktentryid=4352678";
		// + getDocID(ID.DOCKETENTRY_ID, caseNum, dbType);
	}

	public static String getDocumentAndNoteID(String caseNum, String dbType, String id) {
		return getSiValue(dbType) + "viewdocument?dmdlsid=" + id + "&caseid=" + findACaseID(caseNum, dbType);
	}

	/** Open a Docket Entry in Briefcase from the NDA link */

	public void openADktEntryInBriefcase(String caseNum, String dbType) {
		searchForACase(caseNum);
		loadNDALinksInBriefcase(getDktentryid(caseNum, dbType));
		verifyElementsAreDisplayed("DOCKET ENTRY", event, docketText);
		clickBack(3);
	}

	/** Open a document in Briefcase from the NDA link */

	public void openADocumentInBriefCase(String caseNum, String dbType) {
		loadNDALinksInBriefcase(getDocumentAndNoteID(caseNum, dbType, "2832314"));
		select(BriefcaseCoordinates.DISMISS);
		performPageLoad(driver);
		assertTrue("********CAN'T OPEN A DOCUMENT IN BRIEFCASE FROM THE NDA LINK*********",
				isDisplayed(Locator.ID, PDFPageView));
		tap(Locator.NAME, close);
	}

	/** Open a note in Briefcase from the NDA link */

	public void openANoteInBriefcase(String caseNum, String dbType) {
		loadNDALinksInBriefcase(getDocumentAndNoteID(caseNum, dbType, getDocID(ID.NOTE_ID, caseNum, dbType)));
		verifyElementsAreDisplayed("NOTE", transactionNote, addANote);
		clickBack(2);

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
		for (int i = 0; i < backBtn; i++) {
			tap(Locator.ID, backBTN);

		}
	}

	public void verifyElementsAreDisplayed(String link, String el1, String el2) {
		assertTrue("*********CAN'T OPEN A " + link + " IN BRIEFCASE FROM THE NDA LINK*********",
				isDisplayed(Locator.ID, el1) == true && isDisplayed(Locator.ID, el2) == true);
	}

	public enum ID {
		DOCKETENTRY_ID, DOCUMNET_ID, NOTE_ID
	}

}
