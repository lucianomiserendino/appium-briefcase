package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGE_VOTE_DPF_RELIEF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.NON_ORALLY_ARGUED_CASES;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.clickOnNumberInRange;
import static java.util.Collections.sort;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class EnBancPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(id = "Categories")
	public static WebElement categories;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static WebElement total;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'EN BANC ')]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static List<WebElement> enBanc;

	private static String xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView/XCUIElementTypeOther//XCUIElementTypeStaticText";

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VoteOptions']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<WebElement> judgeVotes;

	@iOSXCUITFindBy(id = "Back")
	public static WebElement back;

	String select = "Please Select";


	public String getReffCategories(String pe_id, List<UserInputData> userInputData) {
		List<String> referralCategories = executeQuery(
				getID(replace(NON_ORALLY_ARGUED_CASES, "CMR_CYV_CODE", "lbrrpt"), pe_id), userInputData);
		sort(referralCategories);

		String caseNum = "";

		Boolean elementNotFound = true;

		while (elementNotFound) {

			for (int i = 0; i < referralCategories.size(); ++i) {

				Utility.scrollDownIfNotDisplayed(xpath + "[contains(@name, '" + referralCategories.get(i) + "')]");

				performPageLoad(driver);

				if (enBanc.size() > 0) {

					caseNum = DocumentPage.selectRandomCaseNumber(enBanc);

					elementNotFound = false;

					break;

				} else {
					dashboard.click();
					Utility.scroll(categories, "up");
					elementNotFound = true;
				}

			}

		}
		return caseNum;

	}

	public void selectRandomVote(List<UserInputData> userInputData, String caseNum) {
		String ccr_id = CommonPages.getCCRID(caseNum, userInputData);

		String voteText = "";
		String text = "";
		String reliefText = getRelief(ccr_id, userInputData);

		tap(Locator.XPATH, getIndexOfVoteButton(reliefText, 1));

		Page.sleep(4000);
		List<WebElement> votes = judgeVotes;

		String voteList = "";
		Iterator<WebElement> i = votes.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			voteList += row.getText();
		}

		if (voteList.contains(select)) {
			try {
				votes.remove(select);

			} catch (NoSuchElementException e) {
				e.getMessage();
			}

			voteText += clickOnNumberInRange(votes);
			text += ifNoVoteDisplayed(voteText, reliefText);

		} else {
			voteText += clickOnNumberInRange(votes);
			text += ifNoVoteDisplayed(voteText, reliefText);
		}

		tap(Locator.XPATH, getIndexOfNoteIcon(reliefText));

		tap(back);

		CommonPages.getPanel(Panel.valueOf("Vote_Information"));
		String relief = getRelief(ccr_id, userInputData);
		findElement(By.xpath("(//XCUIElementTypeStaticText[@name='" + relief
				+ "']/following::XCUIElementTypeOther[contains(@name, '" + text + "')])[1]")).click();
		performPageLoad(driver);
		assertTrue(isDisplayed(Locator.XPATH, containsElement("$$")));
		String title = getAllColumns(Queries.DM_DESCRIPTION, userInputData);
		assertTrue(isDisplayed(Locator.XPATH, containsElement(title)));

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
		getGroupIcons(GroupIcons.Expand);
		assertFalse(
				"SITE TABLE VARIABLE \"BRIEFCASECTADMINDKT\" IS SET TO 'N', HOWEVER COURT ADMINS CAN SEE ACTIONS IN BRIEFCASE",
				isDisplayed(Locator.XPATH, containsElement("Actions")));
	}

	public String ifNoVoteDisplayed(String voteText, String reliefText) {
		if (voteText.equals("No Change")) {
			return getText(Locator.XPATH, getIndexOfVoteButton(reliefText, 1));
		} else {
			return voteText;
		}
	}

}
