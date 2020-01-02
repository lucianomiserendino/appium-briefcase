package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrollDownIfNotDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.tapByCoordinate;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import gov.uscourts.ao.mobileBriefcase.common.Actions;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import gov.uscourts.ao.mobileBriefcase.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.models.UserInputData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSBy;

public class JenieLoginPage extends Base {

	public JenieLoginPage() {
		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
	}

	static String open = "Open";

	static String okButton = "OK";

	static String user = "User";

	static String appellateJudges = "Appellate Judges";

	static String bankruptcyJudges = "Bankruptcy Judges";

	static String staffAttorneys = "Staff Attorneys";

	@iOSBy(accessibility = "Production")
	public MobileElement production;

	// @WithTimeout(time = 200, unit = TimeUnit.SECONDS)
	@iOSBy(accessibility = "Integration")
	public MobileElement integration;

	@iOSBy(accessibility = "Staging")
	public MobileElement staging;

	@iOSBy(accessibility = "Testing")
	public MobileElement testing;

	@iOSBy(xpath = "//XCUIElementTypeOther[@name='JENIE Single Sign On']/XCUIElementTypeOther[5]/XCUIElementTypeTextField")
	public static WebElement userName;

	@iOSBy(xpath = "//XCUIElementTypeOther[@name=\"JENIE Single Sign On\"]/XCUIElementTypeOther[6]/XCUIElementTypeSecureTextField")
	public static WebElement password;

	@iOSBy(id = "SIGN ON")
	public static WebElement submButton;

	@FindBy(partialLinkText = "Send Key to Device")
	public WebElement sendKeyButton;

	// @WithTimeout(time = 300, unit = TimeUnit.SECONDS)
	@iOSBy(accessibility = "Appellate DC Development - CMKA")
	public MobileElement cmka;

	@iOSBy(accessibility = "Appellate DC Development - CM1A")
	public MobileElement cm1a;

	@iOSBy(accessibility = "Appellate DC Development - CM5A")
	public MobileElement cm5a;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public static MobileElement dashboard;

	// @WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSBy(xpath = "//*[contains(@name, 'User')]")
	public MobileElement selectUser;

	@iOSBy(xpath = "//*[contains(@name, 'NavigationRenderer')]/XCUIElementTypeButton[2]")
	public static MobileElement settingsIcon;

	@iOSBy(xpath = "//XCUIElementTypeStaticText[@name='Logout of Briefcase']")
	public static MobileElement logout;

	@iOSBy(accessibility = "ReferralsList")
	public static MobileElement referralsList;

	@iOSBy(accessibility = "AvailableJudges")
	public static MobileElement AvailableJudges_Container;

	@iOSBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
	public static MobileElement searchIcon;

	@iOSBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static MobileElement searchBTN;

	@iOSBy(xpath = "//XCUIElementTypeTextField")
	public static MobileElement searchTextField;

	public void getEnvironment(Environment environment) {
		switch (environment) {

		case Production:
			tap(production);
			break;

		case Integration:
			tap(integration);
			break;

		case Staging:
			tap(staging);
			break;

		case Testing:
			tap(testing);
			break;

		default:
			throw new RuntimeException("Invalid Environment");
		}
	}


	public void selectEnvironment(String environment) {

		try {
			performPageLoad(driver);
			getEnvironment(Environment.valueOf(environment));
		} catch (Exception e) {
			tap(Locator.NAME, okButton);
			getEnvironment(Environment.valueOf(environment));
		}
	}

	public void sendCredentials(String Username, String Password) {
		sendKeys(userName, Username, password, Password);
		submButton.click();

	}

	public void sedKeyButton() {
		performPageLoad(driver);
		tapByCoordinate("backX", "backY");
		try {
			sendKeyButton.click();
			tapByCoordinate("x", "y");
		} catch (WebDriverException e) {
			tapByCoordinate("x", "y");
		}

	}

	public static void open() {
		tap(Locator.NAME, open);
	}

	public void getServer(String server) {
		try {
			performPageLoad(driver);
			contains("Appellate DC Development - "+server).click();
	
		} catch (Exception e) {
			if (contains(okButton).isDisplayed()) {
				tap(Locator.NAME, okButton);
				contains("Appellate DC Development - "+server).click();
			}
		}
		performPageLoad(driver);

	}

	public static void selectUser(String availableJudges, String userName) {
		contains(user).click();
		getJudgesList(replace(availableJudges, "_", " "), userName, availableJudges);
	}

	public static void selectUserCategory(String availableJudges, User user) {

		if (contains(availableJudges).isDisplayed() && Actions.findElement(By.xpath(
				"(//XCUIElementTypeOther[@name='AvailableJudges']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, '"
						+ availableJudges + "')]/following:: XCUIElementTypeOther/XCUIElementTypeStaticText)[1]"))
				.getText().contains("(")) {

			switch (user) {
			case Appellate_Judges:
				tap(contains(appellateJudges));
				break;
			case Bankruptcy_Judges:
				tap(contains(bankruptcyJudges));
				break;
			case Staff_Attorneys:
				tap(contains(staffAttorneys));
				break;
			default:
				throw new RuntimeException("Invalid User type");
			}
		}
	}

	public static void getJudgesList(String availableJudges, String user, String userCategory) {

		scrollDownIfNotDisplayed(containsElement(availableJudges));
		try {
			selectUserCategory(availableJudges, User.valueOf(userCategory));
		} catch (AssertionError e) {
			e.getMessage();
		} finally {

			scrollDownIfNotDisplayed(containsElement(user));
		}
	}

	public static void searchForACase(String caseNum) {
		tap(searchIcon);
		sendKeys(searchTextField, caseNum);
		tap(searchBTN);

	}

	public static void logout() {
		try {
			dashboard.click();
			settingsIcon.click();
			logout.click();
			if (findElements(By.xpath(containsElement("Press OK to logout"))).size() > 0) {
				contains(okButton).click();
			} else {
				Page.sleep(55000);
				logout.click();
				contains(okButton).click();
			}
		} catch (ElementNotVisibleException e) {
			e.getMessage();
		}
	}

	public void login(List<UserInputData> userInputData) {
		String court = SystemPropertySetup.getCourtId(userInputData);
		String env = SystemPropertySetup.getEnvironment(userInputData);
		String user = SystemPropertySetup.getUserName(userInputData);
		String pwd = SystemPropertySetup.getPassword(userInputData);

		selectEnvironment(env);
		changeWindow("WEBVIEW");
		sendCredentials(user, pwd);
		sedKeyButton();
		changeWindow("NATIVE");
		open();
		getServer(court);

	}

	public enum User {
		Appellate_Judges, Bankruptcy_Judges, Staff_Attorneys
	}

	public enum Environment {
		Integration, Staging, Testing, Production
	}

	

}