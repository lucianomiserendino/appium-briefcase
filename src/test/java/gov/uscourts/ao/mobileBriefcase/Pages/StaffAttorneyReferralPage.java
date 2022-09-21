package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ID_OF_THE_REFERRAL_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SAs_DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.toArray;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class StaffAttorneyReferralPage extends AppiumPageFactory {
	CommonPages page = new CommonPages();
	// @WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Senior Staff Attorney')]")
	public static WebElement staffAttorney;

	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'User')]")
	public static WebElement selectUser;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<WebElement> refCategories;

	/** Observe the assignment categories that display on the dashboard for SAs */

	public void verifyDataOnTheDashboard(String query, String ra_pe_id, List<UserInputData> userInputData) {

		assertTrue(elementIsDisplayed(Actions.replace(query, "RA_PE_ID", ra_pe_id),
				"//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText",
				userInputData));
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
	public void osberveReferralCategories(String smr_assign_pe_id, List<UserInputData> table) {
		page.getGroupIcons();
		getReferralCategories(smr_assign_pe_id, table);
	}

	public static void getReferralCategories(String smr_assign_pe_id, List<UserInputData> table) {
		List<String> uiRefCategories = new ArrayList<>();
		List<String> dbRefCategories = new ArrayList<>();

		List<String> category = executeQuery(
				replace(Queries.SAs_REFERRAL_CATEGORIES, "SMR_ASSIGN_PE_ID", smr_assign_pe_id), table);
		sort(category);

		for (int i = 1; i < category.size() + 1; ++i) {
			WebElement categoryName = getRefCategory(i, 2);
			WebElement numOfREfCat = getRefCategory(i, 3);

			uiRefCategories.add(categoryName.getText().trim() + " " + numOfREfCat.getText().trim());
			sort(uiRefCategories);
		}
		for (int i = 0; i < category.size(); ++i) {

			List<String> refCatId = executeQuery(replace(ID_OF_THE_REFERRAL_CATEGORY, "SMR_ASSIGN_PE_ID",
					smr_assign_pe_id, "MRC_NAME", category.get(i)), table);

			for (int j = 0; j < refCatId.size(); j++) {

				List<String> refNumbers = executeQuery(replace(Queries.REFERRAL_NUMBERS, "SMR_ASSIGN_PE_ID",
						smr_assign_pe_id, "SMR_MRC_ID", refCatId.get(j)), table);
				for (int k = 0; k < refNumbers.size(); k++) {

					dbRefCategories.add(category.get(i) + " (" + refNumbers.get(k) + ")");
				}
			}
		}

		assertEquals("NUMBER OF REFERRALS IN EACH CATEGORIES, DOESN'T MATCH THE NUMBER OF REFERRALS IN THE DB",
				dbRefCategories, uiRefCategories);
	}

	public static WebElement getRefCategory(int i, int numOfRef) {

		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther["
						+ i + "]/XCUIElementTypeOther[2]/XCUIElementTypeOther[" + numOfRef
						+ "]/XCUIElementTypeStaticText");

	}

	public void tapOnReferralCategory(String category, String caseNumber, String smr_assign_pe_id) {
		page.getGroupIcons();
		findElementBy(Locator.XPATH, containsElement(category)).click();
		findElementBy(Locator.XPATH, containsElement(caseNumber)).click();
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

	public void getCategories(DBType dbType, String refID, String smr_assign_pe_id) {
		page.getGroupIcons();
		assertTrue(getDocuments(refID, dbType, smr_assign_pe_id));
	}

	public boolean getDocuments(String refID, DBType dbType, String smr_assign_pe_id) {
		boolean isDisplayed = false;

		List<String> dbDocs = executeQuery(dbType, getDocCategories(dbType, refID, smr_assign_pe_id));
		sort(dbDocs);
		try {
			for (int i = 0; i < dbDocs.size(); ++i) {
				Utility.scrollDownIfNotDisplayed(
						"//XCUIElementTypeStaticText[contains(@name, '" + dbDocs.get(i) + "')]");

				List<String> docDesc = executeQuery(dbType,
						replace(DOCUMENT_DESCRIPTION, "SMR_ASSIGN_PE_ID", smr_assign_pe_id, "CMD_DOC_CATEGORY",
								dbDocs.get(i), "SMR_MRC_ID",
								toArray(getDocumentCategories(dbType, smr_assign_pe_id, refID))));

				for (int j = 0; j < docDesc.size(); j++) {

					WebElement uiResult = findElementBy(Locator.XPATH, containsElement(dbDocs.get(i)
							+ "')]/following:: XCUIElementTypeStaticText[contains(@name, '" + docDesc.get(j)));

					if (uiResult.isDisplayed())
						isDisplayed = true;
				}
			}
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

}
