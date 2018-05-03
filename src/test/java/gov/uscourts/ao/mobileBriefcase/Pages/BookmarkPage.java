package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.tapByCoordinates;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class BookmarkPage {

	public BookmarkPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='Background'])[4]")
	public static MobileElement referralCategory;

	@iOSFindBy(accessibility = "Pending Tasks")
	public static MobileElement penidngTasks;

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='Background'])[5]")
	public static MobileElement bookmarked1;

	@iOSFindBy(xpath = "(//XCUIElementTypeOther[@name='Background'])[6]")
	public static MobileElement bookmarked2;

	@iOSFindBy(accessibility = "Bookmarked")
	public static MobileElement bookmarked;

	@iOSFindBy(xpath = "//*[contains(@name, 'Total')]")
	public static MobileElement totalNumOfBookMarked;

	public void selectReferralCategory() {
		waitToBeClickable(referralCategory);
	}

	public void tapOnTheBookmark(int row) {

		waitToBeClickable(findElement(By.xpath("(//XCUIElementTypeTable[@name='ReferralsList']/XCUIElementTypeCell["
				+ row + "]//XCUIElementTypeStaticText[@name=''])[1]")));
		retrieveCase(row);
	}

	public void retrieveCase(int row) {
		String caseN = findElement(By.xpath("//XCUIElementTypeTable[@name='ReferralsList']/XCUIElementTypeCell[" + row
				+ "]//*[contains(@name, '-')]")).getText();

		getCaseNum(caseN);

	}

	public void verifyBookmarkedDisplyedOnDashboard() {

		tapByCoordinates("dashboardX", "dashboardY");
		performPageLoad();

		String bookMarkedOnTheDashboard = getText(bookmarked);

		Assert.assertTrue(bookMarkedOnTheDashboard.equals("Bookmarked"));

	}

	public void verifyBookmarkedDisplyedUnderTheRefCategory() {

		try {

			if (bookmarked2.isDisplayed()) {

				waitToBeClickable(bookmarked2);
			} else {

				waitToBeClickable(bookmarked1);
			}
		} catch (Exception e) {

		}

		String totalNumOfBookmarked = totalNumOfBookMarked.getText();
		System.out.println(getCaseNum(totalNumOfBookmarked));
	}

	public static String getCaseNum(String caseNum) {
		return (caseNum + " ").split(" ")[0].trim();

	}

}