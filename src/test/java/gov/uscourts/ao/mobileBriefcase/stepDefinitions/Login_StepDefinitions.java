package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.Base.changeWindow;

import java.net.MalformedURLException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.LoginPage;
import gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities;

public class Login_StepDefinitions implements iOSCapabilities {

	LoginPage logPage;

	@Given("^User Navigates to Sever$")
	public void user_Navigate_to_Sever() throws MalformedURLException {
		logPage = new LoginPage();
		logPage.selectEnvironment();

		changeWindow("WEBVIEW");

	}

	@When("^User enters Credentials to Login$")
	public void and_User_enters_Crdenetials_to_Login() {
		logPage.sendCredentials();

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

	@Then("^After user navigates to Appellate DC Development - CMKA - dev$")
	public void after_user_navigates_to_Appellate_DC_Development_CMKA_dev() {
		logPage.getCMKA();
		

	}


}
