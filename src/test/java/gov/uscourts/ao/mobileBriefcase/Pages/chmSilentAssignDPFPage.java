package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class chmSilentAssignDPFPage extends AppiumPageFactory {
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<MobileElement> caseNum;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"PendingTasksList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[6]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]")
	public static MobileElement assignmentType;

	@iOSXCUITFindBy(id = "My Assignments")
	public static MobileElement MyAssignments;
	
	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<MobileElement> GroupIcon;

	String actionName = "Auto Test";

	public void createChmSilentAssign(List<UserInputData> userInputData) {

		chmAssignDPFPage.submiTransaction();
		if (contains("Dashboard").isDisplayed())
			contains("Dashboard").click();

		String assignment = Utility.splitBy(assignmentType.getText(), 0);
		String assignmentDate = Utility.splitBy(assignmentType.getText(), 1);

		getJudgeAssignment(userInputData, assignment, assignmentDate);
	}

	public static MobileElement getExistingAssignment(String assineeName, String AssignmentTypeAndDate) {
		return findElement(By.xpath("//*[contains(@name, '" + assineeName
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + AssignmentTypeAndDate + "')]"));
	}
	
	

	public void getJudgeAssignment(List<UserInputData> userInputData, String assineeName,
			String AssignmentTypeAndDate) {
		PendingTasksPage.getPendingSubFolder("MyAssignments");
		
		for (int i = 1; i < GroupIcon.size() + 1; i++) {
			String groupIcon = "(//XCUIElementTypeStaticText[@name='GroupIcon'])[";
			while (findElements(
					By.xpath(groupIcon + i + "]/following::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]"))
					.size() == 0) {
				tap(Locator.XPATH, groupIcon + i + "]");
			}
		}

		List<String> list = Utility.retrieveAllReferrals(caseNum, " ", 0);

		Page.sleep(20000);

		MobileElement uiResult = findElementBy(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '"
				+ list.get(Utility.getRandomInt(list.size() - 1)) + "')]");

		uiResult.click();

		boolean isDisplayed = false;

		try {
			MobileElement el = getExistingAssignment(assineeName, AssignmentTypeAndDate);
			if (el.isDisplayed())
				isDisplayed = true;
		} catch (WebDriverException e) {
			isDisplayed = false;
		}
		assertTrue(isDisplayed);

	}

}
