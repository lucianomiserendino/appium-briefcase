package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.pageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.sleep;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.refresh;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_Sync {

	public iOS_Sync() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "//XCUIElementTypeOther[3]/XCUIElementTypeButton")
	public static MobileElement syncBTN;

	@iOSFindBy(xpath = "//*[contains(@name, 'Sync With CM/ECF')]")
	public static MobileElement syncWithCMECF;

	@iOSFindBy(xpath = "//*[contains(@name, 'Available for download')]")
	public static MobileElement docsAvailableForDownload;

	public void verifySyncCompletes() {

		refresh();
		try {
			pageLoad();
			syncBTN.click();
			sleep(200000);
			MobileElement syncCount = findElement(By.xpath("//*[contains(@name, 'Available for download')]"));
			String result = syncCount.getText().split(",")[1].trim().split(" ")[0];
			if (syncCount.isDisplayed() && !result.equals("0")) {
				syncBTN.click();
				sleep(100000);
				assertTrue("*******THE SYNC DIDN'T COMPLETE*******", result.equals("0"));

			} else {
				sleep(150000);
				assertTrue("*******THE SYNC DIDN'T COMPLETE*******", result.equals("0"));
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}
}