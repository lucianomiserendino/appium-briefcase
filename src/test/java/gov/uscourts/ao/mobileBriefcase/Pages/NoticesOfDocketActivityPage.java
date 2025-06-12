package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeDBQuery;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CMD_DM_DLS_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DKT_ENTRY_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DLS_ID;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getSiValue;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.SiteTableVariable;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NoticesOfDocketActivityPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Docket Text']")
	public List<WebElement> docketText;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	@iOSXCUITFindBy(accessibility = "PDF View")
	public static List<WebElement> pdfView;

	@iOSXCUITFindBy(accessibility = "//XCUIElementTypeStaticText[contains(@name, 'Note')]")
	public static List<WebElement> noteView;

	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Back\"]")
	public static List<WebElement> back;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='']")
	public static List<WebElement> arrow;

	public void openNDALink(DataTable dataTable, String peId, List<UserInputData> userInputData) {
		List<String> items = dataTable.asList(String.class);
		for (String item : items) {
			try {
				processNDAItem(item.toLowerCase(), peId, userInputData);
			} catch (Exception e) {
				System.err.println("Error processing item: " + item);
				e.printStackTrace();
			}
			navigateBackToDashboard();
		}
	}

	private void processNDAItem(String item, String peId, List<UserInputData> userInputData) throws Exception {
		switch (item) {
		case "docket_entry":
			handleDocketEntry(peId, userInputData);
			break;
		case "document":
			handleDocument(peId, userInputData);
			break;
		case "note":
			handleNote(peId, userInputData);
			break;
		default:
			System.out.println("Unknown item: " + item);
			return; // Exit early if the item is unknown
		}
	}

	private void handleDocketEntry(String peId, List<UserInputData> userInputData) throws Exception {
		handleCommon(peId, userInputData, ID.DOCKETENTRY_ID, "Docket Text", "Docket Text not found");
	}

	private void handleDocument(String peId, List<UserInputData> userInputData) throws Exception {
		handleCommon(peId, userInputData, ID.DOCUMNET_ID, "PDF View", "Document not found");
		close.click();
	}

	private void handleNote(String peId, List<UserInputData> userInputData) throws Exception {
		handleCommon(peId, userInputData, ID.NOTE_ID, "Note", "Note not found");
	}

	private void handleCommon(String peId, List<UserInputData> userInputData, ID idType, String pageSourceText,
			String errorMessage) throws Exception {
		String ndaLink = getNDALink(idType, peId, userInputData);
		loadNDALinksInBriefcase(ndaLink);
		if (arrow.size() > 0) {
			Utility.clickOnNumberInRange(arrow);
			ifDownloaded(inProgress);
		}
		if (!driver.getPageSource().contains(pageSourceText)) {
			throw new AssertionError(errorMessage + ". NDA Link: " + ndaLink);
		}
	}

	private void navigateBackToDashboard() {
		while (back.size() > 0) {
		    back.get(0).click();
		}
		dashboard.click();
		Page.performPageLoad(driver);
	}

	public String getNDALink(ID id, String pe_id, List<UserInputData> userInputData) {
		String query;
		String si_value = CommonPages.getSiValue(SiteTableVariable.targetOnly, userInputData);

		if ("y".equals(si_value)) {
			query = Queries.FIND_ALL_NON_ORALLY_ARGUED_CASES;
		} else {
			query = Queries.FIND_ALL_ORALLY_ARGUED_CASES;
		}

		List<String[]> findCase = executeDBQuery(getID(query, pe_id).replace("TEXT", "not in ('EN BANC')"),
				userInputData);

		String cs_caseid = "";
		String caseNum = "";

		if (findCase != null && !findCase.isEmpty()) {
			int randomIndex = new Random().nextInt(findCase.size());
			String[] randomRecord = findCase.get(randomIndex);
			cs_caseid = randomRecord[0];
			caseNum = randomRecord[1];
		}

		String dbRecord = getDocID(id, cs_caseid.trim(), userInputData);

		switch (id) {
		case DOCKETENTRY_ID:
			return getDktentryid(cs_caseid, dbRecord, userInputData);

		case DOCUMNET_ID:
		case NOTE_ID:

			return getDocumentAndNoteID(cs_caseid, dbRecord, userInputData);
		default:
			return caseNum.trim();
		}
	}

	public static String getDocID(ID id, String caseNum, List<UserInputData> userInputData) {
		String idColumn = "";
		switch (id) {
		case DOCKETENTRY_ID:
			idColumn = DKT_ENTRY_ID;
			break;
		case DOCUMNET_ID:
			idColumn = CMD_DM_DLS_ID;
			break;
		case NOTE_ID:
			idColumn = DM_DLS_ID;
			break;
		default:
			break;
		}
		return getAllColumns(getID(idColumn, caseNum), userInputData);
	}

	public static String getDktentryid(String caseId, String dbRecord, List<UserInputData> userInputData) {

		return getSiValue(SiteTableVariable.AppLinkRoot, userInputData) + "queryecf?caseid=" + caseId + "&dktentryid="
				+ dbRecord;
	}

	public static String getDocumentAndNoteID(String caseId, String dbRecord, List<UserInputData> userInputData) {
		return getSiValue(SiteTableVariable.AppLinkRoot, userInputData) + "viewdocument?dmdlsid=" + dbRecord
				+ "&caseid=" + caseId;
	}

	public void loadNDALinksInBriefcase(String ndaLink) {
		changeWindow("WEBVIEW");
		driver.get(ndaLink);
		changeWindow("NATIVE");
		ifDownloaded(inProgress);
	}

	public enum ID {
		DOCKETENTRY_ID, DOCUMNET_ID, NOTE_ID
	}

}
