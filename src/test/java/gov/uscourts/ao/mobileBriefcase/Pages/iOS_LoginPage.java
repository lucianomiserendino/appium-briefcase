package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Helper.*;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_LoginPage extends Base {

	public iOS_LoginPage() {

		PageFactory.initElements(new AppiumFieldDecorator(Base.getInstance(Drivers.IOS)), this);
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

	@FindBy(name = "Approved")
	public WebElement approved;

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

	public void selectEnvironment(String env) {

		performPageLoad();
		clickOnElement(env);
	}

	public void sendCredentials(String Username,String Password) {

		userName.sendKeys(Username);
		password.sendKeys(Password);
		waitToBeClickable(submButton);

	}

	public void sedKeyButton() {
		waitToBeClickable(sendKeyButton);
	}

	public void open() {
		clickOn(findElement(By.name(openBtn)));

	}

	public void getServer(String server) {
		clickOnElement(server);
		performPageLoad();
	}

}