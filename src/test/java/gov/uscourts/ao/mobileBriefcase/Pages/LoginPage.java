package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.moibleBriefcase.common.Utilties.findElement;
import static gov.uscourts.ao.moibleBriefcase.common.Utilties.waitForPageToLoad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.moibleBriefcase.common.Configuration;
import gov.uscourts.ao.moibleBriefcase.common.DesiredCapabilitySet;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class LoginPage extends DesiredCapabilitySet {

	public LoginPage() {
		PageFactory.initElements(new AppiumFieldDecorator(DesiredCapabilitySet.getInstance()), this);
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

	@iOSFindBy(accessibility = "Logout of Briefcase")
	public MobileElement logOut;

	@iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[2]")
	public MobileElement settingsIcon;

	String openBtn = "Open";

	public void selectEnvironment() {
		waitForPageToLoad();
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
		clickOn(findElement(By.name(openBtn)));
	}

	public void getCMKA() {
		clickOn(CMKA);
	}

	public void logOut() {
		clickOn(settingsIcon);
		clickOn(logOut);
	}

	public void clickOn(WebElement element) {
		element.click();
	}

}
