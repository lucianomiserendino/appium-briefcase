package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Configuration.getProperty;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findWebElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findWebElements;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.splitBy;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.uscourts.ao.mobileBriefcase.common.Base;

public class CopyDeleteCase extends Base {

	public static String CASE_NUMBER_OF_THE_CASE_TO_BE_COPIED = "fromCase";

	public static String CASE_NUMBER_TO_BE_USED_FOR_NEW_CASE = "toCase";

	public static String COPY_CASE = "*//input[@value='Copy Case'][2]";

	public static String CONFIRM_COPY_PAGE = "*//b[contains(.,'Confirm Copy Case')]";

	public static String CONFIRM_COPY = "Confirm Copy Case";

	public static String CONFIRM_COPY_BTN = "Copy Case";

	public static String CASE_INFO_REPORT = "*//b[contains(.,'Copy Case Information Report')]";

	public static String COPY_CASE_INFO_REPORT = "Copy Case Information Report";

	public static String CS_DATE_ENTERED = "(//input[@type = 'text'])[1]";

	public static String UPDATED_RECORD = "//strong[contains(text(),'record updated')]";

	public static String UPDATED_RECORD_RESULT = "1 record updated";

	public static String CASE_NUMBER_OF_THE_TASE_TO_BE_DELETED = "*//input[@name='fromCase'])[2]";

	public static String DELETE_CASE = "(*//input[@value='Delete Case'])[2]";

	public static String CONFIRM_DELETE_PAGE = "*//b[contains(.,'Confirm Delete Case')]";

	public static String CONFIRM_DELETE = "Confirm Delete Case";

	public static String CONFIRM_DELETE_BTN = "Delete Case";

	public static String DELETE_CASE_INFO_REPORT_PAGE = "*//b[contains(.,'Delete Case Information Report')]";

	public static String createARandomCase() {
		return new SimpleDateFormat("yy-Hm").format(new Date());
	}

	public static void findElementAndsendKeys(By element, String value) {
		findWebElement(element).sendKeys(value);
	}

	public static void findElementsAndsendKeys(By element, int index, String value) {
		findWebElements(element).get(index).sendKeys(value);
	}

	public static void findElementAndclick(By element) {
		findWebElement(element).click();
	}

	public static WebElement findElementThatcontains(String xpath) {
		return webDriver.findElement(By.xpath("//*[contains(@name, '" + xpath + "')]"));
	}

	public static WebElement findElementThatcontains(String xpath, int index) {
		return webDriver.findElement(By.xpath("//*[contains(@name, '" + xpath + "')][" + index + "]"));
	}

	public static void login(String name,String password) {
		findElementAndsendKeys(By.name("usernameEntered"), getProperty(name));
		findElementAndsendKeys(By.name("password"), getProperty(password));
		findElementAndclick(By.name("SUBMIT2"));
	}
	public static void getURLandLogin(String URL) {
		getUrl(URL);
		login(USERNAME,PASSWORD);
	}

	public static void confirm(String title, String expected, String action) {
		String actual = findWebElement(By.xpath(title)).getText();
		if (actual.equals(expected)) {
			findElementAndclick(By.xpath("*//input[@name='xbox']"));
			findElementAndclick(By.xpath("*//input[@value='" + action + "']"));
		}
	}

	public void copyCaseAndUpdateSingleTableEditor(String caseNum) {

		getURLandLogin(COPY_DEL_CASE);
		findElementsAndsendKeys(By.name(CASE_NUMBER_OF_THE_CASE_TO_BE_COPIED), 0, getProperty(CASE));
		findElementAndsendKeys(By.name(CASE_NUMBER_TO_BE_USED_FOR_NEW_CASE), caseNum);
		findElementAndclick(By.xpath(COPY_CASE));
		confirm(CONFIRM_COPY_PAGE, CONFIRM_COPY, CONFIRM_COPY_BTN);
		assertEquals("******CASE CANNOT BE COPIED, PLEASE ENTER A VALID CASE NUMBER******",
				findWebElement(By.xpath(CASE_INFO_REPORT)).getText(), (COPY_CASE_INFO_REPORT));
		closeWebDriver();

		getURLandLogin(SINGLE_TABLE_EDITOR);
		WebElement element = findElementThatcontains("table");
		new Select(element).selectByValue("case");
		findElementThatcontains("submitButton").click();
		findElementThatcontains("cs_year").sendKeys(splitBy(caseNum, 0));
		findElementThatcontains("cs_number").sendKeys(splitBy(caseNum, 1));
		findElementThatcontains("searchButton").click();
		String case_date_entered = findElementThatcontains("cs_date_entered", 2).getAttribute("value");
		findWebElement(By.xpath(CS_DATE_ENTERED)).clear();
		findWebElement(By.xpath(CS_DATE_ENTERED))
				.sendKeys(case_date_entered.replace(case_date_entered.substring(2, 4), splitBy(caseNum, 0)));
		findElementThatcontains("update1").click();

		assertEquals("*******CASE CANNOT BE UPDATED, PLEASE ENTER A VALID CASE NUMBER********",
				findWebElement(By.xpath(UPDATED_RECORD)).getText().trim(), (UPDATED_RECORD_RESULT));
		closeWebDriver();

		getURLandLogin(DATA_UPDATER);

	}

	public void deleteCase(String caseN) {
		getURLandLogin(COPY_DEL_CASE);
		findElementsAndsendKeys(By.name(CASE_NUMBER_OF_THE_CASE_TO_BE_COPIED), 1, caseN);
		findElementAndclick(By.xpath(DELETE_CASE));
		confirm(CONFIRM_DELETE_PAGE, CONFIRM_DELETE, CONFIRM_DELETE_BTN);

	}

	public static void closeWebDriver() {
		if (webDriver != null) {
			webDriver.close();
		}

	}

}
