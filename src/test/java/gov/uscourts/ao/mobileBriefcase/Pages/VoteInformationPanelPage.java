package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.assertThatDBEqualsToUI;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.Actions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class VoteInformationPanelPage {

	public VoteInformationPanelPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	static int filer = 2;
	static int voteInfo = 3;

	public void selectCaseNumber(String caseNumber) {

		clickOn(findElement(By.xpath(selectReferralCategory(Actions.PETITIONS_FOR_REHEARING))));
		click(locateElement(caseNumber));

	}

	/** Observe the Vote Information Panel displays */
	public void getVoteInformationPanel(String element) {

		try {

			if (executeQuery(Queries.VOTE_INFORMATION).size() > 0)
				;
			assertTrue("-------VOTE INFORMATION PANEL IS NOT DISPLAYED---------", panelIsDisplayed(element));
			clickOnPanel(element);

		} catch (AssertionError e) {
			e.getStackTrace();
		}

	}

	/** Observers filer's information */
	public void getFilersInformation() {

		String dbFilersInformation = getAllColumns(Queries.FILERs_INFORMATION).replaceAll(" ", "");
		performPageLoad();
		String uiFilersInformation = replace();
		assertTrue("----FILER'S INFORMATION MISMATCH----", dbFilersInformation.equals(uiFilersInformation));

		assertThatDBEqualsToUI("----FILED DATE MISMATCH----", Queries.FILED_DATE,
				Arrays.asList(getFiledDate(Actions.FILERs_INFORMATION)));

	}

	/** Verifies judge's initials */

	public void checkJudgesIntitials() {
		assertThatDBEqualsToUI("----JUDGE'S INITIALS MISMATCH----", Queries.JUDGEs_INITIALS,
				getColumnCount(Actions.SMC, filer, Actions.RLW, filer, Actions.RWG, filer));

	}

	public void verifyReliefIsDisplayed() {
		assertTrue(findElement(By.xpath(locateElement(getAllColumns(Queries.RELIEF)))).isDisplayed());
	}
	
	
	public static String selectReferralCategory(Actions action) {

		String xpath = "//XCUIElementTypeTable[@name='Categories']/XCUIElementTypeCell";

		switch (action) {
		case PENDING_TASKS:
			xpath += "[1]";
			break;
		case PETITIONS_FOR_REHEARING:
			xpath += "[2]";
			break;

		case CASES_ON_CALENDAR:
			xpath += "[3]";
			break;
		case MOTIONS_PETITIONS:
			xpath += "[4]";
			break;
		case SCREENING_PANELS:
			xpath += "[5]";
			break;
		default:
			break;
		}
		return xpath;

	}

	public static String locateElement(String element) {
		return "//*[contains(@name, '" + element + "')]";
	}

	public static boolean panelIsDisplayed(String element) {
		return isDisplayed(By.xpath(locateElement(element)));

	}

	public static void clickOnPanel(String element) {
		if (panelIsDisplayed(element) == true)
			;
		click(locateElement(element));
	}

	public static String getVoteInformation(Actions action, int index) {

		String filer = "//XCUIElementTypeTable[@name='DocumentList']/XCUIElementTypeCell[" + index
				+ "]/XCUIElementTypeStaticText";

		switch (action) {
		case SMC:
			filer += "[1]";
			break;

		case RLW:
			filer += "[2]";
			break;

		case RWG:
			filer += "[3]";
			break;

		case FILERs_INFORMATION:
			filer += "[4]";
			break;
		default:
			break;
		}
		return filer;
	}

	public static List<String> getColumnCount(Actions judgeInitial, int init, Actions judgeVote, int vote,
			Actions voteDate, int date) {

		List<String> voteInf = new ArrayList<>();
		String smc = voteInformationPanel(judgeInitial, init);
		String rlw = voteInformationPanel(judgeVote, vote);
		String rwg = voteInformationPanel(voteDate, date);
		voteInf.add(smc);
		voteInf.add(rlw);
		voteInf.add(rwg);
		sort(voteInf);
		return voteInf;

	}

	public static String voteInformationPanel(Actions initial, int index) {
		return findElement(By.xpath(getVoteInformation(initial, index))).getText();
	}


	public static String getFiledDate(Actions action) {
		return changeDateFormat(voteInformationPanel(action, filer).split(":")[1].trim(), "yyyy-MM-dd");

	}

	public static String replace() {
		return voteInformationPanel(Actions.FILERs_INFORMATION, filer).replace("(", "").replace(")", "")
				.replaceAll(",", "").split("Filed")[0].replaceAll(" ", "");

	}

	
}
