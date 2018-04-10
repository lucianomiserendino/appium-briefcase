package gov.uscourts.ao.moibleBriefcase.common;

import static gov.uscourts.ao.moibleBriefcase.common.Utilities.waitForPageToLoad;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public abstract class DesiredCapabilitySet {

	public static IOSDriver<MobileElement> driver;
	private static DesiredCapabilities capabilities;

	public static WebDriver getInstance() {

		try {
			capabilities = new DesiredCapabilities();
			SetCapabilitiy("platformName");
			SetCapabilitiy("platformVersion");
			SetCapabilitiy("udid");
			SetCapabilitiy("deviceName");
			SetCapabilitiy("bundleId");
			SetCapabilitiy("xcodeOrgId");
			SetCapabilitiy("xcodeSigningId");
			SetCapabilitiy("autoAcceptAlerts");

			driver = new IOSDriver<MobileElement>(new URL(Configuration.getProperty("host")), capabilities);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return driver;
	}

	public static void SetCapabilitiy(String capability) {
		capabilities.setCapability(capability, Configuration.getProperty(capability));

	}

	public static void changeWindow(String type) {
		waitForPageToLoad();
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