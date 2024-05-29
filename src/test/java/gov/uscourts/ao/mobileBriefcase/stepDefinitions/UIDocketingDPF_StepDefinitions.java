package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.UIDocketingDPFPage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class UIDocketingDPF_StepDefinitions {
	UIDocketingDPFPage page;

	@Then("^User verifies the text \"([^\"]*)\" displays in the light blue banner\\.$")
	public void user_verifies_the_text_displays_in_the_light_blue_banner(String text) {
		page = new UIDocketingDPFPage();
		page.verifyElementIsDisplayed(text);
	}


	@Then("^user verifies an editable Description , Comment , Submit fields are  displayed\\.  The default description is defined in the Default description parameter of the note DPF$")
	public void user_verifies_an_editable_Description_Comment_Submit_fields_are_displayed_The_default_description_is_defined_in_the_Default_description_parameter_of_the_note_DPF(
			List<UserInputData> userInputData) {
		page.verifyFieldsAreDisplayedAndEditable(userInputData);
	}
	
	@Then("^user verifies Briefcase supports the noteText TPF$")
	public void user_verifies_Briefcase_supports_the_noteText_TPF()  {
		page = new UIDocketingDPFPage();
		page.isNoteTextTPFSupported();
	}

	@Then("User selects one of the proposed orders, enters a description, submits the transaction, verify name is saved to the DB.")
	public void user_selects_one_of_the_proposed_orders_enters_a_description_submits_the_transaction_verify_name_is_saved_to_the_db() {
		page = new UIDocketingDPFPage();
		page.selectProposedOrder();
	}

}
