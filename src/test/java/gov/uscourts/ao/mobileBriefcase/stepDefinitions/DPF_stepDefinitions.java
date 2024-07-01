package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.DPFs;
import gov.uscourts.ao.mobileBriefcase.page.common.DPFs.DPF;

public class DPF_stepDefinitions {

	DPFs page;
	CommonPages page2 = new CommonPages();

	String caseNum = DocumentPage.caseNum;
	String refCategory = DocumentPage.category;

	public static String actionName = "";

	@Then("^User selects action$")
	public void user_selects_action(DataTable table) {
		page = new DPFs();
		List<UserInputData> userInputData = null;
		List<List<String>> data = table.raw();
		String dpf = data.get(1).get(0);

		actionName = page.getChmAssign(DPF.valueOf(dpf), caseNum,userInputData);

		page2 = new CommonPages();
		page2.selectBriefcaseAction("Actions", actionName);

	}

	@Then("^Submit action that has multiple dpfs$")
	public void submit_action_that_has_multiple_dpfs_and_verify_data_is_saved() {
		List<UserInputData> userInputData = null;

		String peId = DocumentPage.get_pe_id("jud", userInputData);
		String cmr_cs_caseid = DocumentPage.cs_caseid;

		String cmr_cyv_code = DocumentPage.cmr_cyv_code;

		page = new DPFs();

		page.executeMultipleDPFs(page.foundStrings, actionName, caseNum, refCategory, peId, cmr_cyv_code,
				userInputData);

	}

	@Then("^verifies data is saving when executing multiple DPFs in the same action$")
	public void verifies_data_is_saving_when_executing_multiple_DPFs_in_the_same_action() {
		page = new DPFs();
		page.dataIsSaved(actionName);
	}

}
