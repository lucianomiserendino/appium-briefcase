package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import cucumber.api.java.en.Then;
import gov.uscourts.ao.mobileBriefcase.Pages.DocumentPage;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage.Sort;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;

public class SortingOnTheReferralList_StepDefinitions {

	ReferralSortOrderPage page;
DocumentPage docPage;
	@Then("^User verifies that the Date Down Arrow is selected by default and the referrals are sorted by referred date in descending order \\(newest first\\)\\.$")
	public void user_verify_the_Date_Down_Arrow_is_selected_by_default_and_that_the_referrals_are_sorted_by_referred_date_in_descending_order_newest_first() {
		page = new ReferralSortOrderPage();
		Page.sleep(20000);
		List<String> defaultOrder = page.referralsSortedByDate();
		assertTrue(Utility.checkDatesForDescOrder(defaultOrder, "M/d/yyyy"));

		page.selectSortBtn();

		page.getSortPage(Sort.REFERRAL_DATE_DESCENDING);
		List<String> descOrder = page.referralsSortedByDate();
		assertTrue("REFERRALS ARE NOT SORTED BY REFERRED DATE IN DESCENDING ORDER",Utility.checkDatesForDescOrder(descOrder, "M/d/yyyy"));
	}

	@Then("^User clicks on the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order \\(oldest first\\)\\.$")
	public void user_clicks_on_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_ascending_order_oldest_first() {
		page.getSortPage(Sort.REFERRAL_DATE_ASCENDING);
		List<String> ascendingOrder = page.referralsSortedByDate();
		assertTrue("REFERRALS ARE NOT SORTED BY REFERRED DATE IN ASCENDING ORDER",Utility.checkDatesForAscOrder(ascendingOrder, "M/d/yyyy"));

	}

	@Then("^User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in descending order$")
	public void user_clicks_on_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_descending_order() {
		page.getSortPage(Sort.CASE_NUMBER_DESCENDING);
		List<String> referralsSortedByDescOrd = page.referralsSortedByCase();
		List<String> beforeReversing = referralsSortedByDescOrd;
		Collections.reverse(referralsSortedByDescOrd);
		assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER IN DESCENDING ORDER ------> " + beforeReversing,
				Utility.checkIfSorted(referralsSortedByDescOrd));

	}

	@Then("^User clicks on the Case Up Arrow button and verifies the referrals are sorted by case number in ascending order\\.$")
	public void user_clicks_on_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_ascending_order() {

		page.getSortPage(Sort.CASE_NUMBER_ASCENDING);
		List<String> referralsSortedByAscOrd = page.referralsSortedByCase();
		assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER IN ASCENDING ORDER  ------> " + referralsSortedByAscOrd,
				Utility.checkIfSorted(referralsSortedByAscOrd));
		page.getSortPage(Sort.REFERRAL_DATE_DESCENDING);
		page.selectSortBtn();
	}

	@Then("^User verifies  Document Categories are sorted on the referral detail page and each document for a specific category is listed and ordered by the filed date\\.$")
	public void user_verifies_Document_Categories_are_sorted_on_the_referral_detail_page_and_each_document_for_a_specific_category_is_listed_and_ordered_by_the_filed_date() {
		List<UserInputData> userInputData = null;
		docPage = new DocumentPage();
		docPage.verifyDocumentCategorySorting(userInputData);
	}
}
