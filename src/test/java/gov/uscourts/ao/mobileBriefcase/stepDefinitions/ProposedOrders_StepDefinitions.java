package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.ProposedOrdersPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.DPFs;
import gov.uscourts.ao.mobileBriefcase.page.common.DPFs.DPF;

public class ProposedOrders_StepDefinitions {

	ProposedOrdersPage page;
	String preSelectedPDF = "";
	CommonPages page2 = new CommonPages();
	DPFs dpfPage = new DPFs();
	public static String actionName = "";
	public static String caseNum ="";
	@Then("^user selects a category and case with Proposed Orders$")
	public void user_selects_a_category_and_case_with_Proposed_Orders() {
		List<UserInputData> userInputData = null;
		page = new ProposedOrdersPage();
		caseNum=page.selectRandomCaseNumber(DocumentPage.get_pe_id("jud", userInputData), userInputData);
	}

	@When("^user verifies Proposed Orders are downloaded$")
	public void user_verifies_Proposed_Orders_are_downloaded() {
		page = new ProposedOrdersPage();
		page.verifyDocumentIsDownloaded();
	}
	
	@Then("^User selects action that contains docWP$")
	public void user_selects_action_that_contains_docWP(DataTable table){
		
		List<UserInputData> userInputData = null;
		List<List<String>> data = table.raw();
		String dpf = data.get(1).get(0);
		
		 actionName = dpfPage.getChmAssign(DPF.valueOf(dpf), caseNum, userInputData);
		 
		page2 = new CommonPages();
		page2.selectBriefcaseAction("Actions", actionName);
	}


	@When("^user dockets docWP dpf$")
	public void user_dockets_docWP_dpf() {
		page = new ProposedOrdersPage();
		preSelectedPDF = page.submitDocWPDPF();
	}

	@Then("^user verifies Briefcase supports the docWPText TPF$")
	public void user_verifies_Briefcase_supports_the_docWPText_TPF() {
		DocumentPage	docPage = new DocumentPage();
		docPage.navigateToViewCaseInfo(actionName);
		page = new ProposedOrdersPage();
		page.verifyDocWPIsSupported(preSelectedPDF);
	}

}
