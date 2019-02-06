package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIAL;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_VOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_VOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGE_VOTE_DPF_RELIEF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectAction;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForVisibilityOfElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.clickOnNumberInRange;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getParameter;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getStreamOfRandomInts;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_JudgeVoteDPFPage extends AppiumPageFactory {

	String select = "Please Select";

	@iOSFindBy(id = "Close")
	public static MobileElement close;

	@iOSFindBy(xpath = "//XCUIElementTypeTextView[2]")
	public static MobileElement commentField;

	@iOSFindBy(id = "Apply")
	public static MobileElement applyBtn;

	@iOSFindBy(id = "Select All")
	public static MobileElement selectAll;

	@iOSFindBy(id = "Cut")
	public static MobileElement cut;

	@iOSFindBy(id = "Back")
	public static MobileElement back;

	@iOSFindBy(id = "Cancel")
	public static MobileElement cancel;

	@iOSFindBy(id = "Submit")
	public static MobileElement submit;

	@WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSFindBy(id = "Yes")
	public static MobileElement yesBtn;

	@WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSFindBy(id = "OK")
	public static MobileElement okBtn;

	@WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTable[@name='nav']/XCUIElementTypeCell[2]")
	public static MobileElement dashboard;

	/** verify relief is displayed on the popup page */
	public String selectViewVotes(DBType dbType, String ccr_id, String viewVotes) {

		String relief = getRelief(dbType, ccr_id);
		tap(Locator.XPATH, "//XCUIElementTypeStaticText[contains(@name, '" + relief
				+ "')]/preceding-sibling::XCUIElementTypeStaticText[contains(@name, '" + viewVotes + "')]");
		return relief;

	}

	/** verify each judge's vote and the day they voted on the popup page */
	public void verifyJudgesVote(DBType dbType, String ccr_id) {
		performPageLoad(driver);
		getJudgesInitials(dbType, ccr_id, JUDGES_INITIALS, JUDGES_INITIAL, JUDGES_VOTE, JUDGES_VOTE_DATE);

	}

	public static void getJudgesInitials(DBType dbtype, String ccr_id, String initials, String initial, String votes,
			String voteDates) {
		String reliefText = getRelief(dbtype, ccr_id);

		List<String> dbInitials = executeQuery(dbtype, replace(getID(initials, ccr_id), "RL_LIST_TEXT", reliefText));
		sort(dbInitials);

		/** get judge's initials */

		List<String> dbInitial = executeQuery(dbtype, replace(getID(initial, ccr_id), "RL_LIST_TEXT", reliefText));
		sort(dbInitial);
		try {
			/** verify all initials are displayed */

			for (int inits = 0; inits < dbInitials.size(); ++inits) {

				MobileElement uiJudgeInits = waitForVisibilityOfElement(findElementBy(Locator.XPATH,
						"//XCUIElementTypeOther[@name='JudgesVotesList_Container']/child::*//*[contains(@name, '"
								+ dbInitials.get(inits) + "')]"),
						driver);

				assertTrue(uiJudgeInits.isDisplayed());
			}

			for (int init = 0; init < dbInitial.size(); ++init) {

				/** get judge's current vote */

				List<String> dbVote = executeQuery(dbtype,

						replace(getID(votes, ccr_id), "RL_LIST_TEXT", reliefText, "JU_INITIALS", dbInitial.get(init)));
				sort(dbVote);

				for (int vote = 0; vote < dbVote.size(); ++vote) {

					/** get vote date */

					List<String> dbVoteDate = executeQuery(dbtype, replace(getID(voteDates, ccr_id), "RL_LIST_TEXT",
							reliefText, "JU_INITIALS", dbInitial.get(init)));

					sort(dbVoteDate);
					for (int uiVoteDate = 0; uiVoteDate < dbVoteDate.size(); ++uiVoteDate) {

						String votedDate = changeDateFormat(dbVoteDate.get(uiVoteDate).split(" ")[0], "yyyy-MM-dd",
								"M/d/yyyy");

						MobileElement uiResult = waitForVisibilityOfElement(findElementBy(Locator.XPATH,
								"//XCUIElementTypeOther[@name='JudgesVotesList_Container']/child::*//*[contains(@name, '"
										+ dbInitial.get(init)
										+ "')]/preceding-sibling:: XCUIElementTypeStaticText[contains(@name, '"
										+ dbVote.get(vote)
										+ "')]/following-sibling:: XCUIElementTypeStaticText[contains(@name, '"
										+ votedDate + "')]"),
								driver);
						assertTrue(uiResult.isDisplayed());
					}
				}
			}
		} catch (org.openqa.selenium.TimeoutException e) {
			e.printStackTrace();
		} finally {
			tap(close);
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	public String getVoteSelection(DBType dbType, String ccr_id, String elId) {
		String voteText = "";
		String reliefText = getRelief(dbType, ccr_id);
		if (isDisplayed(Locator.XPATH, getIndexOf(reliefText, 2)) == true) {
			tap(Locator.XPATH, getIndexOf(reliefText, 2));
		} else {
			tap(back);
			CommonPages.getActionName(getAllColumns(dbType, getID(ACTION_NAME, elId)));
			tap(Locator.XPATH, getIndexOf(reliefText, 2));
		}
		List<MobileElement> votes = driver.findElements(
				By.xpath("//XCUIElementTypeTable[@name='VoteOptions']/XCUIElementTypeCell/XCUIElementTypeStaticText"));
		String voteList = "";
		Iterator<MobileElement> i = votes.iterator();
		while (i.hasNext()) {
			MobileElement row = i.next();
			voteList += row.getText();
		}
		if (voteList.contains(select) || voteList.contains("No Change")) {
			try {
				votes.remove(select);
				votes.remove("No Change");
			} catch (NoSuchElementException e) {
				e.getMessage();
			}
			voteText += clickOnNumberInRange(votes);
		} else {
			voteText += clickOnNumberInRange(votes);
		}
		tap(Locator.XPATH, getIndexOf(reliefText, 3));

		addVote(dbType, getID(MBR_NOTE, elId), reliefText, elId);
		tap(dashboard);
		return voteText;
	}

	/**
	 * After adding vote to a note, this will verify judge's vote is updated in Vote
	 * Information Panel
	 */
	public void verifyNoteText(DBType dbType, String ccr_id, String voteText, String noteText) {
		performPageLoad(driver);
		contains("Vote Information").click();
		performPageLoad(driver);
		String relief = getRelief(dbType, ccr_id);
		if (isDisplayed(Locator.XPATH, containsElement(relief)) == false) {
			contains("Vote Information").click();
		}
		assertTrue(getVote(relief, voteText, 1).isDisplayed());

		getVote(relief, voteText, 2).click();
		performPageLoad(driver);
		assertTrue(isDisplayed(Locator.XPATH, containsElement(getTodaysDate())));
		String title = getAllColumns(dbType, DM_DESCRIPTION);
		assertTrue(isDisplayed(Locator.XPATH, containsElement(title)));
		tap(cancel);

	}

	public void addVote(DBType dbType, String query, String relief, String el_id) {
		String text = "";
		if (getParameter(getAllColumns(dbType, query), 4).equals("SKIP")) {
			assertNull(" THE \"NOTE HISTORY PARAMETER\" IS NOT SET TO \"SKIP\" ", commentField.getText());
			tap(cancel);
		} else {
			try {
				if (getText(commentField).isEmpty()) {
					sendANote();
					tap(commentField);
				}
				tap(commentField);
				tap(commentField);
				tap(selectAll);
				tap(cut);
				text += sendANote();
				tap(applyBtn);
				tap(submit);
				try {
					tap(yesBtn);
					tap(okBtn);
				} catch (Exception e) {

					selectAction(dbType, "Actions", el_id);
					performPageLoad(driver);
					tap(Locator.XPATH, getIndexOf(relief, 3));
					assertEquals(
							" THE \"NOTE HISTORY PARAMETER\" IS SET TO \"Y\", HOWEVER THE TEXT OF THE PREVIOUS VOTE NOTE IS NOT DISPLYED CORRECTLY! ",
							text, getText(commentField));
					tap(cancel);
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

	}

	public static MobileElement getVote(String relief, String vote, int index) {
		return findElement(By
				.xpath("//*[contains(@name, 'Vote Information')]/following::XCUIElementTypeStaticText[contains(@name, '"
						+ relief + "')]/preceding-sibling::XCUIElementTypeStaticText[contains(@name, '" + vote
						+ "')]/following-sibling::XCUIElementTypeStaticText[" + index + "]"));
	}

	public static String sendANote() {
		String note = getTodaysDate();
		sendKeys(commentField, note);
		return note;

	}

	public static String getTodaysDate() {
		return "TEST-" + changeDateFormat(getStreamOfRandomInts().split(" ")[0], "yyyy/MM/dd", "MM/dd/yyyy");
	}

	public static String getRelief(DBType dbType, String ccr_id) {
		return getAllColumns(dbType, getID(JUDGE_VOTE_DPF_RELIEF, ccr_id));

	}

	public static String getIndexOf(String relief, int index) {
		return "//XCUIElementTypeStaticText[contains(@name, '" + relief
				+ "')]/preceding-sibling::XCUIElementTypeStaticText[" + index + "]";

	}

}