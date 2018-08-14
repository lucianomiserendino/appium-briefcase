package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_AssignmentCategoriesPage;

public class AssignmentCategories_StepDefinitions {

	iOS_AssignmentCategoriesPage page =new iOS_AssignmentCategoriesPage();;

	@Given("^User selects staff attorney Benjamin Brown in the user's list$")
	public void user_selects_staff_attorney_Benjamin_Brown_in_the_user_s_list() {

		page.selectAnAttorney();
	}

	@Then("^User observes the assignment categories that display on the dashboard\\.$")
	public void user_observes_the_assignment_categories_that_display_on_the_dashboard() {
		page.getAssignmentCategories();

	}

	@Given("^User selects SA Benjamin Brown and assignment type \"([^\"]*)\"$")
	public void user_selects_SA_Benjamin_Brown_and_assignment_type(String assignmentType){

		page.selectAssignmentType();
	}

	@When("^User observes there are six referral categories listed\\.$")
	public void user_observes_there_are_six_referral_categories_listed() {
     page.osberveReferralCategories();
	}



}