package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.ReplaceAnnotatedDocuementsPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class ReplaceAnnotatedDocuements_StepDefinitions {

	ReplaceAnnotatedDocuementsPage page;

	@Then("^User verifies that the annotated/replaced documents are displayed as expected$")
	public void user_verifies_that_the_annotated_replaced_documents_are_displayed_as_expected() {
		page = new ReplaceAnnotatedDocuementsPage();
		List<UserInputData> userInputData = null;
		page.isDisplayed(userInputData);
	}

}
