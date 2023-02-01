package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.SiteTableVariable;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class InternalNotePage extends AppiumPageFactory {
//	public InternalNotePage() {
//		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
//	}

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

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Panel:')]")
	public static WebElement judgePanel;

	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	String panel = "";

	public String ifInternalNoteExists(String category, String referral, List<UserInputData> userInputData) {
		String expectedNote = "";
		String siVal = CommonPages.getSiValue(SiteTableVariable.internalNote, userInputData);

		if (siVal.equalsIgnoreCase("y")) {

			Page.performPageLoad(driver);
			panel = getPanel();
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
			expectedNote = getRandomText();
			sendKeys(textField, expectedNote);
			tap(applyBtn);
			Page.performPageLoad(driver);

			String dbNote = getDBInternalNote(panel, category, referral, userInputData);

			assertEquals("PLEASE VERIFY THAT CMA_VALUE.CHM_MOBILE_DATA IS UPDATED CORRECTLY - >>", expectedNote,
					dbNote);

			String txt2 = getEnteredNoteTxt();

			isCaseNoteDisplayed(expectedNote, txt2);

			driver.navigate().back();
			Page.performPageLoad(driver);

			isCategoryNoteDisplayed(expectedNote, referral);

		} else {
			assertTrue(arrow.size() == 2);

		}
		return expectedNote;

	}

	public void navigateAway(String category, String referral, String expectedTxt) {

		contains("Dashboard").click();

		scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");

		isCategoryNoteDisplayed(expectedTxt, referral);

		scrollDownIfNotDisplayed(Actions.containsElement(referral));

		String txt2 = getEnteredNoteTxt();

		isCaseNoteDisplayed(expectedTxt, txt2);

	}

	public void deleteExistingNote(String category, String referral, String expectedTxt,
			List<UserInputData> userInputData) {
		Page.performPageLoad(driver);
		Actions.findElement(By.xpath(Actions.containsElement("Auto-Test:"))).click();
		clearText(expectedTxt);
		tap(applyBtn);
		Page.performPageLoad(driver);

		String dbNote = getDBInternalNote(panel, category, referral, userInputData);
		Assert.assertTrue("PLEASE VERIFY THAT CMA_VALUE.CHM_MOBILE_DATA IS UPDATED CORRECTLY - >>",dbNote.isEmpty());
		String caseDetailNote = Actions.getText(note);
		isCaseNoteDisplayed("Add Internal Note", caseDetailNote);
		driver.navigate().back();
		Page.performPageLoad(driver);
		isCategoryNoteDisplayed("", referral);

		removeAndNavigateAway(category, referral);

	}

	public void removeAndNavigateAway(String category, String referral) {
		contains("Dashboard").click();

		scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");
		isCategoryNoteDisplayed("", referral);

		scrollDownIfNotDisplayed(Actions.containsElement(referral));
		Page.performPageLoad(driver);
		String caseDetailN = Actions.getText(note);
		isCaseNoteDisplayed("Add Internal Note", caseDetailN);
	}

	public String getEnteredNoteTxt() {
		return Actions.getText(Locator.XPATH, Actions.containsElement("Auto-Test:"));
	}

	public void isCategoryNoteDisplayed(String expectedTxt, String referral) {
		assertTrue(
				"NOTE MISMATCH, PLEASE CHECK THE INTERNAL NOTE ON THE REFERRAL CATEGORY PAGE, CASE# "
						+ referral.toUpperCase() + " AND NOTE INTERFACE ->>",
				categoryScreenNote(referral).equals(expectedTxt));
	}

	public void isCaseNoteDisplayed(String expectedTxt, String actualTxt) {
		assertEquals(
				"NOTE MISMATCH, PLEASE CHECK THE INTERNAL NOTE IN CASE INFORMATION SECTION, UNDER THE SYNC ALL DOCUMENTS AND NOTE INTERFACE ->> ",
				expectedTxt, actualTxt);
	}

	public String getDBInternalNote(String panel, String category, String referral, List<UserInputData> userInputData) {
		String peId = DocumentPage.get_pe_id("jud", userInputData);
		String caseId = CommonPages.getCaseID(referral, userInputData);
		String internalNote = DBUtilities.getAllColumns(Actions.replace(Actions.replace(Queries.CMA_CMR_ID,
				"CMR_JU_PE_ID", peId, "CYV_CATEGORY", category, "CMR_CS_CASEID", caseId), "CMR_PANEL_MEMBERS", panel),
				userInputData);

		return DBUtilities.getAllColumns(Actions.replace(Queries.INTERNAL_NOTE, "CMA_CMR_ID", internalNote),
				userInputData);
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
		return "\' Auto-Test: \"" + Utility.getStreamOfRandomInts();
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

	public static String getPanel() {
		Page.performPageLoad(driver);
		String split = "";
		String text = Actions.findElementBy(Locator.XPATH, Actions.containsElement("Panel:")).getText().split(": ")[1];
		if (text.contains("Involvement")) {
			split = text.split(" Involvement")[0];
		} else {
			split = text.split(" Date")[0];
		}
		return split.trim();

	}

}
