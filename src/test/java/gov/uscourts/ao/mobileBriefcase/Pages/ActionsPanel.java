package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;

import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Page.waitToBeClickable;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import static java.util.Collections.*;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.*;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ActionsPanel {

	public ActionsPanel() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(xpath = "//*[contains(@name, 'Actions')]")
	public static MobileElement actions;

	public void verifyActionsPanelIsDisplayed(String actionsPanel) {

		performPageLoad();
		try {
			if (actions.isDisplayed()) {

				assertTrue(split(actions.getText(), " ", 1).equals(actionsPanel));
				waitToBeClickable(actions);
			}
		} catch (Exception e) {

		}

	}

	public void compareApplicableActions() {

		List<String> dbApplicableActions = executeQuery(Queries.APPLICABLE_ACTIONS);
		sort(dbApplicableActions);

		assertTrue("------APPLICABLE ACTIONS MISMACTH------", dbApplicableActions.containsAll(getUiApplicableAction()));
		waitToBeClickable(actions);
	}

	public static List<String> getUiApplicableAction() {

		List<String> uiApplicableActions = new ArrayList<>();

		for (int i = 2; i <= 12; ++i) {

			String actions = driver
					.findElement(By.xpath("//XCUIElementTypeTable[@name='DocumentList']/XCUIElementTypeCell[" + i
							+ "]/XCUIElementTypeStaticText[1]"))
					.getText().trim();
			uiApplicableActions.add(actions);
			sort(uiApplicableActions);
		}
		return uiApplicableActions;
	}

}
