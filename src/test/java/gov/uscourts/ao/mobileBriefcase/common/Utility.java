package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForVisibilityOfElement;
import static java.lang.Integer.parseInt;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class Utility extends Base {

	static SimpleDateFormat format1;
	static SimpleDateFormat format2;

	public static void tapByCoordinate(String xCoordinates, String yCoordinates) {
		new TouchAction(driver)
				.tap(new PointOption().withCoordinates(getCoordinates(xCoordinates), getCoordinates(yCoordinates)))
				.perform();
	}

	public static int getCoordinates(String coordinates) {
		return parseInt(getProperty(coordinates));

	}

	public static boolean isDisplayed(String element) {
		boolean isDisplayed = false;
		Boolean elementNotFound = true;
		while (elementNotFound) {
			try {
				MobileElement elem = waitForVisibilityOfElement(findElementBy(Locator.XPATH, element), driver);
				if (elem.isDisplayed()) {
					isDisplayed = true;
					break;
				} else {
					scrolldown();
					performPageLoad(driver);
				}
			} catch (NoSuchElementException e) {
				isDisplayed = false;
				scrolldown();
			}
		}
		return isDisplayed;
	}

	public static String scrollDownIfNotDisplayed(String element) {

		Boolean elementNotFound = true;
		while (elementNotFound) {
			try {

				List<MobileElement> elems = findElements(By.xpath(element));
				if (elems.size() == 1) {

					try {
						elems.get(0).click();
						break;
					} catch (WebDriverException e) {
						e.getMessage();
					}

				} else if (elems.size() > 1) {
					elems.get(elems.size() - 1).click();
					break;
				} else {
					scrolldown();
					performPageLoad(driver);
				}
			} catch (NoSuchElementException e) {
				scrolldown();
			}
		}
		return element;

	}

	public static void scrollUp(By by) {
		MobileElement element = Page.waitForPresenceOfElementLocated(by, driver);
		String elementID = element.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", elementID);
		scrollObject.put("direction", "up");
		driver.executeScript("mobile:scroll", scrollObject);
	}

	public static void scrollDown(By by) {
		MobileElement element = Page.waitForPresenceOfElementLocated(by, driver);
		String elementID = element.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", elementID);
		scrollObject.put("direction", "down");
		driver.executeScript("mobile:scroll", scrollObject);
	}

	public static synchronized void scrolldown() {
		try {
			int pressX = getWindowSize().width / 2;
			/** 4/5 of the screen as the bottom finger-press point */
			int bottomY = getWindowSize().height * 4 / 8;
			/** just non zero point, as it didn't scroll to zero normally */
			int topY = getWindowSize().height / 8;
			new TouchAction(driver).longPress(PointOption.point(pressX, bottomY))
					.moveTo(PointOption.point(pressX, topY)).release().perform();
		} catch (WebDriverException e) {
			e.getMessage();
		}
	}

	public static Dimension getWindowSize() {
		return driver.manage().window().getSize();
	}

	public static List<String> retrieveAllCases(List<MobileElement> elements, String split, int index) {
		String[] dest;
		List<String> referrals = new ArrayList<>();
		List<MobileElement> el = elements;
		performPageLoad(driver);
		Iterator<MobileElement> itr = el.iterator();
		while (itr.hasNext()) {
			dest = itr.next().getText().split(split);
			referrals.add(dest[index].trim());
		}

		return referrals;

	}

	public static String changeDateFormat(String element, String actualFormat, String modiffiedFormat) {
		format1 = new SimpleDateFormat(actualFormat);
		format2 = new SimpleDateFormat(modiffiedFormat);
		java.util.Date date = new Date();
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

	public static boolean elementIsDisplayed(String query, String xpath, List<UserInputData> userInputData) {
		boolean isDisplayed = false;
		MobileElement uiResult = null;
		List<String> dbResult = executeQuery(query, userInputData);
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

			MobileElement referral = findElementBy(Locator.XPATH, "(//XCUIElementTypeStaticText[@name='" + element
					+ "']/following::XCUIElementTypeOther/XCUIElementTypeStaticText)[1]");
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

	public static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("MAX MUST BE GREATER THAN MIN");
		}
		return new Random().nextInt((max - min) + 1) + min;
	}

	public static String clickOnNumberInRange(List<MobileElement> value) {
		String text = "";
		int index = getRandomNumberInRange(1, value.size() - 1);
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

	public static String toArray(List<String> num) {
		String id = "";
		Object[] objects = num.toArray();
		for (Object obj : objects)
			id += obj;
		return id;
	}

	public static boolean isSorted(String sort, List<String> listOfStrings) {
		return isSortedinDescOrder(listOfStrings, listOfStrings.size());

	}

	public static boolean isSortedinDescOrder(List<String> listOfStrings, int index) {
		if (index < 2) {
			return true;
		} else if (listOfStrings.get(index - 1).compareTo(listOfStrings.get(index - 2)) > 0) {
			// asc oredr } else if (listOfStrings.get(index -
			// 2).compareTo(listOfStrings.get(index - 1)) > 0) {
			return false;
		} else {
			return isSortedinDescOrder(listOfStrings, index - 1);
		}
	}

	public static boolean checkDatesForDescOrder(List<String> date) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy");

		boolean decendingOrder = true;

		for (int index = 0; index < date.size() - 1; index++) {
			try {
				long d = simpleDateFormat.parse(date.get(index)).getTime();
				long d1 = simpleDateFormat.parse(date.get(index + 1)).getTime();

				if (d < d1) {
					decendingOrder = false;
					break;
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (decendingOrder) {

			return decendingOrder;
		} else {
			System.out.println("The dates are not sorted in descending order:---------> " + date);
			return false;
		}

	}

	public static boolean checkDatesForAscOrder(List<String> date) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy");

		boolean decendingOrder = true;

		for (int index = 0; index < date.size() - 1; index++) {
			try {
				long d = simpleDateFormat.parse(date.get(index)).getTime();
				long d1 = simpleDateFormat.parse(date.get(index + 1)).getTime();

				if (d > d1) {
					decendingOrder = false;
					break;
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (decendingOrder) {

			return decendingOrder;
		} else {
			System.out.println("The dates are not sorted in ascending order:---------> " + date);
			return false;
		}

	}

	public static void isSorted(String sort, List<String> list, List<String> listTwo) {

		Comparator<String> primaryComparator = (a, b) -> Integer.valueOf(a.split("-")[0])
				.compareTo(Integer.valueOf(b.split("-")[0]));

		Comparator<String> secondaryComparator = (a, b) -> Integer.valueOf(a.split("-")[1])
				.compareTo(Integer.valueOf(b.split("-")[1]));

		if (sort.equals("Asc")) {

			listTwo.sort(primaryComparator.thenComparing(secondaryComparator));

			assertEquals("The referrals are not sorted by case number in ascending order: " + list, list, listTwo);

		} else {

			listTwo.sort(primaryComparator.thenComparing(secondaryComparator).reversed());

			assertEquals("The referrals are not sorted by case number in descending order: " + list, list, listTwo);

		}
	}

	public static List<Integer> getCellCount(int time, int navCellSize) {
		List<Integer> cellSize = new ArrayList<>();
		for (int i = time; i < navCellSize; i++) {
			cellSize.add(i);
		}
		return cellSize;
	}

	public static String getParameter(String param, String dpfName, int index) {

		String dpfParam = "";
		String[] items = param.split(";");
		int itemCount = items.length;
		if (itemCount > 1) {
			String[] charac = param.split(";");
			for (int i = 0; i < charac.length; i++) {

				if (charac[i].contains(dpfName)) {
					String[] Value = charac[i].substring(charac[i].indexOf(dpfName + "(")).split("',");
					dpfParam = Value[index].split("'")[1];
				}
			}
		} else {
			String[] charac = param.substring(param.indexOf(dpfName + "(")).split(",");
			if (charac[index].contains("'")) {
				// dpfParam += charac[index].replaceAll("'", "").trim();
				dpfParam = charac[index].replaceAll("'", "").split("\\(")[1].trim();
			}
		}
		return dpfParam;
	}

	public static String getSingleDpf(String value, String dpfName, int index) {
		String[] parValue = value.substring(value.indexOf(dpfName + "(")).split("',");
		return parValue[index].split("'")[1];

	}

}
