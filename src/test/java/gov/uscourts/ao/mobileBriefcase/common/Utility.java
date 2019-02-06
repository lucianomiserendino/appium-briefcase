package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForVisibilityOfElement;
import static java.lang.Integer.parseInt;
import static java.util.Collections.sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class Utility extends Base {

	static SimpleDateFormat format1;
	static SimpleDateFormat format2;

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static void tapByCoordinate(String xCoordinates, String yCoordinates) {
		new TouchAction(driver).tap(getCoordinates(xCoordinates), getCoordinates(yCoordinates)).perform();
	}

	public static int getCoordinates(String coordinates) {
		return parseInt(getProperty(coordinates));

	}

	public static String findElementAndScrollDown(Locator locator, String element, MobileElement el) {

		Boolean elementNotFound = true;
		while (elementNotFound) {
			try {
				MobileElement elem = findElementBy(locator, element);

				if (elem.isDisplayed()) {
					try {
						elem.click();
						break;
					} catch (WebDriverException e) {
						e.getMessage();
					}
				} else {
					scrolldown(el);
					performPageLoad(driver);
				}
			} catch (NoSuchElementException e) {
				scrolldown(el);
			}
		}
		return element;

	}

	public static void scrolldown(MobileElement el) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put(DURATION, getProperty(DURATION));
		params.put(FROM_X, getProperty(FROM_X));
		params.put(FROM_Y, getProperty(FROM_Y));
		params.put(TO_X, getProperty(TO_X));
		params.put(TO_Y, getProperty(TO_Y));
		((MobileElement) el).getId();
		js.executeScript("mobile: dragFromToForDuration", params);
	}

	public static List<String> retrieveAllCases(List<MobileElement> elements, String split, int index) {
		String[] dest;
		List<String> referrals = new ArrayList<>();
		List<MobileElement> el = elements;
		performPageLoad(driver);
		Iterator<MobileElement> itr = el.iterator();
		while (itr.hasNext()) {
			dest = itr.next().getText().split(split);
			if (index == 0) {
				referrals.add(dest[index].trim());
			} else {
				referrals.add(changeDateFormat(dest[index].trim(), "MM/dd/yyyy", "yyyy/MM/dd"));
			}
		}
		return referrals;

	}

	public static String changeDateFormat(String element, String actualFormat, String modiffiedFormat) {
		format1 = new SimpleDateFormat(actualFormat);
		format2 = new SimpleDateFormat(modiffiedFormat);
		java.util.Date date = null;
		try {
			date = format1.parse(element);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return format2.format(date);

	}

	public static String[] sortArray(String[] arr) {
		String tmp = "";
		String tempValue1 = "";
		String tempValue2 = "";
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i].length() == arr[i + 1].length()) {
				if (arr[i].compareTo(arr[i + 1]) > 1) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
				}
			} else if (arr[i].length() > arr[i + 1].length()) {
				tempValue1 = arr[i].substring(0, arr[i].length() - 2);
				tempValue2 = arr[i + 1];
				while (tempValue1.length() > tempValue2.length()) {
					tempValue1 = tempValue1.substring(0, tempValue1.length() - 1);
				}
				if (tempValue1.compareTo(tempValue2) > 1) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
				}
			} else if (arr[i].length() < arr[i + 1].length()) {
				tempValue1 = arr[i];
				tempValue2 = arr[i + 1].substring(0, arr[i].length() - 1);
				while (tempValue1.length() < tempValue2.length()) {
					tempValue2 = tempValue2.substring(0, tempValue2.length() - 1);
				}
				if (tempValue1.compareTo(tempValue2) > 1) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
				}
			}
		}
		return arr;
	}

	public static String getNumOfDisplayedCases(MobileElement element) {
		return getText(waitForVisibilityOfElement(element, driver)).split(",")[1].split("T")[0].trim();

	}

	public static boolean elementIsDisplayed(DBType dbtype, String query, String xpath) {
		boolean isDisplayed = false;
		MobileElement uiResult = null;
		List<String> dbResult = executeQuery(dbtype, query);
		sort(dbResult);
		try {
			for (int i = 0; i < dbResult.size(); ++i) {

				uiResult = findElementBy(Locator.XPATH, xpath + "[contains(@name, '" + dbResult.get(i) + "')]");

				if (uiResult.isDisplayed())
					isDisplayed = true;
			}
		} catch (AssertionError e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public static void expandPanel(String element) {
		try {

			MobileElement referral = findElementBy(Locator.XPATH, containsElement(element));
			if (referral.isDisplayed() && referral.getText().contains("(")) {
				referral.click();
			}
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
	}

	public static void clickOnRandomValue(List<MobileElement> value) {
		value.get(getRandomInt(value.size())).click();
	}

	public static int getRandomInt(int index) {
		return index - 1 - new Random().nextInt(index);
	}

	public static String getParameter(String value, int index) {
		String[] parValue = value.substring(value.indexOf("(") + 1, value.indexOf(")") - 1).split(",");
		return parValue[index].replaceAll("'", "");

	}

	public static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("MAX MUST BE GREATER THAN MIN");
		}
		return new Random().nextInt((max - min) + 1) + min;
	}

	public static String clickOnNumberInRange(List<MobileElement> value) {
		String text = "";
		int index = getRandomNumberInRange(1, value.size()-1);
		text += value.get(index).getText().trim();
		value.get(index).click();
		return text;
	}

	public static String getStreamOfRandomInts() {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

	}

	public static String splitBy(String string, int index) {
		if (string.trim().contains(" ")) {
			return string.split(" ")[index];
		} else {
			return string.split("-")[index];
		}
	}

	public static void replace(String text) {
		if (text.contains(" "))
			text.replace(" ", "_");
	}

}
