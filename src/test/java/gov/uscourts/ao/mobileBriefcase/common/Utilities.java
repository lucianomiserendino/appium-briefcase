package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.select;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.selectReferralCategory;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Page.pageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
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
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
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
		performPageLoad();
		while (itr.hasNext()) {

			dest = itr.next().getText().split(split);
			referrals.add(dest[index].trim());

		}
		return referrals;

	}

	public static List<String> retrieveDates(List<MobileElement> elements, String split, int index, String format) {
		String[] dates;
		List<String> referrals = new ArrayList<>();

		List<MobileElement> element = elements;

		Iterator<MobileElement> itr = element.iterator();
		performPageLoad();
		while (itr.hasNext()) {
			try {

				dates = itr.next().getText().split(split);

				referrals.add(changeDateFormat(dates[index].trim(), format));

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
				assertTrue(message, elementIsDisplayed(element) == true);
				clickOnPanel(element);
			} else {
				assertTrue(!(executeQuery(dbtype, query).size() > 0));

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

	public static void sendKeys(MobileElement elements, String text) {
		elements.sendKeys(text);

	}

	public static String getText(MobileElement elements) {
		return waitForElement(elements).getText().trim();

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

	public static void clickOn(MobileElement element) {

		try {
			if (waitForElement(element).isDisplayed()) {
				element.click();
			} else {
				scroll(1);
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

	public static String getNumOfDisplayedCases(MobileElement element) {
		return getText(waitForElement(element)).split(",")[1].split("T")[0].trim();

	}

	public static void assertThatDBEqualsToUI(String message, String query, List<String> uiValue) {
		List<String> db = executeQuery(DBType.CMKA, query);
		Collections.sort(db);
		List<String> ui = uiValue;
		assertTrue(message, db.containsAll(ui));

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

	public static void getUserCategory(Users userCategory, MobileElement selectUser, Users user) {

		waitToBeClickable(selectUser);

		switch (userCategory) {

		case STAFF_ATTORNEYS:
			select(Users.STAFF_ATTORNEYS);
			break;

		case APPELLATE_JUDGES:
			select(Users.APPELLATE_JUDGES);
			break;

		case BANKRUPTCY_JUDGES:
			select(Users.BANKRUPTCY_JUDGES);
			break;

		default:
			break;
		}
		selectAUser(user);

	}

	public static void refresh() {
		pageLoad();
		select(Users.MOTIONS_PETITIONS);
		select(Users.DASHBOARD);
		performPageLoad();

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

	public static String getStreamOfRandomInts() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);

	}

	public static String getRestrictParam(String param, int index) {
		return param.substring(index).split(",")[0].replaceAll("'", "");
	}

}