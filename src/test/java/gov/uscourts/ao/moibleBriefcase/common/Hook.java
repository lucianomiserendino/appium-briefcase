package gov.uscourts.ao.moibleBriefcase.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {

	@Before
	public void setUp() {

		DesiredCapabilitySet.getInstance().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) DesiredCapabilitySet.getInstance())
					.getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}
		DesiredCapabilitySet.closeDriver();
	}

}
