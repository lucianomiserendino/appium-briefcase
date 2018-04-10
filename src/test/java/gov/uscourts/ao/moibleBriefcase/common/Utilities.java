package gov.uscourts.ao.moibleBriefcase.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;

public class Utilities extends DesiredCapabilitySet {

	public static void waitForPageToLoad() {
		synchronized (driver) {
			try {
				driver.wait(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static By waitForPresenceOfElement(By element) {
		new WebDriverWait(driver, 70).until(ExpectedConditions.presenceOfElementLocated((element)));
		return element;

	}

	public static WebElement waitToBeClickable(WebElement element) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(element)).click();
		return element;
	}

	public static void actions(WebElement element) {
		new Actions(driver).moveToElement(waitToBeClickable(element)).perform();
	}

	public List<MobileElement> getElements(By elements) {
		return driver.findElements(elements);
	}

	public static WebElement findElement(By element) {
		return driver.findElement(element);

	}

	public static void getPageSource() {
		waitForPageToLoad();
		System.out.println(driver.getPageSource());

	}

}
