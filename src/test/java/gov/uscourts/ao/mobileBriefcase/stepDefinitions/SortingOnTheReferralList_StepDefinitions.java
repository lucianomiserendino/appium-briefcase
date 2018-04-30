package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.SortingOnTheReferralListPage;

public class SortingOnTheReferralList_StepDefinitions {

	SortingOnTheReferralListPage page;

	@Given("^User selects a judge and then the motions/petitions category$")
	public void user_selects_a_judge_and_then_the_motions_petitions_category() {
		page = new SortingOnTheReferralListPage();
		page.clickOnMotionsPetitions();

	}

	@When("^User selects the sort button\\. User verifies the Date Down Arrow is selected by default and verifies the referrals are sorted by referred date in descending order \\(newest first\\)\\.$")
	public void user_selects_the_sort_button_User_verifies_the_Date_Down_Arrow_is_selected_by_default_and_verifies_the_referrals_are_sorted_by_referred_date_in_descending_order_newest_first() {
		try {
			List<String> defaulOrder = page.referralsInDefaultOrder();
			page.selectSortBtn();
			List<String> descOrder = page.referralsInDescendingOrder();

			assertTrue("DROP DOWN ARROW OR REFERRALS BY DEFAULT ARE NOT SORTED IN DESCENDING ORDER",
					defaulOrder.equals(descOrder));
		} catch (AssertionError ex) {
			ex.printStackTrace();
		}
	}

	@And("^User clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order \\(oldest first\\)\\.$")
	public void user_clicks_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_ascending_order_oldest_first() {
		try {
			List<String> descOrder = page.referralsInDescendingOrder();
			List<String> ascOrder = page.verifyReferralsInAscendingOrder();

			assertTrue("DATE UP ARROW BUTTON IS NOT SORTING IN ASCENDING ORDER", descOrder.equals(ascOrder));
		} catch (AssertionError ex) {
			ex.printStackTrace();
		}
	}

	@Then("^User clicks the Case Down Arrow button and  verifies the referrals are sorted by case number in descending order after User clicks the Case Up Arrow button, verifies the referrals are sorted by case number in ascending order\\.$")
	public void user_clicks_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_descending_order_after_User_clicks_the_Case_Up_Arrow_button_verifies_the_referrals_are_sorted_by_case_number_in_ascending_order() {
		try {
			List<String> caseDescOrder = page.referralsSortedByCaseNumInDescOrder();
			List<String> caseAscOrder = page.referralsSortedByCaseNumInAscOrder();

			assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER", caseDescOrder.equals(caseAscOrder));
		} catch (AssertionError ex) {
			ex.printStackTrace();
		}
	}

}
