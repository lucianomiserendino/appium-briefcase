package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getCellCount;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getParameter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
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

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> pendingSubFolders;

	public static String category;
	String assinmentType;
	String referral = "";
	String elId = "";
	String dpfName = "chmSilentAssign";
	

	public String getRandomSubFolder() {

		// WebElement randomFolder = pendingSubFolders.get(0);

		int random = Utility.getRandomNumberInRange(1, pendingSubFolders.size());
		WebElement randomFolder = pendingSubFolders.get(random - 1);

		String folder = randomFolder.getText();
		randomFolder.click();

		getGroupIcons(GroupIcons.Expand);

		return folder.trim();
	}

	public void sortedInDescendingOrder(List<UserInputData> userInputData) {
		String folder = getRandomSubFolder();

		selectAssignmentType(folder);

		ArrayList<String> filedDates = new ArrayList<String>();
		List<WebElement> date = Actions.findElements(By.xpath(Actions.containsElement(": ")));

		for (int i = 0; i < date.size(); i++) {
			String text = date.get(i).getText().split(": ")[1];
			filedDates.add(text);
		}
		assertTrue("VERIFY " + folder + " CASES ARE SORTED BY DATE DESCENDING ORDER",
				Utility.checkDatesForDescOrder(filedDates, "M/d/yyyy"));

		assertTrue(
				"VERIFY THE ASSIGNMENT TYPE, PANEL MEMBER INITIALS, DATE LABEL OF THE LATEST ASSIGNMENT DATE TYPE AND DATE ARE DISPLAYED CORRCETLY ON THE PENDING TASKS PAGE",
				getReferralAssignmentInfo(userInputData, folder, assinmentType));

	}

	public boolean getReferralAssignmentInfo(List<UserInputData> userInputData, String folder, String assignmenType) {
		selectRandomCase();
		String uiPanelMembersAndAssignedDate = Actions
				.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name, '" + referral
						+ "')]/following::XCUIElementTypeStaticText[1]"))
				.getText().trim();

		String dbPanelMembers = getAssignmentInfo("cmr_panel_members", category, referral, userInputData, folder,
				assignmenType);
		String chc_cha_id = getAssignmentInfo("chc_cha_id", category, referral, userInputData, folder, assignmenType);

		String assignmentDatetype = getDateAndAssignmentDatetype("cdv_description", chc_cha_id, userInputData);
		String assignmentDate = getDateAndAssignmentDatetype("ad.chd_date", chc_cha_id, userInputData);

		String replace = "";
		if (assignmentDatetype.contains("Date")) {
			replace = assignmentDatetype.replace("Date", "");
		} else {
			replace = assignmentDatetype;
		}

		String formatedDate = Utility.changeDateFormat(assignmentDate, "yyyy-MM-dd", "M/dd/yyyy");

		String dbPanelMembersAndAssignedDate = dbPanelMembers + " " + replace.trim() + ": " + formatedDate;

		String a1 = dbPanelMembersAndAssignedDate;
		String b = uiPanelMembersAndAssignedDate;

		a1 = a1.replace(" ", "");
		b = b.replace(" ", "");

		if (a1.equalsIgnoreCase(b)) {

			return true;
		} else {
			System.out.println("EXPECTED: " + uiPanelMembersAndAssignedDate);

			System.out.println("ACTUAL: " + dbPanelMembersAndAssignedDate);

			return false;
		}

	}

	public static String getAssignmentInfo(String col, String category, String caseNumber,
			List<UserInputData> userInputData, String folder, String assignmenType) {
		String peId = DocumentPage.get_pe_id("jud", userInputData);
		String caseId = CommonPages.getCaseID(caseNumber, userInputData);

		String cha_cav_code = "";
		if (folder.contains("Pending")) {
			cha_cav_code = "pdclkfl";

		} else {
			cha_cav_code = getAllColumns(Actions.replace(Queries.CAV_CODE, "TEXT", assignmenType), userInputData)
					.trim();
		}

		return getAllColumns(
				Actions.replace(Actions.replace(Queries.PENDING_TASKS_PANEL_MEMBERS, "CMR_JU_PE_ID", peId,
						"CMR_CS_CASEID", caseId, "CYV_CATEGORY", category), "FIELD", col, "CHA_CAV_CODE", cha_cav_code),
				userInputData);

	}

	public String getDateAndAssignmentDatetype(String col, String chc_cha_id, List<UserInputData> userInputData) {
		return getAllColumns(
				Actions.replace(Queries.PENDING_TASKS_ASSIGNED_DATE, "FIELD", col, "CHD_CHA_ID", chc_cha_id),
				userInputData);
	}

	public void selectAssignmentType(String folder) {

		List<Integer> count = new ArrayList<>();

		int s = categoryCount.size();

		for (int i = 0; i < s; i++) {

			count.add(Integer.parseInt(Actions.replace(categoryCount.get(i).getText(), "\\(", "", "\\)", "")));
		}

		Integer max = Collections.max(count);

		String categoryName = Actions.containsElement("(" + max.toString() + ")")
				+ "/preceding:: XCUIElementTypeStaticText[1]";

		category = Actions.findElementBy(Locator.XPATH, categoryName).getText().trim();

		Actions.tap(Locator.XPATH, categoryName);

		if (folder.equals("My Assignments") | folder.contains("Referrals Awaiting")) {

			int i;
			for (i = 0; i < max; i++) {

				List<WebElement> icons = Actions.findElements(By.xpath(
						"//XCUIElementTypeOther[@name=\"PendingTasksList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther//following::XCUIElementTypeStaticText[@name='"
								+ category + "']/following::XCUIElementTypeStaticText[@name=\"GroupIcon\"]"));

				if (icons.get(i).getAttribute("value").equals("▽")) {
					icons.get(i).click();
				} else {
					break;
				}
			}

			List<Integer> c = new ArrayList<>();

			List<WebElement> assignmentCount = Actions.findElements(By.xpath(assinmentCount()));

			int i1 = 0;
			for (WebElement element : assignmentCount) {
				String value = Actions.replace(element.getText(), "\\(", "", "\\)", "").trim();
				int intValue = Integer.parseInt(value);
				c.add(intValue);
				i1 += intValue;
				if (i1 >= max) {
					break;
				}
			}

			Integer m = Collections.max(c);

			assinmentType = Actions.findElement(By.xpath(assinmentType(m) + "/preceding::XCUIElementTypeStaticText[1]"))
					.getText();

			assignmentCount.get(c.indexOf(m)).click();

		}

	}

	public void clickOnCase() {
		WebElement ref = selectRandomCase();

		String uiPanelMembersAndAssignedDate = Actions
				.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name, '" + referral
						+ "')]/following::XCUIElementTypeStaticText[1]"))
				.getText().trim();

		ref.click();
	}

	public WebElement selectRandomCase() {

		Page.performPageLoad(driver);

		List<String> list = Utility
				.retrieveAllReferrals(Actions.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + category
						+ "']/following::XCUIElementTypeStaticText[contains(@name, '-')]")), " ", 0);

		/** This might change in 1.8 - AMB-3399 */
		assertFalse(
				"VERIFY IF THERE IS MORE THAN ONE REFERRAL IN THE SAME CATEGORY FOR A CASE, THE CASE IS DISPLAYED ONLY ONCE",
				Utility.hasDuplicates(list));

		int caseN = 0;
		if (list.size() > 1) {
			caseN = Utility.getRandomInt(list.size() - 1);

		} else {
			caseN = 0;
		}
		WebElement uiResult = findElementBy(Locator.XPATH,
				"//XCUIElementTypeStaticText[contains(@name, '" + list.get(caseN) + "')]");

		referral = list.get(caseN).split(" ")[0].trim();
		;

		Page.performPageLoad(driver);
		return uiResult;
	}

	public void getAssignmentCategories() {
		List<Integer> categories = new ArrayList<>();

		for (int i = 0; i < categoryCount.size(); i++) {
			categories.add(Integer.parseInt(Actions.replace(categoryCount.get(i).getText(), "\\(", "", "\\)", "")));
		}
		Integer max = Collections.max(categories);
		Actions.tap(Locator.XPATH, Actions.containsElement("(" + max.toString() + ")"));

	}

	public void leftNavAndPendingTasksCategoriesAreSorted() {

		String folder = getRandomSubFolder();
		getGroupIcons(GroupIcons.Expand);
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
	
	
	public void findAssingmenType(String actionName,List<UserInputData> userInputData ) {
		elId += getAllColumns(getID(Queries.EL_ID, actionName), userInputData);
		String el_functions = getParameter(getAllColumns(getID(MBR_NOTE, elId), userInputData), dpfName, 1);


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

	public String assinmentType(Integer m) {
		return "//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther//following::XCUIElementTypeStaticText[@name='"
				+ category + "']/following::XCUIElementTypeStaticText[contains(@name, '" + "(" + m.toString() + ")"
				+ "')][1]";
	}

	public String assinmentCount() {
		return "//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther//following::XCUIElementTypeStaticText[@name='"
				+ category + "']/following::XCUIElementTypeStaticText[contains(@name, '(')]";
	}

}
