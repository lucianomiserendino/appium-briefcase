package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DB_REFERRAL_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MOTIONS_PETITIONS_SI_VALUE_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MOTIONS_PETITIONS_SI_VALUE_Y;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PETITIONS_FOR_REHEARING_SI_VALUE_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PETITIONS_FOR_REHEARING_SI_VALUE_Y;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SCREENING_PANELS_SI_VALUE_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SCREENING_PANELS_SI_VALUE_Y;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.select;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getNumOfDisplayedCases;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.refresh;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
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

	@iOSFindBy(accessibility = "Screening Panels")
	public static MobileElement screeningPanels;

	@iOSFindBy(accessibility = "Petitions for Rehearing")
	public static MobileElement petitionsForRehearing;

	@iOSFindBy(accessibility = "Cases on Calendar")
	public static MobileElement casesOnCalendar;

	@iOSFindBy(accessibility = "Motions/Petitions")
	public static MobileElement motionsPetitions;



	public String verifyIfPendingTasksAreDisplayed() {
		refresh();
		if (pendingTasks.isDisplayed()) {
			clickOn(pendingTasks);
		}
		performPageLoad();
		return getNumOfDisplayedCases(total);

	}

	public List<String> UIreferralCategoriesList() {
		refresh();
		performPageLoad();
		// return getListOfCategoriesCMKA(motionsPetitions, casesOnCalendar,
		// petitionsForRehearing, screeningPanels);
		return getListOfCategoriesCM5A();
	}

	public List<String> getListOfCategoriesCM5A() {
		List<String> categories = new ArrayList<>();
		categories.addAll(referralCategoriesCM5A());
		sort(categories);

		return categories;

	}

	public static List<String> referralCategoriesCM5A() {

		List<String> dbApplicableActions = executeQuery(DBType.CM5A, DB_REFERRAL_CATEGORIES);
		sort(dbApplicableActions);
		try {
			for (int i = 0; i < dbApplicableActions.size(); ++i) {

				MobileElement actions = waitForElement(
						findElement(By.xpath("//XCUIElementTypeTable[@name='Categories']/child::*//*[contains(@name, '"
								+ dbApplicableActions.get(i) + "')]")));
				actions.getText();
				if (actions.isDisplayed()) {
					actions.getText();

				} else {
					refresh();
					actions.getText();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return dbApplicableActions;
	}

	public void verifyNonOrallyArgCases() {
		refresh();
		getNonOrallyARGCases(PETITIONS_FOR_REHEARING_SI_VALUE_N, PETITIONS_FOR_REHEARING_SI_VALUE_Y,
				verifyNumOfNonOrallyARGCases(petitionsForRehearing));
		select(Users.DASHBOARD);
		performPageLoad();
		getNonOrallyARGCases(MOTIONS_PETITIONS_SI_VALUE_N, MOTIONS_PETITIONS_SI_VALUE_Y,
				verifyNumOfNonOrallyARGCases(motionsPetitions));
		select(Users.DASHBOARD);
		performPageLoad();
		getNonOrallyARGCases(SCREENING_PANELS_SI_VALUE_N, SCREENING_PANELS_SI_VALUE_Y,
				verifyNumOfNonOrallyARGCases(screeningPanels));

	}

	public void getNonOrallyARGCases(String siValueN, String siValueY, String uiNonOrallyARGCases) {

		List<String> DBnonOrallyarguedCasesiValueN = executeQuery(DBType.CMKA, siValueN);
		List<String> DBnonOrallyarguedCaseSiValueY = executeQuery(DBType.CMKA, siValueY);

		List<String> UInonOrallyarguedCases = Arrays.asList(uiNonOrallyARGCases);

		assertTrue("-----RECORD COUNT MISMATCHED-----",
				DBnonOrallyarguedCasesiValueN.containsAll(UInonOrallyarguedCases)
						|| DBnonOrallyarguedCaseSiValueY.containsAll(UInonOrallyarguedCases));

	}

	public String verifyNumOfNonOrallyARGCases(MobileElement element) {
		return uiNonOrallyARGCases(element);

	}

	public String uiNonOrallyARGCases(MobileElement element) {
		clickOn(element);
		performPageLoad();
		return getNumOfDisplayedCases(waitForElement(total));
	}

}