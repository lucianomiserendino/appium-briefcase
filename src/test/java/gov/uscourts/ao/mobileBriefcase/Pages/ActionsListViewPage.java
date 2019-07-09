package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;

public class ActionsListViewPage extends AppiumPageFactory {

	public void getApplicableActions(String dbType, String panel, String cmr_id) {
		performPageLoad(driver);
		getGroupIcons();
		scrollDownIfNotDisplayed(containsElement(panel));
		actionIsDisplayed(dbType, cmr_id);

	}

	public static void actionIsDisplayed(String dbtype, String cmr_id) {

		List<String> dbResult = executeQuery(valueOf(dbtype), getID(APPLICABLE_ACTIONS, cmr_id));
		sort(dbResult);
		String actionName = "";
		for (int i = 0; i < dbResult.size(); ++i) {
			if (dbResult.get(i).contains("'")) {
				actionName += dbResult.get(i).split("'")[0];
				scrollToAction(actionName);
			} else {
				scrollToAction(dbResult.get(i));
			}
		}
	}

	public static void scrollToAction(String actionName) {
		assertTrue(
				isDisplayed("//XCUIElementTypeOther[@name='DocumentList']//XCUIElementTypeStaticText[contains(@name, '"
						+ actionName + "')]"));
	}
}