package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.DocumentList;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitForVisibilityOfElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.expandPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.findElementAndScrollDown;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrolldown;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;

public class ActionsPage extends AppiumPageFactory {

	public void getApplicableActions(String dbType, String panel, String cmr_id) {
		performPageLoad(driver);

		findElementAndScrollDown(Locator.XPATH, containsElement(panel), DocumentList);
		try {
			expandPanel(panel);
			actionIsDisplayed(dbType, DocumentList, cmr_id);

		} catch (AssertionError e) {
			e.getMessage();
		}
	}

	public static boolean findElementAndScroll(String element, MobileElement el) {
		boolean isDisplayed = false;
		Boolean elementNotFound = true;
		while (elementNotFound) {
			try {
				MobileElement elem = waitForVisibilityOfElement(findElementBy(Locator.XPATH,
						"//XCUIElementTypeOther[@name='DocumentList']//XCUIElementTypeStaticText[contains(@name, '"
								+ element + "')]"),
						driver);
				if (elem.isDisplayed()) {
					isDisplayed = true;
					break;
				} else {
					scrolldown(el);
					performPageLoad(driver);
				}
			} catch (NoSuchElementException e) {
				isDisplayed = false;
			}
		}
		return isDisplayed;
	}

	public static void actionIsDisplayed(String dbtype, MobileElement el, String cmr_id) {

		List<String> dbResult = executeQuery(valueOf(dbtype), getID(APPLICABLE_ACTIONS, cmr_id));
		sort(dbResult);

		String actionName = "";
		for (int i = 0; i < dbResult.size(); ++i) {
			if (dbResult.get(i).contains("'")) {
				actionName += dbResult.get(i).split("'")[0];
				assertTrue(findElementAndScroll(actionName, el));
			} else {
				assertTrue(findElementAndScroll(dbResult.get(i), el));

			}
		}
	}
	


}