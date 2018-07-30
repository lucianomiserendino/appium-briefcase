package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static java.util.Collections.reverse;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_SortingOnTheReferralListPage;

public class SortingOnTheReferralList_StepDefinitions {

	iOS_SortingOnTheReferralListPage page;

	@When("^User selects Judge,  \"([^\"]*)\"$")
	public void user_selects_Judge(String category) {
		page = new iOS_SortingOnTheReferralListPage();
		page.clickOnMotionsPetitions(category);
	}

	@When("^User selects the sort button, clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in descending order \\(newest first\\)\\.$")
	public void user_selects_the_sort_button_clicks_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_descending_order_newest_first() {

		page.selectSortBtn();

		List<String> referralsSotedByDatesInDescendingOrder = page.referralsSortedByDatesInDescendingOrder();
		sort(referralsSotedByDatesInDescendingOrder);
		reverse(referralsSotedByDatesInDescendingOrder);
		System.out.println(referralsSotedByDatesInDescendingOrder);

		List<String> referralsSotedByDateInAscendingOrder = page.referralsSortedByDateInAscendingOrder();
		reverse(referralsSotedByDateInAscendingOrder);
		assertTrue("REFERRALS ARE NOT SORTED BY REFERRED DATE IN DESCENDING ORDER",
				referralsSotedByDatesInDescendingOrder.equals(referralsSotedByDateInAscendingOrder));

	}

	@When("^User clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order \\(oldest first\\)\\.$")
	public void user_clicks_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_ascending_order_oldest_first() {

		List<String> referralsSotedByDatesInDescendingOrder = page.referralsSortedByDatesInDescendingOrder();
		sort(referralsSotedByDatesInDescendingOrder);

		List<String> referralsSotedByDateInAscendingOrder = page.referralsSortedByDateInAscendingOrder();
		assertTrue("REFERRALS ARE NOT SORTED BY REFERRED DATE IN ASCENDING ORDER",
				referralsSotedByDatesInDescendingOrder.equals(referralsSotedByDateInAscendingOrder));

	}

	@Then("^User clicks the Case Down Arrow button and  verifies the referrals are sorted by case number in descending order$")
	public void user_clicks_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_descending_order() {
		List<String> referralsSortedByCasesInDescendingOrder = page.referralsSortedByCasesInDescendingOrder();
		sort(referralsSortedByCasesInDescendingOrder);
		reverse(referralsSortedByCasesInDescendingOrder);

		List<String> referralsSortedByCasesInAscendingOrder = page.referralsSortedByCasesInAscendingOrder();
		reverse(referralsSortedByCasesInAscendingOrder);

		assertTrue("REFERRALS  ARE NOT SORTED BY CASE NUMBER IN DESCENDING ORDER",
				referralsSortedByCasesInDescendingOrder.equals(referralsSortedByCasesInAscendingOrder));

	}

	@Then("^User clicks the Case Up Arrow button, verifies the referrals are sorted by case number in ascending order\\.$")
	public void user_clicks_the_Case_Up_Arrow_button_verifies_the_referrals_are_sorted_by_case_number_in_ascending_order()
			throws Throwable {
		List<String> referralsSortedByCasesInDescendingOrder = page.referralsSortedByCasesInDescendingOrder();
		sort(referralsSortedByCasesInDescendingOrder);

		List<String> referralsSortedByCasesInAscendingOrder = page.referralsSortedByCasesInAscendingOrder();

		assertTrue("REFERRALS  ARE NOT SORTED BY CASE NUMBER IN ASCENDING ORDER",
				referralsSortedByCasesInDescendingOrder.equals(referralsSortedByCasesInAscendingOrder));

	}
}
