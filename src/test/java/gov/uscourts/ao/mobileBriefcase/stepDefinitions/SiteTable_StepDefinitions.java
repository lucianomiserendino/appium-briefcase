package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.Pages.Site_TablePage;
import io.cucumber.java.en.Then;

public class SiteTable_StepDefinitions {
	Site_TablePage page;

	@Then("^user observes all reliefs display under Vote Information, use DBType \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_observes_all_reliefs_display_under_Vote_Information_use_DBType(String dbType, String id,
			String caseNum, String peId, String cmr_cyv_code) {
		page = new Site_TablePage();
		page.verifyRerilefIsDisplayed(DBType.valueOf(dbType), id, caseNum, peId, cmr_cyv_code);

	}

}
