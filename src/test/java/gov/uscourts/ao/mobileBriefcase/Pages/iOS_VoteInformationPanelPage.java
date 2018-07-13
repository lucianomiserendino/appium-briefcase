package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILED_DATE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.FILERs_INFORMATION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGEs_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGEs_VOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_EVENT;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.RELIEF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.VOTE_DATE;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users.APPELLATE_JUDGES;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users.COLLOTON_STEVEN;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getColumnCount;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.voteInformationPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.PETITIONS_FOR_REHEARING;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.RLW;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.RWG;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SMC;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.assertThatDBEqualsToUI;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getCollapsablePanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getUserCategory;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCaseNumber;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_VoteInformationPanelPage {

	public iOS_VoteInformationPanelPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	String back = "Back";
	static int filer = 2;
	static int voteInfo = 3;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	public void selectAJudje() {
		getUserCategory(APPELLATE_JUDGES, selectUser, COLLOTON_STEVEN);

	}

	public void selectCase(String caseNumber) {

		performPageLoad();
		selectCaseNumber(PETITIONS_FOR_REHEARING, caseNumber);

	}

	/** Observe the Vote Information Panel displays */
	public void getVoteInformationPanel(String element) {

		getPanel(MBR_EVENT, "VOTE INFORMATION PANEL IS NOT DISPLAYED", element);

	}

	
	
	/** Observers filer's information */
	public void getFilersInformation() {

		String dbFilersInformation = getAllColumns(DBType.CMKA,FILERs_INFORMATION).replaceAll(" ", "");
		performPageLoad();
		String uiFilersInformation = uiFilresInformation();

		assertTrue("----FILER'S INFORMATION MISMATCH----", dbFilersInformation.equals(uiFilersInformation));

		assertThatDBEqualsToUI("----FILED DATE MISMATCH----", FILED_DATE,
				Arrays.asList(getFiledDate(Actions.FILERs_INFORMATION)));

	}

	/** Verifies judge's initials */

	public void checkJudgesIntitials() {
		assertThatDBEqualsToUI("----JUDGE'S INITIALS MISMATCH----", JUDGEs_INITIALS,
				getColumnCount(SMC, filer, RLW, filer, RWG, filer));

	}

	public void verifyReliefIsDisplayed() {
		assertTrue(findElement(By.xpath(locateElement(getAllColumns(DBType.CMKA,RELIEF)))).isDisplayed());
	}

	public void getJudgesVoteInfo() {

		assertTrue(getAllColumns(DBType.CMKA,JUDGEs_VOTE).equals(voteInformationPanel(SMC, voteInfo)));

		assertTrue(getAllColumns(DBType.CMKA,VOTE_DATE).split(" ")[0]
				.equals(changeDateFormat(voteInformationPanel(RLW, voteInfo), "yyyy-MM-dd")));

		getCollapsablePanel(selectUser, APPELLATE_JUDGES);

	}

	public static String getFiledDate(Actions action) {
		return changeDateFormat(voteInformationPanel(action, filer).split(":")[1].trim(), "yyyy-MM-dd");

	}

	public static String uiFilresInformation() {
		return voteInformationPanel(Actions.FILERs_INFORMATION, filer).replace("(", "").replace(")", "")
				.replaceAll(",", "").split("Filed")[0].replaceAll(" ", "");

	}

}
