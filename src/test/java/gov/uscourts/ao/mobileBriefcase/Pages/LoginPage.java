package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Page.pageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Users.selectUser;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getIndex;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getUserCredentials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.DataTable;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Configuration;
import gov.uscourts.ao.mobileBriefcase.common.PlatformVersions;
import gov.uscourts.ao.mobileBriefcase.common.Users.BriefcaseUsers;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class LoginPage extends Base {

	public LoginPage() {

		PageFactory.initElements(new AppiumFieldDecorator(Base.getInstance(PlatformVersions.IOS)), this);
	}

	private String openBtn = "Open";

	@iOSFindBy(accessibility = "Integration")
	public MobileElement server;

	@FindBy(name = "usernameEntered")
	public static WebElement userName;

	@FindBy(name = "password")
	public static WebElement password;

	@FindBy(name = "SUBMIT2")
	public WebElement submButton;

	@FindBy(partialLinkText = "Send Key to Device")
	public WebElement sendKeyButton;

	@iOSFindBy(accessibility = "Appellate DC Development - CMKA")
	public MobileElement CMKA;

	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public MobileElement selectUser;

	@iOSFindBy(xpath = "//*[contains(@name, 'NavigationRenderer')]/XCUIElementTypeButton[2]")
	public MobileElement settingsIcon;

	@iOSFindBy(accessibility = "Logout of Briefcase")
	public MobileElement logout;

	public void selectEnvironment() {
		performPageLoad();
		clickOn(server);

	}

	public void sendCredentials(DataTable userCredentials) {

		userName.sendKeys(getIndex(userCredentials, "userName"));
		password.sendKeys(getIndex(userCredentials, "password"));
		clickOn(submButton);

	}

	public void sedKeyButton() {
		performPageLoad();
		clickOn(sendKeyButton);
	}

	public void open() {
		
		clickOn(findElement(By.name(openBtn)));
	}

	public void getCMKA(DataTable userCredentials) {
		clickOn(CMKA);
		performPageLoad();
		if (getIndex(userCredentials, "userName").equals(Configuration.getProperty("userName"))
				&& getIndex(userCredentials, "password").equals(Configuration.getProperty("password"))) {
			performPageLoad();
			clickOn(selectUser);
			performPageLoad();
			getUserCredentials(userCredentials);

		} else {

			selectUser(BriefcaseUsers.MOTIONS_PETITIONS);
			selectUser(BriefcaseUsers.DASHBOARD);
			pageLoad();
		}
	}

}