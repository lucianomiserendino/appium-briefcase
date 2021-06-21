package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.common.Actions;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import gov.uscourts.ao.mobileBriefcase.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.common.Utility;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class JenieLoginPage extends Base {

	public JenieLoginPage() {
		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
	}

	static String open = "Open";

	static String okButton = "OK";

	static String user = "User";

	@iOSBy(accessibility = "Production")
	public MobileElement production;

	@iOSBy(accessibility = "Integration")
	public MobileElement Integration;

	@iOSBy(accessibility = "Staging")
	public MobileElement staging;

	@iOSBy(accessibility = "Testing")
	public MobileElement testing;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"JENIE Single Sign On\"]/XCUIElementTypeOther[5]/XCUIElementTypeTextField")
	public static WebElement userName;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"JENIE Single Sign On\"]/XCUIElementTypeOther[6]/XCUIElementTypeSecureTextField")
	public static WebElement password;

	@iOSXCUITFindBy(id = "SIGN ON")
	public static WebElement submButton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Send Key to Device\"]")
	public WebElement sendKeyButton;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static MobileElement dashboard;

	// @WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'User')]")
	public MobileElement selectUser;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[2]")
	public static MobileElement settingsIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Logout of Briefcase']")
	public static MobileElement logout;

	@iOSXCUITFindBy(accessibility = "ReferralsList")
	public static MobileElement referralsList;

	@iOSXCUITFindBy(accessibility = "AvailableJudges")
	public static MobileElement AvailableJudges_Container;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
	public static MobileElement searchIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static MobileElement searchBTN;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	public static MobileElement searchTextField;

	public void getEnvironment(Environment environment) {
		switch (environment) {

		case Production:
			tap(production);
			break;

		case Integration:
			tap(Integration);
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

	public void g() {
		tap(Integration);
	}

	public  void sendCredentials(String Username, String Password) {
		sendKeys(userName, Username, password, Password);
		submButton.click();

	}

	public static void open() {
		tap(Locator.XPATH, "(//XCUIElementTypeStaticText[@name=\"Open\"])[1]");
	}

	public void getServer(String server) {
		// contains("Appellate Installation Testing - "+server).click();
		contains(server).click();
		performPageLoad(driver);

	}

	public void selectUser(List<UserInputData> userInputData) {
		String role = SystemPropertySetup.getRoleType(userInputData);
		String name = SystemPropertySetup.getUser(userInputData);
		contains(user).click();
		selectJudge(role, name);

	}

	public static void selectJudge(String availableJudges, String user) {

		while (true) {

			List<MobileElement> elems = driver
					.findElements(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"▽\"`]"));
			if (elems.size() > 0) {
				elems.get(0).click();
			} else {
				break;
			}
		}
		tap(Locator.XPATH, "//*[contains(@name, '" + availableJudges + "')]");

		while (true) {

			List<MobileElement> elems = driver.findElements(By.xpath(containsElement(user)));
			if (elems.size() > 0) {
				elems.get(0).click();
				break;
			} else {
				Utility.scrolldown();
			}
		}
	}

	public static void searchForACase(String caseNum) {
		tap(searchIcon);
		sendKeys(searchTextField, caseNum);
		tap(searchBTN);

	}

	public static void logout() {
		//try {
			if

			(contains("Dashboard").isDisplayed()) {
				contains("Dashboard").click();
				Page.sleep(5000);
				
				settingsIcon.click();
				logout.click();

				if (findElements(By.xpath(containsElement("Press OK to logout"))).size() > 0) {
					contains(okButton).click();
				} else {
					Page.sleep(55000);
					logout.click();
					contains(okButton).click();
				}
			}
//		} catch (ElementNotVisibleException e) {
//			e.getMessage();
//		}
	}

	public void login(List<UserInputData> userInputData) {

		String court = SystemPropertySetup.getCourtId(userInputData);

		String env = SystemPropertySetup.getEnvironment(userInputData);

		String user = SystemPropertySetup.getUserName(userInputData);
		String pwd = SystemPropertySetup.getPassword(userInputData);

		Actions.tap(contains(env));
		changeWindow("WEBVIEW");

		sendCredentials(user, pwd);
		performPageLoad(driver);
		sendKeyButton.click();
		changeWindow("NATIVE");
		// open();
		getServer(court);

	}

	public enum Environment {
		Integration, Staging, Testing, Production
	}

}