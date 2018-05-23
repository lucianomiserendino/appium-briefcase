package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.text.ParseException;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.AssignmentsPanelPage;

public class AssignmentsPanel_StepDefinitions {

	AssignmentsPanelPage page;

	@Given("^User selects Judge Colloton >> Motions/Petitions >> case \"([^\"]*)\"$")
	public void user_selects_Judge_Colloton_Motions_Petitions_case(String caseNum) {
		page = new AssignmentsPanelPage();
		page.getCase(caseNum);

	}

	@When("^User  observes a collapsible panel entitled \"([^\"]*)\" displays\\.$")
	public void user_observes_a_collapsible_panel_entitled_displays(String assignmentPanel) {
		page.verifyAssignmentIsDisplayed(assignmentPanel);

	}

	@Then("^User observes there is an assignment for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_observes_there_is_an_assignment_for_and(String assignmentForKyle, String assignmentForEssley)
			throws ParseException {
		page.getAssignmentLinkedtoTheReferral(assignmentForKyle, assignmentForEssley);

	}

	@And("^User observes there is an assignment for \"([^\"]*)\"$")
	public void user_observes_there_is_an_assignment_for(String assignmentForCourtney) {
		page.getAssignmentLinkedtoCase(assignmentForCourtney);
		page.navigateBack();

	}

}