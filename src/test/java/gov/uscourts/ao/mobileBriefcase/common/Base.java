package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public abstract class Base implements iOSCapabilities {

	public static IOSDriver<MobileElement> driver;
	private static DesiredCapabilities capabilities;
	public static WebDriver webDriver;
	public static WebDriver winAppDriver;
	public static WebElement webElement;

	public static WebDriver getInstance(Drivers drivers) {

		try {
			capabilities = new DesiredCapabilities();
			switch (drivers) {
			case IOS:

				SetCapabilitiy(PLATFORM_NAME);
				SetCapabilitiy(PLATFORM_VERSION);
				SetCapabilitiy(UDID);
				SetCapabilitiy(DEVICE_NAME);
				SetCapabilitiy(BUNDLE_ID);
				SetCapabilitiy(XCODE_ORG_ID);
				SetCapabilitiy(XCODE_SIGNING_ID);
				SetCapabilitiy(AUTO_ACCEPT_ALERTS);
				SetCapabilitiy(TAKES_SCREENSHOT);
				SetCapabilitiy(DEVICE_TYPE);
				getDriver();
				break;

			case WINDOWS:

				capabilities = new DesiredCapabilities();
				SetCapabilitiy(PLATFORM_NAME_w);
				SetCapabilitiy(DEVICE_NAME_w);
				SetCapabilitiy(APP);
				winAppDriver = new RemoteWebDriver(new URL(getProperty(REMOTE_HOST)), capabilities);

				break;

			case WEBRIVER:
				System.setProperty(getProperty(CHROME_DRIVER_KYE), getProperty(CHROME_DRIVER));
				webDriver = new ChromeDriver();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return driver;
	}

	public static void safariInstance() {
		capabilities = new DesiredCapabilities();

		try {
			SetCapabilitiy(PLATFORM_NAME);
			SetCapabilitiy(PLATFORM_VERSION);
			SetCapabilitiy(DEVICE_NAME);
			SetCapabilitiy(BROWSER_NAME);
			SetCapabilitiy(AUTO_ACCEPT_ALERTS);
			SetCapabilitiy(ENSURING_CLEAN_SESSION);
			SetCapabilitiy(DEVICE_TYPE);
			getDriver();

		} catch (Exception e) {

			e.printStackTrace();
		}
		driver.close();

	}

	public static void closeIOSDriver() {
		if (driver != null) {
			driver.quit();
		}

	}

	public static void getDriver() {

		try {
			getHost(getProperty("host"));
		} catch (WebDriverException e) {
			getHost(System.getProperty("remotewebdriver.url"));
		}

	}

	public static void getHost(String host) {
		try {
			driver = new IOSDriver<MobileElement>(new URL(host), capabilities);
		} catch (MalformedURLException v) {
			v.printStackTrace();
		}
	}

	public static void getUrl(String url) {
		getInstance(Drivers.WEBRIVER);
		webDriver.get(getProperty(url));
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
		performPageLoad();
		Set<String> windows = ((AppiumDriver<MobileElement>) driver).getContextHandles();
		for (String window : windows) {
			if (window.contains(type))
				driver.context(window);
			((AppiumDriver<MobileElement>) driver).getContextHandles();
		}
	}

	public enum Drivers {
		IOS, WINDOWS, WEBRIVER
	}

}