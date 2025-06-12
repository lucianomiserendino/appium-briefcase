package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;


import gov.uscourts.ao.mobileBriefcase.Pages.DashboardPage;
import gov.uscourts.ao.mobileBriefcase.Pages.PendingTasksPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


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
