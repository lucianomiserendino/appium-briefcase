package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DUPLICATED_LWK;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ToolsPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tools']")
	public static List<WebElement> tools;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tools']/following::XCUIElementTypeStaticText[@name='']")
	public static List<WebElement> redBullet;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"nav\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]")
	public static WebElement leftNav;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Categories\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]")
	public static WebElement dash;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply All']")
	public static WebElement applyAll;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Apply All']/preceding:: XCUIElementTypeButton[2]")
	public static WebElement exsitingClerkBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel")
	public static WebElement dropDown;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
	public static WebElement done;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Submit']")
	public static WebElement submit;

	public void getToolsCategory() {

		scrollUp();

		int toolSize = tools.size();
		int redBulletCount = redBullet.size();

		if (CommonPages.siVal.equalsIgnoreCase("n")) {
			assertTrue("The left nav displays the Tools category, even when briefcaseDisplayTools is set to 'n'. ",
					toolSize == 0);
		} else {

			assertTrue(
					"The left nav doesn't display the Tools category, even when briefcaseDisplayTools is set to 'y'. ",
					toolSize >= 1);
			assertTrue("the Tools icon displays a red badge with a count in it ", redBulletCount == 0);
		}
	}

	public void applyWithoutExistingClerk() {
		tools.get(0).click();
		if (Actions.isDisplayed(applyAll) == true) {
			applyAll.click();

			boolean progressBar = driver.getPageSource().contains("Please select an existing clerk");
			assertTrue("Tapping 'Apply All' without existing clerk is not generating a message", progressBar);

		}
	}

	public void scrollUp() {
		Utility.scroll(dash, "up");
		Utility.scroll(leftNav, "up");
	}

	public void isSortedInDescending(List<UserInputData> userInputData) {
		int index;
		List<String> pr_first_name = getListOfLwks(2, userInputData);
		List<String> pr_last_name = getListOfLwks(3, userInputData);

		if (pr_first_name.size() > 1) {
			index = Utility.getRandomNumberInRange(0, pr_first_name.size() - 1);
		} else {
			index = 0;
		}
      
		selectExistingClerk(pr_first_name.get(index).trim() + " " + pr_last_name.get(index).trim());

		ReferralSortOrderPage page2 = new ReferralSortOrderPage();

		List<String> referralsSortedByDescOrd = page2.assignmentsSortedByCase();

		List<String> beforeReversing = referralsSortedByDescOrd;
		Collections.reverse(referralsSortedByDescOrd);
		assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER IN DESCENDING ORDER ------> " + beforeReversing,
				Utility.checkIfSorted(referralsSortedByDescOrd));

	}

	public void scrollThroughTheList(String existingClerk) {

		Page.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("order", "next");
		params.put("offset", 0.1);
		params.put("element", ((RemoteWebElement) dropDown).getId());

		if (existingClerk.equals("")) {
			js.executeScript("mobile: selectPickerWheelValue", params);
		} else {
			Boolean elementNotFound = true;
			while (elementNotFound) {

				js.executeScript("mobile: selectPickerWheelValue", params);
				if (dropDown.getText().trim().equals(existingClerk)) {
					elementNotFound = false;

					break;

				} else {
					elementNotFound = true;

				}
			}
		}
		done.click();
	}

	public void selectExistingClerk(String existingClerk) {
		dashboard.click();
		scrollUp();

		int toolSize = tools.size();
		if (CommonPages.siVal.equalsIgnoreCase("n")) {
			assertTrue("The left nav displays the Tools category, even when briefcaseDisplayTools is set to 'n'. ",
					toolSize == 0);
		} else {
			tools.get(0).click();
			exsitingClerkBtn.click();
			scrollThroughTheList(existingClerk);
		}

	}

	public List<String> getListOfLwks(int i, List<UserInputData> userInputData) {
		String pe_id = DocumentPage.get_pe_id("jud", userInputData);
		return execute(getID(DUPLICATED_LWK, pe_id), i, userInputData);
	}

	public void duplicateAssignments(List<UserInputData> userInputData) {
		try {
			int index = 0;

			List<String> list = new ArrayList<>();

			List<String> pr_first_name = getListOfLwks(2, userInputData);
			List<String> pr_last_name = getListOfLwks(3, userInputData);
			List<String> cav_display = getListOfLwks(4, userInputData);
			List<String> cyv_display = getListOfLwks(5, userInputData);
			List<String> casenum = getListOfLwks(6, userInputData);

			for (int i = 0; i < pr_first_name.size(); i++) {
				list.add(cav_display.get(i).trim() + ", " + cyv_display.get(i).trim() + ", " + casenum.get(i).trim());

			}

			List<String> duplicates = findDuplicates(list);

			int dupSize = duplicates.size();

			if (dupSize >=1) {
				index = Utility.getRandomNumberInRange(0, dupSize - 1);
				int lwk1 = Integer.parseInt(duplicates.get(index).split(" ")[0]);
				int lwk2 = Integer.parseInt(duplicates.get(index).split(" ")[1]);

				String lwkName1 = pr_first_name.get(lwk1).trim() + " " + pr_last_name.get(lwk1).trim();
				String lwkName2 = pr_first_name.get(lwk2).trim() + " " + pr_last_name.get(lwk2).trim();

				selectExistingClerk(lwkName1);
				tapIndividualClerk(casenum.get(lwk1).trim(), cyv_display.get(lwk1).trim(),
						cav_display.get(lwk1).trim());
				scrollThroughTheList(lwkName2);

				submit.click();

				boolean duplicatedAssignmentMsg = driver.getPageSource()
						.contains("Duplicated Assignment found for " + lwkName2);

				assertTrue("Verify that user is prevented from creating duplicate assignments" + lwkName1 + " : "
						+ lwkName2, duplicatedAssignmentMsg);

			} else {
				throw new RuntimeException(
						"Ensure there is law clerk assignments of a specific type in a specific referral, but assigned to two different law clerks, lwk list: "
								+ pr_first_name + " " + pr_last_name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> findDuplicates(List<String> list) {
		List<String> duplicates = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {

				if (list.get(i).equals(list.get(j))) {

					duplicates.add(i + " " + j);

				}
			}

		}
		return duplicates;
	}

	public void tapIndividualClerk(String caseNum, String cyv_display, String cav_display) {

		Utility.scrollDownIfNotDisplayed("//XCUIElementTypeStaticText[contains(@name, '" + caseNum + "')]/preceding::"
				+ "XCUIElementTypeStaticText[contains(@name, '" + cyv_display + "')]/preceding::"
				+ "XCUIElementTypeStaticText[contains(@name, '" + cav_display
				+ "')]/preceding::XCUIElementTypeButton[1]");

	}

}
