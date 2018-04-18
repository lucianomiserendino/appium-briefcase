package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.moibleBriefcase.common.Page.getElement;
import static gov.uscourts.ao.moibleBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.moibleBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.moibleBriefcase.common.Utilities.tapByCoordinates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.moibleBriefcase.common.Base;
import gov.uscourts.ao.moibleBriefcase.common.Configuration;
import gov.uscourts.ao.moibleBriefcase.common.PlatformVersions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class LoginPage extends Base {

	private String openBtn = "Open";

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

	public void open() {
		clickOn(getElement(By.name(openBtn)));
	}

	public void getCMKA() {
		clickOn(CMKA);
		int pendingTaskX = 252;
		int pendingTaskY = 343;
		int dashboardX = 33;
		int dashboardY = 116;
		tapByCoordinates(pendingTaskX, pendingTaskY);
		tapByCoordinates(dashboardX, dashboardY);
		performPageLoad();

	}

}
