package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getParameter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
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

	@iOSXCUITFindBy(accessibility = "Submit")
	public static WebElement submit;

	@iOSXCUITFindBy(accessibility = "Pending Clerk's Filing")
	public static WebElement PendingClerk;

	@iOSXCUITFindBy(accessibility = "Pending Clerk's Office")
	public static WebElement PendingClerkOffice;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"PendingTasksList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[contains(@value, '▷')]/following::XCUIElementTypeStaticText[2]")
	public static List<WebElement> categoryCount;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"PendingTasksList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[contains(@value, '▷')]/following::XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]")
	public static List<WebElement> pendingCategories;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='MasterNavPage']/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell")
	public static List<WebElement> navIcons;

	@iOSXCUITFindBy(accessibility = "GroupIcon")
	public static List<WebElement> GroupIcon;

	@iOSXCUITFindBy(xpath = "**/XCUIElementTypeStaticText[label == '▽'][2]")
	public static List<WebElement> GroupIcon2;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='ReferralsList']//XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> caseNum;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'NEW OUT OF')][1]/preceding:: XCUIElementTypeStaticText[1]")
	public static List<WebElement> pendingSubFolders;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Progress halted' or @name='In progress']")
	public static List<WebElement> activityIndicator;

	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;

	public static String category;
	public static String assignmentType;
	public static String referral = "";
	public static int subFolder;
	String elId = "";
	String dpfName = "chmSilentAssign";

	public String processSubFolder(int index) {
		WebElement folderElement = pendingSubFolders.get(index);
		String folder = folderElement.getText();
		folderElement.click();

		getGroupIcons(GroupIcons.Expand);

		return folder.trim();
	}

	public void verifyFolderSortedDescending(List<UserInputData> userInputData, String folder) {
		selectAssignmentType(folder);

		List<String> filedDates = Actions.findElements(By.xpath(Actions.containsElement(": "))).stream()
				.map(el -> el.getText().split(": ")[1]).collect(Collectors.toList());

		assertTrue("VERIFY " + folder + " CASES ARE SORTED BY DATE DESCENDING ORDER",
				Utility.checkDatesForDescOrder(filedDates, "M/d/yyyy"));

		assertTrue("VERIFY INFO IS DISPLAYED CORRECTLY ON PENDING TASKS PAGE",
				getReferralAssignmentInfo(userInputData, folder, assignmentType));
	}

	public void verifyAllFoldersSortedDescending(List<UserInputData> userInputData) {
		for (int i = 0; i < pendingSubFolders.size(); i++) {
			String folder = processSubFolder(i);
			verifyFolderSortedDescending(userInputData, folder);

			Actions.navigateBack();

		}
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

			boolean found = true;

			while (found) {
				List<WebElement> icons = Actions.findElements(By.xpath(
						"//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther"
								+ "//following::XCUIElementTypeStaticText[@name='" + category + "']"
								+ "/following::XCUIElementTypeStaticText[@name='GroupIcon']"));

				found = false;

				for (WebElement icon : icons) {
					if (icon.getAttribute("value").equals("▽")) {
						icon.click();
						found = true;
						break;
					}
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

			assignmentType = Actions
					.findElement(By.xpath(assinmentType(m) + "/preceding::XCUIElementTypeStaticText[1]")).getText()
					.trim();

			assignmentCount.get(c.indexOf(m)).click();

		} else {
			assignmentType = "Pending Clerk''s Filing";
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

		List<WebElement> cases = Actions.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name, '-')]"));

		List<String> list = extractSortedByCase(cases);

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

	private List<String> extractSortedByCase(List<WebElement> elements) {
		Pattern pattern = Pattern.compile("^\\d{2}-\\d{3,5} .+");

		List<WebElement> filtered = elements.stream().filter(el -> pattern.matcher(el.getAttribute("name")).matches())
				.collect(Collectors.toList());

		return Utility.retrieveAllReferrals(filtered, " ", 0);
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

		for (int i = 0; i < pendingSubFolders.size(); i++) {
			String folder = processSubFolder(i);

			List<WebElement> categoryName = pendingCategories;

			ArrayList<String> dashList = new ArrayList<String>();
			for (int y = 0; y < categoryName.size(); y++) {
				String pendingCategory = categoryName.get(y).getText().trim();
				dashList.add(pendingCategory);
			}

			tap(collapseBtn);

			ArrayList<String> navList = new ArrayList<String>();

			List<WebElement> navCells = navIcons;
			List<WebElement> navCategoryCells = navCells.subList(2, navCells.size());

			for (WebElement navCell : navCategoryCells) {
				List<WebElement> navTexts = navCell.findElements(By.xpath(".//XCUIElementTypeStaticText"));
				if (navTexts.isEmpty())
					continue;

				String navCategoryName = navTexts.get(0).getText().trim();

				// Skip "Tools" and "Bookmarked"
				if (navCategoryName.equals("Tools") || navCategoryName.equals("Bookmarked")) {
					continue;
				}
				navList.add(navCategoryName);
			}

			List<String> commonFromDashInOrder = dashList.stream().filter(navList::contains)
					.collect(Collectors.toList());

			List<String> commonFromNavInOrder = navList.stream().filter(dashList::contains)
					.collect(Collectors.toList());

			assertEquals(
					"The referral categories displayed in " + folder + " folder are not sorted "
							+ "in the same way as they are in the left-hand navigation",
					commonFromNavInOrder, commonFromDashInOrder);

			tap(collapseBtn);
			Actions.navigateBack();

		}
	}

	public void findAssingmenType(String actionName, List<UserInputData> userInputData) {
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

	public void verifyAssignmentDateType() {
		for (int i = 0; i < pendingSubFolders.size(); i++) {
			String folder = processSubFolder(i);
			
		       System.out.println("------------------------------------------------------");
		        System.out.println("Selected folder name: " + folder);
		        System.out.println("------------------------------------------------------");
		        
			selectAssignmentType(folder);

			List<UserInputData> userInputData = null;

			selectRandomCase();

			String cmr_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);
			String cmr_cs_caseid = CommonPages.getCaseID(referral, userInputData);

			String cmr_cyv_code = CommonPages.cmr_cyv_code(category, cmr_cs_caseid, userInputData).trim();

			AssignmentsPage assig = new AssignmentsPage();

			assig.getRecentAssignmentDate(userInputData, referral, cmr_ju_pe_id, cmr_cyv_code, assignmentType);

			Actions.navigateBack();

		}
	}

	public boolean validateChmSilentAssignment(String category, String assignmentType, String referral) {
		
      Actions.contains("Pending Tasks").click();
		
		MyAssignments.click();

		getGroupIcons(GroupIcons.Expand);

		Actions.tap(Locator.XPATH,
				"//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther//following::XCUIElementTypeStaticText[@name='"
						+ category + "']");

		boolean found = true;

		while (found) {
			List<WebElement> icons = Actions.findElements(By.xpath(
					"//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther"
							+ "//following::XCUIElementTypeStaticText[@name='" + category + "']"
							+ "/following::XCUIElementTypeStaticText[@name='GroupIcon']"));

			found = false;

			for (WebElement icon : icons) {
				if (icon.getAttribute("value").equals("▽")) {
					icon.click();
					found = true;
					break;
				}
			}
		}

		Actions.findElement(By
				.xpath("//XCUIElementTypeOther[@name='PendingTasksList']/XCUIElementTypeScrollView/XCUIElementTypeOther"
						+ "//following::XCUIElementTypeStaticText[@name='" + category + "']"
						+ "/following::XCUIElementTypeStaticText[@name='" + assignmentType + "']"))
				.click();

		return Actions.isDisplayed(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '" + referral + "')]");
	}

}
