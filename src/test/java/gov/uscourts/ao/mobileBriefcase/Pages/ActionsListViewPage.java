package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.isDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ActionsListViewPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='GroupIcon']/following:: XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> documentListAccordionText;

	public void getApplicableActions(String panel, List<UserInputData> userInputData) {
		if (CommonPages.siVal.equalsIgnoreCase("n")) {
			assertSiteTableVariable();
			return;
		}

		String panelXPath = "(" + containsElement(panel)
				+ "/following:: XCUIElementTypeStaticText[contains(@name, '(')])[1]";

		int actionSize = Integer
				.parseInt(scrollDownIfNotDisplayed(panelXPath).replace("(", "").replace(")", "").trim());
		String cmrId = CommonPages.getCMRID(userInputData);
		List<String> dbResults = executeQuery(getID(APPLICABLE_ACTIONS, cmrId), userInputData);
		if (dbResults.size() != actionSize) {
			throw new AssertionError("Mismatch in Action count: expected " + actionSize + " from UI, but found "
					+ dbResults.size() + " in the database.");
		}

		verifyRandomActions(dbResults);
	}

	private void verifyRandomActions(List<String> dbResults) {
		Random random = new Random();
		Set<Integer> indices = new HashSet<>();

		while (indices.size() < 3 && indices.size() < dbResults.size()) {
			indices.add(random.nextInt(dbResults.size()));
		}

		indices.forEach(index -> {
			String actionName = dbResults.get(index);
			verifyActionDisplayed(actionName);
		});
	}

	public static String scrollDownIfNotDisplayed(String elementXPath) {
		int scrollCount = 0;
		while (scrollCount < 3) {
			List<WebElement> elements = Actions.findElements(By.xpath(elementXPath));
			if (!elements.isEmpty()) {
				try {
					WebElement targetElement = elements.get(elements.size() - 1);
					String text = targetElement.getText();
					targetElement.click();
					return text;
				} catch (WebDriverException e) {
					System.err.println("Error clicking element: " + e.getMessage());
				}
			} else {
				Utility.scrollPage("up");
				Page.performPageLoad(driver);
				scrollCount++;
			}
		}
		return null;
	}

	private void assertSiteTableVariable() {
		boolean hasActions = Utility.getWebElementList(documentListAccordionText).contains("Actions");
		assertTrue("Site table variable briefcaseCtAdmDkt not being honored", !hasActions);
	}


	private void verifyActionDisplayed(String actionName) {
		String xpath = String.format(
				"//XCUIElementTypeOther[@name='DocumentList']//XCUIElementTypeStaticText[contains(@name, '%s')]",
				actionName);

		assertTrue("Action '" + actionName + "' not found :", isDisplayed(xpath));

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
				for (String result : dbResult) {
				    if (result.contains("mbr - docWP") || result.contains("mbr note DMI")) {
				        actionName = result;
				    }
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