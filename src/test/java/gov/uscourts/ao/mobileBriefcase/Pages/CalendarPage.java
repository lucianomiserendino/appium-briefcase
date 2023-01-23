package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASES_ON_CALENDAR_SESSIONS;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCaseID;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.checkDatesForAscOrder;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.checkDatesForDescOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.SiteTableVariable;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage.Category;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage.Sort;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;

public class CalendarPage extends AppiumPageFactory {

	static String CLU_DATE_HEARING = "clu_date_hearing";
	static String CTS_TERM = "cts_term";
	static String ARG_DISPLAY = "arg_display as arg_description";
	static String CTS_DATE_FROM = "cts_date_from";
	static String CTS_DATE_TO = "cts_date_to";
	static String CMR_PANEL_MEMBERS = "cmr_panel_members";
	static String HEARING_ORDER = "ph_hearing_order";
	public static String caseN = "";

	/**
	 * This verifies that the main headers display "Month Year" and they are sorted
	 * either in ascending or descending order, based on what sort option is
	 * selected by the user
	 */
	public void isSortedByMonthAndYear() {

		int random = Utility.getRandomNumberInRange(1, 2);

		String format = "M yyyy";

		ReferralSortOrderPage page = new ReferralSortOrderPage();
		page.selectSortBtn();

		if (random == 1) {
			page.getSortPage(Sort.REFERRAL_DATE_DESCENDING);
			getGroupIcons();

			assertTrue("THE MAIN HEADERS ARE NOT SORTED BY \"MONTH YEAR\" IN DESCENDING ORDER: ",
					checkDatesForDescOrder(getMonthlySessions(), format));

		} else if (random == 2) {
			page.getSortPage(Sort.REFERRAL_DATE_ASCENDING);
			getGroupIcons();
			assertTrue("THE MAIN HEADERS ARE NOT SORTED BY \"MONTH YEAR\" IN ASCENDING ORDER: ",
					checkDatesForAscOrder(getMonthlySessions(), format));

		}
	}

	public String getSubHeader(String oralArgView, String courtSession) {

		List<WebElement> groups = getSessionGroups(3);

		int mainHeader = Utility.getRandomNumberInRange(1, 4);/**
																 * expands one of the random main header
																 */
		int numOfCases = totalNumOfCases(groups, mainHeader);

		String month = getSession(mainHeader);

		/**
		 * If briefcaseOralArgsView = n or doesn't exist, subheaders display "Day, Month
		 * Date
		 */
		if (oralArgView.equals("n") || oralArgView.isEmpty()) {

			return newUI(numOfCases, month);
		} else {

			/** If briefcaseOralArgsView = y, cases are organized based on weekly sessions */
			return oldUI(courtSession, numOfCases, month);
		}

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

	public static String getSession(int i) {
		List<WebElement> monthlySession = getSessionGroups(2);
		String session = monthlySession.get(i).getText();
		monthlySession.get(i).click();
		return session;
	}

	public static int totalNumOfCases(List<WebElement> groups, int randomGroup) {
		return Integer.parseInt(Actions.replace(groups.get(randomGroup).getText(), "\\(", "", "\\)", "").trim());
	}

	public static String newUI(int numOfCases, String month) {
		String hearing = "";
		int subHeader = 0;
		int caseCount = 1;
		int total = 0;

		Boolean elementNotFound = true;

		while (elementNotFound) {

			List<WebElement> icons = Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + month
					+ "']/following::XCUIElementTypeStaticText[@name=\"GroupIcon\"]"));

			for (int i = 0; i < icons.size(); i++) {

				if (icons.get(i).getAttribute("value").equals("▽")) {

					icons.get(i).click();

				}

				List<WebElement> groups = Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + month
						+ "']/following::XCUIElementTypeStaticText[contains(@name, '(')]"));

				int left = totalNumOfCases(groups, i);

				total += left;

				if (total == numOfCases) {

					if (caseCount > 1) {
						subHeader = Utility.getRandomNumberInRange(0, caseCount - 1);
					} else {
						subHeader = 0;
					}

					int n1 = subHeader, n2 = 1, sum;
					sum = n1 + n2;

					hearing = Actions.findElement(By.xpath("(//XCUIElementTypeStaticText[@name='" + month
							+ "']/following::XCUIElementTypeStaticText[@name='GroupIcon']/following::XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1])["
							+ sum + "]")).getText();

					icons.get(subHeader).click();
					elementNotFound = false;

					break;

				} else {
					caseCount++;
					elementNotFound = true;

				}

			}
		}
		caseN += getCaseNumber();
		return getHearingDate(hearing);

	}

	public static String getCaseNumber() {

		DocumentPage page = new DocumentPage();
		String caseN = page.getRandomCase(Category.CaseOnCalendar).split(" ")[0].trim();
		driver.navigate().back();
		return caseN;

	}

	public boolean selectRandomCase(String caseN, String hearing, List<UserInputData> userInputData) {

		String uiPanel = Actions.findElement(By.xpath("(//XCUIElementTypeStaticText[contains(@name, '" + caseN
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, 'Panel:')])[1]")).getText().trim();

		String peId = DocumentPage.get_pe_id("jud",userInputData);

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

		String time = getCourtSessionFields(CourtSession.ARG_DISPLAY, peId, hearing, userInputData, caseN);
		String panelMembers = getCourtSessionFields(CourtSession.CMR_PANEL_MEMBERS, peId, hearing, userInputData,
				caseN);
		String hearing_order = getCourtSessionFields(CourtSession.HEARING_ORDER, peId, hearing, userInputData, caseN);

		String dbPanel = trimIffNull("Panel:", panelMembers) + trimIffNull("Order:", hearing_order)
				+ trimIffNull("Time:", time);

		String a1 = dbPanel;
		String b = uiPanel;
		a1 = a1.replace(" ", "");
		b = b.replace(" ", "");

		if (a1.equalsIgnoreCase(b)) {

			return true;
		} else {
			return false;
		}

	}

	public static String getCourtSessionFields(CourtSession session, String peId, String hearing,
			List<UserInputData> table, String caseNum) {
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

		case HEARING_ORDER:
			courtSession += HEARING_ORDER;
			break;

		case CTS_DATE_FROM:
			courtSession += CTS_DATE_FROM;
			break;
		case CTS_DATE_TO:
			courtSession += CTS_DATE_TO;
			break;

		default:
			break;
		}

		return getCourtSessionTable(courtSession, peId, getCaseID(caseNum, table), hearing, table);
	}

	public static String getCourtSessionTable(String value, String peID, String caseID, String hearing,
			List<UserInputData> table) {
		return DBUtilities.getAllColumns(Actions
				.replace(CASES_ON_CALENDAR_SESSIONS, "VALUE", value, "CMR_JU_PE_ID", peID, "CMR_CS_CASEID", caseID)
				.replace("CLU_DATE_HEARING", hearing), table);
	}

	public enum CourtSession {
		CLU_DATE_HEARING, CTS_TERM, ARG_DISPLAY, CTS_DATE_FROM, CTS_DATE_TO, CMR_PANEL_MEMBERS, HEARING_ORDER

	}

	public static String trimIffNull(String text, String field) {
		String string = "";
		String dbCol = field.trim();
		if (dbCol.equals("null") || dbCol.isEmpty()) {
			return string;

		} else {
			string = text + field;
		}
		return string;
	}

	public String getbriefcaseOralArgsView(SiteTableVariable siVal, List<UserInputData> table) {
		return CommonPages.getSiValue(siVal, table).trim();

	}

	public String oldUI(String courtSession, int numOfCases, String mainHeader) {
		WebElement monthName = null;

		int randomGroup = 0;

		if (numOfCases > 1) {
			randomGroup = Utility.getRandomNumberInRange(1, numOfCases);
		} else {
			randomGroup = 1;
		}

		//String panel = getXpathOfSession(randomGroup, mainHeader, 1).getText();

		monthName = getXpathOfSession(randomGroup, mainHeader, 2);

		String subHeader = monthName.getText().trim();

		String txt = mainHeader.substring(3, mainHeader.indexOf(" "));
		String mainHeaderTxt = mainHeader.replace(txt, "");

		if (courtSession.equals("y")) {
			/**
			 * If briefcaseUseCourtSession = y, the app will use the To/From fields in the
			 * court_session table for the weekly session dates.
			 */
			String subHeader1 = split(subHeader, 0);
			String subHeader2 = split(subHeader, 1);

			assertEquals(mainHeaderTxt, replace(subHeader1));
			assertEquals(mainHeaderTxt, replace(subHeader2));

		} else {

			/**
			 * If briefcaseUseCourtSession = n or doesn't exist, the app will calculate the
			 * weekly sessions based on the hearing date in the cluster table
			 */

			assertEquals(mainHeaderTxt, replace(subHeader));
		}

		monthName.click();

		caseN += getCaseNumber();

		String hearing = Actions.findElement(By.xpath("//*[contains(@name, '" + caseN
				+ "')]/preceding:: XCUIElementTypeStaticText[contains(@name, '" + mainHeader.split(" ")[0] + "')][1]"))
				.getText();

		return getHearingDate(hearing);

	}

	public WebElement getXpathOfSession(int randomGroup, String month, int i) {
		return Actions.findElement(By.xpath("(//XCUIElementTypeStaticText[@name='" + month
				+ "']/following::XCUIElementTypeStaticText[contains(@name, '')])[" + randomGroup
				+ "]/preceding::XCUIElementTypeStaticText[" + i + "]"));
	}

	public static String getHearingDate(String hearing) {

		String hearingDate = "";
		String nameOfTheMonth = "";
		String index = "";

		String session = hearing.split(", ", 2)[1];

		nameOfTheMonth += session.substring(0, session.indexOf(" ")).trim();

		index += Utility.parseMonthName(nameOfTheMonth);

		hearingDate += session.replace(nameOfTheMonth, index).trim();

		return changeDateFormat(hearingDate.replace(",", ""), "MM dd yyyy", "yyyy-M-d");

	}

	public static String replace(String header) {
		String a = header.substring(3, header.indexOf(",") + 1);
		return header.replace(a, "");
	}

	public static String split(String str, int index) {
		return str.split(" - ")[index];
	}

}
