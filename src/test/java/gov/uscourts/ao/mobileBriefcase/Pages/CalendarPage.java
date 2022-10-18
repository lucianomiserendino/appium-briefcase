package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASES_ON_CALENDAR_SESSIONS;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCaseID;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.checkDatesForAscOrder;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.checkDatesForDescOrder;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrolldown;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage.Sort;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CalendarPage extends AppiumPageFactory {

	static String CLU_DATE_HEARING = "clu_date_hearing";
	static String CTS_TERM = "cts_term";
	static String ARG_DISPLAY = "arg_display as arg_description";
	static String CTS_DATE_FROM = "cts_date_from";
	static String CTS_DATE_TO = "cts_date_to";
	static String CMR_PANEL_MEMBERS = "cmr_panel_members";

	// @WithTimeout(time = 300, unit = TimeUnit.SECONDS)

	// @WithTimeout(time = 300, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='SessionGroups']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static WebElement weeklySessions;

	// @WithTimeout(time = 200, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(accessibility = "SessionGroups")
	public WebElement sessionGroups;

	// @WithTimeout(time = 200, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DayGroups']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]")
	public static WebElement referral;

	// @WithTimeout(time = 200, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DayGroups']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]")
	public static WebElement argDescription;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DayGroups']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static WebElement hearingDate;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='GroupIcon']")
	public static List<WebElement> GroupIcon;

	static SimpleDateFormat format1;
	static SimpleDateFormat format2;

	public void getWeeklySession(List<UserInputData> userInputData) {
		performPageLoad(driver);

		String name = SystemPropertySetup.getJudge(userInputData);

		String peId = getPE_ID("jud", name, userInputData);

		findElementAndGetText(getText(weeklySessions), peId, userInputData);

	}

	public static void findElementAndGetText(String element, String peId, List<UserInputData> table) {
		String fromToDate = "";
		String initials = "";

		Boolean elementNotFound = true;
		while (elementNotFound) {
			try {

				if (weeklySessions.isDisplayed()) {
					initials += weeklySessions.getText();
					fromToDate += findElementBy(Locator.XPATH,
							containsElement(weeklySessions.getText()) + "/preceding::XCUIElementTypeStaticText[1]")
							.getText();
					tap(weeklySessions);
					performPageLoad(driver);
					break;
				}
			} catch (NoSuchElementException ex) {
				scrolldown();
			}

		}
		/**
		 * In the chm_mobile_referral table, there is a field for the panel_case.ph_id
		 * (cmr_ph_id), in the panel_case table there is a field for the
		 * panel_sitting.pns_id (ph_pns_id). In the panel_sitting table, there is a
		 * field for the cluster.clu_id. The cluster table stores the hearing date
		 * (clu_date_hearing), this is the date that is displayed when you tap on the
		 * weekly session.To find the dates (cts_date_to, cts_date_from) for the weekly
		 * session, query the court_session table where cts_id = clu_cts_id.
		 * 
		 */
		assertEQ(CLU_DATE_HEARING, getCourtSessionFields(CourtSession.CLU_DATE_HEARING, peId, table),
				getUiToFromDates(getCluDateHearing(hearingDate.getText())));

		assertEQ(ARG_DISPLAY, getCourtSessionFields(CourtSession.ARG_DISPLAY, peId, table),
				add(splitBy(argDescription.getText(), "Time:", 1).trim()));

		assertEQ(CMR_PANEL_MEMBERS, getCourtSessionFields(CourtSession.CMR_PANEL_MEMBERS, peId, table), add(initials));

	}

	public static void assertEQ(String courtSessionFiel, List<String> list, List<String> list2) {
		assertEquals("PLEASE MAKE SURE " + courtSessionFiel.toUpperCase() + " IS CORRECT", list, list2);
	}

	public static List<String> add(String text) {
		List<String> addString = new ArrayList<>();
		addString.add(text);
		return addString;

	}

	public static String splitBy(String weeklySession, String splitBy, int index) {
		return weeklySession.split(splitBy)[index];
	}

	/** You find the court_session session data through the panel information. */
	public static List<String> getUiToFromDates(String weeklySession) {

		String monthDate = splitBy(weeklySession, ",", 0).trim();

		String year = splitBy(weeklySession, ",", 1).trim();

		String month = splitBy(monthDate, " ", 0);

		String date = splitBy(monthDate, " ", 1);

		String toFromDate = year.trim() + "-" + Utility.parseMonthName(month) + "-" + date;
		List<String> uiToFromDate = new ArrayList<>();

		if (toFromDate.length() == 9) {
			uiToFromDate.add(new StringBuilder(toFromDate).insert(8, '0').toString());
		} else {
			uiToFromDate.add(toFromDate);
		}
		return uiToFromDate;
	}

	public static String getCluDateHearing(String text) {
		return text.substring(text.indexOf(",")).substring(2);
	}

	public static String split(String weeklySession, int index) {
		return splitBy(weeklySession, "-", index);
	}

	public static List<String> getCourtSessionFields(CourtSession session, String peId, List<UserInputData> table) {
		String courtSession = "";
		switch (session) {
		case CLU_DATE_HEARING:
			courtSession += CLU_DATE_HEARING;
			break;
		case CTS_TERM:
			courtSession += CTS_TERM;
			break;
		case ARG_DISPLAY:
			courtSession += ARG_DISPLAY;
			break;

		case CMR_PANEL_MEMBERS:
			courtSession += CMR_PANEL_MEMBERS;
			break;

		default:
			break;
		}
		return getCourtSessionTable(courtSession, peId, getCaseID(referral, table), table);
	}

	public static List<String> getCourtSessionTable(String value, String peID, String caseID,
			List<UserInputData> table) {
		return executeQuery(DBType.CMKA,
				replace(CASES_ON_CALENDAR_SESSIONS, "VALUE", value, "CMR_JU_PE_ID", peID, "CMR_CS_CASEID", caseID));
	}

	public enum CourtSession {
		CLU_DATE_HEARING, CTS_TERM, ARG_DISPLAY, CTS_DATE_FROM, CTS_DATE_TO, CMR_PANEL_MEMBERS

	}


	public void isSortedByMonthAndYear() {
		int random = Utility.getRandomNumberInRange(1, 2);

		String format = "M yyyy";

		ReferralSortOrderPage page = new ReferralSortOrderPage();
		page.selectSortBtn();

		if (random == 1) {
			page.getSortPage(Sort.REFERRAL_DATE_DESCENDING);
			getGroupIcons();
			assertTrue(checkDatesForDescOrder(getMonthlySessions(), format));

		} else if (random == 2) {
			page.getSortPage(Sort.REFERRAL_DATE_ASCENDING);
			getGroupIcons();
			assertTrue(checkDatesForAscOrder(getMonthlySessions(), format));

		}
		page.getSortPage(Sort.REFERRAL_DATE_DESCENDING);

	}

	public static List<String> getMonthlySessions() {

		List<String> sortedSessions = new ArrayList<>();

		List<WebElement> groups = getSessionGroups(2);
		 for (int i = 0; i < groups.size(); i++) {


			String session = groups.get(i).getText();

			String month = session.substring(0, session.indexOf(" ")).trim();

			Utility.parseMonthName(month);

			sortedSessions.add(session.replace(month, Utility.parseMonthName(month)));

		}

		return sortedSessions;

	}

	public static List<WebElement> getSessionGroups(int index) {
		return Actions.findElements(By.xpath(
				"	//XCUIElementTypeOther[@name='SessionGroups']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther["
						+ index + "]/XCUIElementTypeStaticText"));
	}

	
}
