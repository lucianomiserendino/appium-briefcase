package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SAs_ASSIGNMENT_CATEGORIES;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.StaffAttorneyReferralPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class StaffAttorney_StepDefinitions {
	StaffAttorneyReferralPage page;

	@Given("^User verifies Data is displayed on the Dashboard, retrieves categories from db ,'RA_PE_ID' : \"([^\"]*)\"$")
	public void user_verifies_Data_is_displayed_on_the_Dashboard_retrieves_categories_from_db_RA_PE_ID(String ra_pe_id,
			List<UserInputData> table) {
		page = new StaffAttorneyReferralPage();
		List<UserInputData> userInputData = null;
		page.verifyDataOnTheDashboard(SAs_ASSIGNMENT_CATEGORIES, ra_pe_id, userInputData);
	}

	@Given("^User selects assignment type \"([^\"]*)\"$")
	public void user_selects_assignment_type(String assignmentType) {
		page = new StaffAttorneyReferralPage();
		page.selectAssignmentType(assignmentType);
	}

	@When("^User observes there are six referral categories listed on UI and DB, use smr_assign_pe_id: \"([^\"]*)\"$")
	public void user_observes_there_are_six_referral_categories_listed_on_UI_and_DB_use_smr_assign_pe_id(
			String smr_assign_pe_id, List<UserInputData> table) {
		page.osberveReferralCategories(smr_assign_pe_id, table);
	}

	@Then("^User selects category \"([^\"]*)\" and \"([^\"]*)\" ,SMR_ASSIGN_PE_ID : \"([^\"]*)\"$")
	public void user_selects_category_and_SMR_ASSIGN_PE_ID(String category, String caseNum, String smr_assign_pe_id) {
		page = new StaffAttorneyReferralPage();
		page.tapOnReferralCategory(category, caseNum, smr_assign_pe_id);
	}

	@Then("^After selecting \"([^\"]*)\" , user verifies the document categories and the number of docs displayed for each category matches the number of docs in the DB \"([^\"]*)\"\\. smr_assign_pe_id: \"([^\"]*)\"$")
	public void after_selecting_user_verifies_the_document_categories_and_the_number_of_docs_displayed_for_each_category_matches_the_number_of_docs_in_the_DB_smr_assign_pe_id(
			String category, String dbType, String smr_assign_pe_id) {
		page.getCategories(valueOf(dbType), category, smr_assign_pe_id);
	}

//@Given("^I tap on referral category, the categories can be found using 'RA_PE_ID' : \"([^\"]*)\"$")
//public void i_tap_on_referral_category_the_categories_can_be_found_using_RA_PE_ID(String ra_pe_id,List<UserInputData> table) {
//	page = new StaffAttorneyReferralPage();
////page.selectRandomCase(ra_pe_id,table);
//	
//}
}
