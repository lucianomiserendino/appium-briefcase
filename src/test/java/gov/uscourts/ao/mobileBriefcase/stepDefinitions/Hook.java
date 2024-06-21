package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;

public class Hook extends Base {

	@Before
	public void setUp() {
		safariInstance();
		JenieLoginPage page=new JenieLoginPage();
		page.checkLoginScreen();
	}

	@After
	public void tearDown() {
		JenieLoginPage.logout();
		closeIOSDriver();
	}

}
