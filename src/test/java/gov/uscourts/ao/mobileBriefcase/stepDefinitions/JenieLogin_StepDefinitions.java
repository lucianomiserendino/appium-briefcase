package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;
import java.util.Map;

import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.iOSCapabilities;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.page.common.Base.Driver;

public class JenieLogin_StepDefinitions extends Base implements iOSCapabilities {

	JenieLoginPage logPage;
	
	 @DataTableType
	    public UserInputData userInputDataEntry(Map<String, String> entry) {
	        UserInputData data = new UserInputData();

	        data.setUserType(entry.get("userType"));
	        data.setPersonrole(entry.get("personrole"));
	        data.setJud(entry.get("jud"));
	        data.setUser(entry.get("user"));


	        return data;
	    }


	@Given("^I am logged into Briefcase$")
	public void i_am_logged_into_Briefcase(List<UserInputData> table) {
		logPage = new JenieLoginPage();
		logPage.login(table);

	}

	@Then("I select a user")
	public void i_select_a_user(List<UserInputData> users) {
	    logPage = new JenieLoginPage();
	    for (UserInputData user : users) {
	        logPage.selectUser(user);
	    }
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