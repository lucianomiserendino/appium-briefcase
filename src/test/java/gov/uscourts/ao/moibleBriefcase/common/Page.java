package gov.uscourts.ao.moibleBriefcase.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;

public class Page extends Base {

	public static MobileElement getElement(By element) {
		return driver.findElement(waitForPresenceOfElement(element));

	}

	public static List<MobileElement> getElements(By elements) {
		return driver.findElements(elements);
	}

	public boolean isDisplayed(By by) {
		boolean isDisplayed = false;
		try {
			if (getElement(by).isDisplayed())
				isDisplayed = true;
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	public static void performPageLoad() {
		synchronized (driver) {
			try {
				driver.wait(8000);
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
		new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(element)).click();
		return element;
	}

	public static void getPageSource() {
		performPageLoad();
		System.out.println(driver.getPageSource());

	}



}
