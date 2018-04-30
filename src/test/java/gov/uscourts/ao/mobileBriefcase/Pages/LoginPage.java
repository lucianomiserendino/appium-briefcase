package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Page.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.tapByCoordinates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Configuration;
import gov.uscourts.ao.mobileBriefcase.common.PlatformVersions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class LoginPage {

	public LoginPage() {

		PageFactory.initElements(new AppiumFieldDecorator(Base.getInstance(PlatformVersions.IOS)), this);
	}

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

	@iOSFindBy(accessibility = "User: * User Not Selected *")
	public MobileElement selectUser;

	@iOSFindBy(xpath = "//*[contains(@name, 'NavigationRenderer')]/XCUIElementTypeButton[2]")
	public MobileElement settingsIcon;

	@iOSFindBy(accessibility = "Logout of Briefcase")
	public MobileElement logout;

	public void selectEnvironment() {
		performPageLoad();
		clickOn(server);
	}

	public void sendCredentials() {

		userName.sendKeys(Configuration.getProperty("userName"));
		password.sendKeys(Configuration.getProperty("password"));
		clickOn(submButton);

	}

	public void sedKeyButton() {
		clickOn(sendKeyButton);
	}

	public void opens() {
		String openBtn = "Open";
		clickOn(findElement(By.name(openBtn)));
		
	}

	public void getCMKA() {
		clickOn(CMKA);
		performPageLoad();
		tapByCoordinates("motionsPetitionsX", "motionsPetitionsY");
		tapByCoordinates("dashboardX", "dashboardY");
		performPageLoad();

	}

	public void logOut() {
		clickOn(settingsIcon);
		clickOn(logout);
		String okBtn = "OK";
		clickOn(findElement(By.name(okBtn)));
		clickOn(findElement(By.name(okBtn)));
		
	}


}
