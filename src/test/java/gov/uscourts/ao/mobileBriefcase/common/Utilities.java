package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.select;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.pageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfWebElement;
import static java.util.Collections.sort;
import static java.util.Comparator.comparing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import cucumber.api.DataTable;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.Coordinates;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class Utilities extends Base {

	static SimpleDateFormat format1;
	static SimpleDateFormat format2;

	public static List<String> retrieveAllCases(List<MobileElement> elements, String split, int index) {
		String[] dest;
		List<String> referrals = new ArrayList<>();
		List<MobileElement> el = elements;
		pageLoad();
		Iterator<MobileElement> itr = el.iterator();
		while (itr.hasNext()) {
			dest = itr.next().getText().split(split);
			referrals.add(dest[index].trim());

		}
		return referrals;

	}

	public static List<String> retrieveDates(List<MobileElement> elements, String split, int index, String format) {
		String[] dates;
		List<String> referrals = new ArrayList<>();
		pageLoad();
		List<MobileElement> element = elements;

		Iterator<MobileElement> itr = element.iterator();
		while (itr.hasNext()) {
			try {
				dates = itr.next().getText().split(split);
				referrals.add(changeDateFormat(dates[index].trim(), "MM/dd/yyyy", format));

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return referrals;

	}

	public static void getPanel(DBType dbtype, String query, String message, String element) {

		try {
			if (executeQuery(dbtype, query).size() > 0) {
				performPageLoad();
				assertTrue(message, elementIsDisplayed(element));
				clickOnElement(element);
			} else {
				assertTrue(!(executeQuery(dbtype, query).size() > 0));

			}
		} catch (Exception e) {
			e.getStackTrace();

		}

	}

	public static void scroll(int iCount, String direction) {

		while (iCount > 0) {
			HashMap<String, String> swipeObject = new HashMap<String, String>();
			swipeObject.put("direction", direction);

			JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

			jsDriver.executeScript("mobile:scroll", swipeObject);
			iCount--;

		}

	}

	public static void click(String xpath) {
		clickOn(findElement(By.xpath(xpath)));

	}

	public static void selectCase(String xpath) {

		String Case = xpath;

		boolean caseDisplayed = isDisplayed(waitForPresenceOfElement(By.xpath(Case)));

		if (caseDisplayed == true) {
			findElement(By.xpath(Case)).click();
		} else {
			scroll(1, "down");
			findElement(By.xpath(Case)).click();
		}

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

	public static void sendKeys(MobileElement elements, String text) {
		elements.sendKeys(text);

	}

	public static String getText(MobileElement elements) {
		return waitForElement(elements).getText().trim();

	}

	public static WebElement findWebElement(By element) {
		return webDriver.findElement(waitForPresenceOfWebElement(element));

	}

	public static MobileElement findElement(By element) {

		return driver.findElement(waitForPresenceOfElement(element));

	}

	public static String findElementAndGetText(By element) {
		return driver.findElement(waitForPresenceOfElement(element)).getText().trim();

	}

	public static List<MobileElement> findElements(By elements) {
		return driver.findElements(waitForPresenceOfElement(elements));
	}

	public static List<WebElement> findWebElements(By elements) {
		return webDriver.findElements(elements);
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

	public static boolean isDisplayed(MobileElement element) {
		boolean isDisplayed = false;
		try {
			if (waitForElement(element).isDisplayed())
				isDisplayed = true;
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

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

	public static void tapByCoordinate(String xCoordinates, String yCoordinates) {
		new TouchAction(driver).tap(getCoordinates(xCoordinates), getCoordinates(yCoordinates)).perform();

	}

	public static int getCoordinates(String coordinates) {
		return Integer.parseInt(Configuration.getProperty(coordinates));

	}

	public static String getNumOfDisplayedCases(MobileElement element) {
		return getText(waitForElement(element)).split(",")[1].split("T")[0].trim();

	}

	public static void assertThatDBEqualsToUI(String message, DBType dbType, String query, List<String> uiValue) {
		List<String> db = executeQuery(dbType, query);
		Collections.sort(db);
		List<String> ui = uiValue;
		assertTrue(message, db.containsAll(ui));

	}

	public static void logout(String xpath, String id, String name) {
		clickOn(findElement(By.xpath(xpath)));
		clickOn(findElement(By.id(id)));
		clickOn(findElement(By.name(name)));
		clickOn(findElement(By.name(name)));
	}

	public static String split(String caseNum, String substr, int index) {
		return (caseNum + " ").split(substr)[index].split(" ")[0].trim();

	}

	public static void navigateBack(String back) {
		findElement(By.name(back)).click();
	}

	public static void navigateBack(MobileElement element, Coordinates user) {
		element.click();
		Page.performPageLoad();
		select(user);
		driver.navigate().back();
	}

	public static void selectAUser(Coordinates user) {
		select(user);
		pageLoad();
		select(Coordinates.MOTIONS_PETITIONS);
		select(Coordinates.DASHBOARD);

	}

	public static void getCollapsablePanel(MobileElement element, Coordinates user) {
		selectAUser(Coordinates.DASHBOARD);
		navigateBack(element, user);
	}

	public static void getIndexOfDataTable(DataTable table, int index1, int index2) {
		List<Map<Integer, Integer>> credentials = table.asMaps(Integer.class, Integer.class);
		credentials.get(index1).get(index2);
	}

	public static void verifyTextIsDisplayed(MobileElement element, String text) {
		try {
			assertTrue(elementIsPresent(element) == true);

			assertEquals(text, getText(element));

		} catch (AssertionError e) {

			e.printStackTrace();
		}

	}

	public static boolean elementIsPresent(MobileElement element) {
		try {
			element.isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void clickOn(WebElement element) {

		try {
			if (element.isDisplayed()) {
				element.click();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static String getParameter(String value, int index) {
		String[] parValue = value.substring(value.indexOf("(") + 1, value.indexOf(")") - 1).split(",");
		return parValue[index].replaceAll("'", "");

	}

	public static String getStreamOfRandomInts() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);

	}

	public static String getRestrictParam(String param, int index) {
		return param.substring(index).split(",")[0].replaceAll("'", "");
	}

	public static boolean isDisplayed(DBType dbtype, String query, String xpath) {
		boolean isDisplayed = false;
		List<String> dbResult = executeQuery(dbtype, query);
		sort(dbResult);
		try {
			for (int i = 0; i < dbResult.size(); ++i) {

				MobileElement uiResult = findElement(By.xpath(xpath + "[contains(@name, '" + dbResult.get(i) + "')]"));

				if (uiResult.isDisplayed())
					isDisplayed = true;
			}
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

	public static String splitBy(String string, int index) {
		if (string.trim().contains(" ")) {
			return string.split(" ")[index];
		} else {
			return string.split("-")[index];
		}
	}

	public static String replace(String text, String oldText, String newText) {
		return text.replace(oldText, newText);
	}

	public static String replace(String text, String oldText1, String newText1, String oldText2, String newText2) {
		return text.replace(oldText1, newText1).replace(oldText2, newText2);
	}

	public static String replace(String text, String oldText1, String newText1, String oldText2, String newText2,
			String oldText3, String newText3) {
		return text.replace(oldText1, newText1).replace(oldText2, newText2).replace(oldText3, newText3);
	}

	public static String getCurrentDateTime() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

	}

	public static List<String> sortStringListByLength(List<String> list) {
		sort(list, comparing(String::length));
		return list;
	}

	public static int getRandomInt(int index) {
		return index - 1 - new Random().nextInt(index);
	}

	public static void clickOnRandomValue(List<MobileElement> value) {
		value.get(getRandomInt(value.size())).click();
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

	public static void selectCaseNumber(String category, String caseNum) {
		clickOnElement(category);
		performPageLoad();
		// selectCase(caseNum);
		selectCase(locateElement(caseNum));

	}

	public static void selectUser(MobileElement users, By by) {
		scrollDown(users, by);
	}

	@SuppressWarnings("unchecked")
	public static void scrollDown(MobileElement element, By by) {
		while (true) {
			try {

				if (element.isDisplayed()) {
					clickOn(element);

					break;
				} else {

					JavascriptExecutor js = (JavascriptExecutor) driver;
					Map<String, Object> params = setCoordinates();
					SetCapabilitiy(DURATION);
					SetCapabilitiy(FROM);
					SetCapabilitiy(FROM);
					SetCapabilitiy(TO);
					SetCapabilitiy(TO);
					params.put("element", ((MobileElement) driver.findElement(by)).getId());
					js.executeScript("mobile: dragFromToForDuration", params);
				}
			} catch (Exception NoSuchElementException) {
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void SetCapabilitiy(Object value) {
		setCoordinates().put(Configuration.getProperty((String) value), Configuration.getProperty((String) value));
	}

	@SuppressWarnings("rawtypes")
	public static HashMap setCoordinates() {
		return new HashMap<>();

	}

	public static TouchAction tapByCoordinates(int x, int y) {
		return new TouchAction(driver).tap(x, y).perform();

	}
}