package gov.uscourts.ao.mobileBriefcase.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class Utilities extends Base {

	public static String getText(MobileElement elements) {
		return elements.getText();

	}
	

	public static void clearCookies(WebDriver webDriver) {
		((JavascriptExecutor) webDriver).executeScript("var cookies = document.cookie.split(\";\");"
				+ "for (var i = 0; i < cookies.length; i++) {" + "var cookie = cookies[i];"
				+ "	var eqPos = cookie.indexOf(\"=\");" + "	var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;"
				+ "	document.cookie = name + \"=;expires=Thu, 01 Jan 1970 00:00:00 GMT\";" + "	};");
	}

	public static List<String> retrieveAllRefererrals(List<MobileElement> elements, String split, int index) {
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

	public static void clickOn(WebElement element) {
		element.click();
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

}