package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.dashboard;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.clicksOn;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.stepDefinitions.Document_StepDefinitions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AppliedCasesPage extends Base {

	public AppliedCasesPage() {
		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);

	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[2]")
	public WebElement bookmarkBTN;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Bookmarked'])[2]")
	public WebElement bookOnDashboard;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'linked')]/preceding::XCUIElementTypeStaticText[@name='Viewed'][1]/preceding::XCUIElementTypeStaticText[contains(@name, '-')][1])")
	public static List<WebElement> targetCase;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Applied Referrals']/following::XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> appliedCase;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Viewed']/following::XCUIElementTypeStaticText[contains(@name, '-')]")
	public static List<WebElement> redBullet;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Xamarin_Forms_Platform_iOS_NavigationRenderer_ParentingView']/XCUIElementTypeButton[3]")
	public static WebElement searchIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	public static WebElement searchTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SEARCH']")
	public static WebElement searchBTN;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='On Device']")
	public static WebElement on_device;

	public void getBookmarkedReferral(String caseNumber) {

		try {
			if (bookOnDashboard.isDisplayed()) {

				tap(bookOnDashboard);
				if (isDisplayed(Locator.XPATH, "//XCUIElementTypeOther[@name='ReferralsList']/child::*"
						+ containsElement(caseNumber)) == true) {

					findElementBy(Locator.XPATH, containsElement(caseNumber)
							+ "/following::XCUIElementTypeOther[3]//XCUIElementTypeStaticText").click();
					tap(dashboard);
				}
			}
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
	}

	public void selectDate(String date, String panel) {

		scrollDownIfNotDisplayed("//XCUIElementTypeStaticText[@name='" + date
				+ "']/following::XCUIElementTypeOther/XCUIElementTypeStaticText[@name='" + panel + "']");
	}

	public void getAppliedCaseLink(String caseNumber) {
		verifyAppliedCaseLinkIsDisplayed(caseNumber);
		tap(Locator.XPATH, containsElement(caseNumber));
		tap(bookmarkBTN);
		tap(dashboard);
	}

	public void verifyAppliedCaseLinkIsDisplayed(String caseNumber) {
		assertTrue("******APPLIED CASES LINK ICON DISAPPEARS WHEN BOOKMARKING CASE/REFERRAL******",
				isDisplayed(Locator.XPATH, containsElement(caseNumber)
						+ "/following::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[@name='linked']"));

	}

	public void getAdditionalCaseInfoScreen(String caseNum) {
		performPageLoad(driver);
		CommonPages.getPanel(Panel.valueOf("Applied_Referrals"));
		CommonPages.selectReferral(containsElement(caseNum));
		performPageLoad(driver);
		Actions.isDisplayed(Locator.XPATH, containsElement("Case Caption"));
		Actions.isDisplayed(Locator.XPATH, containsElement("Docket Entries"));
		Actions.isDisplayed(Locator.XPATH, containsElement("Associated Cases"));
	}

	public static List<String> caseList(List<WebElement> element) {
		String[] dest;
		List<String> referrals = new ArrayList<>();
		performPageLoad(driver);
		Iterator<WebElement> itr = element.iterator();
		while (itr.hasNext()) {
			dest = itr.next().getText().split(" ");
			referrals.add(dest[0].trim());
		}

		return referrals;
	}

	public static String getRandomTargetCase(List<String> caseList) {
		int randomCase = Utility.getRandomInt(caseList.size() - 1);

		String caseNum = caseList.get(randomCase);

		Actions.tap(Locator.XPATH, Actions.containsElement(caseNum));

		return caseNum;

	}


	public void changeSiValue(String val, List<UserInputData> userInputData) {
		CommonPages.setValue(val, "briefcaseTargetOnly", userInputData);
	}

	public String getSiVal(List<UserInputData> userInputData) {
		return CommonPages.getSiValue("briefcaseTargetOnly", userInputData);
	}

	public void getSiteTableVariable(String category, List<UserInputData> userInputData) {

		if (getSiVal(userInputData).equals("n")) {
			changeSiValue("y", userInputData);

		} else {
			List<String> target = caseList(targetCase);
			getRandomTargetCase(target);
			DocumentPage.getAppliedCase();

			List<String> applied = caseList(appliedCase);

			changeSiValue("n", userInputData);
			Base.closeIOSDriver();
			getInstance(Driver.IOS);
			CommonPages.selectReferralCategory(category);
			assertTrue((Actions.isDisplayed(Locator.XPATH,
					"//XCUIElementTypeStaticText[@name='Viewed']/following::XCUIElementTypeStaticText[contains(@name, '"
							+ applied + "')]")));
			changeSiValue("y", userInputData);
		}
	}

	public void appliedCaseSearch(String targetCase,String applCase ,List<UserInputData> userInputData) {
		try {
			if (getSiVal(userInputData).equals("y")) {

				clicksOn(searchIcon);

				sendKeys(searchTextField, applCase);
				clicksOn(searchBTN);
				clicksOn(on_device);
				performPageLoad(driver);

				Actions.findElement(By.xpath(Actions.containsElement(applCase))).click();
				assertTrue("------------------> THE USER IS NOT DIRECTED TO THE TARGET CASE REFERRAL DETAIL PAGE", Actions.isDisplayed(
						Locator.XPATH, Actions.containsElement("Sync all documents for case #" + targetCase)));
				

			} else {
				throw new RuntimeException("----------->PLEASE SET THE SITE TABLE VARIABLE \"BRIEFCASETARGETONLY\" TO \"Y\"");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
