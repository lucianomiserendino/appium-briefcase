package gov.uscourts.ao.mobileBriefcase.common;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;

public class Page extends Base {

	public static MobileElement waitForPresenceOfElementLocated(By element, WebDriver driver) {
		return (MobileElement) new WebDriverWait(driver, 120)
				.until(ExpectedConditions.presenceOfElementLocated((element)));
	}

	public static List<WebElement> waitForVisibilityOfAllElements(List<WebElement> elements, WebDriver driver) {
		return new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public static void waitToBeClickable(MobileElement element, WebDriver driver) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public static MobileElement waitForVisibilityOfElement(MobileElement element, WebDriver driver) {
		return (MobileElement) new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(element));
	}

	public static void performPageLoad(WebDriver driver) {

		synchronized (driver) {
			try {
				driver.wait(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



}
