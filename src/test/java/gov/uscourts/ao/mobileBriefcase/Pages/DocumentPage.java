package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeDBQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.SiteTableVariable;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DocumentPage extends AppiumPageFactory {

//	public DocumentPage() {
//		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
//	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> regCaseNum;

	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, 'Actions')]/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static List<WebElement> docCategories;

	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<WebElement> GroupIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Applied Referrals')]")
	public static List<WebElement> appliedRefs;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'linked')]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static List<WebElement> targetCase;

	@iOSXCUITFindBy(xpath = "//*[@name='Applied Referrals' or @name='Associated Cases']/following::XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> appliedCase;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Bookmark']/preceding:: XCUIElementTypeStaticText[contains(@name, 'Panel:')]/preceding:: XCUIElementTypeStaticText[1]")
	public static List<WebElement> caseOncalender;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'EN BANC ')]")
	public static List<WebElement> enBanc;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Downloaded_Container'])[1]")
	public static WebElement downloaded;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PDF Page View']")
	public static List<WebElement> pdfPageView;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'View Case Info')]")
	public static List<WebElement> viewCaseInfo;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Docket Entries ')]")
	public static WebElement docketEntries;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Progress halted' or @name='In progress']")
	public static List<WebElement> progressInd;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'EN BANC ')]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static List<WebElement> enBancCases;
	
	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='OK']")
	public static WebElement okBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText")
	public static WebElement collapseBtn;

	static String panel = "";
	String randomCategory = "";
	public static String randomDocument = "";
	public final static String jud = "jud";
	public final static String stf = "stf";
	public final static String judFirstName = "judFirstName";
	public final static String stfFirstName = "stfFirstName";

	public static String cs_caseid = "";
	public static String caseNum = "";
	public static String category = "";
	public static String cmr_cyv_code = "";

	public static String selectRandomItem(String query, String xpath, List<UserInputData> userInputData) {
		String category = "";
		List<String> list = null;

		List<String> dbResult = executeQuery(query, userInputData);
		list = dbResult;
		if (list.contains("Reference Documents") | list.contains("Pending Tasks") | list.contains("Cases on Calendar")
				| list.contains("No Argument Case"))
			list.remove("Reference Documents");
		list.remove("Pending Tasks");
		list.remove("Cases on Calendar");
		list.remove("No Argument Case");
		list.remove("Oral Argument");
		sort(list);

		category = list.get(Utility.getRandomInt(list.size() - 1));
		scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");

		return category;

	}

	public void selectRandomCategory(String enBanc, List<UserInputData> userInputData) {
	    StringBuilder set = new StringBuilder();
	    String peId = DocumentPage.get_pe_id("jud", userInputData);
	    String query;
	    String siValue = CommonPages.getSiValue(SiteTableVariable.targetOnly, userInputData);

	    if ("y".equals(enBanc)) {
	        set.append("in ('EN BANC')");
	    } else {
	        set.append("not in ('EN BANC')");
	    }

	    if ("y".equals(siValue)) {
	        query = Queries.FIND_ALL_NON_ORALLY_ARGUED_CASES.replace("TEXT", set.toString());
	    } else {
	        query = Queries.FIND_ALL_ORALLY_ARGUED_CASES.replace("TEXT", set.toString());
	    }

	    List<String[]> foundCases = executeDBQuery(getID(query, peId), userInputData);

	    // Categories to exclude
	    Set<String> excludedCategories = new HashSet<>(Arrays.asList(
	        "Reference Documents",
	        "Pending Tasks",
	        "Cases on Calendar",
	        "No Argument Case",
	        "Oral Argument"
	    ));

	    if (foundCases != null && !foundCases.isEmpty()) {
	        List<String[]> filteredCases = foundCases.stream()
	            .filter(record -> !excludedCategories.contains(record[2].trim()))
	            .collect(Collectors.toList());

	        if (filteredCases.isEmpty()) {
	            throw new RuntimeException("No valid categories found after filtering.");
	        }

	        int randomIndex = new Random().nextInt(filteredCases.size());
	        String[] randomRecord = filteredCases.get(randomIndex);

	        cs_caseid = randomRecord[0].trim();
	        caseNum = randomRecord[1].trim();
	        category = randomRecord[2].trim();
	        cmr_cyv_code = randomRecord[3].trim();

	        scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category + "')]");
	        System.out.println("------------------------------------------------------");
	        System.out.println("Selected category name: " + category);
	        System.out.println("------------------------------------------------------");
	    }
	}


	public void selectRandomReferral() {

		scrollDownIfNotDisplayed("//XCUIElementTypeStaticText[contains(@name, '" + caseNum + "')]");
		System.out.println("------------------------------------------------------");
		System.out.println("Selected case number: " + caseNum);
		System.out.println("------------------------------------------------------");

	}

	public String selectRandomSTFCategory(List<UserInputData> userInputData) {
		
        collapseBtn.click(); 
        
		String category = "";
		String category_code = "";

		List<String> sfa_display = getAssignmentCategories(2, userInputData);
		List<String> smr_sfa_code = getAssignmentCategories(3, userInputData);

		sort(sfa_display);

		int randomCat = Utility.getRandomNumberInRange(1, sfa_display.size() - 1);

		category = sfa_display.get(randomCat);
		category_code = smr_sfa_code.get(randomCat);

		scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + category.trim() + "')]");

		// collapseBtn.click(); 
		return category_code.trim();

	}

	public static List<String> getAssignmentCategories(int col, List<UserInputData> userInputData) {

		return execute(Actions.replace(Queries.SAs_ASSIGNMENT_CATEGORIES, "RA_PE_ID", get_pe_id("stf", userInputData)),
				col, userInputData);
	}

	public String selectRandomJudgeCategory(List<UserInputData> userInputData) {
		return selectRandomItem(getID(Queries.REFERRAL_CATEGORIES, get_pe_id("jud", userInputData)), xpath,
				userInputData);

	}

	public static String get_pe_id(String pe_rt_code, List<UserInputData> userInputData) {
		String courtId = SystemPropertySetup.getCourtId(userInputData) + ".";

		String fName = "";
		String lName = "";

		if (pe_rt_code.equals("jud")) {

			lName = getProperty(courtId + jud);
			fName = getProperty(courtId + judFirstName);

		} else if (pe_rt_code.equals("stf")) {
			lName = getProperty(courtId + stf);
			fName = getProperty(courtId + stfFirstName);
		}

		return getPE_ID(pe_rt_code, lName, fName, userInputData);

	}

	public static List<String> getDocumentCategories() {

		List<String> categories = new ArrayList<>();

		for (int i = 0; i < docCategories().size(); i++) {
			categories.add(docCategories().get(i).getText());
		}
		String docName = "";
		if (categories.size() >= 1) {
			docName = categories.get(0);
			contains(categories.get(0)).click();
		} else {

			int randomDoc = Utility.getRandomNumberInRange(1, categories.size() - 1);
			docName = categories.get(randomDoc).trim();
			contains(docName).click();
		}

		int randomDoc = getRandomDocument(docName);

		click(randomDoc, docName);

		return categories;
	}

	public static List<WebElement> getDocName(String text, String categoryName) {
		int index;
		if (text.equals("docCategory")) {
			index = 1;
		} else {
			index = 2;
		}
		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + categoryName
				+ "']/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther["
				+ index + "]/XCUIElementTypeStaticText"));
	}

	public static int getSize(String categoryName) {

		return getDocName("docCategory", categoryName).size();
	}

	public static int getRandomDocument(String categoryName) {
		return Utility.getRandomInt(getSize(categoryName));
	}

	public static String getText(String text, int i, String categoryName) {
		return getDocName(text, categoryName).get(i).getText();
	}

	public static void click(int index, String categoryName) {
		getDocName("docCategory", categoryName).get(index).click();
	}

	public static List<WebElement> docCategories() {
		String pane = "";
		if (appliedRefs.size() > 0) {
			pane = "Applied Referrals";
		} else {
			pane = "Actions";
		}
		return Actions.findElements(By.xpath(
				"//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, '"
						+ pane + "')]"
						+ "/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText"));
	}

	public static String selectRandomCaseNumber(List<WebElement> elements) {
	    String referral = "";
	    Page.performPageLoad(driver);

	    // Filter out en banc cases (if applicable)
	    List<WebElement> filteredElements = elements.stream()
	        .filter(e -> e.getText() != null && !e.getText().toLowerCase().contains("en banc"))
	        .collect(Collectors.toList());

	    // Filter based on name pattern
	    Pattern pattern = Pattern.compile("^\\d{2}-\\d{3,5} .+");
	    List<WebElement> filtered = filteredElements.stream()
	        .filter(el -> {
	            String nameAttr = el.getAttribute("name");
	            return nameAttr != null && pattern.matcher(nameAttr).matches();
	        })
	        .collect(Collectors.toList());

	    List<String> list = Utility.retrieveAllReferrals(filtered, " ", 0);

	    int caseN = 0;
	    if (list.size() > 1) {
	        caseN = Utility.getRandomInt(list.size() - 1);
	    }

	    WebElement uiResult = findElementBy(Locator.XPATH,
	        "//XCUIElementTypeStaticText[contains(@name, '" + list.get(caseN) + "')]");

	    referral = list.get(caseN);
	    uiResult.click();
	    return referral;
	}


	public static void getAppliedCase() {
		scrollDownIfNotDisplayed("//*[@name='Applied Referrals' or @name='Associated Cases']");
	}

	public static String getRandomCase(Category cat) {

		List<WebElement> element = null;

		switch (cat) {
		case judgeRegularCase:
			element = regCaseNum;
			break;

		case targetCase:
			element = targetCase;
			break;

		case appliedCase:
			CommonPages page = new CommonPages();
			page.getGroupIcons(GroupIcons.Expand);
			getAppliedCase();
			DocumentPage p = new DocumentPage();
			element = p.appliedCase;
			break;

		case CaseOnCalendar:

			element = caseOncalender;
			break;

		case EnBanc:

			element = enBanc;
			break;

		default:
			break;
		}
		return selectRandomCaseNumber(element);
	}

	public void getDocumentCategories(List<UserInputData> userInputData) {

		DocumentPage.verifyDocumentCategorySorting(userInputData);
		String pane = DocumentPage.panel;

		List<String> uiDocList = new ArrayList<>();

		for (int k = 0; k < getDocList(pane).size(); k++) {
			uiDocList.add(getDocList(pane).get(k).getText().split(",")[1].split("Pages")[0]);
		}
		assertTrue("DOCUMENTS ARE NOT ORDERED BY THE FILED DATE: ", Utility.checkIfSorted(uiDocList));

	}

	public List<WebElement> getDocList(String panel) {

		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + panel
				+ "']/following::XCUIElementTypeStaticText[contains(@name, ',')]"));

	}

	public static List<String> verifyDocumentCategorySorting(List<UserInputData> userInputData) {
	    String cmr_cs_caseid = DocumentPage.cs_caseid;
	    String cmr_cyv_code = DocumentPage.cmr_cyv_code;
	    String cmr_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);

	    List<String> uiDocCategories = new ArrayList<>();
	     panel = (appliedRefs.size() > 0) ? "Applied" : "Actions";

	    for (WebElement element : getDocCategoryLocator(panel)) {
	        uiDocCategories.add(element.getText().trim());
	    }

	    // Fetch database document categories with cmd_sort
	    Map<String, Integer> dbCategoryMap = new HashMap<>();
	    List<String[]> dbDocCategoriesRaw = executeDBQuery(
	        Actions.replace(DOCUMENT_CATEGORIES, "CMR_CYV_CODE", cmr_cyv_code, "CMR_JU_PE_ID", cmr_ju_pe_id, "CMR_CS_CASEID", cmr_cs_caseid),
	        userInputData
	    );


	    if (dbDocCategoriesRaw == null || dbDocCategoriesRaw.isEmpty()) {
	        throw new AssertionError("No document categories returned from database.");
	    }

	    for (String[] record : dbDocCategoriesRaw) {
	        String cmd_doc_category = record[0].trim();
	        int cmd_sort = Integer.parseInt(record[1].trim());
	        dbCategoryMap.put(cmd_doc_category, cmd_sort);
	    }

	    // Verify that all UI categories exist in DB results
	    if (!dbCategoryMap.keySet().containsAll(uiDocCategories)) {

	        throw new AssertionError("UI document categories contain categories not found in the database.");
	    }

	    // Verify sorting
	    for (int i = 0; i < uiDocCategories.size() - 1; i++) {
	        String currentCategory = uiDocCategories.get(i);
	        String nextCategory = uiDocCategories.get(i + 1);

	        int currentSort = dbCategoryMap.get(currentCategory);
	        int nextSort = dbCategoryMap.get(nextCategory);

	        if (currentSort > nextSort) {
	            throw new AssertionError("Document categories are not sorted by cmd_sort ascending.");
	        } else if (currentSort == nextSort) {
	            // If cmd_sort is the same, check lexicographical order of categories
	            if (currentCategory.compareTo(nextCategory) > 0) {
	                throw new AssertionError("Document categories with same cmd_sort are not sorted lexicographically.");
	            }
	        }
	    }

	    System.out.println("Document categories sorted correctly: " + uiDocCategories);
	    return uiDocCategories;
	}


	public void getDocumentList(List<UserInputData> userInputData) {
		if (verifyDocumentCategorySorting(userInputData).size() > 0) {

			randomCategory = Utility.clickOnNumberInRange(getDocCategoryLocator(panel));
			performPageLoad(driver);
			
	        System.out.println("------------------------------------------------------");
	        System.out.println("Selected document category name: " + randomCategory);
	        System.out.println("------------------------------------------------------");
	        
			randomDocument = Utility.clickOnNumberInRange(getDocListLocator(randomCategory));
			
	        System.out.println("------------------------------------------------------");
	        System.out.println("Selected document name: " + randomDocument);
	        System.out.println("------------------------------------------------------");
			performPageLoad(driver);
			
			Utility.ifLoaded(inProgress);
			
			Boolean elementNotFound = true;
			int attemptCount = 0;

			while (elementNotFound && attemptCount < 3) {
				if (!(pdfPageView.size() == 1)) {
					elementNotFound = true;
					Utility.scrollPage("down");
					attemptCount++;
				} else {
					elementNotFound = false;
					break;
				}
			}

			if (elementNotFound) {
				throw new RuntimeException("The PDF document was not found: document category: " + randomCategory
						+ ", document name: " + randomDocument);
			}
		}
	}

	public List<WebElement> getDocListLocator(String categoryName) {
		return Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + categoryName + "']/following::"
				+ "XCUIElementTypeStaticText[@name='Downloaded']/preceding::XCUIElementTypeStaticText[2]"));
	}

	public static List<WebElement> getDocCategoryLocator(String panel) {
		return Actions.findElements(By.xpath(
				"//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView//child::*//*[contains(@name, '"+panel+"')]"
				+ "/following:: XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]"));

	}

	public void navigateToViewCaseInfo(String actionName) {
		// Scroll until viewCaseInfo is visible
		while (viewCaseInfo.size() != 1) {
			Utility.scrollPage("down");
		}

		// Click on the first viewCaseInfo element
		viewCaseInfo.get(0).click();

		ifDownloaded(inProgress);

		// Wait for docketEntries to be clickable
		Page.waitToBeClickable(docketEntries, driver);

		ifDownloaded(inProgress);
		// Wait for actionName to be clickable
		// Remove single quote if present
		actionName = actionName.contains("'") ? actionName.split("'")[0] : actionName;
		Utility.tapAndSwipe(Direction.DOWN);
		Page.waitForPresenceOfElementLocated(By.xpath("(//*[contains(@name, '" + actionName + "')])[1]"), driver)
				.click();
		// Page.waitToBeClickable(contains(actionName), driver);
	}

	
	public void deleteDocument(String docName) {


		Assert.assertTrue("Verify the viewed document: "+docName+" shows a green checkmark",verifyGreenCheckmark( docName,"y"))
		 ;
		
         // Utility.swipeElement("//XCUIElementTypeStaticText[@name='"+docName+"']");
          
		if (Utility.isDisplayed("//XCUIElementTypeAlert[@name='Delete Document?']"))
			;
		Actions.tap(okBtn);
		
		performPageLoad(driver);
		
		WebElement checkMark2 = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+docName+"']/following::XCUIElementTypeStaticText[2]"));


		WebElement downloaded = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Downloaded' and not(@label='')]"));

		boolean downloadArrow = checkMark2.equals(downloaded);

		Assert.assertTrue("The icon didn't switch back to a download arrow after deleting the "+docName+" via swipe", downloadArrow);

	}
	
	
	public boolean verifyGreenCheckmark(String docName,String isDocumentOpen) {
        if (isDocumentOpen.equals("y")) {
		Actions.tap(close);
		performPageLoad(driver);
	
        }
        WebElement checkMark = Page.waitForPresenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='"+docName+"']/following::XCUIElementTypeStaticText[2]"), driver);

        WebElement downloadedElement = Page.waitForPresenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Downloaded' and @label='']"), driver);

      
		boolean isSameElement = checkMark.equals(downloadedElement);


		return  isSameElement;
	}
	
	
	
	public void getReplacedDocument(List<UserInputData> userInputData) {
	    String peId = DocumentPage.get_pe_id("jud", userInputData);


	
	    String query = Queries.REPLACED_DOCUMENTS.replace("?", peId);


	    List<String[]> foundCases = executeDBQuery(getID(query, peId), userInputData);

	  
	    for (String[] row : foundCases) {
	        String caseNum = row[0].trim();
	        String dm_description = row[1].trim();
	        String cs_caseid = row[2].trim();
	        String dm_last_updated = row[3].trim();

	        System.out.println(caseNum + " | " + dm_description + " | " + cs_caseid + " | " + dm_last_updated);
	    }


	    
	}


	
	public enum Category {
		Referral_Category, Referral, Panel, judgeRegularCase, targetCase, appliedCase, CaseOnCalendar, EnBanc
	}

}
