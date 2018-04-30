package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class Hook {

	public static IOSDriver<MobileElement> driver;

	@Before
	public void setUp() {
		// Base.getInstance(PlatformVersions.IOS).manage().timeouts().implicitlyWait(10,
		// TimeUnit.SECONDS);

	}

	@After

	public void tearDown(Scenario scenario) throws Exception {

		if (scenario.isFailed()) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
				scenario.write("URL at failure: " + driver.getCurrentUrl());
			} catch (WebDriverException wde) {
				scenario.write("Embed Failed " + wde.getMessage());
			} catch (ClassCastException cce) {
				cce.printStackTrace();
			}
		}
	}

}
