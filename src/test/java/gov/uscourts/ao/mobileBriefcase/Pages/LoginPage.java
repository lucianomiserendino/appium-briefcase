package gov.uscourts.ao.mobileBriefcase.Pages;


import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.select;

import static gov.uscourts.ao.mobileBriefcase.common.Page.*;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getIndex;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.DataTable;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users;
import gov.uscourts.ao.mobileBriefcase.common.Configuration;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
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

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Appellate DC Development - CMKA")
	public MobileElement CMKA;

	@WithTimeout(time = 15, unit = TimeUnit.SECONDS)
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
		performPageLoad();
		clickOn(findElement(By.name(openBtn)));
	}

	public void getCMKA(DataTable userCredentials) {
		waitToBeClickable(CMKA);
		performPageLoad();
		if (getIndex(userCredentials, "userName").equals(Configuration.getProperty("userName"))
				&& getIndex(userCredentials, "password").equals(Configuration.getProperty("password"))) {
			performPageLoad();
			waitToBeClickable(selectUser);
			performPageLoad();
			getUserCredentials(userCredentials);

		} else {

            pageLoad();
            select(Users.MOTIONS_PETITIONS);
            select(Users.DASHBOARD);
			pageLoad();

		}
	}

}