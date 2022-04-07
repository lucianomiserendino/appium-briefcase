package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.DBDocketingDPFPage;
import gov.uscourts.ao.mobileBriefcase.model.ElListText;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class DBDocketingDPF_StepDefinitions {
	DBDocketingDPFPage page;

	@Then("^User selecs action, enters a comment in the editable field, submits and verifies Db \"([^\"]*)\" is updated correctly, \\( use \"([^\"]*)\"  and \"([^\"]*)\" \\)$")
	public void user_selecs_action_enters_a_comment_in_the_editable_field_submits_and_verifies_Db_is_updated_correctly_use_and(
			String dbType, String caseNume, String peID, List<ElListText> table) {
		page = new DBDocketingDPFPage();
		List<UserInputData> userInputData = null;
		int values = table.size();
		for (int i = 0; i < values; i++) {
			page.selectActioName(table, i, caseNume, peID, userInputData);
		}

	}
}
