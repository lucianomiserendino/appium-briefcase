package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.AD_CHD_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_INFO;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_TYPE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CDV_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHA_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL_LAST_NAME;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.trim;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getRandomNumberInRange;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.Actions;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class AssignmentsPage extends AppiumPageFactory {

	/** Find staff assignments associated with the referral */
	public void getAssignmentsLinkedToReferral(DBType dbType, String caseId, String peID, String cmr_cyv_code) {

		List<String> staffAssignments = new ArrayList<>();
		List<String> staffAssignment = new ArrayList<>();
		List<String> dbStaffFName = getAssignmentsFirstName(dbType, caseId, peID, cmr_cyv_code);

		for (int i = 0; i < dbStaffFName.size(); i++) {

			List<String> dbStaffLName = getAssignmentsLastName(dbType,
					STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL_LAST_NAME, dbStaffFName.get(i), caseId, peID,
					cmr_cyv_code);

			for (int j = 0; j < dbStaffLName.size(); j++) {

				List<String> dbassignmentType = getAssignmentsLastName(dbType, ASSIGNMENT_TYPE, dbStaffFName.get(i),
						caseId, peID, cmr_cyv_code);

				for (int l = 0; l < dbassignmentType.size(); l++) {

					List<String> code = getAssignmentsLastName(dbType, CHA_ID, dbStaffFName.get(i), caseId, peID,
							cmr_cyv_code);

					for (int k = 0; k < code.size(); k++) {

						List<String> dbAssignment = getAssignmentTypeAndDate(dbType, CDV_DESCRIPTION, code.get(k));
						for (int m = 0; m < dbAssignment.size(); m++) {

							List<String> assignmentDate = getAssignmentTypeAndDate(dbType, AD_CHD_DATE, code.get(k));

							for (int n = 0; n < assignmentDate.size(); n++) {

								String a = "";
								if (dbAssignment.get(m).contains("Date")) {
									a = dbAssignment.get(m).replaceAll("Date", "");
								} else {
									a = dbAssignment.get(m) + " ";
								}

								staffAssignments.add(("//*[contains(@name, '" + dbStaffLName.get(j) + " "
										+ dbStaffFName.get(i) + ", " + dbassignmentType.get(l)
										+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + a
										+ changeFormat(assignmentDate.get(n)) + "')]").replaceAll("Date", ""));

								staffAssignment.add(dbStaffLName.get(j) + " " + dbStaffFName.get(i) + ", "
										+ dbassignmentType.get(l));
			}}	}}}
		}

		for (int i = 0; i < staffAssignments.size(); i++) {
			assertTrue(Actions.isDisplayed(Locator.XPATH, staffAssignments.get(i)));
		}
	}

	public static List<String> getAssignmentsFirstName(DBType dbType, String caseId, String peID, String cmr_cyv_code) {
		return executeQuery(dbType, replace(STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL, "CMR_CS_CASEID", caseId,
				"CHA_JU_PE_ID", peID, "CMR_CYV_CODE", cmr_cyv_code));
	}

	public static List<String> getAssignmentsLastName(DBType dbType, String query, String string, String caseId,
			String peID, String cmr_cyv_code) {
		return executeQuery(dbType, replace(replace(query, "PR_LAST_NAME", string), "CMR_CS_CASEID", caseId,
				"CHA_JU_PE_ID", peID, "CMR_CYV_CODE", cmr_cyv_code));
	}

	public static List<String> getAssignmentTypeAndDate(DBType dbType, String query, String string) {
		return executeQuery(dbType, replace(query, "CHD_CHA_ID", string));

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

	public String getAssignmentDateType(DBType dbType, String cmrId, String pr_last_name, String pr_first_name,
			int column) {
		return execute(dbType, Actions.replace(Queries.ASSIGNMENT_DATE_TYPE, "CMR_ID", cmrId, "PR_LAST_NAME",
				pr_last_name, "PR_FIRST_NAME", pr_first_name), column).get(0);
	}

	public void getRecentAssignmentDate(DBType dbType, String caseNumber, String peId, String cmr_cyv_code,
			String pr_last_name, String pr_first_name) {

		String cmrId = getCMR_ID(dbType, caseNumber, peId, cmr_cyv_code);

		String value = null;

		try {
			value = getAssignmentDateType(dbType, cmrId, pr_last_name, pr_first_name, 2);
			CommonPages.getGroupIcons();
			if (!(value.length() == 0)) {
				tap(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '" + value + "')]");

				assertTrue(Actions.isDisplayed(Locator.XPATH,
						getassignmentDate(getAssignmentDateType(dbType, cmrId, pr_last_name, pr_first_name, 3),
								getAssignmentDateType(dbType, cmrId, pr_last_name, pr_first_name, 4), caseNumber,
								getAssignmentDateType(dbType, cmrId, pr_last_name, pr_first_name, 2))));
			}
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}
	
	
	

	public static String getassignmentDate(String cdv_display, String chd_date, String caseNumber, String cav_display) {
		return "//XCUIElementTypeStaticText[contains(@name, '" + cdv_display + ": "
				+ changeDateFormat(chd_date, "yyyy-MM-dd", "M/d/yyyy") + "')]"
				+ "/preceding::XCUIElementTypeStaticText[1][contains(@name, '" + caseNumber + "')]"
				+ "/preceding::XCUIElementTypeStaticText[contains(@name, '" + cav_display + "')]";

	}

	public enum AssignmentInfo {
		ASSINMENT_TYPE, ASSIGNMENT_DATE, NAME_OF_THE_ASSIGNEE_AND_LATEST_ASSIGNMENT_DATE, ASSIGNMENT_TYPE_AND_RELIEF, LATEST_ASSIGNED_ASSIGNMENT_DUE_DATES, ASSIGNMENT_NOTE_DATE
	}

}
