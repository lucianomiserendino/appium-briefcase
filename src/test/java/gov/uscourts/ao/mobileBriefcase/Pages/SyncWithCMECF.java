package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;

import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.select;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectAUser;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;



import gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SyncWithCMECF {

	public SyncWithCMECF() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther[2]")
	public static MobileElement syncButton;

	@iOSFindBy(id = "Dashboard")
	public static MobileElement dashboard;

	@WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public MobileElement selectUser;

	
	
	
	
	public void selectUser() {
		selectAUser(Users.APPELLATE_JUDGES);
		verifyDashboardIsDisplayed(Users.APPELLATE_JUDGES, Users.BRIGHT_MYRON);
	}

	
	
	public void verifyDashboardIsDisplayed(Users category, Users user) {
		try {

			if (isDisplayed(dashboard) == false) {
				select(Users.BACK);
				clickOn(waitForElement(selectUser));
				selectAUser(category);
				selectAUser(user);

			} else {
				assertTrue(isDisplayed(dashboard));
			}

		} catch (Exception e) {

		}

	}

}
