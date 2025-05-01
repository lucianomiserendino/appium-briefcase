package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeDBQuery;


import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.mobile.NetworkConnection;
//import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Configuration;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AccessingAnnotatedDocuments extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/preceding::XCUIElementTypeStaticText[@name='+']")
	public static WebElement plusIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/preceding::XCUIElementTypeStaticText[@name='-']")

	public static WebElement minIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='-']/following::XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']")
	public static WebElement originalDoc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='-']/following::XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/following::XCUIElementTypeStaticText[contains(@name, 'Annotated')]")
	public static WebElement annotatedDoc;

	static String PDFPageView = "PDF View";

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public WebElement dashboard;

	@iOSXCUITFindBy(xpath = "label[id='settings-checkbox-1']")
	public static WebElement checkBox;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[2]")
	public static WebElement settingsIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Allow staff to view annotated documents']/following::XCUIElementTypeSwitch[1]")
	public static WebElement viewAnnotatedDoc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Back up Annotations to CM/ECF']/following::XCUIElementTypeSwitch[1]")
	public static WebElement backUpAnnotations;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeToolbar[@name='Toolbar'])[2]/preceding::XCUIElementTypeOther[1]/XCUIElementTypeButton")
	public static List<WebElement> toolBar2;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton")
	public static List<WebElement> navigationTool1;

	@iOSXCUITFindBy(accessibility = "PDF OPTIONS")
	public static WebElement pdfOptions;

	@iOSXCUITFindBy(accessibility = "Annotations")
	public static WebElement annotations;

	@iOSXCUITFindBy(accessibility = "FreeText")
	public static WebElement freeText;

	@iOSXCUITFindBy(accessibility = "Text Annotation")
	public static WebElement textAnnotation;

	@iOSXCUITFindBy(accessibility = "Drawing")
	public static WebElement drawing;

	@iOSXCUITFindBy(accessibility = "Freeform Highlight")
	public static WebElement freeformHighlight;

	@iOSXCUITFindBy(accessibility = "Ink_Magic")
	public static WebElement ink_Magic;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Text Annotation'])[1]")
	public static WebElement sentText;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='Note']")
	public static WebElement note;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='Note']/XCUIElementTypeCell/XCUIElementTypeTextView")
	public static WebElement sentNote;

	@iOSXCUITFindBy(accessibility = "Toolbar")
	public static WebElement toolbar;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Author Name']")
	public static WebElement author;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='Name']")
	public static WebElement name;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
	public static WebElement done;

	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='Search Results']/XCUIElementTypeCell")
	public static List<WebElement> searchResult;

	@iOSXCUITFindBy(accessibility = "Search")
	public static WebElement searchIcon;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeSearchField[@name=\"Search Document\"])[1]")
	public static WebElement searchTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static WebElement searchBTN;

	@iOSXCUITFindBy(accessibility = "ResultsList")
	public static WebElement category;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CM/ECF']")
	public static WebElement cmecf;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='On Device']")
	public static WebElement on_device;
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'FindAppendix?')]")
	public static List<WebElement> appxLink;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, 'Actions')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static List<WebElement> docCategories;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='Wi-Fi']")
	public static WebElement wifi;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch")
	public static WebElement switchBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Page Label']/XCUIElementTypeOther/following:: XCUIElementTypeStaticText")
	public static WebElement pageNumber;

	@iOSXCUITFindBy(accessibility = "PDF View")
	public static WebElement pdfView;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeMenuItem[@name='Delete']")
	public static List<WebElement> delete;

//	public void getAnnotatedDoc() {
//		openPDFDoc(originalDoc);
//		openPDFDoc(annotatedDoc);
//		tap(dashboard);
//	}
//
//	public void openPDFDoc(WebElement el) {
//		CommonPages.getPanel(Panel.Briefs);
//		tap(plusIcon);
//		tap(el);
//		Page.performPageLoad(driver);
//		try {
//			verifyTextPresentInPDF(PDFPageView);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		Actions.tap(close);
//
//		tap(minIcon);
//
//	}
//
//	public static String viewAnnotateDoc(String url) throws IOException {
//		URL pdfUrl = new URL(url);
//		InputStream in = pdfUrl.openStream();
//		BufferedInputStream bf = new BufferedInputStream(in);
//		PDDocument doc = PDDocument.load(bf);
//		PDFTextStripper pdfStrip = new PDFTextStripper();
//		pdfStrip.setStartPage(2);
//		String content = pdfStrip.getText(doc);
//		doc.close();
//		return content;
//	}

	public static int getPageCount(PDDocument doc) {
		int pageCount = doc.getNumberOfPages();
		return pageCount;
	}

//	public void verifyTextPresentInPDF(String textToVerify) throws IOException {
//		try {
//			String pdfOutput = null;
//			URL pdfURL = new URL(driver.getCurrentUrl());
//			BufferedInputStream pdfToParse = new BufferedInputStream(pdfURL.openStream());
//			PDDocument document = PDDocument.load(pdfToParse);
//			pdfOutput = new PDFTextStripper().getText(document);
//			Assert.assertEquals(textToVerify, pdfOutput);
//		}
//
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public void getToggle() {
		if (contains("Dashboard").isDisplayed())
			contains("Dashboard").click();
		Page.sleep(5000);

		settingsIcon.click();

		Assert.assertTrue(Actions.isDisplayed(pdfOptions));

		try {
			if (viewAnnotatedDoc.isDisplayed() == true || backUpAnnotations.isDisplayed() == true) {

				assertTrue("THE TOGGLE ENTITLED \"BACK UP ANNOTATIONS TO CM/ECF\" SHOULD BE TURNED ON BY DEFAULT.",
						Utility.getToggleState(backUpAnnotations));

				Actions.tap(backUpAnnotations);

				assertFalse(Utility.getToggleState(backUpAnnotations));
				assertFalse(Utility.getToggleState(viewAnnotatedDoc));

			} else {
				throw new RuntimeException(
						" THE \"BACK UP ANNOTATIONS TO CM/ECF\" TOGGLE IS MISSING FROM THE SETTINGS PAGE");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void annotateDocument(String docName, String caseNum, List<UserInputData> userInputData) {

		System.out.println("------------------------------------------------------");
		System.out.println("Selected document name: " + docName);
		System.out.println("------------------------------------------------------");

		Actions.tap(annotations);
		if (Actions.isDisplayed(author) == true) {
			if (done.isEnabled() == false) {
				name.sendKeys("Test");
			}

			Actions.tap(done);
		}
		Page.waitForVisibilityOfAllElements(toolBar2, driver);
		String annotation = ifEditingToolsExist(toolBar2);

		Actions.tap(annotations);
		performPageLoad(driver);
		Actions.tap(close);
		Page.waitForVisibilityOfElement(contains(docName), driver).click();
		CommonPages.ifDownloaded(inProgress);
		performPageLoad(driver);
		assertTrue("Verify annotation is saved, document name: " + docName,driver.getPageSource().contains(annotation));

		getBackEndUpdates(caseNum, docName, userInputData);

	}

	public static String ifEditingToolsExist(List<WebElement> tools) {

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

	public static String getEditingToolList(String text) {
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

	public static String draw(String text, WebElement element, String actual) {
		Actions.tap(Locator.ID, text);
		for (int i = 0; i < 2; i++) {
			Utility.tapByCoordinate("pdfX", "pdfY");
		}
		String expected = element.getAttribute("name").trim();
		assertEquals(actual, expected);
		return expected;

	}

	public static String sendText(String text, WebElement element, WebElement sentTxt, String actual) {
		Actions.tap(Locator.ID, text);
		Utility.tapByCoordinate("pdfX", "pdfY");
		Actions.tap(element);
		Actions.sendKeys(element, actual);
		String expected = sentTxt.getText().trim();

		assertEquals(actual, expected);
		return expected;
	}

	public static void assertEquals(String actual, String expected) {
		Assert.assertEquals("THE EDITING TOOLS TO CREATE ANNOTATIONS ARE NOT FUNCTIONING PROPERLY IN PSPDFKIT", actual,
				expected);
	}

	public static void getBackEndUpdates(String uiCaseNum, String uiDocName, List<UserInputData> userInputData) {
		// Fetching data from the database
		List<String[]> dbAnnotatedDocDetails = executeDBQuery(DBUtilities.getText(Queries.annotatedDoc, uiDocName),
				userInputData);

		// Handling case when no data is returned from the database
		if (dbAnnotatedDocDetails == null || dbAnnotatedDocDetails.isEmpty()) {
			throw new AssertionError("No document categories returned from the database.");
		}

		// Extracting data from the database result
		String dbCaseNum = "";
		String dbDocName = "";
		String dbPeId = "";
		for (String[] record : dbAnnotatedDocDetails) {
			dbCaseNum = record[0].trim();
			dbDocName = record[1].trim();
			dbPeId = record[2].trim();
		}

		// Finding pe_id of the logged in judge
		String uiPeId = DocumentPage.get_pe_id("jud", userInputData);

		// Assertions to verify data consistency between UI and database
		Assert.assertEquals("Verify correct pe_id is saved in the mbr_annot_to_doc table", dbPeId, uiPeId);
		Assert.assertEquals("Verify the case number is correct for the annotated document", dbCaseNum, uiCaseNum);
		Assert.assertEquals("Verify correct document is saved in the mbr_annot_to_doc table", dbDocName, uiDocName);

		// Closing the document page
		close.click();
	}

	public List<String> getDocumentCategories() {

		Page.performPageLoad(driver);
		List<String> categories = new ArrayList<>();

		for (int i = 0; i < docCategories.size(); i++) {
			categories.add(docCategories.get(i).getText());
		}
		return categories;
	}

//	public static void wifiOff() throws InterruptedException {
//
//		NetworkConnection mobileDriver = (NetworkConnection) driver;
//		if (mobileDriver.getNetworkConnection() != ConnectionType.AIRPLANE_MODE) {
//			// enabling Airplane mode
//			mobileDriver.setNetworkConnection(ConnectionType.AIRPLANE_MODE);
//		}
//	}

	public static void togglewiFi() {
		driver.activateApp("com.apple.Preferences");
		wifi.click();
		switchBtn.click();
		driver.activateApp(Configuration.getProperty(BUNDLE_ID));

	}

	public static void getRandomDoc(String categoryName) {

		List<String> docName = new ArrayList<>();

		int randomDoc = DocumentPage.getRandomDocument(categoryName);

		for (int i = 0; i < DocumentPage.getSize(categoryName); i++) {

			docName.add(DocumentPage.getText("docCategory", i, categoryName));
		}
		DocumentPage.click(randomDoc, categoryName);
		randomPage();

		pdfView.click();
		String lastViewedPage = splitBy(0);
		close.click();
		getLastViewedPage(randomDoc, 0, lastViewedPage, categoryName);
		close.click();

	}

	public static void randomPage() {
		int pageNum = Integer.parseInt(splitBy(1));
		int lastViewPage = Integer.parseInt(splitBy(0));
		int randomNum;

		if (pageNum == lastViewPage) {
			randomNum = Utility.getRandomNumberInRange(1, pageNum);
			Utility.swipe(randomNum, "left");
		} else {
			randomNum = Utility.getRandomNumberInRange(lastViewPage, pageNum);
			Utility.swipe(randomNum, "right");
		}
	}

	public static void getLastViewedPage(int randomNum, int splitBy, String expectedPageNum, String categoryName) {
		DocumentPage.click(randomNum, categoryName);
		String actualPageNum = splitBy(splitBy);
		Assert.assertEquals(expectedPageNum, actualPageNum);
	}

	public static String splitBy(int index) {
		AccessingAnnotatedDocuments p = new AccessingAnnotatedDocuments();
		return p.pageNumber.getText().split(" of ")[index];
	}

	public void selectRandomDocument(List<UserInputData> userInputData, String cmr_cyv_code, String cmr_ju_pe_id,
			String cmr_cs_caseid) {
		CommonPages.getGroupIcons(GroupIcons.Expand);
		List<String> uiDocCategory = new ArrayList<>();

		List<String> category = getDocumentCategories();

		List<String> dbDocCategories = executeQuery(Actions.replace(DOCUMENT_CATEGORIES, "CMR_CYV_CODE", cmr_cyv_code,
				"CMR_JU_PE_ID", cmr_ju_pe_id, "CMR_CS_CASEID", cmr_cs_caseid), userInputData);

		for (int k = 1; k < dbDocCategories.size(); k++) {
			uiDocCategory.add(category.get(category.size() - k));
		}

		uiDocCategory = category.subList(Math.max(category.size() - dbDocCategories.size(), 0), category.size());

		Assert.assertEquals(uiDocCategory, dbDocCategories);

		int randomDoc = Utility.getRandomNumberInRange(1, category.size() - 1);
		String docName = category.get(randomDoc).trim();
		contains(docName).click();

		Page.sleep(50000);
		Actions.tap(annotations);
		if (Actions.isDisplayed(author) == true) {
			Actions.tap(done);
		}
		ifEditingToolsExist(toolBar2);

		Actions.tap(annotations);
		Actions.tap(close);

		getBackEndUpdates(cmr_cs_caseid, docName, userInputData);
	}

}