package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.REFERRAL_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getReferralCategories;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.refresh;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_StaffAttorneyPage {

	@WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Senior Staff Attorney')]")
	public static MobileElement staffAttorney;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	public iOS_StaffAttorneyPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void verifyDataOnTheDashboard(String dbType, String query) {
		refresh();
		assertTrue(isDisplayed(valueOf(dbType), query,
				"//XCUIElementTypeTable[@name='Categories']/XCUIElementTypeCell/XCUIElementTypeStaticText"));
	}

	public void selectAssignmentType() {
		performPageLoad();
		staffAttorney.click();

	}

	public void osberveReferralCategories() {

		List<String> uiRefCategories = getReferralCategories();
		List<String> dbuiRefCategories = executeQuery(DBType.CMKA, REFERRAL_CATEGORIES);
		assertDbContainsAllFromUi(dbuiRefCategories, uiRefCategories);
	}

	public static void assertDbContainsAllFromUi(List<String> ui, List<String> db) {
		List<String> uiReferralAssignments = ui;
		List<String> dbReferralAssignments = db;
		try {
			assertTrue("Record count mismatch", dbReferralAssignments.containsAll(uiReferralAssignments));

		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}

}
