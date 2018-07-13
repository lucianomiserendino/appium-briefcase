package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getEL_Function;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.MOTIONS_PETITIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
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
import static gov.uscourts.ao.mobileBriefcase.common.Constants.*;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_UIDocketingDPFPage extends Base {

	public iOS_UIDocketingDPFPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	static String actionName = "//XCUIElementTypeTable[@name='DocumentList']/child::*//*[contains(@name, '"
			+ getAllColumns(DBType.CMKA,ACTION_NAME) + "')]";

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

	public void getCase(String caseNum) {
		refresh();
		selectCaseNumber(MOTIONS_PETITIONS, caseNum);
	}

	public void getActionsPanel() {
		clickOnPanel(ACTIONS, actionName);

	}

	public void verifyActionName() {
		verifyTextIsDisplayed(actionsName, getAllColumns(DBType.CMKA,ACTION_NAME).trim());
	}

	public void verifyAddNewNoteDisplayed(String text) {
		verifyTextIsDisplayed(addNewNote, text);
	}

	public void verifyFieldsAreDisplayed(String descriptionText, String commentText, String submitText) {

		verifyTextIsDisplayed(description, descriptionText);
		getDefaulDescription();
		verifyTextIsDisplayed(comments, commentText);
		verifyTextIsDisplayed(submit, submitText);

	}

	public void getDefaulDescription() {
		try {
			if (getEL_Function(COURT_USERS, 31).equals("SKIP")) {
				assertTrue(descriptionField.getText().equals("Transaction Note"));
			} else {
				assertTrue(getEL_Function(COURT_USERS, 31).equals(descriptionField.getText()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
