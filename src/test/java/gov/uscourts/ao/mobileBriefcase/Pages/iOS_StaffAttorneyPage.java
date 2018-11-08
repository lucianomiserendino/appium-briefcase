package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOCUMENT_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ID_OF_THE_REFERRAL_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.REFERRAL_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.REFERRAL_NUMBERS;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.replace;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_StaffAttorneyPage extends AppiumPageFactory {

	@WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Senior Staff Attorney')]")
	public static MobileElement staffAttorney;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	/** Observe the assignment categories that display on the dashboard */

	public void verifyDataOnTheDashboard(String dbType, String query) {
		assertTrue(isDisplayed(valueOf(dbType), query,
				"//XCUIElementTypeTable[@name='Categories']/XCUIElementTypeCell/XCUIElementTypeStaticText"));
	}

	public void selectAssignmentType(String assignmenType) {
		performPageLoad();
		clickOnElement(assignmenType);

	}

	/**
	 * Observe there are six referral categories listed .Verify the number of
	 * referrals in each categories, matches the number of referrals in the DB
	 */
	public void osberveReferralCategories() {
		assertTrue(getReferralCategories());
	}

	public static boolean getReferralCategories() {
		DBType dbType = valueOf("CMKA");
		boolean isDisplayed = false;
		List<String> category = executeQuery(dbType, REFERRAL_CATEGORIES);
		sort(category);
		try {
			for (int i = 0; i < category.size(); ++i) {

				List<String> refCatId = executeQuery(dbType,
						replace(ID_OF_THE_REFERRAL_CATEGORY, "MRC_NAME", category.get(i)));
				for (int j = 0; j < refCatId.size(); j++) {

					List<String> refNumbers = executeQuery(dbType,
							replace(REFERRAL_NUMBERS, "SMR_MRC_ID", refCatId.get(j)));
					for (int k = 0; k < refNumbers.size(); k++) {

						MobileElement uiResult = findElement(By
								.xpath("//*[contains(@name, '" + category.get(i) + " (" + refNumbers.get(k) + ")')]"));

						if (uiResult.isDisplayed())
							isDisplayed = true;
					}
				}
			}
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public void tap(String caseNum, String category) {
		if (elementIsDisplayed(caseNum)) {
			click(locateElement(caseNum));
		} else {
			click(locateElement(category));
			click(locateElement(caseNum));
		}
	}

	/**
	 * Find the document categories and verify the number of docs displayed for each
	 * category matches the number of docs in the DB by running this query for each
	 * document category
	 */

	public static String getDocumentCategories(DBType dbType, String referral) {
		return getAllColumns(dbType, replace(ID_OF_THE_REFERRAL_CATEGORY, "MRC_NAME", referral));
	}

	public void getCategories(DBType dbType, String referral) {
		isDisplayed(dbType, replace(DOCUMENT_CATEGORIES, "SMR_MRC_ID", getDocumentCategories(dbType, referral)),
				"//XCUIElementTypeStaticText");
		assertTrue(getDocuments(referral));
		
	}

	public boolean getDocuments(String referral) {
		DBType dbType = valueOf("CMKA");
		boolean isDisplayed = false;
		List<String> docCategory = executeQuery(dbType,
				replace(DOCUMENT_CATEGORIES, "SMR_MRC_ID", getDocumentCategories(dbType, referral)));
		sort(docCategory);

		try {
			for (int i = 0; i < docCategory.size(); ++i) {
				
				
				List<String> docDesc = executeQuery(dbType, replace(DOCUMENT_DESCRIPTION, "CMD_DOC_CATEGORY",
						docCategory.get(i), "SMR_MRC_ID", getDocumentCategories(dbType, referral)));
				for (int j = 0; j < docDesc.size(); j++) {

					MobileElement uiResult = findElement(By.xpath("//*[contains(@name, '" + docCategory.get(i)
							+ "')]/following:: XCUIElementTypeStaticText[contains(@name, '" + docDesc.get(j) + "')]"));

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
