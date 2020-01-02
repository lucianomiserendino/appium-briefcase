package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities;
import gov.uscourts.ao.mobileBriefcase.models.UserInputData;

public class JenieLogin_StepDefinitions extends Base implements iOSCapabilities {

	JenieLoginPage logPage;

	@Given("^user is logged into Briefcase$")
	public void user_is_logged_into_Briefcase(List<UserInputData> table) {
		logPage = new JenieLoginPage();
		logPage.login(table);
	}

	@Given("^user selects a \"([^\"]*)\"$")
	public void user_selects_a(String server) {
		logPage.getServer(server);
	}

	@Then("^User selects a userCategory \"([^\"]*)\" and  name \"([^\"]*)\"$")
	public void user_selects_a_userCategory_and_name(String availableJudges, String user) {
		logPage.selectUser(availableJudges, user);
	}

}