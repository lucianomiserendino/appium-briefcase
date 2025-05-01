package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ActionsListViewPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='GroupIcon']/following:: XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> documentListAccordionText;

	public void getApplicableActions(String panel, List<UserInputData> userInputData) {
	    if (CommonPages.siVal.equalsIgnoreCase("n")) {
	        assertSiteTableVariable();
	        return;
	    }

	    String panelXPath = containsElement(panel);
	    
	    Utility.scrollPage("up");
	    
	    scrollDownIfNotDisplayed(panelXPath);
	    

	    String cmrId = CommonPages.getCMRID(userInputData);
	    displayApplicableActions(cmrId, userInputData);
	}

	private void assertSiteTableVariable() {
	    boolean hasActions = Utility.getWebElementList(documentListAccordionText).contains("Actions");
	    assertTrue("Site table variable briefcaseCtAdmDkt not being honored", !hasActions);
	}

	private void displayApplicableActions(String cmrId, List<UserInputData> userInputData) {
	    List<String> dbResults = executeQuery(getID(APPLICABLE_ACTIONS, cmrId), userInputData);
	    if (dbResults.isEmpty()) {
	        System.out.println("No actions found in the database results.");
	        return;
	    }

	    dbResults.stream()
	            .map(action -> action.contains("'") ? action.split("'")[0] : action.trim())
	            .sorted()
	            .forEach(this::verifyActionDisplayed); 
	}

	private void verifyActionDisplayed(String actionName) {
	    String xpath = String.format(
	            "//XCUIElementTypeOther[@name='DocumentList']//XCUIElementTypeStaticText[contains(@name, '%s')]",
	            actionName
	    );
                 
	    assertTrue("Action '"+actionName+"' not found :",isDisplayed(xpath));

	}


	public static void scrollToAction(String actionName) {
		String xpath = String.format(
				"//XCUIElementTypeOther[@name='DocumentList']//XCUIElementTypeStaticText[contains(@name, '%s')]",
				actionName);
		assertTrue(isDisplayed(xpath));
    	Utility.scrollPage("up");

	}

	/**
	 * * this line checks if mbr docWP action is displayed when the me_cav_code is
	 * set to 'judgement'.
	 */
	public void verifyMbrdocWPisDisplayed(List<UserInputData> userInputData) {
		if (CommonPages.siVal.equalsIgnoreCase("n")) {
			assertSiteTableVariable();
		} else {
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
}