package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_DBDocketingDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_DBDocketingDPFPage.mbrNotes;

public class DBDocketingDPF_StepDefinitions {
	iOS_DBDocketingDPFPage page;

	@Given("^Select Judge Colloton , Screening Panels \"([^\"]*)\"$")
	public void select_Judge_Colloton_Screening_Panels(String caseNumber) {
		page = new iOS_DBDocketingDPFPage();
		page.selectAJudje();
		page.selectCase(caseNumber);
	}

	@When("^User  selects an \"([^\"]*)\"$")
	public void user_selects_an(String actionsPanel) {
		page.selectActionPanel(actionsPanel);
	}

	@Then("^User selects \"([^\"]*)\",user enters a comment in the editable field and submits \\. However dm_acc_crt = 'y', dm_acc_ctlink = 'n', dm_acc_spec = 'n'\\. No doc_group or doc_user records are created\\.$")
	public void user_selects_user_enters_a_comment_in_the_editable_field_and_submits_However_dm_acc_crt_y_dm_acc_ctlink_n_dm_acc_spec_n_No_doc_group_or_doc_user_records_are_created(
			String action) {
		page.selectAction(action, mbrNotes.COURT_USERS);

	}

	@Then("^User selects \"([^\"]*)\"user enters a comment in the editable field and submits \\. However the dm_acc_crt = 'n', dm_acc_ctlink = 'y', dm_acc_spec = 'n'\\. No doc_group or doc_user records are created$")
	public void user_selects_user_enters_a_comment_in_the_editable_field_and_submits_However_the_dm_acc_crt_n_dm_acc_ctlink_y_dm_acc_spec_n_No_doc_group_or_doc_user_records_are_created(
			String action) {
		page.selectAction(action, mbrNotes.COURT_USERS_LINKED_TO_CASE);
	}

	@Then("^User selects \"([^\"]*)\",user enters a comment in the editable field and submits \\. However  the dm_acc_crt = 'n', dm_acc_ctlink = 'n', dm_acc_spec = 'y'\\.$")
	public void user_selects_user_enters_a_comment_in_the_editable_field_and_submits_However_the_dm_acc_crt_n_dm_acc_ctlink_n_dm_acc_spec_y3(
			String action) {
		page.selectAction(action, mbrNotes.PANEL_JUDGES_ONLY);
	}

	@Then("^user verifies a doc_user record is  created for each judge on the panel$")
	public void user_verifies_a_doc_user_record_is_created_for_each_judge_on_the_panel() {
		page.verifyDOC_USER();
	}

	@Then("^User selects \"([^\"]*)\",user enters a comment in the editable field and submits \\. However the dm_acc_crt = 'n', dm_acc_ctlink = 'n', dm_acc_spec = 'y'\\.$")
	public void user_selects_user_enters_a_comment_in_the_editable_field_and_submits_However_the_dm_acc_crt_n_dm_acc_ctlink_n_dm_acc_spec_y(
			String action) {
		page.selectAction(action, mbrNotes.PANEL_JUDGES_AND_USERs_CHAMBERS);
	}

	@Then("^user verifies  a doc_user record is   created for each judge on the panel that's not the logged in judge\\.$")
	public void user_verifies_a_doc_user_record_is_created_for_each_judge_on_the_panel_that_s_not_the_logged_in_judge() {

	}

	@Then("^user verifies  a doc_group record will be created for the logged in judge's chambers group$")
	public void user_verifies_a_doc_group_record_will_be_created_for_the_logged_in_judge_s_chambers_group() {
	
	}

	@Then("^user verifies  a doc_user record will not be created$")
	public void user_verifies_a_doc_user_record_will_not_be_created() {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^user verifies  a  doc_group record will be created for the panel members chambers$")
	public void user_verifies_a_doc_group_record_will_be_created_for_the_panel_members_chambers() {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^User selects  \"([^\"]*)\",user enters a comment in the editable field and submits \\. However  the dm_acc_crt = 'n', dm_acc_ctlink = 'n', dm_acc_spec = 'y'\\.$")
	public void user_selects_user_enters_a_comment_in_the_editable_field_and_submits_However_the_dm_acc_crt_n_dm_acc_ctlink_n_dm_acc_spec_y1(
			String arg1) {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^user verifies  a doc_group record will be created for the logged in judge's chambers only$")
	public void user_verifies_a_doc_group_record_will_be_created_for_the_logged_in_judge_s_chambers_only() {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^User selects  \"([^\"]*)\",user enters a comment in the editable field and submits \\. However the dm_acc_crt = 'n',  dm_acc_ctlink = 'n', dm_acc_spec = 'y'\\.$")
	public void user_selects_user_enters_a_comment_in_the_editable_field_and_submits_However_the_dm_acc_crt_n_dm_acc_ctlink_n_dm_acc_spec_y2(
			String arg1) {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^user verifies  a doc_user record will not be created for the pr_prids following the pipe in the Note Available personroles -Default person IDs$")
	public void user_verifies_a_doc_user_record_will_not_be_created_for_the_pr_prids_following_the_pipe_in_the_Note_Available_personroles_Default_person_IDs() {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^user verifies  a doc_group record will be created for the groups ids following the pipe in the Note Available - Default Group IDs$")
	public void user_verifies_a_doc_group_record_will_be_created_for_the_groups_ids_following_the_pipe_in_the_Note_Available_Default_Group_IDs() {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

}
