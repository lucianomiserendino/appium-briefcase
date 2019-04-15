package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Page;

public class CreateStaffAssignments_StepDefinitions extends Base {
	static CommonPages page;
	static chmAssignDPFPage page1;
	String staffMember = "";

	@Then("^user creates a new assignment, checks the back-end, edits existing assignment and checks the db  by using \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"\\);$")
	public void user_creates_a_new_assignment_checks_the_back_end_edits_existing_assignment_and_checks_the_db_by_using(
			String dbType, String dpfName, String actionElID, String cha_ju_pe_id, String cmr_cyv_code,
			String cmr_cs_caseid, String caseNum) {
		page = new CommonPages();
		page.verifyElementIsDisplayed("Assignments");
		page.verifyElementIsDisplayed("New Staff Assignment");
		page.verifyElementIsDisplayed("Submit");
		contains("New Staff Assignment").click();
		page.verifyElementIsDisplayed("Create Assignment");
		page1 = new chmAssignDPFPage();
		page1.createNewStaffAssignment(valueOf(dbType), dpfName, actionElID, cha_ju_pe_id, cmr_cyv_code, cmr_cs_caseid,
				caseNum);
	}

	public static void main(String[] args) {

		Base.getInstance(Driver.IOS);
		Page.sleep(3000);
		page = new CommonPages();

		page.getCategoryWithCase("PETITIONS_FOR_REHEARING", "15-2594");
		page.selectAction(valueOf("CMKA"), "Actions", "3155");
		page.verifyElementIsDisplayed("Assignments");
		page.verifyElementIsDisplayed("New Staff Assignment");
		page.verifyElementIsDisplayed("Submit");
		contains("New Staff Assignment").click();
		page.verifyElementIsDisplayed("Create Assignment");
		page1 = new chmAssignDPFPage();
		page1.createNewStaffAssignment(valueOf("CMKA"), "chmAssign", "3155", "32", "prhr", "82226", "15-3314");
		// page.verifyNoteText(valueOf("CMKA"), "35683", vote, page.getTodaysDate());
	}

}
