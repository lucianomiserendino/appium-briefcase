package gov.uscourts.ao.mobileBriefcase.Pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;

import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.Page;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class DeleteIndividualDocuments extends AppiumPageFactory {

	/**
	 * Performs element scroll by predicate string
	 *
	 * @param el
	 *            the element to scroll
	 * @param pre
	 *            the predicate string
	 * @version java-client: 7.3.0
	 **/
	public void mobileScrollToElementIOS(MobileElement el) {
//		System.out.println("mobileScrollToElementIOS(): pre: '" + pre + "'"); // always log your actions
//
//		// Animation default time:
//		// - iOS: 200 ms
//		// final value depends on your app and could be greater
//		final int ANIMATION_TIME = 200; // ms
//		final HashMap<String, String> scrollObject = new HashMap<String, String>();
//		scrollObject.put("element", el.getId());
//		scrollObject.put("predicateString", pre);
//		try {
//			driver.executeScript("mobile:swipe", scrollObject);
//			Thread.sleep(ANIMATION_TIME); // always allow swipe action to complete
//		} catch (Exception e) {
//			System.err.println("mobileScrollToElementIOS(): FAILED\n" + e.getMessage());
//			return;
//		}
		
		// Java
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "right");
		params.put("velocity", 2500);
		params.put("element", ((RemoteWebElement) el).getId());
		js.executeScript("mobile: swipe", params);
	}
	
	

	public void deleteDoc() {
		MobileElement el = (MobileElement) driver.findElement(By.id("Forum Topics"));

		mobileScrollToElementIOS(el);
		
	Page.performPageLoad(driver);
		//MobileElement element = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Downloaded']");
	
		
		
		System.out.println(	driver.getPageSource());
	}
	
	
	
	

}
