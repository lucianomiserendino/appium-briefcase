package gov.uscourts.ao.mobileBriefcase.common;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppiumPageFactory extends Base {

	public AppiumPageFactory() {
		PageFactory.initElements(new AppiumFieldDecorator(Base.getInstance(Drivers.IOS)), this);
	}
}
