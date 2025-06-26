package gov.uscourts.ao.mobileBriefcase.page.common;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MULIPLE_DPFs;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SINGLE_DPF;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.verifyElementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.clickOnNumberInRange;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.splitBy;
import static java.util.Collections.sort;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.Pages.JudgeVoteDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.UIDocketingDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage;
import gov.uscourts.ao.mobileBriefcase.Pages.chmAssignDPFPage.chmAssign;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DPFs extends AppiumPageFactory {

	public static List<String> foundStrings;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Remove']/preceding::XCUIElementTypeStaticText[@name='Select']")
	public static List<WebElement> selectBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> proposedOrders;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Add New Note\"]/following::XCUIElementTypeStaticText[@name=\"Comment\"][1]/following::XCUIElementTypeTextView")
	public static WebElement noteCommentField;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Comment')]/preceding::XCUIElementTypeTextView")
	public static WebElement chmAssignCommentField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"NoteList\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextView")
	public static WebElement judgeVoteCommentField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='View Votes']/preceding:: XCUIElementTypeButton[1]")
	public static List<WebElement> selectVoteBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"VoteOptions\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<WebElement> judgeVotes;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"View Votes\"]/preceding:: XCUIElementTypeButton[1]/preceding:: XCUIElementTypeStaticText[1]")
	public static List<WebElement> reliefTxt;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Due')]/following::XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeButton")
	public static WebElement dueDate;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Description']")
	public static WebElement description;

	@iOSXCUITFindBy(id = "Cancel")
	public static WebElement cancelBtn;

	@iOSXCUITFindBy(id = "Apply")
	public static WebElement applyBtn;

	public String note = "Test-!@#<>$%^&*()_+|:?";

	public String submit = "//XCUIElementTypeStaticText[@name='Submit']";

	String select = "Please Select";

	static String comment = "Comment";
	static String apply = "Apply";
	static String cancel = "Cancel";
	static String close = "Close";
	static String randomProposedOrder="";

	public List<String> getListOfBriefcaseDPFs(String caseNumber, List<UserInputData> userInputData) {

		String cmr_id = CommonPages.getCMRID( userInputData);

		List<String> dbResult = executeQuery(getID(APPLICABLE_ACTIONS, cmr_id), userInputData);
		sort(dbResult);

		return dbResult;

	}

	public String getChmAssign(DPF dpf, String caseNumber, List<UserInputData> userInputData) {
		List<String> dpfList = getListOfBriefcaseDPFs(caseNumber, userInputData);
		String elFunction = "";
		String dpfType = "";

		switch (dpf) {
		case chmAssign:
		case chmSilentAssign:
		case note:
		case docWP:
		case judgeVote:
			elFunction = dpf.name();
			dpfType = "single";
			break;
		case multiple:
			dpfType = "multiple";
			break;
		default:
			throw new IllegalArgumentException("Unsupported DPF: " + dpf);
		}

		String result = "";
		boolean elementNotFound = true;
		int attemptCounter = 0; // Initialize counter

		// Loop until the element is found or counter exceeds dpfList size
		while (elementNotFound) {
			if (attemptCounter >= dpfList.size()) { // Check if counter exceeds dpfList size
				throw new RuntimeException("Element not found after " + dpfList.size() + " attempts.");
			}

			for (String dpfItem : dpfList) {
				dpfItem = dpfItem.trim();
				String dbResult;

				if ("single".equals(dpfType)) {
					System.out.println("Processing single assignment type...");
					dbResult = DBUtilities.getAllColumns(
							Actions.replace(SINGLE_DPF, "EL_FUNCTIONS", elFunction, "EL_LIST_TEXT", dpfItem),
							userInputData);

					// Check if the database result contains the required elFunction
					if (dbResult.contains(elFunction) && getParameter(dbResult) == 2) {
						result = dpfItem;
						elementNotFound = false;
						break;
					}
				} else {
					System.out.println("Processing multiple assignment type...");
					dbResult = DBUtilities.getAllColumns(Actions.replace(MULIPLE_DPFs, "EL_LIST_TEXT", dpfItem),
							userInputData);

					List<String> foundDPFs = Arrays.asList("chmAssign", "note", "docWP", "judgeVote");
					foundStrings = new ArrayList<>();

					for (String possibleString : foundDPFs) {
						if (dbResult.contains(possibleString)) {
							foundStrings.add(possibleString);
						}
					}

					if (foundStrings.size() == foundDPFs.size() || foundStrings.size() >= 2) {
						result = dpfItem;
						elementNotFound = false;
						break;
					}
				}
			}
			attemptCounter++;
		}
		return result;
	}

	public static int getParameter(String param) {
		return param.split(";").length;
	}

	public void executeMultipleDPFs(List<String> dpf, String actionName, String caseNum, String refCategory,
			String peId, String cmr_cyv_code, List<UserInputData> userInputData) {
		String elId = getAllColumns(getID(Queries.EL_ID, actionName), userInputData);

		for (String dpfItem : dpf) {
			switch (dpfItem) {
			case "docWP":
				executeDocWP();
				break;

			case "note":
				executeNotePage(elId, userInputData);
				break;

			case "judgeVote":
				executeJudgeVote(actionName, elId, userInputData);
				break;

			case "chmAssign":
				executeChmAssign(caseNum, refCategory, actionName, elId, peId, cmr_cyv_code, userInputData);
				break;

			default:
				throw new IllegalArgumentException("Unknown DPF: " + dpfItem);
			}
		}

		scrollDownIfNotDisplayed(submit);
		CommonPages.ifDownloaded(inProgress);
	}

	private void executeDocWP() {
		if (selectBtn.size() > 0) {
			selectBtn.get(0).click();
			 randomProposedOrder += Utility.clickOnNumberInRange(proposedOrders);
			CommonPages.ifDownloaded(inProgress);
			Actions.isDisplayed(Actions.contains(randomProposedOrder.trim()));
		}
	}

	private void executeNotePage(String elId, List<UserInputData> userInputData) {
		UIDocketingDPFPage page = new UIDocketingDPFPage();

		try {
			page.getDefaulDescription("note", elId, userInputData);
			if (noteCommentField.getText().isEmpty()) {
				noteCommentField.sendKeys(note);
			} else {
				throw new RuntimeException("Comment field is not empty or not editable.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("An error occurred while verifying fields are displayed and editable: " + e.getMessage());
		}
	}

	private void executeJudgeVote(String actionName, String elId, List<UserInputData> userInputData) {
		JudgeVoteDPFPage page = new JudgeVoteDPFPage();
		String voteText = "";
		String text = "";

		if (selectVoteBtn.size() > 0) {
			String relief = reliefTxt.get(0).getText();
			selectVoteBtn.get(0).click();

			Page.sleep(4000);
			List<WebElement> votes = judgeVotes;
			String voteList = votes.stream().map(WebElement::getText).collect(Collectors.joining());

			if (voteList.contains(select)) {
				votes.removeIf(vote -> vote.getText().equals(select));
			}

			voteText += clickOnNumberInRange(votes);
			text += page.getVoteName(voteText, relief);

			tap(Locator.XPATH, JudgeVoteDPFPage.getIndexOfNoteIcon(relief));

			if ("SKIP".equals(
					Utility.getParameter(getAllColumns(getID(MBR_NOTE, elId), userInputData), "judgeVote", 4))) {
				assertNull(" THE \"NOTE HISTORY PARAMETER\" IS NOT SET TO \"SKIP\" ", judgeVoteCommentField.getText());
				Actions.tap(cancelBtn);
			} else {
				Page.sleep(3000);
				judgeVoteCommentField.click();
				judgeVoteCommentField.clear();
				JudgeVoteDPFPage.sendANote();
				tap(applyBtn);
			}

			Actions.isDisplayed(Actions.contains(text));
		}
	}

	private void executeChmAssign(String caseNum, String refCategory, String actionName, String elId, String peId,
			String cmr_cyv_code, List<UserInputData> userInputData) {
		assertTrue(isDisplayed(containsElement("Assignments")));
		scrollDownIfNotDisplayed("(" + containsElement("NewStaffButton") + ")[1]");
		CommonPages.verifyElementIsDisplayed("Create Assignment");

		chmAssignDPFPage chmAssignPage = new chmAssignDPFPage();

		chmAssignPage.getCaseDetails(caseNum, refCategory, actionName, userInputData);

		chmAssignDPFPage.getChmAssign(chmAssign.STAFF_MEMBER, "tap");

		String staffMember = chmAssignDPFPage.getAvailableStaffMembers("chmAssign", elId, peId, userInputData);
		String staffFName = splitBy(staffMember, 0);
		String staffLName = splitBy(staffMember, 1);

		chmAssignDPFPage.getChmAssign(chmAssign.ASSIGNMENT, "tap");

		chmAssignDPFPage.getAssignmentType("chmAssign", elId, peId, caseNum, cmr_cyv_code, staffFName, staffLName,
				userInputData);

		chmAssignDPFPage.selectADate(chmAssign.ASSIGNED_DATE);

		if (Actions.isDisplayed(dueDate)) {
			chmAssignDPFPage.selectADate(chmAssign.ASSIGNMENT_DUE);
		}

		verifyElementIsDisplayed(cancel);

		chmAssignDPFPage.getChmAssign(chmAssign.ASSIGNMENT, "text");

		if (!chmAssignCommentField.getText().isEmpty()) {
			chmAssignCommentField.clear();
		}
		chmAssignCommentField.sendKeys("$*!@$%^&*():;?");
		contains(apply).click();
		CommonPages.ifDownloaded(inProgress);
	}

	public void dataIsSaved(String actionName) {
		Page.performPageLoad(driver);

	    Page.waitToBeClickable(contains(actionName), driver);
	    boolean pdfFound = isDocketEntryDisplayed(randomProposedOrder.trim());
	    boolean assignmentNoteFound = isDocketEntryDisplayed("Assignment Note");
	    boolean voteOrTransactionFound = isDocketEntryDisplayed("Judge Vote Note") || isDocketEntryDisplayed("Transaction Note");

	    boolean isDataSaved = pdfFound && assignmentNoteFound && voteOrTransactionFound;


	    assertTrue(
	        "Check if the " + actionName + " transaction was submitted successfully or if data is saved when executing multiple DPFs in a single action.",
	        isDataSaved);
	}



	private boolean isDocketEntryDisplayed(String docketEntryName) {
	    String xpath = String.format("//XCUIElementTypeStaticText[contains(@name, 'Documents')]/following::"
	            + "XCUIElementTypeStaticText[contains(@name, 'Docket Text')]/preceding::"
	            + "XCUIElementTypeStaticText[contains(@name, '%s')]", docketEntryName);

	    try {
	        // Check if the element is displayed
	        WebElement element = driver.findElement(By.xpath(xpath));
	        System.out.println("Found element for docket entry: " + docketEntryName);
	        return element.isDisplayed();
	    } catch (NoSuchElementException e) {
	        // Log the exception and continue
	        System.out.println("Element not found for docket entry: " + docketEntryName);
	        return false;
	    }
	}

	public enum DPF {
		chmAssign, chmSilentAssign, note, docWP, judgeVote, multiple
	}

}
