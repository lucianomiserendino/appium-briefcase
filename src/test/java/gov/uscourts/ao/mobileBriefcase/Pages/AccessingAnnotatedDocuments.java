package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clicksOn;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;

import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AccessingAnnotatedDocuments extends Base {
	public AccessingAnnotatedDocuments() {
		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/preceding::XCUIElementTypeStaticText[@name='+']")
	public static MobileElement plusIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/preceding::XCUIElementTypeStaticText[@name='-']")

	public static MobileElement minIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='-']/following::XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']")
	public static MobileElement originalDoc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='-']/following::XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/following::XCUIElementTypeStaticText[contains(@name, 'Annotated')]")
	public static MobileElement annotatedDoc;

	static String PDFPageView = "PDF View";

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public MobileElement dashboard;

	@iOSXCUITFindBy(xpath = "label[id='settings-checkbox-1']")
	public static MobileElement checkBox;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[2]")
	public static MobileElement settingsIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Allow staff to view annotated documents']/following::XCUIElementTypeSwitch[1]")
	public static MobileElement viewAnnotatedDoc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Back up Annotations to CM/ECF']/following::XCUIElementTypeSwitch[1]")
	public static MobileElement backUpAnnotations;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeToolbar[@name='Toolbar'])[1]/following::XCUIElementTypeOther[1]/XCUIElementTypeButton")
	public static List<MobileElement> toolBar1;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton")
	public static List<MobileElement> navigationTool1;

	@iOSXCUITFindBy(accessibility = "PDF OPTIONS")
	public static MobileElement pdfOptions;

	@iOSXCUITFindBy(accessibility = "Annotations")
	public static MobileElement annotations;

	@iOSXCUITFindBy(accessibility = "FreeText")
	public static MobileElement freeText;

	@iOSXCUITFindBy(accessibility = "Text Annotation")
	public static MobileElement textAnnotation;

	@iOSXCUITFindBy(accessibility = "Drawing")
	public static MobileElement drawing;

	@iOSXCUITFindBy(accessibility = "Freeform Highlight")
	public static MobileElement freeformHighlight;

	@iOSXCUITFindBy(accessibility = "Ink_Magic")
	public static MobileElement ink_Magic;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Text Annotation'])[1]")
	public static MobileElement sentText;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='Note']")
	public static MobileElement note;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='Note']/XCUIElementTypeCell/XCUIElementTypeTextView")
	public static MobileElement sentNote;

	@iOSXCUITFindBy(accessibility = "Toolbar")
	public static MobileElement toolbar;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Author Name']")
	public static MobileElement author;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
	public static MobileElement done;

	@iOSXCUITFindBy(accessibility = "Close")
	public static MobileElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='Search Results']/XCUIElementTypeCell")
	public static List<MobileElement> searchResult;

	@iOSXCUITFindBy(accessibility = "Search")
	public static MobileElement searchIcon;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeSearchField[@name=\"Search Document\"])[1]")
	public static MobileElement searchTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static MobileElement searchBTN;

	@iOSXCUITFindBy(accessibility = "ResultsList")
	public static MobileElement category;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CM/ECF']")
	public static MobileElement cmecf;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='On Device']")
	public static MobileElement on_device;
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'FindAppendix?')]")
	public static List<MobileElement> appxLink;

	public void getAnnotatedDoc() {
		openPDFDoc(originalDoc);
		openPDFDoc(annotatedDoc);
		tap(dashboard);
	}

	public void openPDFDoc(MobileElement el) {
		CommonPages.getPanel(Panel.Briefs);
		tap(plusIcon);
		tap(el);
		Page.performPageLoad(driver);
		try {
			verifyTextPresentInPDF(PDFPageView);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Actions.tap(close);

		tap(minIcon);

	}

	public static String viewAnnotateDoc(String url) throws IOException {
		URL pdfUrl = new URL(url);
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		PDFTextStripper pdfStrip = new PDFTextStripper();
		pdfStrip.setStartPage(2);
		String content = pdfStrip.getText(doc);
		doc.close();
		return content;
	}

	public static int getPageCount(PDDocument doc) {
		int pageCount = doc.getNumberOfPages();
		return pageCount;
	}

	public void verifyTextPresentInPDF(String textToVerify) throws IOException {
		try {
			String pdfOutput = null;
			URL pdfURL = new URL(driver.getCurrentUrl());
			BufferedInputStream pdfToParse = new BufferedInputStream(pdfURL.openStream());
			PDDocument document = PDDocument.load(pdfToParse);
			pdfOutput = new PDFTextStripper().getText(document);
			Assert.assertEquals(textToVerify, pdfOutput);
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getToggle() {
		if (contains("Dashboard").isDisplayed())
			contains("Dashboard").click();
		Page.sleep(5000);

		settingsIcon.click();

		Assert.assertTrue(Actions.isDisplayed(pdfOptions));

		try {
			if (viewAnnotatedDoc.isDisplayed() == true || backUpAnnotations.isDisplayed() == true) {

				assertTrue("THE TOGGLE ENTITLED \"BACK UP ANNOTATIONS TO CM/ECF\" SHOULD BE TURNED ON BY DEFAULT.",
						getToggleState(backUpAnnotations));

				Actions.tap(backUpAnnotations);

				assertFalse(getToggleState(backUpAnnotations));
				assertFalse(getToggleState(viewAnnotatedDoc));

			} else {
				throw new RuntimeException(
						" THE \"BACK UP ANNOTATIONS TO CM/ECF\" TOGGLE IS MISSING FROM THE SETTINGS PAGE");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void annotateDocument() {

		Actions.tap(annotations);
		if (Actions.isDisplayed(author) == true) {
			Actions.tap(done);
		}
		String annotation = ifEditingToolsExist(toolBar1);

		Actions.tap(annotations);
		Actions.tap(close);
		assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(annotation)));

	}

	public void addAnnotation() {

	}

	public boolean getToggleState(MobileElement el) {

		boolean status = false;

		if (attributeEquals(el, "0")) {
			status = false;

		} else if (attributeEquals(el, "1")) {
			status = true;
		}
		return status;
	}

	public boolean attributeEquals(MobileElement el, String index) {
		return el.getAttribute("value").equals(index);
	}

	public String ifEditingToolsExist(List<MobileElement> tools) {

		List<String> list = new ArrayList<>();

		String tool = "";

		for (int i = 0; i < tools.size(); i++) {

			tool = tools.get(i).getAttribute("name").trim();

			if (!tool.equals("Text Highlight") & !tool.equals("Undo") & !tool.equals("Redo") & !tool.equals("Done")
					& !tool.equals("Eraser") & !tool.equals("Line_Arrow") & !tool.equals("Image")
					& !tool.equals("SelectionTool"))

				list.add(tool);
		}

		int index = Utility.getRandomNumberInRange(1, list.size() - 1);
		String text = list.get(index).trim();

		// String text = list.get(5).trim();

		return getEditingToolList(text);

	}

	public String getEditingToolList(String text) {
		String expected = "";

		String actual = text + "_" + Utility.getStreamOfRandomInts();

		if (text.equals("FreeText")) {

			expected = sendText(text, textAnnotation, sentText, actual);

		} else if (text.equals("Text")) {

			expected = sendText(text, note, sentNote, actual);

		} else if (text.equals("Ink_Pen")) {

			expected = draw(text, drawing, "Drawing");

		} else if (text.equals("Ink_Highlighter")) {

			expected = draw(text, freeformHighlight, "Freeform Highlight");

		} else if (text.equals("Ink_Magic")) {

			expected = draw(text, drawing, "Drawing");
		}
		return expected;

	}

	public String draw(String text, MobileElement element, String actual) {
		Actions.tap(Locator.ID, text);
		for (int i = 0; i < 2; i++) {
			Utility.tapByCoordinate("pdfX", "pdfY");
		}
		String expected = element.getAttribute("name").trim();
		assertEquals(actual, expected);
		return expected;

	}

	public String sendText(String text, MobileElement element, MobileElement sentTxt, String actual) {
		Actions.tap(Locator.ID, text);
		Utility.tapByCoordinate("pdfX", "pdfY");
		Actions.tap(element);

		Actions.sendKeys(element, actual);
		String expected = sentTxt.getText().trim();

		assertEquals(actual, expected);
		return expected;
	}

	public void assertEquals(String actual, String expected) {
		Assert.assertEquals("THE EDITING TOOLS TO CREATE ANNOTATIONS ARE NOT FUNCTIONING PROPERLY IN PSPDFKIT", actual,
				expected);
	}

	public void searchForAppendix() {
		String text = "Appx";
		performPageLoad(driver);
		clicksOn(searchIcon);
		searchTextField.clear();
		Actions.sendKeys(searchTextField, text);
		String index = Utility.clickOnNumberInRange(searchResult);
		if (!(index == null)) {
			Utility.clickOnNumberInRange(appxLink);
			performPageLoad(driver);
			Assert.assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(text)));
		} else {
			throw new RuntimeException("THIS DOCUMENT DOES NOT CONTAIN ANY HYPERLINKS");
		}

	}

}
