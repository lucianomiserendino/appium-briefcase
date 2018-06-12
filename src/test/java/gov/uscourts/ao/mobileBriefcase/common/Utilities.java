package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;


import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.select;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users.APPELLATE_JUDGES;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.*;
import static gov.uscourts.ao.mobileBriefcase.common.Page.pageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import cucumber.api.DataTable;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class Utilities extends Base {

	static SimpleDateFormat format1;
	static SimpleDateFormat format2;

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

	public static  void getPanel(String query, String message, String element) {

		try {
			if (executeQuery(query).size() > 0) {
				assertTrue(message, elementIsDisplayed(element) == true);
				clickOnPanel(element);

			} else {
				assertTrue(!(executeQuery(query).size() > 0));

			}
		} catch (Exception e) {
			e.getStackTrace();

		}

	}

	public static void selectCaseNumber(Actions action, String caseNum) {
		clickOn(findElement(By.xpath(selectReferralCategory(action))));
		performPageLoad();
		selectCase(locateElement(caseNum));

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

	public static void click(String xpath) {
		clickOn(findElement(By.xpath(xpath)));

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

	public static String changeDateFormat(String element, String format) {
		format1 = new SimpleDateFormat("MM/dd/yyyy");
		format2 = new SimpleDateFormat(format);
		java.util.Date date = null;

		try {

			date = format1.parse(element);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return format2.format(date);

	}

	public static String getText(MobileElement elements) {
		return elements.getText().trim();

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
			e.getMessage();
		}

	}

	public static void clickOn(MobileElement element) {

		try {
			if (element.isDisplayed()) {
				element.click();
			}
		} catch (Exception e) {
			e.getMessage();
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

	public static void tapByCoordinate(String xCoordinates, String yCoordinates) {
		new TouchAction(driver).tap(getCoordinates(xCoordinates), getCoordinates(yCoordinates)).perform();

	}

	public static int getCoordinates(String coordinates) {
		return Integer.parseInt(Configuration.getProperty(coordinates));

	}

	public static String  getNumOfDisplayedCases(MobileElement element){
		return getText(element).split(",")[1].split("T")[0].trim();
		
	}

	public static void assertThatDBEqualsToUI(String message, String query, List<String> uiValue) {
		List<String> db = executeQuery(query);
		Collections.sort(db);
		List<String> ui = uiValue;
		assertTrue(message, db.containsAll(ui));

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

	public static String split(String caseNum, String substr, int index) {
		return (caseNum + " ").split(substr)[index].split(" ")[0].trim();

	}

	public static void navigateBack(String back) {
		findElement(By.name(back)).click();
	}

	public static void navigateBack(MobileElement element, Users user) {

		element.click();
		Page.performPageLoad();
		select(user);
		driver.navigate().back();
	}

	public static void selectAUser(Users user) {
		select(user);
		pageLoad();
		select(Users.MOTIONS_PETITIONS);
		select(Users.DASHBOARD);

	}

	public static void getCollapsablePanel(MobileElement element, Users user) {
		selectAUser(Users.DASHBOARD);
		navigateBack(element, user);
	}

	public static String getIndex(DataTable userCredentials, String object) {
		List<Map<String, String>> credentials = userCredentials.asMaps(String.class, String.class);
		return credentials.get(0).get(object);
	}

	public static void getUserCredentials(DataTable userCredentials) {

		if (getIndex(userCredentials, "briefcaseUser").equals(Configuration.getProperty("STAFF_ATTORNEYS"))) {

			select(Users.STAFF_ATTORNEYS);

		} else if (getIndex(userCredentials, "briefcaseUser").equals(Configuration.getProperty("APPELLATE_JUDGES"))) {
			select(APPELLATE_JUDGES);

		} else if (getIndex(userCredentials, "briefcaseUser").equals(Configuration.getProperty("BANKRUPTCY_JUDGES"))) {
			select(Users.BANKRUPTCY_JUDGES);

		}
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

}