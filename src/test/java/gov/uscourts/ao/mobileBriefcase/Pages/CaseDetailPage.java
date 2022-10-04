package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.List;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CaseDetailPage extends Base {
	public CaseDetailPage() {
		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[4]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeStaticText/preceding:: XCUIElementTypeStaticText[1]")
	public static WebElement note;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Cancel\"]")
	public static WebElement cancelBtn;

	@iOSXCUITFindBy(id = "Internal Note")
	public static WebElement desc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Apply\"]")
	public static WebElement applyBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Briefcase [Test]\"]/XCUIElementTypeWindow[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextView")
	public static WebElement textField;

	@iOSXCUITFindBy(id = "Characters left: 200")
	public static WebElement numOfChar;

	@iOSXCUITFindBy(id = "Add Internal Note")
	public static WebElement addNote;

	public void ifInternalNoteExists(String referral, List<UserInputData> userInputData) {
		String siVal = CommonPages.getSiValue("briefcaseInternalNote", userInputData);
		try {
			if (siVal.equalsIgnoreCase("y")) {
				String text = Actions.getText(note);
				tap(note);
				Page.performPageLoad(driver);
				assertTrue(isDisplayed(cancelBtn));
				assertTrue(isDisplayed(desc));
				assertTrue(isDisplayed(applyBtn));
				clearText(text);
				assertTrue(isDisplayed(numOfChar));
				String newNote = getRandomText();
				int charLeft = getLength(newNote);
				sendKeys(textField, newNote);
				assertTextIsDisplayed("Characters left: " + charLeft + "");
				tap(applyBtn);
				Page.performPageLoad(driver);
				assertTextIsDisplayed(newNote);
				driver.navigate().back();
				assertTrue(categoryScreenNote(referral).equals(newNote));

			} else {
				throw new RuntimeException(
						"----------->PLEASE SET THE SITE TABLE VARIABLE \"BRIEFCASETARGETONLY\" TO \"Y\"");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void clearText(String text) {
		String existingNote = textField.getAttribute("value");

		if (text.trim().equals("Add Internal Note")) {
			assertTrue(existingNote.length() == 0);
		} else {

			assertTrue(existingNote.contains(text));
			textField.clear();
		}

	}

	public String categoryScreenNote(String referral) {
		return Actions.findElementBy(Locator.XPATH,
				Actions.containsElement(referral) + "/following::XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
				.getText();
	}

	public String getRandomText() {
		return "Auto-Test: " + Utility.getStreamOfRandomInts();
	}

	public int getLength(String txt) {
		return 200 - txt.length();
	}

	public String charLeft(String charLeft) {
		return "//XCUIElementTypeStaticText[@name='Characters left: " + charLeft + "']";
	}

	public void assertTextIsDisplayed(String txt) {
		assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(txt)));
	}

}
