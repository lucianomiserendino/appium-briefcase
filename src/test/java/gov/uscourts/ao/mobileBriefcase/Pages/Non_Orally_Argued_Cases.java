package gov.uscourts.ao.mobileBriefcase.Pages;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Non_Orally_Argued_Cases extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Vote Information']/following:: XCUIElementTypeStaticText[contains(@name, 'Filed: ')]")
	public static List<WebElement> filed;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Date: ')]")
	public static WebElement date;

	public static String getFileDate() {
	    List<String> dates = new ArrayList<>();

	    for (WebElement file : filed) {
	        String date = splitBy(file, "Filed: ");
	        dates.add(date);
	    }

	    return latestFiledDate(dates);
	}

	public static String latestFiledDate(List<String> dates) {
	    List<Date> dateObjects = new ArrayList<>(dates.size());
	    SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

	    for (String dateStr : dates) {
	        try {
	            Date dateObj = dateFormat.parse(dateStr);
	            dateObjects.add(dateObj);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }

	    String latestDateStr = dateFormat.format(Collections.max(dateObjects));
	    return latestDateStr;
	}

	public static String splitBy(WebElement element, String str) {
	    return Page.waitForVisibilityOfElement(element, driver).getText().split(str)[1].trim();
	}

	public static String getDate() {
	    return splitBy(date, "Date: ");
	}

	public static String getReferralDate(ReferralDate session) {
	    switch (session) {
	        case Banner:
	            return getDate();
	        case Vote_Information:
	            return getFileDate();
	        default:
	            return "";
	    }
	}

	public void ifReferralDateDisplayed(ReferralDate session, String caseNum) {
	    String caseDetailPage = getReferralDate(session);
	    Actions.navigateBack();
	    Page.performPageLoad(driver);

	    String referralListPage = splitBy(Actions.findElement(By.xpath("(//XCUIElementTypeStaticText[contains(@name, '"
	            + caseNum + "')]/following::XCUIElementTypeStaticText[contains(@name, 'Panel:')])[1]")), "Date: ");

	    assertEquals(
	            "PLEASE DOUBLE CHECK THE REFERRAL LIST PAGE. VERIFY THE MOST RECENT REFERRAL DATE DISPLAYS UNDER THE CASE AND SHORT TITLE",
	            caseDetailPage, referralListPage);
	}

	public enum ReferralDate {
	    Banner, Vote_Information
	}


	
}
