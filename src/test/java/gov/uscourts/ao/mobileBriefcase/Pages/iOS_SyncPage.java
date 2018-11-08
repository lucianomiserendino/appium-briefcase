package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Page.pageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.sleep;
import static org.junit.Assert.assertTrue;

import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_SyncPage extends AppiumPageFactory {

	@iOSFindBy(xpath = "//XCUIElementTypeOther[3]/XCUIElementTypeButton")
	public static MobileElement syncBTN;

	@iOSFindBy(xpath = "//*[contains(@name, 'Sync With CM/ECF')]")
	public static MobileElement syncWithCMECF;

	@iOSFindBy(xpath = "//*[contains(@name, 'Available for download')]")
	public static MobileElement docsAvailableForDownload;

	public void verifySyncCompletes() {

		try {
			pageLoad();
			syncBTN.click();
			sleep(200000);
			String sycnCount = getText("Available for download").split(",")[1].trim().split(" ")[0];
			if (elementIsDisplayed("Available for download") == true && !sycnCount.equals("0")) {
				syncBTN.click();
				sleep(100000);
				assertTrue("*******THE SYNC DIDN'T COMPLETE*******", sycnCount.equals("0"));

			} else {
				sleep(150000);
				assertTrue("*******THE SYNC DIDN'T COMPLETE*******", sycnCount.equals("0"));
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}
}