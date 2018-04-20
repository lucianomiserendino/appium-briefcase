package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Page.getElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.captureScreenShots;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getListOfDisplayedCases;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.scroll;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.tapByCoordinates;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Non_OrallyArguedCasePage extends Base {

	public Non_OrallyArguedCasePage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='Background'])[4]")
	public static MobileElement motionsPetitions;

	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement numberOfNonOrallyARGCases;

	@iOSFindBy(accessibility = "▽ Additional Documents")
	public static MobileElement aditionalDocuments;

	@iOSFindBy(accessibility = "pdf doc in cmecf")
	public static MobileElement pdfDocInCmecf;

	@iOSFindBy(accessibility = "PDF View")
	public static MobileElement pdfDocDownlad;

	public void clickOnMotionsPettitions() {
		clickOn(motionsPetitions);

	}

	public String getNumOfdisplayedCases() {
		return getListOfDisplayedCases(numberOfNonOrallyARGCases, "start", "end");
	}

	public void selectCase(String caseNum) {
		String Case = "//*[contains(@name, '" + caseNum + "')]";
		getElement(By.xpath(Case)).click();
	}

	public void downloadPDFDoc() {

		performPageLoad();
		scroll(3);
		waitToBeClickable(pdfDocInCmecf);
		tapByCoordinates("dismissX", "dismissY");
		captureScreenShots();
		performPageLoad();
		driver.navigate().back();

	}

}
