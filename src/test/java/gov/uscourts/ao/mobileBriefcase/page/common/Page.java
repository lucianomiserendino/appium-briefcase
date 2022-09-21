package gov.uscourts.ao.mobileBriefcase.page.common;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page extends Base {

	public static WebElement waitForPresenceOfElementLocated(By element, WebDriver driver) {
		return (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated((element)));
	}

	public static List<WebElement> waitForVisibilityOfAllElements(List<WebElement> elements, WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public static void waitToBeClickable(WebElement element, WebDriver driver) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element))
				.click();
	}

	public static WebElement waitForVisibilityOfElement(WebElement element, WebDriver driver) {
		return (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOf(element));
	}

	public static void performPageLoad(WebDriver driver) {

		synchronized (driver) {
			try {
				driver.wait(1000);
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
