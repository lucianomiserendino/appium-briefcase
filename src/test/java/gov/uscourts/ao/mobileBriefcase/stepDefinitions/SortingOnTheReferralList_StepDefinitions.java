package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.Utilities.sortArray;
import static java.util.Collections.reverse;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_SortingOnTheReferralListPage;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;

public class SortingOnTheReferralList_StepDefinitions {

	iOS_SortingOnTheReferralListPage page;

	@When("^User selects Judge,  \"([^\"]*)\"$")
	public void user_selects_Judge(String category) {
		page = new iOS_SortingOnTheReferralListPage();
		page.clickOnMotionsPetitions(category);
	}

	@When("^User selects the sort button, clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in descending order \\(newest first\\)\\. User clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order \\(oldest first\\)\\.$")
	public void user_selects_the_sort_button_clicks_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_descending_order_newest_first_User_clicks_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_ascending_order_oldest_first() {

		page.selectSortBtn();

		List<String> referralsSotedByDatesInDescendingOrder = page
				.referralsSortedByDate(Actions.SORT_DATES_IN_DESCENDING_ORDER);
		sort(referralsSotedByDatesInDescendingOrder);
		reverse(referralsSotedByDatesInDescendingOrder);

		List<String> referralsSotedByDateInAscendingOrder = page
				.referralsSortedByDate(Actions.SORT_DATES_IN_ASCENDING_ORDER);
		reverse(referralsSotedByDateInAscendingOrder);

		assertTrue("REFERRALS ARE NOT SORTED BY  DATE",
				referralsSotedByDatesInDescendingOrder.equals(referralsSotedByDateInAscendingOrder));

	}

	@Then("^User clicks the Case Down Arrow button and  verifies the referrals are sorted by case number in descending order \\. User clicks the Case Up Arrow button, verifies the referrals are sorted by case number in ascending order\\.$")
	public void user_clicks_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_descending_order_User_clicks_the_Case_Up_Arrow_button_verifies_the_referrals_are_sorted_by_case_number_in_ascending_order() {

		List<String> referralsSortedByCasesInDescendingOrder = page
				.referralsSortedByCase(Actions.SORT_CASES_IN_DESCENDING_ORDER);
		reverse(referralsSortedByCasesInDescendingOrder);

		String[] arr = referralsSortedByCasesInDescendingOrder.toArray(new String[0]);
		Arrays.toString(arr);
		sortArray(arr);

		List<String> referralsSortedByCasesInAscendingOrder = page
				.referralsSortedByCase(Actions.SORT_CASES_IN_ASCENDING_ORDER);

		assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER IN DESCENDING ORDER",
				Arrays.asList(arr).equals(referralsSortedByCasesInAscendingOrder));

	}

}
