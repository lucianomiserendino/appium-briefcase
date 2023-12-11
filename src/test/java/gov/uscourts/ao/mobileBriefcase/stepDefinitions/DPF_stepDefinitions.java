package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.DPFs;
import gov.uscourts.ao.mobileBriefcase.page.common.DPFs.DPF;

public class DPF_stepDefinitions {
	DPFs page = new DPFs();
	CommonPages page2 = new CommonPages();

	String caseNum = Document_StepDefinitions.regularCase;
	public static String actionName = "";
	
	@Then("^User selects action$")
	public void user_selects_action(DataTable table) {
		List<UserInputData> userInputData = null;
		List<List<String>> data = table.raw();
		String dpf = data.get(1).get(0);
		
		 actionName = page.getChmAssign(DPF.valueOf(dpf), caseNum, userInputData);

		page2 = new CommonPages();
		page2.selectBriefcaseAction("Actions", actionName);

	}
}
