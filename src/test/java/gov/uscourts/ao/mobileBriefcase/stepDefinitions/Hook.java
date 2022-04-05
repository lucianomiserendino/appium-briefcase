package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import io.cucumber.java.Before;

public class Hook extends Base {

	@Before
	public void setUp() {
		safariInstance();
		closeIOSDriver();

	}

//	@After
//	public void tearDown() {
//		JenieLoginPage.logout();
//		closeIOSDriver();
//	}

}
