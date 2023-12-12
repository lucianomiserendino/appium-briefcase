package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.stepDefinitions.DPF_stepDefinitions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class UIDocketingDPFPage extends AppiumPageFactory {

	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextView[1]")
	public static WebElement descriptionField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Select']/preceding::XCUIElementTypeTextField")
	public static WebElement select;

	@iOSXCUITFindBy(accessibility = "proposed order")
	public static WebElement proposedOrder;

	@iOSXCUITFindBy(accessibility = "back")
	public static WebElement backBtn;

	@iOSXCUITFindBy(accessibility = "View Case info")
	public static WebElement viewCaseInfo;

	@iOSXCUITFindBy(accessibility = "Docket Entries")
	public static WebElement docketEntries;

	@iOSXCUITFindBy(accessibility = "Auto Test")
	public static WebElement autoTest;

	@iOSXCUITFindBy(accessibility = "OK")
	public static WebElement OK;

	@iOSXCUITFindBy(accessibility = "Categories")
	public static WebElement categories;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']")
	public static WebElement documentListPage;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Comment')]/following::XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextView")
	public static WebElement commentField;

	@iOSXCUITFindBy(accessibility = "(//XCUIElementTypeStaticText[@name='Downloaded'])[1]/preceding:: XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static WebElement proposedOrderDoc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Description']")
	public static WebElement description;

	public String submit = "//XCUIElementTypeStaticText[@name='Submit']";

	public String note = "Test-!@#<>$%^&*()_+|:?";

	public static String uiParam = "";

	public void verifyFieldsAreDisplayed(List<UserInputData> userInputData) {
		String actionName = DPF_stepDefinitions.actionName;

		String elId = getAllColumns(getID(Queries.EL_ID, actionName), userInputData);

		try {

			getDefaulDescription("note", elId, userInputData);

			if (commentField.getText().length() == 0) {

				commentField.sendKeys(note);
			} else {
				throw new RuntimeException("Verify an editable comment field displays and it does not contain text.");
			}

			scrollDownIfNotDisplayed(submit);
			Page.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * Verify an editable description field displays. The default description is
	 * defined in the Default description parameter of the note DPF. It will always
	 * be in the 5th position in the note DPF. If the value is 'SKIP', the note
	 * description should default to 'Transaction Note'
	 */
	public void getDefaulDescription(String dpfName, String el_id, List<UserInputData> userInputData) {

		assertTrue("Verify an editable description field displays", isDisplayed(description));

		String dbNoteDescription = Utility.getSingleDpf(getAllColumns(getID(MBR_NOTE, el_id), userInputData), dpfName,
				4);

		String uiNoteDescription = descriptionField.getText();

		if (dbNoteDescription.equals("SKIP")) {
			assertTrue(uiNoteDescription.equals("Transaction Note"));

		} else {
			String dbParam = replaceWithEmptyString(dbNoteDescription, "\\");
			String ui = uiNoteDescription;

			if (ui.contains("'")) {
				uiParam += ui.split("'")[0];
			} else {
				uiParam += ui;
			}

			assertEquals("NOTE DESCRIPTION MISMATCH", dbParam.replace("'", ""), uiParam);

		}

	}

	public void verifyElementIsDisplayed(String text) {
		assertTrue(isDisplayed(Locator.XPATH, containsElement(text)));

	}

	public static String replaceWithEmptyString(String text, String charac) {
		if (text.contains(charac))
			;
		return text.replace(charac, "").trim();

	}

	public void selectProposedOrder() {

		CommonPages.getPanel(Panel.Proposed_Orders);

		String docName = Actions.getText(proposedOrderDoc);

		CommonPages.getActionName("Auto Test");
		if (select.isDisplayed()) {
			tap(select);
		} else {
			Page.performPageLoad(driver);
			tap(select);
		}
		tap(Locator.XPATH, containsElement(docName));

		sendKeys(select, "Test");

		scrollDownIfNotDisplayed("//XCUIElementTypeButton[@name='Submit']");

		if (isDisplayed(Locator.XPATH, containsElement("The update succeded")) == true) {
			;
			tap(OK);
		} else {
			throw new RuntimeException("Failed to select proposed order");
		}
		Utility.scroll(categories, "up");
		tap(viewCaseInfo);
		tap(docketEntries);
		assertTrue(isDisplayed(Locator.XPATH, containsElement("Auto Test")));
		tap(autoTest);
		assertTrue(isDisplayed(Locator.XPATH, containsElement(docName)));
	}

	public void isNoteTextTPFSupported() {

		assertTrue("Verify the docket entry page in Briefcase displays the Docket Text & Note",
				isDisplayed(Locator.XPATH, containsElement(uiParam) + containText("Docket Text") + containText(note)));

	}

	public String containText(String txt) {
		return "/following::XCUIElementTypeStaticText[contains(@name, '" + txt + "')]";
	}

}
