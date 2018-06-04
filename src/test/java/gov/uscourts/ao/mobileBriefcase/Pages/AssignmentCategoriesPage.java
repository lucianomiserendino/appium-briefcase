package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getCategories;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getReferralCategories;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getCollapsablePanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectAUser;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users;
import gov.uscourts.ao.mobileBriefcase.common.Constants;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;


public class AssignmentCategoriesPage implements Constants {


	public AssignmentCategoriesPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	private int startRow = 1;
	private int endRow = 3;
	private int cellIndex = 2;
	private String name = "Categories";



	
	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	
	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Senior Staff Attorney')]")
	public static MobileElement staffAttorney;

	public void selectAnAttorney() {

		selectAUser(Users.BROWN_BENJAMIN);


	}

	public void getAssignmentCategories() {
		List<String> uiAssignmentCategories = getCategories(name, startRow, endRow, cellIndex);

		List<String> dbAssignmentCategories = executeQuery(Queries.ASSIGNMENT_CATEGORIES);
		assertDbContainsAllFromUi(dbAssignmentCategories, uiAssignmentCategories);
	}

	public void selectAssignmentType() {
		performPageLoad();
		staffAttorney.click();

	}

	public void osberveReferralCategories() {

		List<String> uiRefCategories = getReferralCategories();
		List<String> dbuiRefCategories = executeQuery(Queries.REFERRAL_CATEGORIES);

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

		finally {

			getCollapsablePanel(selectUser, Users.STAFF_ATTORNEYS);

			

		}

	}

}
