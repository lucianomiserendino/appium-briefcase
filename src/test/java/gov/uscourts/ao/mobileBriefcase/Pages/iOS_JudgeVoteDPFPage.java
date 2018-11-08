package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getCode;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIAL;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_VOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_VOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGE_VOTE_DPF_RELIEF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.VOTE_INFORMATION;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOnRandomValue;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElements;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getParameter;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getStreamOfRandomInts;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.navigateBack;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.splitBy;
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
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_JudgeVoteDPFPage extends AppiumPageFactory {

	String select = "Please Select";

	String backBtn = "Back";

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

	@iOSFindBy(id = "Submit")
	public static MobileElement submit;
	@iOSFindBy(id = "Cancel")

	public static MobileElement cancel;
	@iOSFindBy(id = "Yes")

	@WithTimeout(time = 20, unit = TimeUnit.SECONDS)
	public static MobileElement yesBtn;

	@WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSFindBy(id = "OK")
	public static MobileElement okBtn;

	public void selectAction(String action) {
		clickOnPanel(ACTIONS, action);
	}

	/** verify relief is displayed on the popup page */
	public void selectViewVotes(DBType dbType, String ccr_id, String viewVotes) {

		String relief = getRelief(dbType, ccr_id);
		click("//XCUIElementTypeStaticText[contains(@name, '" + relief
				+ "')]/preceding-sibling::XCUIElementTypeStaticText[contains(@name, '" + viewVotes + "')]");
		elementIsDisplayed(relief);

	}

	/** verify each judge's vote and the day they voted on the popup page */
	public void verifyJudgesInfo(DBType dbType, String ccr_id) {
		performPageLoad();
		getJudgesInitials(dbType, ccr_id, JUDGES_INITIALS, JUDGES_INITIAL, JUDGES_VOTE, JUDGES_VOTE_DATE);

	}

	public static void getJudgesInitials(DBType dbtype, String ccr_id, String initials, String initial, String votes,
			String voteDates) {
		String reliefText = getAllColumns(dbtype, getID(JUDGE_VOTE_DPF_RELIEF, ccr_id));

		List<String> dbInitials = executeQuery(dbtype, getText(getID(initials, ccr_id), reliefText));
		sort(dbInitials);

		/** get judge's initials */

		List<String> dbInitial = executeQuery(dbtype, getText(getID(initial, ccr_id), reliefText));
		sort(dbInitial);

		try {
			/** verify all initials are displayed */

			for (int inits = 0; inits < dbInitials.size(); ++inits) {
				MobileElement uiJudgeInits = waitForElement(findElement(By
						.xpath("//XCUIElementTypeOther[@name='JudgesVotesList_Container']/child::*//*[contains(@name, '"
								+ dbInitials.get(inits) + "')]")));

				assertTrue(uiJudgeInits.isDisplayed());
			}

			for (int init = 0; init < dbInitial.size(); ++init) {

				/** get judge's current vote */

				List<String> dbVote = executeQuery(dbtype,
						getCode(getText(getID(votes, ccr_id), reliefText), dbInitial.get(init)));
				sort(dbVote);

				for (int vote = 0; vote < dbVote.size(); ++vote) {

					/** get vote date */

					List<String> dbVoteDate = executeQuery(dbtype,
							getCode(getText(getID(voteDates, ccr_id), reliefText), dbInitial.get(init)));
					sort(dbVoteDate);

					for (int uiVoteDate = 0; uiVoteDate < dbVoteDate.size(); ++uiVoteDate) {

						String votedDate = changeDateFormat(dbVoteDate.get(uiVoteDate).split(" ")[0], "yyyy-MM-dd",
								"M/d/yyyy");

						MobileElement uiResult = waitForElement(findElement(By.xpath(
								"//XCUIElementTypeOther[@name='JudgesVotesList_Container']/child::*//*[contains(@name, '"
										+ dbInitial.get(init)
										+ "')]/preceding-sibling:: XCUIElementTypeStaticText[contains(@name, '"
										+ dbVote.get(vote)
										+ "')]/following-sibling:: XCUIElementTypeStaticText[contains(@name, '"
										+ votedDate + "')]")));

						assertTrue(uiResult.isDisplayed());
					}
				}
			}
		} catch (org.openqa.selenium.TimeoutException e) {
			e.printStackTrace();
		}

		finally {
			clickOn(close);
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public String getVoteSelection(DBType dbType, String ccr_id, String elId) {

		String reliefText = getRelief(dbType, ccr_id);

		click(getIndexOf(reliefText, 2));

		List<MobileElement> votes = findElements(
				By.xpath("//XCUIElementTypeTable[@name='VoteOptions']/XCUIElementTypeCell/XCUIElementTypeStaticText"));
		String voteList = "";

		Iterator<MobileElement> i = votes.iterator();
		while (i.hasNext()) {
			MobileElement row = i.next();
			voteList += row.getText();
		}
		if (voteList.contains(select)) {
			votes.remove(select);
			clickOnRandomValue(votes);
		} else {
			clickOnRandomValue(votes);
		}
		click(getIndexOf(reliefText, 3));

		return addVote(dbType, getID(MBR_NOTE, elId), reliefText, elId);

	}

	public String addVote(DBType dbType, String query, String relief, String el_id) {
		String text = "";
		if (getParameter(getAllColumns(dbType, query), 4).equals("SKIP")) {
			assertNull(" THE \"NOTE HISTORY PARAMETER\" IS NOT SET TO \"SKIP\" ", commentField.getText());
			clickOnElement("Cancel");
		} else {
			try {
				clickOn(commentField);
				clickOn(commentField);
				clickOn(selectAll);
				clickOn(cut);
				text += sendKeys(commentField,
						"TEST-" + changeDateFormat(getStreamOfRandomInts().split(" ")[0], "yyyy/MM/dd", "MM/dd/yyyy"));
				clickOn(applyBtn);
				clickOn(submit);
				clickOn(yesBtn);
				clickOn(okBtn);
				clickOnPanel(ACTIONS, getAllColumns(dbType, getID(ACTION_NAME, el_id)));
				click(getIndexOf(relief, 3));
				assertEquals(
						" THE \"NOTE HISTORY PARAMETER\" IS SET TO \"Y\", HOWEVER THE TEXT OF THE PREVIOUS VOTE NOTE IS NOT DISPLYED CORRECTLY! ",
						text, getText(commentField));
				clickOn(cancel);

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		return text;
	}

	public String getIndexOf(String relief, int index) {
		return "//XCUIElementTypeStaticText[contains(@name, '" + relief
				+ "')]/preceding-sibling::XCUIElementTypeStaticText[" + index + "]";
	}

	public static MobileElement getVoteNote(String voteNote) {

		return findElement(By.xpath(locateElement(voteNote) + "/preceding-sibling::XCUIElementTypeStaticText[2]"));

	}

	public static String sendKeys(MobileElement elements, String text) {
		elements.sendKeys(text);
		return text;
	}

	public String getRelief(DBType dbType, String ccr_id) {
		return getAllColumns(dbType, getID(JUDGE_VOTE_DPF_RELIEF, ccr_id));

	}

	public void selectVoteInfo(DBType dbType, String ccr_id, String note) {
		navigateBack(backBtn);
		getPanel(ACTIONS);
		getPanel(VOTE_INFORMATION);

		getNoteIcon(dbType, ccr_id);
		getNoteText(note);
	}

	public void getNoteIcon(DBType dbType, String ccr_id) {
		MobileElement note = getVoteNote(getRelief(dbType, ccr_id));
		if (note.isDisplayed() == true) {
			note.click();
		} else {
			try {
				getPanel(VOTE_INFORMATION);
				note.click();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	public void getNoteText(String note) {

		assertTrue(elementIsDisplayed(note));
		String a = splitBy(getText(note), 1);
		System.out.println(getText(note) + "****************");
		System.out.println(a + "***************splited date");
	}

}
