package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ID_OF_THE_REFERRAL_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.REFERRAL_NUMBERS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SAs_REFERRAL_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.toArray;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class StaffAttorneyPage extends AppiumPageFactory {
	CommonPages page = new CommonPages();
	// @WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Senior Staff Attorney')]")
	public static MobileElement staffAttorney;

	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name='▷']")
	public static List<MobileElement> right;

	@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name='▽']")
	public static List<MobileElement> down;

	@iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name='▽'])[1]")
	public static MobileElement viewed;

	@iOSFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<MobileElement> refCategories;

	/** Observe the assignment categories that display on the dashboard for SAs */

	public void verifyDataOnTheDashboard(String dbType, String query) {
		assertTrue(elementIsDisplayed(valueOf(dbType), query,
				"//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText"));
	}

	public void selectAssignmentType(String assignmenType) {
		performPageLoad(driver);
		findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText[contains(@name, '"
						+ assignmenType + "')]").click();
	}

	/**
	 * Observe there are six referral categories listed .Verify the number of
	 * referrals in each categories, matches the number of referrals in the DB
	 */
	public void osberveReferralCategories(DBType dbType) {
		page.getCollapsiblePanel(dbType, SAs_REFERRAL_CATEGORIES);
		getReferralCategories(dbType);
	}

	public static void getReferralCategories(DBType dbType) {
		List<String> uiRefCategories = new ArrayList<>();
		List<String> dbRefCategories = new ArrayList<>();
		try {
			List<String> category = executeQuery(dbType, SAs_REFERRAL_CATEGORIES);
			sort(category);

			for (int i = 1; i < category.size() + 1; ++i) {
				MobileElement categoryName = getRefCategory(i, 2);
				MobileElement numOfREfCat = getRefCategory(i, 3);

				uiRefCategories.add(categoryName.getText().trim() + " " + numOfREfCat.getText().trim());
				sort(uiRefCategories);
			}

			for (int i = 0; i < category.size(); ++i) {

				List<String> refCatId = executeQuery(dbType,
						replace(ID_OF_THE_REFERRAL_CATEGORY, "MRC_NAME", category.get(i)));

				for (int j = 0; j < refCatId.size(); j++) {

					List<String> refNumbers = executeQuery(dbType,
							replace(REFERRAL_NUMBERS, "SMR_MRC_ID", refCatId.get(j)));
					for (int k = 0; k < refNumbers.size(); k++) {

						dbRefCategories.add(category.get(i) + " (" + refNumbers.get(k) + ")");
					}
				}
			}
			assertEquals("NUMBER OF REFERRALS IN EACH CATEGORIES, DOESN'T MATCH THE NUMBER OF REFERRALS IN THE DB",
					dbRefCategories, uiRefCategories);

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static MobileElement getRefCategory(int i, int numOfRef) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='ReferralsList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["
						+ i + "]/XCUIElementTypeOther[1]/XCUIElementTypeOther[" + numOfRef
						+ "]/XCUIElementTypeStaticText");

	}

	public void tapOnReferralCategory(String category, String caseNumber) {
		page.getCollapsiblePanel(DBType.CMKA, SAs_REFERRAL_CATEGORIES);
		findElementBy(Locator.XPATH, containsElement(category)).click();
		findElementBy(Locator.XPATH, containsElement(caseNumber)).click();

	}

	/**
	 * Find the document categories and verify the number of docs displayed for each
	 * category matches the number of docs in the DB by running this query for each
	 * document category
	 */

	public static List<String> getDocumentCategories(DBType dbType, String referral) {
		return executeQuery(dbType, replace(ID_OF_THE_REFERRAL_CATEGORY, "MRC_NAME", referral));
	}

	public String getDocCategories(DBType dbType, String refID) {
		return replace(DOCUMENT_CATEGORIES, "SMR_MRC_ID", toArray(getDocumentCategories(dbType, refID)));
	}

	public void getCategories(DBType dbType, String refID) {
		page.getCollapsiblePanel(dbType, getDocCategories(dbType, refID));
		assertTrue(getDocuments(refID, dbType));
	}

	public boolean getDocuments(String refID, DBType dbType) {
		boolean isDisplayed = false;
		MobileElement uiDocs = null;
		List<String> dbDocs = executeQuery(dbType, getDocCategories(dbType, refID));
		sort(dbDocs);
		try {
			for (int i = 0; i < dbDocs.size(); ++i) {

				uiDocs = findElementBy(Locator.XPATH,
						"//XCUIElementTypeStaticText[contains(@name, '" + dbDocs.get(i) + "')]");

				if (uiDocs.isDisplayed())
					isDisplayed = true;

				uiDocs.click();

				List<String> docDesc = executeQuery(dbType, replace(DOCUMENT_DESCRIPTION, "CMD_DOC_CATEGORY",
						dbDocs.get(i), "SMR_MRC_ID", toArray(getDocumentCategories(dbType, refID))));
				for (int j = 0; j < docDesc.size(); j++) {

					MobileElement uiResult = findElementBy(Locator.XPATH, containsElement(dbDocs.get(i)
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
