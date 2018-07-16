package gov.uscourts.ao.mobileBriefcase.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;

public class Page extends Base {

	public static void performPageLoad() {
		synchronized (driver) {
			try {
				driver.wait(9000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void pageLoad() {
		synchronized (driver) {
			try {
				driver.wait(25000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static By waitForPresenceOfElement(By element) {
		new WebDriverWait(driver, 70).until(ExpectedConditions.presenceOfElementLocated((element)));
		return element;

	}

	public static List<WebElement> waitForPresenceOfElements(List<WebElement> elements) {
		return new WebDriverWait(driver, 70).until(ExpectedConditions.visibilityOfAllElements(elements));

	}

	public static WebElement waitToBeClickable(WebElement element) {
		new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(element)).click();
		return element;
	}

	public static MobileElement waitForElement(MobileElement element) {
		return (MobileElement) new WebDriverWait(driver, 200).until(ExpectedConditions.visibilityOf(element));

	}

	public static void getPageSource() {
		performPageLoad();
		System.out.println(driver.getPageSource());

	}



}
