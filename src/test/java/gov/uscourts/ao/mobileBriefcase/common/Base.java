package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public abstract class Base implements iOSCapabilities {

	public static IOSDriver<MobileElement> driver;
	private static DesiredCapabilities capabilities;
	private static RemoteWebDriver remoteWebDriver;

	/**
	 * Reads the property file and passes the values to DesiredCapability 
	 */


	public static WebDriver getInstance(PlatformVersions drivers) {

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
			
		

				driver = new IOSDriver<MobileElement>(new URL(Configuration.getProperty("host")), capabilities);
				//driver = new IOSDriver<MobileElement>(new URL(System.getProperty("remotewebdriver.url")), capabilities);


				break;

			case WINDOWS:
				SetCapabilitiy(PLATFORM_NAME);
				SetCapabilitiy(DEVICE_NAME);
				SetCapabilitiy(APP);

				remoteWebDriver = new RemoteWebDriver(new URL(Configuration.getProperty("Host")), capabilities);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return driver;
	}
	
	
	
	public static void safariInstance(){
		capabilities = new DesiredCapabilities();

		try {
			SetCapabilitiy(PLATFORM_NAME);
			SetCapabilitiy(PLATFORM_VERSION);
			SetCapabilitiy(DEVICE_NAME);
			SetCapabilitiy(BROWSER_NAME);
			SetCapabilitiy(AUTO_ACCEPT_ALERTS);
			SetCapabilitiy(ENSURING_CLEAN_SESSION);
			

			driver = new IOSDriver<MobileElement>(new URL(Configuration.getProperty("host")), capabilities);
			//driver = new IOSDriver<MobileElement>(new URL(System.getProperty("remotewebdriver.url")), capabilities);


		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		driver.close();

	

	}

	public static void SetCapabilitiy(String type) {
		capabilities.setCapability(type, Configuration.getProperty(type));

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
	
	
	public enum PlatformVersions {
		   IOS,WINDOWS
		}

}