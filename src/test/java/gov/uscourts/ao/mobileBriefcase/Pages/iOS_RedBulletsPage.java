package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Base.getInstance;
import static gov.uscourts.ao.mobileBriefcase.common.Base.safariInstance;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.logout;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.refresh;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.scroll;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCase;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Base.Drivers;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class iOS_RedBulletsPage {

	public iOS_RedBulletsPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	private static String settingsPage = "//*[contains(@name, 'NavigationRenderer')]/XCUIElementTypeButton[2]";
	private static String logout = "Logout of Briefcase";
	private static String OKBtn = "OK";
	String back = "Back";

	public static void verifyRedBullet(RedBullet dispayed, String value) {
		switch (dispayed) {
		case IS_DISPLAYED:
			verifyRedBulletIsDisplayed(value);
			break;
		case IS_NOT_DISPLAYED:
			verifyRedBulletIsNotDisplayed(value);
			break;
		default:
			break;
		}
	}

	public static void verifyRedBulletIsDisplayed(String value) {
		performPageLoad();
		assertTrue(getRedBullet(value));
	}

	public static void verifyRedBulletIsNotDisplayed(String value) {
		performPageLoad();
		assertFalse(getRedBullet(value));
	}

	public static boolean getRedBullet(String value) {
		return elementIsDisplayed(value);
	}

	public static String getDocument(String autoSync) {
		return autoSync + "')]/preceding-sibling::XCUIElementTypeStaticText[contains(@name, 'Viewed";

	}

	public static String getRefferal(String caseNum) {
		String sibling = "";
		sibling += "following";
		String viewed = caseNum + "')]/" + sibling + "-sibling::XCUIElementTypeStaticText[contains(@name, 'Viewed";
		if (elementIsDisplayed(viewed)) {
			return viewed;
		} else {
			return viewed.replace(sibling, "preceding");
		}

	}

	public void getReferral(String category, String verify, String caseNum) {
		refresh();
		clickOnElement(category);
		performPageLoad();
		verifyRedBullet(RedBullet.valueOf(verify), getRefferal(caseNum));
		selectCase(locateElement(caseNum));
	}

	public void downloadTheDocument(String category, String autosync) {
		assertRedBullet(category, autosync, RedBullet.IS_DISPLAYED);
		click(locateElement(autosync));
		performPageLoad();
		driver.closeApp();
		getInstance(Drivers.IOS);

	}

	public void logOut() {
		logout(settingsPage, logout, OKBtn, OKBtn);
		safariInstance();
	}

	public void assertRedBulletIsNotDisplayed(String autosync) {
		assertRedBullet("Briefs", autosync, RedBullet.IS_NOT_DISPLAYED);
		clickOnElement(back);

	}

	public static String getCaseNum() {
		return getText("Case #").split("#")[1].trim();
	}

	public static void assertRedBullet(String panel, String penlRow, RedBullet displayed) {

		/** if auto is Displayed */
		if (elementIsDisplayed(penlRow) == true) {
			verifyRedBullet(displayed, getDocument(penlRow));

			/** if auto is Brief is Displayed but auto is not Displayed */
		} else if (elementIsDisplayed(panel) == true && elementIsDisplayed(penlRow) == false) {
			click(locateElement(panel));
			verifyRedBullet(displayed, getDocument(penlRow));

		} else {
			scroll(1, "down");
			if (elementIsDisplayed(penlRow) == true) {
				verifyRedBullet(displayed, getDocument(penlRow));

				/** if auto is Brief is Displayed but auto is not Displayed */
			} else if (elementIsDisplayed(panel) == true && elementIsDisplayed(penlRow) == false) {
				scroll(1, "down");
				click(locateElement(panel));
				verifyRedBullet(displayed, getDocument(penlRow));

			}
		}
	}

	public enum RedBullet {
		IS_DISPLAYED, IS_NOT_DISPLAYED
	}

}
