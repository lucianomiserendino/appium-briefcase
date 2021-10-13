package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.insertData;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.UPDATE_CHAMBERS_CASE_TO_REFERRAL;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.split;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSBy;

public class TerminateReferrals extends AppiumPageFactory {

	@FindBy(name = "usernameEntered")
	public static WebElement userName;

	@FindBy(name = "password")
	public static WebElement password;

	@FindBy(id = "SUBMIT2")
	public static WebElement submButton;

	// @WithTimeout(time = 50, unit = TimeUnit.SECONDS)
	@iOSBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement totalNumOfNewReferrals;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public static MobileElement dashboard;

	/** find the ccr_id for the referral */
	public static String getCMR_CCR_ID(String caseNum, String dbType, String peID, String cmr_cyv_code) {
		return CommonPages.getCMRID(valueOf(dbType), "cmr_ccr_id", caseNum, peID, cmr_cyv_code);
	}

	/**
	 * set case_to_referral.ccr_date_end date field to terminate/un-terminate the
	 * referral
	 */
	public void terminateReferral(ReferralTermination term, String caseNum, String dbType, String peID,
			String cmr_cyv_code) {

		tap(dashboard);
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
	}

	public void runDataUpdater() {
		Base.getInstance(Driver.WEBRIVER);
		webDriver.get("https://cms-ecf-cmka.isso.dcn/cmecf/servlet/MobileBriefcaseDataUpdater");
		webDriver.findElement(By.xpath("//input[@name='usernameEntered']")).sendKeys("s haenni");
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test2022!");
		webDriver.findElement(By.xpath("//input[@type='submit']")).click();

		Page.sleep(10000);
		webDriver.quit();
		Base.getInstance(Driver.IOS);

		CommonPages page = new CommonPages();
		page.selectReferral("//XCUIElementTypeOther[@name='Categories']" + containsElement("Test Automation"));

	}

	public int getTotalNumOfReferrals() {
		return new Integer(split(totalNumOfNewReferrals.getText(), "T", 0).split(",")[1].trim());
	}

	public IOSDriver<MobileElement> tapp() {
		tap(dashboard);
		return driver;
	}

	public static void updateChambersCaseToReferralEndDate(String dbType, String caseNum, String peID,
			String ccr_date_end, String cmr_cyv_code) {
		insertData(valueOf(dbType), getID(getText(UPDATE_CHAMBERS_CASE_TO_REFERRAL, ccr_date_end),
				getCMR_CCR_ID(caseNum, dbType, peID, cmr_cyv_code)));

	}

	public void sendCredentials(String Username, String Password) {
		sendKeys(userName, Username, password, Password);
		submButton.click();

	}

	public enum ReferralTermination {
		TERMINATE, UN_TERMINATE
	}

	
}
