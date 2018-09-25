package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_DBDocketingDPFPage;
import gov.uscourts.ao.mobileBriefcase.models.ElListText;

public class DBDocketingDPF_StepDefinitions {
	iOS_DBDocketingDPFPage page;

	@Then("^User selecs action, enters a comment in the editable field, submits and verifies Db \"([^\"]*)\" is updated correctly$")
	public void user_selecs_action_enters_a_comment_in_the_editable_field_submits_and_verifies_Db_is_updated_correctly(String dbType,List<ElListText> table)  {

		page = new iOS_DBDocketingDPFPage();
		int values=6;
		for (int i = 0; i < values; i++) {
			page.selectAction(table, i,dbType);
		}

		
       
	}
}
