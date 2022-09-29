package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clear;
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

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[4]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeStaticText")
	public static WebElement addIntNote;

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

	public void ifInternalNoteExists(List<UserInputData> pacerInputData) {
		String siVal = CommonPages.getSiValue("briefcaseInternalNote", pacerInputData);
		try {
			if (siVal.equalsIgnoreCase("y")) {
				tap(addIntNote);
				Page.performPageLoad(driver);
				assertTrue(isDisplayed(cancelBtn));
				assertTrue(isDisplayed(desc));
				assertTrue(isDisplayed(applyBtn));
				clear(textField);
				assertTrue(isDisplayed(numOfChar));
				String text = getRandomText();
				int charLeft = getLength(text);
				sendKeys(textField, text);
				assertTextIsDisplayed("Characters left: " + charLeft + "");
				tap(applyBtn);
				Page.performPageLoad(driver);
				assertTextIsDisplayed(text);
				tap(addIntNote);
				clear(textField);
				tap(applyBtn);
				assertTrue(isDisplayed(addNote));

			} else {
				throw new RuntimeException(
						"----------->PLEASE SET THE SITE TABLE VARIABLE \"BRIEFCASETARGETONLY\" TO \"Y\"");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

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
