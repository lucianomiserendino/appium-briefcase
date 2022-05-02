package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getCellCount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PendingTasksPage extends AppiumPageFactory {

	@iOSXCUITFindBy(accessibility = "Referrals Awaiting Action by Other Chambers")
	public static MobileElement ReferralsAwaiting;

	@iOSXCUITFindBy(accessibility = "My Assignments")
	public static MobileElement MyAssignments;

	@iOSXCUITFindBy(accessibility = "Pending Clerk's Filing")
	public static MobileElement PendingClerk;

	@iOSXCUITFindBy(accessibility = "Pending Clerk's Office")
	public static MobileElement PendingClerkOffice;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText/following:: XCUIElementTypeStaticText[contains(@name, '(')]")
	public static List<MobileElement> categoryCount;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<MobileElement> navIcons;

	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<MobileElement> GroupIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<MobileElement> caseNum;

	public void sortedInDescendingOrder(String folder) {

		getPendingSubFolder(folder);
		getGroupIcons();
		getAssignmentCategories();
		//
		if (folder.equals("MyAssignments") | folder.equals("ReferralsAwaiting")) {
			tapGroupIcons();
			getAssignmentCategories();
		}

		ArrayList<String> filedDates = new ArrayList<String>();
		List<MobileElement> date = Actions.findElements(By.xpath(Actions.containsElement(": ")));

		for (int i = 0; i < date.size(); i++) {
			String text = date.get(i).getText().split(": ")[1];
			filedDates.add(text);
		}

		assertTrue("VERIFY " + folder + " CASES ARE SORTED BY DATE DESCENDING ORDER",
				Utility.checkDatesForDescOrder(filedDates));
	}

	public void getAssignmentCategories() {
		List<Integer> categories = new ArrayList<>();

		for (int i = 0; i < categoryCount.size(); i++) {
			categories.add(Integer.parseInt(Actions.replace(categoryCount.get(i).getText(), "\\(", "", "\\)", "")));
		}
		Integer max = Collections.max(categories);
		Actions.tap(Locator.XPATH, Actions.containsElement("(" + max.toString() + ")"));

	}

	public static void tapGroupIcons() {
		if (GroupIcon.size() > 1) {
			for (int i = 2; i < GroupIcon.size() + 1; i++) {
				String groupIcon = "(//XCUIElementTypeStaticText[@name='GroupIcon'])[";
				while (findElements(
						By.xpath(groupIcon + i + "]/following::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]"))
						.size() == 0) {
					tap(Locator.XPATH, groupIcon + i + "]");
				}
			}
		}
	}

	public static void getPendingSubFolder(String folder) {

		MobileElement el = null;

		if (folder.equals("PendingClerk")) {
			el = PendingClerk;

		} else if (folder.equals("MyAssignments")) {
			el = MyAssignments;

		} else if (folder.equals("ReferralsAwaiting")) {
			el = ReferralsAwaiting;

		} else
			el = PendingClerkOffice;

		Actions.tap(el);
	}

	public void leftNavAndPendingTasksCategoriesAreSorted(String folder) {

		getPendingSubFolder(folder);
		getGroupIcons();
		ArrayList<String> listTwo = new ArrayList<String>();
		for (int y = 1; y < categoryCount.size() + 1; y++) {
			String pendingCategory = pendingCatigories(y).getText();
			listTwo.add(pendingCategory);
		}

		tap(contains("Expand"));
		ArrayList<String> listOne = new ArrayList<String>();

		int navCellSize = navIcons.size();

		List<Integer> nav = getCellCount(3, navCellSize + 1);
		String leftNavCategory = "";
		for (int i = 0; i < navCellSize - 2; i++) {
			try {
				leftNavCategory = navNewRefCount(nav.get(i)).getText().trim();
			} catch (TimeoutException e) {

				leftNavCategory = ifNewCountIsZero(nav.get(i)).getText().trim();
			} finally {
				listOne.add(leftNavCategory);
			}

		}

		List<String> commonElementsFromBothList = new ArrayList<>();

		commonElementsFromBothList
				.addAll(listOne.stream().filter(str -> listTwo.contains(str)).collect(Collectors.toList()));

		assertEquals(
				"The referral categories displayed in --->" + folder + " folder are not sorted "
						+ "in the same way as they are in the left-hand navigation",
				commonElementsFromBothList, listTwo);

	}

	public static MobileElement navNewRefCount(int index) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["
						+ index
						+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeStaticText");

	}

	public static MobileElement ifNewCountIsZero(int index) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther["
						+ index + "]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/" + "XCUIElementTypeOther[2]/"
						+ "XCUIElementTypeStaticText");

	}

	public static MobileElement pendingCatigories(int index) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther["
						+ index + "]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText");
	}

	public static MobileElement getExistingAssignment(String assineeName, String AssignmentTypeAndDate) {
		return findElement(By.xpath("//*[contains(@name, '" + assineeName
				+ "')]/following::XCUIElementTypeStaticText[contains(@name, '" + AssignmentTypeAndDate + "')]"));
	}

	public void getJudgeAssignment(List<UserInputData> userInputData, String assineeName,
			String AssignmentTypeAndDate) {
		getPendingSubFolder("MyAssignments");

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
