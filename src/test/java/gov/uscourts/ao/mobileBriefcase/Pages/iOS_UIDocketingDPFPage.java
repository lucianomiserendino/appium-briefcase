package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getRestrictParam;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.refresh;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCaseNumber;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.verifyTextIsDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_UIDocketingDPFPage extends Base {

	public iOS_UIDocketingDPFPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTextView[1]")
	public static MobileElement descriptionField;

	@WithTimeout(time = 5, unit = TimeUnit.SECONDS)
	@iOSFindBy(id = "Add New Note")
	public static MobileElement addNewNote;

	@iOSFindBy(id = "Description")
	public static MobileElement description;

	@iOSFindBy(id = "Comment")
	public static MobileElement comments;

	@iOSFindBy(id = "Submit")
	public static MobileElement submit;

	@WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSFindBy(id = "//XCUIElementTypeCell[2]/XCUIElementTypeTextView[1]")
	public static MobileElement noteText;

	@WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSFindBy(id = "//XCUIElementTypeTable[@name='DocketingDPFList']/XCUIElementTypeCell[2]/XCUIElementTypeTextView[1]")
	public static MobileElement defaultDescription;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static MobileElement actionsName;

	public void selectCase(String category, String caseNum) {
		refresh();
		selectCaseNumber(category, caseNum);
	}

	public void getActionsPanel(DBType dbType, String el_id) {

		getPanel(ACTIONS);
		String action = "//XCUIElementTypeTable[@name='DocumentList']/child::*//*[contains(@name, '"
				+ getAllColumns(dbType, getID(ACTION_NAME, el_id)) + "')]";

		clickOnPanel(ACTIONS, action);

	}

	public void verifyActionName(DBType dbType, String el_id) {
		verifyTextIsDisplayed(actionsName, getAllColumns(dbType, getID(ACTION_NAME, el_id)).trim());
	}

	public void verifyAddNewNoteDisplayed(String text) {
		verifyTextIsDisplayed(addNewNote, text);
	}

	public void verifyFieldsAreDisplayed(String descriptionText, String commentText, String submitText, DBType dbType,
			String el_id) {

		verifyTextIsDisplayed(description, descriptionText);
		getDefaulDescription(dbType, el_id);
		verifyTextIsDisplayed(comments, commentText);
		verifyTextIsDisplayed(submit, submitText);

	}

	public void getDefaulDescription(DBType dbType, String el_id) {
		try {
			if (getEL_Function(dbType, el_id, 31).equals("SKIP")) {
				assertTrue(descriptionField.getText().equals("Transaction Note"));
			} else {
				assertTrue(getEL_Function(dbType, el_id, 31).equals(descriptionField.getText()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getEL_Function(DBType dbType, String el_id, int index) {
		final String mbrNote = getID(MBR_NOTE, el_id);
		return getRestrictParam(getAllColumns(dbType, mbrNote), index);

	}

	public static void clickOnPanel(Actions panel, String penlRow) {
		try {
			if (isDisplayed(By.xpath(penlRow)) == true) {
				click(penlRow);
			} else {
				getPanel(panel);
				click(penlRow);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

	}

}
