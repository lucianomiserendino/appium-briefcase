package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SAs_ASSIGNMENT_CATEGORIES;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_StaffAttorneyPage;

public class StaffAttorney_StepDefinitions {
	iOS_StaffAttorneyPage page;

	@Given("^User verifies Data is displayed on the Dashboard, retrieves categories from \"([^\"]*)\"$")
	public void user_verifies_Data_is_displayed_on_the_Dashboard_retrieves_categories_from(String dbType) {
		page = new iOS_StaffAttorneyPage();
		page.verifyDataOnTheDashboard(dbType, SAs_ASSIGNMENT_CATEGORIES);
	}

	@Given("^User selects assignment type \"([^\"]*)\"$")
	public void user_selects_assignment_type(String assignmentType) {
		page = new iOS_StaffAttorneyPage();
		page.selectAssignmentType(assignmentType);
	}

	@When("^User observes there are six referral categories listed\\.$")
	public void user_observes_there_are_six_referral_categories_listed() {
		page.osberveReferralCategories();

	}

	@Then("^User selects category \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_selects_category_and(String category, String caseNum) {
		page = new iOS_StaffAttorneyPage();
		page.tapOnReferralCategory(category, caseNum);
	}

	@Then("^After selecting \"([^\"]*)\" , user verifies the document categories and the number of docs displayed for each category matches the number of docs in the DB \"([^\"]*)\"$")
	public void after_selecting_user_verifies_the_document_categories_and_the_number_of_docs_displayed_for_each_category_matches_the_number_of_docs_in_the_DB(
			String category, String dbType) {
		page.getCategories(valueOf(dbType), category);
	}

}
