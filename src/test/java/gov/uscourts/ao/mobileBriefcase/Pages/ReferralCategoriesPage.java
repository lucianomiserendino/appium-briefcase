package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.moibleBriefcase.common.Utilities.findElement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.moibleBriefcase.common.DesiredCapabilitySet;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
public class ReferralCategoriesPage extends DesiredCapabilitySet{

	public ReferralCategoriesPage() {
		PageFactory.initElements(new AppiumFieldDecorator(DesiredCapabilitySet.getInstance()), this);
	}

	@iOSFindBy(accessibility = "Dashboard")
	public MobileElement dashboard;

	//DesiredCapabilitySet.findElements("//XCUIElementTypeStaticText[@name='Dashboard']");
	//DesiredCapabilitySet.findElements("//XCUIElementTypeTable[@name='Categories']/XCUIElementTypeCell[1]");

	public void retrieveRefCategories() {
	findElement(By.id("Dashboard"));

	}

	



}
