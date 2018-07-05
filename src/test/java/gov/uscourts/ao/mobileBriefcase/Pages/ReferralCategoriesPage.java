package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MOTIONS_PETITIONS_SI_VALUE_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MOTIONS_PETITIONS_SI_VALUE_Y;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PETITIONS_FOR_REHEARING_SI_VALUE_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PETITIONS_FOR_REHEARING_SI_VALUE_Y;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SCREENING_PANELS_SI_VALUE_N;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SCREENING_PANELS_SI_VALUE_Y;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.select;
import static gov.uscourts.ao.mobileBriefcase.common.Page.*;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.*;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getListOfCategories;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getNumOfDisplayedCases;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.refresh;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Base.PlatformVersions;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ReferralCategoriesPage {

	public ReferralCategoriesPage() {

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
		return getListOfCategories(motionsPetitions, casesOnCalendar, petitionsForRehearing, screeningPanels);

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

		List<String> DBnonOrallyarguedCasesiValueN = executeQuery(siValueN);
		List<String> DBnonOrallyarguedCaseSiValueY = executeQuery(siValueY);

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