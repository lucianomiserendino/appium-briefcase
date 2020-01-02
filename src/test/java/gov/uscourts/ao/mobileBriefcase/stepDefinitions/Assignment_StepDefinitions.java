package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_INFO;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getRandomNumberInRange;

import java.util.Arrays;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.AssignmentsPage;
import gov.uscourts.ao.mobileBriefcase.Pages.AssignmentsPage.AssignmentInfo;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;

public class Assignment_StepDefinitions {
	AssignmentsPage assig;

	static String cmr_id = "";
	static List<String> asignements;
	static List<String> info;
	static int rnAssignment = 0;
	static DBType db;

	@Then("^User observes a collapsible panel entitled \"([^\"]*)\" displays$")
	public void user_observes_a_collapsible_panel_entitled_displays(String assignment) {
		getPanel(Panel.valueOf(assignment));
	}

	@Then("^User verifies the staff assignments associated with the referral by using \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" ,$")
	public void user_verifies_the_staff_assignments_associated_with_the_referral_by_using(String dbType, String caseId,
			String peId, String cmr_cyv_code) {
		assig = new AssignmentsPage();
		assig.getAssignmentsLinkedToReferral(DBType.valueOf(dbType), caseId, peId, cmr_cyv_code);

	}

	@Given("^User gets judge's/staff assignment's info from DataBase  by using \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_gets_judge_s_staff_assignment_s_info_from_DataBase_by_using(String dbType, String caseId,
			String peId, String cmr_cyv_code) {
		assig = new AssignmentsPage();
		cmr_id = assig.getCMR_ID(DBType.valueOf(dbType), caseId, peId, cmr_cyv_code);

		asignements = execute(DBType.valueOf(dbType), replace(ASSIGNMENT_INFO, "CMR_ID", cmr_id), 2);

		rnAssignment = getRandomNumberInRange(1, asignements.size() - 1);

		db = DBType.valueOf(dbType);
		assig = new AssignmentsPage();
		
	
		
		info = Arrays.asList(
				assig.getAssignmentInfo(db, cmr_id, asignements, rnAssignment,
						AssignmentInfo.NAME_OF_THE_ASSIGNEE_AND_LATEST_ASSIGNMENT_DATE),
				assig.getAssignmentInfo(db, cmr_id, asignements, rnAssignment,
						AssignmentInfo.ASSIGNMENT_TYPE_AND_RELIEF),
				assig.getAssignmentInfo(db, cmr_id, asignements, rnAssignment,
						AssignmentInfo.LATEST_ASSIGNED_ASSIGNMENT_DUE_DATES),
				assig.getAssignmentInfo(db, cmr_id, asignements, rnAssignment, AssignmentInfo.ASSIGNMENT_NOTE_DATE));
	}

	@Then("^User selects a judge or staff assignment  and verifies the information and notes that display on the page$")
	public void user_selects_a_judge_or_staff_assignment_and_verifies_the_information_and_notes_that_display_on_the_page() {
		assig = new AssignmentsPage();
		assig.getAssignmentInformation(db, cmr_id, asignements, rnAssignment, info);

	}

	@Then("^User verifies each assignment display the most recent date type by using following info: dbType \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void user_verifies_each_assignment_display_the_most_recent_date_type_by_using_following_info_dbType(
			String dbType, String caseNumber, String peId, String cmr_cyv_code, String pr_last_name,
			String pr_first_name) {
		assig = new AssignmentsPage();
		assig.getRecentAssignmentDate(DBType.valueOf(dbType), caseNumber, peId, cmr_cyv_code, pr_last_name,
				pr_first_name);
	}

}
