package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.DashboardPage;
import gov.uscourts.ao.mobileBriefcase.Pages.PendingTasksPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;


public class PendingTasks_StepDefinitions {

	PendingTasksPage pending;
	DashboardPage page;
	String folder;


	@When("^User select a sub folder$")
	public void user_select_a_sub_folder() {
		pending = new PendingTasksPage();
		folder = pending.processSubFolder(0);
		pending.selectAssignmentType(folder);

	}

	@Then("^selects a random pending tasks case$")
	public void selects_a_random_pending_tasks_case() {
		pending = new PendingTasksPage();
		pending.clickOnCase();
	}

}
