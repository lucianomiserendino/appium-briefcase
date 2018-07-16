package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DB_LIST_OF_CATEGORIES;
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
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.*;
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
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users;
import gov.uscourts.ao.mobileBriefcase.common.Servers.servers;
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

	@iOSFindBy(accessibility = "Test Automation")
	public static MobileElement testAutomation;

	public void getServer(servers server) {
		refresh();

		switch (server) {
		case CMKA:

			referralCategories(DBType.CMKA, DB_LIST_OF_CATEGORIES);
			break;

		case CM5A:

			referralCategories(DBType.CM5A, DB_REFERRAL_CATEGORIES);
			break;
		default:
			break;
		}

	}

	public static List<String> referralCategories(DBType dbtype, String query) {
		List<String> categories = new ArrayList<>();
		List<String> dbApplicableActions = executeQuery(dbtype, query);
		sort(dbApplicableActions);
		try {
			performPageLoad();
			for (int i = 0; i < dbApplicableActions.size(); ++i) {
				
				MobileElement actions = waitForElement(findElement(By.id(dbApplicableActions.get(i))));
				assertTrue(actions.isDisplayed());
				actions.getText();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		categories.addAll(dbApplicableActions);
		sort(categories);
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
	
	
	public void getID() {
		
	}
	
	

	public String verifyNumOfNonOrallyARGCases(MobileElement element) {
		return uiNonOrallyARGCases(element);

	}

	public String uiNonOrallyARGCases(MobileElement element) {
		clickOn(element);
		performPageLoad();
		return getNumOfDisplayedCases(waitForElement(total));
	}

	public static void getNonOrallyARGCases(String siValueN, String siValueY, String uiNonOrallyARGCases) {

		List<String> DBnonOrallyarguedCasesiValueN = executeQuery(DBType.CMKA, siValueN);
		List<String> DBnonOrallyarguedCaseSiValueY = executeQuery(DBType.CMKA, siValueY);

		List<String> UInonOrallyarguedCases = Arrays.asList(uiNonOrallyARGCases);

		assertTrue("-----RECORD COUNT MISMATCHED-----",
				DBnonOrallyarguedCasesiValueN.containsAll(UInonOrallyarguedCases)
						|| DBnonOrallyarguedCaseSiValueY.containsAll(UInonOrallyarguedCases));

	}
	
	public static void main(String[] args) {
		Queries g=new Queries();
		g.setPeId("32");
	//	System.out.println(g.getPeId());
		 System.out.println(executeQuery(DBType.CMKA, PETITIONS_FOR_REHEARING_SI_VALUE_N));
	}
	
	
	
	
	
	
	
	

}