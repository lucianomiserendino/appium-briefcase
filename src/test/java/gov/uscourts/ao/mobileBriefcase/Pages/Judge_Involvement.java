package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.NON_ORALLY_ARGUED_CASES;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Judge_Involvement extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'Involvement')]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static List<WebElement> caseWithInvolvement;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(id = "Categories")
	public static WebElement categories;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Involvement')]")
	public static WebElement invCode;

	private static String refCatList = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";
	private static String invCodeFromRefDetailPage = "//XCUIElementTypeStaticText[contains(@name, 'CaseNumber')]/following::XCUIElementTypeStaticText[contains(@name, 'Involvement')][1]";

	static String categoryName = "";
	static String caseNum = "";
	static String cmr_ju_pe_id = "";

	public void selectCaseWithInvolvement(List<UserInputData> userInputData) {

	    cmr_ju_pe_id += DocumentPage.get_pe_id("jud", userInputData);

	    // Retrieve and sort the referral categories
	    List<String> referralCategories = executeQuery(
	        getID(replace(NON_ORALLY_ARGUED_CASES, "CMR_CYV_CODE", "lbrrpt"), cmr_ju_pe_id), userInputData);
	    Collections.sort(referralCategories);

	    boolean elementNotFound = true;

	    while (elementNotFound) {
	        for (String category : referralCategories) {
	            Utility.scrollDownIfNotDisplayed(refCatList + "[contains(@name, '" + category + "')]");
	            performPageLoad(driver);

	            if (caseWithInvolvement.size() > 0) {
	                categoryName += category;
	                Page.performPageLoad(driver);

	                List<String> caseList = Utility.retrieveAllReferrals(caseWithInvolvement, " ", 0);

	                int caseIndex = (caseList.size() > 1) ? Utility.getRandomInt(caseList.size() - 1) : 0;

	                caseNum += caseList.get(caseIndex);
	                elementNotFound = false;

	                break;
	            } else {
	                dashboard.click();
	                Utility.scroll(categories, "up");
	                elementNotFound = true;
	            }
	        }
	    }
	}


	public String getInvolvementCode(WebElement el) {
		Page.sleep(1000);
		return el.getText().split("Involvement:")[1].split("Date:")[0].trim();

	}

	public void ifCorrectPanelInvolvementFound(List<UserInputData> userInputData) {
	    try {
	        // Get UI involvement code
	        String uiInvCode = getInvolvementCode(
	                Actions.findElement(By.xpath(invCodeFromRefDetailPage.replace("CaseNumber", caseNum))));

	        // Get case ID
	        String cmr_cs_caseid = CommonPages.getCaseID(caseNum, userInputData);

			String cmr_cyv_code = CommonPages.cmr_cyv_code(categoryName, cmr_cs_caseid, userInputData);

	        // Get DB involvement code
	        String dbInvCode = DBUtilities.getAllColumns(
	                getID(replace(Queries.JUDGE_INVOLVEMENT, "CS_CASEID", cmr_cs_caseid, "CMR_CYV_CODE", cmr_cyv_code),
	                        cmr_ju_pe_id),
	                userInputData).trim();

	        // Verify if UI involvement code matches DB involvement code
	        assertEquals("Verify judge involvement found via chm_mobile_referral.cmr_ic_code", uiInvCode, dbInvCode);

	    } catch (Exception e) {
	        e.printStackTrace();
	        fail("An error occurred while verifying judge involvement: " + e.getMessage());
	    }
	}


}
