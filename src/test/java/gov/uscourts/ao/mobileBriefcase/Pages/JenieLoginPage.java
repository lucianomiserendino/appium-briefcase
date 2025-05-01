package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup.Variables;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class JenieLoginPage extends Base {

	public JenieLoginPage() {
		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
	}

	static String open = "Open";

	static String okButton = "OK";

	static String User = "User";

	@iOSBy(accessibility = "Production")
	public WebElement production;

	@iOSBy(accessibility = "env")
	public WebElement env;

	@iOSBy(accessibility = "Integration")
	public WebElement Integration;

	@iOSBy(accessibility = "Staging")
	public WebElement staging;

	@iOSBy(accessibility = "Testing")
	public WebElement testing;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"JENIE Single Sign On\"]/XCUIElementTypeOther/following::XCUIElementTypeTextField[1]")
	public static WebElement userName;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"JENIE Single Sign On\"]/XCUIElementTypeOther/following:: XCUIElementTypeSecureTextField[1]")
	public static WebElement password;

	@iOSXCUITFindBy(id = "SIGN ON")
	public static WebElement submButton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Send Key to Device']")
	public WebElement sendKeyButton;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static List<WebElement> dashboard;

	// @WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'User')]")
	public WebElement selectUser;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Settings']")
	public static List<WebElement> settingsIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Logout of Briefcase']")
	public static WebElement logout;

	@iOSXCUITFindBy(accessibility = "ReferralsList")
	public static WebElement referralsList;

	@iOSXCUITFindBy(accessibility = "AvailableJudges")
	public static WebElement AvailableJudges_Container;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[1]")
	public static WebElement searchIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static WebElement searchBTN;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	public static WebElement searchTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Court not set']")
	public static List<WebElement> setCourt;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='✓']/following::XCUIElementTypeStaticText[1]")
	public static WebElement checkmark;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=''])[1]")
	public static WebElement arrow;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Retrieving pending referrals\"]")
	public static List<WebElement> retrievePendingRefs;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> inProgress;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='CMECFSevers']")
	public static List<WebElement> CMECFSevers;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Back\"]")
	public static WebElement back;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"JenieSelection\"]")
	public static List<WebElement> jenieSelectionPage;

	// @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;
	
//	@iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@name=\"Dashboard\" and @label=\"\"]")
//	public static List<WebElement> dashboard;

	public final String environment = "environment";
	public final String userType = "userType";
	public final String jud = "jud";
	public final String stf = "stf";
	public final String sysadminUserName = "sysadminUserName";
	public final String sysadminPassword = "sysadminPassword";
	public final String judFirstName = "judFirstName";
	public final String stfFirstName = "stfFirstName";
	public final String user = "user";
	public final String judgeUserName = "judgeUserName";
	public final String judgePassword = "judgePassword";

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

	public void sendCredentials(String username, String password) {
		try {
			// Send the username and password to their respective input fields
			sendKeys(this.userName, username, this.password, password);

			// Wait for the submit button to be clickable
			Page.waitToBeClickable(submButton, driver);

		} catch (WebDriverException e) {
			// Log the exception and rethrow it to ensure it's not silently ignored
			System.err.println("Error sending credentials: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public static void open() {
		tap(Locator.XPATH, "(//XCUIElementTypeStaticText[@name=\"Open\"])[1]");
	}

	public void selectUser(List<UserInputData> userInputData) {
		String env = SystemPropertySetup.getCourtId(userInputData) + ".";
		String user = SystemPropertySetup.getVariable(Variables.USER, userInputData);
		String userType = SystemPropertySetup.getVariable(Variables.USER_TYPE, userInputData);
		String personrole = SystemPropertySetup.getVariable(Variables.PERSONROLE, userInputData);

		ifDownloaded(retrievePendingRefs);

		if (user.equals("sysadmin")) {
			contains(User).click();

			String name = "";

			if (userType.equals("judge")) {
				name = getProperty(env + jud);
			} else if (userType.equals("stf")) {

				name = getProperty(env + stf);
			}

			selectUser(personrole, name);
			ifDownloaded(inProgress);

		}
	}

	public static void selectUser(String availableJudges, String user) {
		// Click all 'GroupIcon' elements with label '▽' until none are left
		while (true) {
			List<WebElement> elems = driver
					.findElements(By.xpath("//XCUIElementTypeStaticText[@name='GroupIcon' and @label='▽']"));
			if (!elems.isEmpty()) {
				elems.get(0).click();
			} else {
				break;
			}
		}

		// Tap on the specified available judge
		tap(Locator.XPATH, "//*[contains(@name, '" + availableJudges + "')]");

		// Attempt to find the user with a maximum of 10 swipes
		int maxAttempts = 10;
		for (int attempt = 0; attempt < maxAttempts; attempt++) {
			List<WebElement> elems = driver.findElements(By.xpath(containsElement(user)));
			if (!elems.isEmpty()) {
				elems.get(0).click();
				return;
			} else {
				Utility.tapAndSwipe(Direction.UP);
			}
		}

		// If the user is not found after 10 attempts, throw an error
		throw new NoSuchElementException("User '" + user + "' not found after 10 attempts.");
	}

	public static void searchForACase(String caseNum) {
		tap(searchIcon);
		sendKeys(searchTextField, caseNum);
		tap(searchBTN);

	}

	public static void checkLoginScreen() {
		Page.sleep(1000);
		ifDownloaded(inProgress);
		if (!(jenieSelectionPage.size() > 0)) {
			logout();
		}
	}

	public static void logout() {
		try {
			if (CMECFSevers != null && CMECFSevers.size() > 0) {
				tap(back);
			}
            collapseBtn.click();
			if (dashboard != null && dashboard.size() > 0) {
				Utility.doubleTap(dashboard.get(0));
				ifDownloaded(inProgress);

				if (settingsIcon != null && settingsIcon.size() > 0) {
					settingsIcon.get(settingsIcon.size()-1).click();

					if (logout != null) {
						logout.click();
					}

					performPageLoad(driver);

					List<WebElement> elements = Actions.findElements(By.xpath(containsElement("Press OK to logout")));
					if (elements != null && elements.size() > 0) {
						if (okButton != null) {
							contains(okButton).click();
						}
					}
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();

		}

	}

	public void login(List<UserInputData> userInputData) {
		// Get the court ID and append a period
		String courtId = SystemPropertySetup.getCourtId(userInputData) + ".";

		// Get the user type from the system properties
		String user = SystemPropertySetup.getVariable(Variables.USER, userInputData);

		// Get the environment property using the court ID
		String env = getProperty(courtId + this.environment);

		// Initialize username and password variables
		String userName;
		String password;

		// Determine the username and password based on the user type
		switch (user) {
		case "judge":
			userName = getProperty(courtId + this.judgeUserName);
			password = getProperty(courtId + this.judgePassword);
			break;
		case "sysadmin":
			userName = getProperty(courtId + this.sysadminUserName);
			password = getProperty(courtId + this.sysadminPassword);
			break;
		default:
			throw new IllegalArgumentException("Invalid user type: " + user);
		}

//		if (!(jenieSelectionPage.size() > 0)) {
//			if (CMECFSevers.size() > 0) {
//				tap(back);
//			}
//			logout();
//		}
		Page.waitToBeClickable(contains(env), driver);

		// Change to WEBVIEW context
		changeWindow("WEBVIEW");

		// Send the credentials
		sendCredentials(userName, password);

		// Wait for the page to load and the send key button to be clickable
		performPageLoad(driver);
		Page.waitToBeClickable(sendKeyButton, driver);

		// Uncomment if needed to click the send key button
		// sendKeyButton.click();

		// Change back to NATIVE context
		changeWindow("NATIVE");

		// Open the server using the provided user input data and court ID
		getServer(userInputData, courtId.replace(".", "").toUpperCase());
	}

	public void reopenTheApp() {
		// Ensure the page is fully loaded
		Page.performPageLoad(driver);

		// Check if the dashboard elements list is not null and not empty
		if (dashboard != null && !dashboard.isEmpty()) {
			// Click on the first element in the dashboard
			dashboard.get(0).click();
			// Wait for 5 seconds to allow any actions triggered by the click to complete
			Page.sleep(5000);
		} else {
			// Handle the case where the dashboard is not available
			System.err.println("Dashboard is not available. Cannot reopen the app.");
		}
	}

	public enum Environment {
		Integration, Staging, Testing, Production
	}

	public static void getServer(List<UserInputData> userInputData, String server) {
		try {
			// Retrieve the user type from system properties
			// String user = SystemPropertySetup.getVariable(Variables.USER, userInputData);

			// Check if the user is a sysadmin
			// if (user.contains("sysadmin")) {

			// if (dashboard.size() > 0) {
			if (setCourt.size() > 0) {
				setCourt.get(0).click();
				// }
				// }
			}

			// Perform a page load
			performPageLoad(driver);
			 String clickedServerText = null;
			if (CMECFSevers.size() > 0) {
				
				    boolean foundElement = false;

				    List<WebElement> serverElements = Actions.findElements(By.xpath(Actions.containsElement(server)));

				    for (WebElement serverElement : serverElements) {
				        String serverText = serverElement.getText().trim();

				        if (serverText.contains(server) && serverText.endsWith(server)) {
				            clickedServerText = serverText; 
				            serverElement.click();
				            foundElement = true;
				            break; 
				        }
				    }

				    if (!foundElement) {
				        throw new RuntimeException("No suitable server element found.");
				    }

				} else {
				    throw new RuntimeException("CMECFServers list is empty.");
				
			}

			performPageLoad(driver);

			// Retrieve the court list value
			String courtListValue = contains(clickedServerText).getAttribute("value");

			String lastPart = courtListValue.substring(courtListValue.lastIndexOf('-'));
			String court1 = courtListValue.split(lastPart)[0].trim();

			// Click on the server and get the name
			contains(server).click();
			String court2 = checkmark.getText();
			checkmark.click();

			// Verify that the selected court has a green checkmark
//			assertEquals("VERIFY A GREEN CHECKMARK DISPLAYS TO THE LEFT OF THE COURT THAT IS CURRENTLY SELECTED: ",
//					court1, court2);

		} catch (Exception e) {
			// Log and rethrow the exception to ensure it's not silently ignored
			System.err.println("Error getting server: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

}