package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.*;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class AssignmentNotesPage {

	public AssignmentNotesPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='Assignments']/XCUIElementTypeCell[5]/XCUIElementTypeStaticText")
	public static MobileElement assignmentNoteDate;

	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='Assignments']/XCUIElementTypeCell[6]/XCUIElementTypeStaticText")
	public static MobileElement assignmentAssignedNoteDate;

	@iOSFindBy(accessibility = "Assignment Note")
	public static MobileElement assignmentNote;

	@iOSFindBy(accessibility = "Assignment Assigned Note")
	public static MobileElement assignmentAssignedNote;

	@iOSFindBy(xpath = "//XCUIElementTypeOther[@name='Private Note - Assignment Note']/XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
	public static MobileElement assignmentNoteText;

	@iOSFindBy(xpath = "//XCUIElementTypeOther[@name='Private Note - Assignment Assigned Note']/XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
	public static MobileElement assignmentAssignedNoteText;

	public void selectAssignment(String assignment) {
		clickOnPanel(Actions.ASSIGNMENTS, assignment);
	}

	public void verifyAssignmentNotes(String assignmentNotes) {
		assertTrue(elementIsDisplayed(assignmentNotes) == true);
	}

	public void verifyAssignmentDates() {
		assertAssignmentNotes(" THE DATE OF THE ASSIGNMENT NOTE IS NOT DISPLAYED ", assignmentNoteDate,
				ASSIGNMENT_NOTE_DATE, assignmentAssignedNoteDate, ASSIGNMENT_ASSIGNED_NOTE_DATE);
	}

	public void verifyNoteDescription() {

		assertAssignmentNotes("NOTE DESCRIPTION IS NOT DISPLYED", assignmentNote, ASSIGNMENT_NOTE,
				assignmentAssignedNote, ASSIGNMENT_ASSIGNED_NOTE);
	}
	
	public void verifyText() {
		assertAssignmentNotes("TEXT IS NOT DISPLYED", assignmentNoteText, ASSIGNMENT_NOTE_TEXT,
				assignmentAssignedNoteText, ASSIGNMENT_ASSIGNED_NOTE_TEXT);	
	}

	public static void assertAssignmentNotes(String message, MobileElement el1, String assign1, MobileElement el2,
			String assign2) {
		assertEquals(getText(el1), assign1);
		assertEquals(getText(el2), assign2);

	}

}
