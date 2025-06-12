package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;


import java.util.Collections;
import java.util.List;

import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage.Sort;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.cucumber.java.en.Then;

public class SortingOnTheReferralList_StepDefinitions {

DocumentPage docPage;

	@Then("^User verifies  Document Categories are sorted on the referral detail page and each document for a specific category is listed and ordered by the filed date\\.$")
	public void user_verifies_Document_Categories_are_sorted_on_the_referral_detail_page_and_each_document_for_a_specific_category_is_listed_and_ordered_by_the_filed_date() {
		List<UserInputData> userInputData = null;
		docPage = new DocumentPage();
		docPage.verifyDocumentCategorySorting(userInputData);
	}
}
