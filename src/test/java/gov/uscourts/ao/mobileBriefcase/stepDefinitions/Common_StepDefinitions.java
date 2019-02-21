package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Categories;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;

public class Common_StepDefinitions {
	CommonPages page;

	@Then("^User selects \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_selects_and(String category, String caseNumber) {
		page = new CommonPages();
		page.getCategoryWithCase(category, caseNumber);
	}

	@Then("^User verifies \"([^\"]*)\" panel is displayed and expands the  panel$")
	public void user_verifies_panel_is_displayed_and_expands_the_panel(String panel) {
		page.getPanel(Panel.valueOf(panel));
	}

	@Then("^User  selects action using dbType \"([^\"]*)\" and  \"([^\"]*)\"  and verifies the name of the action displays in the dark blue banner$")
	public void user_selects_action_using_dbType_and_and_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner(
			String dbType, String elID) {
		page.selectAction(valueOf(dbType), "Actions", elID);
	}

	@When("^User selects a  \"([^\"]*)\"$")
	public void user_selects_a(String category) {
		page = new CommonPages();
		page.selectReferral(category, Categories);
	}

	@Given("^On \"([^\"]*)\" user sets the si_value of the site var to \"([^\"]*)\"  and si_code \"([^\"]*)\"$")
	public void on_user_sets_the_si_value_of_the_site_var_to_and_si_code(String dbType, String si_value,
			String si_code) {
		page = new CommonPages();
		page.setValue(valueOf(dbType), si_value, si_code);
	}
}
