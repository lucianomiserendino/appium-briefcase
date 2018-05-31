package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCase;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.split;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.Utilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class AssignmentsPanelPage {

	public AssignmentsPanelPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='Background'])[4]")
	public static MobileElement motionsPetitions;

	@iOSFindBy(xpath = "//*[contains(@name, 'Assignments')]")
	public static MobileElement assignments;

	@iOSFindBy(xpath = "//*[contains(@name, 'Assigned')]")
	public static List<MobileElement> assignedDates;

	

	public void getCase(String caseNum) {
		clickOn(motionsPetitions);
		selectCase("//*[contains(@name, '" + caseNum + "')]");

	}

	public void verifyAssignmentIsDisplayed(String assignmentOnReferral) {
		performPageLoad();
		try {
			do {
				Assert.assertTrue(split(assignments.getText(), " ", 1).equals(assignmentOnReferral));
				waitToBeClickable(assignments);
			} while (assignments.isDisplayed());

			{
			}

		} catch (Exception e) {

		}

	}

	public void getAssignmentLinkedtoTheReferral(String assignmentForKyle, String assignmentForEssley) {

		List<String> dbReferralAssignments = executeQuery(Queries.STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL);
		try {
			do {
			Assert.assertTrue("STAFF ASSIGNMENTS LINKED TO THE REFERRAL ARE NOT DISPLAYED",
					dbReferralAssignments.containsAll(listOfAssignments(assignmentForKyle, assignmentForEssley)));
			} while (assignments.isDisplayed());
		} catch (Exception e) {
			e.getMessage();
		}

		waitToBeClickable(assignments);
		Utilities.navigateBack("Back");

	}

	public void getAssignmentLinkedtoCase(String assignmentForCourtney) {

		List<String> uiCaseAssignment = new ArrayList<>();

		uiCaseAssignment.add(getCaseAssigments(assignmentForCourtney));

		List<String> dbCaseAssignments = executeQuery(Queries.STAFF_ASSIGNMENTS_LINKED_TO_THE_CASE);
		try {
			do {
			Assert.assertTrue("STAFF ASSIGNMENTS LINKED TO THE CASE ARE NOT DISPLAYED",
					dbCaseAssignments.containsAll(uiCaseAssignment));
		} while (assignments.isDisplayed());
		} catch (Exception e) {
			e.getMessage();
		}

		waitToBeClickable(assignments);
		Utilities.navigateBack("Back");
	}



	public List<String> listOfAssignments(String assignmentForKyle, String assignmentForEssley) {
		List<String> uiReferralAssignments = new ArrayList<>();
		uiReferralAssignments.add(getReferralAssigments(assignmentForKyle));
		uiReferralAssignments.add(getReferralAssigments(assignmentForEssley));
		Collections.sort(uiReferralAssignments);
		return uiReferralAssignments;
	}

	public String getReferralAssigments(String assignments) {
		return split(getReferralAssigmentElements(assignments), " ", 0);

	}

	public String getCaseAssigments(String assignments) {
		return split(getReferralAssigmentElements(assignments), " ", 1).replaceAll(",", "");

	}

	public String getReferralAssigmentElements(String assignments) {
		return findElement(By.xpath("//*[contains(@name, '" + assignments + "')]")).getText();

	}

}