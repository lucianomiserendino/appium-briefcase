package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public abstract class Base  {

	public static IOSDriver<MobileElement> driver;
	private static DesiredCapabilities capabilities;
	private static RemoteWebDriver remoteWebDriver;

	/**
	 * Reads the property file and passes the values to DesiredCapability
	 */
	public static WebDriver getInstance(PlatformVersions drivers) {
		capabilities = new DesiredCapabilities();
		try {
			switch (drivers) {
			case IOS:
				SetCapabilitiy("platformName");
				SetCapabilitiy("platformVersion");
				SetCapabilitiy("udid");
				SetCapabilitiy("deviceName");
				SetCapabilitiy("bundleId");
				SetCapabilitiy("xcodeOrgId");
				SetCapabilitiy("xcodeSigningId");
				SetCapabilitiy("autoAcceptAlerts");
				SetCapabilitiy(MobileCapabilityType.TAKES_SCREENSHOT);

				driver = new IOSDriver<MobileElement>(new URL(Configuration.getProperty("host")), capabilities);
				break;

			case WINDOWS:
				SetCapabilitiy("platformname");
				SetCapabilitiy("deviceame");
				SetCapabilitiy("app");

				remoteWebDriver = new RemoteWebDriver(new URL(Configuration.getProperty("Host")), capabilities);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return driver;
	}

	public static void SetCapabilitiy(String capability) {
		capabilities.setCapability(capability, Configuration.getProperty(capability));

	}

	/**
	 * This method is used for switching driver between "WEBVIEV" and
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

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
		}

	}

}