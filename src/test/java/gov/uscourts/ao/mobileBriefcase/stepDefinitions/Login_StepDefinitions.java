package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.Base.changeWindow;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_LoginPage;
import gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities;

public class Login_StepDefinitions implements iOSCapabilities {

	iOS_LoginPage logPage;

	@Given("^User Navigates to environment$")
	public void user_Navigates_to_environment() {
		logPage = new iOS_LoginPage();
		logPage.selectEnvironment();
		changeWindow("WEBVIEW");
	}

	@When("^User enters Credentials to Login$")
	public void and_User_enters_Crdenetials_to_Login(DataTable userCredentials) {
		logPage.sendCredentials(userCredentials);

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

	@Given("^user selects a server \"([^\"]*)\"$")
	public void user_selects_a_server(String server) {
		logPage.getCMKA(server);
	}

}