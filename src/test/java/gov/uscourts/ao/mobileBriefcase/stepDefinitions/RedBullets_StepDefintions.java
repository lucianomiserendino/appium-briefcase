package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase.DELETE_CASE_INFO_REPORT_PAGE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase.closeWebDriver;
import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase.createARandomCase;
import static gov.uscourts.ao.mobileBriefcase.common.Page.sleep;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findWebElement;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_RedBulletsPage;

public class RedBullets_StepDefintions {
	iOS_RedBulletsPage page;
	iOS_CommonPages page1;
	CopyDeleteCase page2;
	String caseN = createARandomCase();

	@Given("^User creates a  case$")
	public void user_creates_a_case() throws Throwable {
		page2 = new CopyDeleteCase();
		try {
			page2.copyCaseAndUpdateSingleTableEditor(caseN);

		} catch (Exception e) {
			e.getMessage();
		} finally {
			sleep(60000);
			closeWebDriver();
		}
	}

	@When("^User selects a Judge, category: \"([^\"]*)\" and created case$")
	public void user_selects_a_Judge_category_and_created_case(String category) {
		page = new iOS_RedBulletsPage();
		page.getReferral(category, "IS_DISPLAYED", caseN);
	}

	@Then("^User selects \"([^\"]*)\"  and clicks on \"([^\"]*)\"$")
	public void user_selects_and_clicks_on(String category, String document) {
		page.downloadTheDocument(category, document);
	}

	@Then("^User closes Briefcase app, opens back and verifies the red bullet does not display for viewed referrals/documnets \"([^\"]*)\"\\. Then User logs out\\.$")
	public void user_closes_Briefcase_app_opens_back_and_verifies_the_red_bullet_does_not_display_for_viewed_referrals_documnets_Then_User_logs_out(
			String document) {
		page1 = new iOS_CommonPages();
		page.getReferral("Test Automation", "IS_NOT_DISPLAYED", caseN);
		page.assertRedBulletIsNotDisplayed(document);
		page.logOut();
	}

	@When("^User selects a Judge, category: \"([^\"]*)\", created case and verifies Red Bullet for previously viewed Referral and Documnet doesn't display$")
	public void user_selects_a_Judge_category_created_case_and_verifies_Red_Bullet_for_previously_viewed_Referral_and_Documnet_doesn_t_display(
			String document) {
		page.getReferral("Test Automation", "IS_NOT_DISPLAYED", caseN);
		page.assertRedBulletIsNotDisplayed(document);

		try {
			page2.deleteCase(caseN);
			sleep(70000);
			assertTrue(findWebElement(By.xpath(DELETE_CASE_INFO_REPORT_PAGE)).isDisplayed());

		} catch (Exception e) {
			e.getMessage();

		} finally {

			closeWebDriver();

		}
	}

}
