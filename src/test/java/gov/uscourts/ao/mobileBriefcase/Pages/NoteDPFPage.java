package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.NOTE_DPF_DEFAULT_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCaseNumber;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class NoteDPFPage extends Base {

	public NoteDPFPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	String actions = "Actions";

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='DocumentList']/XCUIElementTypeCell/XCUIElementTypeStaticText[1]")
	public static List<MobileElement> senDirections;

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
	

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static MobileElement sendDirectionsText;

	public void getCase(String caseNum) {

		selectCaseNumber(Actions.MOTIONS_PETITIONS, caseNum);
	}

	public void getActionsPanel(String element) {

		getPanel(actions, senDirections, element);
	}

	public void verifyActionName() {

		verifyTextIsDisplayed(sendDirectionsText, getAllColumns(ACTION_NAME));
	}

	public void verifyAddNewNoteDisplayed(String text) {

		verifyTextIsDisplayed(addNewNote, text);
	}

	public void verifyFieldsAreDisplayed(String descriptionText, String commentText, String submitText) {

		verifyTextIsDisplayed(description, descriptionText);
		verifyTextIsDisplayed(comments, commentText);
		verifyTextIsDisplayed(submit, submitText);

		System.out.println(getText(noteText)+"  ui====================");
		System.out.println(getDefaultDescription(NOTE_DPF_DEFAULT_DESCRIPTION)+"  db===============");

	}

	public String getDefaultDescription(String query) {
		return getAllColumns(query).substring(31).split(",")[0].replaceAll("'", "");
	}

}
