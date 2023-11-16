package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.ProposedOrdersPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class ProposedOrders_StepDefinitions {

	ProposedOrdersPage page;
	String preSelectedPDF = "";

	@Then("^user selects a category and case with Proposed Orders$")
	public void user_selects_a_category_and_case_with_Proposed_Orders() {
		List<UserInputData> userInputData = null;
		page = new ProposedOrdersPage();
		page.selectRandomCaseNumber(DocumentPage.get_pe_id("jud", userInputData), userInputData);
	}

	@When("^user verifies Proposed Orders are downloaded$")
	public void user_verifies_Proposed_Orders_are_downloaded() {
		page = new ProposedOrdersPage();
		page.verifyDocumentIsDownloaded();
	}

	@When("^user dockets docWP dpf$")
	public void user_dockets_docWP_dpf() {
		page = new ProposedOrdersPage();
		preSelectedPDF = page.submitDocWPDPF();
	}

	@Then("^user verifies Briefcase supports the docWPText TPF$")
	public void user_verifies_Briefcase_supports_the_docWPText_TPF() {
		page = new ProposedOrdersPage();
		page.verifyDocWPIsSupported(preSelectedPDF);
	}

}
