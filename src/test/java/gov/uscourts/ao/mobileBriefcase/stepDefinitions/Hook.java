package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_LoginPage.logout;
import static gov.uscourts.ao.mobileBriefcase.common.Base.closeIOSDriver;
import static gov.uscourts.ao.mobileBriefcase.common.Base.safariInstance;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {

	@Before
	public void setUp() {
		safariInstance();

	}

	@After

	public void tearDown() {
		logout();
		closeIOSDriver();

	}

}
