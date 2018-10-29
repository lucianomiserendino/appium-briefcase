package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase.DELETE_CASE_INFO_REPORT_PAGE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase.closeWebDriver;
import static gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase.createARandomCase;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.sleep;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findWebElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.update;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.uscourts.ao.mobileBriefcase.Pages.CopyDeleteCase;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_CreateStaffAssignmentsPage;

public class CreateStaffAssignments_StepDefinitions {
	iOS_CommonPages page;
	iOS_CreateStaffAssignmentsPage page1;
	CopyDeleteCase page2;
	String caseN = createARandomCase();

	@Given("^User creates a new case$")
	public void user_creates_a_new_case() throws InterruptedException {
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

	@When("^User selects a Judge, category: \"([^\"]*)\" and case$")
	public void user_selects_a_Judge_category_and_case(String category) {
		page = new iOS_CommonPages();
		update();
		page.selectCategoryAndCase(category, caseN);

	}

	@Then("^User  verifies the \"([^\"]*)\"  in a light blue banner , \"([^\"]*)\"  and \"([^\"]*)\" button display on the page$")
	public void user_verifies_the_in_a_light_blue_banner_and_button_display_on_the_page(String assignment,
			String newStaffAssign, String submit) {
		page.verifyElementIsDisplayed(assignment);
		page.verifyElementIsDisplayed(newStaffAssign);
		page.verifyElementIsDisplayed(submit);
	}

	@When("^User taps on \"([^\"]*)\" it  will display a new page\\. Verify page is entitled \"([^\"]*)\"$")
	public void user_taps_on_it_will_display_a_new_page_Verify_page_is_entitled(String newStaffAssign,
			String createAssignment) {
		clickOnElement(newStaffAssign);
		page.verifyElementIsDisplayed(createAssignment);
	}

	@When("^User verifies a label \"([^\"]*)\" and \"([^\"]*)\" is  displayed next to the  drop-down that contains a list of staff and click on it$")
	public void user_verifies_a_label_and_is_displayed_next_to_the_drop_down_that_contains_a_list_of_staff_and_click_on_it(
			String staffMember, String pleaseSelectBtn) {
		page1 = new iOS_CreateStaffAssignmentsPage();
		page1.getElementNextToDropDown(staffMember, pleaseSelectBtn);
	}

	@Then("^User verifies when tapping the drop-down a popup displays a list of staff based on the screen parameter in the DPF, \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_verifies_when_tapping_the_drop_down_a_popup_displays_a_list_of_staff_based_on_the_screen_parameter_in_the_DPF(
			String dbType, String elId, String peId) {
		page1.verifyListOfStaff(valueOf(dbType), elId, peId);

	}

	@Then("^user verifies  a label \"([^\"]*)\" is  displayed  under the staff drop-down , \"([^\"]*)\" is the default value and clicks on it$")
	public void user_verifies_a_label_is_displayed_under_the_staff_drop_down_is_the_default_value_and_clicks_on_it(
			String assignment, String pleaseSelectBtn) {
		page1.getElementNextToDropDown(assignment, pleaseSelectBtn);
	}

	@Then("^User verifies that when you tap the Please Select button next to the Assignment label, a pop-up displays with valid assignment types\\.  , \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_verifies_that_when_you_tap_the_Please_Select_button_next_to_the_Assignment_label_a_pop_up_displays_with_valid_assignment_types_and(
			String dbType, String elID) {
		page1.getAssignmentType(valueOf(dbType), elID, 1);

	}

	@Then("^User verifies that  A date field \"([^\"]*)\" will be displayed for each value followed by \"([^\"]*)\"\\.  Verifies when tapping a the date field, that a calendar pop-ups and today's date is selected by default\\.  Select a date and verify the date field is populated with the date$")
	public void user_verifies_that_A_date_field_will_be_displayed_for_each_value_followed_by_Verifies_when_tapping_a_the_date_field_that_a_calendar_pop_ups_and_today_s_date_is_selected_by_default_Select_a_date_and_verify_the_date_field_is_populated_with_the_date(
			String assigned, String selectDate) {
		page1.getElementNextToDropDown(assigned, selectDate);
		page1.selectADate(7);
	}

	@Then("^User verifies that  A date field \"([^\"]*)\" is displayed, followed by \"([^\"]*)\"\\.  Verifies when tapping a the date field, that a calendar pop-ups \\. Select a date and verify the date field is populated with the date$")
	public void user_verifies_that_A_date_field_is_displayed_followed_by_Verifies_when_tapping_a_the_date_field_that_a_calendar_pop_ups_Select_a_date_and_verify_the_date_field_is_populated_with_the_date(
			String assignmnetDue, String selectDate) {
		page1.getElementNextToDropDown(assignmnetDue, selectDate);
		page1.selectADate(5);

	}

	@Then("^User verifies \"([^\"]*)\"  is displayed under the assignment  date type and a text area displays next to the label enabling the user to enter notes about the assignment\\. Then  User verifies  \"([^\"]*)\" button displays \\. If the user clicks the Apply button, the popup will close and the new assignment will display on the chmAssign DPF screen\\. And User verifies  \"([^\"]*)\" button displays\\. If the user clicks Cancel, the popup will close and no data will be saved\\.$")
	public void user_verifies_is_displayed_under_the_assignment_date_type_and_a_text_area_displays_next_to_the_label_enabling_the_user_to_enter_notes_about_the_assignment_Then_User_verifies_button_displays_If_the_user_clicks_the_Apply_button_the_popup_will_close_and_the_new_assignment_will_display_on_the_chmAssign_DPF_screen_And_User_verifies_button_displays_If_the_user_clicks_Cancel_the_popup_will_close_and_no_data_will_be_saved(
			String comment, String apply, String cancel) {
		page.verifyElementIsDisplayed(comment);
		page.verifyElementIsDisplayed(apply);
		page.verifyElementIsDisplayed(cancel);

	}

	@Then("^User clicks on \"([^\"]*)\" and then \"([^\"]*)\" button and verifies that beck end \"([^\"]*)\" is updated correctly using \"([^\"]*)\" ,\"([^\"]*)\"$")
	public void user_clicks_on_and_then_button_and_verifies_that_beck_end_is_updated_correctly_using(String apply,
			String submit, String dbType, String cha_ju_pe_id, String elId) {
		page1.getExistingAssignment(apply, submit, valueOf(dbType), cha_ju_pe_id, elId);

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
