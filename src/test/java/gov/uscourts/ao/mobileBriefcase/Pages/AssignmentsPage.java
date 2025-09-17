package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AssignmentsPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Assignments']/following::XCUIElementTypeStaticText[contains(@name, '')]/preceding::XCUIElementTypeStaticText[2]")
	public static List<WebElement> assignments;

	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;

	private static String firstName = "";
	private static String lastName = "";
	private static String assignDateType = "";
	private static String assignType = "";
	private static String assignDate = "";

	public boolean ifAssignmentsExist() {
		CommonPages page = new CommonPages();
		page.getGroupIcons(GroupIcons.Expand);

		boolean isDisplayed = false;

		List<WebElement> elems = findElements(By.xpath(Actions.containsElement("Assignments")));
		try {
			if (elems.size() > 0)
				elems.get(0).click();
			isDisplayed = true;
		} catch (WebDriverException e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	/** Find staff assignments associated with the referral */
	public List<String> getAssignmentsLinkedToReferral(List<UserInputData> userInputData, String caseId, String peID,
			String cmr_cyv_code) {
		collapseBtn.click();
		List<String> staffAssignments = new ArrayList<>();
		List<String> dbAssignments = new ArrayList<>();
		Map<String, List<String>> xpathToDetailsMap = new HashMap<>();

		String staffAssignmentsQuery = Queries.STAFF_ASSIGNMENTS.replace("CMR_CS_CASEID", caseId).replace("?", peID)
				.replace("CMR_CYV_CODE", cmr_cyv_code);

		List<String[]> firstQueryResult = DBUtilities.executeDBQuery(staffAssignmentsQuery, userInputData);

		if (firstQueryResult != null) {
			for (String[] record : firstQueryResult) {
				String chaId = record[0];
				String prFirstName = record[1];
				String prLastName = record[2];
				String cavDescription = record[3];

				String assigneesNameAndLastDateQuery = Queries.ASSIGNEES_NAME_AND_LASTED_DATE_TYPE.replace("?", chaId);

				List<String[]> secondQueryResult = DBUtilities.executeDBQuery(assigneesNameAndLastDateQuery,
						userInputData);

				if (secondQueryResult != null) {
					for (String[] secondRecord : secondQueryResult) {
						String cdvDescription = secondRecord[0];
						String chdDate = secondRecord[1];

						String xpath = "//*[contains(@name, '" + prFirstName.trim() + " " + prLastName.trim() + ", "
								+ cavDescription.trim() + "')]/following::XCUIElementTypeStaticText[contains(@name, '"
								+ cdvDescription.replaceAll("Date", "").trim() + " " + changeFormat(chdDate).trim()
								+ "')]";

						staffAssignments.add(xpath);

						List<String> details = new ArrayList<>();
						details.add(prFirstName);
						details.add(prLastName);
						details.add(cavDescription);
						details.add(cdvDescription);
						details.add(chdDate);
						xpathToDetailsMap.put(xpath, details);
					}
				}
			}
		}

		checkStaffAssignments(staffAssignments);

		if (!staffAssignments.isEmpty()) {
			Random random = new Random();
			int randomIndex = random.nextInt(staffAssignments.size());
			String randomXpath = staffAssignments.get(randomIndex);
			WebElement element = driver.findElement(By.xpath(randomXpath));
			element.click();
			collapseBtn.click();
			Page.performPageLoad(driver);

			List<String> clickedElementDetails = xpathToDetailsMap.get(randomXpath);
			if (clickedElementDetails != null) {
				String prFirstName = clickedElementDetails.get(0);
				String prLastName = clickedElementDetails.get(1);
				String cavDescription = clickedElementDetails.get(2);
				String cdvDescription = clickedElementDetails.get(3);
				String chdDate = clickedElementDetails.get(4);

				firstName += prFirstName;
				lastName += prLastName;
				assignType += cavDescription;
				assignDateType += cdvDescription;
				assignDate += chdDate;

			}
		}

		return dbAssignments;
	}

	public void checkStaffAssignments(List<String> staffAssignments) {
		for (String xpath : staffAssignments) {

			assertTrue("VERIFY THAT CORRECT STAFF ASSIGNMENTS ARE DISPLAYING ON THE REFERRAL LIST PAGE: " + xpath,
					Utility.isDisplayed(xpath));
		}
	}

	public void verifyAssignmentInfoAndNoteDatesDisplayed() {
		String fullName = firstName.trim() + " " + lastName.trim();
		String formattedAssignDateType = assignDateType.replaceAll("Date", "").trim();
		String formattedAssignDate = changeFormat(assignDate).trim();

		assertTrue("Assignee's name is missing or incorrect on the assignment detail screen.",
				Actions.isDisplayed(Locator.XPATH, Actions.containsElement(fullName)));

		assertTrue("Assignment type is missing or incorrect on the assignment detail screen.",
				Actions.isDisplayed(Locator.XPATH, Actions.containsElement(assignType.trim())));

		assertTrue("Assignment date type or date is missing or incorrect on the assignment detail screen.",
				Actions.isDisplayed(Locator.XPATH, Actions.containsElement(formattedAssignDateType)
						+ "/following::XCUIElementTypeStaticText[@name='" + formattedAssignDate + "']"));
	}

	public static String changeFormat(String assignDate) {
		String date = "";
		try {
			if (!(assignDate.length() == 0) && assignDate.contains(" ")) {
				date += changeDateFormat(assignDate.split(" ")[0], "yyyy-MM-dd", "M/d/yyyy");
			} else if (!(assignDate.length() == 0) && !assignDate.contains(" ")) {
				date += changeDateFormat(assignDate, "yyyy-MM-dd", "M/d/yyyy");
			} else {
				date += null;
			}
		} catch (NullPointerException e) {
			date += null;
		}
		return date;
	}

	public static String getCMR_ID(String caseId, String peId, String cmr_cyv_code, List<UserInputData> userInputData) {
		return getCMRID("cmr_id", caseId, peId, cmr_cyv_code, userInputData);
	}

	public List<String[]> getAssignmentDateType(String cmrId, String prLastName, String prFirstName,
			List<UserInputData> userInputData, String assignType) {
		try {
			String query = Actions.replace(Queries.ASSIGNMENT_DATE_TYPE, "CMR_ID", cmrId, "PR_LAST_NAME", prLastName,
					"PR_FIRST_NAME", prFirstName);
			query = Actions.replace(query, "CAV_DISPLAY", assignType);

			List<String[]> result = DBUtilities.executeDBQuery(query, userInputData);

			if (result.isEmpty()) {
				throw new RuntimeException("No data returned for query: " + query);
			}

			return result;
		} catch (Exception e) {
			throw new RuntimeException("Error getting assignment date type", e);
		}
	}

	public void getRecentAssignmentDate(List<UserInputData> userInputData, String caseNumber, String peId,
			String cmrCyvCode, String assignType) {
		try {
			String env = SystemPropertySetup.getCourtId(userInputData) + ".";
			String prLastName = getProperty(env + "jud");
			String prFirstName = getProperty(env + "judFirstName");

			String cmrId = getCMR_ID(caseNumber, peId, cmrCyvCode, userInputData);

			List<String[]> dbStafMember = getAssignmentDateType(cmrId, prLastName, prFirstName, userInputData,
					assignType);

			if (dbStafMember != null && !dbStafMember.isEmpty()) {
				String[] selectedRecord = null;

				for (String[] record : dbStafMember) {
					if ("Assignment Due".equalsIgnoreCase(record[0])) {
						selectedRecord = record;
						break;
					}
				}

				if (selectedRecord == null) {
					for (String[] record : dbStafMember) {
						if ("Assigned".equalsIgnoreCase(record[0])) {
							selectedRecord = record;
							break;
						}
					}
				}

				if (selectedRecord != null) {
					String cdvDisplay = selectedRecord[0];
					String chdDate = selectedRecord[1];

					String xpath = buildAssignmentDateXpath(cdvDisplay, chdDate, caseNumber, assignType);

					System.out.println(xpath+"_____________________________-");
					assertTrue("Pending Tasks folder: Verify each assignment displays the most recent date type",
							Actions.isDisplayed(Locator.XPATH, xpath));
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("Error verifying recent assignment date", e);
		}
	}

	public static String buildAssignmentDateXpath(String cdvDisplay, String chdDate, String caseNumber,
			String cavDisplay) {
		String formattedDate = changeDateFormat(chdDate, "yyyy-MM-dd", "M/dd/yyyy").trim();

		return "//XCUIElementTypeStaticText[contains(@name, '" + cdvDisplay.trim() + ": " + formattedDate + "')]"
				+ "/preceding::XCUIElementTypeStaticText[1][contains(@name, '" + caseNumber.trim() + "')]";
	}

	public enum AssignmentInfo {
		ASSINMENT_TYPE, ASSIGNMENT_DATE, NAME_OF_THE_ASSIGNEE_AND_LATEST_ASSIGNMENT_DATE, ASSIGNMENT_TYPE_AND_RELIEF,
		LATEST_ASSIGNED_ASSIGNMENT_DUE_DATES, ASSIGNMENT_NOTE_DATE
	}

}
