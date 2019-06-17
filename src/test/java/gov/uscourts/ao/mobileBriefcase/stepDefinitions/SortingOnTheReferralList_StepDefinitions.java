package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.Utility.sortArray;
import static java.util.Collections.reverse;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage.Sort;

public class SortingOnTheReferralList_StepDefinitions {

	ReferralSortOrderPage page;

	@When("^User verifies  the Date Down Arrow is selected by default and that the referrals are sorted by referred date in descending order \\(newest first\\)\\.$")
	public void user_verifies_the_Date_Down_Arrow_is_selected_by_default_and_that_the_referrals_are_sorted_by_referred_date_in_descending_order_newest_first() {
		page = new ReferralSortOrderPage();

		List<String> referralsSotedByDatesInDefaultOrder = page.referralsSortedByDate();
		page.selectSortBtn();
		page.getSortPage(Sort.SORT_DATES_IN_DESCENDING_ORDER);
		List<String> referralsSotedByDatesInDescendingOrder = page.referralsSortedByDate();

		assertTrue("REFERRALS ARE NOT SORTED BY DEFAULT",
				referralsSotedByDatesInDefaultOrder.equals(referralsSotedByDatesInDescendingOrder));
	}

	@When("^User selects the sort button, clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in descending order \\(newest first\\)\\. User clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order \\(oldest first\\)\\.$")
	public void user_selects_the_sort_button_clicks_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_descending_order_newest_first_User_clicks_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_ascending_order_oldest_first() {

		List<String> referralsSotedByDatesInDescendingOrder = page.referralsSortedByDate();
		sort(referralsSotedByDatesInDescendingOrder);
		reverse(referralsSotedByDatesInDescendingOrder);

		page.getSortPage(Sort.SORT_DATES_IN_ASCENDING_ORDER);
		List<String> referralsSotedByDateInAscendingOrder = page.referralsSortedByDate();
		reverse(referralsSotedByDateInAscendingOrder);

		assertTrue("REFERRALS ARE NOT SORTED BY DATE",
				referralsSotedByDatesInDescendingOrder.equals(referralsSotedByDateInAscendingOrder));

	}

	@Then("^User clicks the Case Down Arrow button and  verifies the referrals are sorted by case number in descending order \\. User clicks the Case Up Arrow button, verifies the referrals are sorted by case number in ascending order\\.$")
	public void user_clicks_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_descending_order_User_clicks_the_Case_Up_Arrow_button_verifies_the_referrals_are_sorted_by_case_number_in_ascending_order() {

		List<String> referralsSortedByCasesInDescendingOrder = page
				.referralsSortedByCase(Sort.SORT_CASES_IN_DESCENDING_ORDER);
		reverse(referralsSortedByCasesInDescendingOrder);

		String[] referralsSortedByCasesIn = referralsSortedByCasesInDescendingOrder.toArray(new String[0]);
		Arrays.toString(referralsSortedByCasesIn);
		sortArray(referralsSortedByCasesIn);

		List<String> referralsSortedByCasesInAscendingOrder = page
				.referralsSortedByCase(Sort.SORT_CASES_IN_ASCENDING_ORDER);

		assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER IN DESCENDING ORDER",
				Arrays.asList(referralsSortedByCasesIn).equals(referralsSortedByCasesInAscendingOrder));

	}

	@Then("^User verifies  Document Categories are sorted on the referral detail page \\(\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"\\)$")
	public void user_verifies_Document_Categories_are_sorted_on_the_referral_detail_page(String dbType,
			String cmr_cyv_code, String cmr_ju_pe_id, String cmr_cs_caseid) {
		page = new ReferralSortOrderPage();
		page.getDocumentCategories(dbType, cmr_cyv_code, cmr_ju_pe_id, cmr_cs_caseid);
	}
}
