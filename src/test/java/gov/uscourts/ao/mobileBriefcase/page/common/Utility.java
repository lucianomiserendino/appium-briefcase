package gov.uscourts.ao.mobileBriefcase.page.common;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElements;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.waitForVisibilityOfElement;
import static java.lang.Integer.parseInt;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
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
				WebElement elem = waitForVisibilityOfElement(findElementBy(Locator.XPATH, element), driver);
				if (elem.isDisplayed()) {
					isDisplayed = true;
					break;
				} else {
					tapAndSwipe(Direction.UP);

					performPageLoad(driver);
				}
			} catch (TimeoutException e) {
				isDisplayed = false;
				tapAndSwipe(Direction.UP);
			}
		}
		return isDisplayed;
	}

	public static String scrollDownIfNotDisplayed(String element) {

		Boolean elementNotFound = true;
		while (elementNotFound) {
			try {

				List<WebElement> elems = findElements(By.xpath(element));
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
					tapAndSwipe(Direction.UP);
					performPageLoad(driver);
				}
			} catch (NoSuchElementException e) {
				tapAndSwipe(Direction.UP);
			}
		}
		return element;

	}

	public static void scroll(WebElement element, String direction) {

		((JavascriptExecutor) driver).executeScript("mobile: swipe", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "speed", 500));

	}

//	public static synchronized void scrolldown() {
//
//		try {
//			int pressX = getWindowSize().width / 2;
//			/** 4/5 of the screen as the bottom finger-press point */
//			int bottomY = getWindowSize().height * 4 / 8;
//			/** just non zero point, as it didn't scroll to zero normally */
//			int topY = getWindowSize().height / 8;
//
//			new TouchAction(driver).longPress(PointOption.point(pressX, bottomY))
//					.moveTo(PointOption.point(pressX, topY)).release().perform();
//		} catch (WebDriverException e) {
//			e.getMessage();
//		}
//	}

	public static Dimension getWindowSize() {
		return driver.manage().window().getSize();
	}

	public static List<String> retrieveAllReferrals(List<WebElement> elements, String split, int index) {
		String[] dest;
		List<String> referrals = new ArrayList<>();
		List<WebElement> el = elements;
		if (el.size() > 0) {
			performPageLoad(driver);
			Iterator<WebElement> itr = el.iterator();
			while (itr.hasNext()) {
				dest = itr.next().getText().split(split);
				referrals.add(dest[index].trim());

			}
		} else {
			throw new RuntimeException("---------------------> REFERRAL DATES ARE MISSING FROM THE CASE ROW");
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

	public static String getNumOfDisplayedCases(WebElement element) {
		return getText(waitForVisibilityOfElement(element, driver)).split(",")[1].split("T")[0].trim();

	}

	public static boolean elementIsDisplayed(String query, String xpath, List<UserInputData> userInputData) {
		boolean isDisplayed = false;
		WebElement uiResult = null;
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

			WebElement referral = findElementBy(Locator.XPATH, "(//XCUIElementTypeStaticText[@name='" + element
					+ "']/following::XCUIElementTypeOther/XCUIElementTypeStaticText)[1]");
			if (referral.isDisplayed() && referral.getText().contains("(")) {
				referral.click();
			}
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
	}

	public static int clickOnRandomValue(List<WebElement> value) {
		int random = getRandomInt(value.size());
		value.get(random).click();
		return random;
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

	public static String clickOnNumberInRange(List<WebElement> value) {
//		String text = "";
//		int index = getRandomNumberInRange(1, value.size() - 1);
//		text += value.get(index).getText().trim();
//		value.get(index).click();
//		return text;
		String text = "";
		int index = getRandomInt(value.size());
		text += value.get(1).getText().trim();
		value.get(1).click();
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

	public static boolean checkDatesForDescOrder(List<String> date, String format) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

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

	public static boolean checkDatesForAscOrder(List<String> date, String format) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

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

			for (int i = 0; i < itemCount; i++) {

				if (items[i].contains(dpfName)) {
					String[] Value = items[i].substring(items[i].indexOf(dpfName + "(")).split("',");
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

	public static synchronized Map<String, String> TableDictionaryConverter(List<List<String>> data) {
		Map<String, String> mapTable = new HashMap<String, String>();
		for (List<String> rows : data) {
			mapTable.put(rows.get(0), rows.get(1));
		}
		return mapTable;
	}
//
//	public void test(DataTable table) {
//		List<List<String>> dataTableRows = table.asLists(); // outer List<> is rows, inner List<> is cells
//		for (List<String> row : dataTableRows) { // loop through every row in the DataTable input
//			int rowIdx = dataTableRows.indexOf(row);
//
//			for (String expectedCell : row) { // loop through every cell in the current DataTable row
//				int cellIdx = row.indexOf(expectedCell);
//
//				System.out.println("DataTable row " + rowIdx + ", cell " + cellIdx + ": " + expectedCell);
//
//			}
//		}
//	}

	public static void tapAndSwipe(Direction dir) {

		System.out.println("swipeScreenSmall(): dir: '" + dir + "'"); // always log your actions

		// - iOS: 200 ms
		// final value depends on your app and could be greater
		final int ANIMATION_TIME = 50; // ms

		final int PRESS_TIME = 50; // ms

		PointOption pointOptionStart, pointOptionEnd;

		// init screen variables
		Dimension dims = driver.manage().window().getSize();

		// init start point = center of screen
		pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

		// reduce swipe move into multiplier times comparing to swipeScreen move
		int mult = 2; // multiplier
		switch (dir) {
		case DOWN: // center of footer
			pointOptionEnd = PointOption.point(dims.width / 2, (dims.height / 2) + (dims.height / 2) / mult);
			break;
		case UP: // center of header
			pointOptionEnd = PointOption.point(dims.width / 2, (dims.height / 2) - (dims.height / 2) / mult);
			break;
		case LEFT: // center of left side
			pointOptionEnd = PointOption.point((dims.width / 2) - (dims.width / 2) / mult, dims.height / 2);
			break;
		case RIGHT: // center of right side
			pointOptionEnd = PointOption.point((dims.width / 2) + (dims.width / 2) / mult, dims.height / 2);
			break;
		default:
			throw new IllegalArgumentException("swipeScreenSmall(): dir: '" + dir.toString() + "' NOT supported");
		}

		// execute swipe using TouchAction
		try {
			new TouchAction(driver).tap(pointOptionStart)
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release()
					.perform();

		} catch (Exception e) {
			System.err.println("swipeScreenSmall(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {
		}
	}

	public enum Direction {
		DOWN, UP, LEFT, RIGHT
	}

	public static boolean getToggleState(WebElement el) {

		boolean status = false;

		if (Actions.attributeEquals(el, "0")) {
			status = false;

		} else if (Actions.attributeEquals(el, "1")) {
			status = true;
		}
		return status;
	}

	public static void swipe(int index, String dir) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		for (int i = 0; i < index; i++) {
			scrollObject.put("direction", dir);
			js.executeScript("mobile: swipe", scrollObject);
		}
	}

	public static void doubleTap(WebElement el) {

		((JavascriptExecutor) driver).executeScript("mobile: doubleTap",
				ImmutableMap.of("elementId", ((RemoteWebElement) el).getId()));
	}

	public static String parseMonthName(String nameOfTheMonth) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(inputFormat.parse(nameOfTheMonth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("M").format(cal.getTime());

	}
}
