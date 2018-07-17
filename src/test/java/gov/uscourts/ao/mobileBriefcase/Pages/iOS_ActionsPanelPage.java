package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_EVENT;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.locateElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.findElement;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.refresh;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCase;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_ActionsPanelPage {

	public iOS_ActionsPanelPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Actions')]")
	public static MobileElement actions;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Motion/Petition")
	public static MobileElement motoionCM5A;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Motions/Petitions")
	public static MobileElement motoionCMKA;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	public void selectCaseNumber(MobileElement category, String caseNum) {
		refresh();
		clickOn(category);
		performPageLoad();
		selectCase(locateElement(caseNum));

	}

	public void verifyActionsPanelIsDisplayed(DBType dbtype, String actionsPanel) {

		getPanel(dbtype, MBR_EVENT, " THERE'RE NO ACTIONS OR MBR_EVENT TABLE IS EMPTY ", actionsPanel);
		
	}

	public void compareApplicableActions(DBType dbtype, String cmr_id) {

		try {

			if (executeQuery(dbtype, MBR_EVENT).size() > 0 && actions.isDisplayed()) {
				getApplicableActions(dbtype, cmr_id);
				getPanel(ACTIONS);
			} else {
				assertTrue(!(executeQuery(dbtype, MBR_EVENT).size() > 0));
			}
		} catch (AssertionError e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();

		}

	}

	public static void getApplicableActions(DBType dbtype, String cmr_id) {

		try {
			if (isDisplayed(dbtype, cmr_id) == false) {
				getPanel(ACTIONS);
				assertTrue(isDisplayed(dbtype, cmr_id) == true);
			} else {
				assertTrue(isDisplayed(dbtype, cmr_id) == true);
			}
		} catch (AssertionError e) {
			e.printStackTrace();

		}

	}

	public static boolean isDisplayed(DBType dbtype, String cmr_id) {
		boolean isDisplayed = false;
		List<String> dbApplicableActions = executeQuery(dbtype, getID(APPLICABLE_ACTIONS, cmr_id));
		sort(dbApplicableActions);
		try {
			for (int i = 0; i < dbApplicableActions.size(); ++i) {

				MobileElement actions = findElement(
						By.xpath("//XCUIElementTypeTable[@name='DocumentList']/child::*//*[contains(@name, '"
								+ dbApplicableActions.get(i) + "')]"));
				if (actions.isDisplayed())
					isDisplayed = true;
			}
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;

	}

}
