package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage;
import gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage.Sort;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.cucumber.java.en.Then;

public class SortingOnTheReferralList_StepDefinitions {

	ReferralSortOrderPage page;

	@Then("^User verify the Date Down Arrow is selected by default and that the referrals are sorted by referred date in descending order \\(newest first\\)\\.$")
	public void user_verify_the_Date_Down_Arrow_is_selected_by_default_and_that_the_referrals_are_sorted_by_referred_date_in_descending_order_newest_first() {
		page = new ReferralSortOrderPage();
		Page.sleep(20000);
		List<String> defaultOrder = page.referralsSortedByDate();
		assertTrue(Utility.checkDatesForDescOrder(defaultOrder));

		page.selectSortBtn();

		page.getSortPage(Sort.REFERRAL_DATE_DESCENDING);
		List<String> descOrder = page.referralsSortedByDate();
		assertTrue(Utility.checkDatesForDescOrder(descOrder));
	}

	@Then("^User clicks on the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order \\(oldest first\\)\\.$")
	public void user_clicks_on_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_ascending_order_oldest_first() {
		page.getSortPage(Sort.REFERRAL_DATE_ASCENDING);
		List<String> ascendingOrder = page.referralsSortedByDate();
		assertTrue(Utility.checkDatesForAscOrder(ascendingOrder));

	}

	@Then("^User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in descending order$")
	public void user_clicks_on_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_descending_order() {
		page.getSortPage(Sort.CASE_NUMBER_DESCENDING);
		List<String> referralsSortedByDescOrd = page.referralsSortedByCase();
		Utility.isSorted("Desc", referralsSortedByDescOrd, referralsSortedByDescOrd);

	}

	@Then("^User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in ascending order\\.$")
	public void user_clicks_on_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_ascending_order() {

		page.getSortPage(Sort.CASE_NUMBER_ASCENDING);
		List<String> referralsSortedByAscOrd = page.referralsSortedByCase();
		Utility.isSorted("Asc", referralsSortedByAscOrd, referralsSortedByAscOrd);
		page.getSortPage(Sort.REFERRAL_DATE_DESCENDING);
		page.selectSortBtn();
	}

	@Then("^User verifies  Document Categories are sorted on the referral detail page \\(\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"\\)$")
	public void user_verifies_Document_Categories_are_sorted_on_the_referral_detail_page(String dbType,
			String cmr_cyv_code, String cmr_ju_pe_id, String cmr_cs_caseid) {
		page = new ReferralSortOrderPage();
		page.getDocumentCategories(dbType, cmr_cyv_code, cmr_ju_pe_id, cmr_cs_caseid);
	}
}
