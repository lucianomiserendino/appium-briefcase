package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.select;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users.*;
import static gov.uscourts.ao.mobileBriefcase.common.Page.*;
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

public class SyncWithCMECFPage {

	public SyncWithCMECFPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeOther[3]/XCUIElementTypeButton")
	public static MobileElement syncButton;

	@iOSFindBy(id = "Dashboard")
	public static MobileElement dashboard;

	@WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public MobileElement selectUser;

	@iOSFindBy(id = "Sync With CM/ECF")
	public static MobileElement syncWithCMECF;

	@iOSFindBy(xpath = "//*[contains(@name, 'Pending download')]")
	public static MobileElement pendingDownload;

	public void selectAJudje() {
		getUser(APPELLATE_JUDGES);
	}

	public void selectUser(Users category, Users user) {
		selectAUser(user);
		verifyDashboardIsDisplayed(category, user);

	}

	public void verifySyncCompletes() {
		performPageLoad();
		clickOn(syncButton);

	}

	public void getUser(Users category) {
		switch (category) {
		case APPELLATE_JUDGES:
			selectUser(APPELLATE_JUDGES, Users.BRIGHT_MYRON);
			break;
		case STAFF_ATTORNEYS:
			selectUser(STAFF_ATTORNEYS, CALLOWAY_RENE);
			break;
		case BANKRUPTCY_JUDGES:
			selectUser(BANKRUPTCY_JUDGES, SALADINO_THOMAS);
			break;

		default:
			break;
		}
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
			e.getMessage();

		}

	}

}
