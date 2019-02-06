package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_LoginPage.logout;

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

}
