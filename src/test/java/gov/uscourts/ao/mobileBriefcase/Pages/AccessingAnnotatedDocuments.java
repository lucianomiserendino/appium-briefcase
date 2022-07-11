package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;

import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AccessingAnnotatedDocuments extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/preceding::XCUIElementTypeStaticText[@name='+']")
	public static MobileElement plusIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/preceding::XCUIElementTypeStaticText[@name='-']")

	public static MobileElement minIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='-']/following::XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']")
	public static MobileElement originalDoc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='-']/following::XCUIElementTypeStaticText[@name='Brief for 2441 REPLACED']/following::XCUIElementTypeStaticText[contains(@name, 'Annotated')]")
	public static MobileElement annotatedDoc;

	static String close = "Close";

	static String PDFPageView = "PDF View";

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public MobileElement dashboard;

	@iOSXCUITFindBy(xpath = "label[id='settings-checkbox-1']")
	public static MobileElement checkBox;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[2]")
	public static MobileElement settingsIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Allow staff to view annotated documents']")
	public static MobileElement viewAnnotatedDoc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Back up Annotations to CM/ECF']")
	public static MobileElement backUpAnnotations;

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
		tap(Locator.NAME, close);
		tap(minIcon);

	}

	public void getToggle(List<UserInputData> userInputData) {

		if

		(contains("Dashboard").isDisplayed())
			contains("Dashboard").click();
		Page.sleep(5000);

		try {
			if (viewAnnotatedDoc.isDisplayed() == true || backUpAnnotations.isDisplayed()) {

				Assert.assertTrue(viewAnnotatedDoc.isSelected());
				Assert.assertTrue(backUpAnnotations.isSelected());

			} else {
				throw new RuntimeException(
						"The toggle entitled \"Back up Annotations to CM/ECF & Allow staff to view annotated documents toggle\" should be turned on by default.");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

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

}
