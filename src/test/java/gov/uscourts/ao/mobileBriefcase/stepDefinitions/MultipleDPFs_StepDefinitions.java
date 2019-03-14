package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.MultipleDPFs;

public class MultipleDPFs_StepDefinitions {
	MultipleDPFs page;
	CommonPages page1;

	@Then("^create a staff assignment  by using \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void create_a_staff_assignment_by_using(String dbType, String dpfName, String elId, String cha_ju_pe_id,
			String cmr_cyv_code, String cmr_cs_caseid) {

		page1 = new CommonPages();
		page1.verifyElementIsDisplayed("Assignments");
		page1.verifyElementIsDisplayed("New Staff Assignment");
		contains("New Staff Assignment").click();
		page1.verifyElementIsDisplayed("Create Assignment");
		page = new MultipleDPFs();
		page.createAnAssignment(valueOf(dbType), dpfName, elId, cha_ju_pe_id, cmr_cyv_code, cmr_cs_caseid);
	}

	@Then("^user adds a Note in the comment field$")
	public void user_adds_a_Note_in_the_comment_field() {
		page = new MultipleDPFs();
		page.addANote();
	}

}
