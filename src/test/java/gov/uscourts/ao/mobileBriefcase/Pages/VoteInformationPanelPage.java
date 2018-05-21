package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Panels.clickOnPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Panels.getJudgeInitials;
import static gov.uscourts.ao.mobileBriefcase.common.Panels.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Panels.panelIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Panels.selectReferralCategory;
import static gov.uscourts.ao.mobileBriefcase.common.Panels.voteInformationPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.assertThatDBEqualsToUI;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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

	public void checkJudgesIntitials() {
		assertThatDBEqualsToUI("----JUDGE'S INITIALS MISMATCH----", Queries.JUDGEs_INITIALS, getJudgeInitials(filer));

	}

	public void verifyReliefIsDisplayed() {
		assertTrue(findElement(By.xpath(locateElement(getAllColumns(Queries.RELIEF)))).isDisplayed());
	}

	public static String getFiledDate(Actions action) {
		return changeDateFormat(voteInformationPanel(action, filer).split(":")[1].trim(), "yyyy-MM-dd");

	}

	public static String replace() {
		return voteInformationPanel(Actions.FILERs_INFORMATION, filer).replace("(", "").replace(")", "")
				.replaceAll(",", "").split("Filed")[0].replaceAll(" ", "");

	}

	public void getJudgesVoteInfo() {

		System.out.println(voteInformationPanel(Actions.SMC, voteInfo));

	}

}
