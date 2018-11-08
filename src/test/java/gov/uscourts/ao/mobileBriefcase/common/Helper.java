package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.MobileElement;

public class Helper {

	public static String locateElement(String element) {
		return "//*[contains(@name, '" + element + "')]";
	}

	public static boolean elementIsDisplayed(String element) {
		return isDisplayed(By.xpath(locateElement(element)));
	}

	public static String getText(String element) {
		return findElement(By.xpath(locateElement(element))).getText();

	}

	public static void clickOnElement(String element) {
		if (elementIsDisplayed(element) == true)
			;
		click(locateElement(element));

	}

	public static void clickOnPanel(Actions panel, String penlRow) {
		if (elementIsDisplayed(penlRow) == true) {
			click(locateElement(penlRow));
		} else {
			try {
				getPanel(panel);
				click(locateElement(penlRow));
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}

		}

	}

	/** Panel name, actions */
	public static void getPanel(Actions panel, List<MobileElement> actions, String element) {

		getPanel(panel);
		try {
			for (MobileElement e : actions) {
				if (e.getText().contains(element) == true) {
					e.click();
				} else {
					getPanel(panel);

				}
			}
		} catch (NullPointerException e) {
			e.getMessage();
		}

	}

	public static void getPanel(Actions action) {

		switch (action) {
		case ASSIGNMENTS:
			click(locateElement("Assignments"));
			break;

		case ACTIONS:

			click(locateElement("Actions"));
			break;

		case VOTE_INFORMATION:
			click(locateElement("Vote Information"));
			break;

		case BRIEFS:
			click(locateElement("Briefs"));
			break;

		default:
			break;
		}

	}

	public static String getPanelText(Actions action, String element) {
		if (elementIsDisplayed(element) == true) {
			return getText(element);
		} else {
			getPanel(action);
			return getText(element);
		}
	}

	public enum Actions {

		SORT_DATES_IN_ASCENDING_ORDER, SORT_DATES_IN_DESCENDING_ORDER, SORT_CASES_IN_ASCENDING_ORDER, SORT_CASES_IN_DESCENDING_ORDER,

		/** Referral categories */
		PENDING_TASKS, PETITIONS_FOR_REHEARING, CASES_ON_CALENDAR, MOTIONS_PETITIONS, SCREENING_PANELS,

		/** Panels */
		ASSIGNMENTS, VOTE_INFORMATION, ACTIONS, JUDGMENT_FILED, PETITION_FILED, BRIEFS,

		/** Judge's initials */
		SMC, RLW, RWG,

		FILERs_INFORMATION,

		SPLIT_BY_COMMA, REPLACE, SPLIT_BY_SPACE

	}

}
