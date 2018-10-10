package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;


import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_EVENT;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Page;
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

	public void verifyActionsPanelIsDisplayed(DBType dbtype, String actionsPanel) {
		getPanel(dbtype, MBR_EVENT, " THERE'RE NO ACTIONS OR MBR_EVENT TABLE IS EMPTY ", actionsPanel);

	}

	public void compareApplicableActions(DBType dbtype, String cmr_id) {

		try {

			if (executeQuery(dbtype, MBR_EVENT).size() > 0 && actions.isDisplayed()) {
				getApplicableActions(dbtype, cmr_id);
				getPanel(ACTIONS);
			} else {
				assertFalse(executeQuery(dbtype, MBR_EVENT).size() > 0);
			}
		} catch (AssertionError e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();

		}

	}

	public static void getApplicableActions(DBType dbtype, String cmr_id) {

		try {
			Page.performPageLoad();
			if (getActions(dbtype, cmr_id) == false) {
				getPanel(ACTIONS);
				assertTrue(getActions(dbtype, cmr_id));
			} else {
				Page.performPageLoad();
				assertTrue(getActions(dbtype, cmr_id));
			}
		} catch (AssertionError e) {
			e.printStackTrace();

		}

	}

	public static Boolean getActions(DBType dbtype, String cmr_id) {
		return isDisplayed(dbtype, getID(APPLICABLE_ACTIONS, cmr_id),
				"//*[contains(@name, 'Actions')]/following:: XCUIElementTypeCell//*");
	}

}
