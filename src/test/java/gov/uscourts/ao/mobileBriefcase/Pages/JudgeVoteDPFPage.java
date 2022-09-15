package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIAL;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_VOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_VOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGE_VOTE_DPF_RELIEF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectAction;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.waitForVisibilityOfElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.clickOnNumberInRange;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getParameter;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getStreamOfRandomInts;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class JudgeVoteDPFPage extends AppiumPageFactory {

	String select = "Please Select";

	@iOSXCUITFindBy(id = "Close")
	public static MobileElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='NoteList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextView")
	public static MobileElement commentField;

	@iOSXCUITFindBy(id = "Apply")
	public static MobileElement applyBtn;

	@iOSXCUITFindBy(id = "Select All")
	public static MobileElement selectAll;

	@iOSXCUITFindBy(id = "Cut")
	public static MobileElement cut;

	@iOSXCUITFindBy(id = "Back")
	public static MobileElement back;

	@iOSXCUITFindBy(id = "Cancel")
	public static MobileElement cancel;

	@iOSXCUITFindBy(id = "Submit")
	public static MobileElement submit;

	// @WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(id = "Yes")
	public static MobileElement yesBtn;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(id = "OK")
	public static MobileElement okBtn;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public static MobileElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VoteOptions']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<MobileElement> judgeVotes;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeButton")
	public static List<MobileElement> doc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Configuration Error']")
	public static MobileElement configError;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Dismiss']")
	public static MobileElement dismiss;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PDF View']")
	public static MobileElement pdf;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Page Label']")
	public static MobileElement pageLabel;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'The requested document cannot be displayed at this time. Invalid Document: dls')]")
	public static List<MobileElement> jpgMessage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeAlert[@name='Briefcase']")
	public static MobileElement jpgAlert;

	/** verify relief is displayed on the popup page */
	public String selectViewVotes(List<UserInputData> userInputData, String caseNum) {

		String ccr_id = CommonPages.getCCRID(caseNum, userInputData);
		String relief = getRelief(ccr_id, userInputData);
		tap(Locator.XPATH, "(//XCUIElementTypeStaticText[@name='" + relief
				+ "']/following::XCUIElementTypeOther/XCUIElementTypeButton[@name='View Votes'])[1]");
		return relief;

	}

	/** verify each judge's vote and the day they voted on the popup page */
	public void verifyJudgesVote(List<UserInputData> userInputData, String caseNum) {
		performPageLoad(driver);
		getJudgesInitials(JUDGES_INITIALS, JUDGES_INITIAL, JUDGES_VOTE, JUDGES_VOTE_DATE, userInputData, caseNum);

	}

	public static void getJudgesInitials(String initials, String initial, String votes, String voteDates,
			List<UserInputData> userInputData, String caseNum) {

		String ccr_id = CommonPages.getCCRID(caseNum, userInputData);

		String reliefText = getRelief(ccr_id, userInputData);

		List<String> dbInitials = executeQuery(replace(getID(initials, ccr_id), "RL_LIST_TEXT", reliefText),
				userInputData);
		sort(dbInitials);

		/** get judge's initials */

		List<String> dbInitial = executeQuery(replace(getID(initial, ccr_id), "RL_LIST_TEXT", reliefText),
				userInputData);
		sort(dbInitial);

		/** verify all initials are displayed */

		for (int inits = 0; inits < dbInitials.size(); ++inits) {

			MobileElement uiJudgeInits = waitForVisibilityOfElement(findElementBy(Locator.XPATH,
					"//XCUIElementTypeOther[@name='JudgesVotesList']/child::*//*[contains(@name, '"
							+ dbInitials.get(inits) + "')]"),
					driver);

			assertTrue(uiJudgeInits.isDisplayed());
		}

		for (int init = 0; init < dbInitial.size(); ++init) {

			/** get judge's current vote */

			List<String> dbVote = executeQuery(

					replace(getID(votes, ccr_id), "RL_LIST_TEXT", reliefText, "JU_INITIALS", dbInitial.get(init)),
					userInputData);
			sort(dbVote);

			for (int vote = 0; vote < dbVote.size(); ++vote) {

				/** get vote date */

				List<String> dbVoteDate = executeQuery(replace(getID(voteDates, ccr_id), "RL_LIST_TEXT", reliefText,
						"JU_INITIALS", dbInitial.get(init)), userInputData);

				sort(dbVoteDate);

				for (int uiVoteDate = 0; uiVoteDate < dbVoteDate.size(); ++uiVoteDate) {

					String votedDate = changeDateFormat(dbVoteDate.get(uiVoteDate).split(" ")[0], "yyyy-MM-dd",
							"M/d/yyyy");

					MobileElement uiResult = waitForVisibilityOfElement(findElementBy(Locator.XPATH,
							"//XCUIElementTypeOther[@name='JudgesVotesList']/child::*//*[contains(@name, '"
									+ dbInitial.get(init) + "')]"
									+ "/following::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, '"
									+ dbVote.get(vote)
									+ "')]/following::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, '"
									+ votedDate + "')]"),
							driver);
					assertTrue(uiResult.isDisplayed());
				}
			}
		}

		tap(close);
	}

	@SuppressWarnings("unlikely-arg-type")
	public String getVoteSelection(String dpfName, List<UserInputData> userInputData, String caseNum) {

		String elId = getAllColumns(getID(Queries.EL_ID, "Auto Test"), userInputData);

		String ccr_id = CommonPages.getCCRID(caseNum, userInputData);

		String voteText = "";
		String text = "";
		String reliefText = getRelief(ccr_id, userInputData);

		if (isDisplayed(Locator.XPATH, getIndexOfVoteButton(reliefText, 1)) == true) {
			tap(Locator.XPATH, getIndexOfVoteButton(reliefText, 1));
		} else {
			tap(back);
			CommonPages.getActionName(getAllColumns(getID(ACTION_NAME, elId), userInputData));
			tap(Locator.XPATH, getIndexOfVoteButton(reliefText, 1));
		}
		Page.sleep(4000);
		List<MobileElement> votes = judgeVotes;

		String voteList = "";
		Iterator<MobileElement> i = votes.iterator();
		while (i.hasNext()) {
			MobileElement row = i.next();
			voteList += row.getText();
		}

		if (voteList.contains(select)) {
			try {
				votes.remove(select);

			} catch (NoSuchElementException e) {
				e.getMessage();
			}

			voteText += clickOnNumberInRange(votes);
			text += getVoteName(voteText, reliefText);

		} else {
			voteText += clickOnNumberInRange(votes);
			text += getVoteName(voteText, reliefText);
		}

		tap(Locator.XPATH, getIndexOfNoteIcon(reliefText));

		addVote(dpfName, getID(MBR_NOTE, elId), reliefText, elId, userInputData);
		tap(back);
		Utility.scroll(By.id("DocumentList"), "up");
		return text;
	}

	public String getVoteName(String voteText, String reliefText) {
		if (voteText.equals("No Change")) {
			return getText(Locator.XPATH, getIndexOfVoteButton(reliefText, 1));
		} else {
			return voteText;
		}
	}

	/**
	 * After adding vote to a note, this will verify judge's vote is updated in Vote
	 * Information Panel
	 */
	public void verifyNoteText(String voteText, String noteText, List<UserInputData> userInputData, String caseNum) {
		String ccr_id = CommonPages.getCCRID(caseNum, userInputData);

		CommonPages.getPanel(Panel.valueOf("Vote_Information"));
		String relief = getRelief(ccr_id, userInputData);
		getVote(relief).click();
		performPageLoad(driver);
		assertTrue(isDisplayed(Locator.XPATH, containsElement("$$")));
		String title = getAllColumns(Queries.DM_DESCRIPTION, userInputData);
		assertTrue(isDisplayed(Locator.XPATH, containsElement(title)));
		ifDocumentAccessbile();
		tap(close);

	}

	public static void ifDocumentAccessbile() {

		String docName = "";
		if (doc.size() > 0) {
			docName = clickOnNumberInRange(doc);

			if (docName.equalsIgnoreCase("pdf") & (docName.equalsIgnoreCase("doc"))) {
				performPageLoad(driver);

				if (Actions.isDisplayed(configError) == true) {
					dismiss.click();
				}
				assertTrue(isDisplayed(pdf));
				assertTrue(isDisplayed(pageLabel));

			} else if (docName.equalsIgnoreCase("jpg")) {

				assertTrue(driver.getPageSource()
						.contains("The requested document cannot be displayed at this time. Invalid Document: dls"));

			}

		}
	}

	public void addVote(String dpfName, String query, String relief, String el_id, List<UserInputData> userInputData) {
		String text = "";
		if (getParameter(getAllColumns(query, userInputData), dpfName, 4).equals("SKIP")) {
			assertNull(" THE \"NOTE HISTORY PARAMETER\" IS NOT SET TO \"SKIP\" ", commentField.getText());
			tap(cancel);
		} else {
			Page.sleep(3000);

			commentField.click();
			commentField.clear();

			text = sendANote();
			tap(applyBtn);

			scrollDownIfNotDisplayed("//XCUIElementTypeButton[@name='Submit']");

			performPageLoad(driver);
			Utility.scroll(By.id("DocumentList"), "up");
			getGroupIcons();
			selectAction("Actions", el_id, userInputData);
			performPageLoad(driver);
			tap(Locator.XPATH, getIndexOfNoteIcon(relief));
			performPageLoad(driver);
			assertEquals(
					" THE \"NOTE HISTORY PARAMETER\" IS SET TO \"Y\", HOWEVER THE TEXT OF THE PREVIOUS VOTE NOTE IS NOT DISPLYED CORRECTLY! ",
					text, getText(commentField));
			tap(cancel);

		}

	}

	public static MobileElement getVote(String relief) {

		return findElement(By.xpath("(//XCUIElementTypeStaticText[@name='" + relief
				+ "']/following::XCUIElementTypeOther[contains(@name, 'NoteIcon')])[1]"));

	}

	public static String sendANote() {
		String note = "$$";
		sendKeys(commentField, note);
		return note;

	}

	public static String getTodaysDate() {
		return "TEST-" + changeDateFormat(getStreamOfRandomInts().split(" ")[0], "yyyy/MM/dd", "MM/dd/yyyy");
	}

	public static String getRelief(String ccr_id, List<UserInputData> userInputData) {
		return getAllColumns(getID(JUDGE_VOTE_DPF_RELIEF, ccr_id), userInputData);

	}

	public static String getIndexOfVoteButton(String relief, int index) {
		return "(//XCUIElementTypeStaticText[@name='" + relief
				+ "']/following::XCUIElementTypeOther/XCUIElementTypeButton)[" + index + "]";

	}

	public static String getIndexOfNoteIcon(String relief) {
		return "(//XCUIElementTypeStaticText[@name='" + relief
				+ "']/following::XCUIElementTypeOther/XCUIElementTypeButton[@name='View Votes'][1]"
				+ "/following::XCUIElementTypeOther//XCUIElementTypeStaticText)[1]";

	}

	public void verifyCourtAdminAccess() {
		getGroupIcons();
		assertFalse(
				"SITE TABLE VARIABLE \"BRIEFCASECTADMINDKT\" IS SET TO 'N', HOWEVER COURT ADMINS CAN SEE ACTIONS IN BRIEFCASE",
				isDisplayed(Locator.XPATH, containsElement("Actions")));
	}

}