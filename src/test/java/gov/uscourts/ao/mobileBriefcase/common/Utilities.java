package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElement;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class Utilities extends Base {

	public static List<String> retrieveAllReferrals(List<MobileElement> elements, String split, int index) {
		String[] dest;
		List<String> referrals = new ArrayList<>();
		List<MobileElement> el = elements;
		Iterator<MobileElement> itr = el.iterator();
		while (itr.hasNext()) {

			dest = itr.next().getText().split(split);
			referrals.add(dest[index].trim());

		}
		return referrals;

	}

	public static void scroll(int iCount) {

		while (iCount > 0) {
			HashMap<String, String> swipeObject = new HashMap<String, String>();
			swipeObject.put("direction", "down");

			JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

			jsDriver.executeScript("mobile:scroll", swipeObject);
			iCount--;

		}

	}

	public static void selectCase(String xpath) {

		String Case = xpath;

		boolean caseDisplayed = isDisplayed(waitForPresenceOfElement(By.xpath(Case)));

		if (caseDisplayed == true) {
			findElement(By.xpath(Case)).click();
		} else {
			scroll(2);
			findElement(By.xpath(Case)).click();
		}

	}

	public static String changeDateFormat(String element) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date date = null;

		date = format1.parse(element);
	
		return format2.format(date);

	}

	public static String getText(MobileElement elements) {
		return elements.getText();

	}

	public static MobileElement findElement(By element) {
		return driver.findElement(waitForPresenceOfElement(element));

	}

	public static List<MobileElement> findElements(By elements) {
		return driver.findElements(elements);
	}

	public static boolean isDisplayed(By by) {
		boolean isDisplayed = false;
		try {
			if (findElement(by).isDisplayed())
				isDisplayed = true;
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public static void clickOn(WebElement element) {

		try {
			if (element.isDisplayed()) {
				element.click();
			}
		} catch (Exception e) {

		}

	}

	public static void captureScreenShots() {

		String path = "./src/test/resources/pdfScreenShots";
		try {
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			new File(path).mkdir();
			String file_name = dateFormat.format(new Date()) + ".png";

			FileUtils.copyFile(file, new File(path + "/" + file_name));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void tapByCoordinates(String xCoordinates, String yCoordinates) {
		new TouchAction(driver).tap(getCoordinates(xCoordinates), getCoordinates(yCoordinates)).perform();

	}

	public static int getCoordinates(String coordinates) {
		return Integer.parseInt(Configuration.getProperty(coordinates));

	}

	public static String getNumOfDisplayedCases(MobileElement element, String start, String end) {
		return element.getText().substring(getCoordinates(start), getCoordinates(end)).trim();

	}

	public static List<String> getListOfCategories(MobileElement motionsPetitions, MobileElement casesOnCalendar,
			MobileElement petitionsForRehearing, MobileElement screeningPanels) {
		List<String> categories = new ArrayList<>();
		categories.add(getText(motionsPetitions));
		categories.add(getText(casesOnCalendar));
		categories.add(getText(petitionsForRehearing));
		categories.add(getText(screeningPanels));
		Collections.sort(categories);
		return categories;

	}

	public static void logout(String xpath, String id, String name, String Name) {
		clickOn(findElement(By.xpath(xpath)));
		clickOn(findElement(By.id(id)));
		clickOn(findElement(By.name(name)));
		clickOn(findElement(By.name(Name)));
	}

}