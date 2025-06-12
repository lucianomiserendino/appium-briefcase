package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import java.util.List;


import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.InternalNotePage;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.cucumber.java.en.Then;

public class InternalNote_StepDefinitions {
	String caseNum = DocumentPage.caseNum;
	String refCategory = DocumentPage.category;
	String expectedNote = "";
	InternalNotePage page;

	@Then("^User creates a new internal note and verifies the note is saved/displayed in the referral detail and referral category page$")
	public void user_creates_a_new_internal_note_and_verifies_the_note_is_saved_displayed_in_the_referral_detail_and_referral_category_page() {
		List<UserInputData> userInputData = null;
		page = new InternalNotePage();
		expectedNote = page.ifInternalNoteExists(refCategory,
				caseNum, userInputData);
	}

	@Then("^User navigates away to a different category then back and verifies that the existing note still remains on the referral category and case detail page$")
	public void user_navigates_away_to_a_different_category_then_back_and_verifies_that_the_existing_note_still_remains_on_the_referral_category_and_case_detail_page() {
		page = new InternalNotePage();
		page.navigateAway(refCategory,caseNum, expectedNote);

	}

	@Then("^User verifies that the existing note still remains on the referral category and case detail page$")
	public void user_verifies_that_the_existing_note_still_remains_on_the_referral_category_and_case_detail_page() {
		page = new InternalNotePage();
		page.navigateAway(refCategory, caseNum, expectedNote);

	}

	@Then("^User deletes the existing note and verifies that the note doesn't come back after navigating away to a different category then back$")
	public void user_deletes_the_existing_note_and_verifies_that_the_note_doesn_t_come_back_after_navigating_away_to_a_different_category_then_back() {
		List<UserInputData> userInputData = null;
		page = new InternalNotePage();
		page.deleteExistingNote(refCategory, caseNum,
				expectedNote, userInputData);

	}

	@Then("^User verifies that the note doesn't come back after closing and reopening the app$")
	public void user_verifies_that_the_note_doesn_t_come_back_after_closing_and_reopening_the_app() {
		page = new InternalNotePage();
		page.removeAndNavigateAway(refCategory, caseNum);
	}

}
