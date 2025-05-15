package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.NON_ORALLY_ARGUED_CASES;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProposedOrdersPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Proposed Orders']/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> proposedOrder;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='*not selected*']/following:: XCUIElementTypeStaticText[@name='Select']")
	public static WebElement select;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeToolbar[@name='Toolbar'])[1]/following::XCUIElementTypeOther[1]/XCUIElementTypeButton")
	public static List<WebElement> toolBar1;

	@iOSXCUITFindBy(accessibility = "Annotations")
	public static WebElement annotations;

	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Applied Referrals')]")
	public static List<WebElement> appliedRefs;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(id = "Categories")
	public static WebElement categories;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> caseNum;

	@iOSXCUITFindBy(accessibility = "PDF View")
	public static List<WebElement> pdf;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, '.pdf')]")
	public static WebElement preSelctedPDF;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	static String panel = "";
	static String yes = "Yes";
	static String ok = "OK";
	static String submit = "Submit";

	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	public static String selectRandomCaseNumber(String pe_id, List<UserInputData> userInputData) {
		String caseN = getReffCategories(pe_id, userInputData);
		WebElement uiResult = Actions.findElementBy(Locator.XPATH,
				"//XCUIElementTypeStaticText[contains(@name, '" + caseN + "')]");

		System.out.println("------------------------------------------------------");
		System.out.println("Selected category name: " + caseN);
		System.out.println("------------------------------------------------------");
		uiResult.click();
		Page.performPageLoad(driver);
		return caseN;

	}

	public static String getReffCategories(String pe_id, List<UserInputData> userInputData) {
	    List<String> referralCategories = executeQuery(
	            getID(replace(NON_ORALLY_ARGUED_CASES, "CMR_CYV_CODE", "lbrrpt"), pe_id), userInputData);

	    String caseNum = "";

	    boolean elementFound = false;
	    int maxScrollAttempts = 18;
	    int scrollAttempts = 0;

	    while (!elementFound && scrollAttempts < maxScrollAttempts) {
	        for (String category : referralCategories) {
	            Utility.scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");
	    		System.out.println("------------------------------------------------------");
	    		System.out.println("Selected case number: " + category);
	    		System.out.println("------------------------------------------------------");
	            performPageLoad(driver);

	            String caseN = findReferralWithProposedOrders(category, pe_id, userInputData);

	            if (!caseN.isEmpty()) {
	                caseNum = caseN;
	                elementFound = true;
	                break;
	            }
	        }

	        if (!elementFound) {
	            dashboard.click();
	            Utility.scroll(categories, "up");
	            scrollAttempts++;
	        }
	    }

	    return caseNum;
	}

	public static String findReferralWithProposedOrders(String categoryName, String pe_id,
			List<UserInputData> userInputData) {
		
	    Pattern pattern = Pattern.compile("^\\d{2}-\\d{3,5} .+");

	    List<WebElement> filtered = caseNum.stream()
	        .filter(el -> pattern.matcher(el.getAttribute("name")).matches())
	        .collect(Collectors.toList());

		List<String> list = Utility.retrieveAllReferrals(filtered, " ", 0);

		String caseNum = "";

		Boolean elementNotFound = true;

		while (elementNotFound) {

			for (int i = 0; i < list.size(); ++i) {

				caseNum = list.get(i);

				String cmr_cs_caseid = CommonPages.getCaseID(caseNum, userInputData);
				String cmr_cyv_code = CommonPages.cmr_cyv_code(categoryName, cmr_cs_caseid, userInputData).trim();
				String cmr_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);

				List<String> dbDoCategories = executeQuery(Actions.replace(DOCUMENT_CATEGORIES, "CMR_CYV_CODE",
						cmr_cyv_code, "CMR_JU_PE_ID", cmr_ju_pe_id, "CMR_CS_CASEID", cmr_cs_caseid), userInputData);

				if (dbDoCategories.contains("Proposed Orders")) {

					elementNotFound = false;

					break;

				} else {

					elementNotFound = true;
				}

			}

		}
		return caseNum;
	}

	public static String verifyDocumentIsDownloaded() {
	    String docName = "";
	    CommonPages page = new CommonPages();
	    page.getPanel(Panel.Proposed_Orders);
	    Page.performPageLoad(driver);

	    List<WebElement> docList = getDocList("Proposed Orders");

	    boolean elementNotFound = true;

	    while (elementNotFound && !docList.isEmpty()) {
	        for (WebElement doc : docList) {
	            String name = doc.getText();
	            doc.click();

	            // Wait for the page to load and check if the document is downloaded
	            Page.performPageLoad(driver);
	            ifDownloaded(inProgress);

	            if (!pdf.isEmpty()) {
	                close.click();
	                docName = name;
	                elementNotFound = false;
	                break;
	            }
	        }

	        // Refresh the document list if the element was not found in the current iteration
	        if (elementNotFound) {
	            docList = getDocList("Proposed Orders");
	        }
	    }

	    return docName;
	}


	public static List<WebElement> getDocList(String panel) {

		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + panel
				+ "']/following::XCUIElementTypeStaticText[contains(@name, ',')]/preceding::XCUIElementTypeStaticText[1]"));

	}

	public String submitDocWPDPF() {
	    Assert.assertTrue("Verify the order is preselected in the Upload Documents interface",
	            Actions.isDisplayed(preSelctedPDF));

	    String preSelectedPdf = preSelctedPDF.getText().trim();
	    String description = Utility.getStreamOfRandomInts();

	    // Send keys to the text field matching the pre-selected PDF's value
	    Actions.sendKeys(Locator.XPATH, "//XCUIElementTypeTextField[@value='" + preSelectedPdf + "']", description);

	    try {
	        // Scroll down if the submit button is not displayed and then click submit
	        scrollDownIfNotDisplayed(containsElement(submit));
	        
	        // Tap the OK button
	        tap(Locator.XPATH, containsElement(ok));
	    } catch (WebDriverException e) {
	        System.err.println("WebDriverException caught: " + e.getMessage());
	    }
	    ifDownloaded(inProgress);
	    return description;
	}


	public void verifyDocWPIsSupported(String pdfName) {
		Assert.assertTrue("Verify Briefcase supports the docWPText TPF ",
				Actions.isDisplayed(Locator.XPATH,
						"//*[contains(@name, 'Docket Text')]/following:: XCUIElementTypeStaticText[contains(@name, '"
								+ pdfName.trim() + "')]"));
	}

}