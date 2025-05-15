package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CASES_ON_CALENDAR_SESSIONS;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCaseID;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.SiteTableVariable;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage.Category;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage.Sort;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CalendarPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"SessionGroups\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/following:: XCUIElementTypeStaticText[contains(@name, '(')]")
	public static List<WebElement> categoryCount;

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
	    int randomSortOrder = Utility.getRandomNumberInRange(1, 2);
	    String dateFormat = "M yyyy";

	    ReferralSortOrderPage sortOrderPage = new ReferralSortOrderPage();
	    sortOrderPage.selectSortBtn();

	    try {
	        if (randomSortOrder == 1) {
	            sortOrderPage.getSortPage(Sort.REFERRAL_DATE_DESCENDING);
	            getGroupIcons(GroupIcons.Expand);
	            assertTrue("The main headers are not sorted by \"MONTH YEAR\" in descending order: ",
	                    Utility.checkDatesForDescOrder(getMonthlySessions(), dateFormat));
	        } else if (randomSortOrder == 2) {
	            sortOrderPage.getSortPage(Sort.REFERRAL_DATE_ASCENDING);
	            getGroupIcons(GroupIcons.Expand);
	            assertTrue("The main headers are not sorted by \"MONTH YEAR\" in ascending order: ",
	                    Utility.checkDatesForAscOrder(getMonthlySessions(), dateFormat));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        fail("An exception occurred during the sorting check: " + e.getMessage());
	    }
	}


	public String getSubHeader(String oralArgView, String courtSession) {

		List<Integer> count = new ArrayList<>();

		int s = categoryCount.size();

		for (int i = 0; i < s; i++) {

			count.add(Integer.parseInt(Actions.replace(categoryCount.get(i).getText(), "\\(", "", "\\)", "")));
		}

		Integer max = Collections.max(count);

		String categoryName = Actions.containsElement("(" + max.toString() + ")")
				+ "/preceding:: XCUIElementTypeStaticText[1]";

		String month = Actions.findElementBy(Locator.XPATH, categoryName).getText().trim();

		Actions.tap(Locator.XPATH, categoryName);

		/**
		 * If briefcaseOralArgsView = n or doesn't exist, subheaders display "Day, Month
		 * Date
		 */
		if (oralArgView.equals("n") || oralArgView.isEmpty()) {

			return newUI(max, month);
		} else {

			/**
			 * If briefcaseOralArgsView = y, cases are organized based on weekly sessions
			 */
			return oldUI(courtSession, max, month);
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

		boolean found = true;

		while (found) {
			List<WebElement> icons = Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + month
					+ "']/following::XCUIElementTypeStaticText[@name=\"GroupIcon\"]"));

			found = false;

			for (WebElement icon : icons) {
				if (icon.getAttribute("value").equals("▽")) {
					icon.click();
					found = true; // Found and clicked an icon, need to recheck the list
					break; // Break the loop to recheck the list from the start
				}
			}
		}

		List<Integer> c = new ArrayList<>();

		List<WebElement> assignmentCount = Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + month
				+ "']/following::XCUIElementTypeStaticText[contains(@name, '(')]"));

		int i1 = 0;
		for (WebElement element : assignmentCount) {
			String value = Actions.replace(element.getText(), "\\(", "", "\\)", "").trim();
			int intValue = Integer.parseInt(value);
			c.add(intValue);
			i1 += intValue;
			if (i1 >= numOfCases) {
				break;
			}
		}

		Integer m = Collections.max(c);

		hearing = Actions.findElement(By.xpath(assinmentType(month, m) + "/preceding::XCUIElementTypeStaticText[1]"))
				.getText().trim();

		assignmentCount.get(c.indexOf(m)).click();

		caseN += getCaseNumber();

		return getHearingDate(hearing);

	}

	public static String assinmentType(String month, Integer m) {
		return "//XCUIElementTypeOther[@name='SessionGroups']/XCUIElementTypeScrollView/XCUIElementTypeOther//following::XCUIElementTypeStaticText[@name='"
				+ month + "']/following::XCUIElementTypeStaticText[contains(@name, '" + "(" + m.toString() + ")"
				+ "')][1]";
	}

	public static String getCaseNumber() {

		DocumentPage page = new DocumentPage();
		String caseN = page.getRandomCase(Category.CaseOnCalendar).split(" ")[0].trim();
		driver.navigate().back();
		return caseN;

	}

	public boolean selectRandomCase(String caseNumber, String hearing, List<UserInputData> userInputData) {
		try {
			String uiPanelText = Actions
					.findElement(By.xpath("(//XCUIElementTypeStaticText[contains(@name, '" + caseNumber
							+ "')]/following::XCUIElementTypeStaticText[contains(@name, 'Panel:')])[1]"))
					.getText().trim();

			String panelId = DocumentPage.get_pe_id("jud", userInputData);

			// Fetching necessary fields from the court session
			String sessionTime = getCourtSessionFields(CourtSession.ARG_DISPLAY, panelId, hearing, userInputData,
					caseNumber);
			String panelMembers = getCourtSessionFields(CourtSession.CMR_PANEL_MEMBERS, panelId, hearing, userInputData,
					caseNumber);
			String hearingOrder = getCourtSessionFields(CourtSession.HEARING_ORDER, panelId, hearing, userInputData,
					caseNumber);

			// Construct the expected panel string
			String expectedPanelText = trimIfNotNull("Panel:", panelMembers) + trimIfNotNull("Order:", hearingOrder)
					+ trimIfNotNull("Time:", sessionTime);

			return expectedPanelText.replace(" ", "").equalsIgnoreCase(uiPanelText.replace(" ", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private String trimIfNotNull(String prefix, String value) {
		return value != null ? prefix + value.trim() : "";
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
