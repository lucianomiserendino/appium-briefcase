package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import gov.uscourts.ao.mobileBriefcase.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class Common_StepDefinitions {
	CommonPages page;

	@Then("^User selects \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_selects_and(String category, String caseNumber) {
		// JenieLoginPage logPage = new JenieLoginPage();
		page = new CommonPages();
		page.getCategoryWithCase(category, caseNumber);
	}

	@Then("^User  selects action using \"([^\"]*)\"  and verifies the name of the action displays in the dark blue banner$")
	public void user_selects_action_using_and_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner(
			String elID, List<UserInputData> userInputData) {
		page = new CommonPages();
		page.selectAction("Actions", elID, userInputData);
	}

	@When("^User selects a  \"([^\"]*)\"$")
	public void user_selects_a(String category) {
		page = new CommonPages();
		page.selectReferral("//XCUIElementTypeOther[@name='Categories']" + containsElement(category));
	}

	// @Given("^User sets the \"([^\"]*)\" site var to \"([^\"]*)\" on
	// \"([^\"]*)\"$")
	// public void user_sets_the_site_var_to_on(String si_code, String si_value,
	// List<UserInputData> userInputData) {
	// // page = new CommonPages();
	// page.setValue( si_value, si_code,userInputData);
	//
	// }

	@Given("^User sets the \"([^\"]*)\" site var to \"([^\"]*)\"$")
	public void user_sets_the_site_var_to(String si_code, String si_value, List<UserInputData> userInputData) {
		// page.setValue(si_value, si_code, userInputData);
		String environment = SystemPropertySetup.getEnvironment(userInputData);
		String court = SystemPropertySetup.getCourtId(userInputData);
		String user = SystemPropertySetup.getUserName(userInputData);
		String pwd = SystemPropertySetup.getPassword(userInputData);
		Base.safariInstance();
		String env="";
		if (environment.equals("Integration")) {
			env="isso";
		}else if (environment.equals("Testing")) {
			env="tsso";
		}else {
			env="ssso";
		}
		Base.getUrl(court.toLowerCase(),env);
		page = new CommonPages();
		page.sendCredentials(user, pwd);
		page.updateSi_value(si_code, si_value);
		Base.safariInstance();
		Base.closeIOSDriver();
	}

	@Then("^User verifies \"([^\"]*)\" panel is displayed and expands the  panel$")
	public void user_verifies_panel_is_displayed_and_expands_the_panel(String panel) {
		// page.getGroupIcons();
		page.getPanel(Panel.valueOf(panel));

	}

	@Then("^User logs out of Briefcase$")
	public void user_logs_out_of_Briefcase() {

		JenieLoginPage.logout();
		Base.safariInstance();
		Page.sleep(10000);
	}

	@Given("^Verify DB Informix Connection Is Established$")
	public void verify_DB_Informix_Connection_Is_Established(List<UserInputData> userInputData) {
		assertTrue("Connection Using The New Connection Pool Failed", DBUtilities.getDBConnection(userInputData));

	}

}
