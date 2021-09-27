package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage;
import gov.uscourts.ao.mobileBriefcase.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.common.SystemPropertySetup.Variables;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class CaseQuery_StepDefinitions {

	CaseQueryPage page;
	
	
	@Then("^User taps on magnifying glass icon and searches for a case and  verifies the result if not empty$")
	public void user_taps_on_magnifying_glass_icon_and_searches_for_a_case_and_verifies_the_result_if_not_empty(List<UserInputData> userInputData) {
		page = new CaseQueryPage();
		String caseNumber = SystemPropertySetup.getVariable(Variables.CASE_NUMBER, userInputData);
		page.searchForACase(caseNumber);
	}

}
