package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clicksOn;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.waitForVisibilityOfElement;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Configuration;
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

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, 'Actions')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static List<MobileElement> docCategories;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='Wi-Fi']")
	public static MobileElement wifi;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch")
	public static MobileElement switchBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Page Label']/XCUIElementTypeOther/following:: XCUIElementTypeStaticText")
	public static MobileElement pageNumber;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PDF Page View']")
	public static MobileElement pdfView;

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

	public void annotateDocument(String wifi, String caseNum, List<UserInputData> userInputData) {

		List<String> categories = getDocumentCategories();

		int randomDoc = Utility.getRandomNumberInRange(1, categories.size() - 1);
		String docName = categories.get(randomDoc).trim();
		contains(docName).click();

		if (wifi.equals("OFF")) {
			togglewiFi();
		}
		Actions.tap(annotations);
		if (Actions.isDisplayed(author) == true) {
			Actions.tap(done);
		}
		String annotation = ifEditingToolsExist(toolBar1);

		Actions.tap(annotations);
		Actions.tap(close);

		if (wifi.equals("OFF")) {
			togglewiFi();
		}
		contains(docName).click();
		assertTrue(Actions.isDisplayed(Locator.XPATH, Actions.containsElement(annotation)));

		getBackEndUpdates(caseNum, docName, userInputData);

	}

	public static boolean getToggleState(MobileElement el) {

		boolean status = false;

		if (attributeEquals(el, "0")) {
			status = false;

		} else if (attributeEquals(el, "1")) {
			status = true;
		}
		return status;
	}

	public static boolean attributeEquals(MobileElement el, String index) {
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

	public static boolean isDisplayed(String query) {

		String annotatedDoc = DBUtilities.execute(DBType.CMKA, query, 0).toString();

		boolean isDisplayed = false;

		try {
			MobileElement elem = waitForVisibilityOfElement(
					findElementBy(Locator.XPATH, Actions.containsElement(annotatedDoc)), driver);
			if (elem.isDisplayed())
				isDisplayed = true;
		} catch (WebDriverException e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	public static void getBackEndUpdates(String caseNum, String docName, List<UserInputData> userInputData) {
		List<String> assignInfo = new ArrayList<>();

		for (int i = 2; i <= 5; i++) {
			assignInfo = execute(DBUtilities.getText(Queries.annotatedDoc, docName), i, userInputData);

			if (i == 2) {
				assertTrue(assignInfo.contains(caseNum));

			} else if (i == 3) {

				assertTrue(assignInfo.contains(docName));
			}
		}

	}

	public List<String> getDocumentCategories() {

		getGroupIcons();
		Page.performPageLoad(driver);
		List<String> categories = new ArrayList<>();

		for (int i = 0; i < docCategories.size(); i++) {
			categories.add(docCategories.get(i).getText());
		}
		return categories;
	}

	public static void wifiOff() throws InterruptedException {

		NetworkConnection mobileDriver = (NetworkConnection) driver;
		if (mobileDriver.getNetworkConnection() != ConnectionType.AIRPLANE_MODE) {
			// enabling Airplane mode
			mobileDriver.setNetworkConnection(ConnectionType.AIRPLANE_MODE);
		}
	}

	public static void togglewiFi() {
		driver.activateApp("com.apple.Preferences");
		wifi.click();
		switchBtn.click();
		driver.activateApp(Configuration.getProperty(BUNDLE_ID));

	}


	public static void swipe(int index) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		for (int i = 0; i < index; i++) {
			scrollObject.put("direction", "right");
			js.executeScript("mobile: scroll", scrollObject);
		}
	}

	public static List<MobileElement> getDocName(String docCategory, int index) {
		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + docCategory
				+ "']/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther["
				+ index + "]/XCUIElementTypeStaticText"));
	}

	public static void getRandomDoc() {

		List<String> docName = new ArrayList<>();
		List<String> pages = new ArrayList<>();

		List<MobileElement> categories = getDocName("Briefs", 1);

		int randomDoc = Utility.getRandomInt(categories.size());
		System.out.println(randomDoc);

		for (int i = 0; i < categories.size(); i++) {

			docName.add(getDocName("Briefs", 1).get(i).getText());
			pages.add(getDocName("Briefs", 2).get(i).getText().split("Pages: ")[1].trim());

		}

		String expectedPageNum = pages.get(randomDoc);
		System.out.println(expectedPageNum);

		categories.get(randomDoc).click();

		String actualPageNum = splitBy(1);

		Assert.assertEquals(expectedPageNum, actualPageNum);

		if (Integer.parseInt(actualPageNum) > 1) {
			swipe(Utility.getRandomInt(Integer.parseInt(actualPageNum)));
		}

		pdfView.click();

		String lastViewedPage = splitBy(0);
		close.click();

		getDocName("Briefs", 1).get(randomDoc).click();

		String expectedPage = splitBy(0);

		Assert.assertEquals(lastViewedPage, expectedPage);

	}

	public static String splitBy(int index) {
		return pageNumber.getText().split(" of ")[index];
	}


}
