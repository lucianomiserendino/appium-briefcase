package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.BRIEFCASE_TARGET_ONLY_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.BRIEFCASE_TARGET_ONLY_Y;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.NON_ORALLY_ARGUED_CASES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.REFERRAL_DOCUMENTS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.lbrrpt_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.lbrrpt_CYV_CATEGORY;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.select;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getNumOfDisplayedCases;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.replace;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.Coordinates;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_ReferralCategoriesPage extends AppiumPageFactory {

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Pending Tasks")
	public static MobileElement pendingTasks;

	@WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement total;

	public String verifyIfPendingTasksAreDisplayed() {

		if (pendingTasks.isDisplayed()) {
			clickOn(pendingTasks);
		}
		performPageLoad();
		return getNumOfDisplayedCases(total);

	}

	public void getPendingTasks(DBType dbtype, String query, String pe_id) {
		List<String> DBPendingTasks = executeQuery(dbtype, getID(query, pe_id));
		if (DBPendingTasks.size() > 0) {
			List<String> UIPendingTasks = asList(verifyIfPendingTasksAreDisplayed());
			assertEquals("-----RECORD COUNT MISMATCH-----", DBPendingTasks, UIPendingTasks);
		}
	}

	public void getReferralCategories(DBType dbtype, String query) {

		categories(dbtype, query);

	}

	public static List<String> categories(DBType dbtype, String query) {
		List<String> categories = new ArrayList<>();
		List<String> dbReferralCategories = executeQuery(dbtype, query);
		sort(dbReferralCategories);
		try {
			for (int i = 0; i < dbReferralCategories.size(); ++i) {
				performPageLoad();
				MobileElement referrals = waitForElement(findElement(By.id(dbReferralCategories.get(i))));
				assertTrue(referrals.isDisplayed());
			}
		} catch (org.openqa.selenium.TimeoutException e) {

			e.printStackTrace();
		}
		categories.addAll(dbReferralCategories);
		sort(categories);
		return dbReferralCategories;
	}

	public void verifyNonOrallyArgCases(DBType dbtype, String cyvCategory, String pe_id) {
		getReffCategories(dbtype, cyvCategory, pe_id);

	}

	public static List<String> getReffCategories(DBType dbtype, String cyvCategory, String pe_id) {

		List<String> referralCategories = executeQuery(dbtype,
				getID(replace(NON_ORALLY_ARGUED_CASES, "CMR_CYV_CODE", cyvCategory), pe_id));
		sort(referralCategories);
		try {

			for (int i = 0; i < referralCategories.size(); ++i) {
				performPageLoad();
				MobileElement referrals = waitForElement(findElement(By.id(referralCategories.get(i))));
				performPageLoad();

				String dbNonOrgCases = referrals.getText();

				referrals.click();

				List<String> briefcaseTargReferral_y = executeQuery(dbtype,
						getText(getID(BRIEFCASE_TARGET_ONLY_Y, pe_id), dbNonOrgCases));

				List<String> briefcaseTargReferral_n = executeQuery(dbtype,
						getText(getID(BRIEFCASE_TARGET_ONLY_N, pe_id), dbNonOrgCases));

				List<String> UInonOrallyarguedCases = asList((getNumOfDisplayedCases(waitForElement(total))));

				assertTrue("-----RECORD COUNT MISMATCH-----", briefcaseTargReferral_n.equals(UInonOrallyarguedCases)
						|| briefcaseTargReferral_y.equals(UInonOrallyarguedCases));

				select(Coordinates.DASHBOARD);
			}
		} catch (org.openqa.selenium.TimeoutException e) {

			e.printStackTrace();
		}

		return referralCategories;
	}

	/**
	 * If the chm_mobile_referral.cmr_cyv_code = 'lbrrpt', verify  cyv_category 
	 * displays on the Dashboard page. 
	 */
	public void get_lbrrpt_CATEGORY(DBType dbType, String cyvCategory, String pe_id) {

		MobileElement lbrrptCategory;
		String peID = getPE_ID(dbType, pe_id);
		List<String> cmr_cyv_code = executeQuery(dbType, getID(lbrrpt_CATEGORY, peID));
		if (cmr_cyv_code.contains(cyvCategory)) {
			String cyv_category = getAllColumns(dbType,
					getID(replace(lbrrpt_CYV_CATEGORY, "CMR_CYV_CODE", cyvCategory), peID));
			lbrrptCategory = waitForElement(findElement(By.id(cyv_category)));
			assertTrue(lbrrptCategory.isDisplayed());
			lbrrptCategory.click();

			/** Verify only documents display the referral detail page */
			categories(dbType, getID(REFERRAL_DOCUMENTS, peID));
		}

	}

}