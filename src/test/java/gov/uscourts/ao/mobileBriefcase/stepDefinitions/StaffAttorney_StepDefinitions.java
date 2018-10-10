package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_CATEGORIES;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_StaffAttorneyPage;

public class StaffAttorney_StepDefinitions {
	iOS_StaffAttorneyPage page;

	@Then("^User verifies Data is displayed on the Dashboard, retriev categories from \"([^\"]*)\"$")
	public void user_verifies_Data_is_displayed_on_the_Dashboard_retriev_categories_from(String dbType) {
		page = new iOS_StaffAttorneyPage();
		page.verifyDataOnTheDashboard(dbType, ASSIGNMENT_CATEGORIES);
	}

	@Given("^User selects assignment type \"([^\"]*)\"$")
	public void user_selects_assignment_type(String assignmentType) {
		page.selectAssignmentType();
	}

	@When("^User observes there are six referral categories listed\\.$")
	public void user_observes_there_are_six_referral_categories_listed() {
		page.osberveReferralCategories();
	}

}
