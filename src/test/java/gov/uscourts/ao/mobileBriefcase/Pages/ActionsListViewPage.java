package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ActionsListViewPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='GroupIcon']/following:: XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> documentListAccordionText;

	public void getApplicableActions(String caseNumber, String panel, List<UserInputData> userInputData) {

		if (CommonPages.siVal.equalsIgnoreCase("n")) {
			assertTrue("Site table variable briefcaseCtAdmDkt not being honored",
					!Utility.getWebElementList(documentListAccordionText).contains("Actions"));
		} else {

			scrollDownIfNotDisplayed(containsElement(panel));

			String cmr_id = CommonPages.getCMRID(caseNumber, userInputData);

			actionIsDisplayed(cmr_id, userInputData);
		}
	}

	public static void actionIsDisplayed(String cmr_id, List<UserInputData> userInputData) {

		List<String> dbResult = executeQuery(getID(APPLICABLE_ACTIONS, cmr_id), userInputData);
		sort(dbResult);
		String actionName = "";
		for (int i = 0; i < dbResult.size(); ++i) {
			if (dbResult.get(i).contains("'")) {
				actionName = dbResult.get(i).split("'")[0];
				scrollToAction(actionName);
			} else {
				scrollToAction(dbResult.get(i).trim());

			}
		}
	}

	public static void scrollToAction(String actionName) {

		assertTrue(
				isDisplayed("//XCUIElementTypeOther[@name='DocumentList']//XCUIElementTypeStaticText[contains(@name, '"
						+ actionName + "')]"));
		Utility.tapAndSwipe(Direction.DOWN);

	}

	/**
	 * * this line checks if mbr docWP action is displayed when the me_cav_code is
	 * set to 'judgement'.
	 */
	public void verifyMbrdocWPisDisplayed(List<UserInputData> userInputData) {
		performPageLoad(driver);
		getGroupIcons(GroupIcons.Expand);
		scrollDownIfNotDisplayed(containsElement("Actions"));
		String cmr_id = CommonPages.getCMRID(userInputData);

		List<String> dbResult = executeQuery(getID(APPLICABLE_ACTIONS, cmr_id), userInputData);

		String actionName = "";
		for (int i = 0; i < dbResult.size(); ++i) {
			if (dbResult.get(i).contains("mbr - docWP")) {
				actionName = dbResult.get(i);
			} else if (dbResult.get(i).contains("mbr note DMI")) {
				actionName = dbResult.get(i);
			}

		}

		String elId = getAllColumns(getID(Queries.EL_ID, actionName), userInputData);

		String me_cyv_code = getAllColumns(getID(Queries.me_code, elId), userInputData).trim();

		if (me_cyv_code.equals("-") | me_cyv_code.equals("judgment")) {
			scrollToAction(actionName);
		}
	}
}