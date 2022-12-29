package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.List;
import java.util.Random;

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

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"GroupIcon\"])[1]/preceding::XCUIElementTypeStaticText[@name=\"\"]")
	public static List<WebElement> arrow;

	public void ifInternalNoteExists(String referral, List<UserInputData> userInputData) {
		String siVal = CommonPages.getSiValue("briefcaseInternalNote", userInputData);

		if (siVal.equalsIgnoreCase("y")) {
			String text = Actions.getText(note);
			tap(note);
			Page.performPageLoad(driver);
			assertTrue(isDisplayed(cancelBtn));
			assertTrue(isDisplayed(desc));
			assertTrue(isDisplayed(applyBtn));
			clearText(text);
			assertTrue(isDisplayed(numOfChar));
			String newNote = randomCharArray(201);
			sendKeys(textField, newNote);
			assertTrue("THE CHARACTER LIMIT IS 200: ", textField.getText().length() == 200);
			assertTrue(Actions.contains("Characters left: ").getText().split(": ")[1].trim().equals("0"));
			textField.clear();
			String txt = getRandomText();
			sendKeys(textField, txt);
			tap(applyBtn);
			Page.performPageLoad(driver);
			assertTextIsDisplayed(txt);
			driver.navigate().back();
			assertTrue(categoryScreenNote(referral).equals(txt));

		} else {
			assertTrue(arrow.size() == 2);

		}

	}

	public static void clearText(String text) {
		String existingNote = textField.getAttribute("value");

		if (text.trim().equals("Add Internal Note")) {
			assertNull(
					"CASE INFORMATION SECTION: IF THERE IS ALREADY A NOTE, THE NOTE SHOULD BE DISPLAYED INSTEAD OF \"ADD INTERNAL NOTE\".",
					existingNote);

		} else {
			textField.clear();
		}

	}

	public static String categoryScreenNote(String referral) {
		return Actions.findElementBy(Locator.XPATH,
				Actions.containsElement(referral) + "/following::XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
				.getText();
	}

	public static String getRandomText() {
		return "Auto-Test: " + Utility.getStreamOfRandomInts();
	}

	public static int getLength(String txt) {
		return 200 - txt.length();
	}

	public String charLeft(String charLeft) {
		return "//XCUIElementTypeStaticText[@name='Characters left: " + charLeft + "']";
	}

	public static void assertTextIsDisplayed(String txt) {
		assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(txt)));
	}

	public static String randomCharArray(int len) {

		String randomChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";

		StringBuilder b = new StringBuilder();

		for (int i = 0; i < len; i++) {
			int randIdx = new Random().nextInt(randomChar.length());
			char randChar = randomChar.charAt(randIdx);
			b.append(randChar);
		}

		return b.toString();

	}

}
