package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.Utilities.logout;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities;

public class Hook extends Base implements iOSCapabilities {

	private static String settingsPage = "//*[contains(@name, 'NavigationRenderer')]/XCUIElementTypeButton[2]";
	private static String logout = "Logout of Briefcase";
	private static String OKBtn = "OK";

	@Before
	public void setUp() {

		safariInstance();

	}

	@After

	public void tearDown() {

		//logout(settingsPage, logout, OKBtn, OKBtn);
	

	}

}
