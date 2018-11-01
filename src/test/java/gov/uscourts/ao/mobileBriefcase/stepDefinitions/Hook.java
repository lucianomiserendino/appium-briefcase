package gov.uscourts.ao.mobileBriefcase.stepDefinitions;

import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.select;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.Coordinates.DASHBOARD;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages;
import gov.uscourts.ao.mobileBriefcase.common.Base;
import gov.uscourts.ao.mobileBriefcase.common.iOSCapabilities;

public class Hook extends Base implements iOSCapabilities {

	@Before
	public void setUp() {
		safariInstance();

	 }

	@After

	public void tearDown() {
//		select(DASHBOARD);
//		iOS_CommonPages.logOut();
//		driver.quit();

	}

}
