package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gov.uscourts.ao.mobileBriefcase.common.Base;

public class Hook extends Base {

	@Before
	public void setUp() {
		safariInstance();
		driver.close();

	}

	@After

	public void tearDown() {
		
		logout();
		closeIOSDriver();

	}

	public void embedScreenshot(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				String testName = scenario.getName();
				scenario.embed(screenshot, "image/png");
				scenario.write(testName);
			} catch (WebDriverException wde) {
				System.err.println(wde.getMessage());
			} catch (ClassCastException cce) {
				cce.printStackTrace();
			}
		}
	}

}
