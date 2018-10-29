package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_LoginPage extends Base {

	public iOS_LoginPage() {

		PageFactory.initElements(new AppiumFieldDecorator(Base.getInstance(Drivers.IOS)), this);
	}

	private static String openBtn = "Open";

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

	@iOSFindBy(xpath = "//*[contains(@name, 'Briefcase')]/preceding-sibling::XCUIElementTypeStaticText[contains(@name, 'Test notice for user that is logged in')]")
	public MobileElement testUserLoginPopUp;

	@iOSFindBy(accessibility = "Logout of Briefcase")
	public MobileElement logout;

	public void selectEnvironment(String env) {

		performPageLoad();
		select(env);
	}

	public void sendCredentials(String Username, String Password) {

		userName.sendKeys(Username);
		password.sendKeys(Password);
		Page.sleep(15000);
		submButton.click();

	}

	public void sedKeyButton() {
		Page.sleep(15000);
		sendKeyButton.click();
	}

	public static void open() {
		clickOn(findElement(By.name(openBtn)));

	}

	public void getServer(String server) {
		select(server);
		performPageLoad();
	}

	public void select(String env) {
		if (elementIsDisplayed(env) == true) {
			clickOnElement(env);
		} else {
			clickOnElement("OK");
			performPageLoad();
			clickOnElement(env);

		}
	}

}