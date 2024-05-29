package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASE_NUMBER;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ID_OF_THE_REFERRAL_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SAs_DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.toArray;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Filter;
import gov.uscourts.ao.mobileBriefcase.stepDefinitions.Document_StepDefinitions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class StaffAttorneyReferralPage extends Base {

	CommonPages page = new CommonPages();
	// @WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Senior Staff Attorney')]")
	public static WebElement staffAttorney;

	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'User')]")
	public static WebElement selectUser;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sorting Options']")
	public static WebElement sortingPopup;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Apply\"]")
	public static WebElement apply;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther//following::XCUIElementTypeStaticText[contains(@name, '(')]")
	public static List<WebElement> categoryCount;

	@iOSXCUITFindBy(xpath = "XCUIElementTypeStaticText[@name='Asc'][1]")
	public static WebElement asc;

	@iOSXCUITFindBy(xpath = "XCUIElementTypeStaticText[@name='Desc'][1]")
	public static WebElement desc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='GroupIcon']")
	public static List<WebElement> GroupIcon;
	
	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	public static String smr_sfa_code;
	public static String smr_assign_pe_id;
	public static String sar_cs_caseid;

	/** Observe the assignment categories that display on the dashboard for SAs */

	public void verifyDataOnTheDashboard(List<UserInputData> userInputData) {
	    List<String> assignmentCategories = DocumentPage.getAssignmentCategories(2, userInputData);
	    String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";
	    boolean areElementsDisplayed = elementIsDisplayed(assignmentCategories, xpath, userInputData);

	    assertTrue("Not all assignment categories are displayed on the dashboard", areElementsDisplayed);
	}


	public void selectAssignmentType(String assignmenType) {
		performPageLoad(driver);
		findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText[contains(@name, '"
						+ assignmenType + "')]")
				.click();
	}

	/**
	 * Observe there are six referral categories listed .Verify the number of
	 * referrals in each categories, matches the number of referrals in the DB
	 */
	public void observeReferralCategories(List<UserInputData> table) {
	    page.getGroupIcons(GroupIcons.Expand);
	    verifyReferralCategories(table);
	}

	public static void verifyReferralCategories(List<UserInputData> table) {
	    String smr_sfa_code = Document_StepDefinitions.stfCategory;
	    String smr_assign_pe_id = DocumentPage.get_pe_id("stf", table);

	    List<String> uiRefCategories = new ArrayList<>();
	    List<String> dbRefCategories = new ArrayList<>();

	    List<String> categoryNames = executeQuery(replace(Queries.SAs_REFERRAL_CATEGORIES, "SMR_ASSIGN_PE_ID",
	            smr_assign_pe_id, "SMR_SFA_CODE", smr_sfa_code), table);

	    for (int i = 1; i < categoryNames.size() + 1; ++i) {
	        WebElement categoryNameElement = getRefCategory(i, 2);
	        WebElement numOfRefCatElement = getRefCategory(i, 3);

	        uiRefCategories.add(categoryNameElement.getText().trim() + " " + numOfRefCatElement.getText().trim());
	    }

	    for (String categoryName : categoryNames) {
	        List<String> refCatIds = executeQuery(replace(ID_OF_THE_REFERRAL_CATEGORY, "SMR_ASSIGN_PE_ID",
	                smr_assign_pe_id, "MRC_NAME", categoryName, "SMR_SFA_CODE", smr_sfa_code), table);

	        for (String refCatId : refCatIds) {
	            List<String> refNumbers = executeQuery(replace(Queries.REFERRAL_NUMBERS, "SMR_ASSIGN_PE_ID",
	                    smr_assign_pe_id, "SMR_MRC_ID", refCatId, "SMR_SFA_CODE", smr_sfa_code), table);

	            for (String refNum : refNumbers) {
	                dbRefCategories.add(categoryName + " (" + refNum + ")");
	            }
	        }
	    }

	    assertEquals("NUMBER OF REFERRALS IN EACH CATEGORY DOESN'T MATCH THE NUMBER OF REFERRALS IN THE DB",
	            dbRefCategories, uiRefCategories);
	}

	public static WebElement getRefCategory(int categoryName, int numOfRef) {
	    return findElementBy(Locator.XPATH,
	            "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther["
	                    + categoryName + "]/XCUIElementTypeOther[2]/XCUIElementTypeOther[" + numOfRef
	                    + "]/XCUIElementTypeStaticText");
	}




	public void tapOnReferralCategory(List<UserInputData> table) {
		List<String> caseList = new ArrayList<>();
		List<String> categoryName = new ArrayList<>();

		smr_sfa_code = Document_StepDefinitions.stfCategory;

		smr_assign_pe_id = DocumentPage.get_pe_id("stf", table);

		int caseNumber = getCaseList(smr_assign_pe_id, smr_sfa_code, table, 2).size();

		int random = Utility.getRandomNumberInRange(1, caseNumber);

		for (int i = 0; i < caseNumber; i++) {

			caseList = DBUtilities.execute(
					replace(Queries.SMR_SFA_CODE, "SMR_ASSIGN_PE_ID", smr_assign_pe_id, "SMR_SFA_CODE", smr_sfa_code),
					2, table);

			categoryName = DBUtilities.execute(
					replace(Queries.SMR_SFA_CODE, "SMR_ASSIGN_PE_ID", smr_assign_pe_id, "SMR_SFA_CODE", smr_sfa_code),
					3, table);

		}
		String category = "";

		if (caseList.size() > 1) {
			sar_cs_caseid = caseList.get(random - 1).trim();
			category = categoryName.get(random - 1).trim();
		} else if (caseList.size() == 1) {
			sar_cs_caseid = caseList.get(0).trim();
			category = categoryName.get(0).trim();
		}

		scrollDownIfNotDisplayed(Actions.containsElement(category.trim()));

		String caseNum = DBUtilities.getAllColumns(replace(CASE_NUMBER, "CS_CASEID", sar_cs_caseid), table);

		Actions.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name, '" + category
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + caseNum + "')]")).click();

	}

	public List<String> getCaseList(String smr_assign_pe_id, String smr_sfa_code, List<UserInputData> table, int col) {
	return DBUtilities.execute(
				replace(Queries.SMR_SFA_CODE, "SMR_ASSIGN_PE_ID", smr_assign_pe_id, "SMR_SFA_CODE", smr_sfa_code), col,
				table);
	}

	/**
	 * Find the document categories and verify the number of docs displayed for each
	 * category matches the number of docs in the DB by running this query for each
	 * document category
	 */

	public static List<String> getDocumentCategories(DBType dbType, String smr_assign_pe_id, String referral) {
		return executeQuery(dbType,
				replace(ID_OF_THE_REFERRAL_CATEGORY, "SMR_ASSIGN_PE_ID", smr_assign_pe_id, "MRC_NAME", referral));
	}

	public static String getDocCategories(DBType dbType, String refID, String smr_assign_pe_id) {
		return replace(SAs_DOCUMENT_CATEGORIES, "SMR_ASSIGN_PE_ID", smr_assign_pe_id, "SMR_MRC_ID",
				toArray(getDocumentCategories(dbType, smr_assign_pe_id, refID)));
	}

	public void getDocuments(String smr_assign_pe_id, String smr_sfa_code, String sar_cs_caseid,
			List<UserInputData> table) {

		List<String> docDesc = getDocuments(table, 3);

		int random = Utility.getRandomNumberInRange(1, docDesc.size());

		String randomDoc = "";
		if (docDesc.size() > 1) {
			randomDoc = docDesc.get(random - 1).trim();
		} else if (docDesc.size() == 1) {
			randomDoc = docDesc.get(0).trim();
		}

		scrollDownIfNotDisplayed(Actions.containsElement(randomDoc));
		
		close.click();
	}

	public List<String> getDocuments(List<UserInputData> table, int col) {

		return DBUtilities.execute(replace(DOCUMENT_DESCRIPTION, "SMR_ASSIGN_PE_ID", smr_assign_pe_id, "SMR_SFA_CODE",
				smr_sfa_code, "SAR_CS_CASEID", sar_cs_caseid), col, table);
	}

	public void gestfaty_supervisor_to_group(List<UserInputData> table) {
		List<String> group = execute(Queries.SUPERVISOR_STF, 6, table);
		Actions.tap(Locator.XPATH, group.get(0));
		CommonPages.getGroupIcons(GroupIcons.Expand);

		List<WebElement> categoryName = Actions
				.findElements(By.xpath(containsElement("(") + "/preceding:: XCUIElementTypeStaticText[1]"));

		String stf = categoryName.get(0).getText();

		String categoryCount = Actions.replace(Actions.findElements(By.xpath(containsElement("("))).get(0).getText(),
				"\\(", "", "\\)", "");

		String lName = stf.split(",")[0];
		String fName = stf.split(",")[1].split(" ")[0];

		String peId = DBUtilities.getPE_ID("stf", lName, fName, table);

		int refNumbers = executeQuery(replace(Queries.STF_REFERRAL_CATEGORIES, "RA_PE_ID", peId), table).size();

		Assert.assertEquals(categoryCount, refNumbers);
	}

	public void verifyIconsMatchSfaBriefcaseCatIcon(List<UserInputData> userInputData) {
	    String peId = DocumentPage.get_pe_id("stf", userInputData);
	    String query = Actions.replace(Queries.SAs_ASSIGNMENT_CATEGORIES, "RA_PE_ID", peId);

	    List<String> dashboardIcons = executeQuery(query, userInputData);
	    List<String> dbIcons = executeQuery(Queries.STAFF_ATTORNEY_DASHBOARD_ICONS, userInputData);

	    int matchingIconsCount = Utility.filterArraylistItems(Filter.DUPLICATE_VALUES, dashboardIcons, dbIcons).size();

	    assertEquals(
	        "The icons displayed on the dashboard/navigation don't match the icons stored in the sfa_briefcase_cat_icon field",
	        dashboardIcons.size(), 
	        matchingIconsCount
	    );
	}

}
