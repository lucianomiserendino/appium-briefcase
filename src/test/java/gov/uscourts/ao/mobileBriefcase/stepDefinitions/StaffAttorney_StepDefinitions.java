package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SAs_ASSIGNMENT_CATEGORIES;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.StaffAttorneyReferralPage;

public class StaffAttorney_StepDefinitions {
	StaffAttorneyReferralPage page;

	@Given("^User verifies Data is displayed on the Dashboard, retrieves categories from \"([^\"]*)\" ,'RA_PE_ID' : \"([^\"]*)\"$")
	public void user_verifies_Data_is_displayed_on_the_Dashboard_retrieves_categories_from_RA_PE_ID(String dbType,
			String ra_pe_id) {
		page = new StaffAttorneyReferralPage();
		page.verifyDataOnTheDashboard(dbType, SAs_ASSIGNMENT_CATEGORIES, ra_pe_id);
	}

	@Given("^User selects assignment type \"([^\"]*)\"$")
	public void user_selects_assignment_type(String assignmentType) {
		page = new StaffAttorneyReferralPage();
		page.selectAssignmentType(assignmentType);
	}

	@When("^User observes there are six referral categories listed on UI and DB \"([^\"]*)\", smr_assign_pe_id: \"([^\"]*)\"$")
	public void user_observes_there_are_six_referral_categories_listed_on_UI_and_DB_smr_assign_pe_id(String dbType,
			String smr_assign_pe_id) {
		page.osberveReferralCategories(valueOf(dbType), smr_assign_pe_id);
	}

	@Then("^User selects category \"([^\"]*)\" and \"([^\"]*)\" ,SMR_ASSIGN_PE_ID : \"([^\"]*)\"$")
	public void user_selects_category_and_SMR_ASSIGN_PE_ID(String category, String caseNum, String smr_assign_pe_id) {
		page = new StaffAttorneyReferralPage();
		page.tapOnReferralCategory(category, caseNum, smr_assign_pe_id);
	}



	@Then("^After selecting \"([^\"]*)\" , user verifies the document categories and the number of docs displayed for each category matches the number of docs in the DB \"([^\"]*)\"\\. smr_assign_pe_id: \"([^\"]*)\"$")
	public void after_selecting_user_verifies_the_document_categories_and_the_number_of_docs_displayed_for_each_category_matches_the_number_of_docs_in_the_DB_smr_assign_pe_id(
			String category, String dbType, String smr_assign_pe_id)  {
		page.getCategories(valueOf(dbType), category,smr_assign_pe_id);
	}

}
