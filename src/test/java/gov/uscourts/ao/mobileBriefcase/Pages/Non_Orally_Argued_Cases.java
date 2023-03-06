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

		List<String> dates = new ArrayList<String>();

		for (int i = 0; i < filed.size(); i++) {

			String date = splitBy(filed.get(i), "Filed: ");

			dates.add(date);

		}
		return latestFiledDate(dates);
	}

	/** From Vote Info pane */
	public static String latestFiledDate(List<String> d) {

		List<Date> dates = new ArrayList<>(d.size());

		for (String s : d) {

			try {
				Date dateObj = new SimpleDateFormat("m/d/yyyy").parse(s);

				dates.add(dateObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		String str = new SimpleDateFormat("m/d/yyyy").format(Collections.max(dates));
		return str;

	}

	public static String splitBy(WebElement el, String str) {
		return Page.waitForVisibilityOfElement(el, driver).getText().split(str)[1].trim();
	}

	/** Date’ for the referral that is displayed in the banner on the detail page */
	public static String getDate() {
		return splitBy(date, "Date: ");

	}

	public static String getReferralDate(ReferralDate session) {

		String refDate = "";
		switch (session) {
		case Banner:
			refDate += getDate();
			break;
		case Vote_Information:
			refDate += getFileDate();
			break;

		default:
			break;
		}
		return refDate;

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
