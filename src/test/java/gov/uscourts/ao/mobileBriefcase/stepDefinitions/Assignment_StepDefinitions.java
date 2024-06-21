package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_INFO;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getRandomNumberInRange;

import java.util.Arrays;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.AssignmentsPage;
import gov.uscourts.ao.mobileBriefcase.Pages.AssignmentsPage.AssignmentInfo;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.PendingTasksPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class Assignment_StepDefinitions {
	AssignmentsPage assig;

	static String cmr_id = "";
	static List<String> asignements;
	static List<String> info;
	static int rnAssignment = 0;
	static DBType db;

	@Given("^User gets judge's/staff assignment's info from DataBase  by using  \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_gets_judge_s_staff_assignment_s_info_from_DataBase_by_using(String caseId, String peId,
			String cmr_cyv_code, List<UserInputData> table) {

		assig = new AssignmentsPage();
		List<UserInputData> userInputData = null;

		cmr_id = assig.getCMR_ID(caseId, peId, cmr_cyv_code, userInputData);

		asignements = execute(replace(ASSIGNMENT_INFO, "CMR_ID", cmr_id), 2, userInputData);

		rnAssignment = getRandomNumberInRange(1, asignements.size() - 1);

		assig = new AssignmentsPage();

		String assignee = assig.getAssignmentInfo(cmr_id, asignements, rnAssignment,
				AssignmentInfo.NAME_OF_THE_ASSIGNEE_AND_LATEST_ASSIGNMENT_DATE, userInputData);

		String assignment_type = assig.getAssignmentInfo(cmr_id, asignements, rnAssignment,
				AssignmentInfo.ASSIGNMENT_TYPE_AND_RELIEF, userInputData);

		String assignment_due_date = assig.getAssignmentInfo(cmr_id, asignements, rnAssignment,
				AssignmentInfo.LATEST_ASSIGNED_ASSIGNMENT_DUE_DATES, userInputData);

		String assignment_note_date = assig.getAssignmentInfo(cmr_id, asignements, rnAssignment,
				AssignmentInfo.ASSIGNMENT_NOTE_DATE, userInputData);

		info = Arrays.asList(assignee, assignment_type, assignment_due_date, assignment_note_date);
		info = Arrays.asList(
				assig.getAssignmentInfo(cmr_id, asignements, rnAssignment,
						AssignmentInfo.NAME_OF_THE_ASSIGNEE_AND_LATEST_ASSIGNMENT_DATE, userInputData),

				assig.getAssignmentInfo(cmr_id, asignements, rnAssignment, AssignmentInfo.ASSIGNMENT_TYPE_AND_RELIEF,
						userInputData),

				assig.getAssignmentInfo(cmr_id, asignements, rnAssignment,
						AssignmentInfo.LATEST_ASSIGNED_ASSIGNMENT_DUE_DATES, userInputData),

				assig.getAssignmentInfo(cmr_id, asignements, rnAssignment, AssignmentInfo.ASSIGNMENT_NOTE_DATE,
						userInputData));

	}

	@Then("^User selects a judge or staff assignment  and verifies the information and notes that display on the page$")
	public void user_selects_a_judge_or_staff_assignment_and_verifies_the_information_and_notes_that_display_on_the_page() {
		AssignmentsPage assig = new AssignmentsPage();
		List<UserInputData> userInputData = null;

		assig.getAssignmentInformation(cmr_id, asignements, rnAssignment, info, userInputData);

	}

	@Then("^User verifies each assignment display the most recent date type$")
	public void user_verifies_each_assignment_display_the_most_recent_date_type_by_using_following_info_dbType() {
		List<UserInputData> userInputData = null;
		PendingTasksPage pending = new PendingTasksPage();
		pending.selectRandomCase();
		
		String cmr_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);
		String cmr_cs_caseid = CommonPages.getCaseID(PendingTasksPage.referral, userInputData);

		String cmr_cyv_code = CommonPages.cmr_cyv_code(PendingTasksPage.category, cmr_cs_caseid, userInputData)
				.trim();

		assig = new AssignmentsPage();

		assig.getRecentAssignmentDate(userInputData, PendingTasksPage.referral, cmr_ju_pe_id, cmr_cyv_code,PendingTasksPage.assignmentType);
	}

}
