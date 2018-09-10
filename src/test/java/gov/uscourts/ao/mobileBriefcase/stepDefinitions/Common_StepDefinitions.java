package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages;

public class Common_StepDefinitions {

	iOS_CommonPages page;

	@When("^User selects a Judge,  \"([^\"]*)\" and  \"([^\"]*)\"$")
	public void user_selects_a_Judge_and(String category, String caseNum) {
		page = new iOS_CommonPages();
		page.selectCategoryAndCase(category, caseNum);
	}

	@Then("^User  selects action using dbType \"([^\"]*)\" and  \"([^\"]*)\"  and verifies the name of the action displays in the dark blue banner$")
	public void user_selects_action_using_dbType_and_and_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner(
			String dbType, String elID) {
		page = new iOS_CommonPages();
		page.selectAction(DBType.valueOf(dbType), elID);
	}

}
