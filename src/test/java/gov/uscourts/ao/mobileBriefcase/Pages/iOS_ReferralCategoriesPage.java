package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.BRIEFCASE_TARGET_ONLY_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.BRIEFCASE_TARGET_ONLY_Y;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.NON_ORALLY_ARGUED_CASES;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.select;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getNumOfDisplayedCases;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.refresh;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.scroll;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_ReferralCategoriesPage {

	public iOS_ReferralCategoriesPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Pending Tasks")
	public static MobileElement pendingTasks;

	@WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement total;

	public String verifyIfPendingTasksAreDisplayed() {
		refresh();
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
			assertEquals("-----RECORD COUNT MISMATCH-----", asList(DBPendingTasks.size()), UIPendingTasks);
		}
	}

	public void getReferralCategories(DBType dbtype, String query) {
		refresh();
		referralCategories(dbtype, query);

	}

	public static List<String> referralCategories(DBType dbtype, String query) {
		List<String> categories = new ArrayList<>();
		List<String> dbReferralCategories = executeQuery(dbtype, query);
		sort(dbReferralCategories);
		// scroll(1, "down");
		try {
			for (int i = 0; i < dbReferralCategories.size(); ++i) {
				performPageLoad();
				MobileElement referrals = waitForElement(
						findElement(By.xpath("//*[contains(@name, '" + dbReferralCategories.get(i) + "')]")));
				assertTrue(referrals.isDisplayed());

			}
		} catch (org.openqa.selenium.TimeoutException e) {

			e.printStackTrace();
		}
		categories.addAll(dbReferralCategories);
		sort(categories);
		return dbReferralCategories;
	}

	public void verifyNonOrallyArgCases(DBType dbtype, String pe_id) {
		refresh();
		getReffCategories(dbtype, pe_id);

	}

	public static List<String> getReffCategories(DBType dbtype, String pe_id) {

		List<String> referralCategories = executeQuery(dbtype, getID(NON_ORALLY_ARGUED_CASES, pe_id));
		sort(referralCategories);
		scroll(1, "down");
		try {

			for (int i = 0; i < referralCategories.size(); ++i) {
				// scroll(1, "down");
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

				select(Users.DASHBOARD);
			}
		} catch (org.openqa.selenium.TimeoutException e) {

			e.printStackTrace();
		}

		return referralCategories;
	}

}