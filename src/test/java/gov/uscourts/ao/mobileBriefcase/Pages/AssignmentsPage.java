package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_INFO;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.trim;
import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.waitForVisibilityOfElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getRandomNumberInRange;
import static java.util.stream.Collectors.toList;
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
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Filter;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AssignmentsPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Assignments']/following::XCUIElementTypeStaticText[contains(@name, '')]/preceding::XCUIElementTypeStaticText[2]")
	public static List<WebElement> assignments;

	private static String firstName = "";
	private static String lastName = "";
	private static String assignDateType = "";
	private static String assignType = "";
	private static String assignDate = "";

	public boolean isAssignmentsExist() {
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

						// Store details in the map
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

			// Get details of the clicked element
			List<String> clickedElementDetails = xpathToDetailsMap.get(randomXpath);
			if (clickedElementDetails != null) {
				String prFirstName = clickedElementDetails.get(0);
				String prLastName = clickedElementDetails.get(1);
				String cavDescription = clickedElementDetails.get(2);
				String cdvDescription = clickedElementDetails.get(3);
				String chdDate = clickedElementDetails.get(4);

				// Use the extracted values as needed

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

			assertTrue("VERIFY THAT CORRECT STAFF ASSIGNMENTS ARE DISPLAYING ON THE REFERRAL LIST PAGE: "+xpath, Utility.isDisplayed(xpath));
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

	public void ifAssignmentsSorted(List<String> dbAssignmentList, List<UserInputData> userInputData) {
		List<String> allAssignments = new ArrayList<>();

		List<String> judgeAssignments = new ArrayList<>();
		List<String> staffAssignments = new ArrayList<>();

		for (int i = 0; i < assignments.size(); i++) {
			allAssignments.add(assignments.get(i).getText().split(",")[0].split(" ")[1].trim());
		}
		String env = SystemPropertySetup.getCourtId(userInputData) + ".";

		String fName = getProperty(env + "jud");

		if (dbAssignmentList.contains(fName))
			dbAssignmentList.remove(fName);

		judgeAssignments = Utility.filterArraylistItems(Filter.UNIQUE_VALUES, allAssignments, dbAssignmentList);

		staffAssignments = Utility.filterArraylistItems(Filter.DUPLICATE_VALUES, allAssignments, dbAssignmentList);

		Utility.ifSortedInAlphabeticalOrder(judgeAssignments);
		Utility.ifSortedInAlphabeticalOrder(staffAssignments);

	}

	public static List<String> getAssignmentsFirstName(List<UserInputData> userInputData, String caseId, String peID,
			String cmr_cyv_code) {
		return executeQuery(replace(STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL, "CMR_CS_CASEID", caseId, "CHA_JU_PE_ID",
				peID, "CMR_CYV_CODE", cmr_cyv_code), userInputData);
	}

	public static List<String> getAssignmentsLastName(List<UserInputData> userInputData, String query, String string,
			String caseId, String peID, String cmr_cyv_code) {
		return executeQuery(replace(replace(query, "PR_LAST_NAME", string), "CMR_CS_CASEID", caseId, "CHA_JU_PE_ID",
				peID, "CMR_CYV_CODE", cmr_cyv_code), userInputData);
	}

	public static List<String> getAssignmentTypeAndDate(List<UserInputData> userInputData, String query,
			String string) {
		return executeQuery(replace(query, "CHD_CHA_ID", string), userInputData);

	}

	public static List<String> getListOfAssignmentInfo(String cmr_id, List<String> asignements, int randomAssignment,
			List<UserInputData> userInputData) {

		if (randomAssignment == 0) {
			return uniqueValues(cmr_id, getAssignmentInfo(cmr_id, randomAssignment, userInputData),
					getAssignmentInfo(cmr_id, randomAssignment + 1, userInputData));

		} else if (randomAssignment == asignements.size()) {
			return uniqueValues(cmr_id, getAssignmentInfo(cmr_id, randomAssignment - 1, userInputData),
					getAssignmentInfo(cmr_id, randomAssignment - 2, userInputData));

		} else if (randomAssignment == asignements.size() - 1) {
			return uniqueValues(cmr_id, getAssignmentInfo(cmr_id, randomAssignment, userInputData),
					getAssignmentInfo(cmr_id, randomAssignment - 1, userInputData));

		} else {
			List<String> aa = uniqueValues(cmr_id, getAssignmentInfo(cmr_id, randomAssignment, userInputData),
					getAssignmentInfo(cmr_id, randomAssignment + 1, userInputData));

			List<String> bb = uniqueValues(cmr_id, getAssignmentInfo(cmr_id, randomAssignment, userInputData),
					getAssignmentInfo(cmr_id, randomAssignment - 1, userInputData));

			return uniqueValues(cmr_id, aa, bb);
		}
	}

	public static List<String> uniqueValues(String cmr_id, List<String> listOne, List<String> listTwo) {
		if (listOne.get(0).equals(listTwo.get(0)) && listOne.get(1).equals(listTwo.get(1))
				&& listOne.get(2).equals(listTwo.get(2))) {

			List<String> commonElementsFromBothList = new ArrayList<>();

			commonElementsFromBothList.addAll(listOne.stream().filter(str -> listTwo.contains(str)).collect(toList()));

			commonElementsFromBothList.addAll(listOne.stream().filter(str -> !listTwo.contains(str)).collect(toList()));

			commonElementsFromBothList.addAll(listTwo.stream().filter(str -> !listOne.contains(str)).collect(toList()));

			return commonElementsFromBothList;

		} else {

			return listOne;
		}
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

	public static List<String> getAssignmentInfo(String cmr_id, int index, List<UserInputData> userInputData) {

		List<String> assignInfo = new ArrayList<>();
		String value = null;

		for (int i = 2; i <= 11; i++) {
			value = execute(replace(ASSIGNMENT_INFO, "CMR_ID", cmr_id), i, userInputData).get(index);

			assignInfo.add(trim(value));
		}

		return assignInfo;
	}

	public String getAssignmentInfo(String cmr_id, List<String> asignements, final int randomAssignment,
			AssignmentInfo info, List<UserInputData> userInputData) {

		String assignmentInformation = "";
		List<String> dbColumn = getListOfAssignmentInfo(cmr_id, asignements, randomAssignment, userInputData);

		String assignmentDueDate = getLatestAssignmentDate(dbColumn, "Assignment Due");
		String assignedDate = getLatestAssignmentDate(dbColumn, "Assigned");

		switch (info) {

		case ASSINMENT_TYPE:

			assignmentInformation = containsElement(dbColumn.get(1) + " " + dbColumn.get(0) + ", " + dbColumn.get(2));
			break;

		case NAME_OF_THE_ASSIGNEE_AND_LATEST_ASSIGNMENT_DATE:

			String assignName = preceding() + dbColumn.get(1) + " " + dbColumn.get(0) + "')]";

			if (!(assignmentDueDate.length() == 0)) {
				assignmentInformation = containsElement(assignmentDueDate) + assignName;
			} else {
				assignmentInformation = containsElement(assignedDate) + assignName;
			}
			break;

		case ASSIGNMENT_TYPE_AND_RELIEF:

			String relief = execute(replace(ASSIGNMENT_INFO, "CMR_ID", cmr_id), 9, userInputData).get(randomAssignment);
			if (!(relief.length() == 0)) {
				assignmentInformation = assignInfo(relief, dbColumn.get(2));
			} else {
				assignmentInformation = containsElement(dbColumn.get(2));
			}
			break;

		case LATEST_ASSIGNED_ASSIGNMENT_DUE_DATES:

			if (!(assignedDate.length() == 0) && assignmentDueDate.length() == 0) {
				assignmentInformation = assignInfo(assignedDate, "Assigned");

			} else if (assignedDate.length() == 0 && !(assignmentDueDate.length() == 0)) {
				assignmentInformation = assignInfo(assignmentDueDate, "Assignment Due");
			} else {
				List<String> latestAsiignmentDates = new ArrayList<>();

				String assignDate = assignInfo(assignedDate, "Assigned");
				String assignDueDate = assignInfo(assignmentDueDate, "Assignment Due");

				latestAsiignmentDates.add(assignDate);
				latestAsiignmentDates.add(assignDueDate);
				int d = getRandomNumberInRange(0, latestAsiignmentDates.size() - 1);
				assignmentInformation = latestAsiignmentDates.get(d);
			}

			break;

		case ASSIGNMENT_NOTE_DATE:
			String can_dm_dls_id_AND_can_dm_dls_id = getAssignmentNotes(randomAssignment, cmr_id, 7, 8, userInputData);
			String cdn_dm_dls_id_AND_cdn_date_created = getAssignmentNotes(randomAssignment, cmr_id, 10, 11,
					userInputData);

			if (!(can_dm_dls_id_AND_can_dm_dls_id.length() == 0) && cdn_dm_dls_id_AND_cdn_date_created.length() == 0) {
				assignmentInformation = can_dm_dls_id_AND_can_dm_dls_id;

			} else if (can_dm_dls_id_AND_can_dm_dls_id.length() == 0
					&& !(cdn_dm_dls_id_AND_cdn_date_created.length() == 0)) {
				assignmentInformation = cdn_dm_dls_id_AND_cdn_date_created;

			} else if (!(can_dm_dls_id_AND_can_dm_dls_id.length() == 0)
					&& !(cdn_dm_dls_id_AND_cdn_date_created.length() == 0)) {
				List<String> attachedNotes = new ArrayList<>();
				attachedNotes.add(can_dm_dls_id_AND_can_dm_dls_id);
				attachedNotes.add(cdn_dm_dls_id_AND_cdn_date_created);
				int notes = getRandomNumberInRange(0, attachedNotes.size() - 1);
				assignmentInformation = attachedNotes.get(notes);

			} else {
				assignmentInformation = "Assignment Notes";

			}

		default:
			break;
		}

		return assignmentInformation;

	}

	public static String assignInfo(String assignedDate, String assignmentType) {
		return containsElement(assignedDate) + preceding() + assignmentType + "')]";
	}

	public static String preceding() {
		return "/preceding::XCUIElementTypeStaticText[contains(@name, '";

	}

	public static String getAssignmentNotes(int randomAssignment, String cmr_id, int column1, int column2,
			List<UserInputData> userInputData) {

		String notes = "";
		try {
			String assignmentNote = execute(replace(ASSIGNMENT_INFO, "CMR_ID", cmr_id), column1, userInputData)
					.get(randomAssignment);
			if (!(assignmentNote.length() == 0)) {

				String assignmentNoteDate = execute(replace(ASSIGNMENT_INFO, "CMR_ID", cmr_id), column2, userInputData)
						.get(randomAssignment);

				String assignNoteDesc = getAllColumns(
						"select dm_description from document where dm_dls_id='" + assignmentNote + "'", userInputData);

				notes += "//XCUIElementTypeStaticText[@name='" + assignNoteDesc
						+ "']/preceding::XCUIElementTypeStaticText[@name='" + changeFormat(assignmentNoteDate) + "']";
			}
		} catch (NullPointerException e) {
		}

		return notes;

	}

	public static String getLatestAssignmentDate(List<String> assignmentType, String assignmentDate) {
		int k = 0;
		if (assignmentType.contains(assignmentDate)) {
			k += assignmentType.indexOf(assignmentDate);
			String type = changeFormat(assignmentType.get(k + 1));
			return type;
		} else {
			return "";
		}
	}

	public void getAssignmentInformation(String cmr_id, List<String> satffAssignments, int rnAssignment,
			List<String> info, List<UserInputData> userInputData) {
		tap(Locator.XPATH, getAssignmentInfo(cmr_id, satffAssignments, rnAssignment, AssignmentInfo.ASSINMENT_TYPE,
				userInputData));
		for (int i = 0; i < info.size(); i++) {
			if (!info.get(i).equals("Assignment Notes"))
				assertTrue(Actions.isDisplayed(Locator.XPATH, info.get(i)));
		}

	}

	public static String getCMR_ID(DBType dbType, String caseId, String peId, String cmr_cyv_code) {
		return getCMRID(dbType, "cmr_id", caseId, peId, cmr_cyv_code);
	}

	public static String getCMR_ID(String caseId, String peId, String cmr_cyv_code, List<UserInputData> userInputData) {
		return getCMRID("cmr_id", caseId, peId, cmr_cyv_code, userInputData);
	}

	public String getAssignmentDateType(String cmrId, String prLastName, String prFirstName, int column,
			List<UserInputData> userInputData, String assignType) {
		try {
			String query = Actions.replace(Queries.ASSIGNMENT_DATE_TYPE, "CMR_ID", cmrId, "PR_LAST_NAME", prLastName,
					"PR_FIRST_NAME", prFirstName);
			query = Actions.replace(query, "CAV_DISPLAY", assignType);

			List<String> result = execute(query, column, userInputData);
			if (result.isEmpty()) {
				throw new RuntimeException("No data returned for query: " + query);
			}

			return result.get(0);
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

			String cdvDisplay = getAssignmentDateType(cmrId, prLastName, prFirstName, 2, userInputData, assignType);

			String chdDate = getAssignmentDateType(cmrId, prLastName, prFirstName, 3, userInputData, assignType);

			String xpath = buildAssignmentDateXpath(cdvDisplay, chdDate, caseNumber, assignType);

			
			assertTrue("Pending Tasks folder: Verify each assignment displays the most recent date type",
					Actions.isDisplayed(Locator.XPATH, xpath));

		} catch (Exception e) {
			throw new RuntimeException("Error verifying recent assignment date", e);
		}
	}

	public static String buildAssignmentDateXpath(String cdvDisplay, String chdDate, String caseNumber,
			String cavDisplay) {
		String formattedDate = changeDateFormat(chdDate, "yyyy-MM-dd", "M/dd/yyyy").trim();
//		return "//XCUIElementTypeStaticText[contains(@name, '" + cdvDisplay.trim() + ": " + formattedDate + "')]"
//				+ "/preceding::XCUIElementTypeStaticText[1][contains(@name, '" + caseNumber.trim() + "')]"
//				+ "/preceding::XCUIElementTypeStaticText[contains(@name, '" + cavDisplay.trim() + "')]";
		
		return "//XCUIElementTypeStaticText[contains(@name, '" + cdvDisplay.trim() + ": " + formattedDate + "')]"
		+ "/preceding::XCUIElementTypeStaticText[1][contains(@name, '" + caseNumber.trim() + "')]";
	}

	public enum AssignmentInfo {
		ASSINMENT_TYPE, ASSIGNMENT_DATE, NAME_OF_THE_ASSIGNEE_AND_LATEST_ASSIGNMENT_DATE, ASSIGNMENT_TYPE_AND_RELIEF,
		LATEST_ASSIGNED_ASSIGNMENT_DUE_DATES, ASSIGNMENT_NOTE_DATE
	}

}
