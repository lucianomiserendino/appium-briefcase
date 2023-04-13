package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.iOSCapabilities;
import gov.uscourts.ao.mobileBriefcase.page.common.Base.Driver;

public class JenieLogin_StepDefinitions extends Base implements iOSCapabilities {

	JenieLoginPage logPage;

	@Given("^I am logged into Briefcase$")
	public void i_am_logged_into_Briefcase(List<UserInputData> table) {
		logPage = new JenieLoginPage();
		logPage.login(table);

	}

	@Then("^I select a user$")
	public void i_select_a_user(List<UserInputData> table) {
		logPage = new JenieLoginPage();
		logPage.selectUser(table);
	}

	@Then("^User logs out from the Briefcase$")
	public void user_logs_out_from_the_Briefcase() {
	//	logPage = new JenieLoginPage();
		JenieLoginPage.logout();
		safariInstance();
	}

	@Then("^User closes and reopens the app$")
	public void user_closes_and_reopens_the_app() {
		logPage = new JenieLoginPage();
		logPage.reopenTheApp();

	}
}