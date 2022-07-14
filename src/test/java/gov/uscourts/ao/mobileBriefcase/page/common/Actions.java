package gov.uscourts.ao.mobileBriefcase.page.common;

import static gov.uscourts.ao.mobileBriefcase.page.common.Page.waitForPresenceOfElementLocated;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;

public class Actions extends Base {

	public static MobileElement findElementBy(Locator identifier, String element) {

		By by = null;

		switch (identifier) {
		case ID:
			by = By.id(element);
			break;

		case XPATH:
			by = By.xpath(element);
			break;

		case NAME:
			by = By.name(element);
			break;

		case LINK_TEXT:
			by = By.linkText(element);
			break;

		case PARTIAL_LINK_TEXT:
			by = By.partialLinkText(element);
			break;

		case CLASS_NAME:
			by = By.className(element);
			break;

		case CSS_SELECTOR:
			by = By.cssSelector(element);
			break;

		case TAG_NAME:
			by = By.tagName(element);
			break;

		default:
			break;
		}
		return waitForPresenceOfElementLocated(by, driver);
	}

	public static MobileElement findElement(By by) {
		return driver.findElement(by);
	}

	public static List<MobileElement> findElements(By by) {
		return driver.findElements(by);
	}

	public static MobileElement contains(String element) {
		return Page.waitForPresenceOfElementLocated(By.xpath(containsElement(element)), driver);
	}

	public static String containsElement(String element) {
		return "//*[contains(@name, '" + element + "')]";
	}

	public static void sendKeys(MobileElement element, String text) {
		element.sendKeys(text);
	}

	public static String trim(String string) {
		if (!(string == null))
			return string.trim();
		else {
			return string;
		}

	}

	public static void sendKeys(WebElement element1, String text1, WebElement elemen2, String text2) {
		element1.click();
		element1.sendKeys(text1);
		Page.sleep(10000);
		elemen2.click();
		elemen2.sendKeys(text2);
	}

	public static void sendKeys(Locator identifier, String element, String text) {
		findElementBy(identifier, element).sendKeys(text);
	}

	public static void tap(MobileElement element) {
		Page.waitForVisibilityOfElement(element, driver).click();
	}

	public static void tap(Locator identifier, String element) {
		findElementBy(identifier, element).click();
	}

	public static String getText(MobileElement element) {
		return element.getText().trim();
	}

	public static String getText(Locator identifier, String element) {
		return findElementBy(identifier, element).getText().trim();

	}

	public static String split(String text, String splitBy, int index) {
		return text.split(splitBy)[index];
	}

	public static String replace(String text, String fromText, String toText) {
		return text.replaceAll(fromText, toText);
	}

	public static String replace(String text, String fromText1, String toText1, String fromText2, String toText2) {
		return text.replaceAll(fromText1, toText1).replaceAll(fromText2, toText2);
	}

	public static String replace(String text, String oldText1, String newText1, String oldText2, String newText2,
			String oldText3, String newText3) {
		return text.replace(oldText1, newText1).replace(oldText2, newText2).replace(oldText3, newText3);
	}

	public static boolean isDisplayed(Locator locator, String element) {
		boolean isDisplayed = false;

		try {
			MobileElement el = findElementBy(locator, element);
			if (el.isDisplayed())
				isDisplayed = true;
		} catch (WebDriverException e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public static boolean isDisplayed(MobileElement el) {
		boolean isDisplayed = false;

		try {
			if (el.isDisplayed())
				isDisplayed = true;
		} catch (WebDriverException e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public static void clicksOn(MobileElement element) {
		try {
			element.click();
		} catch (Exception e) {
			e.getMessage();
			javaScriptExecute("arguments[0].click();", element);
		}
	}

	public static void javaScriptExecute(String script, MobileElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script, element);
	}

	public enum Locator {
		ID, XPATH, NAME, LINK_TEXT, PARTIAL_LINK_TEXT, CLASS_NAME, CSS_SELECTOR, TAG_NAME
	}
}
