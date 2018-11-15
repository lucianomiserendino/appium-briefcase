package gov.uscourts.ao.mobileBriefcase.common;

import static org.openqa.selenium.support.PageFactory.initElements;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppiumPageFactory extends Base {

	public AppiumPageFactory() {
		initElements(new AppiumFieldDecorator(driver), this);
	}
}
