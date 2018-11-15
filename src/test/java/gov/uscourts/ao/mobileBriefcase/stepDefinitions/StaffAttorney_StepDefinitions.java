package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ASSIGNMENT_CATEGORIES;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_StaffAttorneyPage;

public class StaffAttorney_StepDefinitions {
	iOS_StaffAttorneyPage page;

	String refCategory = "";

	@Then("^User verifies Data is displayed on the Dashboard, retriev categories from \"([^\"]*)\"$")
	public void user_verifies_Data_is_displayed_on_the_Dashboard_retriev_categories_from(String dbType) {
		page = new iOS_StaffAttorneyPage();
		page.verifyDataOnTheDashboard(dbType, ASSIGNMENT_CATEGORIES);
	}

	@Given("^User selects assignment type \"([^\"]*)\"$")
	public void user_selects_assignment_type(String assignmentType) {
		page.selectAssignmentType(assignmentType);
	}

	@When("^User observes there are six referral categories listed\\.$")
	public void user_observes_there_are_six_referral_categories_listed() {
		page.osberveReferralCategories();

	}

	@Then("^User  selects  \"([^\"]*)\" and  \"([^\"]*)\"$")
	public void user_selects_and(String category, String caseNum) {
		this.refCategory = category;
		page.tap(caseNum, refCategory);
		System.out.println(refCategory+"***************************");
	}

	@Then("^User verifies the document categories and the number of docs displayed for each category after selecting \"([^\"]*)\"  and \"([^\"]*)\"$")
	public void user_verifies_the_document_categories_and_the_number_of_docs_displayed_for_each_category_after_selecting_and(
			String category, String dbType) {
		page.getCategories(valueOf(dbType), category);
		
	}
	

}
