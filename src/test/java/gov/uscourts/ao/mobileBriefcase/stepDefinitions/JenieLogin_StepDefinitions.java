package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities;

public class JenieLogin_StepDefinitions extends Base implements iOSCapabilities {

	JenieLoginPage logPage;

	@Given("^User Navigates to  \"([^\"]*)\" environment$")
	public void user_Navigates_to_environment(String env) {
		logPage = new JenieLoginPage();
		logPage.selectEnvironment(env);
		changeWindow("WEBVIEW");

	}

	@When("^User enters Credentials to Login \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enters_Credentials_to_Login_and(String username, String password) {
		logPage.sendCredentials(username, password);
	}

	@When("^User clicks on Send Key to Device$")
	public void user_clicks_on_Send_Key_to_Device() {
		logPage.sedKeyButton();

	}

	@Then("^User navigates to MobileBrifcase App$")
	public void user_navigates_to_MobileBrifcase_App() {
		changeWindow("NATIVE");
		logPage.open();

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