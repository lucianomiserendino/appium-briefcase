package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.insertData;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.UPDATE_CHAMBERS_CASE_TO_REFERRAL;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_LoginPage.dashboard;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.sleep;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;

public class iOS_TerminateReferrals extends AppiumPageFactory {

	@FindBy(xpath = "//XCUIElementTypeOther[@name='JENIE Single Sign On']/XCUIElementTypeOther[6]/XCUIElementTypeTextField")
	public static WebElement userName;

	@FindBy(xpath = "//XCUIElementTypeOther[@name='JENIE Single Sign On']/XCUIElementTypeOther[7]/XCUIElementTypeSecureTextField")
	public static WebElement password;

	@FindBy(id = "SIGN ON")
	public static WebElement submButton;

	/** find the ccr_id for the referral */
	public static String getCMR_CCR_ID(String caseNum, String dbType, String peID, String cmr_cyv_code) {
		return getCMRID(valueOf(dbType), caseNum, peID, cmr_cyv_code);
	}

	/**
	 * set case_to_referral.ccr_date_end date field to terminate/un-terminate the
	 * referral
	 */
	public void terminateReferral(ReferralTermination term, String caseNum, String dbType, String peID,
			String cmr_cyv_code) {
		assertTrue("REFERRAL IS TERMINATED", isDisplayed(Locator.XPATH, containsElement(caseNum)));

		String ccr_date_end = "";
		switch (term) {
		case TERMINATE:
			ccr_date_end = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			break;

		case UN_TERMINATE:
			ccr_date_end = "NULL";
			break;
		default:
			break;
		}
		updateChambersCaseToReferralEndDate(dbType, caseNum, peID, ccr_date_end, cmr_cyv_code);

		tap(dashboard);

		runDataUpdater(getProperty("dataUpdater"));

	}

	public static void runDataUpdater(String ndaLink) {
		try {
			safariInstance();
			changeWindow("WEBVIEW");
			driver.get(ndaLink);
			performPageLoad(driver);
			sendKeys(userName, "s haenni", password, "Test2021!");
			submButton.click();
			sleep(20000);
			changeWindow("NATIVE");

			performPageLoad(driver);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static void updateChambersCaseToReferralEndDate(String dbType, String caseNum, String peID,
			String ccr_date_end, String cmr_cyv_code) {
		insertData(valueOf(dbType), getID(getText(UPDATE_CHAMBERS_CASE_TO_REFERRAL, ccr_date_end),
				getCMR_CCR_ID(caseNum, dbType, peID, cmr_cyv_code)));

	}

	public enum ReferralTermination {
		TERMINATE, UN_TERMINATE
	}
}
