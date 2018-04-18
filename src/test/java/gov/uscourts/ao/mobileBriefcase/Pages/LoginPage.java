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
		String openBtn = "Open";
		clickOn(getElement(By.name(openBtn)));
	}

	public void getCMKA(){
		clickOn(CMKA);
		performPageLoad();
		tapByCoordinates(getCoordinates("pendingTaskX"), getCoordinates("pendingTaskY"));
		tapByCoordinates(getCoordinates("dashboardX"), getCoordinates("dashboardY"));
		performPageLoad();

	}

	public static int getCoordinates(String coordinates) {
		return Integer.parseInt(Configuration.getProperty(coordinates));

	}

}
