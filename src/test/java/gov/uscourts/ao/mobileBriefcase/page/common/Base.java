package gov.uscourts.ao.mobileBriefcase.page.common;

import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.ios.IOSDriver;

public abstract class Base implements iOSCapabilities {

	public static IOSDriver driver;
	protected static DesiredCapabilities capabilities;
	public static WebDriver webDriver;
	public static WebDriver winAppDriver;
	public static WebElement webElement;

	public static WebDriver getInstance(Driver drivers) {

		try {
			capabilities = new DesiredCapabilities();

			switch (drivers) {
			case IOS:

				SetCapabilitiy(PLATFORM_NAME);
				SetCapabilitiy(PLATFORM_VERSION);
				SetCapabilitiy(AUTOMATION_NAME);
				SetCapabilitiy(DEVICE_NAME);
				SetCapabilitiy(BUNDLE_ID);
				SetCapabilitiy(XCODE_ORG_ID);
				SetCapabilitiy(XCODE_SIGNING_ID);
				SetCapabilitiy(AUTO_ACCEPT_ALERTS);
				SetCapabilitiy(TAKES_SCREENSHOT);
				SetCapabilitiy(DEVICE_TYPE);
				SetCapabilitiy(FULL_RESET);
				SetCapabilitiy(NO_RESET);
				SetCapabilitiy(START_IWDP);
				SetCapabilitiy(SAFARI_INITIAL_URL);
				SetCapabilitiy(SHOULD_TERMINATE_APP);
				getDriver();
				break;

			case WINDOWS:
				capabilities = new DesiredCapabilities();
				SetCapabilitiy(PLATFORM_NAME_w);
				SetCapabilitiy(DEVICE_NAME_w);
				SetCapabilitiy(APP);
				winAppDriver = new RemoteWebDriver(new URL(getProperty(REMOTE_HOST)), capabilities);
				break;



			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return driver;
	}

	public static WebDriver safariInstance() {
		capabilities = new DesiredCapabilities();
		try {
			SetCapabilitiy(PLATFORM_VERSION);
			SetCapabilitiy(PLATFORM_NAME);
			SetCapabilitiy(AUTOMATION_NAME);
			SetCapabilitiy(DEVICE_NAME);
			SetCapabilitiy(BROWSER_NAME);
			driver = new IOSDriver(new URL(getProperty(LOCAL_HOST)), capabilities);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	public static void closeIOSDriver() {
		if (driver != null) {
			driver.quit();
		}

	}

	public static void getDriver() {
		try {
			getHost(getProperty(LOCAL_HOST));

		} catch (WebDriverException e) {
			getHost(System.getProperty("remotewebdriver.url"));
		}

	}

	public static void getHost(String host) {
		try {
			driver = new IOSDriver(new URL(host), capabilities);

		} catch (MalformedURLException v) {
			v.printStackTrace();
		}
	}

	public static void getUrl(String courtId, String env) {
		capabilities = new DesiredCapabilities();

		SetCapabilitiy(PLATFORM_VERSION);
		SetCapabilitiy(PLATFORM_NAME);
		SetCapabilitiy(AUTOMATION_NAME);
		SetCapabilitiy(DEVICE_NAME);
		SetCapabilitiy(BROWSER_NAME);
		String singleTabEditor = getProperty("singleTableEditor").replace("courtId", courtId).replace("env", env);
		driver.get(singleTabEditor);
		Page.sleep(20000);

	}

	/**
	 * Reads the property file and passes the values to DesiredCapability
	 */
	public static void SetCapabilitiy(String type) {
		capabilities.setCapability(type, getProperty(type));

	}

	/**
	 * This method is used for switching the driver between "WEBVIEW" and
	 * "NATIVE_APP"
	 */
	public static void changeWindow(String type) {
		try {
			// Perform the initial page load
			Page.performPageLoad(driver);

			// Get the available context handles (windows)
			Set<String> windows = ((IOSDriver) driver).getContextHandles();

			// Iterate through the context handles
			for (String window : windows) {
				// Switch to the context that contains the specified type
				if (window.contains(type)) {
					driver.context(window);
					break; // Exit the loop once the desired context is found
				}
			}
		} catch (WebDriverException e) {
			// Log the exception and rethrow it to ensure it's not silently ignored
			System.err.println("Error changing window context to: " + type);
			e.printStackTrace();
			throw e;
		}
	}

	public static List<String> getUdid(String ipad) {
		List<String> udid = new ArrayList<>();
		try {
			new BufferedReader(
					new InputStreamReader(Runtime.getRuntime().exec("xcrun simctl list devices").getInputStream()))
					.lines().forEach(s -> {
						if (s.contains(ipad) && s.contains("Booted")) {
							udid.add(s.substring(s.indexOf("ion)") + 6, s.indexOf("ion)") + 42));
						}
						;
					});
		} catch (IOException e) {
			e.getMessage();
		}
		return udid;

	}

	public enum Driver {
		IOS, WINDOWS, WEBRIVER
	}
}