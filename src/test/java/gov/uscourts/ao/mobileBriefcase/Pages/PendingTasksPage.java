package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
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
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PendingTasksPage extends AppiumPageFactory {

	@iOSXCUITFindBy(accessibility = "Referrals Awaiting Action by Other Chambers")
	public static WebElement ReferralsAwaiting;

	@iOSXCUITFindBy(accessibility = "My Assignments")
	public static WebElement MyAssignments;

	@iOSXCUITFindBy(accessibility = "Pending Clerk's Filing")
	public static WebElement PendingClerk;

	@iOSXCUITFindBy(accessibility = "Pending Clerk's Office")
	public static WebElement PendingClerkOffice;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText/following:: XCUIElementTypeStaticText[contains(@name, '(')]")
	public static List<WebElement> categoryCount;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<WebElement> navIcons;

	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<WebElement> GroupIcon;

	@iOSXCUITFindBy(xpath = "**/XCUIElementTypeStaticText[label == '▽'][2]")
	public static List<WebElement> GroupIcon2;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> caseNum;

	public void sortedInDescendingOrder(String folder) {

		getPendingSubFolder(folder);
		getGroupIcons();
		sortedInDescendingOr(folder);

		ArrayList<String> filedDates = new ArrayList<String>();
		List<WebElement> date = Actions.findElements(By.xpath(Actions.containsElement(": ")));

		for (int i = 0; i < date.size(); i++) {
			String text = date.get(i).getText().split(": ")[1];
			filedDates.add(text);
		}

		assertTrue("VERIFY " + folder + " CASES ARE SORTED BY DATE DESCENDING ORDER",
				Utility.checkDatesForDescOrder(filedDates, "M/d/yyyy"));
	}

	public void sortedInDescendingOr(String folder) {

		int caseCount = 1;
		int total = 0;

		List<Integer> count = new ArrayList<>();

		int s = categoryCount.size();

		for (int i = 0; i < s; i++) {

			count.add(Integer.parseInt(Actions.replace(categoryCount.get(i).getText(), "\\(", "", "\\)", "")));
		}

		Integer max = Collections.max(count);

		String categoryName = Actions.containsElement("(" + max.toString() + ")")
				+ "/preceding:: XCUIElementTypeStaticText[1]";

		String catN = Actions.findElementBy(Locator.XPATH, categoryName).getText().trim();
		Actions.tap(Locator.XPATH, categoryName);

		if (folder.equals("MyAssignments") | folder.equals("ReferralsAwaiting")) {

			Boolean elementNotFound = true;

			while (elementNotFound) {

				List<WebElement> icons = Actions.findElements(By.xpath(
						"//XCUIElementTypeOther[@name=\"PendingTasksList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther//following::XCUIElementTypeStaticText[@name='"
								+ catN + "']/following::XCUIElementTypeStaticText[@name=\"GroupIcon\"]"));

				for (int i = 0; i < s + icons.size(); i++) {

					if (icons.get(i).getAttribute("value").equals("▽")) {

						icons.get(i).click();

					}

					List<WebElement> groups = Actions.findElements(By.xpath(
							"//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther//following::XCUIElementTypeStaticText[@name='"
									+ catN + "']/following::XCUIElementTypeStaticText[contains(@name, '(')]"));

					int left = CalendarPage.totalNumOfCases(groups, i);

					total += left;

					if (total == max) {

						if (caseCount > 1) {

							List<Integer> c = new ArrayList<>();

							for (int k = 0; k < caseCount; k++) {

								c.add(Integer.parseInt(Actions.replace(groups.get(k).getText(), "\\(", "", "\\)", "")));
							}

							Integer m = Collections.max(c);

							WebElement g = Actions.findElement(By.xpath(
									"//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther//following::XCUIElementTypeStaticText[@name='"
											+ catN + "']/following::XCUIElementTypeStaticText[contains(@name, '" + "("
											+ m.toString() + ")" + "')][1]"));

							// Actions.findElementBy(Locator.XPATH, categoryName).getText().trim();

							g.click();

						} else {
							groups.get(0).click();
							;
						}

						elementNotFound = false;

						break;

					} else {
						caseCount++;
						elementNotFound = true;

					}

				}
			}
		}

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

		WebElement el = null;

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

	public static WebElement navNewRefCount(int index) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["
						+ index
						+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeStaticText");

	}

	public static WebElement ifNewCountIsZero(int index) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='nav']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther["
						+ index + "]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/" + "XCUIElementTypeOther[2]/"
						+ "XCUIElementTypeStaticText");

	}

	public static WebElement pendingCatigories(int index) {
		return findElementBy(Locator.XPATH,
				"//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther["
						+ index + "]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText");
	}

}
