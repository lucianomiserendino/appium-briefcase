package gov.uscourts.ao.moibleBriefcase.common;

import java.util.ArrayList;
import static gov.uscourts.ao.moibleBriefcase.common.Page.*;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class Utilities extends Base {

	public static String getReferralCategoryText(MobileElement elements) {
		return elements.getText();

	}

	public static List<String> getListOfCategories(MobileElement motionsPetitions, MobileElement casesOnCalendar,
			MobileElement petitionsForRehearing, MobileElement screeningPanels) {
		List<String> categories = new ArrayList<>();
		categories.add(getReferralCategoryText(motionsPetitions));
		categories.add(getReferralCategoryText(casesOnCalendar));
		categories.add(getReferralCategoryText(petitionsForRehearing));
		categories.add(getReferralCategoryText(screeningPanels));
		Collections.sort(categories);
		return categories;

	}

	public static void clickOn(WebElement element) {
		element.click();
	}

	public static void tapByCoordinates(int xCoordinates, int yCoordinates) {
		performPageLoad();
		new TouchAction(driver).tap(xCoordinates, yCoordinates).perform();

	}

	public static String getListOfDisplayedCases(MobileElement element, int start, int end) {
		return element.getText().substring(start, end).trim();

	}

}