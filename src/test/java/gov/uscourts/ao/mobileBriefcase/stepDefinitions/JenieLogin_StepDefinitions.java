package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class JenieLogin_StepDefinitions extends Base implements iOSCapabilities {

	JenieLoginPage logPage;

	@Given("^I am logged into Briefcase$")
	public void i_am_logged_into_Briefcase(List<UserInputData> table) {
		logPage = new JenieLoginPage();
		logPage.login(table);
	}
	
	@Given("^User is logged into Briefcase,   \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\"$")
	public void user_is_logged_into_Briefcase(String environment, String userName, String password, String courtId){
		logPage = new JenieLoginPage();
		logPage.login(environment,userName,password,courtId);
	}


	@Given("^user selects a \"([^\"]*)\"$")
	public void user_selects_a(String server) {
		logPage.getServer(server);
	}

	@Then("^I select a user$")
	public void i_select_a_user(List<UserInputData> table) {
		logPage = new JenieLoginPage();
		logPage.selectUser(table);
	}

	@Then("^User logs out from the Briefcase$")
	public void user_logs_out_from_the_Briefcase() throws Throwable {
		logPage = new JenieLoginPage();
		logPage.logout();
		safariInstance();
	}

}