package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getCyv_code;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIAL;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_VOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_VOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGE_VOTE_DPF_RELIEF;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElements;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getStreamOfRandomInts;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.sendKeys;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_JudgeVoteDPFPage {
	public iOS_JudgeVoteDPFPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "//*[contains(@name, 'Close')]")
	public static MobileElement close;

	@iOSFindBy(xpath = "//XCUIElementTypeTextView[2]")
	public static MobileElement commentField;
	
	@iOSFindBy(xpath = "//*[contains(@name, 'Apply')]")
	public static MobileElement applyBtn;

	public void selectAction(String action) {
		clickOnPanel(ACTIONS, action);
	}

	/** verify relief is displayed on the popup page */
	public void selectViewVotes(DBType dbType, String ccr_id, String viewVotes) {

		String relief = getRelief(dbType, JUDGE_VOTE_DPF_RELIEF, ccr_id);

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
		String reliefText = getAllColumns(dbtype, getID(Queries.JUDGE_VOTE_DPF_RELIEF, ccr_id));

		List<String> dbInitials = executeQuery(dbtype, getText(getID(initials, ccr_id), reliefText));
		sort(dbInitials);

		/** get judge's initials */

		List<String> dbInitial = executeQuery(dbtype, getText(getID(initial, ccr_id), reliefText));
		sort(dbInitial);
		try {
			/** verify all initials are displayed */

			for (int inits = 0; inits < dbInitials.size(); ++inits) {
				MobileElement uiJudgeInitis = waitForElement(findElement(By
						.xpath("//XCUIElementTypeOther[@name='JudgesVotesList_Container']/child::*//*[contains(@name, '"
								+ dbInitials.get(inits) + "')]")));
				assertTrue(uiJudgeInitis.isDisplayed());
			}

			for (int init = 0; init < dbInitial.size(); ++init) {

				/** get judge's current vote */

				List<String> dbVote = executeQuery(dbtype,
						getCyv_code(getText(getID(votes, ccr_id), reliefText), dbInitial.get(init)));
				sort(dbVote);

				for (int vote = 0; vote < dbVote.size(); ++vote) {

					/** get vote date */

					List<String> dbVoteDate = executeQuery(dbtype,
							getCyv_code(getText(getID(voteDates, ccr_id), reliefText), dbInitial.get(init)));
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

	public void getVoteSelection(DBType dbType, String ccr_id) {
		String reliefText = getRelief(dbType, JUDGE_VOTE_DPF_RELIEF, ccr_id);
		click("//XCUIElementTypeStaticText[contains(@name, '" + reliefText
				+ "')]/preceding-sibling::XCUIElementTypeStaticText[2]");

		List<MobileElement> votes = findElements(
				By.xpath("//XCUIElementTypeTable[@name='VoteOptions']/XCUIElementTypeCell"));
		for (int i = 0; i < votes.size(); i++) {
			MobileElement vote = votes.get(0);
			vote.click();

			click("//XCUIElementTypeStaticText[contains(@name, '" + reliefText
					+ "')]/preceding-sibling::XCUIElementTypeStaticText[3]");

			
			commentField.clear();
			sendKeys(commentField, getStreamOfRandomInts());

		}

	}

	public String getRelief(DBType dbType, String query, String ccr_id) {
		return getAllColumns(dbType, getID(query, ccr_id));
	}

}
